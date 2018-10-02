package kapoor.sachin.mydatabaseapp5;

import android.content.Intent;
import android.database.Cursor;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ListView;
import android.widget.RelativeLayout;
import android.widget.SimpleCursorAdapter;
import android.widget.TextView;
import android.widget.Toast;

import static android.R.attr.id;
import static kapoor.sachin.mydatabaseapp5.MainActivity.dbHelper;

public class DisplayRecords extends AppCompatActivity {


    private SimpleCursorAdapter dbAdapter;
    private ListView listView;
    private MyDBHelper dbHelper;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_display_records);

        int []viewId={R.id.textView1,R.id.textView2,R.id.textView3};
        dbHelper= new MyDBHelper(this);
        String[]colNames=dbHelper.getAllColums();
        dbAdapter=new SimpleCursorAdapter(this,R.layout.simplecursorrowlayout,dbHelper.getCursor(),colNames,viewId,0);
        listView=(ListView)findViewById(R.id.listView);
        listView.setAdapter(dbAdapter);
        listView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> adapterView, View view, int i, long l) {

                TextView tv=(TextView)view.findViewById(R.id.textView1);
                String contactid=tv.getText().toString();
                Toast.makeText(DisplayRecords.this,"You clicked on "+contactid,Toast.LENGTH_LONG).show();
                Bundle bdl=new Bundle();
                Intent intent=new Intent(DisplayRecords.this,EditRecord.class);
                bdl.putString("contactid",contactid);
                intent.putExtras(bdl);
                startActivity(intent);
            }
        });
    }

    @Override
    protected void onRestart() {
        super.onRestart();
        Cursor cr=dbHelper.getCursor();
        dbAdapter.changeCursor(cr);
    }
}
