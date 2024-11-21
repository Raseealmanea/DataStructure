/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.mycompany.mavenproject8;

/**
 *
 * @author slymanalmny
 */

public class NodeLinkedList<T> {
    public T data;
    public NodeLinkedList<T> next;
    public NodeLinkedList () {
        data = null;
        next = null;
    }
    public NodeLinkedList (T val) {
        data = val;
        next = null;
    }

    public T getData() {
        return data;
    }

    public void setData(T data) {
        this.data = data;
    }

    public NodeLinkedList<T> getNext() {
        return next;
    }

    public void setNext(NodeLinkedList<T> next) {
        this.next = next;
    }

}