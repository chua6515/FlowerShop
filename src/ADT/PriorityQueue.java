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
public class PriorityQueue<T extends Comparable<? super T>> implements PriorityQueueInterface<T> {
    
    private Node firstNode = null;
    private static int numberOfEntries = 0;

    @Override
    public void enqueue(T newEntry) {//add new entry into the queue according to its priority
        Node newNode = new Node(newEntry);
        Node current = firstNode;

        // enter node when queue empty
        if (current == null) {
            current = newNode;
            newNode.next = null;
        } else {
            Node nextNode = current;
            Node previousNode = nextNode;
            boolean correctPosition = false;

            do {// loop through the queue to find the correct point of insertion
                if (newEntry.compareTo(nextNode.data) > 0) {
                    correctPosition = true;//set the correctPosition to true to exit do-while loop
                }
                if (correctPosition == false) {//shift the nodes infront if haven't found the right position
                    previousNode = nextNode;
                    nextNode = nextNode.next;
                }
            } while (nextNode != null && correctPosition == false);

            newNode.next = nextNode;
            // the edge case to see if the newNode has greatest priority
            if (newNode.data.compareTo(firstNode.data) > 0) {//if newNode > firstNode
                current = newNode;
            } else {
                previousNode.next = newNode;
            }
        }
        firstNode = current;
        numberOfEntries++;
    }

    @Override
    public T getFront() {//get the first data from the queue
        return firstNode.data;
    }

    @Override
    public T dequeue() {//remove the first entry from the queue
        T data = firstNode.data;
        firstNode = firstNode.next;
        return data;
    }

    @Override
    public boolean isEmpty() {//check the queue to see whether its empty or not
        return firstNode == null;
    }

    @Override
    public void clear() {//clear the whole queue
        firstNode = null;
    }

    public int getNumberOfEntries() {//get the total number of entries in the queue
        return numberOfEntries;
    }

    @Override
    public String toString() {
        String str = "";
        Node currentNode = firstNode;
        int count = 1;
        while (currentNode != null) {
            str += Integer.toString(count) + ". " + currentNode.data + "\n";
            currentNode = currentNode.next;
            count++;
        }
        return str;
    }

    private class Node {

        private T data;
        private Node next;

        private Node(T data, Node next) {
            this.data = data;
            this.next = next;
        }

        private Node(T data) {
            this.data = data;
        }
    }
    
}
