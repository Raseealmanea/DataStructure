package com.mycompany.mavenproject8;

public class BSTtree  <K extends Comparable<K>, T>{
    class BSTNode<K extends Comparable<K>, T> {
        public K Key;
        public T value;
        public BSTNode<K,T> left, right;

        public BSTNode(K k, T val) {
            Key = k;
            value = val;
            left = right = null;
        }


    }

    BSTNode<K, T> root;
    BSTNode<K, T > current;
    int count ;

    public BSTtree()
    {
        root = current = null;
        count = 0;
    }

    public int size()
    {
        return count;
    }

    public boolean empty()
    {
        return root == null;
    }

    public void clear()
    {
        root = current = null;
        count = 0;
    }

    public T retrieve()
    {
        T data =null;
        if (current != null)
            data = current.value;
        return data;
    }

    public void update(T e)
    {
        if (current != null)
            current.value = e;
    }


    public boolean find(K key)
    {
        BSTNode<K,T> p = root;

        if(empty())
            return false;

        while(p != null) {
            if(p.Key.compareTo(key) == 0) {
                current = p;
                return true;
            }
            else if(key.compareTo(p.Key) < 0)
                p = p.left;
            else
                p = p.right;
        }
        return false;
    }


    public boolean insert(K key, T data)
    {

        if(empty())
        {
            current = root = new BSTNode <K, T> ( key, data);
            count ++;
            return true;
        }
        BSTNode<K,T> par = null;
        BSTNode<K,T> child  = root;

        while(child != null) {
            if(child.Key.compareTo(key) == 0) {
                return false;
            }
            else if(key.compareTo(child.Key) < 0)
            {
                par = child;
                child = child.left;
            }
            else
            {
                par = child;
                child = child.right;
            }
        }

        if(key.compareTo(par.Key) < 0)
        {
            par.left = new BSTNode <K, T> ( key, data);
            current = par.left;
        }

        else
        {
            par.right = new BSTNode <K, T> ( key, data);
            current = par.right;
        }
        count ++;
        return true;
    }


    public boolean remove(K key)
    {    //////Check it

        //Boolean removed = new Boolean(false);
        boolean removed=false;

        BSTNode<K,T> p;

        p = remove_aux(key, root, removed);
        root = p;

        if (current.Key.compareTo(key) == 0)
            current = root;
        if (removed)
            count -- ;

        return removed;
    }

    private BSTNode<K,T> remove_aux(K key, BSTNode<K,T> p, boolean flag)
    {
        BSTNode<K,T> q, child = null;
        if(p == null)
            return null;
        if(key.compareTo(p.Key ) < 0)
            p.left = remove_aux(key, p.left, flag); //go left
        else if(key.compareTo(p.Key) > 0)
            p.right = remove_aux(key, p.right, flag); //go right
        else {

            flag = true;
            if (p.left != null && p.right != null)
            { //two children
                q = find_min(p.right);
                p.Key = q.Key;
                p.value = q.value;
                p.right = remove_aux(q.Key, p.right, flag);
            }
            else
            {
                if (p.right == null)
                    child = p.left;
                else if (p.left == null)
                    child = p.right;
                return child;
            }
        }
        return p;
    }
    private BSTNode<K,T> find_min(BSTNode<K,T> p)
    {
        if(p == null)
            return null;

        while(p.left != null){
            p = p.left;
        }
        return p;
    }

    public void loopT()
    {
        if (root != null)
            loopTreeT(root);
    }

    private void loopTreeT (BSTNode<K,T> node  )
    {
        if (node == null)
            return;
        loopTreeT( node.left);
        System.out.println(node.value);
        loopTreeT( node.right);

    }

    public void Print()
    {
        if (root != null)
            print(root);
    }

    private void print (BSTNode<K,T> node)
    {
        if (node == null)
            return;
        print( node.left );

        System.out.print(node.Key);
        if (node.value instanceof WordCount )
        {
            System.out.print("   docs: ");
            boolean [] docs = ((WordCount) node.value).getAllDoc();
            for ( int i  = 0 ; i < 50 ; i++)
                if ( docs[i])
                    System.out.print( " " + i + " " );
            System.out.println("");
        }
        print( node.right);
    }
}
