package action;

import javax.servlet.http.HttpServletRequest;

public abstract class Action {
    abstract String processRequest(HttpServletRequest request);
    //MEMO processRequestは隠して，公開した関数executeから実行する　-> なぜ？
    public String execute(HttpServletRequest request) {
        return processRequest(request);
    }
}
