package com.hungrycollegekids.login_activity;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;
import android.content.Intent;
import android.widget.EditText;

import com.hungrycollegekids.listmenu_activity.ListMenuActivity;

public class LoginActivity extends AppCompatActivity {
    public static final String EXTRA_MESSAGE = "com.com.hungrycollegekids.listmenu_activity.MESSAGE";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);


    }

    public void submitLoginCredentials(View view) {
        Intent intent = new Intent(this, ListMenuActivity.class);
        EditText editText = (EditText) findViewById(R.id.editText);
        String message = editText.getText().toString();
        intent.putExtra(EXTRA_MESSAGE, message);
        startActivity(intent);
    }
}
