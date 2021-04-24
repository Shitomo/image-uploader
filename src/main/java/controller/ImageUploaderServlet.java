package controller;

import java.io.IOException;
import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.MultipartConfig;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import action.Action;
import action.ImageUploadAction;
import action.InvalidAction;
import bean.Image;


@WebServlet(urlPatterns = { "/image" })
@MultipartConfig
public class ImageUploaderServlet extends HttpServlet {

    @Override
    protected void doPost (
            HttpServletRequest request,
            HttpServletResponse response
    )
            throws ServletException, IOException {
        String strActionName = request.getParameter("action_name");
        Action action = getInstance(strActionName);
        String forwardPath = action.execute(request);
        RequestDispatcher rd = request.getRequestDispatcher(forwardPath);

        rd.forward(request, response);
    }


    private static Action getInstance(String actionName) {
        switch (actionName) {
            case "upload":
                return new ImageUploadAction();
            default:
                //TODO　何もしないアクションを返すべき
                return new InvalidAction();
        }
    }
}
