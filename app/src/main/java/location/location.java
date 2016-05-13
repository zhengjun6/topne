package location;


import android.content.Context;
import android.location.LocationListener;
import android.os.Handler;
import android.widget.Toast;

import com.baidu.location.BDLocation;
import com.baidu.location.BDLocationListener;
import com.baidu.location.LocationClient;
import com.baidu.location.LocationClientOption;

import fragmentactivity.fragmentlayout;

/**
 * Created by Administrator on 2016/5/6.
 */






 public class location  {
private fragmentlayout f=new fragmentlayout();
   private LocationClient mLocationClient;
   public static  String lo;
   private Handler h;
   private Context context;
   public location(){

   }
   public location(Context context, Handler h){
      this.context=context;
      this.h=h;
   }
   public void init(){
      mLocationClient=new LocationClient(context);
      mLocationClient.registerLocationListener(new listen());

      setLocationOption();

      mLocationClient.start();// 开始定位
   }
   private void setLocationOption() {
      LocationClientOption option = new LocationClientOption();
      option.setOpenGps(true);
      option.setIsNeedAddress(true);// 返回的定位结果包含地址信息
      option.setAddrType("all");// 返回的定位结果包含地址信息
      option.setCoorType("bd09ll");// 返回的定位结果是百度经纬度,默认值gcj02
      option.setScanSpan(5000);// 设置发起定位请求的间隔时间为5000ms
      option.disableCache(true);// 禁止启用缓存定位
      option.setPoiNumber(5); // 最多返回POI个数
      option.setPoiDistance(1000); // poi查询距离
      option.setPoiExtraInfo(true); // 是否需要POI的电话和地址等详细信息
      mLocationClient.setLocOption(option);
     // Toast.makeText(this,"gg",Toast.LENGTH_LONG).show();
   }



   class  listen implements BDLocationListener {

      @Override
      public void onReceiveLocation(BDLocation location) {
         if (location == null)
            return;
         StringBuffer sb = new StringBuffer(256);
         sb.append("当前时间 : ");
         sb.append(location.getTime());
         sb.append("\n错误码 : ");
         sb.append(location.getLocType());
         sb.append("\n纬度 : ");
         sb.append(location.getLatitude());
         sb.append("\n经度 : ");
         sb.append(location.getLongitude());
         sb.append("\n半径 : ");
         sb.append(location.getRadius());
         if (location.getLocType() == BDLocation.TypeGpsLocation) {
            sb.append("\n速度 : ");
            sb.append(location.getSpeed());
            sb.append("\n卫星数 : ");
            sb.append(location.getSatelliteNumber());
         } else if (location.getLocType() == BDLocation.TypeNetWorkLocation) {
            sb.append("\n地址 : ");
            sb.append(location.getDistrict());
            lo=location.getAddrStr();
            json j=new json(context,h);
            j.init(location.getDistrict());
         }
        // f.t.setText(sb.toString());
      //   Toast.makeText(context,sb.toString(),Toast.LENGTH_LONG).show();
         if(sb.length()>0){
            mLocationClient.stop();
         }
         // t1.setText("12345435");
      }

      @Override
      public void onReceivePoi(BDLocation poiLocation) {
         // TODO Auto-generated method stub
         if (poiLocation == null) {
            return;
         }
         StringBuffer sb = new StringBuffer(256);
         sb.append("Poi time : ");
         sb.append(poiLocation.getTime());
         sb.append("\nerror code : ");
         sb.append(poiLocation.getLocType());
         sb.append("\nlatitude : ");
         sb.append(poiLocation.getLatitude());
         sb.append("\nlontitude : ");
         sb.append(poiLocation.getLongitude());
         sb.append("\nradius : ");
         sb.append(poiLocation.getRadius());
         if (poiLocation.getLocType() == BDLocation.TypeNetWorkLocation) {
            sb.append("\naddr : ");
            sb.append(poiLocation.getDistrict());
         }
         if (poiLocation.hasPoi()) {
            sb.append("\nPoi:");
            sb.append(poiLocation.getPoi());
         } else {
            sb.append("noPoi information");
         }
        //Toast.makeText(context,sb.toString(),Toast.LENGTH_LONG).show();
      }}

   public void close(){
      mLocationClient.stop();
   }
}
