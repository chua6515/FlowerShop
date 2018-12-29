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
public interface WhereByClause<T> {
    boolean match(T entry);
}
