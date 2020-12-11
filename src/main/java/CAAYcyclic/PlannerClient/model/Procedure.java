/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package CAAYcyclic.PlannerClient.model;

import CAAYcyclic.PlannerClient.model.Parcel;
import CAAYcyclic.PlannerClient.model.Parcelable;

/**
 *
 * @author Youssef
 */
public class Procedure implements Parcelable{
    
    Integer id;
    String title;
    String description;

    public Integer getId() {
        return id;
    }

    public String getTitle() {
        return title;
    }

    public String getDescription() {
        return description;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    @Override
    public String getParcelableDescription() {
        return "Procedure";
    }

    @Override
    public Parcel convertToParcel() {
        Parcel parcel = new Parcel();
        parcel.writeInteger(id);
        parcel.writeString(title);
        parcel.writeString(description);
        return parcel;
    }

    @Override
    public void createFromParcel(Parcel parcel) {
        this.id = parcel.readInteger();
        this.title = parcel.readString();
        this.description = parcel.readString();
    }
    
    
}
