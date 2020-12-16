/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package CAAYcyclic.PlannerClient.model;

import java.util.ArrayList;
import java.util.Set;

/**
 *
 * @author Amos
 */
public class Parcel {
    
    private ArrayList<String> stringList;
    private ArrayList<Integer> integerList;
    private ArrayList<String[]> stringArrayList;
    private ArrayList<Set<String>> stringArraySet;
    private ArrayList<Boolean> booleanList;
    private ArrayList<Parcel> parcelArrayList;
    
    public void writeString(String string){
        if(stringList == null){
            stringList = new ArrayList<>();
        }
        stringList.add(string);
    }
    
    public void writeInteger(Integer integer){
        if(integerList == null){
            integerList = new ArrayList<>();
        }
        integerList.add(integer);
    }
    
    public void writeBoolean(Boolean bool){
        if(booleanList == null){
            booleanList = new ArrayList<>();
        }
        booleanList.add(bool);
    }
    
    public void writeStringArray(String[] list){
        if(stringArrayList == null){
            stringArrayList = new ArrayList<>();
        }
        stringArrayList.add(list);
    }
    
     public void writeStringSet (Set<String> list){
        if(stringArraySet == null){
            stringArraySet = new ArrayList<>();
        }
        stringArraySet.add(list);
    }
    

    
     public void writeProcArray(ArrayList<Parcel> list){
        if(parcelArrayList == null){
            parcelArrayList = new ArrayList<>();
        }
        parcelArrayList.addAll(list);
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
    
     public Set<String> readStringSet(){
        return stringArraySet.remove(0);
    }
    
    public ArrayList readProcList(){
        return parcelArrayList;
        
    }
}
