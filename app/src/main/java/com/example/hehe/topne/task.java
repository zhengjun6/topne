package com.example.hehe.topne;

import android.os.AsyncTask;
import android.os.Bundle;
import android.os.Handler;
import android.os.Message;
import android.util.Log;



import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

/**
 * Created by hehe on 2016/4/22.
 */
public class task extends AsyncTask<List<data>,Integer,List<data>> {
    private Elements e;
    private data k;
    private  List<data> list=new ArrayList<data>();
    private Handler h;
    private String url;
     public task(String url, Handler h){
         this.url=url;
         this.h=h;
     }

    @Override
    protected List<data> doInBackground(List<data>... params) {

        try {

            Bundle b=new Bundle();
            Document d = Jsoup.connect(url).get();
            e = d.select("div.fallsFlow").select("h3");
            for(Element a:e){
                k=new data(a.getElementsByTag("a").attr("href").toString(),a.text().toString());
                list.add(k);
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
        return list;
    }

    @Override
    protected void onPostExecute(List<data> datas) {
        super.onPostExecute(datas);
        Message m=new Message();
        if(datas!=null){
            m.obj=datas;
            m.arg1=1;
        }
        else{
            m.arg1=0;
        }
        h.sendMessage(m);
    }
}
