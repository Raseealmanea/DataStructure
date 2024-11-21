package com.mycompany.mavenproject8;
import java.io.File;
import java.util.Scanner;
public class Search {
    int tokens = 0;
    int vocab = 0;

    pointer Pointer;
    ALLInfo invertedindex;

    BST_InvertedIndex indexBST;
    BSTRepetition  bstrepetition;


    public Search ()
    {
        Pointer = new pointer();
        invertedindex = new ALLInfo();
        indexBST= new BST_InvertedIndex();
        bstrepetition = new BSTRepetition();
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
                d = d.substring(0, d.length() - 2);

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
                        {
                            this.Pointer.addFile(docID, word);
                            this.invertedindex.ADD(docID, word);
                            this.indexBST.added(docID, word);
                            this.bstrepetition.ADDnew(docID, word);
                        }
                    }
                }

            }
            vocab = indexBST.size();

            System.out.println("tokens " + tokens);
            System.out.println("vocabs " + vocab);

            reader.close();
            reader.close();
        }
        catch (Exception ex) {
            System.out.println(ex.getMessage());
        }
    }

    public boolean []  Boolean_Retrieval(String str , int DSType)
    {
        boolean [] docs = new boolean [50] ;
        for ( int i = 0 ; i < docs.length ; i++)
            docs[i] = false;

        switch (DSType)
        {
            case 1 :
                docs = this.invertedindex.function(str);
                break;
            case 2:
                docs = this.indexBST.AO_Function(str);
                break;
            default :
                System.out.println("Bad data structure");

        }
        return docs;
    }

    public void RepetitionRetrieval(String str)
    {
        bstrepetition.FN(str);
    }


}