package il.ac.huji.todolist;

import java.util.Calendar;
import java.util.Date;

/**
 * Created by Solution on 29/03/2015.
 */
public class TodoItem {
    private Date date;
    private Calendar calendar;
    private String title;


    public TodoItem(Date date, String title) {
        this.date = date;
        this.title = title;
    }

    public String getDateAsString(){
        int year = date.getYear();
        int month = date.getMonth();
        int day = date.getDay();
        return day+"/"+month+"/"+year;

    }

    public String getTitle(){
        return title;
    }
}

