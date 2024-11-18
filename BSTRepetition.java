
package com.mycompany.mavenproject8;

public class BSTRepetition {


    BSTtree <Integer, BSTtree<String,RepetitionW>> BSTrank;
    freq [] freqs = new freq[50];

    public BSTRepetition() {
        BSTrank = new BSTtree <Integer, BSTtree <String,RepetitionW>>();

    }

    public boolean ADDnew (int Documentid, String Word)
    {
        if (BSTrank.empty())
        {
            BSTtree <String,RepetitionW> miniRepetitionW= new BSTtree <String,RepetitionW>();
            miniRepetitionW.insert(Word, new RepetitionW (Word,1));

            BSTrank.insert(Documentid, miniRepetitionW);
            return true;
        }
        else
        {
            if (BSTrank.find(Documentid))
            {
                BSTtree <String,RepetitionW> miniRank= BSTrank.retrieve();
                if (miniRank.find(Word))
                {
                    RepetitionW rank = miniRank.retrieve();
                    rank.addCount();
                    miniRank.update(rank);
                    BSTrank.update(miniRank);
                    return false;
                }
                miniRank.insert(Word, new RepetitionW (Word , 1));
                BSTrank.update(miniRank);
                return true;
            }
            BSTtree <String,RepetitionW> miniRank= new BSTtree <String,RepetitionW>();
            miniRank.insert(Word, new RepetitionW (Word,1));

            BSTrank.insert(Documentid, miniRank);
            return true;
        }
    }

    public boolean FOUND (int docID, String word)
    {
        if (BSTrank.find(docID) )
            if (BSTrank.retrieve().find(word))
                return true;
        return false;
    }

    public int getRepetition (int docID, String word)
    {

        if (BSTrank.find(docID) )
            if (BSTrank.retrieve().find(word))
                return BSTrank.retrieve().retrieve().getCount();
        return 0;

    }
    public void DOCprint()
    {
        BSTrank.loopT();
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
