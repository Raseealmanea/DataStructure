/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.mycompany.mavenproject8;

/**
 *
 * @author slymanalmny
 */
public class LinkedList<T> {
    private NodeLinkedList<T> head;
    private NodeLinkedList<T> current;
    int count;
    
    public LinkedList () {
        head = current = null;
        count = 0 ;
    }
    public boolean empty () {
        return head == null;
    }
    public int size ()
    {
        return count;
    }
    
    public boolean last () {
        return current.next == null;
    }
    public boolean full () {
            return false;
    }
    public void findFirst () {
            current = head;
    }
    public void findNext () {
            current = current.next;
    }
    public T retrieve () {
            return current.data;
    }
    public void update (T val) {
            current.data = val;
    }
    
    public void insert (T val) {
            NodeLinkedList<T> tmp;
            if (empty()) {
                    current = head = new NodeLinkedList<T> (val);
            }
            else {
                    tmp = current.next;
                    current.next = new NodeLinkedList<T> (val);
                    current = current.next;
                    current.next = tmp;
            }
            count++ ;
    }

    public void remove () {
            if (current == head) {
                    head = head.next;
            }
            else {
                    NodeLinkedList<T> tmp = head;

                    while (tmp.next != current)
                            tmp = tmp.next;

                    tmp.next = current.next;
            }

            if (current.next == null)
                    current = head;
            else
                    current = current.next;
            count --;
    }
}
