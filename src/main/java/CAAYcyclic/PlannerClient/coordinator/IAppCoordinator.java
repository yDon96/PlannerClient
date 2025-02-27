/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package CAAYcyclic.PlannerClient.coordinator;

import CAAYcyclic.PlannerClient.builder.AlertDialog.IAlertBuilder;
import CAAYcyclic.PlannerClient.model.Parcelable;
import CAAYcyclic.PlannerClient.model.Procedure;
import java.util.List;

/**
 *
 * @author Amos
 */
public interface IAppCoordinator {
    
    void popBack();
    
    void showAlert(IAlertBuilder alert);
    
    void switchPanelToDash();
    
    void switchPanelToActivitiesPanel();
       
    void navigateActivitiesPanelToAddForm(Parcelable procedureList);
    
    void navigateActivitiesPanelToAddFormWithNoProcedures();
    
    void switchPanelToMaintPanel();
    
    void navigateActivitiesPanelToEditForm(Parcelable activity, Parcelable procedures);
    
    void navigateActivitiesPanelToEditForm(Parcelable activity);
    
    void navigateActivitiesPanelToAssignForm(Parcelable activity, Parcelable procedures);
    
     void navigateActivitiesPanelToAssignForm(Parcelable activity);
}
