/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package CAAYcyclic.PlannerClient.builder.AlertDialog;

import java.awt.event.MouseAdapter;

/**
 *
 * @author Youssef
 */
public interface AlertBuilder {
    
     /**
     * Set Title to display in Alert Panel.
     * @param title
     */
    void setTitle(String title);
    
     /**
     * Set Message to display in Alert Panel.
     * @param message
     */
    void setMessage(String message);
    
     /**
     * Set Positive Button to display in Alert Panel.
     * @param value Text to display.
     * @param action Action to perform.
     */
    void setPositiveButton(String value,MouseAdapter action);
    
     /**
     * Set Negative Button to display in Alert Panel.
     * @param value Text to display.
     * @param action Action to perform.
     */
    void setNegativeButton(String value,MouseAdapter action);
}
