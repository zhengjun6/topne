package fragmentactivity;

import android.content.ContentValues;
import android.database.sqlite.SQLiteDatabase;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.example.hehe.topne.R;

import db.sqlitehelp;

/**
 * Created by Administrator on 2016/5/3.
 */
public class feedback extends Fragment implements View.OnClickListener{
    private EditText t1,t2;
    private Button commit;
    private sqlitehelp sql;
    private SQLiteDatabase write;
    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view=inflater.inflate(R.layout.feedback,container,false);
        t1=(EditText)view.findViewById(R.id.editText);
        t2=(EditText)view.findViewById(R.id.editText2);
        commit=(Button)view.findViewById(R.id.button);
commit.setOnClickListener(this);
        return  view;
    }

    private void init(){
       // Toast.makeText(getActivity(),t1.getText().toString(),Toast.LENGTH_LONG).show();;
        sql=new sqlitehelp(getActivity());
        if(t1.getText().toString().length()>5){
        write=sql.getWritableDatabase();
            ContentValues c=new ContentValues();
            c.put("command",t1.getText().toString());
            c.put("qq",t2.getText().toString());
            write.insert("feedback",null,c);
            Toast.makeText(getActivity(),"succ",Toast.LENGTH_LONG).show();
            write.close();
            sql.close();;
        }
        else{
            Toast.makeText(getActivity(),"请正确填写",Toast.LENGTH_LONG).show();;
        }
    }

    @Override
    public void onClick(View v) {
        init();//写入数据库；
    }
}
