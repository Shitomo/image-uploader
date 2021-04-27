package controller;

import action.Action;
import bean.Form;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

@WebServlet(urlPatterns = { "/creator" })
public class SurveyCreatorServlet extends HttpServlet {
    @Override
    protected void doGet(
            HttpServletRequest request,
            HttpServletResponse response
    )
            throws ServletException, IOException {
        Form form = new Form(true);
        request.setAttribute("formData", form);
        RequestDispatcher rd = request.getRequestDispatcher("/WEB-INF/jsp/survey-creator.jsp");
        rd.forward(request, response);
    }

    @Override
    protected void doPost(
            HttpServletRequest request,
            HttpServletResponse response
    )
            throws ServletException, IOException {
        doGet(request, response);
    }
}
