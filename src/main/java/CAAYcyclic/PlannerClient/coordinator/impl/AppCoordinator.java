/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package CAAYcyclic.PlannerClient.coordinator.impl;

import CAAYcyclic.PlannerClient.builder.AlertDialog.IAlertBuilder;
import CAAYcyclic.PlannerClient.controller.IPanelController;
import CAAYcyclic.PlannerClient.controller.content.ActivitiesPanelController;
import CAAYcyclic.PlannerClient.controller.content.DashBoardPanelController;
import CAAYcyclic.PlannerClient.controller.content.MaintPanelController;
import CAAYcyclic.PlannerClient.coordinator.IAppCoordinator;
import CAAYcyclic.PlannerClient.factory.container.ActivitiesAddContainerViewFactory;
import CAAYcyclic.PlannerClient.factory.container.ActivitiesEditContainerViewFactory;
import CAAYcyclic.PlannerClient.factory.container.HomeContainerViewFactory;
import CAAYcyclic.PlannerClient.factory.container.IContainerViewAbstractFactory;
import CAAYcyclic.PlannerClient.factory.container.MaintContainerViewFactory;
import CAAYcyclic.PlannerClient.model.Parcelable;
import CAAYcyclic.PlannerClient.navigation.NavigationController;

/**
 *
 * @author Amos
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
        navigationController.performBackStack();
    }

    @Override
    public void showAlert(IAlertBuilder alert) {
        alert.setParentFrame(navigationController.getCurrentFrame());
        alert.display();
    }

    @Override
    public void switchPanelToDash() {
        IPanelController dashBoardPanelController = navigationController.retrivePanelFromMap(DashBoardPanelController.class.getName());
        if(dashBoardPanelController == null) {
            dashBoardPanelController = new DashBoardPanelController();
        }
        dashBoardPanelController.setCoordinator(this);
        navigationController.performPanelNavigationTo(dashBoardPanelController);
    }

    @Override
    public void switchPanelToActivitiesPanel() {
       IPanelController activitiesPanelController = navigationController.retrivePanelFromMap(ActivitiesPanelController.class.getName());
        if(activitiesPanelController == null) {
            activitiesPanelController = new ActivitiesPanelController();
        }
        activitiesPanelController.setCoordinator(this);
        navigationController.performPanelNavigationTo(activitiesPanelController);
    }

   
    @Override
    public void navigateActivitiesPanelToEditForm(Parcelable activity, Parcelable procedures) {
     IContainerViewAbstractFactory homeContainerViewFactory = new ActivitiesEditContainerViewFactory();
        IPanelController panelController = homeContainerViewFactory.getContentPanelController();
        IPanelController barController = homeContainerViewFactory.getBarController();
        if(activity != null){
            panelController.setParcel(activity.getParcelableDescription(), activity.convertToParcel());
        }
        
        
        if(procedures != null){
                panelController.setParcel(procedures.getParcelableDescription(), procedures.convertToParcel());
        }
        
        panelController.setCoordinator(this);
        barController.setCoordinator(this);
        navigationController.performViewNavigationTo(barController,panelController);
    }

    @Override
    public void navigateActivitiesPanelToAddForm(Parcelable procedures) {
        IContainerViewAbstractFactory homeContainerViewFactory = new ActivitiesAddContainerViewFactory();
        IPanelController panelController = homeContainerViewFactory.getContentPanelController();
        IPanelController barController = homeContainerViewFactory.getBarController();
       
         if(procedures != null){
                panelController.setParcel(procedures.getParcelableDescription(), procedures.convertToParcel());
        }
                
        panelController.setCoordinator(this);
        barController.setCoordinator(this);
        navigationController.performViewNavigationTo(barController,panelController);
    }
    
    @Override
    public void navigateActivitiesPanelToAddFormWithNoProcedures() {
        IContainerViewAbstractFactory homeContainerViewFactory = new ActivitiesAddContainerViewFactory();
        IPanelController panelController = homeContainerViewFactory.getContentPanelController();
        IPanelController barController = homeContainerViewFactory.getBarController();
       
            
        panelController.setCoordinator(this);
        barController.setCoordinator(this);
        navigationController.performViewNavigationTo(barController,panelController);
    }

    @Override
    public void switchPanelToMaintPanel() {
       IPanelController maintPanelController = navigationController.retrivePanelFromMap(MaintPanelController.class.getName());
        if(maintPanelController == null) {
            maintPanelController = new MaintPanelController();
        }
        maintPanelController.setCoordinator(this);
        navigationController.performPanelNavigationTo(maintPanelController);
    }

    @Override
    public void navigateActivitiesPanelToAssignForm(Parcelable activity, Parcelable procedures) {
        IContainerViewAbstractFactory homeContainerViewFactory = new MaintContainerViewFactory();
        IPanelController panelController = homeContainerViewFactory.getContentPanelController();
        IPanelController barController = homeContainerViewFactory.getBarController();
        if(activity != null){
            panelController.setParcel(activity.getParcelableDescription(), activity.convertToParcel());
        }
        
        
        if(procedures != null){
                panelController.setParcel(procedures.getParcelableDescription(), procedures.convertToParcel());
        }
        
        panelController.setCoordinator(this);
        barController.setCoordinator(this);
        navigationController.performViewNavigationTo(barController,panelController);
       
    }

    @Override
    public void navigateActivitiesPanelToEditForm(Parcelable activity) {
        IContainerViewAbstractFactory homeContainerViewFactory = new ActivitiesEditContainerViewFactory();
        IPanelController panelController = homeContainerViewFactory.getContentPanelController();
        IPanelController barController = homeContainerViewFactory.getBarController();
        if(activity != null){
            panelController.setParcel(activity.getParcelableDescription(), activity.convertToParcel());
        }
           
        panelController.setCoordinator(this);
        barController.setCoordinator(this);
        navigationController.performViewNavigationTo(barController,panelController);
    }

    @Override
    public void navigateActivitiesPanelToAssignForm(Parcelable activity) {
       IContainerViewAbstractFactory homeContainerViewFactory = new MaintContainerViewFactory();
        IPanelController panelController = homeContainerViewFactory.getContentPanelController();
        IPanelController barController = homeContainerViewFactory.getBarController();
        if(activity != null){
            panelController.setParcel(activity.getParcelableDescription(), activity.convertToParcel());
        }
        
               
        panelController.setCoordinator(this);
        barController.setCoordinator(this);
        navigationController.performViewNavigationTo(barController,panelController);
       
    }
    
}
