/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.mycompany.mavenproject8;

public class WordCount {
    Vocab word;
    boolean [] docNumber ;

    public WordCount() {
        docNumber = new boolean [50];
        for (int i = 0 ; i < docNumber.length ; i++)
            docNumber [i] = false;
        word = new Vocab("");
    }

    public WordCount(String word, boolean [] docN) {
        this.word = new Vocab(word);
        this.docNumber = new boolean [docN.length];
        for (int i = 0 ; i < docN.length ; i++)
            this.docNumber [i] = docN[i];

    }
 

    public void setVocab(Vocab word)
    {
        this. word = word; 
    }
    
    public Vocab getVocab()
    {
         return word;
    }
    
    public boolean [] getAllDoc ()
    {
        boolean [] test = new boolean [docNumber.length];
        for ( int i = 0 ; i < test.length ; i++)
            test[i] = docNumber[i];
        return test;
    }

    public boolean add_docNumber ( int docN)
    {
        if (! docNumber[docN])
        {
            this.docNumber[docN] = true;
            return true;
        }
        return false;
    }

    @Override
    public String toString() {
        String documant = "";
        for (int i = 0, j = 0 ; i < docNumber.length; i++)
            if ( j == 0 && docNumber [i]==true )
            {
                documant += " " + String.valueOf(i) ;
                j++;
            }
            else if ( docNumber [i]==true )
            {
                documant += ", " + String.valueOf(i) ;
                j++;
            }
        
        return word + "[" +  documant + ']';
    }




    
}
