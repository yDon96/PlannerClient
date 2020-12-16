/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package CAAYcyclic.PlannerClient.controller.bar;

import CAAYcyclic.PlannerClient.view.panel.bar.MainSideBarPanel;

import javax.swing.*;
import java.awt.event.*;
import java.util.logging.Logger;


/**
 *
 * @author Amos
 */
public class MainSideBarController extends BarController {

    private MainSideBarPanel mainSideBarView;
    
    private static final Logger LOG = Logger.getLogger(MainSideBarController.class.getName());
    
    
    private JButton dashBtn;
    private JButton activitiesBtn;
    private JButton maintBtn;


    public MainSideBarController() {
        super();
        setBarPanel(MainSideBarPanel.class);
         initComponet();
    }
    
   
    public void initComponet(){
        this.mainSideBarView = (MainSideBarPanel) getPanel();
        dashBtn = mainSideBarView.getDashBtn();
        activitiesBtn = mainSideBarView.getActivitiesBtn();
        maintBtn = mainSideBarView.getMaintBtn();
        dashBtn.addMouseListener(dashBtnAction);
        activitiesBtn.addMouseListener(activitiesBtnAction);
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
            swichAction(maintBtn);
               getCoordinator().switchPanelToMaintPanel();

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

}
