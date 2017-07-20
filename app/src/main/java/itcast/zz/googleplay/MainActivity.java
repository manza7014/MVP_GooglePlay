package itcast.zz.googleplay;

import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentPagerAdapter;
import android.support.v4.view.MenuItemCompat;
import android.support.v4.view.PagerTabStrip;
import android.support.v4.view.ViewPager;
import android.support.v4.widget.DrawerLayout;
import android.support.v7.app.ActionBar;
import android.support.v7.app.ActionBarDrawerToggle;
import android.support.v7.widget.SearchView;
import android.support.v7.widget.Toolbar;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.FrameLayout;

import itcast.zz.googleplay.fragment.BaseFragment;
import itcast.zz.googleplay.fragment.FragmentFactory;
import itcast.zz.googleplay.holder.MenuHolder;
import itcast.zz.googleplay.utils.LogUtil;
import itcast.zz.googleplay.utils.ToastUtils;
import itcast.zz.googleplay.utils.UIUtils;

public class MainActivity extends BaseActivity implements SearchView.OnQueryTextListener {

    private static final String TAG = "Googleplay";
    private DrawerLayout drawerLayout;
    private ActionBarDrawerToggle actionBarDrawerToggle;
    private ViewPager viewPager;
    private PagerTabStrip pagerTabStrip;
    private Toolbar toolbar;
    private String[] tabNames;
    private FrameLayout flMenu;

    @Override
    protected void init() {
        super.init();
        tabNames = UIUtils.getStringArray(R.array.tab_names);
    }

    @Override
    protected void initView() {
        super.initView();
        //view初始化
        setContentView(R.layout.activity_main);
        drawerLayout = (DrawerLayout) findViewById(R.id.drawerlayout);
        viewPager = (ViewPager) findViewById(R.id.pager);
        pagerTabStrip = (PagerTabStrip) findViewById(R.id.pager_tab_strip);
        //设置下划线颜色
        pagerTabStrip.setTabIndicatorColorResource(R.color.indicatorcolor);
        flMenu = (FrameLayout) findViewById(R.id.fl_menu);
        toolbar = (Toolbar) findViewById(R.id.toolbar);
        //设置数据
        viewPager.setAdapter(new MyPagerAdapter(getSupportFragmentManager()));
        //监听切换
        viewPager.addOnPageChangeListener(new ViewPager.SimpleOnPageChangeListener(){
            @Override
            public void onPageSelected(int position) {
                super.onPageSelected(position);
                //调用show方法 切换请求服务器数据
                BaseFragment baseFragment = FragmentFactory.create(position);
                baseFragment.show();
            }
        });

        //添加菜单
        MenuHolder menuHolder = new MenuHolder();
//        menuHolder.setData();
        flMenu.addView(menuHolder.getContentView());
    }

    @Override
    protected void initToolBar() {
        super.initToolBar();
        //替换actionbar
        setSupportActionBar(toolbar);
        //显示tabs
        ActionBar actionBar = getSupportActionBar();
        actionBar.setDisplayHomeAsUpEnabled(true);
        // 实现 ActionBar& drawerLayout的联动
        actionBarDrawerToggle = new ActionBarDrawerToggle(this, drawerLayout, R.string.open, R.string.close) {
            @Override
            public void onDrawerOpened(View drawerView) {
                super.onDrawerOpened(drawerView);
                ToastUtils.showToast(MainActivity.this, "抽屉打开了");
            }

            @Override
            public void onDrawerClosed(View drawerView) {
                super.onDrawerClosed(drawerView);
                ToastUtils.showToast(MainActivity.this, "抽屉关闭了");
            }
        };

        drawerLayout.addDrawerListener(actionBarDrawerToggle);

        //同步  ActionBar DrawerLayout同步
        actionBarDrawerToggle.syncState();

    //%s 代表字符串  %d 表示数字
        LogUtil.d("%s","我是日志,能看到我吗?");
        LogUtil.e("%s","我是日志,能看到我吗?");
    }

    class MyPagerAdapter extends FragmentPagerAdapter {

        public MyPagerAdapter(FragmentManager fm) {
            super(fm);
        }

        // 返回Fragment对象
        @Override
        public Fragment getItem(int position) {
            return FragmentFactory.create(position);
        }

        //返回pager的页面数量
        @Override
        public int getCount() {
            return tabNames.length;
        }

        /*返回当前页面的标题*/
        @Override
        public CharSequence getPageTitle(int position) {
            return tabNames[position];
        }
    }


    // 应用 ActionBar上 menu,xml
    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.menu_activity_main, menu);

        // 获取searchView  等同于 findviewbyid
        SearchView searchView = (SearchView) MenuItemCompat.getActionView(menu.findItem(R.id.action_search));

        //监听
        searchView.setOnQueryTextListener(this);
        return super.onCreateOptionsMenu(menu);
    }

    //响应ActionBar上的按钮
    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        if (actionBarDrawerToggle.onOptionsItemSelected(item)) {
            return true;
        }

//        if (item.getItemId() == R.id.action_search){
//            Toast.makeText(MainActivity.this, "点击了搜索", Toast.LENGTH_SHORT).show();
//        }
        return super.onOptionsItemSelected(item);
    }

    @Override
    public boolean onQueryTextChange(String newText) {
        // 内容改变的时候调用
//        Toast.makeText(MainActivity.this, "onQueryTextChange >>"+newText, Toast.LENGTH_SHORT).show();
        ToastUtils.showToast(this, "onQueryTextChange >>" + newText);
        return true;
    }

    @Override
    public boolean onQueryTextSubmit(String query) {
        // 回车按钮的时候调用
//        Toast.makeText(MainActivity.this, "onQueryTextSubmit >>"+query, Toast.LENGTH_SHORT).show();
        ToastUtils.showToast(this, "onQueryTextSubmit >>" + query);
        return true;
    }


}
