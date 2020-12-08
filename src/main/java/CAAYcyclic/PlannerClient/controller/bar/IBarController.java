/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package CAAYcyclic.PlannerClient.controller.bar;

import javax.swing.JPanel;

/**
 *
 * @author Youssef
 */
public interface IBarController {
    
    /**
     * Set the panel that the controller manages
     * @param panelName Class<? extends JPanel>
     */
    void setBarPanel(Class<? extends JPanel> panelName);

}
