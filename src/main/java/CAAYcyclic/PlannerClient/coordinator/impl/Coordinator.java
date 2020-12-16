/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package CAAYcyclic.PlannerClient.coordinator.impl;

import CAAYcyclic.PlannerClient.coordinator.ICoordinator;
import CAAYcyclic.PlannerClient.navigation.NavigationController;
import java.util.ArrayList;
import java.util.Objects;

/**
 *
 * @author Amos
 */
public abstract class Coordinator implements ICoordinator{
    
    protected ArrayList<Coordinator> childCoordinator;
    protected NavigationController navigationController;

    @Override
    public int hashCode() {
        int hash = 3;
        hash = 17 * hash + Objects.hashCode(this.childCoordinator);
        hash = 17 * hash + Objects.hashCode(this.navigationController);
        return hash;
    }

    @Override
    public boolean equals(Object obj) {
        if (this == obj) {
            return true;
        }
        if (obj == null) {
            return false;
        }
        if (getClass() != obj.getClass()) {
            return false;
        }
        final Coordinator other = (Coordinator) obj;
        if (!Objects.equals(this.childCoordinator, other.childCoordinator)) {
            return false;
        }
        if (!Objects.equals(this.navigationController, other.navigationController)) {
            return false;
        }
        return true;
    }
    
}
