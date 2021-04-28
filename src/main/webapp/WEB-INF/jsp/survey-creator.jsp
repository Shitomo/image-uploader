<%@ page import="java.util.List" %>
<%@ page import="java.util.ArrayList" %>
<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<!--pom.xmlにJSTLを追加する必要あり-->
<!DOCTYPE html>
<jsp:useBean id="image" scope="session" beanName="bean.Image" type="bean.Image" />
<jsp:useBean id="formData" scope="request" beanName="bean.Form" type="bean.Form" />
<%
    List<String> list = new ArrayList<>();
    list.add("Hello 1.");
    list.add("Hello 2.");
    request.setAttribute("list", list);
%>
<html>
<header>
    <meta charset="UTF-8" />
    <title>Java Sample</title>
    <!--WEB-INFはサーブレットを介してしかアクセスできないリソース．生成された静的ファイルからはアクセスできない-->
    <!--ここから指定できるパスはオリジン(ホスト名+ポート名)を基準としている-->
    <script type="text/javascript" src="/uploader/js/jquery.js"></script>
    <script type="text/javascript" src="/uploader/js/jquery-ui.min.js"></script>
    <script type="text/javascript" src="/uploader/js/form-builder.min.js"></script>
    <script type="text/javascript" src="/uploader/js/form-render.min.js"></script>
    <script type="text/javascript" src="/uploader/js/site.min.js"></script>
    <script type="text/javascript" src="/uploader/js/subwindow.js"></script>
</header>
<body>
<h1>Hello</h1>
<form action="image" method="post" enctype="multipart/form-data">
    <input type="hidden" name="action_name" value="upload" />
    <input type="file" name="file" />
    <button>Upload</button>
</form>
<h1>CSVアップロード</h1>
<form action="csv" method="post" enctype="multipart/form-data">
    <input type="hidden" name="action_name" value="upload" />
    <input type="file" name="file" />
    <button>Upload</button>
</form>
<h1>CSVダウンロード</h1>
<!--
pageContext.request.contextPath =>
/uploader/$%7BpageContext.request.contextPath%7D/download

何も指定しないとGETメソッドになる-->
<form action="download" method="post">
    <input type="hidden" name="id" value="1" >
    <!--onclickの上書きすると，フォームを送信できなくなるので注意-->
    <button>
        ユーザー情報一括ダウンロード(csv)
    </button>
</form>
<img src=<%= image.source%> />
<div>
    <button class="subwindow">別のウィンドウでホームを開く</button>
</div>

<h1>サンプルフォーム</h1>
<form action="changemode" method="post">
    <input type="text" value=<%= formData.getText() %> name="test" <%= formData.isTextIsEditable()? "" : "readonly"%>>
    <input type="datetime-local" value=<%= formData.getDate() %> name="test" <%= formData.isTextIsEditable()? "" : "readonly"%>>
    <button>更新</button>
</form>
<!---更新のためのビジネスロジックは更新できる要素グループごとに作成するのがよさそう-->
<!---新規作成モード，更新モード(本登録前，本登録後，確認期間終了後)-->
<!---各要素ごとに，編集可能かどうかの判定メソッドを用意する．
判定基準はその要素が編集可能となるモードか否かtrue-->

<h1>一覧</h1>
<table border="1">
    <tr>
        <th>名前</th>
        <th>日時</th>
    </tr>
<c:forEach items="${formList}" var="listItem" >
    <tr>
        <td><c:out value="${listItem.text}"/></td>
    　   <td><c:out value="${listItem.displayDate}"/></td>
    </tr>
</c:forEach>
</table>

<div id="fb-editor"></div>
</body>
<style>
    input:read-only, textarea:read-only {
        background-color: #ccc;
        border: none;
    }
</style>
<script>
    jQuery(function($) {
        var options = {
                i18n: {
                    location : '/uploader/assets/lang',
                    locale: 'ja-JP'
                }
            },
            $fbTemplate = $(document.getElementById('fb-editor'));
        $fbTemplate.formBuilder(options);
        $fbTemplate.actions.setLang('ja-JP')
    });

    function double(btn){
        btn.disabled=true;
    }
</script>
</html>
