package com.example.cbluser22.drawerapp.activity;

import android.os.PersistableBundle;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.widget.DrawerLayout;
import android.support.v7.app.ActionBarDrawerToggle;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.Toolbar;
import android.util.Log;
import android.view.MenuItem;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ListView;

import com.example.cbluser22.drawerapp.R;
import com.example.cbluser22.drawerapp.adapter.DrawerAdapter;
import com.example.cbluser22.drawerapp.fragments.Fragment1;
import com.example.cbluser22.drawerapp.fragments.Fragment2;
import com.example.cbluser22.drawerapp.fragments.Fragment3;
import com.example.cbluser22.drawerapp.model.DataModel;

public class MainActivity extends AppCompatActivity {


    private String[] drawerItemTitles;
    private DrawerLayout mDrawerLayout;
    private ListView mDrawerList;
    public Toolbar toolbar;
    private CharSequence mDrawerTitle;
    private CharSequence mTitle;
    DrawerAdapter drawerAdapter;
    android.support.v7.app.ActionBarDrawerToggle mDrawerToggle;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        init();
        setUpToolBar();

        DataModel[] drawerItem = new DataModel[3];

        drawerItem[0] = new DataModel("Setting",R.drawable.logo_adidas);
        drawerItem[1] = new DataModel("Main Menu",R.drawable.logo_aerie);
        drawerItem[2] = new DataModel("Home",R.drawable.logo_aeropostale);

        drawerAdapter=new DrawerAdapter(this,R.layout.listview_item,drawerItem);
        mDrawerList.setAdapter(drawerAdapter);
        getSupportActionBar().setDisplayHomeAsUpEnabled(false);
        getSupportActionBar().setHomeButtonEnabled(true);
        mDrawerList.setOnItemClickListener(new DrawerItemClickListener());
        mDrawerLayout=(DrawerLayout)findViewById(R.id.drawer);
        mDrawerLayout.setDrawerListener(mDrawerToggle);
        setUpDrawerToggle();

    }

    private void setUpDrawerToggle() {
        mDrawerToggle=new ActionBarDrawerToggle(this,mDrawerLayout,toolbar,R.string.app_name,R.string.app_name);
        mDrawerToggle.syncState();

    }

    private void setUpToolBar() {
        toolbar=(Toolbar)findViewById(R.id.tb_toolbarmain);
        setSupportActionBar(toolbar);
        getSupportActionBar().setDisplayShowHomeEnabled(true);

    }

    private void init() {

        drawerItemTitles=getResources().getStringArray(R.array.navigation_drawer_items_array);
        mDrawerLayout=(DrawerLayout)findViewById(R.id.drawer);
        mTitle = mDrawerTitle = getTitle();
        mDrawerList=(ListView)findViewById(R.id.left_drawer);
    }

    private class DrawerItemClickListener implements ListView.OnItemClickListener {

        @Override
        public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
            selectItem(position);
        }

    }

    private void selectItem(int position) {

        Fragment fragment = null;

        switch (position) {
            case 0:
                fragment = new Fragment1();
                break;
            case 1:
                fragment = new Fragment2();
                break;
            case 2:
                fragment = new Fragment3();
                break;

            default:
                break;
        }

        if (fragment != null) {
            FragmentManager fragmentManager = getSupportFragmentManager();
            fragmentManager.beginTransaction().replace(R.id.fl, fragment).commit();

            mDrawerList.setItemChecked(position, true);
            mDrawerList.setSelection(position);
            setTitle(drawerItemTitles[position]);
            mDrawerLayout.closeDrawer(mDrawerList);

        } else {
            Log.e("MainActivity", "Error in creating fragment");
        }
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        if(mDrawerToggle.onOptionsItemSelected(item))
        {
            return  true;
        }
        return super.onOptionsItemSelected(item);
    }

    public void setmTitle(CharSequence mTitle) {

        this.mTitle=mTitle;
        getSupportActionBar().setTitle(mTitle);
    }

    @Override
    public void onPostCreate(Bundle savedInstanceState, PersistableBundle persistentState) {
        super.onPostCreate(savedInstanceState, persistentState);
        mDrawerToggle.syncState();
    }


}
