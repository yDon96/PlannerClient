/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package CAAYcyclic.PlannerClient.coordinator;

import CAAYcyclic.PlannerClient.builder.AlertDialog.IAlertBuilder;
import CAAYcyclic.PlannerClient.model.Parcelable;

/**
 *
 * @author Youssef
 */
public interface IAppCoordinator {
    
    void popBack();
    
    void showAlert(IAlertBuilder alert);
}
