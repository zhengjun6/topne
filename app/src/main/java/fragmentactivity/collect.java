package fragmentactivity;

import android.app.ProgressDialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v4.view.ViewPager;
import android.support.v7.app.AlertDialog;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.BaseAdapter;
import android.widget.LinearLayout;
import android.widget.LinearLayout.LayoutParams;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.Toast;

import com.example.hehe.topne.R;

import java.util.ArrayList;
import java.util.List;

import activity.act1;
import db.sqlitehelp;

/**
 * Created by Administrator on 2016/5/4.
 */
public class collect extends Fragment implements AdapterView.OnItemClickListener,AdapterView.OnItemLongClickListener{
    private ListView lv;
    private TextView t;
    private List<collectdata> list=new ArrayList<>();
    private sqlitehelp sql;
    private SQLiteDatabase read, write;
    private collectdata collectdata;
    private collectadapter adapter;
   private AlertDialog.Builder ad;

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.collect, container, false);
        lv = (ListView) view.findViewById(R.id.listView2);
        t=(TextView)view.findViewById(R.id.textView4);

        init();//初始化读数据库
        list();
        lv.setOnItemClickListener(this);
        lv.setOnItemLongClickListener(this);
        return view;
    }

    private void list(){

//        if(list.size()>0){
//            adapter=new collectadapter(getContext(),list);
//            lv.setAdapter(adapter);
//        }
//        else{
        if(list.size()>0){
            t.setVisibility(View.INVISIBLE);
            adapter=new collectadapter(getContext(),list);
           lv.setAdapter(adapter);
        }
        else{
            lv.setVisibility(View.GONE);
            t.setText("收藏夹为空");
            t.setTextSize(25);
        }
    }
//读取数据库的数据，并传给list数组
    private void init() {
        sql = new sqlitehelp(getActivity());
        read = sql.getReadableDatabase();
        Cursor c = read.rawQuery("select * from collect", null);
        while (c.moveToNext()) {
            String title = c.getString(1);
            String url = c.getString(2);
            int id = c.getInt(0);
            collectdata =new collectdata(id,title,url);
            list.add(collectdata);
           // Toast.makeText(getActivity(),"123435"+title,Toast.LENGTH_LONG).show();
       }
    }

    @Override
    public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
        Intent i=new Intent(getActivity(),act1.class);
        Bundle b=new Bundle();
        b.putString("title", list.get(position).getTitle());
        b.putString("url", list.get(position).getUrl());
        i.putExtras(b);
        startActivity(i);

    }

    @Override
    public boolean onItemLongClick(AdapterView<?> parent, View view, final int position, long id) {
       ad=new  AlertDialog.Builder(getActivity());
        ad.setTitle("提示");
        ad.setMessage("是否删除");
        ad.setCancelable(false);//只有点击提示框的按钮才能结束对话框
        ad.setPositiveButton("是", new DialogInterface.OnClickListener()
        {
            @Override
            public void onClick(DialogInterface dialog, int which)
            {
                delate(list.get(position).getId());
                adapter.notifyDataSetChanged();
                list.clear();//先清除list的原有数据，防止重复
                init();
                adapter=new collectadapter(getActivity(),list);
                lv.setAdapter(adapter);
               // init();
                //Toast.makeText(MainActivity.this, ""+a.list.get(id).getId(), 1000).show();


            }
        });
        //    设置一个NegativeButton
        ad.setNegativeButton("否", new DialogInterface.OnClickListener()
        {
            @Override
            public void onClick(DialogInterface dialog, int which)
            {

            }
        });
        ad.show();

        return true;
    }

    private void delate(int id){
        sql = new sqlitehelp(getActivity());
        write=sql.getWritableDatabase();
        write.delete("collect","id=?",new String[]{""+id});
        write.close();

    }


}

