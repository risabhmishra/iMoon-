package niot.imoon;

import android.support.annotation.IdRes;
import android.support.v4.view.ViewPager;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.MenuItem;

import com.ToxicBakery.viewpager.transforms.CubeInTransformer;
import com.ToxicBakery.viewpager.transforms.CubeOutTransformer;
import com.ToxicBakery.viewpager.transforms.RotateUpTransformer;
import com.roughike.bottombar.BottomBar;
import com.roughike.bottombar.BottomBarTab;
import com.roughike.bottombar.OnTabReselectListener;
import com.roughike.bottombar.OnTabSelectListener;

public class MainActivity extends AppCompatActivity {
ViewPager pager;
    private aboutus frag_about;
    private dataavailability frag_dataavail;
    private developers frag_dev;
    private realtimedata frag_realtdata;
    private tutorial frag_tut;
    BottomBarTab prevMenuItem;
    BottomBar bottomBar;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        pager = (ViewPager)findViewById(R.id.viewpager);
        setUpViewPager(pager);

        bottomBar = (BottomBar) findViewById(R.id.bottomBar);

        bottomBar.setOnTabSelectListener(new OnTabSelectListener() {
            @Override
            public void onTabSelected(@IdRes int tabId) {
                if (tabId == R.id.aboutus) {
                    pager.setCurrentItem(0);
                } else
                if (tabId == R.id.tutorial) {
                    pager.setCurrentItem(1);
                } else
                if (tabId == R.id.realtimedata) {
                    pager.setCurrentItem(2);
                } else
                if (tabId == R.id.dataavailability) {
                    pager.setCurrentItem(3);
                } else
                if (tabId == R.id.developers) {
                    pager.setCurrentItem(4);
                }
            }
        });

        bottomBar.setOnTabReselectListener(new OnTabReselectListener() {
            @Override
            public void onTabReSelected(@IdRes int tabId) {
                if (tabId == R.id.aboutus) {
                    pager.setCurrentItem(0);
                } else
                if (tabId == R.id.tutorial) {
                    pager.setCurrentItem(1);
                } else
                if (tabId == R.id.realtimedata) {
                    pager.setCurrentItem(2);
                } else
                if (tabId == R.id.dataavailability) {
                    pager.setCurrentItem(3);
                } else
                if (tabId == R.id.developers) {
                    pager.setCurrentItem(4);
                }
            }
        });



    }

    private void setUpViewPager(ViewPager pager) {



        ViewPagerAdapter adapter = new ViewPagerAdapter(getSupportFragmentManager());
        frag_about = new aboutus();
        frag_dataavail = new dataavailability();
        frag_dev = new developers();
        frag_realtdata = new realtimedata();
        frag_tut = new tutorial();

        adapter.addFragment(frag_about);
        adapter.addFragment(frag_tut);
        adapter.addFragment(frag_realtdata);
        adapter.addFragment(frag_dataavail);
        adapter.addFragment(frag_dev);

        pager.setAdapter(adapter);
        pager.setPageTransformer(true, new CubeOutTransformer());



        pager.addOnPageChangeListener(new ViewPager.OnPageChangeListener() {
            @Override
            public void onPageScrolled(int position, float positionOffset, int positionOffsetPixels) {

            }

            @Override
            public void onPageSelected(int position) {
                if (prevMenuItem != null) {
                    prevMenuItem.setActivated(false);
                } else {
                    bottomBar.getTabAtPosition(0).setActivated(false);

                }

                bottomBar.getTabAtPosition(position).setActivated(true);
                prevMenuItem = bottomBar.getTabAtPosition(position);
            }

            @Override
            public void onPageScrollStateChanged(int state) {


            }
        });

    }

}

