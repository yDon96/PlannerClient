/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package CAAYcyclic.PlannerClient.controller.content;

import javax.swing.JPanel;

/**
 *
 * @author Youssef
 */
public interface IContentPanelController {
    
    /**
     * Set the panel that the controller manages
     * @param panelName Class<? extends JPanel>
     */
    public void setContentPanel(Class<? extends JPanel> panelName);
    
    /**
     * Set the panel that the controller manages
     * @param panel JPanel
     */
    public void setContentPanel(JPanel panel);
}
