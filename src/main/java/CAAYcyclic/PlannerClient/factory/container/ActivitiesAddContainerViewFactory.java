/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package CAAYcyclic.PlannerClient.factory.container;

import CAAYcyclic.PlannerClient.controller.bar.BackBarController;
import CAAYcyclic.PlannerClient.controller.bar.BarController;
import CAAYcyclic.PlannerClient.controller.content.ActivitiesPanelController;
import CAAYcyclic.PlannerClient.controller.content.ActivityAddFormPanelController;
import CAAYcyclic.PlannerClient.controller.content.ContentPanelController;


/**
 *
 * @author User
 */
public class ActivitiesAddContainerViewFactory implements IContainerViewAbstractFactory{

    @Override
    public BarController getBarController() {
        return new BackBarController();
    }

    @Override
    public ContentPanelController getContentPanelController() {
         return new ActivityAddFormPanelController(); //To change body of generated methods, choose Tools | Templates.
    }
    
}
