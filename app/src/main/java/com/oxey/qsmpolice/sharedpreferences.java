package com.oxey.qsmpolice;

import android.content.Context;
import android.content.SharedPreferences;

import java.util.HashSet;
import java.util.Set;

import static android.content.Context.MODE_PRIVATE;

public class sharedpreferences {

   Context context;
   public sharedpreferences(Context context){
      this.context=context;
   }

   public void put_used_element(Set<String> state){
      SharedPreferences data_loaded =  context.getSharedPreferences("app_state_details", MODE_PRIVATE);
      SharedPreferences.Editor editor_low = data_loaded.edit();
      editor_low.putStringSet("app_state",state);
      editor_low.clear();
      editor_low.apply();
   }
   public void clear_used_element(){
      SharedPreferences data_loaded =  context.getSharedPreferences("app_state_details", MODE_PRIVATE);
      SharedPreferences.Editor editor_low = data_loaded.edit();
      editor_low.clear();
      editor_low.apply();
   }

   public Set<String> get_used_element(){
      SharedPreferences data_loaded = context.getSharedPreferences("app_state_details", MODE_PRIVATE);

      return  data_loaded.getStringSet("app_state",new HashSet<String>());
   }


}
