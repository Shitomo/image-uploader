<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<jsp:useBean id="image" scope="session" beanName="bean.Image" type="bean.Image" />
<html>
<header>
    <meta charset="UTF-8" />
    <title>Java Sample</title>
    <!--WEB-INFはサーブレットを介してしかアクセスできないリソース．生成された静的ファイルからはアクセスできない-->
    <!--ここから指定できるパスはオリジン(ホスト名+ポート名)を基準としている-->
    <script type="text/javascript" src="/uploader/js/jquery.js"></script>
    <script type="text/javascript" src="/uploader/js/jquery-ui.min.js"></script>
    <script type="text/javascript" src="/uploader/js/subwindow.js"></script>
</header>
<body>
<h1>Hello</h1>
<form action="csv" method="post" enctype="multipart/form-data">
    <input type="hidden" name="action_name" value="upload" />
    <input type="file" name="file" />
    <button>Upload</button>
</form>
</body>
</html>
