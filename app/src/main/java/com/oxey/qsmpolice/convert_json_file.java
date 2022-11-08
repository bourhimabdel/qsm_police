package com.oxey.qsmpolice;

import android.content.Context;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.io.IOException;
import java.io.InputStream;
import java.util.ArrayList;

public class convert_json_file {

    public ArrayList<data> get_data(Context context) {

        ArrayList<data> info = new ArrayList<>();
        try {
            InputStream is = context.getResources().openRawResource(R.raw.my_data);
            int size = is.available();
            byte[] buffer = new byte[size];

            is.read(buffer);

            is.close();
            String json = new String(buffer, context.getResources().getString(R.string.UTF_8));


            JSONObject jsonObject = new JSONObject(json);

            JSONObject setting = jsonObject.getJSONObject("data");

            for (int i = 1; i < 101; i++) {
                String ob_name = "q_" + i;

                String qustion = setting.getJSONObject(ob_name).getString("q");

                JSONArray arr = setting.getJSONObject(ob_name).getJSONArray("choise");

                ArrayList<String> list=new ArrayList<>();
                for (int j = 0; j < arr.length(); j++) {
                    list.add(arr.getJSONObject(j).getString("t"+j));
                }

                data d=new data(qustion,list);
                d.setId(i);
                info.add(d);
            }

            return info;
        } catch (IOException | JSONException e) {
            e.printStackTrace();
            return null;
        }
    }

}
