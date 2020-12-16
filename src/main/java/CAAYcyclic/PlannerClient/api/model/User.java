/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package CAAYcyclic.PlannerClient.api.model;

import java.time.LocalDate;

/**
 *
 * @author Youssef
 */
@Deprecated
public class User {
    
    Integer user_id;
    String name;
    String surname;
    LocalDate dob;
    String role;

    public Integer getUser_id() {
        return user_id;
    }

    public String getName() {
        return name;
    }

    public String getSurname() {
        return surname;
    }

    public LocalDate getDob() {
        return dob;
    }

    public String getRole() {
        return role;
    }

    public void setUser_id(Integer user_id) {
        this.user_id = user_id;
    }

    public void setName(String name) {
        this.name = name;
    }

    public void setSurname(String surname) {
        this.surname = surname;
    }

    public void setDob(LocalDate dob) {
        this.dob = dob;
    }

    public void setRole(String role) {
        this.role = role;
    }
    
    
}
