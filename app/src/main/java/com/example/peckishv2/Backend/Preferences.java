package com.example.peckishv2.Backend;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.text.SpannableString;
import android.text.style.UnderlineSpan;
import android.view.View;
import android.widget.ImageButton;
import android.widget.TextView;

import com.example.peckishv2.R;

public class Preferences extends AppCompatActivity {

    ImageButton[] btns = new ImageButton[12];
    ImageButton next_btn;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_preferences);

        TextView skip = findViewById(R.id.skip_link);
        String text = skip.getText().toString();

        next_btn = findViewById(R.id.continue_btn);
        next_btn.setEnabled(false);

        // creating an underline
        SpannableString spannableString = new SpannableString(text);
        spannableString.setSpan(new UnderlineSpan(), 0, text.length(), 0);
        skip.setText(spannableString);



        // skip to home page without saving data
        skip.setOnClickListener(v -> startActivity(new Intent(Preferences.this, MainApp.class)));


        // getting imagebutton views
        btns[0] = findViewById(R.id.button1);
        btns[1] = findViewById(R.id.button2);
        btns[2] = findViewById(R.id.button3);
        btns[3] = findViewById(R.id.button4);
        btns[4] = findViewById(R.id.button5);
        btns[5] = findViewById(R.id.button6);
        btns[6] = findViewById(R.id.button7);
        btns[7] = findViewById(R.id.button8);
        btns[8] = findViewById(R.id.button9);
        btns[9] = findViewById(R.id.button10);
        btns[10] = findViewById(R.id.button11);
        btns[11] = findViewById(R.id.button12);

        for (final ImageButton btn : btns)
        {
            btn.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    toggleBtn(btn, next_btn);
                }
            });
        }

        //go to home page
        next_btn.setOnClickListener(v -> startActivity(new Intent(Preferences.this, MainApp.class)));
    }

    // change button background depending on selection
    private void toggleBtn(ImageButton btn, ImageButton next_btn)
    {
        btn.setSelected(!btn.isSelected());

        if(btn.isSelected())
        {
            btn.setBackground(getDrawable(R.drawable.selected_item_background));
            btn.setElevation(0);
            next_btn.setEnabled(true);
        }
        else
        {
            btn.setBackground((getDrawable(R.drawable.deselected_item_background)));
            btn.setElevation(15);
            checkEnableBtn(next_btn);
        }
    }

    //check if at least 1 button is selected
    private void checkEnableBtn(ImageButton next_btn) {
        boolean btnSelected = false;

        for (ImageButton btn : btns) {
            if (btn.isSelected()) {
                btnSelected = true;
                break;
            }
        }
        next_btn.setEnabled(btnSelected);
    }
}