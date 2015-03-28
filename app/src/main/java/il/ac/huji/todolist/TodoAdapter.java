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
import android.util.Log;
import 	android.net.Uri;
import android.content.Intent;
import android.app.Activity;


public class TodoAdapter extends ArrayAdapter<String> {
    private final Context context;
    private  ArrayList<String> values = new ArrayList<String>();
    private final String call = "Call ";
    final static String TAG = "myApp";


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

    /*
    this is the dialog of long pressing a todo
     */
    private void todo_dialog(final int position) {
        String todoTitle = values.get(position);
        final Dialog dialog = new Dialog(this.context);
        dialog.setContentView(R.layout.dialog_body);
        dialog.setTitle(todoTitle);
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
        dialog.show();

        Button callButton = (Button) dialog.findViewById(R.id.menuItemCall);

        if (todoTitle.startsWith(call)) {
            callButton.setText(todoTitle);
            final String phoneNum = todoTitle.replace(call,"");
            phoneNum.trim();
            Log.v(TAG, "this is it"+phoneNum);


            callButton.setOnClickListener(new OnClickListener() {
                @Override
                public void onClick(View v) {
                    Intent dial = new Intent(Intent.ACTION_DIAL);
                    dial.setData(Uri.parse("tel:" + phoneNum));
                    context.startActivity(dial);
                }
            });
        }
        else {
            callButton.setVisibility(View.INVISIBLE);
        }
    }

}
























