/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.mycompany.vetassignment;

/**
 *
 * @author keano
 */
public abstract class Animal {
    private String name;
    private String species;
    private String colour;
    private int age;
    private String ownerName;
    
    public Animal(String name, String species, String colour, int age, String ownerName) {
        this.name = name;
        this.species = species;
        this.colour = colour;
        this.age = age;
        this.ownerName = ownerName;
    }
    
    public String getName() {
        return this.name;
    }
    
    public String getSpecies() {
        return this.species;
    }
    
    public String getColour() {
        return this.colour;
    }
    
    public int getAge() {
        return this.age;
    }
    
    public String getOwnerName() {
        return this.ownerName;
    }
}
