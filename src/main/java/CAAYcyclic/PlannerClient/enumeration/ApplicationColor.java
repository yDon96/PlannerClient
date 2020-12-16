/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package CAAYcyclic.PlannerClient.enumeration;

import java.awt.Color;

/**
 *
 * @author Amosdonadeo
 */
public enum ApplicationColor {
    primaryColor(Color.decode("#031A6B")),
    accentColor(Color.decode("#04269F"));

    public final Color value;

    private ApplicationColor(Color value) {
        this.value = value;
    }
}
