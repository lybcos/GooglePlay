package com.example.administrator.tablayouttest.activity;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.support.design.widget.TabLayout;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentPagerAdapter;
import android.support.v4.view.ViewPager;
import android.support.v4.widget.DrawerLayout;
import android.support.v7.app.ActionBar;
import android.support.v7.app.ActionBarDrawerToggle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.view.Window;
import android.widget.Toast;

import com.example.administrator.tablayouttest.R;
import com.example.administrator.tablayouttest.fragment.BaseFragment;
import com.example.administrator.tablayouttest.fragment.FragmentFactroy;

import java.util.ArrayList;
import java.util.List;


public class MainActivity extends AppCompatActivity implements Toolbar.OnMenuItemClickListener{
    private TabLayout tabLayout;
    private ViewPager vp_pager,viewPager;
    private weiboPagerAdapter adapter;
    private ActionBarDrawerToggle mDrawerToggle;
    private DrawerLayout drawerLayout;
    private long lastBack=0;
    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.title_menu,menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        switch (item.getItemId()) {
            case R.id.search:
                Intent intent = new Intent(this, SearchActivity.class);
                startActivity(intent);
                break;
        }
        return super.onOptionsItemSelected(item);
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        requestWindowFeature(Window.FEATURE_NO_TITLE);
        setContentView(R.layout.activity_main);

        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        drawerLayout = (DrawerLayout) findViewById(R.id.drawer_layout);
        setSupportActionBar(toolbar);
//        toolbar.setLogo(R.drawable.googleplaylogo);
        mDrawerToggle = new ActionBarDrawerToggle(this, drawerLayout
                , toolbar, R.string.open, R.string.close){
            @Override
            public void onDrawerOpened(View drawerView) {
                super.onDrawerOpened(drawerView);
            }

            @Override
            public void onDrawerClosed(View drawerView) {
                super.onDrawerClosed(drawerView);
            }
        };
        drawerLayout.setDrawerListener(mDrawerToggle);
//        NavigationView navView = (NavigationView) findViewById(R.id.nav_view);
        ActionBar actionBar = getSupportActionBar();
        if (actionBar != null) {
            actionBar.setDisplayShowHomeEnabled(true);
            actionBar.setDisplayHomeAsUpEnabled(true);
            actionBar.setHomeAsUpIndicator(R.drawable.menu);
        }
//        navView.setCheckedItem(R.id.nav_call);
//        navView.setNavigationItemSelectedListener(new NavigationView.OnNavigationItemSelectedListener() {
//            @Override
//            public boolean onNavigationItemSelected(@NonNull MenuItem item) {
//                drawerLayout.closeDrawers();
//                return true;
//            }
//        });
        tabLayout = (TabLayout) findViewById(R.id.tablayout);
        vp_pager = (ViewPager) findViewById(R.id.vp_pager);
        adapter = new weiboPagerAdapter(getSupportFragmentManager(), this);
        vp_pager.setAdapter(adapter);
        tabLayout.setupWithViewPager(vp_pager);
        //设置监听
        tabLayout.setOnTabSelectedListener(new TabLayout.OnTabSelectedListener() {
            @Override
            public void onTabSelected(TabLayout.Tab tab) {
            //选中了tab的逻辑
            }

            @Override
            public void onTabUnselected(TabLayout.Tab tab) {
                //未选中tab的逻辑
            }

            @Override
            public void onTabReselected(TabLayout.Tab tab) {
                //再次选中tab的逻辑
            }
        });

        }

    @Override
    public boolean onMenuItemClick(MenuItem item) {
        switch (item.getItemId()) {
            case R.id.search:
                Intent intent = new Intent(this, SearchActivity.class);
                startActivity(intent);
                break;
            case R.id.nav_call:
                Toast.makeText(this,"侧滑",Toast.LENGTH_LONG).show();
                break;
            case R.id.nav_email:
                break;
                case R.id.nav_friend:
                break;
                case R.id.nav_location:
                break;
                case R.id.nav_task:
                break;
                case R.id.nav_view:
                break;
        }
        return true;
    }



    class weiboPagerAdapter extends FragmentPagerAdapter {
        public String[] mTitle;
        private Context mcontext;
        private List<View> mlist = new ArrayList<>();

        public weiboPagerAdapter(FragmentManager fm, Context context) {
            super(fm);
            this.mcontext = context;
            mTitle = context.getResources().getStringArray(R.array.tab_long_Title);
        }


        @Override
        public Fragment getItem(int position) {
            BaseFragment fragment = FragmentFactroy.createFragment(position);
            return fragment;
        }

        @Override
        public int getCount() {
            return mTitle.length;
        }

        @Override
        public CharSequence getPageTitle(int position) {
            return mTitle[position];

        }
    }
        @Override
        public void onBackPressed() {
            if (lastBack == 0 || System.currentTimeMillis() - lastBack > 2000) {
                Toast.makeText(this, "再按一次返回退出程序", Toast.LENGTH_SHORT).show();
                lastBack = System.currentTimeMillis();
                return;
            }
            super.onBackPressed();
        }
    }

