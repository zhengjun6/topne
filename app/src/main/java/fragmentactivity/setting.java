package fragmentactivity;

import android.content.DialogInterface;
import android.graphics.drawable.Drawable;
import android.os.Bundle;
import android.os.Message;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v7.app.AlertDialog;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.CompoundButton;
import android.widget.Switch;
import android.widget.Toast;

import cachdeleat.cach;
import location.location;
import com.example.hehe.topne.R;

import java.io.File;

import application.myapplication;

/**
 * Created by Administrator on 2016/5/3.
 */
public class setting extends Fragment implements View.OnClickListener,CompoundButton.OnCheckedChangeListener{
    private   View view;
    private Button btheme,bclear,babout,blocation;
    //private CheckBox cnet,cfinish;
    private Switch cnet,cfinish,night;
    private AlertDialog.Builder builder;
    private static  int locatio=0;//标识颜色
    private int linshi;//临时代表颜色的位置
    private static boolean net=false;
    private static boolean finish=false;
    private String []color=new String[]{"默认","粉红","橙黄","翡翠绿","海蓝","麦粉色","薰衣草色"};
    private myapplication my;
    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
         view=inflater.inflate(R.layout.setting,container,false);

        my= (myapplication) getActivity().getApplication();
        getActivity().getWindow().setBackgroundDrawable((Drawable) my.change());

        init();
        check();
        return view;
    }
    private void check() {

            cnet.setChecked(net);
        cfinish.setChecked(finish);
      //  night.setChecked();

    }

   private void init(){
       bclear=(Button)view.findViewById(R.id.clean);
       btheme=(Button)view.findViewById(R.id.theme);
       babout=(Button)view.findViewById(R.id.about);
       blocation=(Button)view.findViewById(R.id.loca);
       cfinish=(Switch)view.findViewById(R.id.finish);
       cnet=(Switch)view.findViewById(R.id.net);
       night=(Switch)view.findViewById(R.id.night);
        change();;
       babout.setOnClickListener(this);
       blocation.setOnClickListener(this);
       bclear.setOnClickListener(this);
       btheme.setOnClickListener(this);
       cnet.setOnCheckedChangeListener(this);
       cfinish.setOnCheckedChangeListener(this);
       night.setOnCheckedChangeListener(this);


   }
    private void change(){
        bclear.setBackgroundDrawable((Drawable) my.change());
        btheme.setBackgroundDrawable((Drawable) my.change());
        babout.setBackgroundDrawable((Drawable) my.change());
        blocation.setBackgroundDrawable((Drawable) my.change());


        if(my.night){
           // btheme.setTextColor(R.color.night);
            btheme.setTextColor(getResources().getColor(R.color.night));
            bclear.setTextColor(getResources().getColor(R.color.night));
            babout.setTextColor(getResources().getColor(R.color.night));

        }
        else{

        }
    }


    @Override
    public void onClick(View v) {

        switch (v.getId()){
            case  R.id.about:
                about();
                break;
            case R.id.clean:

                clear();
                break;
            case R.id.theme:
                theme();
                break;
            case R.id.loca:
              loc();
                break;
            default:
                break;
        }

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

             change();
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
