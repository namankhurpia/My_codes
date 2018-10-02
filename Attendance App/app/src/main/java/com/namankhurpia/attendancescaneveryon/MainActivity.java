package com.namankhurpia.attendancescaneveryon;

import android.app.Activity;
import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.firebase.client.Firebase;
import com.google.firebase.database.FirebaseDatabase;
import com.google.zxing.integration.android.IntentIntegrator;
import com.google.zxing.integration.android.IntentResult;

public class MainActivity extends AppCompatActivity {

    private EditText ed1;
    private Button btn;
private Firebase mrootref;
private String urlhere,regno;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);



        ed1=(EditText)findViewById(R.id.edittxt);
        btn=(Button)findViewById(R.id.btn);

        final Activity activity = this;

        btn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                regno=ed1.getText().toString();
                savevalue();        //saving by naman

                //calling the scanner services
                IntentIntegrator integrator = new IntentIntegrator(activity);
                integrator.setDesiredBarcodeFormats(IntentIntegrator.ALL_CODE_TYPES);
                integrator.setPrompt("Scan");
                integrator.setCameraId(0);
                integrator.setBeepEnabled(true);
                integrator.setBarcodeImageEnabled(false);
                integrator.initiateScan();


            }
        });

    }

    private void savevalue() {


    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        IntentResult result = IntentIntegrator.parseActivityResult(requestCode, resultCode, data);
        if(result != null){
            if(result.getContents()==null){
                Toast.makeText(this, "You cancelled the scanning", Toast.LENGTH_LONG).show();
            }
            else {
                //Toast.makeText(this, result.getContents(),Toast.LENGTH_LONG).show();

                urlhere=result.getContents().toString();



                 sendvalue();

            }
        }
        else {
            super.onActivityResult(requestCode, resultCode, data);
        }
    }

    private void sendvalue()
    {
        mrootref =new Firebase (urlhere);

        String key="present";

        Firebase childref=mrootref.child(regno);
        childref.setValue(key);

        Toast.makeText(this,regno+"MARKED PRESENT",Toast.LENGTH_LONG).show();

    }
}
