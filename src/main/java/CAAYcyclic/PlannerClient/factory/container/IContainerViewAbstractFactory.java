/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package CAAYcyclic.PlannerClient.factory.container;

import CAAYcyclic.PlannerClient.controller.bar.BarController;
import CAAYcyclic.PlannerClient.controller.content.ContentPanelController;


/**
 *
 * @author Amos
 */
public interface IContainerViewAbstractFactory {
    
     /**
     * Generate the Bar panel controller assosieted to View Controller.
     * @return BarController
     */
    BarController getBarController();
    
     /**
     * Generate the Panel controller assosieted to View Controller.
     * @return ContentPanelController
     */
    ContentPanelController getContentPanelController();
    
}
