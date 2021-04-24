package action;

import bean.Image;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;
import javax.servlet.http.Part;
import java.io.*;
import java.nio.charset.StandardCharsets;
import java.util.Base64;

public class CsvUploadAction extends Action {
    @Override
    protected String processRequest(HttpServletRequest request) throws IOException, ServletException {
        Part filePart = request.getPart("file");
        String fileTitle = filePart.getName();
        InputStream fileContent = filePart.getInputStream();
        byte[] byteArray = getByteArray(fileContent);
        return new String(byteArray, StandardCharsets.UTF_8);
    }

    private static byte[] getByteArray(InputStream stream) throws IOException {
        ByteArrayOutputStream b = new ByteArrayOutputStream();
        BufferedOutputStream os = new BufferedOutputStream(b);

        while(true) {
            int i = stream.read();
            if (i == -1) break;
            os.write(i);
        }

        os.flush(); //TODO ここで何をしているか理解する
        os.close();
        return b.toByteArray();
    }
}
