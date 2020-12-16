/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package CAAYcyclic.PlannerClient.controller.content;

import CAAYcyclic.PlannerClient.api.ApiManager;
import CAAYcyclic.PlannerClient.api.delegate.ApiActivityDelegate;
import CAAYcyclic.PlannerClient.model.Activity;
import CAAYcyclic.PlannerClient.builder.AlertDialog.impl.AlertDialogBuilder;
import CAAYcyclic.PlannerClient.model.Parcel;
import CAAYcyclic.PlannerClient.model.Procedure;
import CAAYcyclic.PlannerClient.model.ProceduresList;
import CAAYcyclic.PlannerClient.view.panel.component.RoundedJTextArea;
import CAAYcyclic.PlannerClient.view.panel.component.ToggleSwitch;
import CAAYcyclic.PlannerClient.view.panel.content.ActivityFormPanel;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import java.util.logging.Logger;
import javax.swing.DefaultComboBoxModel;
import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JLabel;

/**
 *
 * @author Amos
 */
public class ActivityAddFormPanelController extends ContentPanelController{
    
      private static final Logger LOG = Logger.getLogger(ActivityAddFormPanelController.class.getName());
      private ActivityFormPanel activityForm;
      
      private RoundedJTextArea descriptionTextArea;
      private ToggleSwitch interrToggleBtn;
      private JLabel ETAValueLabel;	    
      private JLabel  WeekValueLabel;		
      private JButton saveButton;
      private JComboBox procComboBox;
      
      private ProceduresList proceduresParcel;
      private List<Procedure> procedures;
      
      
      
    public ActivityAddFormPanelController() {
        super();
        setContentPanel(ActivityFormPanel.class);
        initComponent();
    }     
       
    @Override
    public void panelWillAppear() {
        super.panelWillAppear();
         if(getParcels() != null){ 
             
            proceduresParcel = new ProceduresList();
             
            proceduresParcel.createFromParcel(getParcels()
                    .get(proceduresParcel.getParcelableDescription())); 
            
            DefaultComboBoxModel model = (DefaultComboBoxModel) procComboBox.getModel();
            Iterator<Parcel> it = proceduresParcel.getList().iterator();
         
            while (it.hasNext()) {
                     Parcel procPars = it.next(); 
                     Procedure proc = new Procedure();
                     proc.createFromParcel(procPars);
                     
                     procedures.add(proc);                                         
                     model.addElement(proc.getId()+" - "+proc.getTitle());
            }
         }
         else{
             procComboBox.setEnabled(false);
             
         }
         
        
    }
    
    private void initComponent() {
        LOG.log(java.util.logging.Level.CONFIG, "Init Activity panel component into controller.");
        this.activityForm = (ActivityFormPanel) getPanel();
        
        descriptionTextArea = activityForm.getDescriptionTextArea();
        interrToggleBtn = activityForm.getInterrToggleBtn();
        ETAValueLabel = activityForm.getETAValueLabel();	    
        WeekValueLabel = activityForm.getWeekValueLabel();	
        saveButton = activityForm.getSaveButton();        
        procComboBox = activityForm.getProcComboBox();
        procComboBox.addItem("None");
        
        procedures = new ArrayList<>();

        setButtonAction();
    }
    
    private void setButtonAction() {
        saveButton.addMouseListener(saveBtnAction);
    }
    
    private MouseAdapter saveBtnAction = new MouseAdapter() {
        @Override
        public void mousePressed(MouseEvent mouseEvent) {
            super.mousePressed(mouseEvent);
            LOG.log(java.util.logging.Level.INFO, "Start saving action.");
            startSavingActivity();
        }
    };
    
    private Activity generateActivity() {
               
        Activity activity = new Activity();       
        
        activity.setDescription(descriptionTextArea.getText());
        activity.setInterruptable(interrToggleBtn.isActivated());
        activity.setWeek(Integer.parseInt(WeekValueLabel.getText()));
        activity.setEstimatedTime(Integer.parseInt(ETAValueLabel.getText()));      

        
        if(procComboBox.getSelectedIndex()!=0)
            activity.setProcedureId((procedures.get(procComboBox.getSelectedIndex()-1)
                .getId()));
        
        return activity;
    }
    
    private void startSavingActivity() {
        Activity activity = generateActivity();
        if(activity != null){
            activityForm.setSavingText();
            ApiManager.getIstance().createActivity(activity, apiDelegate);
        }
    }
        
    private void endSavingActivity() {
      //  NavigationController.getInstance().unlockNavigation();
        activityForm.setSaveText();
    }
        
     private ApiActivityDelegate apiDelegate = new ApiActivityDelegate() {
        @Override
        public void onGetAllSuccess(List<Activity> activities) {
            endSavingActivity();
        }

        @Override
        public void onGetSuccess(Activity activity) {
            endSavingActivity();
        }

        @Override
        public void onFailure(String message) {
            endSavingActivity();
            showError(message);
        }

        @Override
        public void onCreateSuccess() {
            endSavingActivity();
            showConfirmationMessage("Activity successfully added");
            getCoordinator().popBack();
        }
    };
     
     private void showError(String message){
        AlertDialogBuilder alertBuilder = new AlertDialogBuilder();
        alertBuilder.setTitle("Error");
        alertBuilder.setMessage(message);
        alertBuilder.setDefaultPositiveAction();
        getCoordinator().showAlert(alertBuilder);
    }
     
     private void showConfirmationMessage(String message){
        AlertDialogBuilder alertBuilder = new AlertDialogBuilder();
        alertBuilder.setTitle("Confirmation Message");
        alertBuilder.setMessage(message);
        alertBuilder.setDefaultPositiveAction();
        getCoordinator().showAlert(alertBuilder);
        
    }
    
    @Override
    public Logger getLogger() {
       return LOG;
    }
    
}
