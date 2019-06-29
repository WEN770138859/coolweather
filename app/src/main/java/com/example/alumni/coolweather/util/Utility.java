package com.example.alumni.coolweather.util;

import android.text.TextUtils;

import com.example.alumni.coolweather.db.City;
import com.example.alumni.coolweather.db.County;
import com.example.alumni.coolweather.db.Province;

import org.json.JSONArray;
import org.json.JSONObject;

public class Utility {

    public static boolean handleProvinceResponse(String response){
        if (!TextUtils.isEmpty(response)){
            try{
                JSONArray allProvince = new JSONArray(response);
                for (int i = 0 ; i < allProvince.length(); i ++){
                    JSONObject provinceObjecet = allProvince.getJSONObject(i);
                    Province province = new Province();
                    province.setProvinceName(provinceObjecet.getString("name"));
                    province.setProvinceCode(provinceObjecet.getInt("id"));
                    province.save();
                }
                return true;

            } catch (Exception e) {
                e.printStackTrace();
            }
        }
        return false;
    }

    public static boolean handleCityResponse(String response, int provinceId){
        if (!TextUtils.isEmpty(response)){
            try{
                JSONArray allCities = new JSONArray(response);
                for (int i = 0 ; i < allCities.length() ; i ++){
                    JSONObject cityOBject = allCities.getJSONObject(i);
                    City city = new City();
                    city.setCityName(cityOBject.getString("name"));
                    city.setProvinceId(provinceId);
                    city.setCityCode(cityOBject.getInt("id"));
                    city.save();
                }
                return true;
            } catch (Exception e) {
                e.printStackTrace();
            }
        }
        return false;
    }

    public static boolean handleCountyResponse(String response, int cityId){
        if (!TextUtils.isEmpty(response)){
            try {
                JSONArray allCounties = new JSONArray(response);
                for (int i = 0 ; i < allCounties.length() ; i ++ ){
                    JSONObject countyObject = allCounties.getJSONObject(i);
                    County county = new County();
                    county.setCountyName(countyObject.getString("name"));
                    county.setWeatherId(countyObject.getString("weather_id"));
                    county.setCityId(cityId);
                    county.save();
                }
                return true;
            } catch (Exception e) {
                e.printStackTrace();
            }
        }
        return false;
    }
}
