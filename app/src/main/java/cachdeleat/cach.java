package cachdeleat;

import android.content.Context;
import android.widget.Toast;

import java.io.File;

/**
 * Created by Administrator on 2016/5/12.
 */
public class cach {
    private Context context;
    public cach(Context context){
        this.context=context;
    }
    //清除本应用内部缓存(
    public void delecach(){
        if (context.getCacheDir() != null && context.getCacheDir().exists() && context.getCacheDir().isDirectory()) {
            for (File item :context.getCacheDir().listFiles()) {
                item.delete();
                //Toast.makeText(context,"成功",Toast.LENGTH_SHORT).show();;
            }
            Toast.makeText(context,"清除成功",Toast.LENGTH_SHORT).show();;
        }

    }

}
