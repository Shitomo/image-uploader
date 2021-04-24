package controller;

import action.Action;
import action.CsvUploadAction;
import action.InvalidAction;
import bean.CensusData;
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
import java.util.Set;


@WebServlet(urlPatterns = { "/csv" })
@MultipartConfig
public class CSVUploaderServlet extends HttpServlet {

    @Override
    protected void doPost(
            HttpServletRequest request,
            HttpServletResponse response
    )
            throws ServletException, IOException {
        String strActionName = request.getParameter("action_name");
        Action action = getInstance(strActionName);
        String csvContent = action.execute(request);

        //バリデーターのセットアップ
        ValidatorFactory factory = Validation.buildDefaultValidatorFactory();
        Validator validator = factory.getValidator();

        CsvMapper mapper = new CsvMapper();
        CsvSchema schema =  CsvSchema.emptySchema().withHeader();

        try {
            MappingIterator<CensusData> it = mapper.readerFor(CensusData.class).with(schema)
                    .readValues(csvContent);
            while (it.hasNextValue()) {
                CensusData value = it.nextValue();
                Set<ConstraintViolation<CensusData>> constraintViolations = validator
                        .validate(value);
                CensusData.setMode("DETAIL");
                for (ConstraintViolation<CensusData> validateResult : constraintViolations) {
                    System.out.println(validateResult.getMessage());
                }
            }
            RequestDispatcher rd = request.getRequestDispatcher("/WEB-INF/jsp/image.jsp");
            rd.forward(request, response);
        } catch (UnrecognizedPropertyException e) {
            request.setAttribute("errMsg","ヘッダー行が不正です");
            RequestDispatcher rd = request.getRequestDispatcher("/WEB-INF/jsp/error.jsp");
            rd.forward(request, response);
        } catch (CsvMappingException e) {
            request.setAttribute("errMsg","CSVの形式が不正です");
            RequestDispatcher rd = request.getRequestDispatcher("/WEB-INF/jsp/error.jsp");
            rd.forward(request, response);
        }
    }


    private static Action getInstance(String actionName) {
        switch (actionName) {
            case "upload":
                return new CsvUploadAction();
            default:
                //TODO　何もしないアクションを返すべき
                return new InvalidAction();
        }
    }
}
