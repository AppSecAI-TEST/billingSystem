package ifox.hh.Controller.impl.computerImpl;

import ifox.hh.Controller.Controller;
import ifox.hh.service.ServiceOfBillingSystem;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.sql.SQLException;

/**
 * Created by 41988 on 2017/8/10.
 */
public class DeleteComputerController implements Controller {
    ServiceOfBillingSystem serviceOfBillingSystem = new ServiceOfBillingSystem();
    @Override
    public String handleRequest(HttpServletRequest request, HttpServletResponse response) throws SQLException {
        int id = Integer.parseInt(request.getParameter("ComputerId"));
        boolean result = serviceOfBillingSystem.deleteComputer(id);
        if (result) {
            return "success.jsp" ;// TODO: 2017/8/10 删除电脑的JSP
        }
        return "failed.jsp";// TODO: 2017/8/10 删除电脑失败的JSO页面
    }
}
