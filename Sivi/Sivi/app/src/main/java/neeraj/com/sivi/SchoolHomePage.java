package neeraj.com.sivi;

import android.content.Intent;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.design.widget.NavigationView;
import android.support.design.widget.TabLayout;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentPagerAdapter;
import android.support.v4.view.GravityCompat;
import android.support.v4.view.ViewPager;
import android.support.v4.widget.DrawerLayout;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.Toolbar;
import android.view.MenuItem;
import android.view.View;
import android.widget.TextView;
import android.widget.Toast;

public class SchoolHomePage extends AppCompatActivity
{
    private NavigationView navigationView;
    private DrawerLayout drawerLayout;
    private TabLayout tabLayout;
    private ViewPager viewPager;
    private Toolbar toolbar;
    private MyPageAdapter myPageAdapter;
    TextView tvName;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_school_home_page);
        toolbar= findViewById(R.id.toolBar);
        setSupportActionBar(toolbar);
        toolbar.setTitle("Sivi");
        final android.support.v7.app.ActionBar actionBar=getSupportActionBar();
        actionBar.setDisplayHomeAsUpEnabled(true);
        actionBar.setHomeAsUpIndicator(R.drawable.ic_menu);
        drawerLayout=findViewById(R.id.drawerLayout);
        navigationView=findViewById(R.id.navigationView);
        View hView=navigationView.getHeaderView(0);
        tvName=hView.findViewById(R.id.textname);
        tvName.setText("SIVI");
        navigationView.setNavigationItemSelectedListener(new NavigationView.OnNavigationItemSelectedListener() {
            @Override
            public boolean onNavigationItemSelected(@NonNull MenuItem item)
            {
                switch (item.getItemId())
                {
                    case R.id.setting: {
                        item.setChecked(true);
                        Toast.makeText(SchoolHomePage.this, "Group Selected", Toast.LENGTH_SHORT).show();
                        drawerLayout.closeDrawers();
                        return true;
                    }
                    case R.id.logout:
                    {
                        item.setChecked(true);
                        drawerLayout.closeDrawers();
                        startActivity(new Intent(SchoolHomePage.this,LoginHomeActivity.class));
                        finish();
                        return true;
                    }
                }
                return false;
            }
        });
        viewPager=findViewById(R.id.viewPager);
        tabLayout=findViewById(R.id.homePageTabLayout);
        myPageAdapter=new MyPageAdapter(getSupportFragmentManager());
        viewPager.setAdapter(myPageAdapter);
        tabLayout.setupWithViewPager(viewPager);
        tabLayout.addOnTabSelectedListener(new TabLayout.OnTabSelectedListener() {
            @Override
            public void onTabSelected(TabLayout.Tab tab)
            {
                switch (tab.getPosition())
                {
                    case 0:
                        getSupportFragmentManager().beginTransaction().add(R.id.homePageFragment,new SchoolHome()).commit();
                        break;
                    case 1:
                        getSupportFragmentManager().beginTransaction().add(R.id.homePageFragment,new SchoolGroup()).commit();
                        break;
                    case 2:
                        getSupportFragmentManager().beginTransaction().add(R.id.homePageFragment,new SchoolInvite()).commit();
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
                    return new SchoolHome();
                case 1:
                    return new SchoolGroup();
                case 2:
                    return new SchoolInvite();
            }
            return null;
        }

        @Override
        public int getCount() {
            return 3;
        }

        @Nullable
        @Override
        public CharSequence getPageTitle(int position)
        {
            switch (position)
            {
                case 0:
                    return "Home";
                case 1:
                    return "Groups";
                case 2:
                    return "Invite";
            }
            return null;
        }
    }
    @Override
    public boolean onOptionsItemSelected(MenuItem item)
    {
        switch (item.getItemId())
        {
            case android.R.id.home:
                drawerLayout.openDrawer(GravityCompat.START);
                return true;
        }
        return super.onOptionsItemSelected(item);
    }
}
