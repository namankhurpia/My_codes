package kapoor.sachin.mydatabaseapp5;

import android.content.ContentValues;
import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;
import android.widget.Toast;

import static java.lang.Integer.parseInt;

public class EditRecord extends AppCompatActivity {

    EditText edtContact,edtName,edtPhone;
    int cont_id;
    private MyDBHelper dbHelper;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_edit_record);
        Intent i=getIntent();
        Bundle b=i.getExtras();
        String contactid=b.getString("contactid");
        cont_id= parseInt(contactid);
        edtContact=(EditText)findViewById(R.id.edtContact);

        edtName=(EditText)findViewById(R.id.edtName);
        edtPhone=(EditText)findViewById(R.id.edtPhone);
        dbHelper=new MyDBHelper(this);
        fillRec();


    }
    public void fillRec()
    {
        Contact obj=dbHelper.findContact(cont_id);

        edtContact.setText(String.valueOf(obj.getId()));
        edtName.setText(obj.getName());
        edtPhone.setText(obj.getPhNum());

    }
    public void updRec(View v)
    {
        Contact newContact=new Contact();
        newContact.setId(parseInt(edtContact.getText().toString()));
        newContact.setName(edtName.getText().toString());
        newContact.setPhNum(edtPhone.getText().toString());
        boolean result=dbHelper.updateContact(newContact);
        String message="";
        if(result)
            message="Record Updated!";
        else
            message="Could Not Update The Record!";
        Toast.makeText(this,message,Toast.LENGTH_LONG).show();
    }
    public void delRec(View v)
    {
        int id=Integer.parseInt(edtContact.getText().toString());
        boolean result=MainActivity.dbHelper.deleteContact(id);
        String message="";
        if(result)
            message="Record Deleted!";
        else
            message="Could Not Delete The Record!";
        Toast.makeText(this,message,Toast.LENGTH_LONG).show();
        findViewById(R.id.btn4).setClickable(false);
    }
}
