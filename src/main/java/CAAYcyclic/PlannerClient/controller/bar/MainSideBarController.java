/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package CAAYcyclic.PlannerClient.controller.bar;

import CAAYcyclic.PlannerClient.controller.content.DashBoardPanelController;
import CAAYcyclic.PlannerClient.view.panel.bar.MainSideBarPanel;

import javax.swing.*;
import java.awt.event.*;
import CAAYcyclic.PlannerClient.controller.bar.IBarController;
import CAAYcyclic.PlannerClient.controller.content.ActivitiesPanelController;
import CAAYcyclic.PlannerClient.controller.content.ContentPanelController;
import CAAYcyclic.PlannerClient.navigation.Segue;
import java.util.logging.Logger;


/**
 *
 * @author Youssef
 */
public class MainSideBarController extends BarController {

    private MainSideBarPanel mainSideBarView;
    
    private static final Logger LOG = Logger.getLogger(MainSideBarController.class.getName());
    
    
    private JButton dashBtn;
    private JButton activitiesBtn;
    private JButton userBtn;


    public MainSideBarController() {
        super();
        setBarPanel(MainSideBarPanel.class);
         initComponet();
    }
    
   
    public void initComponet(){
        this.mainSideBarView = (MainSideBarPanel) getPanel();
        dashBtn = mainSideBarView.getDashBtn();
        activitiesBtn = mainSideBarView.getActivitiesBtn();
        userBtn = mainSideBarView.getMaintBtn();
        dashBtn.addMouseListener(dashBtnAction);
        activitiesBtn.addMouseListener(activitiesBtnAction);
        userBtn.addMouseListener(maintBtnAction);
        
    }
    

    @Override
    public void panelDidAppear() {
        super.panelDidAppear();
        
    }
    
    

    private MouseAdapter dashBtnAction = new MouseAdapter() {
        @Override
        public void mousePressed(MouseEvent mouseEvent) {
            super.mousePressed(mouseEvent);
            LOG.log(java.util.logging.Level.INFO, "DashButton selected.");
            swichAction(dashBtn);
            getCoordinator().switchPanelToDash();
        }
    };

    private MouseAdapter activitiesBtnAction = new MouseAdapter() {
        @Override
        public void mousePressed(MouseEvent mouseEvent) {
            super.mousePressed(mouseEvent);
            LOG.log(java.util.logging.Level.INFO, "ActivityButton selected.");
            swichAction(activitiesBtn);
               getCoordinator().switchPanelToActivitiesPanel();

        }
    };

    private MouseAdapter maintBtnAction = new MouseAdapter() {
        @Override
        public void mousePressed(MouseEvent mouseEvent) {
            super.mousePressed(mouseEvent);
            LOG.log(java.util.logging.Level.INFO, "MaintainerButton selected.");
        }
    };
    
       
     private void swichAction(JButton buttonToHighlight) {
        if (!isLockNavigation()) {
            mainSideBarView.highlightButton(buttonToHighlight);
        } else {
            LOG.log(java.util.logging.Level.WARNING, "Cannot swich panel, navigation is locked.");
        }
    }

    @Override
    public Logger getLogger() {
        return LOG;
    }

    @Override
    public void prepare(Segue segue) {
        
    }
}
