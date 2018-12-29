/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package util;

/**
 *
 * @author QinYing
 */
public interface OrderByClause<T> {
    public static final int MOVE_TO_FRONT = -1;
    public static final int MOVE_TO_BACK = 1;
    int compare(T t1, T t2);
}
