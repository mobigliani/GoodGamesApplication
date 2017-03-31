package com.example.mateusz.goodgamesapplication;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.CheckBox;
import android.widget.EditText;

public class MainActivity extends AppCompatActivity {

    public final static String OPTS_PLAYER_NAME = "PlayerName";
    public final static String OPTS_SYNC_IMAGES = "SyncImages";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
    }

    /** Go, play the game. */
    public void saveName(View view) {
        Intent intent = new Intent(this, DisplayImageActivity.class);

        EditText editText = (EditText) findViewById(R.id.edit_message);
        String message = editText.getText().toString();
        intent.putExtra(OPTS_PLAYER_NAME, message);

        CheckBox checkBox = (CheckBox)findViewById(R.id.check_hard);
        intent.putExtra(OPTS_SYNC_IMAGES, checkBox.isChecked());

        startActivity(intent);
    }
}
