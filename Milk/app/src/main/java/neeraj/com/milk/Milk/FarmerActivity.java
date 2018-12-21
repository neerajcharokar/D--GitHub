package neeraj.com.milk.Milk;

import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.design.widget.TabLayout;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentActivity;
import android.os.Bundle;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentPagerAdapter;
import android.support.v4.app.FragmentStatePagerAdapter;
import android.support.v4.view.PagerAdapter;
import android.support.v4.view.ViewPager;
import android.view.View;

import neeraj.com.milk.R;

public class FarmerActivity extends FragmentActivity {
    private TabLayout tabLayout;
    private ViewPager viewPager;
     MyPageAdapter myPageAdapter;
    android.support.v4.app.FragmentTransaction ft;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_farmer);
        tabLayout=findViewById(R.id.farmer_layout);
        viewPager=findViewById(R.id.viewpager);
        myPageAdapter=new MyPageAdapter(getSupportFragmentManager());
        viewPager.setAdapter(myPageAdapter);
        tabLayout.setupWithViewPager(viewPager);
        tabLayout.addOnTabSelectedListener(new TabLayout.OnTabSelectedListener() {
            @Override
            public void onTabSelected(TabLayout.Tab tab) {
                int pos=tab.getPosition();
                switch (pos)
                {
                    case 0:
                        ft=getSupportFragmentManager().beginTransaction();
                        ft.add(R.id.farmer_fragment,new AddFarmer(),"Add_farmer_Fragment");
                        ft.commit();
                        break;
                    case 1:
                        ft=getSupportFragmentManager().beginTransaction();
                        ft.add(R.id.farmer_fragment,new ViewFarmer(),"Add_farmer_Fragment");
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
                    return new AddFarmer();
                case 1:
                    return new ViewFarmer();
            }
            return null;
        }

        @Override
        public int getCount() {
            return 2;
        }

        @Nullable
        @Override
        public CharSequence getPageTitle(int position) {
            switch (position)
            {
                case 0:
                    return "Add";
                case 1:
                    return "View";
            }
            return null;
        }
    }
}
