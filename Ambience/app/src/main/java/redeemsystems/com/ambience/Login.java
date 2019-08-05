package redeemsystems.com.ambience;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.android.volley.AuthFailureError;
import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.StringRequest;
import com.android.volley.toolbox.Volley;

import org.json.JSONException;
import org.json.JSONObject;

import java.util.HashMap;
import java.util.Map;

public class Login extends AppCompatActivity {
    Button btn;
    EditText email, password;
    String email1,pass;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);
        email = findViewById(R.id.email);
        password = findViewById(R.id.password);
        btn = findViewById(R.id.btn);

        btn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                email1 = email.getText().toString().trim();
                pass = password.getText().toString().trim();


                StringRequest stringRequest = new StringRequest(Request.Method.POST,"http://182.74.149.122:8080/ambience/android_login.php", new Response.Listener<String>() {
                    @Override
                    public void onResponse(String response) {
                        Log.d("joan", response);
//                            String resp = response.substring(response.indexOf("{"), response.length());
//                            String resp1 = resp.substring(resp.indexOf("main"), resp.length());
//                            String resp2 = resp1.substring(resp1.indexOf("{"), resp1.length());
//                            Log.d("nike", resp2);

                        try {

                            JSONObject jsonObject = new JSONObject(response);
                            String message = jsonObject.getString("status");

                            if (message.equals("ok")) {
                                Intent in = new Intent(Login.this,MainActivity.class);
                                startActivity(in);

                            } else if (message.equals("failed")) {
                                Toast.makeText(Login.this, "Login failed", Toast.LENGTH_SHORT).show();
                                return;
                            }

                        } catch (JSONException e) {
                            e.printStackTrace();
                            Log.d("nike", "exception " + e.toString());
                        }
                    }
                },
                        new Response.ErrorListener() {
                            @Override
                            public void onErrorResponse(VolleyError error) {
                                Toast.makeText(Login.this, error.getMessage(), Toast.LENGTH_LONG).show();
                            }
                        }) {
                    @Override
                    protected Map<String, String> getParams() throws AuthFailureError {
                        Map<String, String> params = new HashMap<>();
                        params.put("email", email1);
                        params.put("pass", pass);
                        return params;
                    }
                };

                RequestQueue requestQueue = Volley.newRequestQueue(Login.this);
                requestQueue.add(stringRequest);

//                if (email1.equals("redeem@gmail.com") && pass.equals("redeem")){
//
//                }else {
//                    Toast.makeText(Login.this, "Login Failed!!!", Toast.LENGTH_SHORT).show();
//                    return;
//                }
            }
        });
    }


}
