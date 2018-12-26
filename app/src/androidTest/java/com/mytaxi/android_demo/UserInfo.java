package com.mytaxi.android_demo;

import android.content.Context;

import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.JsonObjectRequest;
import com.android.volley.toolbox.Volley;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

class UserInfo {
    private Context context;
    UserInfo(Context context) {
        this.context = context;
    }

    String[] getLoginDetails() {
        String url = "https://randomuser.me/api/?seed=a1f30d446f820665";
        final String userInfo[] = new String[2];

        JsonObjectRequest request = new JsonObjectRequest(Request.Method.GET, url, null,
                new Response.Listener<JSONObject>() {
                    @Override
                    public void onResponse(JSONObject response) {
                        try {
                            JSONArray jsonArray = response.getJSONArray("results");
                            JSONObject employee = jsonArray.getJSONObject(0);
                            JSONObject login = employee.getJSONObject("login");

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
        return userInfo;

    }
}
