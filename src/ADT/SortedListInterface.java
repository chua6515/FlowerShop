/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package ADT;

import java.util.Iterator;
import util.OrderByClause;
import util.WhereByClause;
import util.FirstOrDefaultClause;

/**
 *
 * @author QinYing
 */
public interface SortedListInterface<T> {
    
    public boolean add(T newEntry); //add

    public boolean remove(T anEntry); //remove

    public T getEntry(int givenPosition); //retrive

    public boolean remove(int givenPosition);
    
    public boolean contains(T anEntry); 

    public void clear(); //clear data
    
    public boolean set(int index, T newEntry);
    
    public T get(int index);
    
    public int indexOf(T newEntry);

    public int getLength();

    public boolean isEmpty();

    public boolean isFull();
    
    SortedList where(WhereByClause<T> condition);
    void orderBy(OrderByClause<T> condition);
    T firstOrDefault(FirstOrDefaultClause<T> condition);
    Iterator<T> getIterator();
}
