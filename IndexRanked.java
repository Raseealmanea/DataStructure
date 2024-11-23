package com.mycompany.mavenproject8;

public class IndexRanked {
    class File {
        int docID;
        LinkedList <String> index;

        public File() {
            docID = 0;
            index = new LinkedList <String>();
        }

        public void ADD (String word)
        {
            index.insert(word);
        }

        public boolean found(String word)
        {
            if (index.empty())
                return false;

            index.findFirst();
            for ( int i = 0 ; i < index.count ; i++)
            {
                if ( index.retrieve().compareTo(word) == 0)
                    return true;
                index.findNext();
            }
            return false;
        }
    }

    File [] indexes;
    freq [] freqs;


    public IndexRanked() {
        freqs = new freq [50];
        indexes = new File [50];
        for ( int i = 0 ; i < indexes.length ; i++)
        {
            indexes [i] = new File();
            indexes [i].docID = i;
        }
    }

    public void addFile ( int docID, String data)
    {
        indexes[docID].ADD(data);
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

        for ( int docs = 0 ; docs <50 ; docs++)
        {
            for ( int i = 0 ; i < words.length ; i++)
            {
                indexes[docs].index.findFirst();
                int wordcount = 0;
                for ( int x = 0 ; x < indexes[docs].index.size() ; x++ )
                {
                    if (indexes[docs].index.retrieve().compareTo(words[i])==0)
                        wordcount ++;
                    indexes[docs].index.findNext();
                }

                if (wordcount > 0)
                    freqs[docs].f += wordcount;
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
