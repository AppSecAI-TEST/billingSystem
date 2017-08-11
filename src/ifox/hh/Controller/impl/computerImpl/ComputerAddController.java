package ifox.hh.Controller.impl.computerImpl;

import ifox.hh.Controller.Controller;
import ifox.hh.entity.Computer;
import ifox.hh.service.ServiceOfBillingSystem;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.sql.SQLException;

/**
 * Created by 41988 on 2017/8/10.
 */
public class ComputerAddController implements Controller {
    ServiceOfBillingSystem serviceOfBillingSystem = new ServiceOfBillingSystem();

    @Override
    public String handleRequest(HttpServletRequest request, HttpServletResponse response) throws SQLException {
        Computer computer = new Computer(request.getParameter("computerBrand"));
        boolean result = serviceOfBillingSystem.addComputer(computer);
        if (result) {
            return "success.jsp";//TODO 添加电脑成功页面；
        }
        return "failed.jsp";// TODO: 2017/8/10 添加电脑失败页面
    }
}
