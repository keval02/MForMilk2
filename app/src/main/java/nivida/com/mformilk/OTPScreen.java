package nivida.com.mformilk;

import android.content.Context;
import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.telephony.TelephonyManager;
import android.text.Editable;
import android.text.TextWatcher;
import android.util.Log;
import android.view.View;
import android.widget.EditText;
import android.widget.LinearLayout;
import android.widget.TextView;
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

public class OTPScreen extends AppCompatActivity {
    LinearLayout getOtpcodeLayout;
    EditText etotp;
    String stotp,stmobile;
    AppPref appPref;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_otpscreen);
        fetchid();

    }

    private void fetchid() {
        getOtpcodeLayout=(LinearLayout)findViewById(R.id.getOtpcodeLayout);
        etotp= (EditText) findViewById(R.id.edit_otp);
        appPref=new AppPref(getApplicationContext());

//        Intent i=getIntent();
//        stmobile=i.getStringExtra("mobile");
        getOtpcodeLayout.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                if(etotp.getText().toString().length() == 6) {
                    Intent in3 = new Intent(OTPScreen.this, HomeActivity.class);
                    startActivity(in3);
                    finish();
                    overridePendingTransition(R.anim.from_left, R.anim.from_right);
                    appPref.setLoggedIn(true);
                   // sendOtp();

                }
                else {
                    etotp.setError("Enter valid OTP");
                    etotp.requestFocus();
                }
            }
        });
    }


    public void sendOtp() {
        // Log.e("11","11");
         appPref=new AppPref(getApplicationContext());
        final Custom_ProgressDialog loadingView = new Custom_ProgressDialog(OTPScreen.this, "");
        loadingView.setCancelable(false);
        loadingView.show();
        // Log.e("22","22");
        //layout_login.setVisibility(View.GONE);
        StringRequest request = new StringRequest(GlobalFile.POST, GlobalFile.server_link + "User/App_Login_OTP1",
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


                                JSONObject data=jObj.getJSONObject("data");
                                JSONObject customer=data.getJSONObject("Customer");

                                String id=customer.getString("id");
                                String fullname=customer.getString("fullname");
                                String mobile=customer.getString("mobile");
                                String email=customer.getString("email");
                                String otp=customer.getString("otp");
                                String billing_address=customer.getString("billing_address");
                                String delivery_address=customer.getString("delivery_address");
                                String status=customer.getString("status");
                                String device_id=customer.getString("device_id");
                                String apple_id=customer.getString("apple_id");
                                String created=customer.getString("created");
                                String modified=customer.getString("modified");
                                appPref.setId(id);
                                appPref.setFullname(fullname);
                                appPref.setMobile(mobile);
                                appPref.setEmail(email);
                                appPref.setOtp(otp);
                                appPref.setBilling_address(billing_address);
                                appPref.setDelivery_address(delivery_address);
                                appPref.setStatus(status);
                                appPref.setDevice_id(device_id);
                                appPref.setApple_id(apple_id);
                                appPref.setCreated(created);
                                appPref.setModified(modified);


                                Intent in3 = new Intent(OTPScreen.this, PersonalDetails.class);
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

                params.put("mobile",appPref.getMobile());
                params.put("otp",etotp.getText().toString());
                params.put("device_id",telephonyManager.getDeviceId());

                return params;
            }
        };
        MForMilk.getInstance().addToRequestQueue(request);
    }
}
