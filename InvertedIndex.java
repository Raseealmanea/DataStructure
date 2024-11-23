

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
            WordCount wc = new WordCount ();
            wc.setVocab(new Vocab (word));
            wc.add_docNumber(docID);
            invertedindex.insert(wc);
            return true;
        }
        else
        {
            invertedindex.findFirst();
            while ( ! invertedindex.last())
            {
                if ( invertedindex.retrieve().word.word.compareTo(word) == 0)
                {
                    WordCount wc = invertedindex.retrieve();
                    wc.add_docNumber(docID);
                    invertedindex.update(wc);
                    return false;
                }
                invertedindex.findNext();
            }
            if ( invertedindex.retrieve().word.word.compareTo(word) == 0)
            {
                WordCount wc = invertedindex.retrieve();
                wc.add_docNumber(docID);
                invertedindex.update(wc);
                return false;
            }
            else
            {
                WordCount wc = new WordCount ();
                wc.setVocab(new Vocab(word));
                wc.add_docNumber(docID);
                invertedindex.insert(wc);
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
    public boolean [] AO_Function (String word )
    {
        if (! word.contains(" OR ") && ! word.contains(" AND "))
        {
            boolean [] wo = new boolean[50];
            word = word.toLowerCase().trim();

            if (this.found (word))
                wo =  this.invertedindex.retrieve().getAllDoc();
            return wo;
        }

        else if (word.contains(" OR ") && word.contains(" AND "))
        {
            String [] ANDOR = word.split(" OR ");
            boolean []  wo = AND (ANDOR[0]);

            for ( int i = 1 ; i < ANDOR.length ; i++  )
            {
                boolean [] wo2 =AND (ANDOR[i]);

                for ( int j = 0 ; j < 50 ; j++ )
                    wo [j] = wo[j] || wo2[j];
            }
            return wo;
        }

        else  if (word.contains(" AND "))
            return AND (word);

        return OR (word);
    }

    public boolean [] AND (String word)
    {
        String [] AND= word.split(" AND ");
        boolean [] w1 = new boolean [50];

        if (this.found (AND[0].toLowerCase().trim()))
            w1 = this.invertedindex.retrieve().getAllDoc();

        for ( int i = 1 ; i< AND.length ; i++)
        {
            boolean [] w2 = new boolean [50];
            if (this.found (AND[i].toLowerCase().trim()))
                w2 = this.invertedindex.retrieve().getAllDoc();

            for ( int j = 0 ; j < 50 ; j++)
                w1 [j] = w1[j] && w2[j];
        }

        return w1;
    }

    public boolean [] OR (String word)
    {
        String [] OR = word.split(" OR ");
        boolean [] w1 = new boolean [50];

        if (this.found (OR[0].toLowerCase().trim()))
            w1 = this.invertedindex.retrieve().getAllDoc();

        for (int i = 1; i< OR.length ; i++)
        {
            boolean [] w2 = new boolean [50];
            if (this.found (OR[i].toLowerCase().trim()))
                w2 = this.invertedindex.retrieve().getAllDoc();

            for ( int j = 0 ; j < 50 ; j++)
                w1[j] = w1[j] || w2[j];

        }
        return w1;
    }


}
