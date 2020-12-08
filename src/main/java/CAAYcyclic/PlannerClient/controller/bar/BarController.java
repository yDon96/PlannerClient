/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package CAAYcyclic.PlannerClient.controller.bar;

import CAAYcyclic.PlannerClient.controller.PanelController;
import javax.swing.JPanel;

/**
 *
 * @author Youssef
 */
public abstract class BarController extends PanelController implements IBarController  {
    
        
    public BarController() {
        super();
    }
    
    public void setBarPanel(Class<? extends JPanel> panelName){
        LOG.log(java.util.logging.Level.INFO, "Set panel: {0}", panelName.getClass().getName());
        try{
            this.setPanel((JPanel) panelName.newInstance());
        } catch (IllegalAccessException | InstantiationException exception){
            LOG.log(java.util.logging.Level.WARNING, "Cannot instancete the panel.");
            return;
        }
        panelWillAppear();
        getContainerController().setBarPanel(this);
        panelDidAppear();
    }
    
    
}