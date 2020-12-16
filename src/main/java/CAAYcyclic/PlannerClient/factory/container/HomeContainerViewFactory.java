/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package CAAYcyclic.PlannerClient.factory.container;

import CAAYcyclic.PlannerClient.controller.bar.BarController;
import CAAYcyclic.PlannerClient.controller.content.DashBoardPanelController;
import CAAYcyclic.PlannerClient.controller.bar.MainSideBarController;
import CAAYcyclic.PlannerClient.controller.content.ContentPanelController;

/**
 *
 * @author Amos
 */
public class HomeContainerViewFactory implements IContainerViewAbstractFactory {

    @Override
    public ContentPanelController getContentPanelController() {
        return new DashBoardPanelController();//To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public BarController getBarController() {
        return new MainSideBarController();//To change body of generated methods, choose Tools | Templates.
    }
    
}
