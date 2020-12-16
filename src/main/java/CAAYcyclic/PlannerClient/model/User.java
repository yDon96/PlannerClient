/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package CAAYcyclic.PlannerClient.model;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.Set;

/**
 *
 * @author Amos
 */
public class User implements Parcelable{
    
    private Integer user_id;
    private String name;
    private String surname;
    private LocalDate dob;
    private String role;
    private Set<String> competencies;

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

    public void setDob(LocalDate dob) {
        this.dob = dob;
    }

    public Set<String> getCompetencies() {
        return competencies;
    }

    public void setCompetencies(Set<String> competencies) {
        this.competencies = competencies;
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

    public void setRole(String role) {
        this.role = role;
    }

    @Override
    public String getParcelableDescription() {
        return "User";
    }

    @Override
    public Parcel convertToParcel() {
        Parcel parcel = new Parcel();
        parcel.writeInteger(user_id);
        parcel.writeString(name);
        parcel.writeString(surname);
        parcel.writeString(dob.format(DateTimeFormatter.ofPattern("dd/MM/yyyy")));
        parcel.writeString(role);        
        parcel.writeStringSet(this.competencies);
        
        return parcel;
    }

    @Override
    public void createFromParcel(Parcel parcel) {
        this.user_id = parcel.readInteger();
        this.name = parcel.readString();
        this.surname = parcel.readString();
        this.dob = LocalDate.parse((parcel.readString()),DateTimeFormatter.ofPattern("dd/MM/yyyy"));
        this.role = parcel.readString();
        this.competencies = parcel.readStringSet();
        
        
    }

    @Override
    public String toString() {
        return "User{" + "user_id=" + user_id + ", name=" + name + ", surname=" + surname + ", dateOfBirth=" + dob + ", role=" + role + ", competencies=" + competencies + '}';
    }
    
    
    
}
