package activity;

import android.app.Activity;
import android.content.ContentValues;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.graphics.Bitmap;
import android.graphics.drawable.Drawable;
import android.os.Bundle;
import android.os.Handler;
import android.os.Message;
import android.support.v4.util.LruCache;
import android.support.v7.app.ActionBar;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.Gravity;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;
import android.widget.Toast;

import com.android.volley.RequestQueue;
import com.android.volley.toolbox.ImageLoader;
import com.android.volley.toolbox.Volley;
import com.example.hehe.topne.R;

import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;

import java.io.IOException;
import java.text.BreakIterator;
import java.util.zip.Inflater;

import application.myapplication;
import db.sqlitehelp;

/**
 * Created by hehe on 2016/4/27.
 */
public class act1 extends AppCompatActivity implements Toolbar.OnMenuItemClickListener{
    private LinearLayout l1;
    private sqlitehelp sql;
    private SQLiteDatabase write,read;
    private TextView t1,t;
    private Elements e;
    private RequestQueue que;//初始化vollery;
    private Toolbar tb;
    private String title,url;
    private boolean flag=false;
    private myapplication my;
//动态添加控件
    private Handler h=new Handler(){
        @Override
        public void handleMessage(Message msg) {
            super.handleMessage(msg);
         switch (msg.arg1){
             case 0:
                 addimage((String)msg.obj);
                 break;
             case 1:
                 addtextview(1,(String)msg.obj);
                 break;
             case 2:
                 addtextview(2,(String)msg.obj);
                 break;
             default:
                 break;
         }
        }
    };


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.content);
            my= (myapplication) getApplication();
        this.getWindow().setBackgroundDrawable((Drawable) my.change());
        l1=(LinearLayout)findViewById(R.id.l1);
        t1=(TextView)findViewById(R.id.textView);
        tb=(Toolbar)findViewById(R.id.tl);

        setSupportActionBar(tb);

        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
       // getSupportActionBar().setDefaultDisplayHomeAsUpEnabled(true);
        //监听返回事件
        tb.setNavigationOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
               finish();;
            }
        });
        tb.setOnMenuItemClickListener(this);
        que= Volley.newRequestQueue(this);

        Bundle b=getIntent().getExtras();
        url=b.getString("url");//获取url
        title=b.getString("title");//获取title
        menu();//判断是否已收藏
        thre(b.getString("url"));
        t1.setText(b.getString("title"));


    }
    //解析网页数据
    private void thre(String ul){
        final String url=ul;
        new Thread(new Runnable() {
            @Override
            public void run() {
                try {
                    Document d= Jsoup.connect(url).get();
                    e=d.select("div.text").select("p");

                        for (Element a : e) {
                            Message m = new Message();
                            //判断里面有没有图片
                            if (a.getElementsByTag("img").attr("src").length() > 0) {
                                m.obj = a.getElementsByTag("img").attr("src");
                                m.arg1 = 0;
                                h.sendMessage(m);


                            } else {
                                //判断文字是否居中显示
                                if (a.attr("align").length() > 0) {
                                    m.arg1 = 1;
                                    m.obj = a.text();
                                    h.sendMessage(m);
                                } else {
                                    m.arg1 = 2;
                                    m.obj = a.text();
                                    h.sendMessage(m);
                                }
                            }
                        }

                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
        }).start();
    }
private void addtextview(int id,String text){
    if(id==1){
         t=new TextView(this);
        t.setLayoutParams(new LinearLayout.LayoutParams(
               ViewGroup.LayoutParams.WRAP_CONTENT, ViewGroup.LayoutParams.WRAP_CONTENT));
       // text=text.replaceAll("，",",");
        t.setText(text);
        l1.setGravity(Gravity.CENTER);//使控件居中显示

    }
    else{
        t=new TextView(this);

        t.setLayoutParams(new LinearLayout.LayoutParams(
                ViewGroup.LayoutParams.MATCH_PARENT, ViewGroup.LayoutParams.WRAP_CONTENT));

        t.setText(text);
    }
    t.setTextSize(20);
    l1.addView(t);

}


    private void addimage(String url){
        ImageView ig=new ImageView(this);
        ig.setLayoutParams(new LinearLayout.LayoutParams(
                ViewGroup.LayoutParams.WRAP_CONTENT, ViewGroup.LayoutParams.WRAP_CONTENT));
        l1.setGravity(Gravity.CENTER);
        final LruCache<String, Bitmap> mImageCache = new LruCache<String, Bitmap>(
                20);
        ImageLoader.ImageCache imageCache = new ImageLoader.ImageCache() {
            @Override
            public void putBitmap(String key, Bitmap value) {
                mImageCache.put(key, value);
            }

            @Override
            public Bitmap getBitmap(String key) {
                return mImageCache.get(key);
            }
        };
        ImageLoader mImageLoader = new ImageLoader(que, imageCache);
        // imageView是一个ImageView实例
        // ImageLoader.getImageListener的第二个参数是默认的图片resource id
        // 第三个参数是请求失败时候的资源id，可以指定为0
        ImageLoader.ImageListener listener = ImageLoader
                .getImageListener(ig, android.R.drawable.ic_menu_rotate,
                        android.R.drawable.ic_delete);
        mImageLoader.get(url, listener);
        l1.addView(ig);
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        if(flag==true) {
            getMenuInflater().inflate(R.menu.colletrue, menu);
        }
        else{
            getMenuInflater().inflate(R.menu.coll, menu);
        }
        return true;
    }

    @Override
    public boolean onMenuItemClick(MenuItem item) {
       // Toast.makeText(this,""+item.getItemId(),Toast.LENGTH_LONG).show();

        if(item.getItemId()==R.id.coll){
            //写入数据库
            write();
        }

        return  true;
    }
    private void write() {


        sql = new sqlitehelp(this);
        read = sql.getReadableDatabase();
        Cursor cr = read.rawQuery("select * from collect where url=?", new String[]{url});
        if (cr.moveToFirst()) {
            Toast.makeText(this, "已存在", Toast.LENGTH_LONG).show();
            read.close();
        }
         else {
            write = sql.getWritableDatabase();
            ContentValues c = new ContentValues();
            c.put("title", title);
            c.put("url", url);
            write.insert("collect", null, c);
            Toast.makeText(this,"收藏成功",Toast.LENGTH_LONG).show();
            write.close();

        }
    }
    private void menu(){
        sql = new sqlitehelp(this);
        read = sql.getReadableDatabase();
        Cursor cr = read.rawQuery("select * from collect where url=?", new String[]{url});
        if (cr.moveToFirst()) {
           flag=true;
        }
        cr.close();
        read.close();

    }

}
