package com.example.fpm0322.myfirstapp.activity;

import android.Manifest;
import android.annotation.SuppressLint;
import android.content.ComponentName;
import android.content.Context;
import android.content.Intent;
import android.content.ServiceConnection;
import android.content.pm.PackageManager;
import android.net.Uri;
import android.os.Handler;
import android.os.IBinder;
import android.os.Message;
import android.os.RemoteException;
import android.support.v4.app.ActivityCompat;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import com.example.fpm0322.aidldemo.Book;
import com.example.fpm0322.aidldemo.BookManager;
import com.example.fpm0322.myfirstapp.R;

import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;

public class TestAidlActivity extends AppCompatActivity {

    @BindView(R.id.image)
    TextView image;

    @BindView(R.id.book_name)
    TextView bookName;

    @BindView(R.id.book_price)
    TextView bookPrice;

    //由AIDL文件生成的Java类
    private BookManager mBookManager = null;

    //标志当前与服务端连接状况的布尔值，false为未连接，true为连接中
    private boolean mBound = false;

    //包含Book对象的list
    private List<Book> mBooks;
    @SuppressLint("HandlerLeak")
    private Handler mHandler = new Handler() {
        @Override
        public void handleMessage(Message msg) {
            super.handleMessage(msg);
            switch (msg.what) {
                case 1:
                    List<Book> books = (List<Book>) msg.obj;
                    if (null != books && books.size() > 0) {
                        Book book = books.get(books.size() - 1);
                        bookName.setText(book.getBookName());
                        bookPrice.setText(book.getBookPrice() + "");
                    }
                    break;
            }
        }
    };
    private ServiceConnection mServiceConnection = new ServiceConnection() {
        @Override
        public void onServiceConnected(ComponentName componentName, IBinder iBinder) {
            Log.e(getLocalClassName(), "service connected");
            mBookManager = BookManager.Stub.asInterface(iBinder);
            mBound = true;
            if (mBookManager != null) {
                try {
                    mBooks = mBookManager.getBooks();
                    Message msg = new Message();
                    msg.what = 1;
                    msg.obj = mBooks;
                    mHandler.sendMessage(msg);
                    Log.e(getLocalClassName(), mBooks.toString());
                } catch (RemoteException e) {
                    e.printStackTrace();
                }
            }
        }

        @Override
        public void onServiceDisconnected(ComponentName componentName) {
            if (mBookManager != null) {
                Log.e(getLocalClassName(), "service disconnected");
                mBound = false;
            }
        }
    };

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_test_aidl);
        ButterKnife.bind(this);
    }

    @Override
    protected void onStart() {
        super.onStart();
        if (!mBound) {
            atteptBindSerice();
        }
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        if (mBound) {
            unbindService(mServiceConnection);
            mBound = false;
        }
    }

    /**
     * 按钮的点击事件，点击之后调用服务端的addBookIn方法
     */
    public void addBook() {
        //如果与服务端的连接处于未连接状态，则尝试连接
        if (!mBound) {
            atteptBindSerice();
            Toast.makeText(this, "当前与服务端处于未连接状态，正在尝试重连，请稍后再试", Toast.LENGTH_SHORT).show();
            return;
        }
        if (mBookManager == null) return;

        Book book = new Book();
        book.setBookName("APP研发录In");
        book.setBookPrice(30);
        try {
            mBookManager.addBook(book);
            mBooks = mBookManager.getBooks();
            Message msg = new Message();
            msg.what = 1;
            msg.obj = mBooks;
            mHandler.sendMessage(msg);
            Log.e(getLocalClassName(), mBooks.toString());
        } catch (RemoteException e) {
            e.printStackTrace();
        }
    }

    private void atteptBindSerice() {
        Intent intent = new Intent();
        intent.setAction("com.fpm0322.aidldemo");
        intent.setPackage("com.eg.android.AlipayGphone");
        bindService(intent, mServiceConnection, Context.BIND_AUTO_CREATE);
    }

    /**
     * 穿越到其他应用
     */
    @OnClick(R.id.btn_add)
    void transPort() {
        Intent intent = new Intent(Intent.ACTION_VIEW, Uri.parse("http://www.baidu.com"));
        if (ActivityCompat.checkSelfPermission(this, Manifest.permission.CALL_PHONE) != PackageManager.PERMISSION_GRANTED) {

            return;
        }
        startActivity(intent);
    }
}
