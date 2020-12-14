/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package CAAYcyclic.PlannerClient.enumeration;

/**
 *
 * @author User
 */
public enum TableViewHeaders {
    ACTIVITY( new String[] {"Activity Id", "Description", "ETA", "Interruptable", "Week", "Maintainer Id", "Procedure Id"});
   

    public final String[] value;

    private TableViewHeaders(String[] value) {
        this.value = value;
    }
}
