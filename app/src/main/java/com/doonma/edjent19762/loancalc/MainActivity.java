package com.doonma.edjent19762.loancalc;

import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentActivity;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentPagerAdapter;
import android.support.v4.view.ViewPager;
import android.view.Menu;
import android.view.MenuItem;




//import com.doonma.viewpagerwithfragmentpageradapter.a.fragments.*;


public class MainActivity extends FragmentActivity {
    FragmentPagerAdapter adapterViewPager;
    static String title;




    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        ViewPager vpPager = (ViewPager) findViewById(R.id.vpPager);
        adapterViewPager = new MyPagerAdapter(getSupportFragmentManager());
        vpPager.setAdapter(adapterViewPager);
    }


    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_main, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        int id = item.getItemId();

        //noinspection SimplifiableIfStatement
        if (id == R.id.action_settings) {
            return true;
        }

        return super.onOptionsItemSelected(item);
    }

    public static class MyPagerAdapter extends FragmentPagerAdapter {

        private static int NUM_ITEMS = 2;

        public MyPagerAdapter(FragmentManager fragmentManager) {

            super(fragmentManager);
        }

        @Override
        public int getCount(){
            return NUM_ITEMS;
        }
        @Override
        public Fragment getItem(int position) {
            switch(position) {
                case 0:
                    return LoanCalculator.newInstance(0, "Loan Calculator");
                case 1:
                    return PaymentSchedule.newInstance(1, "Payment Schedule");
                default:
                    return null;
            }
        }
        @Override
        public CharSequence getPageTitle(int position) {
            if (position == 0) {
                return "Loan Calculator";
            }else{
                return "Payment Schedule";
            }
        }

    }
}
