package action;

import bean.Image;

import java.io.BufferedOutputStream;
import java.io.ByteArrayOutputStream;
import java.io.InputStream;
import java.util.Base64;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;
import javax.servlet.http.Part;

public class ImageUploadAction extends Action {
    @Override
    protected String processRequest(HttpServletRequest request) {
        try {
            Part filePart = request.getPart("file");
            String fileTitle = filePart.getName();
            InputStream fileContent = filePart.getInputStream();
            byte[] byteArray = getByteArray(fileContent);
            String base64String = Base64.getEncoder().encodeToString(byteArray);
            //表示する画像の拡張子を指定しています．
            //MEMO とりあえずpngを指定しましたが，なぜかjpgをアップロードしても普通に表示されます
            String base64StringWithMIMEType = "data:image/png;base64,".concat(base64String);
            Image image = new Image(base64StringWithMIMEType, fileTitle);
            HttpSession session = request.getSession();
            session.setAttribute("image", image);
            return "/WEB-INF/jsp/survey-creator.jsp";
        } catch (Exception e) {
            //TODO 具体的なエラーでキャッチする
            return "/WEB-INF/jsp/survey-creator.jsp";
        }
    }
    //TODO 具体的なエラーを投げるようにする
    private static byte[] getByteArray(InputStream stream) throws Exception {
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
