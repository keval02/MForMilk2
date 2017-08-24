package nivida.com.mformilk;

import android.graphics.Color;
import android.os.Build;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.design.widget.TabLayout;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentPagerAdapter;
import android.support.v4.view.ViewPager;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.Window;

import nivida.com.mformilk.CalenderTab.CalenderTabFragment;
import nivida.com.mformilk.SubscriptionTab.MySubscriptionTabFragment;
import nivida.com.mformilk.WalletTab.WalletTabFragment;

public class HomeFragment extends Fragment {

    public static TabLayout tabLayout;
    public static ViewPager viewPager;
    public static int int_items = 3 ;
    Window window;

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        /**
         *Inflate tab_layout and setup Views.
         */
            View x =  inflater.inflate(R.layout.home_tab_layout,null);
            tabLayout = (TabLayout) x.findViewById(R.id.tabs);
            viewPager = (ViewPager) x.findViewById(R.id.viewpager);
             window = getActivity().getWindow();

        /**
         *Set an Apater for the View Pager
         */
        viewPager.setAdapter(new MyAdapter(getChildFragmentManager()));

        viewPager.setOnPageChangeListener(new ViewPager.OnPageChangeListener() {
            @Override
            public void onPageScrolled(int position, float positionOffset, int positionOffsetPixels) {

            }

            @Override
            public void onPageSelected(int position) {
                if(position==0){
                    tabLayout.setSelectedTabIndicatorColor(Color.WHITE);
                  //  getActivity().findViewById(R.id.toolbar).setBackgroundColor(Color.parseColor("#ED3B3B"));
                    if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.LOLLIPOP)
                    {
                      //  window.setStatusBarColor(getResources().getColor(R.color.white));
                    }
                    else
                    {

                    }

                }
                else if(position==1){
                    tabLayout.setSelectedTabIndicatorColor(Color.WHITE);
                    //getActivity().findViewById(R.id.toolbar).setBackgroundColor(Color.parseColor("#8BC34A"));

                    if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.LOLLIPOP)
                    {
                      //  window.setStatusBarColor(getResources().getColor(R.color.white));
                    }
                    else
                    {

                    }

                }
                else if(position==2){
                    tabLayout.setSelectedTabIndicatorColor(Color.WHITE);
                   // getActivity().findViewById(R.id.toolbar).setBackgroundColor(Color.parseColor("#03A9F4"));
                    if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.LOLLIPOP)
                    {
                      //  window.setStatusBarColor(getResources().getColor(R.color.white));
                    }
                    else
                    {

                    }
                }
            }

            @Override
            public void onPageScrollStateChanged(int state) {

            }
        });
        tabLayout.post(new Runnable() {
            @Override
            public void run() {
                    tabLayout.setupWithViewPager(viewPager);
                   }
        });

        return x;

    }

    class MyAdapter extends FragmentPagerAdapter{

        public MyAdapter(FragmentManager fm) {
            super(fm);
        }

        /**
         * Return fragment with respect to Position .
         */

        @Override
        public Fragment getItem(int position)
        {
          switch (position){
              case 0 : return new CalenderTabFragment();
              case 1 : return new MySubscriptionTabFragment();
              case 2 : return new WalletTabFragment();
          }
        return null;
        }

        @Override
        public int getCount() {

            return int_items;

        }

        /**
         * This method returns the title of the tab according to the position.
         */

        @Override
        public CharSequence getPageTitle(int position) {

            switch (position){
                case 0 :
                    return "Calendar";
                case 1 :
                    return "Subscription";
                case 2 :
                    return "Wallet";
            }
                return null;
        }
    }

}
