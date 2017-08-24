package nivida.com.mformilk;

import android.content.Context;
import android.content.SharedPreferences;

/**
 * Created by SEO on 1/31/2017.
 */

public class AppPref {


    private static final String USER_PREFS = "USER_PREFS";
    private SharedPreferences appSharedPrefs;
    private SharedPreferences.Editor prefsEditor;
    Boolean skipflag=true;
    boolean loggedIn=false;
    String id="";
    String fullname="";
    String mobile="";
    String email="";
    String billing_address="";
    String otp="";
    String delivery_address="";
    String status="";
    String device_id="";
    String apple_id="";
    String created="";
    String modified="";

    public AppPref(Context context) {

        this.appSharedPrefs = context.getSharedPreferences(USER_PREFS, Context.MODE_PRIVATE);
        this.prefsEditor = appSharedPrefs.edit();
        id=this.appSharedPrefs.getString("id","");
        fullname=this.appSharedPrefs.getString("fullname","");
        mobile=this.appSharedPrefs.getString("mobile","");
        email=this.appSharedPrefs.getString("email","");
        billing_address=this.appSharedPrefs.getString("billing_address","");
        otp=this.appSharedPrefs.getString("otp","");
        delivery_address=this.appSharedPrefs.getString("delivery_address","");
        status=this.appSharedPrefs.getString("status","");
        device_id=this.appSharedPrefs.getString("device_id","");
        apple_id=this.appSharedPrefs.getString("apple_id","");
        created=this.appSharedPrefs.getString("created","");
        modified=this.appSharedPrefs.getString("modified","");
        loggedIn=this.appSharedPrefs.getBoolean("loggedIn",false);


    }

    public boolean isLoggedIn() {
        return appSharedPrefs.getBoolean("loggedIn",false);
    }

    public void setLoggedIn(boolean loggedIn) {
        this.loggedIn = loggedIn;
        prefsEditor.putBoolean("loggedIn",loggedIn).commit();
    }

    public String getFullname() {
        return appSharedPrefs.getString("fullname","");
    }

    public void setFullname(String fullname) {
        this.fullname = fullname;
        prefsEditor.putString("fullname",fullname).commit();
    }

    public String getMobile() {
        return appSharedPrefs.getString("mobile","");

    }

    public void setMobile(String mobile) {
        this.mobile = mobile;
        prefsEditor.putString("mobile",mobile).commit();
    }

    public String getEmail() {
        return appSharedPrefs.getString("email","");
    }

    public void setEmail(String email) {
        this.email = email;
        prefsEditor.putString("email",email).commit();
    }

    public String getBilling_address() {
        return appSharedPrefs.getString("billing_address","");
    }

    public void setBilling_address(String billing_address) {
        this.billing_address = billing_address;
        prefsEditor.putString("billing_address",billing_address).commit();
    }

    public String getOtp() {
        return appSharedPrefs.getString("otp","");
    }

    public void setOtp(String otp) {
        this.otp = otp;
        prefsEditor.putString("otp",otp).commit();
    }

    public String getDelivery_address() {
        return appSharedPrefs.getString("delivery_address","");

    }

    public void setDelivery_address(String delivery_address) {
        this.delivery_address = delivery_address;
        prefsEditor.putString("delivery_address",delivery_address).commit();
    }

    public String getStatus() {
        return appSharedPrefs.getString("status","");

    }

    public void setStatus(String status) {
        this.status = status;
        prefsEditor.putString("status",status).commit();
    }

    public String getDevice_id() {
        return appSharedPrefs.getString("device_id","");

    }

    public void setDevice_id(String device_id) {
        this.device_id = device_id;
        prefsEditor.putString("device_id",device_id).commit();
    }

    public String getApple_id() {
        return appSharedPrefs.getString("apple_id","");

    }

    public void setApple_id(String apple_id) {
        this.apple_id = apple_id;
        prefsEditor.putString("apple_id",apple_id).commit();
    }

    public String getCreated() {
        return appSharedPrefs.getString("created","");

    }

    public void setCreated(String created) {
        this.created = created;
        prefsEditor.putString("created",created).commit();
    }

    public String getModified() {
        return appSharedPrefs.getString("modified","");

    }

    public void setModified(String modified) {
        this.modified = modified;
        prefsEditor.putString("modified",modified).commit();
    }



    public String getId() {
        return appSharedPrefs.getString("id","");

    }

    public void setId(String id) {
        this.id = id;
        prefsEditor.putString("id",id).commit();
    }







}
