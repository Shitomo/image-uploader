package controller;

import bean.CensusData;
import bean.User;
import com.fasterxml.jackson.databind.MappingIterator;
import com.fasterxml.jackson.dataformat.csv.CsvMapper;
import com.fasterxml.jackson.dataformat.csv.CsvSchema;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.*;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Date;
import java.util.List;

@WebServlet(urlPatterns = { "/download" })
public class CSVDownloadServlet extends HttpServlet {
    @Override
    protected void doPost(
            HttpServletRequest request,
            HttpServletResponse response
    )
            throws ServletException, IOException {
        //
        String id = request.getParameter("id");

        // ディレクトリ用の日時のフォーマット.定数として共通の位置においておくべき
        String dateFormat = "yyyy-MMdd-HHmmss";
        // 表示用の日時のフォーマット
        String dateFormatForDisplay = "yyyy年MM月dd日 HH:mm:ss";
        SimpleDateFormat sdfForDirectoryName = new SimpleDateFormat(dateFormat);
        SimpleDateFormat sdfForDisplay = new SimpleDateFormat(dateFormatForDisplay);

        /** TODO idチェック */

        // サーブレットが動作しているコンテキストと，相対パスから絶対パスを得る
        String path = this.getServletContext().getRealPath("/WEB-INF/csv/");


        Date nowDate = new Date();
        String nowDateFormat = sdfForDirectoryName.format(nowDate);
        Path p = Paths.get(path + id + "/" + nowDateFormat);

        try {
            Files.createDirectories(p);
        } catch(IOException e) {
            System.out.println("ディレクトリの作成に失敗しました" + e);
            return;
        }

        // ディレクトリの名前を入手
        List<String> directoryNames = getDirectoryList(path + id);
        List<Date> dateList = getDateList(directoryNames, sdfForDirectoryName);
        List<String> dateDisplay = getDateDisplayList(dateList, sdfForDisplay);

        String csvContent;
        try {
            csvContent = getList(path + "test.csv");
        } catch (FileNotFoundException e) {
            System.out.println("ファイル見つかりません" + e);
            return;
        } catch (UnsupportedEncodingException e) {
            System.out.println("サポートされていない文字列を指定されています" + e);
            return;
        } catch (IOException  e) {
            System.out.println("ファイルの読み込み中にエラーが発生しました" + e);
            return;
        }

        //文字コードと出力するCSVファイル名を設定
        // これは、バイナリファイルでは既定です。これは未知のバイナリ形式のファイルを表すものであり、ブラウザーはふつう実行したり、実行するべきか確認したりしません。これらは Content-Disposition ヘッダーの値に attachment が設定されたかのように扱い、「名前を付けて保存」ダイアログを提案します。
        response.setContentType("application/octet-stream;charset=MS932");
        response.setHeader("Content-Disposition", "attachment; filename=\"test.csv\"");

        try (PrintWriter pw = response.getWriter()) {
            pw.print(csvContent);
        } catch (IOException e) {
            e.printStackTrace();
        }
        /*//try-with-resources文を使うことでclose処理を自動化
        try (PrintWriter pw = response.getWriter()) {
            User data1 = new User("日本語", "1");
            User data2 = new User("使える", "23");

            List<User> allUsers = new ArrayList<>(Arrays.asList(data1, data2));
            for (int i = 0; i < allUsers.size(); i++) {
                String name = allUsers.get(i).getName();
                String age  = allUsers.get(i).getAge();

                //CSVファイル内部に記載する形式で文字列を設定
                String outputString = name + "," + age + "\r\n";

                //CSVファイルに書き込み
                pw.print(outputString);
            }
        } catch (IOException e) {
            e.printStackTrace();
        }*/
    }

    /**
     *
     */
    // すでにCSVファイルとして保存されているものをCSVファイルにする必要はない
    public String getList(String path)
        throws FileNotFoundException, UnsupportedEncodingException, IOException {
        StringBuilder builder = new StringBuilder();

        List<User> list=new ArrayList<>();
        try (FileInputStream fis = new FileInputStream(path);
             InputStreamReader isr = new InputStreamReader(fis, "MS932");
             BufferedReader br = new BufferedReader(isr)) {
            String oneLine = br.readLine();
            while (oneLine != null){
                builder.append(oneLine + "\r\n");
                oneLine = br.readLine();
            }
        }
        return builder.toString();
    }

    private List<String> getDirectoryList(String path) {
        //Fileクラスのオブジェクトを生成する
        File dir = new File(path);

        //listFilesメソッドを使用して一覧を取得する
        File[] list = dir.listFiles();
        List<String> directoryNameList = new ArrayList<>();

        if(list != null) {
            for (File file : list) {
                //ディレクトリの場合
                if (file.isDirectory()) {
                    directoryNameList.add(file.getName());
                }
            }
        }
        return directoryNameList;
    }

    private List<Date> getDateList(List<String> directoryNameList, SimpleDateFormat sdf) {
        List<Date> dateList = new ArrayList<>();
        for (String directoryName : directoryNameList) {
            try {
                Date date = sdf.parse(directoryName);
                dateList.add(date);
            } catch (ParseException e) {
                // 形式に沿っていないディレクトリはスキップする．
                System.out.println("警告 : 形式が異なるディレクトリが存在します");
            }
        }
        return dateList;
    }

    private List<String> getDateDisplayList(List<Date> dateList, SimpleDateFormat sdf) {
        List<String> dateDisplayList = new ArrayList<>();
        for (Date date : dateList) {
            dateDisplayList.add(sdf.format(date));
        }
        return dateDisplayList;
    }
}
