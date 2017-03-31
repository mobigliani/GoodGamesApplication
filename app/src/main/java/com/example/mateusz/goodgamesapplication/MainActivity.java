package com.example.mateusz.goodgamesapplication;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.CheckBox;
import android.widget.EditText;

public class MainActivity extends AppCompatActivity {

    public final static String EXTRA_MESSAGE = "com.example.mateusz.goodgamesapplication.MESSAGE";
    public final static String HARD_CHECKED = "com.example.mateusz.goodgamesapplication.HARD_CHECKED";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
    }

    /** */
    public void saveName(View view) {
        Intent intent = new Intent(this, DisplayImageActivity.class);

        EditText editText = (EditText) findViewById(R.id.edit_message);
        String message = editText.getText().toString();
        intent.putExtra(EXTRA_MESSAGE, message);

        CheckBox checkBox = (CheckBox)findViewById(R.id.check_hard);
        intent.putExtra(HARD_CHECKED, checkBox.isChecked());

        startActivity(intent);
    }
}
