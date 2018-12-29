/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package ADT;

import java.util.Iterator;
import util.FirstOrDefaultClause;
import util.OrderByClause;
import util.WhereByClause;

/**
 *
 * @author QinYing
 * @param <T>
 */
public class SortedList<T> implements SortedListInterface<T> {
    
    private Node firstNode;
    private Node lastNode;
    private int length;
    private static final int ELEMENT_NOT_FOUND = -1;

    @Override
    public boolean add(T newEntry) {  //add newEntry to the end of the linked list with the position this is linked
        if (newEntry != null) {
            Node newNode = new Node(newEntry);
            if (isEmpty()) {
                firstNode = newNode;
                lastNode = newNode;
            } else {
                newNode.prev = lastNode;
                lastNode.next = newNode;
                lastNode = newNode;
            }
            length++;
        }
        return false;
    }
    
    @Override
    public boolean remove(T newEntry) { //remove the given element from this list
        if (newEntry == null || isEmpty()) {
            return false;
        } else {
            Node currentNode = travelTo(newEntry);
            if (currentNode != null) {
                remove(currentNode);
                return true;
            }
            return false;
        }
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
    public boolean remove(int givenPosition) { //remove the element at the specified index in this list
        if (isEmpty() || !inRange(givenPosition)) {
            return false;
        } else {
            remove(travelTo(givenPosition));
            return true;
        }
    }
    
    @Override
    public boolean contains(T newEntry) { //whether list contains the given newEntry
        if (newEntry != null) {
            return travelTo(newEntry) != null;
        }
        return false;
    }
    
    @Override
    public void clear() { //remove all the entries in the list
        firstNode = lastNode = null;
        length = 0;
    }
    
    @Override
    public boolean set(int index, T newEntry) { // replace the newEntry at the specified index in this list with the given newEntry
        if (isEmpty() || !inRange(index) || newEntry == null) {
            return false;
        } else {
            Node currentNode = travelTo(index);
            currentNode.data = newEntry;
            return true;
        }
    }
    
    @Override
    public T get(int index) { //newEntry located at the given index in this list
        T data = null;
        if (inRange(index)) {
            Node currentNode = travelTo(index);
            data = currentNode.data;
        }
        return data;
    }
    
    @Override
    public int indexOf(T newEntry) { //index of the given newEntry in this list
        if (newEntry != null) {
            int index = 0;
            for (Node currentNode = firstNode; currentNode != null && inRange(index); index++, currentNode = currentNode.next) {
                if (currentNode.data.equals(newEntry)) {
                    return index;
                }
            }
        }
        return ELEMENT_NOT_FOUND;
    }
    
    @Override
    public int getLength() { //get the number of entries in the list
        return length;
    }
    
    @Override
    public boolean isEmpty() { //determine whether the list is empty
        return length == 0;
    }
    
    @Override
    public boolean isFull() { //determine whether the list is full
        return false;
    }
    
    
    
    @Override
    public void orderBy(OrderByClause<T> condition) { //arrangment data in the list
        int endIndex = length - 1;
        //If each Sort pass has changes (return true), reduce endIndex by 1 and continue until sorting return false (no changes)
        while (Sort(endIndex--, condition)) {
        }
    }
    
    private boolean Sort(int endIndex, OrderByClause<T> condition) { //
        int startIndex = 0;
        boolean hasChanges = false;
        for (Node currentNode = firstNode; startIndex < endIndex; startIndex++, currentNode = currentNode.next) {
            if (condition.compare(currentNode.data, currentNode.next.data) == OrderByClause.MOVE_TO_BACK) {
                T temp = currentNode.data;
                currentNode.data = currentNode.next.data;
                currentNode.next.data = temp;
                hasChanges = true;
            }
        }
        return hasChanges;
    }
    
    @Override
    public T firstOrDefault(FirstOrDefaultClause<T> condition) { //return the first occurrence of data in this list which matched with the given condition
        T data = null;
        boolean found = false;
        for (Node currentNode = firstNode; currentNode != null && !found; currentNode = currentNode.next) {
            if (condition.match(currentNode.data)) {
                data = currentNode.data;
                found = true;
            }
        }
        return data;
    }
    
    @Override
    public Iterator<T> getIterator() { //return a iterator which act as cursor or pointer that point to the first data in list
        return new SortedListIterator();
    }
    
    private void remove(Node currentNode) { //remove the given node from this list based on 4 condition
        if (currentNode == firstNode && currentNode == lastNode) { //given node is the only node
            firstNode = null;
            lastNode = null;
        } else if (currentNode == firstNode) { //given node is first node
            firstNode.next.prev = null;
            firstNode = firstNode.next;
        } else if (currentNode == lastNode) { //given node is last node
            lastNode.prev.next = null;
            lastNode = lastNode.prev;
        } else { //given node is at the middle of this list
            currentNode.prev.next = currentNode.next;
            currentNode.next.prev = currentNode.prev;
        }
        length--;
    }
    
    private boolean isElementsValid(T... newEntry) { //if all given newElements are not null
        boolean valid = true;
        for (int i = 0; i < newEntry.length && valid; i++) {
            if (newEntry[i] == null) {
                valid = false;
            }
        }
        return valid;
    }
    
    private Node travelTo(T element) { //node which contains the given data else null
        Node currentNode = firstNode;
        boolean reach = false;
        while (currentNode != null && !reach) {
            if (currentNode.data.equals(element)) {
                reach = true;
            } else {
                currentNode = currentNode.next;
            }
        }
        return currentNode;
    }
    
    private Node travelTo(int dest) { //node which located at the given index(destination) in this list
        int half = length / 2; 
        return dest < half ? travelFromFirstTo(dest) : travelFromLastTo(dest);
    }
    
    private Node travelFromLastTo(int dest) { //start traveling to the given index(destionation) from last node
        int start = length - 1;
        Node currentNode = lastNode;
        while (start != dest) {
            currentNode = currentNode.prev;
            start--;
        }
        return currentNode;
    }
    
    private Node travelFromFirstTo(int dest) { //start traveling to the given index(destination) from first node
        int start = 0;
        Node currentNode = firstNode;
        while (start != dest) {
            currentNode = currentNode.next;
            start++;
        }
        return currentNode;
    }
    
    private boolean inAddRange(int index) { //if given index is valid position for adding new data
        return index >= 0 && index <= length;
    }
    
    private boolean inRange(int index) { //if given index is between 0 and size-1 of this list, else false
        return index >= 0 && index < length;
    }
    
    @Override
    public String toString() {
        String str = "";
        for (Node currentNode = firstNode; currentNode != null; currentNode = currentNode.next) {
            str += currentNode.data + "\n";
        }
        return str;
    }

    @Override
    public SortedList where(WhereByClause<T> condition) {
        SortedList<T> list = new SortedList<>();
        for (Node currentNode = firstNode; currentNode != null; currentNode = currentNode.next) {
            if (condition.match(currentNode.data)) {
                list.add(currentNode.data);
            }
        }
        return list;
    }
    
    private class Node {

        private T data;
        private Node next;
        private Node prev;

        private Node(T data) {
            this.data = data;
        }
    }

    private class SortedListIterator implements Iterator<T> {

        Node currentNode = firstNode;

        @Override
        public boolean hasNext() {
            return currentNode != null;
        }

        @Override
        public T next() {
            T data = null;
            if (hasNext()) {
                data = currentNode.data;
                currentNode = currentNode.next;
            }
            return data;
        }

    }
}
