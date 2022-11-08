package com.oxey.qsmpolice;

import java.util.ArrayList;

public class data {

   int id;

   public int getId() {
      return id;
   }

   public void setId(int id) {
      this.id = id;
   }

   String qustion;
   ArrayList<String> list_choix;
   int exact_choix;

   public data(String qustion, ArrayList<String> list_choix) {
      this.qustion = qustion;
      this.list_choix = list_choix;
   }

   public String getQustion() {
      return qustion;
   }

   public void setQustion(String qustion) {
      this.qustion = qustion;
   }

   public ArrayList<String> getList_choix() {
      return list_choix;
   }

   public void setList_choix(ArrayList<String> list_choix) {
      this.list_choix = list_choix;
   }

   public int getExact_choix() {
      return exact_choix;
   }

   public void setExact_choix(int exact_choix) {
      this.exact_choix = exact_choix;
   }
}
