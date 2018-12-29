/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package ADT;

/**
 *
 * @author use
 */
public class Linked<T> implements LinkedInterface<T> {
    
    private Node firstNode;
    private int size;
    
    public Linked(){
        firstNode = null;
        size = 0;
    }
    
    public void add(T element) {
        if(isEmpty())
        {
            Node newNode = new Node(element);
            firstNode = newNode;
            size++;
        }
        else
        {
            Node newNode = new Node(element,firstNode);
            firstNode = newNode;
            size++;
        }
    }
    
    public boolean remove(int position) {
        
        if(position >=1 && position<=size){
            if(position == 1){
                firstNode = firstNode.next;
            }
            else{
                Node prevNode = firstNode;
                for(int i = 1; i < position-1; i++){
                    prevNode = prevNode.next;
                }
                prevNode.next = prevNode.next.next;                
            }
            size--;
            return true;
        }
        else return false;
    }
        
    public boolean remove(T element) {
        boolean result = false;
        
        if(firstNode.data == element){
            result =true;
            firstNode = firstNode.next;
            size--;
        }
        else{
            Node prevNode = firstNode;
            while( prevNode.next != null && prevNode.next.data != element){
                prevNode = prevNode.next;
            }
            if(prevNode.next != null){
                result =true;
                prevNode.next = prevNode.next.next;
                size--;
            }            
        }
        return result;
    }
    
    public boolean isEmpty() {
        return size == 0;
    }
    
    public T get(int position) {
        
        if(position >=1 && position<=size){
            if(position == 1){
                return (T) firstNode.data;
            }
            else{
                Node prevNode = firstNode;
                for(int i = 1; i < position-1; i++){
                    prevNode = prevNode.next;
                }
                return (T) prevNode.next.data;            
            }
        }
        else return (T) "Cannot be found.";       
    }
    
    public void clear() {
        firstNode = null;
        size = 0;
    }
    
    public int getSize(){
        return size;
    }
    
    public boolean contains(T element) {
        boolean result = false;
        
        if(firstNode.data == element){
            result =true;
        }
        else{
            Node prevNode = firstNode;
            while( prevNode.next != null && prevNode.next.data != element){
                prevNode = prevNode.next;
            }
            if(prevNode.next != null){
                result =true;
            }            
        }        
        return result;
    }

    public String toString(){
        String outPut = "";
        Node currNode = firstNode;
        int num = 0;
        while(currNode != null){
            outPut += ++num+". "+currNode.data + "\n";
            currNode = currNode.next;
        }
        return outPut;
    }
    
    private class Node<T>
    {
        T data;
        Node next;
        
        public Node(T data)
        {
            this.data = data;
            this.next = null;
        }
        public Node(T data, Node next)
        {
            this.data = data;
            this.next = next;
        }
    }
}
