package ifox.hh.servlet;

import ifox.hh.Controller.impl.OperateImpl.EndOperateComputerCt;
import ifox.hh.Controller.impl.OperateImpl.OperateComputerCt;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.sql.SQLException;

/**
 * Created by 41988 on 2017/8/10.
 */
public class OperateOfBillingSystem extends HttpServlet {
    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        try {
            process(req, resp);
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        try {
            process(req, resp);
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    private void process(HttpServletRequest req, HttpServletResponse resp) throws SQLException, ServletException, IOException {
        String url = req.getRequestURI();
        int lastIndex = url.lastIndexOf("/");
        String action = url.substring(lastIndex + 1);
        String dispatcherUrl = null;
        if (action.equals("operate")) {
            OperateComputerCt operateComputerCt = new OperateComputerCt();
            dispatcherUrl = operateComputerCt.handleRequest(req, resp);
        } else if (action.equals("quit")) {
            EndOperateComputerCt endOperateComputerCt = new EndOperateComputerCt();
            dispatcherUrl = endOperateComputerCt.handleRequest(req, resp);
        }
        if (dispatcherUrl != null) {
            req.getRequestDispatcher(dispatcherUrl).forward(req,resp);
        }
    }
}
