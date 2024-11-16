package com.mycompany.mavenproject8;

public class BST_InvertedIndex {
    BSTtree < String , WordCount> invertedindexBST;
    int count = 0;

    public BST_InvertedIndex() {
        invertedindexBST = new BSTtree< String , WordCount> ();
    }

    public int size()
    {
        return invertedindexBST.count;
    }

    public boolean added (int docID, String word)
    {
        if (invertedindexBST.empty())
        {
            count ++;
            WordCount t = new WordCount ();
            t.setVocab(new Vocab(word));
            t.add_docNumber(docID);
            invertedindexBST.insert(word, t);
            return true;
        }
        else
        {
            if (invertedindexBST.find(word))
            {
                WordCount t = this.invertedindexBST.retrieve();
                t.add_docNumber(docID);
                invertedindexBST.update(t);
                return false;

            }

            count ++;
            WordCount t = new WordCount ();
            t.setVocab(new Vocab(word));
            t.add_docNumber(docID);
            invertedindexBST.insert(word, t);
            return true;
        }
    }

    public boolean FOUND(String word)
    {
        return invertedindexBST.find(word);
    }

    public void DOCprint()
    {
        invertedindexBST.loopT();
    }
    //=====================================================================
    public boolean [] AO_Function (String str )
    {
        if (! str.contains(" OR ") && ! str.contains(" AND "))
        {
            boolean [] r1 = new boolean[50];
            str = str.toLowerCase().trim();

            if (this.FOUND (str))
                r1 =  this.invertedindexBST.retrieve().getAllDoc();
            return r1;
        }

        else if (str.contains(" OR ") && str.contains(" AND "))
        {
            String [] ANDOR = str.split(" OR ");
            boolean []  r1 = AND (ANDOR[0]);

            for ( int i = 1 ; i < ANDOR.length ; i++  )
            {
                boolean [] r2 =AND (ANDOR[i]);

                for ( int j = 0 ; j < 50 ; j++ )
                    r1 [j] = r1[j] || r2[j];
            }
            return r1;
        }

        else  if (str.contains(" AND "))
            return AND (str);

        return OR(str);
    }

    public boolean [] AND (String str)
    {
        String [] ANDs = str.split(" AND ");
        boolean [] b1 = new boolean [50];

        if (this.FOUND (ANDs[0].toLowerCase().trim()))
            b1 = this.invertedindexBST.retrieve().getAllDoc();

        for ( int i = 1 ; i< ANDs.length ; i++)
        {
            boolean [] b2 = new boolean [50];
            if (this.FOUND (ANDs[i].toLowerCase().trim()))
                b2 = this.invertedindexBST.retrieve().getAllDoc();

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
            b1 = this.invertedindexBST.retrieve().getAllDoc();

        for ( int i = 1 ; i< ORs.length ; i++)
        {
            boolean [] b2 = new boolean [50];
            if (this.FOUND (ORs[i].toLowerCase().trim()))
                b2 = this.invertedindexBST.retrieve().getAllDoc();

            for ( int j = 0 ; j < 50 ; j++)
                b1 [j] = b1[j] || b2[j];

        }
        return b1;
    }
}
