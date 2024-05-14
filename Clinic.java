/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.mycompany.vetassignment;

import java.util.ArrayList;
import java.util.List;

/**
 *
 * @author keano
 */
public class Clinic {
    private String name;
    private List<Animal> pets;
    
    
    public Clinic(String name) {
        this.name = name;
        this.pets = new ArrayList<>();
    }
    
    public void addPet(Animal pet) {
        this.pets.add(pet);
    }
    
    public Animal getPet(int index) {
        return this.pets.get(index);
    }
    
    public List getPetList() {
        return this.pets;
    }
    
    public String getName() {
        return this.name;
    }
}
