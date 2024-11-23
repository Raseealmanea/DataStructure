package com.mycompany.mavenproject8;

public class InvertedIndex_Ranked {
    LinkedList <WordCountRank> invertedindex;
    freq [] freqs;

    public InvertedIndex_Ranked() {
        invertedindex = new LinkedList <WordCountRank>();
        freqs = new freq[50];
    }

    public int size()
    {
        return invertedindex.size();
    }

    public boolean ADD (int docID, String word)
    {
        if (invertedindex.empty())
        {
            WordCountRank t = new WordCountRank ();
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
                    WordCountRank t = invertedindex.retrieve();
                    t.add_docNumber(docID);
                    invertedindex.update(t);
                    return false;
                }
                invertedindex.findNext();
            }
            if ( invertedindex.retrieve().word.word.compareTo(word) == 0)
            {
                WordCountRank t = invertedindex.retrieve();
                t.add_docNumber(docID);
                invertedindex.update(t);
                return false;
            }
            else
            {
                WordCountRank t = new WordCountRank ();
                t.setVocab(new Vocab (word));
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

    public void FN(String str)
    {
        str = str.toLowerCase().trim();
        String [] words = str.split(" ");
        freqs = new freq[50];
        for ( int i = 0 ; i < 50 ; i++ )
        {
            freqs[i] = new freq();
            freqs[i].docID = i;
            freqs[i].f = 0;
        }

        for ( int i = 0 ; i < words.length ; i++)
        {
            if (found (words[i]))
            {
                int [] docs = invertedindex.retrieve().getDocs();
                for ( int j = 0 ; j < docs.length ; j ++)
                {
                    if (docs[j] != 0)
                    {
                        int index = j;
                        freqs[index].docID = index;
                        freqs[index].f += docs[j];
                    }
                }
            }
        }

        concatsort(freqs, 0, freqs.length-1 );

        System.out.println("\nDocIDt\tScore");
        for ( int x = 0 ;  freqs[x].f != 0 ; x++)
            System.out.println(freqs[x].docID + "\t\t" + freqs[x].f);
    }

    public static void concatsort ( freq [] A , int l , int r )
    {
        if ( l >= r )
            return;
        int m = ( l + r ) / 2;
        concatsort (A , l , m ) ;          // Sort first half
        concatsort (A , m + 1 , r ) ;    // Sort second half
        concat (A , l , m , r ) ;            // Merge
    }

    private static void concat ( freq [] A , int l , int m , int r )
    {
        freq [] B = new freq [ r - l + 1];
        int i = l , j = m + 1 , k = 0;

        while ( i <= m && j <= r )
        {
            if ( A [ i ].f >= A [ j ].f)
                B [ k ++] = A [ i ++];
            else
                B [ k ++] = A [ j ++];
        }

        if ( i > m )
            while ( j <= r )
                B [ k ++] = A [ j ++];
        else
            while ( i <= m )
                B [ k ++] = A [ i ++];

        for ( k = 0; k < B . length ; k ++)
            A [ k + l ] = B [ k ];
    }

}
