package com.namankhurpia.animation_3_image_profile;

import android.app.ActivityOptions;
import android.content.Intent;
import android.media.Image;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Pair;
import android.view.View;
import android.view.WindowManager;
import android.widget.ImageView;
import android.widget.RelativeLayout;
import android.widget.TextView;

public class MainActivity extends AppCompatActivity {

    private RelativeLayout relativeLayout;
    private ImageView profile_pic;
    private TextView name;
    private TextView desc;
    private TextView clicktoview;



    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN,
                WindowManager.LayoutParams.FLAG_FULLSCREEN);

        setContentView(R.layout.activity_main);

        relativeLayout=(RelativeLayout)findViewById(R.id.relativelayout);
        profile_pic=(ImageView)findViewById(R.id.profile_small);
        name=(TextView)findViewById(R.id.nametext);
        desc=(TextView)findViewById(R.id.desc_text);



    }


    public void click(View view) {

        Intent i=new Intent(MainActivity.this,profile.class);

        Pair[] pairs=new Pair[3];
        pairs[0]=new Pair<View, String>(profile_pic,"pic_transition");
        pairs[1]=new Pair<View, String>(name,"name_transition");
        pairs[2]=new Pair<View, String>(desc,"desc_transition");


        ActivityOptions option=ActivityOptions.makeSceneTransitionAnimation(MainActivity.this,pairs);

        startActivity(i,option.toBundle());
    }
}
