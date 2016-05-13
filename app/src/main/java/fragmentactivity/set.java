package fragmentactivity;

import android.content.DialogInterface;
import android.graphics.drawable.Drawable;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v7.app.AlertDialog;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.CompoundButton;
import android.widget.RelativeLayout;
import android.widget.Switch;

import com.example.hehe.topne.R;

import application.myapplication;
import cachdeleat.cach;
import location.location;

/**
 * Created by Administrator on 2016/5/12.
 */
public class set extends Fragment implements View.OnClickListener,CompoundButton.OnCheckedChangeListener{
    private AlertDialog.Builder builder;
        private View view;
    private Switch cnet,cfinish,night;
    private RelativeLayout about,change,clean,loca;
    private String []color=new String[]{"默认","粉红","橙黄","翡翠绿","海蓝","麦粉色","薰衣草色"};
    private myapplication my;
    private static boolean net=false;
    private static boolean finish=false;
    private static  int locatio=0;//标识颜色
    private int linshi;//临时代表颜色的位置
    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        view=inflater.inflate(R.layout.set,container,false);
        my= (myapplication) getActivity().getApplication();
        getActivity().getWindow().setBackgroundDrawable((Drawable) my.change());
        init();
        return view;
    }

    private void init() {
        about=(RelativeLayout)view.findViewById(R.id.about);
       change=(RelativeLayout)view.findViewById(R.id.change);
        clean=(RelativeLayout)view.findViewById(R.id.clean);
       loca=(RelativeLayout)view.findViewById(R.id.loca);
        cnet=(Switch)view.findViewById(R.id.net);
        cfinish=(Switch)view.findViewById(R.id.finish);
        night=(Switch)view.findViewById(R.id.night);

        about.setOnClickListener(this);
        clean.setOnClickListener(this);
        change.setOnClickListener(this);
        loca.setOnClickListener(this);

    }
    //颜色改变的
    private void color(){

    }

    @Override
    public void onClick(View v) {
        switch (v.getId()){
            case R.id.about:
                about();
                break;
            case R.id.change:
                theme();
                break;
            case R.id.clean:
                clear();;
                break;
            case R.id.loca:
                loc();
                break;
        }

    }

    //定位
    private void loc(){
        builder=new AlertDialog.Builder(getActivity());
        location l=new location();
        builder.setTitle("具体位置");
        builder.setMessage(l.lo);
        builder.setPositiveButton("确认", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {
                //  my= (myapplication) getActivity().getApplication();

            }
        });
        builder.show();
    }
    //执行关于按钮的内容
    private void about(){
        builder=new AlertDialog.Builder(getActivity());
        builder.setTitle("关于我们");
        builder.setMessage("该软件纯粹用于课外娱乐所用，不作为商业化，其中的新闻内容都为我个人从第三方网页获取，" +
                "本软件同时还采用了百度的定位sdk和聚合数据的天气预报，能实时定位你的当前位置以及当前位置的天气信息。" +
                "本软件还存在较多的bug，欢迎" +
                "广大的使用者提供本软件的不足以及更好的设计意见，联系方式见侧滑栏");
        builder.setPositiveButton("确认", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {
                //  my= (myapplication) getActivity().getApplication();

            }
        });
        builder.show();
    }
    //执行更改主题的action
    private void theme(){
        builder=new AlertDialog.Builder(getActivity());
        builder.setTitle("请选择你喜欢的主题");
        builder.setSingleChoiceItems(color, locatio, new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {
                linshi=which;
            }
        });
        builder.setPositiveButton("确认", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {
                //  my= (myapplication) getActivity().getApplication();
                my.c=locatio=linshi;
                getActivity().getWindow().setBackgroundDrawable((Drawable) my.change());//

            }
        });
        builder.setNegativeButton("取消", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {

            }
        });
        builder.show();
    }
    //执行清楚缓存的action
    private void clear(){
        cach c=new cach(getContext());
        c.delecach();


    }

    @Override
    public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {
        switch (buttonView.getId()){
            case R.id.net:
                if(isChecked){
                    net=true;
                    my.net=net;
                }
                else {
                    net=false;
                    my.net=net;
                }
                break;
            case R.id.finish:
                if(isChecked){
                    finish=true;
                    my.finsh=finish;
                }
                else {
                    finish=false;
                    my.finsh=finish;
                }
                break;

            case R.id.night:
                if(isChecked){


                }
                else{

                }
                break;
            default:
                break;
        }
    }
}
