/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package CAAYcyclic.PlannerClient.controller.content;

import CAAYcyclic.PlannerClient.api.ApiManager;
import CAAYcyclic.PlannerClient.api.delegate.ApiActivityDelegate;
import CAAYcyclic.PlannerClient.api.delegate.ApiNumberOfSkill;
import CAAYcyclic.PlannerClient.api.delegate.ApiUserDelegate;
import CAAYcyclic.PlannerClient.builder.AlertDialog.impl.AlertDialogBuilder;
import CAAYcyclic.PlannerClient.enumeration.TableViewHeaders;
import CAAYcyclic.PlannerClient.model.Activity;
import CAAYcyclic.PlannerClient.model.Procedure;
import CAAYcyclic.PlannerClient.model.ProceduresList;
import CAAYcyclic.PlannerClient.model.User;
import CAAYcyclic.PlannerClient.view.panel.content.AvailabilityMaintPanel;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.util.ArrayList;
import java.util.List;
import java.util.logging.Logger;
import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JLabel;
import javax.swing.JTable;
import javax.swing.table.DefaultTableModel;

/**
 *
 * @author Amos
 */
public class MaintPanelController  extends ContentPanelController{
    
    public AvailabilityMaintPanel maintView;
    private static final Logger LOG = Logger.getLogger(MaintPanelController.class.getName());
    private JTable table;
    private JComboBox weekComboBox;
    private JComboBox dayComboBox;
    private JButton updateBtn;
    private JButton assignBtn;
    private JLabel idLabel;
    private JLabel desLabel;
    
    Activity activityToAssign;
    ProceduresList proceduresParcel;
    ArrayList<User> maintainers;
    ArrayList<Procedure> procedures;
    ArrayList<Activity> activities;
    
    Integer actualIndex;

    public MaintPanelController() {
        super();
        setContentPanel(AvailabilityMaintPanel.class);  
        initComponent();
        setButtonAction();        
    }
    
    public void initComponent(){
         LOG.log(java.util.logging.Level.CONFIG, "Init Avilability Maint panel component into controller.");
         maintView = (AvailabilityMaintPanel) getPanel();
         updateBtn = maintView.getUpdateBtn();
         weekComboBox = maintView.getWeekComboBox();
         dayComboBox = maintView.getDayComboBox();
         table = maintView.getTableView();
         
         idLabel = maintView.getIdActivityLabel();
         desLabel = maintView.getDescrptionActivityLabel();
         assignBtn = maintView.getAssignBtn();
         
         maintView.setTableHeader(TableViewHeaders.MAINTAINER_AVAILABILITY.value);
         table.setAutoResizeMode(JTable.AUTO_RESIZE_OFF);
         procedures = new ArrayList<>();
        

    }
    
    @Override
    public void panelWillAppear() {
        super.panelWillAppear();
        
        if(getParcels() != null){
        
            activityToAssign = new Activity();
            proceduresParcel = new ProceduresList();
            
            activityToAssign.createFromParcel(getParcels()
                    .get(activityToAssign.getParcelableDescription()));
           
             idLabel.setText("Activity ID: "+activityToAssign.getId());
             desLabel.setText("Activity Description: "+activityToAssign.getDescription());
             weekComboBox.setSelectedItem(Integer.toString(activityToAssign.getWeek()));
              
       
         }
        
        startUpdate();
          
        }

    public ArrayList<User> getMaintainers() {
        return maintainers;
    }

    public void setMaintainers(ArrayList<User> maintainers) {
        this.maintainers = maintainers;
    }
    
    private void setButtonAction() {
        assignBtn.addMouseListener(assignBtnAction);
        updateBtn.addMouseListener(updateBtnAction);
        
    }
    
    public void setActivities(ArrayList<Activity> activities) {
        this.activities = activities;               
    }
    
    private MouseAdapter assignBtnAction = new MouseAdapter() {
        @Override
        public void mouseClicked(MouseEvent mouseEvent) {
            super.mousePressed(mouseEvent);
            LOG.log(java.util.logging.Level.INFO, "Start assign action.");
            if (table.getRowCount() < 0 || table.getSelectedRow() < 0 || table.getSelectedColumn()<2) {
                showError("Assign Error", "No starting hour is selected.");
                return;                
             }
            
            startAssignActivity();
        }
    };
    
    private MouseAdapter updateBtnAction = new MouseAdapter(){
        @Override
        public void mouseClicked(MouseEvent mouseEvent) {
            super.mousePressed(mouseEvent);
            LOG.log(java.util.logging.Level.INFO, "Update Maintainers Availability action");
                        
            startUpdate();
        }
        
    };     
       
    private void startUpdate() { 
  
        List<String> rolesString = new ArrayList<>();
        rolesString.add("Maintainer");
        
        ApiManager.getIstance().getUsers(apiDelegate,rolesString);
       
    }
    
    private void loadAvailability(){
        
        if(maintainers!=null){     
           
            actualIndex = maintainers.size();                
             ApiManager.getIstance().getMaintainersAvailability(apiDelegateAv,
                weekComboBox.getSelectedIndex()+1,
                dayComboBox.getSelectedIndex()+1,
                maintainers.get(actualIndex-1).getUser_id());
                    
                
        }
                
        }
    
    private void loadNumberSkills(){
        
        if(maintainers!=null && activityToAssign.getProcedureId()!=null ){   
         
            actualIndex = maintainers.size();     
            ApiManager.getIstance().getNumberOfSkill(apiDelegateSkill,
                maintainers.get(actualIndex-1).getUser_id(), activityToAssign.getProcedureId());
    }
        
    }
    
    private void startAssignActivity() {
           assignActivity();
           ApiManager.getIstance().updateActivity(activityToAssign, apiDelegateAssign);
      }
           
    private ApiUserDelegate apiDelegate = new ApiUserDelegate() {
        @Override
        
        public void onGetSuccess(User user) {
           ; 
        }

        @Override
        public void onGetAllSuccess(List<User> users) {
           if(users.size()>0){
               DefaultTableModel model = (DefaultTableModel) table.getModel();
                Integer rowNumber = table.getRowCount();
                for (int index = rowNumber - 1; index >= 0; index--) {
                    model.removeRow(index);
                }
                
               
               setMaintainers((ArrayList<User>) users);          
                for (User main : maintainers){
                    Object[] row = {main.getUser_id()};
                    model.addRow(row);
                }
                loadAvailability();
            
           }
           else{
                showSelectionError("There are no maintainers!");
                getCoordinator().popBack();
           }
        }

        @Override
        public void onFailure(String message) {
            showSelectionError(message);
            getCoordinator().popBack();
        }

        @Override
        public void onCreateSuccess() {
           }             
             };
    
    private ApiActivityDelegate apiDelegateAv = new ApiActivityDelegate() {
        @Override
        public void onGetAllSuccess(List<Activity> activitiesRec) {          
                 
                for(Activity act: activitiesRec){
                 DefaultTableModel model = (DefaultTableModel) table.getModel();
                model.setValueAt(Integer.toString(act.getEstimatedTime())+" min",
                        actualIndex-1, act.getStartingHour()+2);
                }

               if(actualIndex > 1){               
                actualIndex--;
                ApiManager.getIstance().getMaintainersAvailability(apiDelegateAv,
                weekComboBox.getSelectedIndex()+1,
                dayComboBox.getSelectedIndex()+1,
                maintainers.get(actualIndex-1).getUser_id());
               }
               else
                   loadNumberSkills();
              
          
        }

        @Override
        public void onGetSuccess(Activity activity) {
         ;
        }

        @Override
        public void onFailure(String message) {
           showSelectionError(message);
           getCoordinator().popBack();
        }

        @Override
        public void onCreateSuccess() {
           
        }
       
             };
    
    private ApiNumberOfSkill apiDelegateSkill = new ApiNumberOfSkill(){
        
        @Override
        public void onGetSuccess(Integer numberSkill) {
            System.out.println(numberSkill);
            
            DefaultTableModel model = (DefaultTableModel) table.getModel();
                model.setValueAt(numberSkill,
                        actualIndex-1, 1);
            
            if(actualIndex > 1){               
                actualIndex--;     
                ApiManager.getIstance().getNumberOfSkill(apiDelegateSkill,
                maintainers.get(actualIndex-1).getUser_id(), activityToAssign.getProcedureId());
               }            
        }

        @Override
        public void onFailure(String message) {
            showSelectionError(message);
            getCoordinator().popBack();
        }

        @Override
        public void onCreateSuccess() {
            throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
        }

        @Override
        public void onGetAllSuccess(List<Integer> objects) {
            ;
        }

                
    };
    
    private ApiActivityDelegate apiDelegateAssign = new ApiActivityDelegate() {
        @Override
        public void onGetAllSuccess(List<Activity> activities) {
        }

        @Override
        public void onGetSuccess(Activity activity) {
        }

        @Override
        public void onFailure(String message) {
            showError("Assign error",message);
        }

        @Override
        public void onCreateSuccess() {
            showConfirmationMessage("Activity successfully assigned");
            getCoordinator().popBack();
        }
    };
      
    private void showEmptyError(String message) {
        showError("Empty Set Error", message);
    }
    
    private void showSelectionError(String message) {
        showError("Error", message);
    }

    private void showError(String title, String message) {
        AlertDialogBuilder alertBuilder = new AlertDialogBuilder();
        alertBuilder.setTitle(title);
        alertBuilder.setMessage(message);
        alertBuilder.setDefaultPositiveAction();
        getCoordinator().showAlert(alertBuilder);
    }
    
    private void assignActivity(){
        activityToAssign.setStartingDay(dayComboBox.getSelectedIndex()+1);
        activityToAssign.setStartingHour(table.getSelectedColumn()-2);
        activityToAssign.setWeek(weekComboBox.getSelectedIndex()+1);
        activityToAssign.setMaintainerId((Integer) table.getModel().getValueAt(table.getSelectedRow(), 0));
              
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
