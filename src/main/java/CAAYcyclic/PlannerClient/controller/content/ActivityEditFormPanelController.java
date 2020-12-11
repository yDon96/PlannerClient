/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package CAAYcyclic.PlannerClient.controller.content;

import CAAYcyclic.PlannerClient.api.ApiManager;
import CAAYcyclic.PlannerClient.api.delegate.ApiActivityDelegate;
import CAAYcyclic.PlannerClient.api.model.Activity;
import CAAYcyclic.PlannerClient.builder.AlertDialog.impl.AlertDialogBuilder;
import CAAYcyclic.PlannerClient.navigation.NavigationController;
import CAAYcyclic.PlannerClient.navigation.Segue;
import CAAYcyclic.PlannerClient.view.panel.component.RoundedJTextArea;
import CAAYcyclic.PlannerClient.view.panel.component.ToggleSwitch;
import CAAYcyclic.PlannerClient.view.panel.content.ActivityFormPanel;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.util.List;
import java.util.logging.Logger;
import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JSlider;
import javax.swing.JTextField;

/**
 *
 * @author User
 */
public class ActivityEditFormPanelController extends ContentPanelController{
    
      private static final Logger LOG = Logger.getLogger(ActivityEditFormPanelController.class.getName());
      private ActivityFormPanel activityForm;
      
      RoundedJTextArea descriptionTextArea;
      ToggleSwitch interrToggleBtn;
      JLabel ETAValueLabel;	    
      JLabel  WeekValueLabel;	
      JTextField idMaintField;	
      JTextField idProcField;	
      JButton saveButton;
      Activity activityToEdit;
      
      
      
      public ActivityEditFormPanelController() {
        super();
        setContentPanel(ActivityFormPanel.class);
    }

    public ActivityFormPanel getActivityForm() {
        return activityForm;
    }
      
    
     
    public void fillPanel() {
        setDescriptionTitle(activityToEdit.getDescription());
        setETAValue(activityToEdit.getEstimatedTime());
        setWeekValue(activityToEdit.getWeek());
        setIDMaintValue(activityToEdit.getMaintainerId());
        setIDProcValue(activityToEdit.getProcedureId());
        setToggleButtonInter(activityToEdit.isInterruptable());
    }

    public void setActivityToEdit(Activity activityToEdit) {
        this.activityToEdit = activityToEdit;
    }
    
    
      
     @Override
    public void panelDidAppear() {
        super.panelDidAppear();
        initComponent();
    }
    
    private void initComponent() {
        LOG.log(java.util.logging.Level.CONFIG, "Init Activity panel component into controller.");
        this.activityForm = (ActivityFormPanel) getPanel();
        
        descriptionTextArea = activityForm.getDescriptionTextArea();
        interrToggleBtn = activityForm.getInterrToggleBtn();
        ETAValueLabel = activityForm.getETAValueLabel();	    
        WeekValueLabel = activityForm.getWeekValueLabel();	
        idMaintField = activityForm.getIdMaintField();
        idProcField = activityForm.getIdProcField();	
        saveButton = activityForm.getSaveButton();
        
        
        setButtonAction();
        

    }
    
    private void setButtonAction() {
        saveButton.addMouseListener(saveBtnAction);
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
        
        if(idMaintField.getText().isBlank()|| !idMaintField.getText().matches("-?[0-9]+")){
            showError("Wrong Maintainer Id");
            return null;
        }
        
        if(idProcField.getText().isBlank() || !idProcField.getText().matches("-?[0-9]+")){
            showError("Wrong Procedure Id");
            return null;
        }
        
        activityToEdit.setDescription(descriptionTextArea.getText());
        activityToEdit.setInterruptable(interrToggleBtn.isActivated());
        activityToEdit.setWeek(Integer.parseInt(WeekValueLabel.getText()));
        activityToEdit.setEstimatedTime(Integer.parseInt(ETAValueLabel.getText()));      
        activityToEdit.setMaintainerId(Integer.parseInt(idMaintField.getText())); 
        activityToEdit.setProcedureId(Integer.parseInt(idProcField.getText())); 
        
        
        
        return activityToEdit;
    }
    
        private void startSavingActivity() {
        Activity activity = generateActivity();
        if(activity != null){
            activityForm.setSavingText();
            NavigationController.getInstance().lockNavigation();
            ApiManager.getIstance().createActivity(activity, apiDelegate);
        }
    }
        
        private void endSavingActivity() {
        NavigationController.getInstance().unlockNavigation();
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
            popBackView();
        }
    };
     
     private void showError(String message){
        AlertDialogBuilder alertBuilder = new AlertDialogBuilder();
        alertBuilder.setTitle("Error");
        alertBuilder.setMessage(message);
        alertBuilder.setDefaultPositiveAction();
        alertBuilder.show();
    }
     
     private void showConfirmationMessage(String message){
        AlertDialogBuilder alertBuilder = new AlertDialogBuilder();
        alertBuilder.setTitle("Confirmation Message");
        alertBuilder.setMessage(message);
        alertBuilder.setDefaultPositiveAction();
        alertBuilder.show();
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
    
    public void setIDProcValue(Integer id){
        
         this.idProcField.setText(Integer.toString(id));
        
    }
    
    
    public void setToggleButtonInter(Boolean value){
        if(value)
        this.interrToggleBtn.setActivated(value);
    }
    

    @Override
    public void prepare(Segue segue) {
        if(segue != null){
            ActivitiesPanelController activitiesPanelController = (ActivitiesPanelController) segue.getSeguePanelController();
            //activitiesPanelController.g
        }
    }

    @Override
    public Logger getLogger() {
       return LOG;
    }
    
}
