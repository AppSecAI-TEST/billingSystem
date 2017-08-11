package ifox.hh.Controller.impl.OperateImpl;

import ifox.hh.Controller.Controller;
import ifox.hh.service.ServiceOfBillingSystem;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.sql.SQLException;

/**
 * Created by 41988 on 2017/8/11.
 */
public class OperateComputerCt implements Controller {
    private ServiceOfBillingSystem serviceOfBillingSystem = new ServiceOfBillingSystem();

    @Override
    public String handleRequest(HttpServletRequest request, HttpServletResponse response) throws SQLException {
        int cardId = Integer.parseInt(request.getParameter("cardId"));
        int computerId = Integer.parseInt(request.getParameter("computerId"));
        String status = request.getParameter("status");
        boolean result = serviceOfBillingSystem.confirmOperate(cardId, computerId, status);
        if (result) {
            request.setAttribute("operateMsg", "上机成功");
        } else {
            request.setAttribute("operateMsg", "上机失败");
        }
        return "";// TODO: 2017/8/11 上机之后的页面
    }
}
