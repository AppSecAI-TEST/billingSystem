package ifox.hh.dao.impl;

import ifox.hh.dao.BaseDAO;
import ifox.hh.db.JDBCUtils;
import ifox.hh.entity.Record;
import ifox.hh.vo.Page;

import java.sql.*;
import java.util.List;

/**
 * Created by 41988 on 2017/8/11.
 */
public class RecordBillingSystemDAOImpl implements BaseDAO<Record> {

    @Override
    public boolean save(Record entity) throws SQLException {
        String insertRecordSql = "insert into record(BEGINTIME,RECORDID)values(?,?)";
        Connection connection = JDBCUtils.getConnection();
        PreparedStatement insertRecordPs = connection.prepareStatement(insertRecordSql);
        insertRecordPs.setString(1, entity.getBeginTime());
        insertRecordPs.setInt(2, entity.getRecordId());
        try {
            if (insertRecordPs.executeUpdate() > 0) {
                connection.commit();
                return true;
            }
                return false;
        } catch (Exception e) {
            e.printStackTrace();
            connection.rollback();
        }finally {
            JDBCUtils.close(insertRecordPs);
        }
        return false;
    }

    @Override
    public boolean delete(int id) throws SQLException {
        return false;
    }

    @Override
    public boolean update(Object...args) throws SQLException {
        String endRecordSql = "UPDATE record set ENDTIME = ?,COSTMONEY = ? WHERE RECORDID = ? AND ENDTIME='-1'";
        Connection connection = JDBCUtils.getConnection();
        PreparedStatement endRecordPs = connection.prepareStatement(endRecordSql);
        endRecordPs.setObject(1, args[2]);
        endRecordPs.setObject(2, args[1]);
        endRecordPs.setObject(3, args[0]);
        try{
            if (endRecordPs.executeUpdate() > 0) {
                connection.commit();
                return true;
            }
                return false;
        }catch (Exception e){
            e.printStackTrace();
            connection.rollback();
        }finally {
            JDBCUtils.close(endRecordPs);
        }
        return false;
    }

    @Override
    public Record search(int id) throws SQLException {
        Record record = new Record();
        String sql = "SELECT BEGINTIME FROM record WHERE RECORDID = ? AND ENDTIME = '-1'";
        Connection connection = JDBCUtils.getConnection();
        connection.setAutoCommit(true);
        PreparedStatement searchBeginTimePs = connection.prepareStatement(sql);
        searchBeginTimePs.setInt(1, id);
        ResultSet resultSet = searchBeginTimePs.executeQuery();
        if (resultSet.next()) {
            record.setBeginTime(resultSet.getString(1));
        }
        JDBCUtils.close(resultSet,searchBeginTimePs);
        return record;
    }

    @Override
    public List<Record> searchAllBySpilt(int pageNum) throws SQLException {
        return null;
    }

    @Override
    public Page<Record> getPage(int pageNum) throws SQLException {
        return null;
    }

    @Override
    public long getTotalItemNum() throws SQLException {
        return 0;
    }
}
