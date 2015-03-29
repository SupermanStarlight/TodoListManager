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
        int month = date.getMonth()+1;
        int day = date.getDate();
        return day+"/"+month+"/"+year;

    }

    public String getTitle(){
        return title;
    }

    public boolean isItemOverdue(){
        Calendar calendar = Calendar.getInstance();
        int year = calendar.get(Calendar.YEAR);
        int month = calendar.get(Calendar.MONTH);
        int day = calendar.get(Calendar.DAY_OF_MONTH);
        Date todayDate = new Date(year,month,day);
        if(this.date.before(todayDate)){
            return true;
        }
        else return false;

    }
}

