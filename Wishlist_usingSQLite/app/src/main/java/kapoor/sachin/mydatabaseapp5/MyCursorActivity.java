package kapoor.sachin.mydatabaseapp5;

import android.content.Intent;
import android.database.Cursor;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ListView;
import android.widget.RelativeLayout;
import android.widget.SimpleCursorAdapter;
import android.widget.TextView;
import android.widget.Toast;

import static kapoor.sachin.mydatabaseapp5.MainActivity.dbHelper;

public class MyCursorActivity extends AppCompatActivity {

    private MyCursorAdapter dbAdapter;
    private ListView listView;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_my_cursor);


        dbAdapter=new MyCursorAdapter(this,MainActivity.dbHelper.getCursor());
        listView=(ListView)findViewById(R.id.listView);
        listView.setAdapter(dbAdapter);
        listView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> adapterView, View view, int i, long l) {
                RelativeLayout rl=(RelativeLayout)view;
                TextView tv=(TextView)rl.findViewById(R.id.textView1);
                String contactid=tv.getText().toString();
                Toast.makeText(MyCursorActivity.this,"You clicked on "+contactid,Toast.LENGTH_LONG).show();
                Bundle bdl=new Bundle();
                Intent intent=new Intent(MyCursorActivity.this,EditRecord.class);
                bdl.putString("contactid",contactid);
                intent.putExtras(bdl);
                startActivity(intent);
            }
        });
    }

    @Override
    protected void onRestart() {
        super.onRestart();
        Cursor cr= dbHelper.getCursor();
        dbAdapter.changeCursor(cr);
    }
}
