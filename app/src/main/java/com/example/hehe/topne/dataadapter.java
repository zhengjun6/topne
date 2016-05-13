package com.example.hehe.topne;

import android.content.Context;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.BaseAdapter;
import android.widget.LinearLayout;
import android.widget.TextView;
import android.widget.Toast;



import java.util.ArrayList;
import java.util.List;
import java.util.zip.Inflater;




/**
 * Created by hehe on 2016/4/20.
 */
public class dataadapter extends BaseAdapter{
    private Context context;
    private List<data> list;
    public dataadapter(Context context,List<data> list){

        this.context=context;
        this.list=list;

       // Toast.makeText(context,list.get(10).getTitle(),Toast.LENGTH_SHORT).show();;
    }

    @Override
    public int getCount() {
        return list.size();
    }

    @Override
    public Object getItem(int position) {
        return list.get(position);
    }

    @Override
    public long getItemId(int position) {
        return position;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {


        View view;
        viewholder vh=new viewholder();
        if(convertView==null){
            view= LayoutInflater.from(context).inflate(R.layout.list,null);
            vh.t1=(TextView)view.findViewById(R.id.t3);
            view.setTag(vh);


        }
        else{
            view=convertView;
            vh=(viewholder)view.getTag();
        }
        vh.t1.setTextSize(25);
        vh.t1.setText(list.get(position).getTitle());



        return view;
    }

    class viewholder{
        TextView t1;
    }
}
