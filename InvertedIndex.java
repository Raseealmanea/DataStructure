

package com.mycompany.mavenproject8;


public class InvertedIndex {

    LinkedList <WordCount> allInfo;

    public InvertedIndex(){
        allInfo=new LinkedList<WordCount>() ;

    }


    public int size() {
        return allInfo.count;
    }

    public boolean ADD(int FileID, String word){
        if(allInfo.empty()){
            WordCount wc=new WordCount();
            Vocab v=new Vocab(word);
            wc.setVocab(v);
            wc.add_docNumber(FileID);
            allInfo.insert(wc);
            return true;
        }
        else{
            allInfo.findFirst();
            while (!allInfo.last() )
            {
                if(allInfo.retrieve().word.word.compareTo(word)==0)
                {
                    WordCount wc=allInfo.retrieve();
                    wc.add_docNumber(FileID);
                    allInfo.update(wc);
                    return false;
                }
                allInfo.findNext();
            }
            if(allInfo.retrieve().word.word.compareTo(word)==0){
                WordCount wc=allInfo.retrieve();
                wc.add_docNumber(FileID);
                allInfo.update(wc);
                return false;
            }
            else{
                WordCount wc = new WordCount();
                Vocab v=new Vocab(word);
                wc.setVocab(v);
                wc.add_docNumber(FileID);
                allInfo.insert(wc);
            }
            return true;

        }

    }
    public boolean found(String word){
        if(allInfo.empty())
            return false;
        allInfo.findFirst();
        for(int i=0; i<allInfo.count;i++)
        {
            if(allInfo.retrieve().word.word.compareTo(word)==0)
                return true;
            allInfo.findNext();
        }
        return false;
    }
    public boolean [] function(String str) {
        if (!str.contains("OR") && !str.contains("AND")) {
            boolean[] f = new boolean[50];
            str = str.toLowerCase().trim();

            if (this.found(str))
                f = this.allInfo.retrieve().getAllDoc();
            return f;
        } else if (str.contains("OR") && str.contains("AND")) {
            String[] fun = str.split("OR");
            boolean[] f = FAnd(fun[0]);

            for (int i = 1; i < fun.length; i++) {
                boolean[] f2 = FAnd(fun[i]);
                for (int n = 0; n < 50; n++)
                    f[n] = f[n] || f2[n];
            }
            return f;
        } else if (str.contains("AND"))
            return FAnd(str);
        return FOr(str);
    }

    public boolean [] FAnd(String str) {
        String[] and = str.split("AND");
        boolean[] m = new boolean[50];

        if (this.found(and[0].toLowerCase().trim()))
            m = this.allInfo.retrieve().getAllDoc();

        for (int i = 1; i < and.length; i++) {
            boolean[] m1 = new boolean[50];
            if (this.found(and[i].toLowerCase().trim()))
                m1 = this.allInfo.retrieve().getAllDoc();

            for (int n = 0; n<50; i++)
                m[n] = m[n] && m1[n];
        }
        return m;
    }
    public boolean [] FOr(String str) {
        String[] or = str.split("OR");
        boolean[] m = new boolean[50];

        if (this.found(or[0].toLowerCase().trim()))
            m = this.allInfo.retrieve().getAllDoc();

        for (int i = 1; i < or.length; i++) {
            boolean[] m1 = new boolean[50];
            if (this.found(or[i].toLowerCase().trim()))
                m1 = this.allInfo.retrieve().getAllDoc();
            for (int n = 0; n<50; i++)
                m[n] = m[n] || m1[n];
        }
        return m;
    }

    public void printFile(int FileID) {
        if (this.allInfo.empty())
            System.out.println("ALLInfo is empty");

        else {
            this.allInfo.findFirst();
            while(!this.allInfo.last()){
                System.out.println(allInfo.retrieve());
                this.allInfo.findNext();
            }
            System.out.println(allInfo.retrieve());
        }
    }
}
