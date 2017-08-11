package ifox.hh.servlet;

import ifox.hh.Controller.impl.OperateImpl.CheckOperateCt;
import ifox.hh.Controller.impl.cardImpl.ActivateCardController;
import ifox.hh.service.ServiceOfBillingSystem;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.sql.SQLException;

/**
 * Created by 41988 on 2017/8/9.
 */
@WebServlet(name = "CardServletOfBillingSystem" ,urlPatterns = "/card" )
public class CardServletOfBillingSystem extends HttpServlet {
    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        try {
            process(req,resp);
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        try {
            process(req,resp);
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    private void process(HttpServletRequest req, HttpServletResponse resp) throws SQLException, ServletException, IOException {
        String url = req.getRequestURI();
        int lastIndex = url.lastIndexOf("/");
        String action = url.substring(lastIndex + 1);
        String dispatcherUrl = null;
        if(action.equals("activateCard")){
            ActivateCardController activateCardController = new ActivateCardController();
            dispatcherUrl = activateCardController.handleRequest(req, resp);
        }else if(action.equals("recharge")){
            Double moneyOfRecharge = Double.parseDouble(req.getParameter("moneyOfRecharge"));
            int cid = Integer.parseInt(req.getParameter("cid"));
            boolean result = new ServiceOfBillingSystem().recharge(cid,moneyOfRecharge);
        } else if (action.equals("checkCardStatusAndBalance")) {
            CheckOperateCt checkOperateCt = new CheckOperateCt();
            dispatcherUrl = checkOperateCt.handleRequest(req, resp);
        }
        if (dispatcherUrl != null) {
            req.getRequestDispatcher(dispatcherUrl).forward(req, resp);
        }
    }


}
