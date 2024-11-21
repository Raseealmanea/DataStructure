package com.mycompany.mavenproject8;

import java.security.Key;

public class AVL <K extends Comparable<K>,T> {
    private AVLnode<K, T> root;
    private AVLnode<K, T> current;
    private int count;

    class AVLnode<K extends Comparable<K>, T> {
        public K Key;
        public T data;
        AVLnode<K, T> parent;
        AVLnode<K, T> left;
        AVLnode<K, T> right;
        int BalanceF;

        public AVLnode() {
            this.Key = null;
            this.data = null;
            this.parent = null;
            this.left = null;
            this.right = null;
            this.BalanceF = 0;
        }

        public AVLnode(K Key, T Data) {
            this.Key = Key;
            this.data = Data;
            this.parent = null;
            this.left = null;
            this.right = null;
            this.BalanceF = 0;
        }

        public AVLnode(K Key, T data, AVLnode<K, T> m, AVLnode<K, T> n, AVLnode<K, T> p) {
            this.Key = Key;
            this.data = data;
            parent = m;
            left = n;
            right = p;
            BalanceF = 0;
        }

        public T getData() {
            return data;
        }

        public AVLnode<K, T> getRight() {
            return right;
        }

        public AVLnode<K, T> getLeft() {
            return left;
        }

        public String toString() {
            return "AVL Node {" + "Key=" + Key + ", Data =" + data + "}";
        }
    }


    public AVL() {
        root = null;
        current = null;
        count = 0;

    }

    public boolean empty() {
        return root == null;
    }

    public int size() {
        return count;
    }

    public void clear() {
        root = null;
        current = null;
        count = 0;
    }

    public T retrieve() {
        T data = null;
        if (current != null)
            data = current.data;
        return data;
    }

    public void update(T c) {
        if (current != null)
            current.data = c;
    }

    private T search(AVLnode<K, T> node, K Key) {
        if (node == null)
            return null;
        else if (node.Key.compareTo(Key) == 0) {
            current = node;
            return node.data;
        } else if (node.Key.compareTo(Key) > 0)
            return search(node.left, Key);

        return search(node.right, Key);

    }

    private void updateB(AVLnode<K, T> node) {
        if (node.BalanceF < -1 || node.BalanceF > 1) {
            balance(node);
            return;
        }
        if (node.parent != null) {
            if (node == node.parent.left) {
                node.parent.BalanceF -= 1;
            }
            if (node == node.parent.right) {
                node.parent.BalanceF += 1;
            }
            if (node.parent.BalanceF != 0) {
                updateB(node.parent);
            }

        }
    }

    void balance(AVLnode<K, T> node) {
        if (node.BalanceF > 0) {
            if (node.right.BalanceF < 0) {
                rotateR(node.right);
                rotateL(node);
            } else {
                rotateL(node);
            }
        }else
        if (node.BalanceF < 0) {
            if (node.left.BalanceF > 0) {
                rotateL(node.left);
                rotateR(node);
            } else {
                rotateR(node);
            }
        }
    }

    public boolean find(K Key) {
        T data = search(this.root, Key);
        if (data != null)
            return true;
        return false;
    }

    void rotateR(AVLnode<K, T> y) {
        AVLnode<K, T> w = y.left;
        y.left = w.right;
        if (w.right != null) {
            w.right.parent = y;
        }
        w.parent = y.parent;
        if (y.parent == null) {
            this.root = w;
        } else if (y == y.parent.right) {
            y.parent.right = w;
        } else {
            y.parent.left = w;
        }
        w.right = y;
        y.parent = w;

        y.BalanceF = y.BalanceF + 1 - Math.min(0, w.BalanceF);
        w.BalanceF = w.BalanceF + 1 + Math.max(0, y.BalanceF);
    }

    void rotateL(AVLnode<K, T> y) {
        AVLnode<K, T> w = y.right;
        y.right = w.left;
        if (w.left != null) {
            w.left.parent = y;
        }
        w.parent = y.parent;
        if (y.parent == null) {
            this.root = w;
        } else if (y == y.parent.left) {
            y.parent.left = w;
        } else {
            y.parent.right = w;
        }
        w.left = y;
        y.parent = w;

        y.BalanceF = y.BalanceF - 1 - Math.max(0, w.BalanceF);
        w.BalanceF = w.BalanceF - 1 + Math.min(0, y.BalanceF);
    }

    public boolean insert(K Key, T data) {
        AVLnode<K, T> node = new AVLnode<K, T>(Key, data);
        AVLnode<K, T> p = null;
        AVLnode<K, T> curr = this.root;

        while (curr != null) {
            p = curr;
            if (node.Key.compareTo(curr.Key) == 0) {
                return false;
            } else if (node.Key.compareTo(curr.Key) < 0) {
                curr = curr.left;
            } else {
                curr = curr.right;
            }
        }
        node.parent = p;
        if (p == null) {
            root = node;
            current = node;
        } else if (node.Key.compareTo(p.Key) < 0) {
            p.left = node;
        } else {
            p.right = node;
        }
        count++;
        updateB(node);
        return true;
    }

    public boolean remove(K key) {
        K l = key;
        AVLnode<K, T> p = root;
        AVLnode<K, T> q = null;

        while (p != null) {
            if (l.compareTo(p.Key) < 0) {
                q = p;
                p = p.left;
            } else if (l.compareTo(p.Key) > 0) {
                q = p;
                p = p.right;
            }
            else{
                if((p.left!=null)&&(p.right!=null))
                {
                    AVLnode<K, T> min =p.right;
                    q=p;
                    while (min.left!=null){
                        q=min;
                        min=min.left;
                    }
                    p.Key= min.Key;
                    p.data=min.data;
                    l= min.Key;
                    p=min;
                }
                if(p.left!=null)
                {
                    p=p.left;
                }
                else {
                    p=p.right;
                }
                if(q==null){
                    root=p;
                    this.updateB(p);
                }
                else
                {
                    if(l.compareTo(q.Key)<0)
                        q.left=p;
                    else
                        q.right=p;
                    this.updateB(q);
                }
                count--;
                current=root;
                return true;
            }
        }
        return false;
    }
    public void loop(){
        if(root!=null)
            loopTree(root);
    }
    public void loopTree(AVLnode<K,T> node){
    if(node==null)
        return;
        loopTree(node.left);
        System.out.println(node.data);
        loopTree(node.right);
    }

    public void loopT(){
        if(root!=null)
            loopTreeT(root);
    }
    public void loopTreeT(AVLnode<K,T> node){
        if(node==null)
            return;
        loopTreeT(node.left);
        if(node.getData() instanceof AVL){
            System.out.println("Node Key="+node.Key);
            ((AVL<String,RepetitionW>)node.getData()).loop();
        }else
        System.out.println(node.data);
        loopTree(node.right);
    }

}








