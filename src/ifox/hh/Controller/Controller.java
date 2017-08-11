package ifox.hh.Controller;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.sql.SQLException;

/**
 * Created by 41988 on 2017/8/9.
 */
public interface Controller {
    String handleRequest(HttpServletRequest request, HttpServletResponse response) throws SQLException;
}
