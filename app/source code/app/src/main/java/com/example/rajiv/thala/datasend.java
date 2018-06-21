package com.example.rajiv.thala;

import android.content.Intent;
import android.os.AsyncTask;
import android.os.Bundle;
import android.os.StrictMode;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.View;
import android.widget.EditText;
import android.widget.TextView;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.io.PrintWriter;
import java.net.InetAddress;
import java.net.Socket;
import java.net.UnknownHostException;

public class datasend extends AppCompatActivity {
    Socket socket;
    public static String newLine = System.getProperty("line.separator");
    EditText datasend;
    public static boolean connected=false;
    boolean result=true;
    TextView data;
    boolean addrset=false;
    boolean exit=false;
    EditText ip;
    String ipaddr;
    public static PrintWriter out=null;
    public static BufferedReader red=null;
    TextView r;
    ConnectPhone x;
            ;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        ip=(EditText) findViewById(R.id.ip);
        datasend=(EditText) findViewById(R.id.datatext);
        data=(TextView) findViewById(R.id.data);
        StrictMode.ThreadPolicy policy = new StrictMode.ThreadPolicy.Builder().permitAll().build();
        StrictMode.setThreadPolicy(policy);
        String t="";
        /*while (!exit) {
            if(connected) {
                try {
                    t = red.readLine();
                    if (t != "")
                        Log.i("inp", t);
                    t = "";
                } catch (IOException e) {
                    Log.i("nas", "kk");
                    e.printStackTrace();
                }
            }
        }*/
        Log.i("connectivity","connec");





    }
/*    public void endcon(View view)
    {
        exit=true;
        out.println("exit");
    }*/
    public void ipenter(View view)
    {
        //ipaddr=ip.getText().toString();
        ipaddr="192.168.43.18";
        Log.i("ip",ipaddr);
       addrset=true;
      x=new ConnectPhone();
       x.execute();
        Intent i = new Intent(this,startexploring.class);
        startActivity(i);
    }
  /*  public void senddata(View view)
    {
        if(connected) {
            out.println(datasend.getText().toString());
            Log.i(datasend.getText().toString(),"sent");
            datasend.setText("");
        }
    }*/
    public class ConnectPhone extends AsyncTask<Void, Void, Boolean> {


        @Override
        protected Boolean doInBackground(Void... params) {

            try {
                Log.i(InetAddress.getLocalHost()+"","test");
            } catch (UnknownHostException e) {
                e.printStackTrace();
            }

            try {

                socket = new Socket(ipaddr, 8006);




            } catch (IOException e) {
                result=false;
                e.printStackTrace();

            }
            if(result) {
                try {
                    out = new PrintWriter(new BufferedWriter(new OutputStreamWriter(socket.getOutputStream(),"utf-8")), true);
                } catch (IOException e) {
                    e.printStackTrace();
                }
                try {
                    red = new BufferedReader(new InputStreamReader(socket.getInputStream()));
                    Log.i(socket.getInputStream() + "", "istream"+red.toString());
                } catch (IOException e) {
                    e.printStackTrace();
                }
                connected = true;

                Log.i("connectivity","connec");

            }


            return result;
        }


       // @SuppressLint("SetTextI18n")
        @Override
        protected void onPostExecute(Boolean result) {
       /*     Log.i("res:",result+"");
            String t = "";
            try {
                t = red.readLine();
                if (t != "") {
                    Log.i("inp", t);
                    data.setText(t);
                    t=red.readLine();
                    data.setText(t);
                    Log.i("inp2",t);
                }
                t = "";
            } catch (IOException e) {
                Log.i("nas", "kk");
                e.printStackTrace();
            }*/

            finish();

              /*  try {
                    Log.i(red.readLine(),"its");
                } catch (IOException e) {

                }*/
            }





        }
    }

