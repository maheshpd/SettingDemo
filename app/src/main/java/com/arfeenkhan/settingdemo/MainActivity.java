package com.arfeenkhan.settingdemo;

import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.preference.PreferenceManager;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.Snackbar;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.View;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.TextView;

public class MainActivity extends AppCompatActivity {

    public static final String PREF_DELETE_OLD_MESSAGE = "pref_delete_old_message";
    public static final String PREF_SMS_DELETE_LIMIT = "sms_delete_limit";
    public static final String PREF_MMS_DELETE_LIMIT = "mms_delete_limit";
    public static final String PREF_SMS_DELIVERY_REPORT = "pref_delivery_report_sms";
    public static final String PREF_MMS_DELIVERY_REPORT = "pref_delivery_report_mms";

    private TextView Txt_delete_old_messages, Txt_sms_delete_limit,Txt_mms_delete_limit,Txt_sms_delivery,
            Txt_mms_delivery;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);


        PreferenceManager.setDefaultValues(this,R.xml.preferences,false);

        Txt_delete_old_messages = findViewById(R.id.message_delete_status);
        Txt_sms_delete_limit = findViewById(R.id.sms_delete_limit);
        Txt_mms_delete_limit = findViewById(R.id.mms_delete_limit);
        Txt_sms_delivery = findViewById(R.id.sms_delivery_report);
        Txt_mms_delivery = findViewById(R.id.mms_delivery_report);
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_main, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        int id = item.getItemId();

        //noinspection SimplifiableIfStatement
        if (id == R.id.action_settings) {
            startActivity(new Intent(this, Setting.class));
            return true;

        }

        return super.onOptionsItemSelected(item);
    }

    public void readSettings(View view) {

        SharedPreferences sharedPreferences = PreferenceManager.getDefaultSharedPreferences(this);

        Txt_delete_old_messages.setText("Message Delete Status :"+String.valueOf(sharedPreferences.getBoolean(PREF_DELETE_OLD_MESSAGE,false)));
        Txt_sms_delete_limit.setText("SMS Delete Limit :"+sharedPreferences.getString(PREF_SMS_DELETE_LIMIT,"0"));
        Txt_mms_delete_limit.setText("MMS Delete Limit :"+sharedPreferences.getString(PREF_MMS_DELETE_LIMIT,"0"));

        Txt_sms_delivery.setText("SMS Delivery Report :"+String.valueOf(sharedPreferences.getBoolean(PREF_SMS_DELIVERY_REPORT,false)));
        Txt_mms_delivery.setText("MMS Delivery Report :"+String.valueOf(sharedPreferences.getBoolean(PREF_MMS_DELIVERY_REPORT,false)));
    }
}
