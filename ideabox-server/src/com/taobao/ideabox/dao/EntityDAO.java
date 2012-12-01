package com.taobao.ideabox.dao;

import java.util.List;

/**
 * Created with IntelliJ IDEA.
 * User: Warren
 * Date: 12-12-1
 * Time: обнГ4:32
 */
public interface EntityDAO<T> {
    public int insert(T t);

    public int update(T t);

    public int delete(int pk);

    public List<T> query(String condition, int page, int size);

}
