package com.namankhurpia.animation_3_image_profile;

import android.app.ActivityOptions;
import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Pair;
import android.view.View;
import android.view.WindowManager;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.TextView;

public class profile extends AppCompatActivity {
    private ImageButton closebtbn;
    private ImageView profile_big;
    private TextView name;
    private TextView desc;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN,
                WindowManager.LayoutParams.FLAG_FULLSCREEN);

        setContentView(R.layout.activity_profile);

        closebtbn=(ImageButton)findViewById(R.id.closebtn);
        profile_big=(ImageView)findViewById(R.id.profile_big);
        name=(TextView)findViewById(R.id.nametext);
        desc=(TextView)findViewById(R.id.desc_text);

        closebtbn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                Intent back=new Intent(profile.this,MainActivity.class);

                Pair[] pair_two=new Pair[3];
                pair_two[0]=new Pair<View, String>(profile_big,"pic_transition");
                pair_two[1]=new Pair<View, String>(name,"name_transition");
                pair_two[2]=new Pair<View, String>(desc,"desc_transition");


                ActivityOptions option_two=ActivityOptions.makeSceneTransitionAnimation(profile.this,pair_two);

                startActivity(back,option_two.toBundle());

            }
        });


    }
}
