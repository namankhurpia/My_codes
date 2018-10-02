package kapoor.sachin.mydatabaseapp5;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;
import android.widget.TextView;

public class MainActivity extends AppCompatActivity {

    public static MyDBHelper dbHelper;
    private EditText ed1,ed2,ed3;
    private TextView tv;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        dbHelper=new MyDBHelper(this);
        ed1=(EditText)findViewById(R.id.ed1);
        ed2=(EditText)findViewById(R.id.ed2);
        ed3=(EditText)findViewById(R.id.ed3);
        tv=(TextView)findViewById(R.id.tv4);

    }

    public void addRec(View v)
    {
        int id= Integer.parseInt(ed1.getText().toString());
        String name=ed2.getText().toString();
        String phone=ed3.getText().toString();

        Contact obj=new Contact();
        obj.setId(id);
        obj.setName(name);
        obj.setPhNum(phone);
        boolean result=dbHelper.addContact(obj);
        if(result)
        {
            tv.setText("Record inserted");
        }
        else
        {
            tv.setText("Record insertion failed");
        }
    }
    public void dispRec(View v)
    {
        Intent i=new Intent(this,DisplayRecords.class);
        //Intent i=new Intent(this,MyCursorActivity.class);
        startActivity(i);

    }


}
