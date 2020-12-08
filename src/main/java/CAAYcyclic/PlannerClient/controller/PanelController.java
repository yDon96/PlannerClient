/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package CAAYcyclic.PlannerClient.controller;

import CAAYcyclic.PlannerClient.controller.container.ContainerController;
import CAAYcyclic.PlannerClient.factory.container.IContainerViewAbstractFactory;
import CAAYcyclic.PlannerClient.navigation.NavigationController;
import CAAYcyclic.PlannerClient.navigation.Segue;
import java.util.logging.Logger;
import javax.swing.JPanel;

/**
 *
 * @author Youssef
 */
public abstract class PanelController implements IPanelController{
    
    private JPanel panel;
    
    private ContainerController containerController;
    
    private boolean lockNavigation = false;
    
    protected Logger LOG;

    public PanelController() {
        LOG = getLogger();
        containerController = NavigationController.getInstance().getCurrentContainer();
    }

    public void setPanel(JPanel panel) {
        this.panel = panel;
    }

    public void setContainerController(ContainerController containerController) {
        this.containerController = containerController;
    }

    public ContainerController getContainerController() {
        return containerController;
    }
    

    @Override
    public void unlockNavigation(){
        LOG.log(java.util.logging.Level.WARNING, "Unlock Navigation.");
        lockNavigation = false;
    }

    @Override
    public void lockNavigation(){
        LOG.log(java.util.logging.Level.WARNING, "Lock Navigation.");
        lockNavigation = true;
    }

    public boolean isLockNavigation() {
        return lockNavigation;
    }
    
    @Override
    public void panelWillAppear(){
        LOG.log(java.util.logging.Level.INFO, "{0} will appear.", panel.getClass().getName());
    }
    
    @Override
    public void panelDidAppear(){
        LOG.log(java.util.logging.Level.INFO, "{0} did appear.", panel.getClass().getName());
    }
    
    @Override
    public void panelWillDisappear(){
        LOG.log(java.util.logging.Level.INFO, "{0} will disappear.", panel.getClass().getName());
    }
    
    @Override
    public void panelDidDisappear(){
        LOG.log(java.util.logging.Level.INFO, "{0} did disappear.", panel.getClass().getName());
    }
    /**
     * Notifies the view controller that a navigation event is about to be performed.
     * Return an object "Segue" that contain all info about new Panel.
     * @param segue
     */
    public abstract void prepare(Segue segue);
    
    /**
     * Initiates the new panel with the specified panel class from the current panel controller's 
     * @param panelName PanelController
     */
    public void startPanel(Class<? extends PanelController> panelName){
        LOG.log(java.util.logging.Level.INFO, "Switch current panel with: {0}", panelName.getName());
        NavigationController.getInstance().performPanelNavigationTo(panelName);
    }
    /**
     * Initiates the new view with the specified view factory from the current panel controller's 
     * @param factory IContainerViewAbstractFactory
     */
    public void startView(IContainerViewAbstractFactory factory){
        LOG.log(java.util.logging.Level.INFO, "Switch current view with the one given from: {0}", factory.getClass().getName());
        NavigationController.getInstance().performViewNavigationTo(factory);
    }
    
    /**
     * Pops to last view controller from the navigation stack and updates the display.
     */
    public void popBackView(){
        LOG.log(java.util.logging.Level.INFO, "Pop back to last view.");
        if(lockNavigation == false){
            NavigationController.getInstance().performBackToStack();
        } else {
            LOG.log(java.util.logging.Level.WARNING, "Navigation is locked.");
        }
    }
    
    @Override
    public JPanel getPanel() {
        return panel;
    }
}
