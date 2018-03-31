package com.tnn_inc.writgear.view;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentTransaction;
import android.support.v4.view.GravityCompat;
import android.support.v4.widget.DrawerLayout;
import android.support.v7.app.ActionBar;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;

import com.squareup.leakcanary.LeakCanary;
import com.tnn_inc.writgear.R;
import com.tnn_inc.writgear.view.fragment.CreateNoteFragment;
import com.tnn_inc.writgear.view.fragment.MainFragment;

import java.util.Objects;

import butterknife.BindView;
import butterknife.ButterKnife;

public class WringGearApplication extends AppCompatActivity implements ActivityCallback{

    private static String TAG = "MainWringGearApplication";

    private FragmentManager fragmentManager;
    private DrawerLayout mDrawerLayout;
    private String fragment = "";

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        if (LeakCanary.isInAnalyzerProcess(this)) {
            // This process is dedicated to LeakCanary for heap analysis.
            // You should not init your app in this process.
            return;
        }
        LeakCanary.install(this.getApplication());

        setContentView(R.layout.main_activity);
        mDrawerLayout = findViewById(R.id.drawer_layout);

        Toolbar toolbar = findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        ActionBar actionbar = getSupportActionBar();
        actionbar.setDisplayHomeAsUpEnabled(true);
        actionbar.setDisplayShowTitleEnabled(false);
        actionbar.setHomeAsUpIndicator(R.drawable.ic_menu);

        fragmentManager = getSupportFragmentManager();
        Fragment fragment = fragmentManager.findFragmentByTag(TAG);

        if (fragment == null) replaceFragment(new MainFragment(), false);
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getActivity().getMenuInflater().inflate(R.menu.menu_main, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        switch (item.getItemId()) {
            case android.R.id.home:
                if(this.fragment.equals("MainFragment"))
                    mDrawerLayout.openDrawer(GravityCompat.START);
                else onBackPressed();
                return true;
        }
        return super.onOptionsItemSelected(item);
    }

    private void replaceFragment(Fragment fragment, boolean addBackStack) {
        FragmentTransaction transaction = fragmentManager.beginTransaction();
        transaction.replace(R.id.container, fragment, TAG);
        if (addBackStack) transaction.addToBackStack(null);
        transaction.commit();
    }

    @Override
    public void startCreateNote() {
        replaceFragment(CreateNoteFragment.newInstance(), true);
    }

    @Override
    public AppCompatActivity getActivity() {
        return this;
    }

    @Override
    public void setFragmentName(String fragmentName) {
        ActionBar actionbar = getSupportActionBar();
        this.fragment = fragmentName;
        if (fragmentName.equals("MainFragment"))
            actionbar.setHomeAsUpIndicator(R.drawable.ic_menu);
        else
            actionbar.setHomeAsUpIndicator(R.drawable.ic_back);

    }


}
