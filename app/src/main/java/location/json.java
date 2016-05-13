package location;

import android.content.Context;
import android.os.Handler;
import android.os.Message;
import android.util.Log;
import android.widget.Toast;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.UnsupportedEncodingException;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.URL;
import java.net.URLEncoder;

import fragmentactivity.fragmentlayout;

/**
 * Created by Administrator on 2016/5/6.
 */
public class json {
    private Handler h;
    private String url;
    private Context context;
    private String res;
    public json(Context context, Handler h){
        this.context=context;
        this.h=h;
    }
    public void init(String city){
        res=city;

        try {
          //  Toast.makeText(context,city,Toast.LENGTH_LONG).show();
            city= URLEncoder.encode(city,"UTF-8");
        } catch (UnsupportedEncodingException e) {
            e.printStackTrace();
        }
        url="http://op.juhe.cn/onebox/weather/query?cityname="+city+"&key=779d3ba24eb335c0c4793ab582893911";
        new Thread(new Runnable() {
            @Override
            public void run() {
                try {
                    URL u=new URL(url);
                    HttpURLConnection conn=(HttpURLConnection) u.openConnection();
                    conn.setRequestMethod("GET");
                    conn.setUseCaches(false);
                    conn.setConnectTimeout(3000);
                    conn.setDoInput(true);
                    conn.connect();
                    InputStream is = conn.getInputStream();
                    BufferedReader reader=new BufferedReader(new InputStreamReader(is));
                    String strRead = null;
                    StringBuffer sb = new StringBuffer();
                    while ((strRead = reader.readLine()) != null) {
                        sb.append(strRead);
                    }
                  analyze(sb.toString());
                } catch (MalformedURLException e) {
                    Toast.makeText(context,"gg",Toast.LENGTH_LONG).show();;
                    e.printStackTrace();
                } catch (IOException e) {//urlconnection
                    Toast.makeText(context,"ggurl",Toast.LENGTH_LONG).show();;
                    e.printStackTrace();
                }

            }
        }).start();

    }
    //解析json数据
private void analyze(String data){
    try {

       // Toast.makeText(context,"12345",Toast.LENGTH_LONG).show();
        JSONObject json=new JSONObject(data);
        JSONObject result=json.getJSONObject("result");
        JSONObject da=result.getJSONObject("data");
       // JSONObject realtime=da.getJSONObject("realtime");
       // JSONObject weather=realtime.getJSONObject("weather");
        JSONArray weather=da.getJSONArray("weather");
        JSONObject info=weather.optJSONObject(0).getJSONObject("info");

        String in=info.getString("day");
        in=in.replaceAll("\"","").replaceAll("]","").replaceAll("\\[","");//使用正则表达式去除不需要的符号
        String []a=in.split(",");//用逗号作为分割的标志
       // Log.d("dd",info.getString("day"));
        Log.d("gg",a[0]+a[1]);
         res=res+"   天气："+ a[1]+ "   温度："+ a[2];
       Log.d("gg",  res);
        Message m=new Message();
        m.obj=res;
        h.sendMessage(m);

    } catch (JSONException e) {
        e.printStackTrace();
    }

}

}
