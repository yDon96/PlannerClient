/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package CAAYcyclic.PlannerClient.enumeration;

/**
 *
 * @author Amos
 */

public enum TableViewHeaders {
    ACTIVITY( new String[] {"Activity Id", "Description", "ETA", "Interruptable", "Day", "Start time", "Maintainer Id", "Procedure Id"}),
  //  ACTIVITY2( new String[] {"Activity Id", "Description", "ETA", "Interruptable", "Week", "Maintainer Id", "Procedure Id"});
   
    MAINTAINER_AVAILABILITY ( new String[] {"Maintainer", "Skills", "00:00", "01:00", "02:00",
    "03:00", "04:00", "05:00", "06:00", "07:00","08:00", "09:00", "10:00",
    "11:00", "12:00","13:00","14:00","15:00","16:00","17:00","18:00",
    "19:00","20:00","21:00","22:00","23:00"});

    public final String[] value;

    private TableViewHeaders(String[] value) {
        this.value = value;
    }
}
