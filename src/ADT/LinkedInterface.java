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
public interface LinkedInterface<T> {
    public void add(T element); //Insert element at the end of the list.
    public boolean remove(int position); //Remove the element at a specific position from the list.
    public boolean remove(T element);//Remove the element from the list which match to the element.
    public boolean isEmpty();// To check whether the List is empty or not.
    public T get(int position); //Retrieve the element at position in the list, without removing it.
    public void clear(); //Remove all the elements from the list so that the list now empty.
    public int getSize(); //Get the size of the list.
    public boolean contains(T element); //To check whether the list cointains the element or not.
}
