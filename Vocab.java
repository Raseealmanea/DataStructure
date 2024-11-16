/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.mycompany.mavenproject8;

/**
 *
 * @author slymanalmny
 */


public class Vocab {
    public String word;
    
    public Vocab() {
        word = "";
    }

    public Vocab(String word) {
        this.word = word;
    }

    @Override
    public String toString() {
        return word;
    }
}
