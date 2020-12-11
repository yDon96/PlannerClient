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
import CAAYcyclic.PlannerClient.controller.PanelController;
import CAAYcyclic.PlannerClient.enumeration.TableViewHeaders;
import CAAYcyclic.PlannerClient.factory.container.ActivitiesAddContainerViewFactory;
import CAAYcyclic.PlannerClient.factory.container.ActivitiesEditContainerViewFactory;
import CAAYcyclic.PlannerClient.factory.container.IContainerViewAbstractFactory;
import CAAYcyclic.PlannerClient.navigation.NavigationController;
import CAAYcyclic.PlannerClient.navigation.Segue;
import CAAYcyclic.PlannerClient.view.panel.content.ActivitiesPanel;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.util.Iterator;
import java.util.List;
import java.util.logging.Logger;
import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JTable;
import javax.swing.table.DefaultTableModel;
import javax.swing.table.TableModel;

/**
 *
 * @author User
 */
public class ActivitiesPanelController extends ContentPanelController{
    
    public ActivitiesPanel activitiesView;
    private static final Logger LOG = Logger.getLogger(ActivitiesPanelController.class.getName());
    
    private JButton updateBtn;
    private JButton editBtn;
    private JButton addBtn;
    private JTable table;
    private JComboBox weekComboBox;
    List<Activity> activities;
    
    public ActivitiesPanelController() {
        super();
        setContentPanel(ActivitiesPanel.class);        
    }
    
     @Override
    public void panelDidAppear() {
        super.panelDidAppear();
        initComponent();
        setButtonAction();
        LOG.log(java.util.logging.Level.INFO, "Start update action.");
        startUpdate();
        
    }
    
       
    private void initComponent() {
        LOG.log(java.util.logging.Level.CONFIG, "Init Activity panel component into controller.");
        activitiesView = (ActivitiesPanel) getPanel();
        updateBtn = activitiesView.getUpdateBtn();
        editBtn = activitiesView.getEditBtn();
        addBtn = activitiesView.getAddBtn();
        table = activitiesView.getTableView();
        weekComboBox = activitiesView.getWeekComboBox();
        activitiesView.setTableHeader(TableViewHeaders.ACTIVITY.value);

    }
    
     private void setButtonAction() {
        updateBtn.addMouseListener(updateBtnAction);
        addBtn.addMouseListener(addBtnAction);
        editBtn.addMouseListener(editBtnAction);
    }
     
         private MouseAdapter updateBtnAction = new MouseAdapter() {
        @Override
        public void mouseClicked(MouseEvent mouseEvent) {
            super.mousePressed(mouseEvent);
            LOG.log(java.util.logging.Level.INFO, "Start update action.");
            startUpdate();
        }
    };
         
         private MouseAdapter editBtnAction = new MouseAdapter() {
        @Override
        public void mouseClicked(MouseEvent mouseEvent) {
            super.mousePressed(mouseEvent);
            LOG.log(java.util.logging.Level.INFO, "Start edit action.");
            if (table.getRowCount() < 0 || table.getSelectedRow() < 0) {
                LOG.log(java.util.logging.Level.WARNING, "Number of row is \"0\" or no row is selected.");
                showError("Edit Error", "No element is selected.");
                return;
            }
            
            if (!isLockNavigation()) {
 
               ActivitiesEditContainerViewFactory factEdit = new ActivitiesEditContainerViewFactory();
            //   prepare(new Segue(factEdit.getContentPanelController()));
               startView(factEdit);               
               prepare(new Segue(factEdit.getContentPanelController()));
               
            } else {
                LOG.log(java.util.logging.Level.WARNING, "Cannot swich panel, navigation is locked.");
                showSelectionError("Wait until data ends updating.");
            }
            
           
        }
    };
         
     private final MouseAdapter addBtnAction = new MouseAdapter() {
        @Override
        public void mouseClicked(MouseEvent mouseEvent) {
            super.mousePressed(mouseEvent);
            LOG.log(java.util.logging.Level.INFO, "Start add action.");
            if (!isLockNavigation()) {
                startView(new ActivitiesAddContainerViewFactory());
            } else {
                LOG.log(java.util.logging.Level.WARNING, "Cannot swich panel, navigation is locked.");
                showSelectionError("Wait until data ends updating.");
            }
        }
    };
         
    private void startUpdate() {
        updateBtn.setText("Refreshing...");        
        NavigationController.getInstance().lockNavigation();
        ApiManager.getIstance().getActivities(apiDelegate);
    }
    
    public void setActivities(List<Activity> activities) {
        this.activities = activities;
               
    }

    public List<Activity> getActivities() {
        return activities;
    }
    
    
    
    private ApiActivityDelegate apiDelegate = new ApiActivityDelegate() {
        @Override
        public void onGetAllSuccess(List<Activity> activitiesRec) {
            endUpdate();
            if (activitiesRec.size() > 0) {              
               
                
                setActivities(activitiesRec);              
                DefaultTableModel model = (DefaultTableModel) table.getModel();
                Integer rowNumber = table.getRowCount();
                for (int index = rowNumber - 1; index >= 0; index--) {
                    model.removeRow(index);
                }
                
                //CODICE TEMPORANEO IN ATTESA DI GET(Week='value')//
                Iterator<Activity> it = getActivities().iterator();
                
                 while (it.hasNext()) {
                     Activity activity = it.next();
                     
                     if(activity.getWeek()!=null && weekComboBox.getSelectedIndex()+1 == activity.getWeek()){
                    Object[] row = {activity.getId(),activity.getDescription(), 
                        activity.getEstimatedTime()+" min", activity.isInterruptableYesNo(), 
                        activity.getWeek(),activity.getMaintainerId(), 
                        activity.getProcedureId()};
                    model.addRow(row);
                    }
                    else
                         it.remove();                    
            
                 }
                 
                 if(activities.isEmpty())
                     showEmptyError("No activity for the selected week");
                 
                 
                 
                 //FINE CODICE TEMPORANEO//
                
//                for (Activity activity : activities) {                    
//                    if(activity.getWeek()!=null && weekComboBox.getSelectedIndex()+1 == activity.getWeek()){
//                    Object[] row = {activity.getId(),activity.getDescription(), 
//                        activity.getEstimatedTime()+" min", activity.isInterruptableYesNo(), 
//                        activity.getWeek(),activity.getMaintainerId(), 
//                        activity.getProcedureId()};
//                    model.addRow(row);
//                    }
//                    
//                }
//                

           //     numberOfRow.setText(String.valueOf(table.getRowCount()));
            }
            else
                     showEmptyError("There are no activity in the system");
            
        }
        @Override
        public void onGetSuccess(Activity activity) {
            endUpdate();
        }

        @Override
        public void onFailure(String message) {
            endUpdate();
            System.out.println("Errore "+message);
            showSelectionError(message);
        }

        @Override
        public void onCreateSuccess() {
            endUpdate();
        }
    };
    
     private void endUpdate() {
        updateBtn.setText("Refresh");
        NavigationController.getInstance().unlockNavigation();
        
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
        alertBuilder.show();
    }


    @Override
    public void prepare(Segue segue) {
        
        if(segue != null){
            ActivityEditFormPanelController editControl = null;
           try{ 
               editControl = (ActivityEditFormPanelController) segue.getSeguePanelController();
           
           }
           catch (Exception e){
                LOG.log(java.util.logging.Level.CONFIG, "Segue error - ActivitiesPanelController");
                return;
           }
            
        Activity selActivity = activities.get(table.getSelectedRow());
        
            
//        TableModel mod =  table.getModel(); 
//        Integer selRow = table.getSelectedRow();
//        String ETAValue = (String) mod.getValueAt(selRow,2);
//        String InterValue = (String) mod.getValueAt(selRow, 3);
//       
//        
//       editControl.setDescriptionTitle((String) mod.getValueAt(selRow,1));    
//       editControl.setETAValue(Integer.parseInt(ETAValue.split(" ",2)[0]));    
//       editControl.setWeekValue((Integer) mod.getValueAt(selRow,4));
//       editControl.setIDMaintValue((Integer) mod.getValueAt(selRow,5));
//       editControl.setIDProcValue((Integer) mod.getValueAt(selRow,6));
       
//         editControl.setDescriptionTitle(selActivity.getDescription());
//         editControl.setETAValue(selActivity.getEstimatedTime());
//         editControl.setWeekValue(selActivity.getWeek());
//         editControl.setIDMaintValue(selActivity.getMaintainerId());
//         editControl.setIDProcValue(selActivity.getProcedureId());
//         editControl.setToggleButtonInter(selActivity.isInterruptable());

        editControl.setActivityToEdit(selActivity);
        editControl.fillPanel();
       
           
           
        }
       
    }

    @Override
    public Logger getLogger() {
        return LOG;
    }
    
}
