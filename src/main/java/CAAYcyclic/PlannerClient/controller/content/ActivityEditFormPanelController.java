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
import java.util.Objects;
import java.util.logging.Logger;
import javax.swing.DefaultComboBoxModel;
import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JLabel;
import javax.swing.JTextField;

/**
 *
 * @author Amos
 */
public class ActivityEditFormPanelController extends ContentPanelController{
    
      private static final Logger LOG = Logger.getLogger(ActivityEditFormPanelController.class.getName());
      private ActivityFormPanel activityForm;
      
      RoundedJTextArea descriptionTextArea;
      ToggleSwitch interrToggleBtn;
      JLabel ETAValueLabel;	    
      JLabel  WeekValueLabel;	
      JTextField idMaintField;	
      JButton saveButton;
      JComboBox procComboBox;
      
      
      Activity activityToEdit;
      ProceduresList proceduresParcel;
      ArrayList<Procedure> procedures;
      
      
      
      
    public ActivityEditFormPanelController() {
        super();
        setContentPanel(ActivityFormPanel.class);
         initComponent();
    }

    public ActivityFormPanel getActivityForm() {
        return activityForm;
    }
      
    @Override
    public void panelWillAppear() {
        super.panelWillAppear();
        
        if(getParcels() != null){
        
            activityToEdit = new Activity();
            proceduresParcel = new ProceduresList();
            
            activityToEdit.createFromParcel(getParcels()
                    .get(activityToEdit.getParcelableDescription()));
            
           try{ proceduresParcel.createFromParcel(getParcels()
                    .get(proceduresParcel.getParcelableDescription()));
           }
           catch(Exception e){
               proceduresParcel = null;
           }
                       
            
            fillPanel();
        }
        else{
             procComboBox.setEnabled(false);
         
         }
    
    }
     
    public void fillPanel() {
        setDescriptionTitle(activityToEdit.getDescription());
        setETAValue(activityToEdit.getEstimatedTime());
        setWeekValue(activityToEdit.getWeek());
        setToggleButtonInter(activityToEdit.isInterruptable());
        
        if(proceduresParcel != null){
         DefaultComboBoxModel model = (DefaultComboBoxModel) procComboBox.getModel();
         Iterator<Parcel> it = proceduresParcel.getList().iterator();
         
         while (it.hasNext()) {
                     Parcel procPars = it.next(); 
                     Procedure proc = new Procedure();
                     proc.createFromParcel(procPars);
                     
                     procedures.add(proc);
                     
                     model.addElement(proc.getId()+" - "+proc.getTitle());
                     if(Objects.equals(proc.getId(), activityToEdit.getProcedureId())){
                        model.setSelectedItem(proc.getId()+" - "+proc.getTitle());
                     }
                     
         }
         
        }
        else{
            procComboBox.setEnabled(false);
        }
         
      //    procComboBox.setSelectedItem();
                
    }

    public void setActivityToEdit(Activity activityToEdit) {
        this.activityToEdit = activityToEdit;
    }
 
    @Override
    public void panelDidAppear() {
        super.panelDidAppear();
       
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

    public void setDescriptionTitle(String title){
        this.descriptionTextArea.setText(title);
    }
    
    public void setETAValue(Integer value){        
        this.activityForm.setETASliderFromInput(value);       
    }
    
    public void setWeekValue(Integer value){        
        this.activityForm.setWeekSliderFromInput(value);       
    }
     
    public void setIDMaintValue(Integer id){        
        this.idMaintField.setText(Integer.toString(id));        
    }        
    
    public void setToggleButtonInter(Boolean value){
        if(value)
        this.interrToggleBtn.setActivated(value);
    }
    
    private MouseAdapter saveBtnAction = new MouseAdapter() {
        @Override
        public void mousePressed(MouseEvent mouseEvent) {
            super.mousePressed(mouseEvent);
            LOG.log(java.util.logging.Level.INFO, "Start edit action.");
            startSavingActivity();
        }
    };
    
    private Activity generateActivity() {
        
       
        activityToEdit.setDescription(descriptionTextArea.getText());
        activityToEdit.setInterruptable(interrToggleBtn.isActivated());
        activityToEdit.setWeek(Integer.parseInt(WeekValueLabel.getText()));
        activityToEdit.setEstimatedTime(Integer.parseInt(ETAValueLabel.getText()));      
  
        if(procComboBox.getSelectedIndex()!=0) {
              activityToEdit.setProcedureId((procedures.get(procComboBox.getSelectedIndex()-1)
                      .getId()));
          } else {
              activityToEdit.setProcedureId(null);
          }
               
        return activityToEdit;
    }
    
    private void startSavingActivity() {
        Activity activity = generateActivity();
        if(activity != null){
            activityForm.setSavingText();
            ApiManager.getIstance().updateActivity(activity, apiDelegate);
        
        }
    }
        
    private void endSavingActivity() {
      
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
            showConfirmationMessage("Activity successfully updated");
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
