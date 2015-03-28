package il.ac.huji.todolist;

import android.content.Intent;
import android.support.v7.app.ActionBarActivity;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.view.Window;
import android.app.Activity;
import android.widget.Button;
import android.widget.DatePicker;
import android.widget.EditText;
import java.util.Calendar;
import java.util.Date;



public class AddNewTodoItemActivity extends Activity {
    Button okButton;
    Button cancelButton;
    EditText newTodoInput;
    DatePicker dueDate;
    private int dueDay;
    private int dueMonth;
    private int dueYear;





    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        getWindow().requestFeature(Window.FEATURE_NO_TITLE);
        setContentView(R.layout.activity_add_new_todo_item);
        settingDatePicker();
        addingNewTodo();


    }


    private void addingNewTodo(){
        okButton = (Button) findViewById(R.id.btnOK);
        newTodoInput = (EditText)findViewById(R.id.edtNewItem);
        cancelButton = (Button) findViewById(R.id.btnCancel);


        //on pressing ok
        okButton.setOnClickListener(new View.OnClickListener() {

            @Override
            public void onClick(View v) {
               String newTodoText = newTodoInput.getText().toString();
                Intent responseIntent = new Intent();
                responseIntent.putExtra("title", newTodoText);
                dueDay=dueDate.getDayOfMonth();
                String dayeSTR = String.valueOf(dueDay);
                dueMonth =dueDate.getMonth();
                dueYear = dueDate.getYear();
                responseIntent.putExtra("Date",dayeSTR);


                setResult(1, responseIntent);

                finish();

            }
        });
        //on pressing cancel
        cancelButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                finish();
            }
        });

    }
    //setting the datepicker, and sets it to this day
    private void settingDatePicker(){
        dueDate = (DatePicker)findViewById(R.id.datePicker);
        Calendar calendar = Calendar.getInstance();
        int year = calendar.get(Calendar.YEAR);
        int month = calendar.get(Calendar.MONTH);
        int day = calendar.get(Calendar.DAY_OF_MONTH);
        dueDate.init(year,month,day,null);


    }



    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_add_new_todo_item, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        int id = item.getItemId();

        //noinspection SimplifiableIfStatement
        if (id == R.id.action_settings) {
            return true;
        }

        return super.onOptionsItemSelected(item);
    }
}
