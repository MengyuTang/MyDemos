package com.example.fpm0322.myfirstapp.activity;

import android.annotation.SuppressLint;
import android.content.Intent;
import android.databinding.DataBindingUtil;
import android.graphics.Bitmap;
import android.os.Handler;
import android.os.Message;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.widget.ImageView;
import android.widget.TextView;

import com.android.volley.RequestQueue;
import com.android.volley.toolbox.ImageLoader;
import com.android.volley.toolbox.Volley;
import com.example.fpm0322.aidldemo.Book;
import com.example.fpm0322.myfirstapp.Api;
import com.example.fpm0322.myfirstapp.Constants;
import com.example.fpm0322.myfirstapp.R;
import com.example.fpm0322.myfirstapp.bean.BaseBean;
import com.example.fpm0322.myfirstapp.bean.ImageBean;
import com.example.fpm0322.myfirstapp.databinding.ActivityDataBinding;
import com.example.fpm0322.myfirstapp.imageutils.BitCache;

import java.io.Serializable;
import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;
import io.reactivex.Observer;
import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.disposables.Disposable;
import io.reactivex.functions.Function;
import io.reactivex.schedulers.Schedulers;
import okhttp3.ResponseBody;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

public class DBActivity extends AppCompatActivity {

    private final int LOADIMAGE = 123;
    @BindView(R.id.tv_next_page)
    TextView tvNexPage;
    @BindView(R.id.iv)
    ImageView imageView;
    private Api mApi;
    private ActivityDataBinding dataBinding;
    private int n = 0;
    private List<ImageBean> datas;
    @SuppressLint("HandlerLeak")
    private Handler mHandler = new Handler() {
        @Override
        public void handleMessage(Message msg) {
            switch (msg.what) {
                case LOADIMAGE:
                    ImageBean bean = (ImageBean) msg.obj;
                    if (null != bean) {
                        initImageLoader(imageView, bean.getImage_url());
                    }
                    break;
            }
        }
    };

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        dataBinding = DataBindingUtil.setContentView(this, R.layout.activity_data);
        ButterKnife.bind(this);
        initData();
        getImageUrl();
    }

    public void initData() {
        Book book = new Book();
        book.setBookPrice(12.00);
        book.setBookName("《Android开发艺术探索》");
        dataBinding.setBook(book);
    }

    public void getImageUrl() {

        Retrofit retrofit = new Retrofit.Builder()
                .addConverterFactory(GsonConverterFactory.create())
                .baseUrl(Constants.BaseUrl)
                .build();
        mApi = retrofit.create(Api.class);
        Call<BaseBean> images = mApi.getImageUrls("0", "30", "壁纸", "全部", "utf8");
        images.enqueue(new Callback<BaseBean>() {
            @Override
            public void onResponse(Call<BaseBean> call, Response<BaseBean> response) {
                if (response.isSuccessful()) {
                    BaseBean bean = response.body();
                    datas = bean.getData();
                    Log.e("getImage（）", "data:" + datas.get(0).getImage_url());
                    mHandler.sendMessage(Message.obtain(mHandler, LOADIMAGE, datas.get(0)));
                } else {
                    Log.e("getImage（）", "error:" + response.errorBody());
                }
            }

            @Override
            public void onFailure(Call<BaseBean> call, Throwable t) {
                t.printStackTrace();
                Log.e("getImage（）", "error:" + call.isExecuted());
            }
        });

    }

    @OnClick(R.id.tv_next_page)
    void nextPage() {
        if (null != datas) {
            if (datas.size() >= ++n) {
                ImageBean bean = datas.get(n);
                mHandler.sendMessage(Message.obtain(mHandler, LOADIMAGE, bean));
            }
        }
    }

    @OnClick(R.id.tv_all)
    void seeAll() {
        Bundle bundle = new Bundle();
        Intent intent = new Intent();
        bundle.putSerializable("datas", (Serializable) datas);
        intent.putExtras(bundle);
        intent.setClass(DBActivity.this, ImageListActivity.class);
        startActivity(intent);
    }

    public void initImageLoader(ImageView iv, String url) {
        RequestQueue requestQueue = Volley.newRequestQueue(DBActivity.this);
        ImageLoader loader = new ImageLoader(requestQueue, new BitCache());
        ImageLoader.ImageListener listener = ImageLoader.getImageListener(iv, R.mipmap.ic_launcher, R.mipmap.ic_launcher);
        loader.get(url, listener, 500, 500);
    }


}
