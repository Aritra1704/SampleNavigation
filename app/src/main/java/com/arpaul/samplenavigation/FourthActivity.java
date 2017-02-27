package com.arpaul.samplenavigation;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.view.GravityCompat;
import android.support.v4.widget.DrawerLayout;
import android.support.v7.app.ActionBarDrawerToggle;
import android.support.v7.app.AppCompatActivity;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.ExpandableListAdapter;
import android.widget.ExpandableListView;
import android.widget.Toast;

import com.arpaul.samplenavigation.adapter.CustomExpandableListAdapter;

import java.util.ArrayList;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;

/**
 * Created by Aritra on 27-02-2017.
 */

public class FourthActivity extends AppCompatActivity {

    private ExpandableListView navList;
    private DrawerLayout mDrawerLayout;
    private ActionBarDrawerToggle mDrawerToggle;
    private String mActivityTitle;

    private ExpandableListAdapter mExpandableListAdapter;

    private List<String> mExpandableListTitle;
    private Map<String, List<String>> mExpandableListData;
    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_fourth);

        initialiseControls();

        bindControls();
    }

    void bindControls() {
        View listHeaderView = LayoutInflater.from(this).inflate(R.layout.nav_header_main, null, false);
        navList.addHeaderView(listHeaderView);

        addDrawerItems();
        setupDrawer();

        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        getSupportActionBar().setHomeButtonEnabled(true);
    }

    void addDrawerItems() {

        mExpandableListTitle = new ArrayList<String>();
        mExpandableListTitle.add("Groups");
        mExpandableListTitle.add("Group Leaders");
        mExpandableListTitle.add("Group Members");

        mExpandableListData = new LinkedHashMap<>();
        List<String> list = new ArrayList<String>();
        list.add("Group1");
        list.add("Group2");
        list.add("Group3");
        mExpandableListData.put("Groups", list);

        list = new ArrayList<String>();
        list.add("Groupleader1");
        list.add("Groupleader2");
        list.add("Groupleader3");
        list.add("Groupleader4");
        mExpandableListData.put("Group Leaders", list);

        list = new ArrayList<String>();
        list.add("Randy Curtis");
        list.add("Edward Lynch");
        list.add("Kenneth Barnes");
        list.add("Ben Curtis");
        mExpandableListData.put("Group Members", list);

        mExpandableListAdapter = new CustomExpandableListAdapter(this, mExpandableListTitle, mExpandableListData);
        navList.setAdapter(mExpandableListAdapter);
    }

    private void setupDrawer() {
        mDrawerToggle = new ActionBarDrawerToggle(this, mDrawerLayout, R.string.navigation_drawer_open, R.string.navigation_drawer_close) {

            /** Called when a drawer has settled in a completely open state. */
            public void onDrawerOpened(View drawerView) {
                super.onDrawerOpened(drawerView);
                getSupportActionBar().setTitle(R.string.app_name);
                invalidateOptionsMenu(); // creates call to onPrepareOptionsMenu()
            }

            /** Called when a drawer has settled in a completely closed state. */
            public void onDrawerClosed(View view) {
                super.onDrawerClosed(view);
                getSupportActionBar().setTitle(mActivityTitle);
                invalidateOptionsMenu(); // creates call to onPrepareOptionsMenu()
            }
        };

        navList.setOnGroupExpandListener(new ExpandableListView.OnGroupExpandListener() {
            @Override
            public void onGroupExpand(int groupPosition) {
                getSupportActionBar().setTitle(mExpandableListTitle.get(groupPosition).toString());
            }
        });

        navList.setOnGroupCollapseListener(new ExpandableListView.OnGroupCollapseListener() {
            @Override
            public void onGroupCollapse(int groupPosition) {
                Toast.makeText(FourthActivity.this, "Group position: " + groupPosition, Toast.LENGTH_SHORT).show();
            }
        });

        navList.setOnChildClickListener(new ExpandableListView.OnChildClickListener() {
            @Override
            public boolean onChildClick(ExpandableListView parent, View v,
                                        int groupPosition, int childPosition, long id) {
                String selectedItem = ((List) (mExpandableListData.get(mExpandableListTitle.get(groupPosition))))
                        .get(childPosition).toString();
                getSupportActionBar().setTitle(selectedItem);

                Toast.makeText(FourthActivity.this, mExpandableListTitle.get(groupPosition), Toast.LENGTH_SHORT).show();

                mDrawerLayout.closeDrawer(GravityCompat.START);
                return false;
            }
        });

        mDrawerToggle.setDrawerIndicatorEnabled(true);
        mDrawerLayout.setDrawerListener(mDrawerToggle);
    }

    @Override
    protected void onPostCreate(Bundle savedInstanceState) {
        super.onPostCreate(savedInstanceState);
        // Sync the toggle state after onRestoreInstanceState has occurred.
        mDrawerToggle.syncState();
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_fourth, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        int id = item.getItemId();

        // Activate the navigation drawer toggle
        if (mDrawerToggle.onOptionsItemSelected(item)) {
            return true;
        }

        return super.onOptionsItemSelected(item);
    }

    void initialiseControls() {
        navList = (ExpandableListView)findViewById(R.id.navList);

        mDrawerLayout = (DrawerLayout) findViewById(R.id.drawer_layout);
        mActivityTitle = getTitle().toString();
    }
}
