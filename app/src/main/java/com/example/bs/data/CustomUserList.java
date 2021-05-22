package com.example.bs.data;

import android.app.Activity;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.Button;
import android.widget.PopupWindow;
import android.widget.TextView;

import com.example.bs.R;

import java.util.ArrayList;
import java.util.List;

public class CustomUserList extends BaseAdapter {
    private Activity context;
    List<User> users;
    private PopupWindow pwindo;
    SQLiteDatabaseHandler db;
    BaseAdapter ba;

    public CustomUserList(Activity context, List<User> users, SQLiteDatabaseHandler db) {
        this.context = context;
        this.users = users;
        this.db=db;
    }

    public static class ViewHolder
    {
        TextView textViewId;
        TextView textViewFirstName;
        TextView textViewEmail;
        Button bookButton;
    }
    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        View row = convertView;
            LayoutInflater inflater = context.getLayoutInflater();
            ViewHolder vh;
            if (convertView == null) {
                vh = new ViewHolder();
                row = inflater.inflate(R.layout.row_item, null, true);

                        vh.textViewId = (TextView) row.findViewById(R.id.textViewId);
                        vh.textViewFirstName = (TextView) row.findViewById(R.id.textViewFirstName);
                        vh.textViewEmail = (TextView) row.findViewById(R.id.textViewEmail);
                        vh.bookButton = (Button) row.findViewById(R.id.edit);

                // store the holder with the view.
                row.setTag(vh);
            } else {

                    vh = (ViewHolder) convertView.getTag();

            }

            vh.textViewFirstName.setText(users.get(position).getFirstName());
            vh.textViewId.setText("" + users.get(position).getId());
            vh.textViewEmail.setText("" + users.get(position).getEmail());
            final int positionPopup = position;
            vh.bookButton.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {

                    Log.d("Save: ", "" + positionPopup);
//                    editPopup(positionPopup);
                }
            });

        return  row;
    }

    public long getItemId(int position) {
        return position;
    }

    public Object getItem(int position) {
        return position;
    }

    public int getCount() {
        return users.size();
    }

}
