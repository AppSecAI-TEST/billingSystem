package ifox.hh.servlet;

import ifox.hh.Controller.impl.computerImpl.ComputerAddController;
import ifox.hh.Controller.impl.computerImpl.DeleteComputerController;
import ifox.hh.Controller.impl.computerImpl.SearchComputerInfoController;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.sql.SQLException;

/**
 * Created by 41988 on 2017/8/10.
 */
@WebServlet(name = "ComputerServletOfBillingSystem")
public class ComputerServletOfBillingSystem extends HttpServlet {

    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        try {
            computerProcess(request, response);
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        try {
            computerProcess(request, response);
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    private void computerProcess(HttpServletRequest request, HttpServletResponse response) throws SQLException, ServletException, IOException {
        String url = request.getRequestURI();
        int lastIndex = url.lastIndexOf("/");
        String action = url.substring(lastIndex + 1);
        String dispatcherUrl = null;
        if(action.equals("addComputer")){
            ComputerAddController computerAddController = new ComputerAddController();
            dispatcherUrl = computerAddController.handleRequest(request, response);
        } else if (action.equals("deleteComputer")) {
            DeleteComputerController deleteComputerController = new DeleteComputerController();
            dispatcherUrl = deleteComputerController.handleRequest(request, response);
        } else if (action.equals("searchComputer")) {
            SearchComputerInfoController searchComputerInfoController = new SearchComputerInfoController();
            dispatcherUrl = searchComputerInfoController.handleRequest(request, response);
        } else if (action.equals("changeStatus")) {

        }
        request.getRequestDispatcher(dispatcherUrl).forward(request,response);
    }
}
