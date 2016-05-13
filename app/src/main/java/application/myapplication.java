package application;

import android.app.Application;
import android.content.res.Resources;
import android.graphics.drawable.Drawable;

import com.example.hehe.topne.R;

/**
 * Created by Administrator on 2016/5/10.
 */
public class myapplication extends Application {

    public boolean net=false;//判断是否能在非wifi状态下加载图片
    public static  boolean finsh=false;//判断是否连续按两下就退出
    public static int c=0;//判断是在哪个颜色里
    private Drawable drawable;
    public static boolean night=false;

    public Object change(){
        Resources res=getResources();
        switch (c){
            case 0:
                drawable=res.getDrawable(R.drawable.withe);

                break;

            case 1:
                drawable=res.getDrawable(R.drawable.fred);
                break;
            case 2:
                drawable=res.getDrawable(R.drawable.cyellow);
                break;
            case 3:
                drawable=res.getDrawable(R.drawable.fcgrade);
                break;
            case 4:
                drawable=res.getDrawable(R.drawable.hblue);
                break;
            case 5:
                drawable=res.getDrawable(R.drawable.mfcolor);
                break;
            case 6:
                drawable=res.getDrawable(R.drawable.xyc);
                break;
            case 7:
                drawable=res.getDrawable(R.drawable.back);
                break;
            default:
                break;
        }
        return drawable;
    }

}
