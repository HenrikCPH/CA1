package entities;

import java.io.Serializable;
import java.util.Date;
import java.util.Objects;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Temporal;


@Entity
public class Members implements Serializable {

    private static final long serialVersionUID = 1L;
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;
    private String name;
    private String studentID;
    private String movie;
    
    @Temporal(javax.persistence.TemporalType.DATE)
    private Date created;
    
    @Temporal(javax.persistence.TemporalType.DATE)
    private Date lastEdited;
    
    private String groupnumber;
    

    public Members(String name, String studentID, String movie) {
        this.id = id;
        this.name = name;
        this.studentID = studentID;
        this.movie = movie;
        this.created = new Date();
        this.lastEdited = new Date();
        this.groupnumber = "Group 10";
        
    }
    
    
    
     public Members() {
    }

    public int getid() {
        return id;
    }

    public void setid(int id) {
        this.id = id;
    }

    public String getname() {
        return name;
    }

    public void setname(String name) {
        this.name = name;
    }

    public String getstudentID() {
        return studentID;
    }

    public void setstudentID(String studentID) {
        this.studentID = studentID;
    }
    
   
    
    

    public String getmovie() {
        return movie;
    }

    public void setmovie(String movie) {
        this.movie = movie;
    }
    
    
    
    
    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    @Override
    public int hashCode() {
        int hash = 7;
        hash = 19 * hash + Objects.hashCode(this.id);
        return hash;
    }

    

   
    
}
