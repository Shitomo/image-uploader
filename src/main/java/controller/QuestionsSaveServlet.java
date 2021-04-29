package controller;

import bean.CensusData;
import bean.Form;
import bean.QuestionBean;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.json.JsonMapper;
import com.fasterxml.jackson.dataformat.csv.CsvMapper;
import com.fasterxml.jackson.dataformat.csv.CsvSchema;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.List;

@WebServlet(urlPatterns = { "/questions" })
public class QuestionsSaveServlet extends HttpServlet {
    @Override
    protected void doPost(
            HttpServletRequest request,
            HttpServletResponse response
    )
            throws ServletException, IOException {
        request.setCharacterEncoding("UTF-8");
        String formDataRaw = request.getParameter("questions");
        String formData = formDataRaw.replace("\n", "");
        final ObjectMapper jsonMapper = new ObjectMapper();
        QuestionBean[] questions = jsonMapper.readValue(formData, QuestionBean[].class);

        RequestDispatcher rd = request.getRequestDispatcher("/WEB-INF/jsp/survey-creator.jsp");
        rd.forward(request, response);
    }
}
