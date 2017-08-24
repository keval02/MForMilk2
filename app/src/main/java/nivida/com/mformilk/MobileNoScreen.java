package nivida.com.mformilk;

import android.Manifest;
import android.annotation.TargetApi;
import android.app.Activity;
import android.content.Intent;
import android.os.Build;
import android.support.annotation.NonNull;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.EditText;
import android.widget.LinearLayout;
import android.widget.Toast;

import com.android.volley.NetworkError;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.StringRequest;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import static android.content.pm.PackageManager.PERMISSION_GRANTED;
import static nivida.com.mformilk.GlobalFile.noInternet;
import static nivida.com.mformilk.GlobalFile.serverError;

public class MobileNoScreen extends AppCompatActivity {
    LinearLayout getOtpLayout;
    AppPref appPref;
    EditText mobileno;
    String etmobile;

   // Custom_ProgressDialog loadingView;

    String[] perms = {android.Manifest.permission.SYSTEM_ALERT_WINDOW, android.Manifest.permission.WRITE_EXTERNAL_STORAGE, Manifest.permission.READ_PHONE_STATE,Manifest.permission.INTERNET,Manifest.permission.ACCESS_WIFI_STATE,Manifest.permission.ACCESS_FINE_LOCATION,Manifest.permission.ACCESS_COARSE_LOCATION,Manifest.permission.CALL_PHONE,Manifest.permission.GET_ACCOUNTS,Manifest.permission.READ_PHONE_STATE,Manifest.permission.ACCESS_NETWORK_STATE};

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_mobilenoscreen);
        isPermissionRequestRequired(this, perms, 1);
       // loadingView = new Custom_ProgressDialog(MobileNoScreen.this, "");
        fetchid();
    }
    @TargetApi(Build.VERSION_CODES.M)
    public static boolean isPermissionRequestRequired(Activity activity, @NonNull String[] permissions, int requestCode) {
        if (isMarshmallowPlusDevice() && permissions.length > 0) {
            List<String> newPermissionList = new ArrayList<>();
            for (String permission : permissions) {
                if (PERMISSION_GRANTED != activity.checkSelfPermission(permission)) {
                    newPermissionList.add(permission);
                }
            }
            if (newPermissionList.size() > 0) {
                activity.requestPermissions(newPermissionList.toArray(new String[newPermissionList.size()]), requestCode);
                return true;
            }
        }

        return false;
    }

    public static boolean isMarshmallowPlusDevice() {

        return Build.VERSION.SDK_INT > Build.VERSION_CODES.LOLLIPOP_MR1;
    }


    private void fetchid() {
        getOtpLayout=(LinearLayout)findViewById(R.id.getOtpLayout);
        mobileno= (EditText) findViewById(R.id.edit_mobileno);
        etmobile=mobileno.getText().toString();
        getOtpLayout.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {



                if(mobileno.getText().toString().length() == 10) {
                  //  getOtp();

                   Intent in3 = new Intent(MobileNoScreen.this, OTPScreen.class);
                   startActivity(in3);
                  finish();
                    overridePendingTransition(R.anim.from_left, R.anim.from_right);
                }
                else {
                    mobileno.setError("Enter valid no");
                    mobileno.requestFocus();
                }
            }
        });
    }

    public void getOtp() {
       // Log.e("11","11");
      appPref=new AppPref(getApplicationContext());
        final Custom_ProgressDialog loadingView = new Custom_ProgressDialog(MobileNoScreen.this, "");
        loadingView.setCancelable(false);
        loadingView.show();
       // Log.e("22","22");
        //layout_login.setVisibility(View.GONE);
        StringRequest request = new StringRequest(GlobalFile.POST, GlobalFile.server_link + "User/App_Login_OTP",
                new Response.Listener<String>() {
                    @Override
                    public void onResponse(String response) {

                        loadingView.dismiss();


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
                               // loadingView.dismiss();
                                //layout_login.setVisibility(View.VISIBLE);
                                //avi.hide();
                            }
                            else
                            {

                                Log.e("tt","tt");

                                String Message = jObj.getString("message");
                                Toast.makeText(getApplicationContext(), "" + Message, Toast.LENGTH_LONG).show();
                            //    loadingView.dismiss();
                                appPref.setMobile(etmobile);
                                Intent in3 = new Intent(MobileNoScreen.this, OTPScreen.class);
                               // in3.putExtra("mobile",etmobile);
                                startActivity(in3);
                                finish();
                                overridePendingTransition(R.anim.from_left, R.anim.from_right);
//
//                                JSONArray dataArray=jObj.getJSONArray("data");
//                                Log.e("array",""+dataArray);




                            }
                        } catch (JSONException j) {
                            j.printStackTrace();
                           // loadingView.dismiss();
                            Log.e("Exception",""+j.getMessage());
                        }
                    }
                },
                new Response.ErrorListener() {
                    @Override
                    public void onErrorResponse(VolleyError error) {

                        loadingView.dismiss();
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

                Map<String, String> params = new HashMap<String, String>();
                params.put("mobile",etmobile);

                return params;
            }
        };
        MForMilk.getInstance().addToRequestQueue(request);
    }
}
