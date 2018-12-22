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
public interface PriorityQueueInterface<T> {
    
    public void enqueue(T newEntry);
    public T getFront();
    public T dequeue();
    
    public int getNumberOfEntries();
    public boolean isEmpty();
    public void clear();
}
