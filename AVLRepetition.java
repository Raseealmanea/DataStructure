package com.mycompany.mavenproject8;

public class AVLRepetition {


    AVL <Integer, AVL<String,RepetitionW>> AVLrank;
    freq [] freqs = new freq[50];

    public AVLRepetition() {
        AVLrank = new AVL <Integer, AVL <String,RepetitionW>>();

    }

    public boolean ADDnew (int Documentid, String Word)
    {
        if (AVLrank.empty())
        {
            AVL <String,RepetitionW> miniRepetitionW= new AVL <String,RepetitionW>();
            miniRepetitionW.insert(Word, new RepetitionW (Word,1));

            AVLrank.insert(Documentid, miniRepetitionW);
            return true;
        }
        else
        {
            if (AVLrank.find(Documentid))
            {
                AVL <String,RepetitionW> miniRank= AVLrank.retrieve();
                if (miniRank.find(Word))
                {
                    RepetitionW rank = miniRank.retrieve();
                    rank.addCount();
                    miniRank.update(rank);
                    AVLrank.update(miniRank);
                    return false;
                }
                miniRank.insert(Word, new RepetitionW (Word , 1));
                AVLrank.update(miniRank);
                return true;
            }
            AVL <String,RepetitionW> miniRank= new AVL <String,RepetitionW>();
            miniRank.insert(Word, new RepetitionW (Word,1));

            AVLrank.insert(Documentid, miniRank);
            return true;
        }
    }

    public boolean FOUND (int docID, String word)
    {
        if (AVLrank.find(docID) )
            if (AVLrank.retrieve().find(word))
                return true;
        return false;
    }

    public int getRepetition (int docID, String word)
    {
        int value = 0;
        if (AVLrank.find(docID) )
            if (AVLrank.retrieve().find(word))
                return AVLrank.retrieve().retrieve().getCount();
        return value;

    }

    public void FN (String word)
    {
        word = word.toLowerCase().trim();
        String [] words = word.split(" ");

        int index = 0;
        for ( int docID = 0 ; docID < 50 ; docID++ )
        {
            int count = 0 ;
            for ( int j = 0 ;j < words.length ; j++ )
                count += this.getRepetition(docID, words[j]);
            if (count > 0)
            {
                freqs[index] = new freq();
                freqs[index].docID = docID;
                freqs[index].f = count;
                index ++;
            }
        }

        mergesort(freqs, 0, index-1 );

        for ( int x = 0 ; x < index ; x++)
            System.out.println(freqs[x].docID + "\t\t" + freqs[x].f);
    }

    public static void mergesort ( freq [] A , int l , int r ) {
        if (l >= r)
            return;
        int m = (l + r) / 2;
        mergesort(A, l, m);
        mergesort(A, m + 1, r);
        merge(A, l, m, r);
    }

    private static void merge ( freq [] A , int l , int m , int r )
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
