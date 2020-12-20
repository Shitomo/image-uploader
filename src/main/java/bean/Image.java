package bean;

public class Image {
    public String source;
    public String title;

    public String getSource() {
        return source;
    }

    public void setSource(String source) {
        this.source = source;
    }

    /*
     JSP側でImageをインスタンス化するための空のコンストラクタ
    */
    public Image() {
    }

    public Image(String source, String title) {
        this.source = source;
        this.title = title;
    }
}
