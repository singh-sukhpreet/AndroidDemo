package com.mytaxi.android_demo;

import android.content.Context;
// volley import statemetns
import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.JsonObjectRequest;
import com.android.volley.toolbox.Volley;
// json import statements
import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

class UserInfo {
    // initializing context
    private Context context;
    UserInfo(Context context) {
        this.context = context;
    }
    // getLoginDetails -  To fetch and return the details of the user
    String[] getLoginDetails() {
        String url = "https://randomuser.me/api/?seed=a1f30d446f820665";
        // array to store user details
        final String userInfo[] = new String[2];
        // JsonObject to fetch the entire json Object
        JsonObjectRequest request = new JsonObjectRequest(Request.Method.GET, url, null,
                new Response.Listener<JSONObject>() {
                    @Override
                    public void onResponse(JSONObject response) {
                        try {
                            // fetching array results
                            JSONArray results = response.getJSONArray("results");
                            // fetching first object from results array
                            JSONObject firstObject = results.getJSONObject(0);
                            // fetching login object from firstObject
                            JSONObject login = firstObject.getJSONObject("login");
                            // Initializing userInfor array with usernmae and password details
                            userInfo[0] = login.getString("username");
                            userInfo[1] = login.getString("password");
                        } catch (JSONException e) {
                            e.printStackTrace();
                        }
                    }
                }, new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError error) {
                error.printStackTrace();
            }
        });
        //creating a request queue
        RequestQueue requestQueue = Volley.newRequestQueue(context);
        //adding the string request to request queue
        requestQueue.add(request);
        // returning userInfo array containing username and password
        return userInfo;
    }
}
