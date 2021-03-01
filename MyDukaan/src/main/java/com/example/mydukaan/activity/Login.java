package com.example.mydukaan.activity;

import android.app.ProgressDialog;
import android.content.Context;
import android.content.Intent;
import android.net.ConnectivityManager;
import android.net.NetworkInfo;
import android.os.Bundle;
import android.text.method.HideReturnsTransformationMethod;
import android.text.method.PasswordTransformationMethod;
import android.util.Log;
import android.view.View;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.cardview.widget.CardView;

import com.example.mydukaan.R;
import com.example.mydukaan.modal.SignIn.ChatAgentLogin;
import com.example.mydukaan.service.ApiClient;
import com.example.mydukaan.service.RecyclerInterface;
import com.example.mydukaan.service.SessionManager;
import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.FirebaseApp;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.iid.FirebaseInstanceId;
import com.google.firebase.messaging.FirebaseMessaging;
import com.google.gson.Gson;
import com.thekhaeng.pushdownanim.PushDownAnim;

import retrofit2.Call;
import retrofit2.Callback;

public class Login extends AppCompatActivity implements View.OnClickListener {

    TextView txt_signup, txt_forget_password;
    CardView crd_yallar, crd_login;
    EditText edt_email, edt_pass;
    RecyclerInterface apiInterface;
    String lengueasge = "eng";
    ImageView edit_pass_visibale, img_appicon, btn_edit;
    boolean check = true;
    final String emailPattern = "[\\u0621-\\u064Aa-zA-Z0-9._-]+@[\\u0621-\\u064Aa-zA-Z0-9._-]+\\.+[\\u0621-\\u064Aa-z]+";
    SessionManager sessionManager;
    FirebaseAuth auth;
    private static final String TAG = "Login";


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);


        /* --------------------------------find Id--------------------------------*/
        txt_signup = (TextView) findViewById(R.id.txt_signup);
        txt_forget_password = (TextView) findViewById(R.id.txt_forget_password);
        crd_yallar = (CardView) findViewById(R.id.crd_yallar);
        crd_login = (CardView) findViewById(R.id.crd_login);
        edt_email = (EditText) findViewById(R.id.edt_email);
        edt_pass = (EditText) findViewById(R.id.edt_pass);
        edit_pass_visibale = (ImageView) findViewById(R.id.edit_pass_visibale);
        img_appicon = (ImageView) findViewById(R.id.img_appicon);
        btn_edit = (ImageView) findViewById(R.id.btn_edit);
        /* --------------------------------***********--------------------------------*/
        apiInterface = ApiClient.getClient().create(RecyclerInterface.class);





        sessionManager = new SessionManager();
        if (sessionManager.getPreferences(Login.this, "LoginTest") != null && sessionManager.getPreferences(Login.this, "LoginTest").equalsIgnoreCase("true")) {
            Intent i = new Intent(Login.this, Home.class);
            startActivity(i);
            finish();
            overridePendingTransition(R.anim.activity_back_in, R.anim.activity_back_out);
        } else {

        }


        PushDownAnim.setPushDownAnimTo(crd_yallar).setOnClickListener(this);
        txt_signup.setOnClickListener(this);
        PushDownAnim.setPushDownAnimTo(crd_login).setOnClickListener(this);
        edit_pass_visibale.setOnClickListener(this);
        txt_forget_password.setOnClickListener(this);


    }

    @Override
    public void onClick(View view) {
        if (view == txt_forget_password) {

          /*  Intent i = new Intent(SingIn.this, Forgetpassword.class);

            startActivity(i);
            overridePendingTransition(R.anim.activity_back_in, R.anim.activity_back_out);
*/


        } else if (view == txt_signup) {
          /*  Intent i = new Intent(SingIn.this, SingUp.class);
            startActivity(i);
            overridePendingTransition(R.anim.activity_back_in, R.anim.activity_back_out);*/

        } else if (view == crd_yallar) {
          /*  Intent i = new Intent(Login.this, Home.class);
            startActivity(i);
            overridePendingTransition(R.anim.activity_back_in, R.anim.activity_back_out);*/
        } else if (view == edit_pass_visibale) {

            if (check == true) {

                edt_pass.setTransformationMethod(HideReturnsTransformationMethod.getInstance());
                check = false;
            } else {

                edt_pass.setTransformationMethod(PasswordTransformationMethod.getInstance());
                check = true;
            }
        } else if (view == crd_login) {
            String email = edt_email.getText().toString();
            String password = edt_pass.getText().toString();
            if (email.isEmpty()) {
                edt_email.setError(getResources().getText(R.string.Empty));
            } else if (password.isEmpty()) {
                edt_pass.setError(getResources().getText(R.string.Empty));
            } else {
                if (haveNetworkConnection()) {
                    if (email.matches(emailPattern)) {
                        SingIn(email, password);

                    } else {
                        Toast.makeText(Login.this, getResources().getText(R.string.Email_have_must_contain), Toast.LENGTH_SHORT).show();
                    }


                } else {

                    Toast.makeText(Login.this, getResources().getText(R.string.Please_Check_Internet_Connection), Toast.LENGTH_SHORT).show();
                }
            }
        }
    }

    private boolean haveNetworkConnection() {
        boolean haveConnectedWifi = false;
        boolean haveConnectedMobile = false;

        ConnectivityManager cm = (ConnectivityManager) this.getSystemService(Context.CONNECTIVITY_SERVICE);
        NetworkInfo[] netInfo = cm.getAllNetworkInfo();

        for (NetworkInfo ni : netInfo) {
            if (ni.getTypeName().equalsIgnoreCase("WIFI"))
                if (ni.isConnected())
                    haveConnectedWifi = true;
            if (ni.getTypeName().equalsIgnoreCase("MOBILE"))
                if (ni.isConnected())
                    haveConnectedMobile = true;
        }
        return haveConnectedWifi || haveConnectedMobile;
    }

    @Override
    protected void onStart() {
        super.onStart();
        getSupportActionBar().hide();
    }

    String fireBaseToken = "";

    private void SingIn(final String email, final String password) {
        final ProgressDialog progressDialog = new ProgressDialog(this);
        progressDialog.setMessage(getResources().getText(R.string.Please_wait));
        progressDialog.setCancelable(false);
        progressDialog.show();
        FirebaseApp.initializeApp(this);




        FirebaseInstanceId.getInstance().getInstanceId().addOnSuccessListener(instanceIdResult -> {
            fireBaseToken = instanceIdResult.getToken();
            Toast.makeText(Login.this, fireBaseToken, Toast.LENGTH_SHORT).show();
            Call<ChatAgentLogin> call = apiInterface.SingIn(email, password, fireBaseToken);
            call.enqueue(new Callback<ChatAgentLogin>() {
                @Override
                public void onResponse(Call<ChatAgentLogin> call, retrofit2.Response<ChatAgentLogin> response) {
                    Log.e("TAG", "Login : " + new Gson().toJson(response.body()));
                    progressDialog.dismiss();
                    if (response.isSuccessful()) {
                        Log.e("TAG", "onResponse: " + response.body().getAgentsdetails());
                        Log.e("TAG", "onResponse: " + response.body().getAgentsdetails());
                        if (response.body().getAgentsdetails().getName() != null) {
                            sessionManager.setPreferences(Login.this, "name", response.body().getAgentsdetails().getName());
                        }

                        if (response.body().getAgentsdetails().getLoginId() != null) {
                            sessionManager.setPreferences(Login.this, "login_id", response.body().getAgentsdetails().getLoginId());
                        }

                        if (response.body().getAgentsdetails().getTime() != null) {
                            sessionManager.setPreferences(Login.this, "login_time", response.body().getAgentsdetails().getTime());
                        }

                        FirebaseMessaging.getInstance().subscribeToTopic("global")
                                .addOnCompleteListener(new OnCompleteListener<Void>() {
                                    @Override
                                    public void onComplete(@NonNull Task<Void> task) {
                                        String msg = "sucessfull";
                                        if (!task.isSuccessful()) {
                                            msg = "Faild";
                                        }


                                    }
                                });
                        Intent i = new Intent(Login.this, Home.class);
                        sessionManager.setPreferences(Login.this, "LoginTest", "true");
                        sessionManager.setPreferences(Login.this, "token", fireBaseToken);
                        startActivity(i);
                        overridePendingTransition(R.anim.activity_back_in, R.anim.activity_back_out);
                    } else {
                        Toast.makeText(Login.this, getResources().getText(R.string.Something_worg_from_server), Toast.LENGTH_SHORT).show();

                    }

                }

                @Override
                public void onFailure(Call<ChatAgentLogin> call, Throwable t) {
                    progressDialog.dismiss();
                    Log.e("TAG", "onFailure: " + t.toString());
                    Toast.makeText(Login.this, getResources().getText(R.string.Please_Check_Internet_Connection), Toast.LENGTH_SHORT).show();
                    // Log error here since request failed
                }
            });
            //Toast.makeText(SingIn.this, fireBaseToken, Toast.LENGTH_SHORT).show();
        });


    }

}