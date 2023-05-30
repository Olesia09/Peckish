package com.example.peckishv2.Backend;

import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;
import androidx.core.view.GravityCompat;
import androidx.drawerlayout.widget.DrawerLayout;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentTransaction;

import android.annotation.SuppressLint;
import android.app.Dialog;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.view.ViewGroup;
import android.widget.EditText;
import android.widget.ImageButton;
import android.widget.TextView;
import android.widget.Toast;


import com.example.peckishv2.R;
import com.example.peckishv2.Utils.LoginDBHelper;
import com.example.peckishv2.databinding.ActivityMainAppBinding;

public class MainApp extends AppCompatActivity {

    ActivityMainAppBinding binding;
    ImageButton user_profile, preferences, about_us, change_profile, logout, help_btn, change_details_submit;
    DrawerLayout drawerLayout;
    Toolbar tool_bar;
    Dialog about_us_dialog, change_profile_dialog, help_dialog;
    EditText change_username_txt, change_password_txt, change_email_txt, confirm_current_pass_txt;
    String newUsername, newPassword, newEmail, conform_pass;
    LoginDBHelper dbHelper;

    @SuppressLint("NonConstantResourceId")
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        dbHelper = new LoginDBHelper(this);

        //binding xml to java
        binding = ActivityMainAppBinding.inflate(getLayoutInflater());
        setContentView(binding.getRoot());
        replaceFragment(new HomePage());// on initial load, home page is loaded first

        // replacing fragments on icon click
        binding.navigation.setOnItemSelectedListener(item ->
        {
            switch (item.getItemId()) {

                case R.id.home_page:
                    replaceFragment(new HomePage());
                    break;
                case R.id.shopping_list:
                    replaceFragment(new ShoppingList());
                    break;
                case R.id.search_recipe:
                    replaceFragment(new SearchRecipe());
                    break;
            }
            return true;
        });

        // defining the variables
        drawerLayout = findViewById(R.id.drawer_layout);
        tool_bar = findViewById(R.id.toolbar);
        user_profile = findViewById(R.id.profile);
        preferences = findViewById(R.id.user_preference);
        about_us = findViewById(R.id.about_us);
        change_profile = findViewById(R.id.change_profile);
        logout = findViewById(R.id.log_out);
        help_btn = findViewById(R.id.help_btn);
        change_details_submit = findViewById(R.id.change_details_btn);
        change_username_txt = findViewById(R.id.change_username);
        change_password_txt = findViewById(R.id.change_password);
        change_email_txt = findViewById(R.id.change_email);
        confirm_current_pass_txt = findViewById(R.id.confirm_current_pass);


        //setting up the custom tool bar
        setSupportActionBar(tool_bar);

        //open drawer
        user_profile.setOnClickListener(v -> drawerLayout.openDrawer(GravityCompat.START));

        //options actions
        change_profile.setOnClickListener(v -> change_profile_dialog.show());
        preferences.setOnClickListener(v -> startActivity(new Intent(MainApp.this, Preferences.class)));
        about_us.setOnClickListener(v -> about_us_dialog.show());
        logout.setOnClickListener(v -> startActivity(new Intent(MainApp.this, Login.class)));
        help_btn.setOnClickListener(v -> help_dialog.show());

        //help dialog
        help_dialog = new Dialog(this);
        help_dialog.setContentView(R.layout.dialog_help);
        help_dialog.getWindow().setBackgroundDrawable(getDrawable(R.drawable.dialog_box));
        help_dialog.getWindow().setLayout(ViewGroup.LayoutParams.WRAP_CONTENT, ViewGroup.LayoutParams.WRAP_CONTENT);


        //edit profile dialog
        change_profile_dialog = new Dialog(this);
        change_profile_dialog.setContentView(R.layout.dialog_change_detials);
        change_profile_dialog.getWindow().setBackgroundDrawable(getDrawable(R.drawable.dialog_box_mainapp));
        change_profile_dialog.getWindow().setLayout(ViewGroup.LayoutParams.WRAP_CONTENT, ViewGroup.LayoutParams.WRAP_CONTENT);

        /*change_details_submit.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                newEmail = change_email_txt.getText().toString();
                newPassword = change_password_txt.getText().toString();
                newUsername = change_username_txt.getText().toString();
                conform_pass = confirm_current_pass_txt.getText().toString();

                updateDetails(newEmail, newPassword, newUsername, conform_pass);
            }
        });*/


        //about us dialog
        about_us_dialog = new Dialog(this);
        about_us_dialog.setContentView(R.layout.dialog_about_us);
        about_us_dialog.getWindow().setBackgroundDrawable(getDrawable(R.drawable.dialog_box_mainapp));
        about_us_dialog.getWindow().setLayout(ViewGroup.LayoutParams.WRAP_CONTENT, ViewGroup.LayoutParams.WRAP_CONTENT);

       /* about_us_back.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                about_us_dialog.dismiss();
            }
        });*/

        String userName = dbHelper.getUsername();

        View headerView = binding.drawer.getHeaderView(0);

        TextView userNameHeader = headerView.findViewById(R.id.user_name); // Replace R.id.user_name_textview with the actual ID of the view in the drawer header
        userNameHeader.setText(userName);

    }

    //closes in case of pressing on back arrow of the system
    @Override
    public void onBackPressed() {
        if (drawerLayout.isDrawerOpen(GravityCompat.START)) {
            drawerLayout.closeDrawer(GravityCompat.START);
        } else {
            super.onBackPressed();
        }
    }

    //replaces fragments
    private void replaceFragment(Fragment frag){

        FragmentManager fragmentManager = getSupportFragmentManager();
        FragmentTransaction fragmentTransaction = fragmentManager.beginTransaction();
        fragmentTransaction.replace(R.id.fragment_space,frag);
        fragmentTransaction.commit();
    }

     public void updateDetails(String email, String password, String username, String confirm_pass)
    {
        boolean update = dbHelper.updateDetails(username, password, email, confirm_pass);
        if (update)
        {
            Toast.makeText(MainApp.this,"Details Updated", Toast.LENGTH_SHORT).show();

            change_profile_dialog.dismiss();
        }
        else
        {
            Toast.makeText(MainApp.this,"Password incorrect", Toast.LENGTH_SHORT).show();
        }
    }
}