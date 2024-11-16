/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.mycompany.mavenproject8;

/**
 *
 * @author slymanalmny
 */
public class RepetitionW {
    Vocab word;
    int count ;

    public RepetitionW() {
        count = 0;
        word = new Vocab("");
    }

    public RepetitionW(String word, int rank) {
        this.word = new Vocab(word);
        this.count = rank ;
    }

    public int addCount ()
    {
        return count++;
    }

    public int getCount ()
    {
        return this.count;
    }

    public Vocab getVocab()
    {
        return word;
    }

    public void setVocab(Vocab word)
    {
        this. word = word;
    }

    @Override
    public String toString() {
        return word + ", " + count ;
    }

}