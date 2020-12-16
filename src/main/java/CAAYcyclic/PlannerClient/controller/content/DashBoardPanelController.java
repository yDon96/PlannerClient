/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package CAAYcyclic.PlannerClient.controller.content;

import CAAYcyclic.PlannerClient.view.panel.content.DashBoardPanel;
import java.util.logging.Logger;


/**
 *
 * @author Amos
 */
public class DashBoardPanelController extends ContentPanelController {

    private static final Logger LOG = Logger.getLogger(DashBoardPanelController.class.getName());

    public DashBoardPanelController() {
        super();
        setContentPanel(DashBoardPanel.class);
    }

    @Override
    public void panelWillAppear() {
        super.panelWillAppear();
    }

    @Override
    public Logger getLogger() {
        return LOG;
    }
    
}
