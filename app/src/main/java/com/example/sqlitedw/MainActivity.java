package com.example.sqlitedw;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;

import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import android.os.StrictMode;
import android.text.TextUtils;
import android.util.Log;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ListView;
import android.widget.Spinner;
import android.widget.Toast;


import java.time.LocalDate;
import java.time.LocalTime;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
import java.util.List;

import javax.mail.Message;
import javax.mail.MessagingException;
import javax.mail.PasswordAuthentication;
import javax.mail.Session;
import javax.mail.Transport;
import javax.mail.internet.InternetAddress;
import javax.mail.internet.MimeMessage;
import java.util.Properties;

public class MainActivity extends AppCompatActivity {

    //zmienne
    Button btn_insert;
    EditText text_name;
    EditText text_name2;
    Spinner spinnerv;
    Spinner spinnerv1;
    Spinner spinnerv2;
    Spinner spinnerv3;
    String text;
    String text1;
    String text2;
    String text3;
    String text_info;
    int cena=0;
    Intent i;
    Intent i2;
    AlertDialog.Builder builder;
    AlertDialog.Builder builderaut;






    public void getProd() {
        //data
        spinnerv = (Spinner) findViewById(R.id.spinner);
        spinnerv1 = (Spinner) findViewById(R.id.spinner1);
        spinnerv2 = (Spinner) findViewById(R.id.spinner2);
        spinnerv3 = (Spinner) findViewById(R.id.spinner3);

        //string
        text = spinnerv.getSelectedItem().toString();
        text1 = spinnerv1.getSelectedItem().toString();
        text2 = spinnerv2.getSelectedItem().toString();
        text3 = spinnerv3.getSelectedItem().toString();
        text_name = (EditText) findViewById(R.id.editTextName);
        text_name2 = (EditText) findViewById(R.id.editTextName2);

    }

    public boolean emptyif() {
        //czy pusty
        getProd();
        if (text_name.getText().toString().trim().equals("") || text_name2.getText().toString().trim().equals("")) {
        return true;
        }else{return false;}

    }

    public void cennik() {
        i = new Intent(getApplicationContext(), MainActivity.class);
        getProd();
        //
        switch(text) {
            case "GTX1650":
                cena+=4000;
                break;
            case "RTX2060":
                cena+=5800;
                break;
            case "RTX3070Ti":
                cena+=9400;
                break;
            case "RTX3090":
                cena+=18000;
                break;
        }
        //
        switch(text1) {
            case "Klawiatura":
                text1="-";
                break;
            case "LogitechK120":
                cena+=100;
                break;
            case "LogitechK270":
                cena+=180;
                break;
            case "LogitechK400":
                cena+=220;
                break;
        }
        //
        switch(text2) {
            case "Myszka":
                text2="-";
                break;
            case "LogitechM650":
                cena+=100;
                break;
            case "LogitechM650L":
                cena+=140;
                break;
        }
        //
        switch(text3) {
            case "Monitor":
                text3="-";
                break;
            case "AcerVG270Sbmiipx":
                cena+=1000;
                break;
            case "AcerVG271UPbmiipx":
                cena+=1400;
                break;
        }

    }




    @Override
    protected void onCreate(Bundle savedInstanceState) {

        DataBaseHelper mydb;

        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);


        //spinner
        final List<String> states = Arrays.asList("GTX1650", "RTX2060", "RTX3070Ti", "RTX3090");
        final Spinner spinner = findViewById(R.id.spinner);
        //spinner1
        final List<String> states1 = Arrays.asList("Klawiatura", "LogitechK120",
                "LogitechK270", "LogitechK400");
        final Spinner spinner1 = findViewById(R.id.spinner1);
        //spinner2
        final List<String> states2 = Arrays.asList("Myszka", "LogitechM650", "LogitechM650L");
        final Spinner spinner2 = findViewById(R.id.spinner2);

        //spinner3
        final List<String> states3 = Arrays.asList("Monitor", "AcerVG270Sbmiipx", "AcerVG271UPbmiipx");
        final Spinner spinner3 = findViewById(R.id.spinner3);

        //Spinner
        com.example.sqlitedw.SpinnerAdapter adapter = new SpinnerAdapter(getApplicationContext(), states);
        adapter.setDropDownViewResource(R.layout.my_dropdown_item);

        spinner.setAdapter(adapter);


        //Spinner1
        com.example.sqlitedw.SpinnerAdapter adapter1 = new SpinnerAdapter(getApplicationContext()
                , states1);
        adapter1.setDropDownViewResource(R.layout.my_dropdown_item);

        spinner1.setAdapter(adapter1);

        //Spinner2
        com.example.sqlitedw.SpinnerAdapter adapter2 = new SpinnerAdapter(getApplicationContext()
                , states2);
        adapter2.setDropDownViewResource(R.layout.my_dropdown_item);

        spinner2.setAdapter(adapter2);

        //Spinner3
        com.example.sqlitedw.SpinnerAdapter adapter3 = new SpinnerAdapter(getApplicationContext()
                , states3);
        adapter3.setDropDownViewResource(R.layout.my_dropdown_item);

        spinner3.setAdapter(adapter3);

        //log
        Log.i("spinnerdata",  text+" "+text1+" "+text2+" "+text3);
        Log.i("spinnerdata1",  "EEEEEEE ");
        //db
        mydb=new DataBaseHelper(this);


        //
        btn_insert=(Button)findViewById(R.id.btn_insert);


        //alert
        builder = new AlertDialog.Builder(this)
                .setTitle("Błąd")
                .setMessage("coś sie popsuło")

                .setPositiveButton("Potwierdzam", new DialogInterface.OnClickListener() {
                    public void onClick(DialogInterface dialog, int id) {
                        //refresh
                        Toast.makeText(MainActivity.this, "Zamówienie złożone", Toast.LENGTH_SHORT).show();
                        startActivity(i);
                    }
                });

        //alertautor
        builderaut = new AlertDialog.Builder(this)
                .setTitle("Autor: ")
                .setMessage("Dawid Wilemski 4P")

                .setPositiveButton("Epicko", new DialogInterface.OnClickListener() {
                    public void onClick(DialogInterface dialog, int id) {
                    }
                });

        btn_insert.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v){

                if(emptyif()){

                    getProd();
                    cennik();
                    builder.setMessage("Prosimy o wypełnienie wszystkich wymaganych danych.");
                    builder.setPositiveButton("OK", new DialogInterface.OnClickListener() {
                        public void onClick(DialogInterface dialog, int id) {
                            //refresh
                            startActivity(i);
                        }
                    });
                    builder.show();
                    text_info = text + " " + text1 + " " + text2 + " " + text3;

                }else{

                    getProd();
                    cennik();
                    builder.setTitle("Zamówienie");
                    builder.setMessage("Komputer z kartą: " + text + "\nKlawiatura: " + text1 + "\nMyszka: " + text2 + "\nMonitor: " + text3 + "\nKoszt: " + cena + "zł");
                    builder.show();
                    builder.setPositiveButton("OK", new DialogInterface.OnClickListener() {
                        public void onClick(DialogInterface dialog, int id) {
                            //refresh
                            Toast.makeText(MainActivity.this, "Zamówienie złożone", Toast.LENGTH_SHORT).show();
                            startActivity(i);
                        }
                    });
                    text_info = text + " " + text1 + " " + text2 + " " + text3;
                    //
                    boolean isinserted = mydb.insertdata(text_name.getText().toString(), text_info, cena, text_name2.getText().toString());
                    //if (isinserted == true){}


                    //mail
                    new Thread() {
                        public void run() {
                            try {
                                //proszę się nie włamywać na maila
                                Sender sender = new Sender("gofrywujkabena@gmail.com", "GofryWujkaBenaSpZoo");
                                sender.sendMail("Dziękujemy za złożenie zamówienia "+ LocalDate.now()+" "+ LocalTime.now(),
                                        text_name2.getText().toString()+", oto twoje zamówienie: Komputer z kartą: " + text + " Klawiatura: " + text1 + " Myszka: " + text2 + " Monitor: " + text3 + " Koszt: " + cena + "zł",
                                        "gofrywujkabena@gmail.com",
                                        text_name.getText().toString());
                                Log.i("SendMail", "Email sent");
                            } catch (Exception e) {
                                Log.e("SendMail", e.getMessage(), e);
                            }
                        }
                    }.start();
                    //mailend

                }
            }
        });
    }

    //menu
    @Override
    public boolean onCreateOptionsMenu(Menu menu){
        MenuInflater inflater = getMenuInflater();
        inflater.inflate(R.menu.menu, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(@NonNull MenuItem item) {
        switch (item.getItemId()){
            case R.id.it1:
               builderaut.show();
               break;
            case R.id.it2:
                i2 = new Intent(getApplicationContext(), MainActivityList.class);
                startActivity(i2);
                break;
        }
        return super.onOptionsItemSelected(item);

    }
}