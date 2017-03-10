package com.example.listviewtest;

import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;

import android.os.Bundle;
import android.app.Activity;
import android.content.Context;
import android.text.TextUtils;
import android.view.Menu;
import android.view.MotionEvent;
import android.view.View;
import android.view.inputmethod.InputMethodManager;
import android.widget.ListView;
import android.widget.TextView;

public class MainActivity extends Activity {

	private Context mContext;
    private ListView list_book;
    private ListView list_app;

    private MyAdapter<App> myAdapter1 = null;
    private MyAdapter<Book> myAdapter2 = null;
    private List<App> mData1 = null;
    private List<Book> mData2 = null;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        mContext = MainActivity.this;
        init();

    }

    private void init() {

        list_book = (ListView) findViewById(R.id.list_book);
        list_app = (ListView) findViewById(R.id.list_app);

        //���ݳ�ʼ��
        mData1 = new ArrayList<App>();
        mData1.add(new App(R.mipmap.iv_icon_baidu,"�ٶ�"));
        mData1.add(new App(R.mipmap.iv_icon_douban,"����"));
        mData1.add(new App(R.mipmap.iv_icon_zhifubao,"֧����"));

        mData2 = new ArrayList<Book>();
        mData2.add(new Book("����һ�д���Android��","����"));
        mData2.add(new Book("��AndroidȺӢ����","������"));
        mData2.add(new Book("��Android��������̽����","�����"));

        //Adapter��ʼ��
        myAdapter1 = new MyAdapter<App>((ArrayList)mData1,R.layout.item_one) {
            @Override
            public void bindView(ViewHolder1 holder, App obj) {
                holder.setImageResource(R.id.img_icon,obj.getaIcon());
                holder.setText(R.id.txt_aname,obj.getaName());
            }
        };
        myAdapter2 = new MyAdapter<Book>((ArrayList)mData2,R.layout.item_two) {
            @Override
            public void bindView(ViewHolder1 holder, Book obj) {
                holder.setText(R.id.txt_bname,obj.getbName());
                holder.setText(R.id.txt_bauthor,obj.getbAuthor());
            }
        };

        //ListView������Adapter��
        list_book.setAdapter(myAdapter2);
        list_app.setAdapter(myAdapter1);

    }


    public boolean onTouchEvent(MotionEvent event) {
        //if(null != this.getCurrentFocus()){
        if(getCurrentFocus()!=null && getCurrentFocus().getWindowToken()!=null){  
            /**
             * ����հ�λ�� ���������
             */
            InputMethodManager mInputMethodManager = (InputMethodManager) getSystemService(INPUT_METHOD_SERVICE);
            return mInputMethodManager.hideSoftInputFromWindow(this.getCurrentFocus().getWindowToken(), 0);
        }
        return super .onTouchEvent(event);
    }
}
