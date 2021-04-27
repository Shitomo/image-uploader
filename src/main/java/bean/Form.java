package bean;

import java.text.SimpleDateFormat;
import java.util.Date;

public class Form {
    public static String dateTimeLocalFormat = "yyyy-MM-dd'T'hh:mm:ss";
    public static String dateTimeLocalFormatForDisplay = "yyyy年MM月dd日　hh:mm";
    private String text;
    private boolean textIsEditable;
    private Date date;

    public String getText() {
        return text;
    }

    public boolean isNewMode() {
        return true;
    }

    public boolean isUpdateModeAfter() {
        return true;
    }

    public boolean isTextIsEditable() {
        //ここにはビジネスロジックが入る
        return textIsEditable;
    }

    public String getDate() {
        SimpleDateFormat sdf = new SimpleDateFormat(dateTimeLocalFormat);
        if (date == null) return "";
        else return sdf.format(date);
    }

    public String getDisplayDate() {
        SimpleDateFormat sdf = new SimpleDateFormat(dateTimeLocalFormatForDisplay);
        if (date == null) return "";
        else return sdf.format(date);
    }

    public void setText(String text) {
        this.text = text;
    }

    public void setTextIsEditable(boolean textIsEditable) {
        this.textIsEditable = textIsEditable;
    }

    public Form(String text, boolean textIsEditable, Date date) {
        this.text = text;
        this.textIsEditable = textIsEditable;
        this.date = date;
    }

    public Form(boolean textIsEditable) {
        this.textIsEditable = textIsEditable;
    }

    public Form() {
    }
}
