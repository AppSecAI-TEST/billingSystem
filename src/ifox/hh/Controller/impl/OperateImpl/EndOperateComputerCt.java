package ifox.hh.Controller.impl.OperateImpl;

import ifox.hh.Controller.Controller;
import ifox.hh.service.ServiceOfBillingSystem;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.sql.SQLException;

/**
 * Created by 41988 on 2017/8/11.
 */
public class EndOperateComputerCt implements Controller {
    private ServiceOfBillingSystem serviceOfBillingSystem = new ServiceOfBillingSystem();

    @Override
    public String handleRequest(HttpServletRequest request, HttpServletResponse response) throws SQLException {

        int cardId = Integer.parseInt(request.getParameter("cardId"));
        int computerId = Integer.parseInt(request.getParameter("computerId"));
        String status = request.getParameter("status");
        if (serviceOfBillingSystem.endOperate(cardId, computerId, status)) {
            request.setAttribute("endMsg", "成功下机");
        } else {
            request.setAttribute("endMsg", "下机失败");
        }
        return "";// TODO: 2017/8/11 下机之后的页面 
    }
}
