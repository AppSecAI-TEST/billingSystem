package ifox.hh.dao;

import ifox.hh.vo.Page;

import java.sql.SQLException;
import java.util.List;

/**
 * Created by 41988 on 2017/8/9.
 */
public interface BaseDAO<T> {
    boolean save(T entity) throws SQLException;

    boolean delete(int id) throws SQLException;

    boolean update(Object...args) throws SQLException;

    T search(int id) throws SQLException;


    List<T> searchAllBySpilt(int pageNum) throws SQLException;

    Page<T> getPage(int pageNum) throws SQLException;

    long getTotalItemNum() throws SQLException;
}
