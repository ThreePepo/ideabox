package com.taobao.ideabox.dao;

import java.util.List;

/**
 * User: shufj
 * Date: 11/29/12 7:54 обнГ
 */
public interface BaseDAO<BaseDO> {

    boolean insert(BaseDO baseDO);

    boolean update(BaseDO baseDO);

    boolean delete(BaseDO baseDO);

    List<BaseDO> Query(String where, int page, int size, String order);

}
