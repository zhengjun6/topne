package fragment;

import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;
import android.os.Message;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.Toast;

import com.example.hehe.topne.R;
import com.example.hehe.topne.data;
import com.example.hehe.topne.dataadapter;
import com.example.hehe.topne.fragment;
import com.example.hehe.topne.task;
import com.handmark.pulltorefresh.library.PullToRefreshBase;
import com.handmark.pulltorefresh.library.PullToRefreshListView;

import java.util.List;

import activity.act1;

/**
 * Created by hehe on 2016/4/22.
 */
public class fr1 extends Fragment implements PullToRefreshBase.OnRefreshListener,AdapterView.OnItemClickListener{
    private ListView l;
    private task t;
    private List<data> list;
    private PullToRefreshListView pf;
    private  dataadapter d;
    private Handler h=new Handler(){
        @Override
        public void handleMessage(Message msg) {
            super.handleMessage(msg);
            if(msg.arg1==1){
                list= (List<data>) msg.obj;
                 d=new dataadapter(getActivity(),list);
                pf.setAdapter(d);
            }
            else{
                Toast.makeText(getActivity(),"error",Toast.LENGTH_LONG).show();
            }
            pf.onRefreshComplete();//刷新完毕，隐藏刷新图标
        }

    };

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view=inflater.inflate(R.layout.fr2,container,false);
      //l=(ListView) view.findViewById(R.id.listView);
        pf=(PullToRefreshListView)view.findViewById(R.id.pull_refresh_list);
       // pf.setMode(PullToRefreshBase.Mode.BOTH);
        pf.getLoadingLayoutProxy(true, false).setPullLabel("下拉加载更多");
        pf.getLoadingLayoutProxy(true, false).setRefreshingLabel("正在加载...");
       pf.getLoadingLayoutProxy(true, false).setReleaseLabel("松开加载更多...");
       refresh();
        //pf.setAdapter(d);
        pf.setOnRefreshListener(this);
        pf.setOnItemClickListener(this);
        return view;
    }

    @Override
    public void onRefresh(PullToRefreshBase refreshView) {
      refresh();


    }
   private void  refresh(){
       task t=new task("http://world.huanqiu.com/article", h);
       t.execute();
       }


    @Override
    public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
       // Toast.makeText(getActivity(),""+position,Toast.LENGTH_LONG).show();
        Intent intent=new Intent(getContext(), act1.class);
        Bundle b=new Bundle();
        b.putString("title",list.get(position-1).getTitle());
        b.putString("url",list.get(position-1).getUrl());
        intent.putExtras(b);
        startActivity(intent);
    }
}
