package com.mycompany.mavenproject8;
import java.io.File;
import java.util.Scanner;
public class Search {
        int tokens = 0;
        int vocab = 0;

        pointer Pointer;
        InvertedIndex invertedindex;
        BST_InvertedIndex indexBST;
        AVL_InvertedIndex  indexAVL;

        IndexRanked Rindex;
        InvertedIndex_Ranked  Rinvertedindex;
        BSTRepetition  bstrepetition;
        AVLRepetition avlrepetition;

        public Search ()
        {
            Pointer = new pointer();
            invertedindex = new InvertedIndex();
            indexBST= new BST_InvertedIndex();
            indexAVL = new AVL_InvertedIndex();

            Rindex = new IndexRanked();
            Rinvertedindex = new InvertedIndex_Ranked();
            bstrepetition = new BSTRepetition();
            avlrepetition = new AVLRepetition();
        }

        public void load (String Stopf, String name)
        {
            try{
                File stopfile = new File (Stopf);
                Scanner reader = new Scanner (Stopf).useDelimiter("\\Z");
                String stops = reader.next();
                stops = stops.replaceAll("\n", " ");

                File doc = new File(name);
                Scanner reade = new Scanner (doc);
                String line = reade.nextLine();

                for ( int lineID = 0 ; lineID <50 ; lineID ++ ) {
                    line = reade.nextLine().toLowerCase();

                    int pos = line.indexOf(',');
                    int docID = Integer.parseInt(line.substring(0, pos));

                    String d = line.substring(pos + 1, line.length() - pos).trim();
                    d = d.substring(0, d.length() - 1);

                    d = d.toLowerCase();
                    d = d.replaceAll("[\']", "");
                    d = d.replaceAll("[^a-zA-Z0-9]", " ").trim();

                    String[] words = d.split(" ");

                    for (int i = 0; i < words.length; i++) {
                        String word = words[i].trim();

                        if (word.compareToIgnoreCase("") != 0)
                            tokens++;

                        if (!stops.contains(word + " "))
                        {
                                this.Pointer.addFile(docID, word);
                                this.indexBST.added(docID, word);
                                this.invertedindex.ADD(docID, word);
                                this.indexAVL.added(docID, word);
                                this.Rindex.addFile(docID,word);
                                this.Rinvertedindex.ADD(docID,word);
                                this.bstrepetition.ADDnew(docID, word);
                                this.avlrepetition.ADDnew(docID, word);

                        }
                    }

                }
               vocab = indexAVL.AVLInverIndex.size();
                System.out.println("tokens " + tokens);
                System.out.println("vocabs " + vocab);


                reader.close();
                reader.close();
            }
            catch (Exception ex) {
                System.out.println(ex.getMessage());
            }
        }


    public void Ranked_Retrieval(String str,int choice1){
        switch (choice1)
        {
            case 1:
                System.out.println("get ranked from index list");
                RIndex(str);
                break;
            case 2:
                System.out.println("get ranked from inverted index list");
                RInvertedIndex(str);
                break;
            case 3:
                System.out.println("get ranked from BST");
                RepetitionRetrievalBST(str);
                break;
            case 4:
                System.out.println("get ranked from AVL");
                RepetitionRetrievalAVL(str);
                break;
        }
    }

    public void RIndex(String str) {this.Rindex.FN(str);}
    public void RInvertedIndex(String str) {this.Rinvertedindex.FN(str);}
    public void RepetitionRetrievalBST(String str) {bstrepetition.FN(str);}
    public void RepetitionRetrievalAVL(String str) {avlrepetition.FN(str);}


    public boolean []  Boolean_Retrieval(String str , int DSType)
        {
            boolean [] docs = new boolean [50] ;
            for ( int i = 0 ; i < docs.length ; i++)
                docs[i] = false;

            switch (DSType)
            {
                case 1 :
                    System.out.println(" Boolean_Retrieval using index list");
                    docs = this.Pointer.AO_Function(str);
                    break;
                case 2 :
                    System.out.println(" Boolean_Retrieval using inverted index list");
                    docs = this.invertedindex.AO_Function(str);
                    break;

                case 3:
                    System.out.println(" Boolean_Retrieval using BST");
                    docs = this.indexBST.AO_Function(str);
                    break;

                case 4:
                    System.out.println(" Boolean_Retrieval using AVL");
                    docs = this.indexAVL.AO_Function(str);
                    break;
                default :
                    System.out.println("Not corret input");

            }
            return docs;
        }

    public boolean []  Term_Retrieval(String str , int DSType)
    {
        boolean [] docs = new boolean [50] ;
        for ( int i = 0 ; i < docs.length ; i++)
            docs[i] = false;

        switch (DSType)
        {
            case 1 :
                docs = Pointer.getDocs(str);
                break;
            case 2 :
                if (invertedindex.found(str))
                    docs = invertedindex.invertedindex.retrieve().getAllDoc();
                break;
            case 3:
                if (indexBST.FOUND(str))
                    docs = indexBST.invertedindexBST.retrieve().getAllDoc();
                break;
            case 4:
                if (indexAVL.FOUND(str))
                    docs = indexAVL.AVLInverIndex.retrieve().getAllDoc();
                break;
            default :
                System.out.println("Bad data structure");
        }
        return docs;
    }

    public void Indexed_Documents()
    {
        System.out.println("All Documents with the number of words in them ");
        for ( int i = 0 ; i < 50 ; i++ )
        {
            int size = Pointer.pointers[i].pointer.size();
            System.out.println("Document# " + i +"  with size(" +  size + ")"  );
        }

    }

    public void Indexed_Tokens()
    {
        System.out.println("All tokens with the documents appear in it ");
        indexBST.invertedindexBST.Print();
        indexAVL.AVLInverIndex.Print();
    }



}