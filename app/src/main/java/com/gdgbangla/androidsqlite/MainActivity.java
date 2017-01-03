package com.gdgbangla.androidsqlite;

import android.database.Cursor;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

public class MainActivity extends AppCompatActivity {
    DatabaseHelper myDB;
    EditText etName;
    EditText etEmail;
    EditText etCheck;
    TextView tvData;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        myDB = new DatabaseHelper(this);
        etName=(EditText)findViewById(R.id.name);
        etEmail=(EditText)findViewById(R.id.email);
        etCheck=(EditText)findViewById(R.id.checkValue);
        tvData=(TextView)findViewById(R.id.tvData);

    }

    public void save(View view) {
        boolean isInserted =  myDB.insertData(etName.getText().toString(), etEmail.getText().toString());

        if(isInserted == true)
            Toast.makeText(MainActivity.this, "Data Inserted", Toast.LENGTH_LONG).show();
        else
            Toast.makeText(MainActivity.this, "Data Insert failed", Toast.LENGTH_LONG).show();
    }

    public void view(View view) {
        Cursor res = myDB.getAllData();
        if(res.getCount() == 0){

            Toast.makeText(MainActivity.this, "No data found", Toast.LENGTH_LONG).show();

            return;
        }else {

            StringBuffer buffer = new StringBuffer();

            while (res.moveToNext()){
                buffer.append("Id: " + res.getString(0) + "\n");
                buffer.append("Name: " + res.getString(1) + "\n");
                buffer.append("Email: " + res.getString(2) + "\n \n");

            }

            tvData.setText(buffer.toString());
        }
    }

    public void check(View view) {
        etCheck=(EditText)findViewById(R.id.checkValue);
        Cursor res = myDB.getOneValue(etCheck.getText().toString());
        if(res.getCount() == 0){
            Toast.makeText(MainActivity.this, "No data found", Toast.LENGTH_LONG).show();
            return;
        }else {
            Toast.makeText(MainActivity.this, "data found", Toast.LENGTH_LONG).show();
        }
    }
}
