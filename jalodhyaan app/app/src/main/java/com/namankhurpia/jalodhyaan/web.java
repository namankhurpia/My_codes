package com.namankhurpia.jalodhyaan;

import android.app.Activity;
import android.app.NotificationManager;
import android.app.PendingIntent;
import android.content.Context;
import android.content.Intent;
import android.support.v4.app.NotificationCompat;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.webkit.WebResourceRequest;
import android.webkit.WebView;
import android.webkit.WebViewClient;
import android.widget.TextView;
import android.widget.Toast;

import com.firebase.client.Firebase;
import com.firebase.client.FirebaseError;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

public class web extends Activity {

    private WebView webv;

    private Firebase mrootref;
    private TextView txt;



    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_web);
        webv = (WebView) findViewById(R.id.webv);
        webv.getSettings().setJavaScriptEnabled(true);
        webv.loadUrl("http://jalodhyaan.herokuapp.com/");





        mrootref = new Firebase("https://projectjalodhyaan.firebaseio.com/SJT/TAP1/tap");
            fetch();
        webv.setWebViewClient(new WebViewClient() {
            @Override
            public boolean shouldOverrideUrlLoading(WebView view, WebResourceRequest request) {
                view.loadUrl(request.getUrl().toString());
                return false;
            }
        });






    }

    public void fetch()
    {
        mrootref.addValueEventListener(new com.firebase.client.ValueEventListener() {
            @Override
            public void onDataChange(com.firebase.client.DataSnapshot dataSnapshot) {

                String val=dataSnapshot.getValue(String.class).trim();

                if(val.equals("WaterWaste"))
                {
                    buildnotifications();
                    Toast.makeText(getApplicationContext(),"BUILDING NOTIFICATIONS",Toast.LENGTH_SHORT).show();
                }
                else
                {
                    //do nothing
                    Toast.makeText(getApplicationContext(),"NOTHING YET",Toast.LENGTH_SHORT).show();

                }


            }

            @Override
            public void onCancelled(FirebaseError firebaseError) {

            }
        });
    }

    private void buildnotifications() {

        NotificationManager notificationManager=(NotificationManager) getApplicationContext().getSystemService(Context.NOTIFICATION_SERVICE);
        Intent repeating=new Intent(this,web.class);

        PendingIntent pendingIntent=PendingIntent.getActivity(this,100,repeating,PendingIntent.FLAG_UPDATE_CURRENT);

        NotificationCompat.Builder builder=new NotificationCompat.Builder(this)
                .setContentIntent(pendingIntent)
                .setSmallIcon(R.drawable.common_google_signin_btn_icon_dark)
                .setContentTitle("Jalodhyaan")
                .setContentText(" Water waste in your home")
                .setAutoCancel(true);

        notificationManager.notify(100,builder.build());


    }










    /**
     * Called when the activity has detected the user's press of the back
     * key.  The default implementation simply finishes the current activity,
     * but you can override this to do whatever you want.
     */
    @Override
    public void onBackPressed() {
        return;
        //do nothing
    }
}
