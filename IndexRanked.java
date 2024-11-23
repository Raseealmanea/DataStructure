package com.mycompany.mavenproject8;

public class IndexRanked {
    class File {
        int docNumber;
        LinkedList <String> pointer;

        public File() {
            docNumber = 0;
            pointer = new LinkedList <String>();
        }

        public void ADD (String word)
        {
            pointer.insert(word);
        }

        public boolean found(String word)
        {
            if (pointer.empty())
                return false;

            pointer.findFirst();
            for ( int i = 0 ; i < pointer.count ; i++)
            {
                if ( pointer.retrieve().compareTo(word) == 0)
                    return true;
                pointer.findNext();
            }
            return false;
        }
    }

    File [] files;
    freq [] freqs;


    public IndexRanked() {
        freqs = new freq [50];
        files = new File [50];
        for (int i = 0; i < files.length ; i++)
        {
            files[i] = new File();
            files[i].docNumber = i;
        }
    }

    public void addFile ( int docID, String data)
    {
        files[docID].ADD(data);
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
                files[docs].pointer.findFirst();
                int wordcount = 0;
                for (int x = 0; x < files[docs].pointer.size() ; x++ )
                {
                    if (files[docs].pointer.retrieve().compareTo(words[i])==0)
                        wordcount ++;
                    files[docs].pointer.findNext();
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
