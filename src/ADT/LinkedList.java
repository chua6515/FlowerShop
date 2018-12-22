/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package ADT;

/**
 *
 * @author QinYing
 */
public class LinkedList<T extends Comparable<? super T>> implements LinkedListInterface<T> {
    
    private Node firstNode;
    private int length;

    

    public LinkedList() {
        firstNode = null;
        length = 0;
    }

    @Override
    public boolean add(T newEntry) {//add newEntry to the end of the linked list with the position this is linked
        Node newNode = new Node(newEntry);

        Node nodeBefore = null;// For linked list traversal: to reference the node before the current node
        Node currentNode = firstNode;// For linked list traversal: to reference the current node
        while (currentNode != null && newEntry.compareTo(currentNode.data) > 0) {
            nodeBefore = currentNode;
            currentNode = currentNode.next;
        }

        if (isEmpty() || (nodeBefore == null)) { // CASE 1: add at beginning
            newNode.next = firstNode;
            firstNode = newNode;
        } else {	// CASE 2: add in the middle or at the end
            newNode.next = currentNode;
            nodeBefore.next = newNode;
        }
        length++;
        return true;
    }

    @Override
    public boolean remove(T anEntry) {
        if (firstNode == null) //case1: empty list
        {
            return false;
        } else if (firstNode.data.equals(anEntry)) { //case2: remove 1st
            firstNode = firstNode.next;
            return true;
        } else {//case3,4:remove middle or last node
            Node nodeBefore = firstNode;
            Node currentNode = firstNode.next;
            while (currentNode != null && currentNode.data.compareTo(anEntry) < 0) {
                nodeBefore = currentNode;
                currentNode = currentNode.next;
            }
            if (currentNode != null && currentNode.data.equals(anEntry)) {
                nodeBefore.next = currentNode.next;
                length--;
                return true;
            }
        }
        return false;
    }

    @Override
    public int getPosition(T anEntry) {
        int position = 1;
        while ((position <= length)
                && (anEntry.compareTo(getEntry(position)) > 0)) {
            position++;
        }
        if ((position > length)
                || (anEntry.compareTo(getEntry(position)) != 0)) {
            position = -position; // anEntry is not in list
        }
        return position;
    }

    @Override
    public T getEntry(int givenPosition) {
        T result = null;

        if ((givenPosition >= 1) && (givenPosition <= length)) {
            Node currentNode = firstNode;
            for (int i = 0; i < givenPosition - 1; ++i) {
                currentNode = currentNode.next;		// advance currentNode to next node
            }
            result = currentNode.data;	// currentNode is pointing to the node at givenPosition
        }

        return result;
    }

    @Override
    public boolean contains(T anEntry) {
        boolean found = false;
        Node currentNode = firstNode;

        while (!found && (currentNode != null)) {
            if (anEntry.equals(currentNode.data)) {
                found = true;
            } else {
                currentNode = currentNode.next;
            }
        }

        return found;
    }

    @Override
    public T remove(int givenPosition) {
        T result = null;

        if ((givenPosition >= 1) && (givenPosition <= length)) {
            if (givenPosition == 1) {      	// CASE 1: remove first entry
                result = firstNode.data;     	// save entry to be removed 
                firstNode = firstNode.next;		// update firstNode to point to the next node
            } else {                         	// CASE 2: remove interior entry or last entry
                Node nodeBefore = firstNode;
                for (int i = 1; i < givenPosition - 1; ++i) {
                    nodeBefore = nodeBefore.next;		// advance nodeBefore to its next node
                }
                result = nodeBefore.next.data;  	// save entry to be removed	
                nodeBefore.next = nodeBefore.next.next;	// make node before point to node after the 
            } // one to be deleted (to disconnect node from chain)

            length--;
        }

        return result;
    }
    
    @Override
    public final void clear() {//remove all the entries in the list
        firstNode = null;
        length = 0;
    }

    @Override
    public int getLength() {//get the number of entries in the list
        return length;
    }

    @Override
    public boolean isEmpty() {//determine whether the list is empty
        return (length == 0);
    }

    @Override
    public boolean isFull() {//determine whether the list is full
        return false;
    }

    @Override
    public String toString() {
        String outputStr = "";
        Node currentNode = firstNode;
        int count = 1;
        
        while (currentNode != null) {
            outputStr += "      " + Integer.toString(count) + ". "
                    + "" + currentNode.data + "\n";;
            currentNode = currentNode.next;
            count++;
        }
        return outputStr;
    }

    private class Node {

        private T data;
        private Node next;

        private Node(T dataPortion) {
            data = dataPortion;
            next = null;
        }

        private Node(T dataPortion, Node nextNode) {
            data = dataPortion;
            next = nextNode;
        }
    }
}
