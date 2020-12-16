/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package CAAYcyclic.PlannerClient.controller.content;

import CAAYcyclic.PlannerClient.api.ApiManager;
import CAAYcyclic.PlannerClient.api.delegate.ApiActivityDelegate;
import CAAYcyclic.PlannerClient.api.delegate.ApiProcedureDelegate;
import CAAYcyclic.PlannerClient.model.Activity;
import CAAYcyclic.PlannerClient.builder.AlertDialog.impl.AlertDialogBuilder;
import CAAYcyclic.PlannerClient.enumeration.TableViewHeaders;
import CAAYcyclic.PlannerClient.model.Parcel;
import CAAYcyclic.PlannerClient.model.Procedure;
import CAAYcyclic.PlannerClient.model.ProceduresList;
import CAAYcyclic.PlannerClient.view.panel.content.ActivitiesPanel;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import java.util.logging.Logger;
import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JTable;
import javax.swing.table.DefaultTableModel;

/**
 *
 * @author Amos
 */
public class ActivitiesPanelController extends ContentPanelController{
    
    public ActivitiesPanel activitiesView;
    private static final Logger LOG = Logger.getLogger(ActivitiesPanelController.class.getName());
    
    private JButton updateBtn;
    private JButton editBtn;
    private JButton addBtn;
    private JButton assignBtn;
    private JTable table;
    private JComboBox weekComboBox;
    private List<Activity> activities;
    private ProceduresList procedures;
    
    public ActivitiesPanelController() {
        super();
        setContentPanel(ActivitiesPanel.class);  
        initComponent();
        setButtonAction();
    }
    
        
    @Override
    public void panelWillAppear() {
        super.panelWillAppear();
        LOG.log(java.util.logging.Level.INFO, "Start update action.");
        startGetProceduresAndActivities();       
    }    
       
    private void initComponent() {
        LOG.log(java.util.logging.Level.CONFIG, "Init Activity panel component into controller.");
        activitiesView = (ActivitiesPanel) getPanel();
        updateBtn = activitiesView.getUpdateBtn();
        editBtn = activitiesView.getEditBtn();
        addBtn = activitiesView.getAddBtn();
        assignBtn = activitiesView.getAssignBtn();
        table = activitiesView.getTableView();
        weekComboBox = activitiesView.getWeekComboBox();
        activitiesView.setTableHeader(TableViewHeaders.ACTIVITY.value);
        procedures = new ProceduresList();
   }
    
    public void setActivities(List<Activity> activities) {
        this.activities = activities;               
    }

    public List<Activity> getActivities() {
        return activities;
    }
    
    public void setProcedures(List<Parcel> procedures) {
        this.procedures.setList((ArrayList<Parcel>) procedures);
    }
        
    private void setButtonAction() {
        updateBtn.addMouseListener(updateBtnAction);
        addBtn.addMouseListener(addBtnAction);
        editBtn.addMouseListener(editBtnAction);
        assignBtn.addMouseListener(assignBtnAction);
    }
         
    private MouseAdapter updateBtnAction = new MouseAdapter() {
        @Override
        public void mouseClicked(MouseEvent mouseEvent) {
            super.mouseClicked(mouseEvent);
            LOG.log(java.util.logging.Level.INFO, "Start update action.");
            startGetProceduresAndActivities();   
        }
    };
         
    private MouseAdapter editBtnAction = new MouseAdapter() {
        @Override
        public void mouseClicked(MouseEvent mouseEvent) {
            super.mouseClicked(mouseEvent);
            LOG.log(java.util.logging.Level.INFO, "Start edit action.");
            if (table.getRowCount() < 0 || table.getSelectedRow() < 0) {
                LOG.log(java.util.logging.Level.WARNING, "Number of row is \"0\" or no row is selected.");
                showError("Edit Error", "No element is selected.");
                return;
            }
           
            
            if (procedures.getList() !=null){
                   getCoordinator().navigateActivitiesPanelToEditForm(activities.get(table.getSelectedRow()),
                   procedures);
            }
            else{
                getCoordinator().navigateActivitiesPanelToEditForm
                (activities.get(table.getSelectedRow()));
            }
                     
        }
    };
             
    private MouseAdapter assignBtnAction = new MouseAdapter() {
        @Override
        public void mouseClicked(MouseEvent mouseEvent) {
            super.mouseClicked(mouseEvent);
            LOG.log(java.util.logging.Level.INFO, "Start assign action.");
            if (table.getRowCount() < 0 || table.getSelectedRow() < 0) {
                LOG.log(java.util.logging.Level.WARNING, "Number of row is \"0\" or no row is selected.");
                showError("Assign Error", "No element is selected.");
                return;
            }           
            
            getCoordinator().navigateActivitiesPanelToAssignForm(activities.get(table.getSelectedRow()));
                     
        }
    };
         
    private final MouseAdapter addBtnAction = new MouseAdapter() {
        @Override
        public void mouseClicked(MouseEvent mouseEvent) {
            super.mouseClicked(mouseEvent);
            LOG.log(java.util.logging.Level.INFO, "Start add action.");
            
             if (procedures.getList() !=null){
                 getCoordinator().navigateActivitiesPanelToAddForm(procedures);
             }
             else
                getCoordinator().navigateActivitiesPanelToAddFormWithNoProcedures();
        }
    };
    
    private ApiActivityDelegate apiDelegate = new ApiActivityDelegate() {
        @Override
        public void onGetAllSuccess(List<Activity> activitiesRec) {
            endUpdate();
            dropTable();
            DefaultTableModel model = (DefaultTableModel) table.getModel();
           
            if (activitiesRec.size() > 0) {              
                setActivities(activitiesRec);              
                for (Activity activity : activities) {                    
                    Object[] row = {activity.getId(),activity.getDescription(), 
                        activity.getEstimatedTime()+" min", activity.isInterruptableYesNo(), 
                        activity.stringDay(), activity.stringStartingTime(),
                        activity.getMaintainerId(), 
                        activity.getProcedureId()};
                    model.addRow(row);
                    }
            }
            else{
                showEmptyError("No activity for the selected week");
            }
            
        }
        @Override
        public void onGetSuccess(Activity activity) {
            endUpdate();
        }

        @Override
        public void onFailure(String message) {
            endUpdate();
            dropTable();
            showSelectionError(message);
        }

        @Override
        public void onCreateSuccess() {
            endUpdate();
        }

        
    };
    
    private ApiProcedureDelegate apiDelegateProc = new ApiProcedureDelegate() {
        @Override
        public void onGetAllSuccess(List<Procedure> proceduresRec) {
            if (proceduresRec.size() > 0) {
                
                 Iterator<Procedure> it = proceduresRec.iterator();
                 ArrayList<Parcel> parcelProcedure = new ArrayList<>();
                 
                 while (it.hasNext()) {
                     Parcel parcel = it.next().convertToParcel();
                     parcelProcedure.add(parcel);                    
                     
                 }             
                                
                 setProcedures(parcelProcedure);
 
        }
             startUpdate(); 
        }

        @Override
        public void onGetSuccess(Procedure procedure) {
          
        }

        @Override
        public void onFailure(String message) {
            startUpdate();
        }

        @Override
        public void onCreateSuccess() {
        }
    };
     
    private void startGetProceduresAndActivities(){
        ApiManager.getIstance().getProcedures(apiDelegateProc);
    }
         
    private void startUpdate() {
        updateBtn.setText("Refreshing...");        
        ApiManager.getIstance().getActivitiesByWeek(apiDelegate, 
                  this.weekComboBox.getSelectedIndex()+1);
    }    

    public void dropTable(){
        DefaultTableModel model = (DefaultTableModel) table.getModel();       
                Integer rowNumber = table.getRowCount();
                for (int index = rowNumber - 1; index >= 0; index--) {
                    model.removeRow(index);
                }
    }  
    
    private void endUpdate() {
        updateBtn.setText("Refresh");             
    }
    
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
    @Override
    public Logger getLogger() {
        return LOG;
    }
    
}
