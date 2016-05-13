package fragmentactivity;

import android.app.Activity;
import android.graphics.drawable.Drawable;
import android.location.Location;
import android.os.Bundle;

import application.myapplication;
import  location.location;

import android.os.Handler;
import android.os.Message;
import android.provider.Settings;
import android.support.design.widget.NavigationView;
import android.support.v4.app.FragmentManager;
import android.support.v4.widget.DrawerLayout;
import android.support.v7.app.ActionBarDrawerToggle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.FrameLayout;
import android.widget.TextView;
import android.widget.Toast;


import com.example.hehe.topne.MainActivity;
import com.example.hehe.topne.R;

import org.jsoup.safety.Cleaner;

import java.util.zip.Inflater;

import fragment.fr1;


/**
 * Created by hehe on 2016/4/28.
 */
public class fragmentlayout extends AppCompatActivity implements Toolbar.OnMenuItemClickListener,NavigationView.OnNavigationItemSelectedListener {
    private DrawerLayout dl;
    private NavigationView nv;
    public Toolbar tb;
    private ActionBarDrawerToggle ab;
    private MainActivity m;
    private FrameLayout fra;
    public TextView t;
    private FragmentManager f=getSupportFragmentManager();
    private myapplication my;
    private long starttime;//代表第一次按退出的时间
    private int count=0;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.fragmentlayout);
        my= (myapplication) getApplication();
       this.getWindow().setBackgroundDrawable((Drawable) my.change());

        t=(TextView)findViewById(R.id.weather);

        init();

        home();
    }

    //重写onbackpressed方法，
    @Override
    public void onBackPressed() {
       // super.onBackPressed();
        //判断是否选择了连续两次退出
        if(my.finsh) {

           if (count == 1) {
                if (System.currentTimeMillis() - starttime < 1000) {
                  this.finish();
                } else {
                    count = 0;
                }
            }

           else if (count == 0) {
               starttime = System.currentTimeMillis();
               count++;
               Toast.makeText(this, "再按一次退出", Toast.LENGTH_SHORT).show();
           }
        }
        else{
            super.onBackPressed();
        }
    }


    //设置toolbar以及滑动菜单栏
    private void init(){
        dl=(DrawerLayout) findViewById(R.id.drawerlayout);
        nv=(NavigationView)findViewById(R.id.navigation_view);
        fra=(FrameLayout)findViewById(R.id.framelayout);
        tb=(Toolbar)findViewById(R.id.tl);
        setSupportActionBar(tb);//将内容及其他东西放入toolbar
        getSupportActionBar().setHomeButtonEnabled(true); //设置返回键可用
        ab=new ActionBarDrawerToggle(this,dl,tb,R.string.open,R.string.close){
            @Override
            //打开抽屉执行事件
            public void onDrawerOpened(View drawerView) {

                //Toast.makeText(fragmentlayout.this, "setting", Toast.LENGTH_LONG).show();
                location l=new location( fragmentlayout.this,h);
                l.init();;
            }
//关闭抽屉执行事件
            @Override
            public void onDrawerClosed(View drawerView) {
                super.onDrawerClosed(drawerView);

              //  Toast.makeText(fragmentlayout.this, "setting12345", Toast.LENGTH_LONG).show();
//                location l=new location( fragmentlayout.this);
//                l.close();
            }
        };

        ab.syncState();;
        dl.setDrawerListener(ab);

        tb.setOnMenuItemClickListener(this);
        nv.setNavigationItemSelectedListener(this);
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {

        tb.inflateMenu(R.menu.item);
        return true;
    }
//菜单栏监听事件
    @Override
    public boolean onMenuItemClick(MenuItem item) {
        switch (item.getItemId()) {
            case R.id.home:
                Toast.makeText(this, "gg", Toast.LENGTH_LONG).show();
                break;
            case R.id.action_settings:
                Toast.makeText(this, "setting", Toast.LENGTH_LONG).show();

                break;

            case R.id.ab_search:
                Toast.makeText(this, "search", Toast.LENGTH_LONG).show();
                break;

            case R.id.action_share:
                Toast.makeText(this, "share", Toast.LENGTH_LONG).show();
                break;
            default:
                break;
        }

        return true;
    }
//侧滑菜单监听事件
    @Override
    public boolean onNavigationItemSelected(MenuItem item) {
        switch (item.getItemId()){
            case R.id.home:

                    home();

                break;
            case R.id.collect:

              collect c=new collect();
                FragmentManager f1=getSupportFragmentManager();
             f1.beginTransaction().replace(R.id.framelayout,c ).commit();
                break;
            case R.id.setting:
               setting s=new setting();
                f.beginTransaction().replace(R.id.framelayout,s).commit();
                break;
            case R.id.tell:
                feedback feedback=new feedback();
                f.beginTransaction().replace(R.id.framelayout,feedback).commit();

                break;
            case R.id.finish:
                this.finish();
                break;
            default:
                break;
        }
dl.closeDrawers();
        return true;
    }
    //初始化主页
    private void home(){

      MainActivity m=new MainActivity();
       FragmentManager fragmentManager=getSupportFragmentManager();
        fragmentManager.beginTransaction().replace(R.id.framelayout,m ).commit();
    }
    public Handler h=new Handler(){
        @Override
        public void handleMessage(Message msg) {
            super.handleMessage(msg);
//            if(msg.arg1==1){
//                Log.d("haha","nima");
//
//                tb.setBackgroundDrawable((Drawable) my.change());
//            }else {
            t.setText((String)msg.obj);
        }
    };


}
