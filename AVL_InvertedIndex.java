package com.mycompany.mavenproject8;



public class AVL_InvertedIndex {

    AVL<String, WordCount> AVLInverIndex  ;

    public AVL_InvertedIndex() {
        AVLInverIndex  = new AVL <String, WordCount>();
    }

    public int size()
    {
        return AVLInverIndex.size();
    }

    public boolean added (int docID, String word)
    {
        if (AVLInverIndex .empty())
        {
            WordCount t = new WordCount ();
            t.setVocab(new Vocab (word));
            t.add_docNumber(docID);
            AVLInverIndex.insert(word, t);
            return true;
        }
        else
        {
            if (AVLInverIndex.find(word))
            {
                WordCount t = AVLInverIndex.retrieve();
                t.add_docNumber(docID);
                AVLInverIndex.update(t);
                return false;

            }

            WordCount t = new WordCount ();
            t.setVocab(new Vocab (word));
            t.add_docNumber(docID);
            AVLInverIndex.insert(word, t);
            return true;
        }
    }

    public boolean FOUND(String word)
    {
        return AVLInverIndex.find(word);
    }



    public boolean [] AO_Function (String S)
    {
        if (! S.contains(" OR ") && ! S.contains(" AND "))
        {
            boolean [] r1 = new boolean[50];
            S = S.toLowerCase().trim();

            if (this.FOUND (S))
                r1 =  this.AVLInverIndex.retrieve().getAllDoc();
            return r1;
        }

        else if (S.contains(" OR ") && S.contains(" AND "))
        {
            String [] ANDOR = S.split(" OR ");
            boolean []  r1 = AND (ANDOR[0]);

            for ( int i = 1 ; i < ANDOR.length ; i++  )
            {
                boolean [] r2 =AND (ANDOR[i]);

                for ( int j = 0 ; j < 50 ; j++ )
                    r1 [j] = r1[j] || r2[j];
            }
            return r1;
        }

        else  if (S.contains(" AND "))
            return AND(S);

        return OR (S);
    }

    public boolean [] AND (String str)
    {
        String [] ANDs = str.split(" AND ");
        boolean [] b1 = new boolean [50];

        if (this.FOUND (ANDs[0].toLowerCase().trim()))
            b1 = this.AVLInverIndex.retrieve().getAllDoc();

        for ( int i = 1 ; i< ANDs.length ; i++)
        {
            boolean [] b2 = new boolean [50];
            if (this.FOUND (ANDs[i].toLowerCase().trim()))
                b2 = this.AVLInverIndex.retrieve().getAllDoc();

            for ( int j = 0 ; j < 50 ; j++)
                b1 [j] = b1[j] && b2[j];
        }
        return b1;
    }

    public boolean [] OR (String str)
    {
        String [] ORs = str.split(" OR ");
        boolean [] b1 = new boolean [50];

        if (this.FOUND (ORs[0].toLowerCase().trim()))
            b1 = this.AVLInverIndex.retrieve().getAllDoc();

        for ( int i = 1 ; i< ORs.length ; i++)
        {
            boolean [] b2 = new boolean [50];
            if (this.FOUND (ORs[i].toLowerCase().trim()))
                b2 = this.AVLInverIndex.retrieve().getAllDoc();

            for ( int j = 0 ; j < 50 ; j++)
                b1 [j] = b1[j] || b2[j];

        }
        return b1;
    }

}