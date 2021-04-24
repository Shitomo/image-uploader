package action;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import java.io.IOException;

public abstract class Action {
    abstract String processRequest(HttpServletRequest request) throws IOException, ServletException;
    //MEMO processRequestは隠して，公開した関数executeから実行する　-> なぜ？
    public String execute(HttpServletRequest request) throws IOException, ServletException {
        return processRequest(request);
    }
}
