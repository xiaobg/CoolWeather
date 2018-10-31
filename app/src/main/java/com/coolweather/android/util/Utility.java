package com.coolweather.android.util;

import android.text.TextUtils;

import com.coolweather.android.db.Province;
import com.coolweather.android.db.County;
import com.coolweather.android.db.City;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

public class Utility {
    public  static boolean handleProvinceResponse(String resqpnse){
        if(!TextUtils.isEmpty(resqpnse)){
    try{
        JSONArray allProvinces = new JSONArray(resqpnse);
        for(int i=0;i<allProvinces.length();i++){
            JSONObject provinceObject = allProvinces.getJSONObject(i);
            Province province = new Province();
            province.setProvinceName(provinceObject.getString("name"));
            province.setProvinceCode(provinceObject.getInt("id"));
            province.save();
        }
        return  true;
    }
    catch (JSONException e){
        e.printStackTrace();
    }
        }
        return  false;
    }

    public  static boolean handleCountyResponse(String response,int cityId){
        if(!TextUtils.isEmpty(response)){
            try{
                JSONArray allCounties =new JSONArray(response);
                for(int i=0;i<allCounties.length();i++){
                    JSONObject countyObject = allCounties.getJSONObject(i);
                    County county =new County();
                    county.setCountyName(countyObject.getString("name"));
                    county.setWeatherId(countyObject.getString("weather_id"));
                    county.setCityId(cityId);
                    county.save();
                }
                return true;
            }catch (JSONException e){
                e.printStackTrace();
            }
        }
        return  false;
    }
    public  static  boolean handleCityResponse(String reponse,int provinceID){
        if(!TextUtils.isEmpty(reponse)){
            try {
                JSONArray allcities=new JSONArray(reponse);
                for(int i=0;i<allcities.length();i++){
                    JSONObject cityObject = allcities.getJSONObject(i);
                    City city=new City();
                    city.setCityName(cityObject.getString("name"));
                    city.setCityCode(cityObject.getInt("id"));
                    city.setProvinceId(provinceID);
                    city.save();
                }
                return true;
            }catch ( JSONException e){
                e.printStackTrace();
            }
        }

        return false;
    }


}
