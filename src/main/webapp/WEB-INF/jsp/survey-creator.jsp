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
<div id="fb-editor"></div>
</body>
<script>
    jQuery(function($) {
        var options = {
                locale: 'ja-JP',
                override: {
                    'ja-JP': {
                        addOption: '選択肢を追加'
                    }
                }
            },
            $fbTemplate = $(document.getElementById('fb-editor'));
        $fbTemplate.formBuilder(options);
    });

    function double(btn){
        btn.disabled=true;
    }
</script>
</html>
