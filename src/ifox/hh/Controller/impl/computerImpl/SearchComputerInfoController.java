package ifox.hh.Controller.impl.computerImpl;

import ifox.hh.Controller.Controller;
import ifox.hh.service.ServiceOfBillingSystem;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.sql.SQLException;

/**
 * Created by 41988 on 2017/8/10.
 */
public class SearchComputerInfoController implements Controller {
    private ServiceOfBillingSystem serviceOfBillingSystem = new ServiceOfBillingSystem();
    @Override
    public String handleRequest(HttpServletRequest request, HttpServletResponse response) throws SQLException {
        int pageNum = 1;
        try{
            pageNum = Integer.parseInt(request.getParameter("pageNum"));
        }catch (Exception e){}
        request.setAttribute("computerPage",serviceOfBillingSystem.searchComputerInfo(pageNum));
        return "computerDetailInfo.jsp"; // TODO: 2017/8/10 查询电脑显示页面 
    }
}
