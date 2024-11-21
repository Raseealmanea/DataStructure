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


}









