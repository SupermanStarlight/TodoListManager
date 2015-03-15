package il.ac.huji.todolist;

import android.content.Context;
import android.graphics.Color;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.TextView;
import android.view.View.OnClickListener;
import android.view.View.OnLongClickListener;
import java.util.ArrayList;
import android.app.Dialog;

public class TodoAdapter extends ArrayAdapter<String> {
    private final Context context;
    private  ArrayList<String> values = new ArrayList<String>();


    public TodoAdapter(Context context, ArrayList<String> values) {
        super(context, 0, values);
        this.context = context;
        this.values = values;

    }

    @Override
    public View getView(final int position, View convertView, ViewGroup parent) {
        LayoutInflater inflater = (LayoutInflater) context
                .getSystemService(Context.LAYOUT_INFLATER_SERVICE);
        View rowView = inflater.inflate(R.layout.row, parent, false);
        TextView textView = (TextView) rowView.findViewById(R.id.textView);
        textView.setText(values.get(position));
        if(position%2==0){
            textView.setTextColor(Color.RED);
        }
        else {
            textView.setTextColor(Color.BLUE);
        }
        textView.setOnLongClickListener(new OnLongClickListener() {
            public boolean onLongClick(View v) {
                todo_dialog(position);
                return true;
            }
        });


        return rowView;
    }

    private void todo_dialog(final int position){
        final Dialog dialog = new Dialog(this.context);
        dialog.setContentView(R.layout.dialog_body);
        dialog.setTitle(values.get(position));
        Button deleteButton = (Button)
                dialog.findViewById(R.id.menuItemDelete);
       deleteButton.setOnClickListener(new OnClickListener() {
           @Override
           public void onClick(View v) {
               values.remove(position);
               notifyDataSetChanged();
               dialog.dismiss();
           }
       });
//TODO register button listener.
        dialog.show();
    }
}

























//import android.widget.ArrayAdapter;
//import java.util.ArrayList;
//import android.content.Context;
//import android.view.LayoutInflater;
//import android.view.View;
//import android.view.ViewGroup;
//import android.widget.ArrayAdapter;
//import android.widget.TextView;
//
//
///**
// * Created by Solution on 14/03/2015.
// */
//public class TodoAdapter extends ArrayAdapter<String> {
//
//    private ArrayList<String> todos;
//
//    public TodoAdapter(Context context, int textViewResourceId, ArrayList<String> todos) {
//        super(context, textViewResourceId, todos);
//        this.todos = todos;
//    }
//
//    public View getView(int position, View convertView, ViewGroup parent) {
//        LayoutInflater inflater =
//                LayoutInflater.from(getContext());
//        View view = inflater.inflate(R.layout.row);
//
//
//    }
//}
