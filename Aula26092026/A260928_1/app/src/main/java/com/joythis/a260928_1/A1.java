package com.joythis.a260928_1;

import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.Toast;

import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;

public class A1 extends AppCompatActivity
implements View.OnClickListener /* requires us to write the onClick method */
{
    Button mBtnULC; // camel notation
    Button mBtnURC, mBtnLLC, mBtnLRC;

    // approach #4 - shared listener
    View.OnClickListener mBtnClickHandler =
    new View.OnClickListener() {
        @Override
        public void onClick(View view) {
            Button b = (Button)view;
            String s = b.getText().toString();
            String msg = "Hello from "+s+" via shared listener";
            Toast t = Toast.makeText(
                A1.this,
                msg,
                Toast.LENGTH_SHORT
            );
            t.show();
        }
    };


    /*
    There are 3 ways to set an object's behavior
    from (strictly) the Java code:
    - by interface/inheritance
    - by runtime exclusive objects
    - by runtime reusable object <==== our pattern
     */


    /*
    this method can be used from XML
    just declare a Button with
    android:onClick = "respondToTheClick"
    But beware that this is now deprecated
     */
    public void respondToTheClick(View pV){
        // empty
        Toast t = Toast.makeText(
            A1.this,
            "Hello!",
            Toast.LENGTH_SHORT
        );
        t.show();
    }




    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        //EdgeToEdge.enable(this);


        // this is the constraintLayout
        // setContentView(R.layout.activity_a1);

        // this other sets the RelativeLayout
        // setContentView(R.layout.rl_activity_a1);

        // this other sets the RelativeLayout
        setContentView(R.layout.ll_activity_a1);

        /*
        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main), (v, insets) -> {
            Insets systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars());
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom);
            return insets;
        });

         */

        // value the relevant data members here
        // and take care of their behaviors
        // right here
        // OR
        // act with our own pattern "init"
        init();
    }

    // our pattern!
    void init(){
        // 1 - data members initialization
        // a associação entre uma var (membro de dados)
        // Java e um objeto XML
        // the binding between a Java var (a data member)
        // and an XML object
        mBtnULC = findViewById(R.id.id_btn_ulc);
        mBtnURC = findViewById(R.id.id_btn_urc);
        mBtnLLC = findViewById(R.id.id_btn_llc);
        mBtnLRC = findViewById(R.id.id_btn_lrc);

        // approach #3 - exclusive inner objects
        // advantage - error isolation
        // disadvantage - lenghty code, repetition,
        /*
        mBtnULC.setOnClickListener
        (new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                // code for the behavior of ULC (alone)
                // EXCLUSIVE
                // TODO
            }
        });

        mBtnURC.setOnClickListener
        (new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                // code for the behavior of URC (alone)
                // EXCLUSIVE
                // TODO
            }
        });

        mBtnLLC.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                // code for the behavior of LLC (alone)
                // EXCLUSIVE
                // TODO
            }
        });

        mBtnLRC.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                // code for the behavior of LRC (alone)
                // EXCLUSIVE
                // TODO
            }
        });

         */

        // 2 - behavior binding
        Button[] allWithTheSameBehavior = {
            mBtnULC, mBtnURC,
            mBtnLLC, mBtnLRC
        };

        for (Button b:allWithTheSameBehavior)
            // behavior technique #2 - via implements
            b.setOnClickListener(
                mBtnClickHandler // approach #4 - shared listener
                // A1.this // approach #2 - implements
            );

        /*
        mBtnULC.setOnClickListener(
            // A1.this // the behavior is provided the by current instance of the A1 class
            this
        );

         */
    }//init

    @Override
    public void onClick(View view) {
        Button btnClicked = (Button)view;
        String textOfTheClickedButton =
            btnClicked.getText().toString();

        String msg = "hello from "+
                textOfTheClickedButton;

        Toast t = Toast.makeText(
            A1.this,
            msg,
            Toast.LENGTH_LONG
        );
        t.show();
    }//onClick
}