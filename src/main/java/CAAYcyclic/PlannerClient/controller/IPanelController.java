/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package CAAYcyclic.PlannerClient.controller;

import java.util.logging.Logger;
import javax.swing.JPanel;

/**
 *
 * @author Youssef
 */
public interface IPanelController {
    
    /**
     * Panel associeted to panel controller
     */
    JPanel getPanel();
    
    /**
     *  Notifies the panel controller that its panel is about to be added to a panel hierarchy.
     */
    public void panelWillAppear();
    
    /**
     *  Notifies the panel controller that its panel was added to a panel hierarchy.
     */
    public void panelDidAppear();
    
    /**
     * Notifies the panel controller that its panel is about to be removed from a panel hierarchy.
     * 
     */
    public void panelWillDisappear();
    
    /**
     *  Notifies the panel controller that its panel was removed from a panel hierarchy.
     */
    public void panelDidDisappear();
    
    /**
     * Get logger assosieted to panel controller.
     * @return Logger
     */
    public Logger getLogger();
    
    /**
     * Set lockNavigation to true.
     */
    public void lockNavigation();
    
    /**
     *  Set lockNavigation to false.
     */
    public void unlockNavigation();
}
