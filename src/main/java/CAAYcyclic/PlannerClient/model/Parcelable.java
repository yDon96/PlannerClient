/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package CAAYcyclic.PlannerClient.model;

/**
 *
 * @author Amos
 */
public interface Parcelable {
    
     String getParcelableDescription();
     
     Parcel convertToParcel();
     
     void createFromParcel(Parcel parcel);
    
}
