package neeraj.com.milk.Milk;

import android.support.annotation.Nullable;
import android.support.design.widget.TabLayout;

import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentActivity;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentPagerAdapter;
import android.support.v4.app.FragmentTransaction;
import android.support.v4.view.ViewPager;

import neeraj.com.milk.R;

public class BillingActivity extends FragmentActivity {
    TabLayout tabLayout;
    ViewPager billingPager;
    MyPageAdapter myPageAdapter;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_billing);
        billingPager=findViewById(R.id.billingPager);
        myPageAdapter=new MyPageAdapter(getSupportFragmentManager());
        billingPager.setAdapter(myPageAdapter);
        tabLayout=findViewById(R.id.payment_tab_layout);
        tabLayout.setupWithViewPager(billingPager);
        tabLayout.addOnTabSelectedListener(new TabLayout.OnTabSelectedListener() {
            @Override
            public void onTabSelected(TabLayout.Tab tab) {
                int pos=tab.getPosition();
                FragmentTransaction ft=getSupportFragmentManager().beginTransaction();
                switch (pos)
                {
                    case 0:
                    ft.add(R.id.billing_fragment,new MilkFragment(),"Milk_Fragment");
                    ft.commit();
                    break;
                    case 1:
                        ft.add(R.id.billing_fragment,new AdvancePaymentFragment(),"Milk_Fragment");
                        ft.commit();
                        break;
                }
            }

            @Override
            public void onTabUnselected(TabLayout.Tab tab) {

            }

            @Override
            public void onTabReselected(TabLayout.Tab tab) {

            }
        });
    }
    public class MyPageAdapter extends FragmentPagerAdapter
    {

        public MyPageAdapter(FragmentManager fm) {
            super(fm);
        }

        @Override
        public Fragment getItem(int position)
        {
            switch (position)
            {
                case 0:
                    return new MilkFragment();
                case 1:
                    return new AdvancePaymentFragment();
            }
            return null;
        }

        @Override
        public int getCount() {
            return 2;
        }

        @Nullable
        @Override
        public CharSequence getPageTitle(int position)
        {
            switch (position)
            {
                case 0:
                    return "Milk";
                case 1:
                    return "Payment";
            }
            return null;
        }
    }
}
