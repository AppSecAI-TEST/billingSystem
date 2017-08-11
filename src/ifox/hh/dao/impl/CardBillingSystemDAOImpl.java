package ifox.hh.dao.impl;

import ifox.hh.dao.BaseDAO;
import ifox.hh.db.JDBCUtils;
import ifox.hh.entity.Card;
import ifox.hh.entity.Record;
import ifox.hh.vo.Page;
import org.junit.Test;

import java.sql.*;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

/**
 * Created by 41988 on 2017/8/9.
 */
public class CardBillingSystemDAOImpl implements BaseDAO<Card> {

    @Override
    public boolean save(Card entity) throws SQLException {
        String sql = "INSERT INTO card(CARDNUM,NAME,BALANCE,CARDSTATUS) VALUES (?,?,?,?)";
        Connection connection = JDBCUtils.getConnection();
        PreparedStatement preparedStatement = connection.prepareStatement(sql);
        preparedStatement.setString(1, entity.getCardNum());
        preparedStatement.setString(2, entity.getName());
        preparedStatement.setDouble(3, entity.getBalance());
        preparedStatement.setString(4, entity.getCardStatus());
        try {
            if (preparedStatement.executeUpdate() > 0) {
                connection.commit();
                return true;
            }
            return false;
        } catch (Exception e) {
            e.printStackTrace();
            connection.rollback();
        } finally {
            JDBCUtils.close(preparedStatement);
        }

        return false;
    }

    @Override
    public boolean delete(int id) {

        return false;
    }


    @Override
    public boolean update(Object... args) throws SQLException {
        String updateStatusSql = "UPDATE card set CARDSTATUS = ? WHERE cid = ?";
        Connection connection = JDBCUtils.getConnection();
        PreparedStatement updateStatusPs = connection.prepareStatement(updateStatusSql);
        updateStatusPs.setString(1, (String) args[1]);
        updateStatusPs.setInt(2, (Integer) args[0]);
        if (args[1].equals("1")) {
            return operate(updateStatusPs, connection);
        } else {
            try {
                if (updateStatusPs.executeUpdate() > 0) {
                    connection.commit();
                    return endOperate((Integer) args[0], updateStatusPs, connection);
                }
                return false;
            } catch (Exception e) {
                e.printStackTrace();
                connection.rollback();
            }
            return false;
        }

    }

    private boolean endOperate(int id, PreparedStatement updateStatusPs, Connection connection) throws SQLException {
        long beginTime = Long.parseLong(new RecordBillingSystemDAOImpl().search(id).getBeginTime());
        double costMoney = (new Date().getTime() - beginTime) / 1000 / 3600 * 3.5 + 2;
        RecordBillingSystemDAOImpl recordDAO = new RecordBillingSystemDAOImpl();
        String sql = "UPDATE card set BALANCE = ? where CID = ?";
        updateStatusPs = connection.prepareStatement(sql);
        updateStatusPs.setDouble(1, search(id).getBalance() - costMoney);
        updateStatusPs.setInt(2, id);
        try {
            if (updateStatusPs.executeUpdate() > 0) {
                connection.commit();
                recordDAO.update(id, costMoney, new Date().getTime());
                return true;
            }
            return false;
        } catch (Exception e) {
            e.printStackTrace();
            connection.rollback();
        } finally {
            JDBCUtils.close(updateStatusPs);
        }
        return false;
    }

    private boolean operate(PreparedStatement updateStatusPs, Connection connection) throws SQLException {
        try {
            if (updateStatusPs.executeUpdate() > 0) {
                connection.commit();
                return true;
            }
            return false;
        } catch (Exception e) {
            e.printStackTrace();
            connection.rollback();
        } finally {
            JDBCUtils.close(updateStatusPs);
        }
        return false;
    }// TODO: 2017/8/11 还没有修改

    @Override
    public Card search(int id) throws SQLException {
        Card cardBySearchId = new Card();
        List<Record> records = new ArrayList<>();
        String sql = "select c.CARDNUM ,c.NAME,c.BALANCE,c.CARDSTATUS,r.RID,r.BEGINTIME,r.ENDTIME,r.COSTMONEY from card c left join record r on c.cid = r.RECORDID WHERE c.cid = ?";
        Connection connection = JDBCUtils.getConnection();
        connection.setAutoCommit(true);
        PreparedStatement preparedStatement = connection.prepareStatement(sql);
        preparedStatement.setInt(1, id);
        ResultSet resultSet = preparedStatement.executeQuery();
        while (resultSet.next()) {
            if (resultSet.getString(1) != null) {
                cardBySearchId.setCardNum(resultSet.getString(1));
                cardBySearchId.setName(resultSet.getString(2));
                cardBySearchId.setBalance(resultSet.getDouble(3));
                cardBySearchId.setCardStatus(resultSet.getString(4));
            }
            if (resultSet.getString(5) != null) {
                Record record = new Record(resultSet.getInt(5), resultSet.getString(6), resultSet.getString(7), resultSet.getDouble(8));
                records.add(record);
            }
        }
        cardBySearchId.setRecords(records);
        JDBCUtils.close(resultSet, preparedStatement);
        return cardBySearchId;
    }

    @Override
    public List<Card> searchAllBySpilt(int pageNum) throws SQLException {
        return null;
    }

    @Override
    public Page<Card> getPage(int pageNum) {
        return null;
    }

    @Override
    public long getTotalItemNum() {
        return 0;
    }


    public boolean recharge(int cid, Double moneyOfRecharge) throws SQLException {
        Card rechargeCard = search(cid);
        String sql = "UPDATE card set BALANCE = ? Where cid = ?";
        Connection connection = JDBCUtils.getConnection();
        PreparedStatement preparedStatement = connection.prepareStatement(sql);
        preparedStatement.setDouble(1, moneyOfRecharge + rechargeCard.getBalance());
        preparedStatement.setInt(2, cid);
        try {
            if (preparedStatement.executeUpdate() > 0) {
                connection.commit();
                return true;
            }
            return false;
        } catch (Exception e) {
            e.printStackTrace();
            connection.rollback();
        } finally {
            JDBCUtils.close(preparedStatement);
        }
        return false;
    }

    public String checkCardStatusAndBalance(int cardID) throws SQLException {
        String checkMsg = null;
        String sql = "SELECT CARDSTATUS,BALANCE FROM card WHERE CID = ?";
        Connection connection = JDBCUtils.getConnection();
        connection.setAutoCommit(true);
        PreparedStatement preparedStatement = connection.prepareStatement(sql);
        preparedStatement.setInt(1, cardID);
        ResultSet resultSet = preparedStatement.executeQuery();
        String cardStatus = null;
        Double balance = 0.0;
        while (resultSet.next()) {
            cardStatus = resultSet.getString(1);
            balance = resultSet.getDouble(2);
        }
        if (cardStatus.equals("0")) {
            if (balance > 0) {
                checkMsg = "该卡可以上网";
            } else {
                checkMsg = "余额不足";
            }
        } else {
            checkMsg = "该卡已上网";
        }
        JDBCUtils.close(resultSet, preparedStatement);
        return checkMsg;
    }

}
