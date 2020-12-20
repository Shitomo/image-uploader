package action;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.Part;
import java.io.InputStream;
import java.util.Base64;

public class InvalidAction extends Action {
    @Override
    protected String processRequest(HttpServletRequest request) {
        return "/jsp/error.jsp";
    }
}
