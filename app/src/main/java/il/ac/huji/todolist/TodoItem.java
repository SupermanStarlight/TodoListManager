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

    public int getId() {
        return id;
    }

    private int id;


    public TodoItem(Date date, String title) {
        this.date = date;
        this.title = title;
        this.id =(int) (System.currentTimeMillis() & 0xfffffff);

    }

    public String getDateAsString(){
        int year = date.getYear();
        int month = date.getMonth()+1;
        int day = date.getDate();
        return day+"/"+month+"/"+year;

    }
    public void setId(int id){
        this.id=id;
    }
    public String getTitle(){
        return title;
    }

    public long getDateAsLong(){
        return date.getTime();
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

