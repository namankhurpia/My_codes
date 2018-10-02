package kapoor.sachin.mydatabaseapp5;

import android.content.Context;
import android.database.Cursor;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.CursorAdapter;
import android.widget.ImageView;
import android.widget.TextView;

/**
 * Created by Sachin on 18-01-2017.
 */

public class MyCursorAdapter extends CursorAdapter {
    private LayoutInflater inflater;
    public MyCursorAdapter(Context context, Cursor cursor){
        super(context,cursor,0);
        inflater=(LayoutInflater)context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
    }
    @Override
    public View newView(Context context, Cursor cursor, ViewGroup viewGroup) {
        View view=inflater.inflate(R.layout.simplecursorrowlayout,viewGroup,false);
        return view;
    }

    @Override
    public void bindView(View view, Context context, Cursor cursor) {
        TextView tv1=(TextView)view.findViewById(R.id.textView1);
        TextView tv2=(TextView)view.findViewById(R.id.textView2);
        TextView tv3=(TextView)view.findViewById(R.id.textView3);
        tv1.setText(String.valueOf(cursor.getInt(0)));
        tv2.setText(cursor.getString(1));
        tv3.setText(cursor.getString(2));
    }

}
