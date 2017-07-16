package com.example.syncedme;

import android.content.Intent;
import android.content.SharedPreferences;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageButton;
import android.widget.TextView;
import android.widget.Toast;

import java.text.SimpleDateFormat;
import java.util.Date;

public class MainActivity extends AppCompatActivity {

    EditText inputMemoName;
    EditText inputMemo;

    ImageButton sendMailButton, sendSmsButton, takeCameraButton;

    String memoName;
    String memoText;
    String smsText;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        inputMemoName = (EditText) findViewById(R.id.inputMemoName);
        inputMemo = (EditText) findViewById(R.id.inputMemo);

        sendMailButton = (ImageButton) findViewById(R.id.sendMailButton);
        sendSmsButton = (ImageButton) findViewById(R.id.sendSmsButton);
        takeCameraButton = (ImageButton) findViewById(R.id.takeCameraButton);

        SharedPreferences preferences = getSharedPreferences("PREFS", 0);
        memoName = preferences.getString("memoName", "");
        memoText = preferences.getString("memoText", "");

        sendMailButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

              memoName = inputMemoName.getText().toString();
              memoText = inputMemo.getText().toString();

              SharedPreferences preferences = getSharedPreferences("PREFS", 0);
              /**preferences.Editor editor = preferences.edit();
              editor.putString(memoName,memoText);
              editor.commit(); **/
              preferences.edit().putString(memoName,memoText).commit();

                Date curDate = new Date();
                SimpleDateFormat format = new SimpleDateFormat("yyyy-MM-dd hh:mm:ss a");
                String dateToStr = format.format(curDate);

                /**
                 * Send email with app selector
                 */
                /*
                Intent i = new Intent(Intent.ACTION_SEND);
                i.setType("message/rfc822");
                i.setData(Uri.parse("mailto:"));
                i.putExtra(Intent.EXTRA_EMAIL  , new String[]{"goran.parapid@gmail.com"});
                i.putExtra(Intent.EXTRA_SUBJECT, "SyncedMe memo: " + memoName + " at " + dateToStr);
                i.putExtra(Intent.EXTRA_TEXT   , memoText);

                try {
                    startActivity(Intent.createChooser(i, "Send mail..."));
                    Toast toast = Toast.makeText(MainActivity.this, "Memo sent to email!", Toast.LENGTH_LONG);
                    View popup = toast.getView();
                    popup.setBackgroundColor(0xA0A0A0);
                    toast.show();
                } catch (android.content.ActivityNotFoundException ex) {
                    Toast.makeText(MainActivity.this, "There are no email clients installed.", Toast.LENGTH_SHORT).show();
                }
                */
                

            }


        });

        sendSmsButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                memoName = inputMemoName.getText().toString();
                memoText = inputMemo.getText().toString();
                smsText = memoName + ": " + memoText;

            }
        });

        takeCameraButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                memoName = "";
                inputMemoName.setText(memoName);
                memoText = "";
                inputMemo.setText(memoText);
            }
        });

    }
}
