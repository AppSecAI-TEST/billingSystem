package ifox.hh.Controller.impl.computerImpl;

import ifox.hh.Controller.Controller;
import ifox.hh.service.ServiceOfBillingSystem;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.sql.SQLException;

/**
 * Created by 41988 on 2017/8/10.
 */
public class ChangeStatus implements Controller {
    ServiceOfBillingSystem serviceOfBillingSystem = new ServiceOfBillingSystem();
    @Override
    public String handleRequest(HttpServletRequest request, HttpServletResponse response) throws SQLException {
        String status = request.getParameter("computerStatus");
        int id = Integer.parseInt(request.getParameter("computerId"));
        boolean result = serviceOfBillingSystem.changeStatus(id,status);
        if (result) {
            return "changeStatus.jsp";// TODO: 2017/8/10 改变状态成功页面  
        }
        return " ";// TODO: 2017/8/10 改变失败页面
    }
}
