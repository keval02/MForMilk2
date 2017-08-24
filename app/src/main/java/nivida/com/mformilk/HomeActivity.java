package nivida.com.mformilk;

import android.content.Intent;
import android.support.design.widget.NavigationView;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentTransaction;
import android.support.v4.widget.DrawerLayout;
import android.support.v7.app.ActionBarDrawerToggle;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.Toolbar;
import android.view.MenuItem;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import nivida.com.mformilk.Navigation.AboutFragment;
import nivida.com.mformilk.Navigation.ContactUs;
import nivida.com.mformilk.Navigation.DeliveryboyFragment;
import nivida.com.mformilk.Navigation.FaqFragment;
import nivida.com.mformilk.Navigation.FeedbackFragment;
import nivida.com.mformilk.Navigation.OffersFragment;
import nivida.com.mformilk.Navigation.OrderFragment;
import nivida.com.mformilk.Navigation.ProfileFragment;
import nivida.com.mformilk.Navigation.RatesFragment;
import nivida.com.mformilk.Navigation.ReviewsFragment;
import nivida.com.mformilk.Navigation.TermFragment;

public class HomeActivity extends AppCompatActivity{
    DrawerLayout myDrawerLayout;
    NavigationView myNavigationView;
    FragmentManager myFragmentManager;
    FragmentTransaction myFragmentTransaction;
    AppPref pref;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main2);
        pref=new AppPref(getApplicationContext());


        /**
         *Setup the DrawerLayout and NavigationView
         */

        myDrawerLayout = (DrawerLayout) findViewById(R.id.drawerLayout);
        myNavigationView = (NavigationView) findViewById(R.id.nav_drawer) ;

        /**
         * Lets inflate the very first fragment
         * Here , we are inflating the HomeFragment as the first Fragment
         */

        myFragmentManager = getSupportFragmentManager();
        myFragmentTransaction = myFragmentManager.beginTransaction();
        myFragmentTransaction.replace(R.id.containerView, new HomeFragment()).commit();
        /**
         * Setup click events on the Navigation View Items.
         */

        myNavigationView.setNavigationItemSelectedListener(new NavigationView.OnNavigationItemSelectedListener() {

            public boolean onNavigationItemSelected(MenuItem selectedMenuItem) {
                myDrawerLayout.closeDrawers();

                if (selectedMenuItem.getItemId() == R.id.nav_item_profile) {
                    FragmentTransaction fragmentTransaction = myFragmentManager.beginTransaction();
                    fragmentTransaction.replace(R.id.containerView, new ProfileFragment()).commit();

                }
                if (selectedMenuItem.getItemId() == R.id.nav_item_about) {
                    FragmentTransaction fragmentTransaction = myFragmentManager.beginTransaction();
                    fragmentTransaction.replace(R.id.containerView, new AboutFragment()).commit();

                }

                if (selectedMenuItem.getItemId() == R.id.nav_item_orders) {
                    FragmentTransaction xfragmentTransaction = myFragmentManager.beginTransaction();
                    xfragmentTransaction.replace(R.id.containerView, new OrderFragment()).commit();
                }


                if (selectedMenuItem.getItemId() == R.id.nav_item_rates) {
                    FragmentTransaction xfragmentTransaction = myFragmentManager.beginTransaction();
                    xfragmentTransaction.replace(R.id.containerView, new RatesFragment()).commit();
                }


                if (selectedMenuItem.getItemId() == R.id.nav_item_faq) {
                    FragmentTransaction fragmentTransaction = myFragmentManager.beginTransaction();
                    fragmentTransaction.replace(R.id.containerView, new FaqFragment()).commit();

                }

                if (selectedMenuItem.getItemId() == R.id.nav_item_offers) {
                    FragmentTransaction xfragmentTransaction = myFragmentManager.beginTransaction();
                    xfragmentTransaction.replace(R.id.containerView, new OffersFragment()).commit();
                }



                if (selectedMenuItem.getItemId() == R.id.nav_item_contact) {
                    FragmentTransaction fragmentTransaction = myFragmentManager.beginTransaction();
                    fragmentTransaction.replace(R.id.containerView, new ContactUs()).commit();

                }

                if (selectedMenuItem.getItemId() == R.id.nav_item_feedback) {
                    FragmentTransaction xfragmentTransaction = myFragmentManager.beginTransaction();
                    xfragmentTransaction.replace(R.id.containerView, new FeedbackFragment()).commit();
                }


                if (selectedMenuItem.getItemId() == R.id.nav_item_reviews) {
                    FragmentTransaction fragmentTransaction = myFragmentManager.beginTransaction();
                    fragmentTransaction.replace(R.id.containerView, new ReviewsFragment()).commit();

                }

                if (selectedMenuItem.getItemId() == R.id.nav_item_termandcondition) {
                    FragmentTransaction xfragmentTransaction = myFragmentManager.beginTransaction();
                    xfragmentTransaction.replace(R.id.containerView, new TermFragment()).commit();
                }

                if (selectedMenuItem.getItemId() == R.id.nav_item_alert) {
                    FragmentTransaction fragmentTransaction = myFragmentManager.beginTransaction();
                    fragmentTransaction.replace(R.id.containerView, new AlertNotification()).commit();

                }

                if (selectedMenuItem.getItemId() == R.id.nav_item_deliveryboy) {
                    FragmentTransaction xfragmentTransaction = myFragmentManager.beginTransaction();
                    xfragmentTransaction.replace(R.id.containerView, new DeliveryboyFragment()).commit();
                }
                return false;
            }

        });

        /**
         * Setup Drawer Toggle of the Toolbar
         */
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        ImageView homeicon= (ImageView) findViewById(R.id.homeicon);
        ImageView logout= (ImageView) findViewById(R.id.logout);
        homeicon.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                Intent i=new Intent(getApplicationContext(),HomeActivity.class);
                startActivity(i);
                finish();

            }
        });
        logout.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                pref.setLoggedIn(false);
                finish();
            }
        });

        setSupportActionBar(toolbar);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        getSupportActionBar().setHomeButtonEnabled(true);



        ActionBarDrawerToggle mDrawerToggle = new ActionBarDrawerToggle(this, myDrawerLayout, toolbar,R.string.app_name,
                R.string.app_name);

        myDrawerLayout.setDrawerListener(mDrawerToggle);

        mDrawerToggle.syncState();

    }



}