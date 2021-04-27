package controller;

import action.Action;
import action.CsvUploadAction;
import action.InvalidAction;
import bean.CensusData;
import bean.Form;
import com.fasterxml.jackson.databind.MappingIterator;
import com.fasterxml.jackson.databind.exc.UnrecognizedPropertyException;
import com.fasterxml.jackson.dataformat.csv.CsvMapper;
import com.fasterxml.jackson.dataformat.csv.CsvMappingException;
import com.fasterxml.jackson.dataformat.csv.CsvSchema;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.MultipartConfig;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.validation.ConstraintViolation;
import javax.validation.Validation;
import javax.validation.Validator;
import javax.validation.ValidatorFactory;
import java.io.IOException;
import java.util.*;


@WebServlet(urlPatterns = { "/changemode" })
@MultipartConfig
// Servlet クラスで指定できるアノテーション。 Servlet のインスタンスが multipart/form-data MIME タイプに準拠するリクエストを期待していることを示します。
public class ChangeEditModeServlet extends HttpServlet {

    @Override
    protected void doPost(
            HttpServletRequest request,
            HttpServletResponse response
    )
            throws ServletException, IOException {
        Form form = new Form("サーバーによって入力されたテキスト(After)", false, new Date());

        Form form1 = new Form("テキスト1", false, new Date());
        Form form2 = new Form("テキスト2", false, new Date());
        List<Form> formList = new ArrayList<>(Arrays.asList(form1,form2));

        request.setAttribute("formData", form);
        request.setAttribute("formList", formList);
        RequestDispatcher rd = request.getRequestDispatcher("/WEB-INF/jsp/survey-creator.jsp");
        rd.forward(request, response);

    }

}
