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
import javax.swing.JTextField;

/**
 *
 * @author User
 */
public class ActivityAddFormPanelController extends ContentPanelController{
    
      private static final Logger LOG = Logger.getLogger(ActivityAddFormPanelController.class.getName());
      private ActivityFormPanel activityForm;
      
      RoundedJTextArea descriptionTextArea;
      ToggleSwitch interrToggleBtn;
      JLabel ETAValueLabel;	    
      JLabel  WeekValueLabel;	
      JTextField idMaintField;	
      JTextField idProcField;	
      JButton saveButton;
      
      
      
      public ActivityAddFormPanelController() {
        super();
        setContentPanel(ActivityFormPanel.class);
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
            LOG.log(java.util.logging.Level.INFO, "Start saving action.");
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
        
        Activity activity = new Activity();
        
        
        activity.setDescription(descriptionTextArea.getText());
        activity.setInterruptable(interrToggleBtn.isActivated());
        activity.setWeek(Integer.parseInt(WeekValueLabel.getText()));
        activity.setEstimatedTime(Integer.parseInt(ETAValueLabel.getText()));      
        activity.setMaintainerId(Integer.parseInt(idMaintField.getText())); 
        activity.setProcedureId(Integer.parseInt(idProcField.getText())); 
        
        
        return activity;
    }
    
        private void startSavingActivity() {
        Activity activity = generateActivity();
        if(activity != null){
            activityForm.setSavingText();
            NavigationController.getInstance().lockNavigation();
            ApiManager.getIstance().updateActivity(activity, apiDelegate);
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
    

    @Override
    public void prepare(Segue segue) {
        if(segue != null){
            ActivitiesPanelController activitiesPanelController = (ActivitiesPanelController) segue.getSeguePanelController();
        }
    }

    @Override
    public Logger getLogger() {
       return LOG;
    }
    
}
