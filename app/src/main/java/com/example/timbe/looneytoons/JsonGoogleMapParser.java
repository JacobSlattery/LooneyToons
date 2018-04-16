package com.example.timbe.looneytoons;

import android.util.Log;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.List;

public class JsonGoogleMapParser {
    private JSONObject result;
    private List<MapLocation> targets;
    private boolean hasResult;
    private boolean hasNextToken;
    private String nextTokenString;

    public JsonGoogleMapParser(){
        targets = new ArrayList<MapLocation>();
        hasResult = false;
    }

    public boolean JsonGoogleMapParser() {return hasNextToken; }
    public void parse(String jsonString){

        Log.v( "Parser", "inside parse" );
        Log.v( "Parser", "JSON String: " + jsonString );

        hasResult = false;
        try{
            result = new JSONObject(jsonString);
            this.hasResult = true;

        }catch(JSONException err){
            Log.v( "PARSER", "exception: " + err.getMessage() );
        }
        try{
            nextTokenString = result.getString( "next_page_token");
            hasNextToken = true;
        }catch(JSONException err){
            nextTokenString = "";
            Log.v( "PARSER", "no next_page_token" );
            hasNextToken = false;
        }
        if (this.hasResult){
            Log.v( "PARSER", "calling generateList");
            addToList();
        }
    }

    public List<MapLocation> getList() { return this.targets; }

    private void addToList(){
        try {
            Log.v("PARSER", "getting array");
            JSONArray array = result.getJSONArray("result");
            Log.v("PARSER", "array length = " + array.length() );
            for(int i = 0; i<array.length(); i++) {
                String name = "";
                String address = "";
                double rating = 0.0;
                int price = -1;
                JSONObject place = array.getJSONObject(i);
                double lat = place.getJSONObject("geometry").getJSONObject("location").getDouble("lat");
                double lon = place.getJSONObject("geometry").getJSONObject("location").getDouble("lng");
                try {
                    name = place.getString("name");
                    address = place.getString("vicinity");
                            rating = place.getDouble("rating");
                            price = place.getInt("price_level");
                }catch(JSONException err){}
                MapLocation mLocation = new MapLocation(name, lat, lon, address);
                this.targets.add(mLocation);

            }
        }catch(JSONException err){
            Log.v( "JSON PARSER", err.getMessage());
        }

    }
}
