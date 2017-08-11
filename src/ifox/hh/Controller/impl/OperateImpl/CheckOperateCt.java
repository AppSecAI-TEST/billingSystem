package ifox.hh.Controller.impl.OperateImpl;

import ifox.hh.Controller.Controller;
import ifox.hh.service.ServiceOfBillingSystem;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.sql.SQLException;

/**
 * Created by 41988 on 2017/8/10.
 */
public class CheckOperateCt implements Controller {
    ServiceOfBillingSystem serviceOfBillingSystem = new ServiceOfBillingSystem();

    @Override
    public String handleRequest(HttpServletRequest request, HttpServletResponse response) throws SQLException {
        int cardID = Integer.parseInt(request.getParameter("cardId"));
        String checkMsg = serviceOfBillingSystem.checkCardStatusAndBalance(cardID);
        request.setAttribute("Msg", checkMsg);
        return "";// TODO: 2017/8/11 检查卡是否能上网之后的页面
    }
}
