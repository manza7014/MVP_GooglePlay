package itcast.zz.googleplay;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.widget.Toolbar;

import itcast.zz.googleplay.fragment.DetailFragment;

public class DetailActivity extends BaseActivity {


    private Toolbar toolbar;
    private String packageName;

    @Override
    protected void init() {
        super.init();
        Intent intent = getIntent();
        packageName = intent.getStringExtra("packageName");
    }

    @Override
    protected void initView() {
        super.initView();
        setContentView(R.layout.activity_detail);
        toolbar = (Toolbar) findViewById(R.id.toolbar);

        //替换为Fragment
        DetailFragment fragment = new DetailFragment();

        //fragment传递数据
        Bundle bundle = new Bundle();
        bundle.putString("packageName",packageName);
        fragment.setArguments(bundle);
        getSupportFragmentManager()
                .beginTransaction()
                .replace(R.id.fl_container, fragment)
                .commit();
    }

    @Override
    protected void initToolBar() {
        super.initToolBar();
        setSupportActionBar(toolbar);
        // 显示返回按钮
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
    }
}
