package ifox.hh.dao.impl;

        import ifox.hh.dao.BaseDAO;
        import ifox.hh.db.JDBCUtils;
        import ifox.hh.entity.Computer;
        import ifox.hh.vo.Page;
        import org.junit.Test;

        import java.sql.*;
        import java.util.ArrayList;
        import java.util.List;

/**
 * Created by 41988 on 2017/8/10.
 */
public class ComputerBillingSystemDAOImpl implements BaseDAO<Computer> {
    private static final int PAGESIZE = 8;

    @Override
    public boolean save(Computer entity) throws SQLException {
        String sql = "INSERT  INTO computer(COMPUTERBRAND) VALUES (?)";
        Connection connection = JDBCUtils.getConnection();
        PreparedStatement preparedStatement = connection.prepareStatement(sql);
        preparedStatement.setString(1,entity.getComputerBrand());
        try{
            if(preparedStatement.executeUpdate()>0){
                connection.commit();
                return true;
            }
            return false;
        }catch (Exception e){
            connection.rollback();
        }finally {
            JDBCUtils.close(preparedStatement);
        }
        return false;
    }

    @Override
    public boolean delete(int id) throws SQLException {
        String sql = "DELETE computer where COMPUTERID = ?";
        Connection connection = JDBCUtils.getConnection();
        PreparedStatement preparedStatement = connection.prepareStatement(sql);
        preparedStatement.setInt(1, id);
        try{
            if (preparedStatement.executeUpdate() > 0) {
                connection.commit();
                return true;
            }
                return false;
        }catch (Exception e){
            connection.rollback();
        }finally {
            JDBCUtils.close(preparedStatement);
        }
        return false;
    }


    @Override
    public boolean update(Object...args) throws SQLException {
        Connection connection = JDBCUtils.getConnection();
        String sql = "UPDATE computer SET COMPUTERSTATUS = ?,CURRENTCARDNAME = ? WHERE COMPUTERID=?";
        PreparedStatement preparedStatement = connection.prepareStatement(sql);
        preparedStatement.setObject(1,args[1]);
        preparedStatement.setObject(2,args[2]);
        preparedStatement.setObject(3,args[0]);

        try{
            if (preparedStatement.executeUpdate() > 0) {
                connection.commit();
                return true;
            }
            return false;
        }catch (Exception e){
            e.printStackTrace();
            connection.rollback();
        }finally {
            JDBCUtils.close(preparedStatement);
        }
        return false;
    }

    @Override
    public Computer search(int id) throws SQLException {
        Computer computer = new Computer();
        String sql ="SELECT COMPUTERID,COMPUTERSTATUS,CURRENTCARDNAME,COMPUTERBRAND FROM computer WHERE id=?";
        Connection connection = JDBCUtils.getConnection();
        connection.setAutoCommit(true);
        PreparedStatement preparedStatement = connection.prepareStatement(sql);
        preparedStatement.setInt(1,id);
        ResultSet resultSet = preparedStatement.executeQuery();
        while(resultSet.next()){
            computer.setComputerId(resultSet.getInt(1));
            computer.setComputerStatus(resultSet.getString(2));
            computer.setCurrentCardName(resultSet.getString(3));
            computer.setComputerBrand(resultSet.getString(4));
        }
        JDBCUtils.close(resultSet,preparedStatement);
        return computer;
    }

    @Override
    public List<Computer> searchAllBySpilt(int pageNum) throws SQLException {
        List<Computer> computers = new ArrayList<>();
        String sql = "SELECT COMPUTERID,COMPUTERSTATUS,CURRENTCARDNAME,COMPUTERBRAND FROM computer LIMIT " +(pageNum-1)*PAGESIZE+","+PAGESIZE;
        Connection connection = JDBCUtils.getConnection();
        connection.setAutoCommit(true);
        PreparedStatement preparedStatement = connection.prepareStatement(sql);
        ResultSet resultSet = preparedStatement.executeQuery();
        while (resultSet.next()) {
            Computer computer = new Computer(resultSet.getInt(1), resultSet.getString(2), resultSet.getString(3), resultSet.getString(4));
            computers.add(computer);
        }
        JDBCUtils.close(resultSet,preparedStatement);
        return computers;
    }

    @Override
    public Page<Computer> getPage(int pageNum) throws SQLException {
        Page<Computer> computerPage = new Page<>(pageNum);
        computerPage.setTotalItemNumber(getTotalItemNum());
        computerPage.setPageNum(computerPage.getPageNum());
        computerPage.setList(searchAllBySpilt(pageNum));
        return computerPage;
    }

    @Override
    public long getTotalItemNum() throws SQLException {
        String sql = "select count(*) FROM computer";
        Connection connection = JDBCUtils.getConnection();
        connection.setAutoCommit(true);
        PreparedStatement preparedStatement = connection.prepareStatement(sql);
        ResultSet resultSet = preparedStatement.executeQuery();
        JDBCUtils.close(resultSet,preparedStatement);
        return resultSet.getLong(1);
    }

}
