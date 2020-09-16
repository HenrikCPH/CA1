/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package dto;

import entities.Joke;

/**
 *
 * @author Marks
 */
public class JokeDTO {
    
    private int id;
    private String thejoke;
    private String reference;
    
    
    public JokeDTO(Joke joke){
        this.id = joke.getId();
        this.thejoke = joke.getThejoke();
        this.reference = joke.getReference();
    }

    public JokeDTO(int id, String thejoke, String reference) {
        this.id = id;
        this.thejoke = thejoke;
        this.reference = reference;
    }
    
    
    
    
    

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
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
    
    
}