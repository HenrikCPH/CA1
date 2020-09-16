/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package entities;

import java.io.Serializable;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;

/**
 *
 * @author Marks
 */
@Entity
public class Joke implements Serializable {

    private static final long serialVersionUID = 1L;
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private int id;
    String thejoke;
    String reference;

    public Joke(String thejoke, String reference) {
        this.id = id;
        this.thejoke = thejoke;
        this.reference = reference;
    }

    public Joke() {
    }
    
    

    public String getThejoke() {
        return thejoke;
    }

    public void setThejoke(String thejoke) {
        this.thejoke = thejoke;
    }

    public String getReference() {
        return reference;
    }

    public void setReference(String reference) {
        this.reference = reference;
    }

    

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

   
    
}
