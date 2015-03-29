package il.ac.huji.todolist;

import android.content.Intent;
import android.support.v7.app.ActionBarActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.ListView;
import android.widget.EditText;
import android.widget.DatePicker;
import java.util.ArrayList;
import java.util.Date;

import android.widget.Button;



public class MainActivity extends ActionBarActivity {
    ListView listView;
    EditText input;
    ArrayList<TodoItem> todos = new ArrayList<TodoItem>();
    TodoAdapter adapter;
    DatePicker dueDate;
    ArrayList<Date> dueDateArray = new ArrayList<Date>();




    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        listView = (ListView) findViewById(R.id.LstTodoItems);
         adapter = new TodoAdapter(this,todos);
        listView.setAdapter(adapter);

    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.menu_main, menu);
        return true;
    }

    private void addTODO(){
        Intent intent = new Intent(this,AddNewTodoItemActivity.class);
        //startActivity(intent);
        startActivityForResult(intent, 1);
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        if (requestCode==1){
            if(data != null) {
                String newTodo = data.getStringExtra("title");
                Date dueDate = new Date(data.getLongExtra("dueDate",-1));

                adapter.add(new TodoItem(dueDate,newTodo));

            }

        }
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        int id = item.getItemId();
        input = (EditText) findViewById(R.id.edtNewItem);

        if (id == R.id.action_settings) {
           addTODO();

            return true;
        }

        return super.onOptionsItemSelected(item);
    }

}
