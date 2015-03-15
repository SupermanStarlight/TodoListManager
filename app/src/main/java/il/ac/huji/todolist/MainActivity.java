package il.ac.huji.todolist;

import android.support.v7.app.ActionBarActivity;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.ListView;
import android.widget.EditText;
import java.util.ArrayList;


public class MainActivity extends ActionBarActivity {
    ListView listView;
    EditText input;
    ArrayList<String> todos = new ArrayList<String>();
    TodoAdapter adapter;




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

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        int id = item.getItemId();
        input = (EditText) findViewById(R.id.edtNewItem);

        if (id == R.id.action_settings) {
            String newTodo = input.getText().toString();
            adapter.add(newTodo);

            return true;
        }

        return super.onOptionsItemSelected(item);
    }

}
