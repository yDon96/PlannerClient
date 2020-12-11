/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package CAAYcyclic.PlannerClient.coordinator.impl;

import CAAYcyclic.PlannerClient.builder.AlertDialog.IAlertBuilder;
import CAAYcyclic.PlannerClient.controller.IPanelController;
import CAAYcyclic.PlannerClient.coordinator.IAppCoordinator;
import CAAYcyclic.PlannerClient.factory.container.HomeContainerViewFactory;
import CAAYcyclic.PlannerClient.navigation.NavigationController;

/**
 *
 * @author Youssef
 */
public class AppCoordinator extends Coordinator implements IAppCoordinator{

    public AppCoordinator(NavigationController navController) {
        this.navigationController = navController;
    }

    @Override
    public void start() {
        HomeContainerViewFactory homeContainerViewFactory = new HomeContainerViewFactory();
        IPanelController dashBoardPanelController = homeContainerViewFactory.getContentPanelController();
        IPanelController mainSideBarController = homeContainerViewFactory.getBarController();
        dashBoardPanelController.setCoordinator(this);
        mainSideBarController.setCoordinator(this);
        navigationController.performViewNavigationTo(mainSideBarController,dashBoardPanelController);
    }

    @Override
    public void popBack() {
        navigationController.performBackToStack();
    }

    @Override
    public void showAlert(IAlertBuilder alert) {
        alert.setParentFrame(navigationController.getCurrentFrame());
        alert.display();
    }
    
}
