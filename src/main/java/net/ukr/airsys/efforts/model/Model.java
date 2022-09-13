package net.ukr.airsys.efforts.model;

import java.util.Map;

public interface Model extends Observable {
   void initialize(String inFileName);
   Map<String, Double[]> getEffortsTable();
}