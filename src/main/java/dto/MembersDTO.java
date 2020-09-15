/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package dto;

import entities.Members;

/**
 *
 * @author Yones
 */
public class MembersDTO { 

    
    private int id;
    private String name;
    private String studentID;
    private String movie;
            
    public MembersDTO(){}
    
    public MembersDTO(Members members) {
        this.id = members.getid();
        this.name = members.getname();
        this.studentID = members.getstudentID();
        this.movie = members.getmovie();
        
          }
    public MembersDTO(int id, String name, String studentID, String movie) {
        this.id = id;
        this.name = name;
        this.studentID = studentID;
        this.movie = movie;
        
        
    }
    
    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getStudentID() {
        return studentID;
    }

    public void setStudentID(String studentID) {
        this.studentID = studentID;
    }

    public String getMovie() {
        return movie;
    }

    public void setMovie(String movie) {
        this.movie = movie;
    }



}