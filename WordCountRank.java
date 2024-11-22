package com.mycompany.mavenproject8;

public class WordCountRank {
    Vocab word;
    int  [] docNumber ;

    public WordCountRank() {
        docNumber = new int [50];
        for (int i = 0 ; i < docNumber.length ; i++)
            docNumber [i] = 0;
        word = new Vocab("");
    }

    public WordCountRank(String word, int [] docIDS) {
        this.word = new Vocab(word);
        this.docNumber = new int [docIDS.length];
        for (int i = 0 ; i < docIDS.length ; i++)
            this.docNumber [i] = docIDS[i];

    }

    public void add_docNumber ( int docID)
    {
        this.docNumber[docID] ++;
    }

    public void setVocab(Vocab word)
    {
        this. word = word;
    }

    public Vocab getVocab()
    {
        return word;
    }

    public int [] getDocs ()
    {
        int [] test = new int [docNumber.length];
        for ( int i = 0 ; i < test.length ; i++)
            test[i] = docNumber[i];
        return test;
    }

    @Override
    public String toString() {
        String docs = "";
        for (int i = 0, j = 0 ; i < docNumber.length; i++)
            if ( j == 0 && docNumber [i] != 0 )
            {
                docs += " " + String.valueOf(i) ;
                j++;
            }
            else if ( docNumber [i] != 0 )
            {
                docs += ", " + String.valueOf(i) ;
                j++;
            }

        return word + "[" + docs + ']';
    }
}
