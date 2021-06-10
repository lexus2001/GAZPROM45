package com.example.gazprom45;

import androidx.appcompat.app.AppCompatActivity;
import androidx.core.app.ActivityCompat;

import android.Manifest;
import android.app.AlertDialog;
import android.app.Dialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.os.Bundle;
import android.os.StrictMode;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

public class MainActivity extends AppCompatActivity {
    TextView Login, Password,Textcounter;
    Button Authorization;
    private int counter = 5;
    private static String ip = "192.168.0.101";
    private static String port = "1433";
    private static String Classes = "net.sourceforge.jtds.jdbc.Driver";
    private static String database = "GAZPROM45";
    private static String username = "Tarantino009";
    private static String password = "123";
    private static String url = "jdbc:jtds:sqlserver://" + ip + ":" + port + "/" + database;
    private Connection connection = null;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        Login = (EditText) findViewById(R.id.TextLogin);
        Password = (EditText) findViewById(R.id.TextPassword);
        Authorization = (Button) findViewById(R.id.ButtonIN);



        ActivityCompat.requestPermissions(this, new String[]{Manifest.permission.INTERNET}, PackageManager.PERMISSION_GRANTED);

        StrictMode.ThreadPolicy policy = new StrictMode.ThreadPolicy.Builder().permitAll().build();
        StrictMode.setThreadPolicy(policy);

        try {
            Class.forName(Classes);
            connection = DriverManager.getConnection(url, username, password);

        } catch (ClassNotFoundException e) {
            e.printStackTrace();

        } catch (SQLException e) {
            e.printStackTrace();

        }
    }

    public void sqlButton(View view) throws SQLException {
        if (connection == null) {
            System.out.println("Connection fail");
        }

        if (!Login.getText().equals("") || !Password.getText().equals("")) {

            PreparedStatement ps = connection.prepareStatement("SELECT * FROM \"User\" where \"Login\" = ? AND \"Password\" = ?");
            ps.setString(1, String.valueOf(Login.getText()));
            ps.setString(2, String.valueOf((Password.getText())));
            ResultSet res = ps.executeQuery();




            if (res.next() && res != null) {
                Intent intent = new Intent(MainActivity.this, MainActivity2.class);
                startActivity(intent);

            } else {
                AlertDialog.Builder builder = new AlertDialog.Builder(MainActivity.this);
                builder.setMessage("Попробовать снова.")
                        .setCancelable(true)
                        .setPositiveButton("ОК", new DialogInterface.OnClickListener() {
                            @Override
                            public void onClick(DialogInterface dialog, int which) {
                                dialog.cancel();
                            }
                        });

                AlertDialog alert  = builder.create();
                alert.setTitle("Введены неверные данные!");
                alert.show();




            }



        }
    }

}
