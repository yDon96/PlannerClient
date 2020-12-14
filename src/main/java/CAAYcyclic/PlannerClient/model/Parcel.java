/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package CAAYcyclic.PlannerClient.model;

import java.util.ArrayList;

/**
 *
 * @author Youssef
 */
public class Parcel {
    
    private ArrayList<String> stringList;
    private ArrayList<Integer> integerList;
    private ArrayList<String[]> stringArrayList;
    private ArrayList<Boolean> booleanList;
    private ArrayList<Parcel> procedureArrayList;
    
    public void writeString(String string){
        if(stringList == null){
            stringList = new ArrayList<String>();
        }
        stringList.add(string);
    }
    
    public void writeInteger(Integer integer){
        if(integerList == null){
            integerList = new ArrayList<Integer>();
        }
        integerList.add(integer);
    }
    
    public void writeBoolean(Boolean bool){
        if(booleanList == null){
            booleanList = new ArrayList<Boolean>();
        }
        booleanList.add(bool);
    }
    
    public void writeStringArray(String[] list){
        if(stringArrayList == null){
            stringArrayList = new ArrayList<String[]>();
        }
        stringArrayList.add(list);
    }
    
     public void writeProcArray(ArrayList<Parcel> list){
        if(procedureArrayList == null){
            procedureArrayList = new ArrayList<Parcel>();
        }
        procedureArrayList.addAll(list);
    }
    
    public String readString(){
        return stringList.remove(0);
    }
    
    public Integer readInteger(){
        return integerList.remove(0);
    }
    
    public Boolean readBoolean(){
        return booleanList.remove(0);
    }
    
    public String[] readStringArray(){
        return stringArrayList.remove(0);
    }
    
    public ArrayList readProcList(){
        return procedureArrayList;
        
    }
}
