package fragmentactivity;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.TextView;
import android.widget.Toast;

import com.example.hehe.topne.R;

import java.util.List;

/**
 * Created by Administrator on 2016/5/4.
 */
public class collectadapter extends BaseAdapter{
    private Context context;
    private List<collectdata> list;

public collectadapter(Context context,List<collectdata> list) {
    this.context=context;
    this.list=list;

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
        viewholder viewholder;
        View v ;
        v= LayoutInflater.from(context).inflate(R.layout.dbdata,null);
        if(convertView==null){
            viewholder=new viewholder();
            viewholder.tshow=(TextView)v.findViewById(R.id.textView3);
            v.setTag(viewholder);

        }
        else{
            v=convertView;
            viewholder=(viewholder)v.getTag();
        }

        viewholder.tshow.setText(list.get(position).getTitle().toString());

        return  v;
    }
    class  viewholder{
        private TextView tshow;
    }

}

