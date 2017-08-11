package ifox.hh.service;

import ifox.hh.dao.impl.CardBillingSystemDAOImpl;
import ifox.hh.dao.impl.ComputerBillingSystemDAOImpl;
import ifox.hh.dao.impl.RecordBillingSystemDAOImpl;
import ifox.hh.entity.Card;
import ifox.hh.entity.Computer;
import ifox.hh.entity.Record;
import ifox.hh.vo.Page;
import org.junit.Test;

import java.sql.SQLException;
import java.util.Date;

/**
 * Created by 41988 on 2017/8/9.
 */
public class ServiceOfBillingSystem {
    private CardBillingSystemDAOImpl cardBillingSystemDAO = new CardBillingSystemDAOImpl();
    private ComputerBillingSystemDAOImpl computerBillingSystemDAO = new ComputerBillingSystemDAOImpl();
    public RecordBillingSystemDAOImpl recordBillingSystemDAO = new RecordBillingSystemDAOImpl();
    public boolean save(Card card) throws SQLException {
        return cardBillingSystemDAO.save(card);
    }

    public boolean recharge(int cid, Double moneyOfRecharge) throws SQLException {
        return cardBillingSystemDAO.recharge(cid,moneyOfRecharge);
    }

    public Page<Computer> searchComputerInfo(int pageNum) throws SQLException {
        return computerBillingSystemDAO.getPage(pageNum);
    }

    public boolean addComputer(Computer computer) throws SQLException {
        return computerBillingSystemDAO.save(computer);
    }

    public boolean deleteComputer(int id) throws SQLException {
        return computerBillingSystemDAO.delete(id);
    }

    public boolean changeStatus(int id ,String status) throws SQLException {
        return computerBillingSystemDAO.update(id,status);
    }

    public String checkCardStatusAndBalance(int cardID) throws SQLException {
        return cardBillingSystemDAO.checkCardStatusAndBalance(cardID);
    }

    public boolean confirmOperate(int cardId, int computerId, String status) throws SQLException {

        Record record = new Record();
        record.setRecordId(cardId);
        record.setBeginTime(String.valueOf(new Date().getTime()));
            if (cardBillingSystemDAO.update(cardId, status)&&recordBillingSystemDAO
                    .save(record)&&computerBillingSystemDAO
                    .update(computerId, status, cardBillingSystemDAO.search(cardId).getName())) {
                return true;
            }
        return false;
    }

    public boolean endOperate(int cardId, int computerId,String status) throws SQLException {
            if (cardBillingSystemDAO.update(cardId, status)&&computerBillingSystemDAO.update(computerId, status, "无人上网")) {
                return true;
            }
            return false;
    }
}
