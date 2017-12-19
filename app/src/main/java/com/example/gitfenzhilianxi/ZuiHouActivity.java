package com.example.gitfenzhilianxi;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.view.ViewGroup;
import android.webkit.WebResourceRequest;
import android.webkit.WebView;
import android.webkit.WebViewClient;
import android.widget.ImageView;

import com.jude.rollviewpager.RollPagerView;
import com.jude.rollviewpager.adapter.StaticPagerAdapter;

import java.io.Serializable;
import java.lang.reflect.Field;

public class ZuiHouActivity extends AppCompatActivity {

    private RollPagerView roll;
    private WebView webView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        initView();
        initData();
    }

    private void initData() {
        Intent intent = getIntent();
        MyData.ResultBean.DataBean user = (MyData.ResultBean.DataBean) intent.getSerializableExtra("user");
        final String url = user.getUrl();

        webView.loadUrl(url);
        webView.getSettings().setJavaScriptEnabled(true);
        webView.setWebViewClient(new WebViewClient() {
            @Override
            public boolean shouldOverrideUrlLoading(WebView view, WebResourceRequest request) {
                webView.loadUrl(url);
                return super.shouldOverrideUrlLoading(view, request);
            }
        });
        roll.setPlayDelay(1000);
        roll.setAdapter(new StaticPagerAdapter() {
            int[] ii = {R.mipmap.ic_launcher, R.mipmap.ic_launcher_round};

            @Override
            public View getView(ViewGroup container, int position) {
                ImageView imageView = new ImageView(ZuiHouActivity.this);
                imageView.setImageResource(ii[position]);
                return imageView;
            }

            @Override
            public int getCount() {
                return ii.length;
            }
        });
    }

    private void initView() {
        roll = (RollPagerView) findViewById(R.id.roll);
        webView = (WebView) findViewById(R.id.webView);
    }
}
