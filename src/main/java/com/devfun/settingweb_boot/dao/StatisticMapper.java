package com.devfun.settingweb_boot.dao;
import java.util.HashMap;
 

 
public interface  StatisticMapper {
    public HashMap<String, Object> selectYearLogin(String year);
    public HashMap<String, Object> selectMonthLogin(String month);
    public HashMap<String, Object> selectDayLogin(String day);
    public HashMap<String, Object> selectTeamLogin(String team, String month);
    public HashMap<String, Object> selectBusinessLogin(String string);
}