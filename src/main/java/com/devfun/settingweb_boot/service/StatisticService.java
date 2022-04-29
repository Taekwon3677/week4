package com.devfun.settingweb_boot.service;
 
import java.util.HashMap;
 
public interface StatisticService {
    public HashMap<String,Object> yearloginNum (String year);
    public HashMap<String,Object> monthuserloginNum(String month);
    
    public HashMap<String,Object> dayuserloginNum(String day);
    
    public HashMap<String,Object> teamloginNum(String team, String month);
    public HashMap<String,Object> businessloginNum(String string);
    
}
