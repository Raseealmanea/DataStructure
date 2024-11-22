package com.mycompany.mavenproject8;

public class pointer {

    class File {
        LinkedList<Vocab> pointer;
        int FileID;

        public File() {
            pointer = new LinkedList<Vocab>();
            FileID = 0;

        }

        public File(int id, String[] words) {
            pointer = new LinkedList<Vocab>();
            FileID = id;
            for (int i = 0; i < words.length; i++)
                pointer.insert(new Vocab(words[i]));
        }

        public void ADD(String word) {
            pointer.insert(new Vocab(word));
        }

        public boolean found(String word) {
            if (pointer.empty())
                return false;

            pointer.findFirst();
            for (int i = 0; i < pointer.count; i++) {
                if (pointer.retrieve().word.compareTo(word) == 0)
                    return true;
                pointer.findNext();
            }
            return false;


        }
    }

    File[] pointers;

    public pointer() {
        pointers = new File[50];
        for (int i = 0; i < pointers.length; i++) {
            pointers[i] = new File();
            pointers[i].FileID = i;
            pointers[i] = new File();

        }
    }

    public void addFile(int FileID, String data) {
        pointers[FileID].ADD(data);

    }

    public void addALLFile(int FileID, String[] data) {

        for (int i = 0; i < data.length; i++)
            pointers[FileID].ADD(data[i]);

    }

    public void printFile(int FileID) {
        if (pointers[FileID].pointer.empty())
            System.out.println("File is empty");

        else {
            pointers[FileID].pointer.findFirst();
            for (int i = 0; i < pointers[FileID].pointer.count; i++) {
                System.out.println(pointers[FileID].pointer.retrieve() + " ");
                pointers[FileID].pointer.findNext();
            }
        }
    }
    public  boolean [] getDocs (String str)
    {
        boolean [] result = new boolean [50];
        for (int i = 0 ; i < result.length ; i++)
            result[i] = false;

        for (int i = 0 ; i < result.length ; i++)
            if (pointers[i].found(str))
                result[i] = true;

        return result;
    }

    public boolean [] AO_Function (String str )
    {
        if (! str.contains(" OR ") && ! str.contains(" AND "))
        {
            str = str.toLowerCase().trim();
            boolean [] r1 = getDocs(str.toLowerCase().trim());
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
        boolean [] b1 = getDocs(ANDs[0].toLowerCase().trim());

        for ( int i = 1 ; i< ANDs.length ; i++)
        {
            boolean [] b2 = getDocs(ANDs[i].toLowerCase().trim());
            for ( int j = 0 ; j < 50 ; j++)
                b1 [j] = b1[j] && b2[j];
        }
        return b1;
    }

    public boolean [] OR (String str)
    {
        String [] ORs = str.split(" OR ");
        boolean [] b1 = getDocs(ORs[0].toLowerCase().trim());

        for ( int i = 1 ; i< ORs.length ; i++)
        {
            boolean [] b2 = getDocs(ORs[i].toLowerCase().trim());
            for ( int j = 0 ; j < 50 ; j++)
                b1 [j] = b1[j] || b2[j];
        }
        return b1;
    }

}









