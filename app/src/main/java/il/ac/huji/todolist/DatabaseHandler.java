package il.ac.huji.todolist;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.content.ContentValues;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import il.ac.huji.todolist.TodoItem;

/**
 * Created by Solution on 24/04/2015.
 */
public class DatabaseHandler extends SQLiteOpenHelper {

    // All Static variables
    // Database Version
    private static final int DATABASE_VERSION = 1;

    // Database Name
    private static final String DATABASE_NAME = "todoManager";

    // Contacts table name
    private static final String TABLE_TODOS = "todos";

    // Contacts Table Columns names
    private static final String KEY_ID = "id";
    private static final String TODO_TITLE = "title";
    private static final String TODO_DATE = "todoDate";

    public  DatabaseHandler(Context context) {
        super(context, DATABASE_NAME, null, DATABASE_VERSION);
    }

    public void onCreate(SQLiteDatabase db) {
        String CREATE_CONTACTS_TABLE = "CREATE TABLE " +TABLE_TODOS + "("+ KEY_ID + " INTEGER PRIMARY KEY,"+
                 TODO_TITLE +" TEXT,"+  TODO_DATE +  " INTEGER" + ")";
        db.execSQL(CREATE_CONTACTS_TABLE);
    }

    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        // Drop older table if existed
        db.execSQL("DROP TABLE IF EXISTS " + TABLE_TODOS);

        // Create tables again
        onCreate(db);
    }

    // Adding new contact
    public void addTodo(TodoItem newTodo) {
        SQLiteDatabase db = this.getWritableDatabase();
        ContentValues values = new ContentValues();

        values.put(TODO_TITLE, newTodo.getTitle()); // Todo title
        values.put(TODO_DATE, newTodo.getDateAsLong()); // todo doDate
        values.put(KEY_ID, String.valueOf(newTodo.getId())); // todo id


        // Inserting Row
        db.insert(TABLE_TODOS, null, values);
        db.close(); // Closing database connection
    }

    // Getting single contact
    public TodoItem getTodo(int id) {
        SQLiteDatabase db = this.getReadableDatabase();

        Cursor cursor = db.query(TABLE_TODOS, new String[] { KEY_ID,
                        TODO_TITLE, TODO_DATE }, KEY_ID + "=?",
                new String[] { String.valueOf(id) }, null, null, null, null);
        if (cursor != null)
            cursor.moveToFirst();
        //int id = Integer.parseInt(cursor.getString(0));
        String todoTitle =  cursor.getString(1);
        Date todoDate = new Date(Integer.parseInt(cursor.getString(2)));

        TodoItem newTodo = new TodoItem(todoDate,todoTitle);
        // return contact
        return newTodo;
    }

    // Getting All Contacts
    public ArrayList<TodoItem> getAllTodos() {
        ArrayList<TodoItem> todoList = new ArrayList<TodoItem>();
        // Select All Query
        String selectQuery = "SELECT  * FROM " + TABLE_TODOS;

        SQLiteDatabase db = this.getWritableDatabase();
        Cursor cursor = db.rawQuery(selectQuery, null);

        // looping through all rows and adding to list
        if (cursor.moveToFirst()) {
            do {
                int id = Integer.parseInt(cursor.getString(0));
                String todoTitle =  cursor.getString(1);
                Date todoDate = new Date(Long.parseLong(cursor.getString(2)));
                TodoItem todo = new TodoItem(todoDate,todoTitle);
                todo.setId(id);

                // Adding contact to list
                todoList.add(todo);
            } while (cursor.moveToNext());
        }

        // return contact list
        return todoList;

    }

    // Getting contacts Count
   // public int getContactsCount() {}
    // Updating single contact
    //public int updateContact(Contact contact) {}

    // Deleting single contact
    public void deleteTodo(TodoItem toRemoveTodo) {
        SQLiteDatabase db = this.getWritableDatabase();
        db.delete(TABLE_TODOS, KEY_ID + " = ?",
                new String[] { String.valueOf(toRemoveTodo.getId())});
        db.close();
    }


}