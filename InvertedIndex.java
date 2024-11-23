

package com.mycompany.mavenproject8;


public class InvertedIndex {

    LinkedList <WordCount> invertedindex;

    public InvertedIndex() {
        invertedindex = new LinkedList <WordCount>();
    }


    public boolean ADD (int docID, String word)
    {
        if (invertedindex.empty())
        {
            WordCount t = new WordCount ();
            t.setVocab(new Vocab (word));
            t.add_docNumber(docID);
            invertedindex.insert(t);
            return true;
        }
        else
        {
            invertedindex.findFirst();
            while ( ! invertedindex.last())
            {
                if ( invertedindex.retrieve().word.word.compareTo(word) == 0)
                {
                    WordCount t = invertedindex.retrieve();
                    t.add_docNumber(docID);
                    invertedindex.update(t);
                    return false;
                }
                invertedindex.findNext();
            }
            if ( invertedindex.retrieve().word.word.compareTo(word) == 0)
            {
                WordCount t = invertedindex.retrieve();
                t.add_docNumber(docID);
                invertedindex.update(t);
                return false;
            }
            else
            {
                WordCount t = new WordCount ();
                t.setVocab(new Vocab(word));
                t.add_docNumber(docID);
                invertedindex.insert(t);
            }
            return true;
        }
    }

    public boolean found(String word)
    {
        if (invertedindex.empty())
            return false;

        invertedindex.findFirst();
        for ( int i = 0 ; i < invertedindex.count ; i++)
        {
            if ( invertedindex.retrieve().word.word.compareTo(word) == 0)
                return true;
            invertedindex.findNext();
        }
        return false;
    }
    public boolean [] AO_Function (String str )
    {
        if (! str.contains(" OR ") && ! str.contains(" AND "))
        {
            boolean [] r1 = new boolean[50];
            str = str.toLowerCase().trim();

            if (this.found (str))
                r1 =  this.invertedindex.retrieve().getAllDoc();
            return r1;
        }

        else if (str.contains(" OR ") && str.contains(" AND "))
        {
            String [] AND_ORs = str.split(" OR ");
            boolean []  r1 = AND (AND_ORs[0]);

            for ( int i = 1 ; i < AND_ORs.length ; i++  )
            {
                boolean [] r2 =AND (AND_ORs[i]);

                for ( int j = 0 ; j < 50 ; j++ )
                    r1 [j] = r1[j] || r2[j];
            }
            return r1;
        }

        else  if (str.contains(" AND "))
            return AND (str);

        return OR (str);
    }

    public boolean [] AND (String str)
    {
        String [] ANDs = str.split(" AND ");
        boolean [] b1 = new boolean [50];

        if (this.found (ANDs[0].toLowerCase().trim()))
            b1 = this.invertedindex.retrieve().getAllDoc();

        for ( int i = 1 ; i< ANDs.length ; i++)
        {
            boolean [] b2 = new boolean [50];
            if (this.found (ANDs[i].toLowerCase().trim()))
                b2 = this.invertedindex.retrieve().getAllDoc();

            for ( int j = 0 ; j < 50 ; j++)
                b1 [j] = b1[j] && b2[j];
        }

        return b1;
    }

    public boolean [] OR (String str)
    {
        String [] ORs = str.split(" OR ");
        boolean [] b1 = new boolean [50];

        if (this.found (ORs[0].toLowerCase().trim()))
            b1 = this.invertedindex.retrieve().getAllDoc();

        for ( int i = 1 ; i< ORs.length ; i++)
        {
            boolean [] b2 = new boolean [50];
            if (this.found (ORs[i].toLowerCase().trim()))
                b2 = this.invertedindex.retrieve().getAllDoc();

            for ( int j = 0 ; j < 50 ; j++)
                b1 [j] = b1[j] || b2[j];

        }
        return b1;
    }

    public void printDocment()
    {
        if (this.invertedindex.empty())
            System.out.println("Empty Inverted Index");
        else
        {
            this.invertedindex.findFirst();
            while ( ! this.invertedindex.last())
            {
                System.out.println(invertedindex.retrieve());
                this.invertedindex.findNext();
            }
            System.out.println(invertedindex.retrieve());
        }
    }
}
