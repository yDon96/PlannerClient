/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package CAAYcyclic.PlannerClient.model;

import java.time.DayOfWeek;


/**
 *
 * @author Amos
 */
public class Activity implements Parcelable{
    
    private Integer id;

    private Integer maintainerId;

    private Integer procedureId;

    private Integer week;
    
    private Integer startingDay;
    
    private Integer startingHour;
 

    private boolean interruptable;

    private Integer estimatedTime;

    private String description;
    

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public Integer getMaintainerId() {
        return maintainerId;
    }

    public void setMaintainerId(Integer maintainerId) {
        this.maintainerId = maintainerId;
    }

    public Integer getProcedureId() {
        return procedureId;
    }

    public void setProcedureId(Integer procedureId) {
        this.procedureId = procedureId;
    }

    public Integer getWeek() {
        return week;
    }

    public void setWeek(Integer week) {
        this.week = week;
    }

    public boolean isInterruptable() {
        return interruptable;
    }
    
    public String isInterruptableYesNo() {
          
        return interruptable? "Yes": "No";
                

    }
    
    public String stringStartingTime(){
        if(startingHour !=null)
            return String.format("%02d",startingHour)+":00";
        else
            return "";
    }
    
    public String stringDay(){
        if(startingDay !=null)
            return DayOfWeek.of(this.getStartingDay()).name();
        else
            return "";
    }


    public void setInterruptable(boolean interruptable) {
        this.interruptable = interruptable;
    }
  

    public Integer getEstimatedTime() {
        return estimatedTime;
    }

    public void setEstimatedTime(Integer estimatedTime) {
        this.estimatedTime = estimatedTime;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    @Override
    public String getParcelableDescription() {
      return "Activity";
    }

    public Integer getStartingDay() {
        return startingDay;
    }

    public void setStartingDay(Integer startingDay) {
        this.startingDay = startingDay;
    }

    public Integer getStartingHour() {
        return startingHour;
    }

    public void setStartingHour(Integer startingHour) {
        this.startingHour = startingHour;
    }
    
    

    @Override
    public Parcel convertToParcel() {
         Parcel parcel = new Parcel();
         
         parcel.writeInteger(id);
         parcel.writeInteger(maintainerId);
         parcel.writeInteger(procedureId);
         parcel.writeInteger(week);
         parcel.writeInteger(startingDay);
         parcel.writeInteger(startingHour);
         parcel.writeInteger(estimatedTime);
         parcel.writeString(description);
         parcel.writeBoolean(interruptable);
         
         return parcel;    

    }

    @Override
    public void createFromParcel(Parcel parcel) {
        
        this.id = parcel.readInteger();
        this.maintainerId = parcel.readInteger();
        this.procedureId = parcel.readInteger();
        this.week = parcel.readInteger();
        this.startingDay = parcel.readInteger();
        this.startingHour = parcel.readInteger();
        this.estimatedTime = parcel.readInteger();        
        this.description = parcel.readString();        
        this.interruptable = parcel.readBoolean();
        
    }

    @Override
    public String toString() {
        return "Activity{" + "id=" + id + ", maintainerId=" + maintainerId + ", procedureId=" + procedureId + ", week=" + week + ", startingDay=" + startingDay + ", startingHour=" + startingHour + ", interruptable=" + interruptable + ", estimatedTime=" + estimatedTime + ", description=" + description + '}';
    }
    
    
  
}
