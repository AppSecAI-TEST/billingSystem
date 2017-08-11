package ifox.hh.Controller.impl.cardImpl;

import ifox.hh.Controller.Controller;
import ifox.hh.entity.Card;
import ifox.hh.service.ServiceOfBillingSystem;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.sql.SQLException;

/**
 * Created by 41988 on 2017/8/9.
 */
public class ActivateCardController implements Controller {
    private ServiceOfBillingSystem serviceOfBillingSystem = new ServiceOfBillingSystem();
    @Override
    public String handleRequest(HttpServletRequest request, HttpServletResponse response) throws SQLException {
        Card card = new Card(request.getParameter("cardNum"),request.getParameter("name"),Double.parseDouble(request.getParameter("balance")),
                request.getParameter("cardStatus"));
       boolean insertStatus = serviceOfBillingSystem.save(card);
        if (insertStatus == true) {
            request.setAttribute("Msg","Success");
            return "success.jsp";//TODO 假设一个页面
        } else {
            request.setAttribute("Msg","Failed");
            return "failed.jsp";//TODO 假设一个页面
        }
    }
}
