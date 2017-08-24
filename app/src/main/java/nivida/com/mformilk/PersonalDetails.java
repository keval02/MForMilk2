package nivida.com.mformilk;


import android.content.Context;
import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.telephony.TelephonyManager;
import android.util.Log;
import android.view.View;
import android.widget.EditText;
import android.widget.LinearLayout;
import android.widget.Toast;

import com.android.volley.NetworkError;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.StringRequest;

import org.json.JSONException;
import org.json.JSONObject;

import java.util.HashMap;
import java.util.Map;

import static nivida.com.mformilk.GlobalFile.noInternet;
import static nivida.com.mformilk.GlobalFile.serverError;


public class PersonalDetails extends AppCompatActivity {

    EditText etemail_add,etbill_add,etdelivery_add;
    LinearLayout submit,skip;
    AppPref appPref;
    String emailPattern = "[a-zA-Z0-9._-]+@[a-z]+\\.+[a-z]+";
    String stemail,stdeladd,stbilladd;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_personal_details);
        fetchid();
    }

    private void fetchid() {
        submit=(LinearLayout)findViewById(R.id.submit);
        skip= (LinearLayout) findViewById(R.id.skip);
        etemail_add= (EditText) findViewById(R.id.edit_email);
        etbill_add= (EditText) findViewById(R.id.edit_bill);
        etdelivery_add= (EditText) findViewById(R.id.edit_delivery);
        stemail=etemail_add.getText().toString();
        stbilladd=etbill_add.getText().toString();
        stdeladd=etdelivery_add.getText().toString();
        submit.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if(!stemail.matches(emailPattern) || stemail.length() == 0 )
                {
                    etemail_add.setError("Enter valid email");
                    etemail_add.requestFocus();
                }
                if(etbill_add.getText().toString().length() == 0)
                {
                    etbill_add.setError("Enter billing address");
                    etbill_add.requestFocus();
                }
                if(etdelivery_add.getText().toString().length() == 0)
                {
                    etdelivery_add.setError("Enter delivery address");
                    etdelivery_add.requestFocus();
                }
                Intent in3 = new Intent(PersonalDetails.this,HomeActivity.class);
                startActivity(in3);
                finish();
                overridePendingTransition(R.anim.from_left, R.anim.from_right);
               // submitdata();


            }
        });

        skip.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent in3 = new Intent(PersonalDetails.this,HomeActivity.class);
                startActivity(in3);
                finish();
                overridePendingTransition(R.anim.from_left, R.anim.from_right);
            }
        });
    }

    public void submitdata() {
        // Log.e("11","11");
        appPref=new AppPref(getApplicationContext());
        final Custom_ProgressDialog loadingView = new Custom_ProgressDialog(PersonalDetails.this, "");
        loadingView.setCancelable(false);
        loadingView.show();
        // Log.e("22","22");
        //layout_login.setVisibility(View.GONE);
        StringRequest request = new StringRequest(GlobalFile.POST, GlobalFile.server_link + "User/App_personal_details",
                new Response.Listener<String>() {
                    @Override
                    public void onResponse(String response) {


                        Log.e("JSON", response);

                        try {
                            JSONObject jObj = new JSONObject(response);

                            Log.e("response",""+response.toString());

                            boolean date = jObj.getBoolean("status");

                            Log.e("status",""+date);
                            if (date==false) {
                                Log.e("ff","ff");

                                String Message = jObj.getString("message");
                                Toast.makeText(getApplicationContext(), "" + Message, Toast.LENGTH_LONG).show();
                                // GlobalFile.CustomToast(Activity_Login.this,""+Message, getLayoutInflater());
                                loadingView.dismiss();
                                //layout_login.setVisibility(View.VISIBLE);
                                //avi.hide();
                            }
                            else
                            {

                                Log.e("tt","tt");

                                String Message = jObj.getString("message");
                                Toast.makeText(getApplicationContext(), "" + Message, Toast.LENGTH_LONG).show();
                                loadingView.dismiss();

                             //   appPref.setFlag(true);
                                Intent in3 = new Intent(PersonalDetails.this, HomeActivity.class);
                                startActivity(in3);
                                finish();
                                overridePendingTransition(R.anim.from_left, R.anim.from_right);

//                                JSONArray dataArray=jObj.getJSONArray("data");
//                                Log.e("array",""+dataArray);




                            }
                        } catch (JSONException j) {
                            j.printStackTrace();
                            loadingView.dismiss();
                            Log.e("Exception",""+j.getMessage());
                        }
                    }
                },
                new Response.ErrorListener() {
                    @Override
                    public void onErrorResponse(VolleyError error) {
                        //  Log.e("ERROR",error.getMessage());
                        if (error instanceof NetworkError) {
                            noInternet(getApplicationContext());
                        } else
                        {
                            serverError(getApplicationContext());
                        }
                    }
                }) {
            @Override
            protected Map<String, String> getParams() {

                TelephonyManager telephonyManager = (TelephonyManager)getSystemService(Context.TELEPHONY_SERVICE);
                telephonyManager.getDeviceId();
                Log.e("device id ---",""+ telephonyManager.getDeviceId());

                Map<String, String> params = new HashMap<String, String>();

                params.put("id",appPref.getId());
                params.put("email",stemail);
                params.put("billing_address",stbilladd);
                params.put("delivery_address",stdeladd);

                return params;
            }
        };
        MForMilk.getInstance().addToRequestQueue(request);
    }
}
