/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package DAO;

import java.util.List;

/**
 *
 * @author TIEN SY
 */
public abstract class CuaHangDAO<E, K> {


    abstract public void insert(E entity);

    abstract public void update(E entity);

    abstract public void delete(K id);

    abstract public E selectbyID(K id);

    abstract public List<E> selectAll();

    abstract public List<E> selectSQL(String sql, Object... args);
}
