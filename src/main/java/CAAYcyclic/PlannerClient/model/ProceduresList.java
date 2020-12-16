/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package CAAYcyclic.PlannerClient.model;

import java.util.ArrayList;

/**
 *
 * @author Amos
 */
public class ProceduresList implements Parcelable{
    
    ArrayList<Parcel> list;

    @Override
    public String getParcelableDescription() {
         return "ProcedureList";
    }

    @Override
    public Parcel convertToParcel() {
        Parcel parcel = new Parcel();
        parcel.writeProcArray(list);
      
        return parcel;
    }

    @Override
    public void createFromParcel(Parcel parcel) {
        this.list = parcel.readProcList();
    }

    public ArrayList<Parcel> getList() {
        return list;
    }

    public void setList(ArrayList<Parcel> list) {
        this.list = list;
    }

    @Override
    public String toString() {
        return "ProceduresList{" + "list=" + list + '}';
    }
    
    
    
    
}
