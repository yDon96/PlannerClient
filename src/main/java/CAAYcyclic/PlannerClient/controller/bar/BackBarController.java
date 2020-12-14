/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package CAAYcyclic.PlannerClient.controller.bar;

import CAAYcyclic.PlannerClient.navigation.Segue;
import CAAYcyclic.PlannerClient.view.panel.bar.BackSideBarPanel;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.util.logging.Logger;

/**
 *
 * @author Youssef
 */
public class BackBarController extends BarController {

    private BackSideBarPanel mainSideBarView;
    
    private static final Logger LOG = Logger.getLogger(BackBarController.class.getName());

    public BackBarController() {
        super();
        setBarPanel(BackSideBarPanel.class);
        initComponent();
    }
    
    @Override
    public void panelDidAppear() {
        super.panelDidAppear();        
    }
    
     private void initComponent() {
        this.mainSideBarView = (BackSideBarPanel) getPanel();
        mainSideBarView.getBackBtn().addMouseListener(backBtnAction);
    }

     
    private MouseAdapter backBtnAction = new MouseAdapter() {
        @Override
        public void mousePressed(MouseEvent mouseEvent) {
            super.mousePressed(mouseEvent);
            backAction();
        }
    };
    
    private void backAction() {
      //  if(!isLockNavigation()){
            getCoordinator().popBack();
//        } else {
//            LOG.log(java.util.logging.Level.WARNING, "Cannot popBack, navigation is locked.");
//        }
    }

    @Override
    public Logger getLogger() {
        return LOG;
    }

    @Override
    public void prepare(Segue segue) {
    }
}
