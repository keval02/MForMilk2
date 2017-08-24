package nivida.com.mformilk;

import android.content.Intent;
import android.os.CountDownTimer;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;

public class SplashScreen extends AppCompatActivity{
    AppPref appPref;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_splash_screen);
        appPref=new AppPref(getApplicationContext());
        new CountDownTimer(2000,2200) {

            @Override
            public void onTick(long millisUntilFinished) {
                // TODO Auto-generated method stub

            }

            @Override
            public void onFinish() {
                // TODO Auto-generated method stub
                if(appPref.isLoggedIn())
                {
                    Intent in3 = new Intent(SplashScreen.this,HomeActivity.class);
                    startActivity(in3);
                    finish();
                    overridePendingTransition(R.anim.from_left, R.anim.from_right);
                }
                else
                {
                    Intent in3 = new Intent(SplashScreen.this,MobileNoScreen.class);
                    startActivity(in3);
                    finish();
                    overridePendingTransition(R.anim.from_left, R.anim.from_right);
                }



            }

        }.start();
    }
}
