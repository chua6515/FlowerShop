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
public interface LinkedListInterface<T> {
    
    public boolean add(T newEntry);

    public boolean remove(T anEntry);

    public int getPosition(T anEntry);

    public T getEntry(int givenPosition);

    public boolean contains(T anEntry);

    public T remove(int givenPosition);

    public void clear();

    public int getLength();

    public boolean isEmpty();

    public boolean isFull();

}
