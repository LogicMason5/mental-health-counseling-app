package com.siddiqui.mentalhealthapp;

import androidx.appcompat.app.AppCompatActivity;

import android.app.PendingIntent;
import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.content.IntentFilter;
import android.os.Bundle;
import android.telephony.SmsManager;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

public class ChatroomActivity extends AppCompatActivity {

    private Button btnSend;
    private EditText tvMessage, tvNumber;
    private IntentFilter intentFilter;

    // Broadcast receiver for incoming messages
    private final BroadcastReceiver intentReceiver = new BroadcastReceiver() {
        @Override
        public void onReceive(Context context, Intent intent) {
            TextView inTxt = findViewById(R.id.textMsg);
            String current = inTxt.getText().toString();

            if (intent.getExtras() != null) {
                String msg = intent.getExtras().getString("message", "");
                current += msg + "\n";
                inTxt.setText(current);
            }
        }
    };

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_chatroom);

        initViews();
        setupListeners();
        setupIntentFilter();
    }

    private void initViews() {
        btnSend = findViewById(R.id.btnSend);
        tvMessage = findViewById(R.id.tvMessage);
        tvNumber = findViewById(R.id.tvNumber);
    }

    private void setupListeners() {
        btnSend.setOnClickListener(v -> {
            String myMsg = tvMessage.getText().toString().trim();
            String theNumber = tvNumber.getText().toString().trim();

            if (!myMsg.isEmpty() && !theNumber.isEmpty()) {
                sendMsg(theNumber, myMsg);
                appendMessageToChat(theNumber + ": " + myMsg);
                tvMessage.setText("");
            }
        });
    }

    private void setupIntentFilter() {
        intentFilter = new IntentFilter();
        intentFilter.addAction("SMS_RECEIVED_ACTION");
    }

    private void appendMessageToChat(String message) {
        TextView inTxt = findViewById(R.id.textMsg);
        String current = inTxt.getText().toString();
        inTxt.setText(current + message + "\n\n");
    }

    private void sendMsg(String theNumber, String myMsg) {
        final String SENT = "Message Sent";
        final String DELIVERED = "Message Delivered";

        PendingIntent sentPI = PendingIntent.getBroadcast(
                this,
                0,
                new Intent(SENT),
                PendingIntent.FLAG_IMMUTABLE
        );

        PendingIntent deliveredPI = PendingIntent.getBroadcast(
                this,
                0,
                new Intent(DELIVERED),
                PendingIntent.FLAG_IMMUTABLE
        );

        SmsManager sms = SmsManager.getDefault();

        // send to actual entered number
        sms.sendTextMessage(theNumber, null, myMsg, sentPI, deliveredPI);

        // Your original overridden hard-coded test line:
        // sms.sendTextMessage("3653382903", null, "user 2: " + myMsg, sentPI, deliveredPI);
    }

    @Override
    protected void onResume() {
        super.onResume();
        registerReceiver(intentReceiver, intentFilter);
    }

    @Override
    protected void onPause() {
        super.onPause();
        unregisterReceiver(intentReceiver);
    }
}
