/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package CAAYcyclic.PlannerClient.api.model;

/**
 *
 * @author User
 */
public class Activity {
    
    private Integer id;

    private Integer maintainerId;

    private Integer procedureId;

    private Integer week;

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
    
    
    
    
}
