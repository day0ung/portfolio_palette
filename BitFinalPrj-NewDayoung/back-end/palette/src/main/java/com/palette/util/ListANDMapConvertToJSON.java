package com.palette.util;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import com.fasterxml.jackson.core.JsonParseException;
import com.fasterxml.jackson.databind.JsonMappingException;
import com.fasterxml.jackson.databind.ObjectMapper;

import net.minidev.json.JSONArray;
import net.minidev.json.JSONObject;

public class ListANDMapConvertToJSON {
    //search from `aljjabaegi.tistory.com/40`
    
    /**
     * List<Map>을 jsonArray로 변환한다.
     *
     * @param list List<Map<String, Object>>.
     * @return JSONArray.
     */
    public static JSONArray getJsonArrayFromList( List<Map<String, Object>> list )
    {
        JSONArray jsonArray = new JSONArray();
        for( Map<String, Object> map : list ) {
            jsonArray.add( getJsonStringFromMap( map ) );
        }
        
        return jsonArray;
    }

    /**
     * List<Map>을 jsonArray로 변환한다.
     *
     * @param list List<Map<String, Object>>.
     * @return JSONArray.
     */
    public static JSONArray getJsonArrayFromListInList( List<List<Map<String, Object>>> listlist )
    {
        JSONArray finishData = new JSONArray();
        JSONArray jsonArray = new JSONArray();
        
        // List<List<Map<String, Object>>> listlist
        // List<Map<String, Object>> list


        for(int i=0; i<listlist.size(); i++){


            for( Map<String, Object> map : listlist.get(i) ) {
                //1개의 대분류에 속한 소분류 json으로 변환.
                jsonArray.add(getJsonStringFromMap(map) );
                System.out.println("소분류맵");
                System.out.println(map);
            }

            if(jsonArray != null){

                finishData.add(jsonArray);
                System.out.println(jsonArray);

                System.out.println("데이터 타입 측정");
                System.out.println(jsonArray.getClass());
                System.out.println(finishData.getClass());
                System.out.println("데이터 타입 측정");

            }
            jsonArray.clear();
            System.out.println("\n대분류단위 : ");
            System.out.println(finishData);
            System.out.println("\n");
        }

        return finishData;
    }



/**
     * List<Map>을 jsonString으로 변환한다.
     *
     * @param list List<Map<String, Object>>.
     * @return String.
     */
    public static String getJsonStringFromList( List<Map<String, Object>> list )
    {
        JSONArray jsonArray = getJsonArrayFromList( list );
        return jsonArray.toJSONString();
    }

  


    /**
     * Map을 json으로 변환한다.
     *
     * @param map Map<String, Object>.
     * @return JSONObject.
     */
    public static JSONObject getJsonStringFromMap( Map<String, Object> map )
    {
        JSONObject jsonObject = new JSONObject();
        for( Map.Entry<String, Object> entry : map.entrySet() ) {
            String key = entry.getKey();
            Object value = entry.getValue();
            jsonObject.put(key, value);
        }
        
        return jsonObject;
    }
        /**
     * JsonObject를 Map<String, String>으로 변환한다.
     *
     * @param jsonObj JSONObject.
     * @return Map<String, Object>.
     */
    @SuppressWarnings("unchecked")
    public static Map<String, Object> getMapFromJsonObject( JSONObject jsonObj )
    {
        Map<String, Object> map = null;
        
        try {
            
            map = new ObjectMapper().readValue(jsonObj.toJSONString(), Map.class) ;
            
        } catch (JsonParseException e) {
            e.printStackTrace();
        } catch (JsonMappingException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }
 
        return map;
    }



    
    
}