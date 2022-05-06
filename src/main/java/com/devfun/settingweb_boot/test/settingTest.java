package com.devfun.settingweb_boot.test;
 
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URL;
import java.net.URLEncoder;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
 
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.ModelAndView;
 
import com.devfun.settingweb_boot.dao.StatisticMapper;
import com.devfun.settingweb_boot.service.StatisticService;
 
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URL;
import java.net.URLEncoder;
import java.io.BufferedReader;
import java.io.IOException;
 
import org.xml.sax.SAXException;
import org.w3c.dom.Document;
import org.w3c.dom.Element;
import org.w3c.dom.Node;
import org.w3c.dom.NodeList;

import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import javax.xml.parsers.ParserConfigurationException;

import org.w3c.dom.Document;

@Controller
public class settingTest {
    
 
    @Autowired
    private StatisticService service;
    
    @ResponseBody 
    @RequestMapping("/sqlyearStatistic")
    public Map<String, Object> sqltest(String year) throws Exception{ 
        
        return service.yearloginNum(year);
    }
    
    @ResponseBody 
    @RequestMapping("/monthuser")
    public Map<String, Object> sqlmonthuser(String month) throws Exception{ 
        
        return service.monthuserloginNum(month);
    }
    
    @ResponseBody 
    @RequestMapping("/dayuser")
    public Map<String, Object> sqldayuser(String day) throws Exception{ 
        
        return service.dayuserloginNum(day);
    }
    
    @ResponseBody 
    @RequestMapping("/teamlogin")
    public Map<String, Object> sqldayteam(String team, String month) throws Exception{ 
    	 
         
        return service.teamloginNum(team, month);
    }
    
    @ResponseBody 
    @RequestMapping("/businesslogin")
    public Map<String, Object> sqlbusinesslogin(String string) throws Exception{ 
        
        return service.businessloginNum(null);
    }
    @ResponseBody 
    @RequestMapping("/restinfo")
    public Map<String, Object> restinfo(String year, String month) throws Exception{ 
    	ArrayList list = new ArrayList();
    	String Month1 = "01";
    	String Year1 = "18";
    	int i = 0;
    	int j = 0;
    	for(j = 0 ; j < 4 ; j++)
    	{
    	for(i = 0 ; i < 12 ; i++)
    	{
    		rest_parsing(Year1, Month1, list);
    		Month1 = Integer.toString(Integer.parseInt(Month1)+1);
    		if(Month1.length() == 1)
    		{
    			Month1 = "0" + Month1;
    		}
    		System.out.println(Month1);
    	}
    	Month1 = "01";
    	Year1 = Integer.toString(Integer.parseInt(Year1)+1);
    	System.out.println(list);
    	}
    	
    	
    	
        return service.restinfosearch(list);
        
           
        
    }
    @RequestMapping("/test") 
    public ModelAndView test() throws Exception{ 
        ModelAndView mav = new ModelAndView("test"); 
        mav.addObject("name", "devfunpj"); 
        List<String> resultList = new ArrayList<String>(); 
        resultList.add("!!!HELLO WORLD!!!"); 
        resultList.add("설정 TEST!!!"); 
        resultList.add("설정 TEST!!!"); 
        resultList.add("설정 TEST!!!!!"); 
        resultList.add("설정 TEST!!!!!!"); 
        mav.addObject("list", resultList); 
        return mav; 
    }
 private static String getTagValue(String tag, Element eElement) {
	    NodeList nlList = eElement.getElementsByTagName(tag).item(0).getChildNodes();
	    Node nValue = (Node) nlList.item(0);
	    if(nValue == null) 
	        return null;
	    return nValue.getNodeValue();
	}
 
 public ArrayList rest_parsing(String Year, String Month, ArrayList list)
 {
	 
		try{
				// parsing할 url 지정(API 키 포함해서)
				String url = "http://apis.data.go.kr/B090041/openapi/service/SpcdeInfoService/getRestDeInfo?serviceKey=VVaN0snYf14wjXJ4ZSDPGH0cOuHxjHPDFnnU4DVrPNEEG5fe0o4OLOvK%2FTCPn6xZ0DyNCIpi7wZanD6BoL3KBw%3D%3D&solYear=20"+Year+"&solMonth="+Month;
				
				DocumentBuilderFactory dbFactoty = DocumentBuilderFactory.newInstance();
				DocumentBuilder dBuilder = dbFactoty.newDocumentBuilder();
				Document doc = dBuilder.parse(url);
				System.out.println("test");
				// root tag 
				doc.getDocumentElement().normalize();
				System.out.println("Root element :" + doc.getDocumentElement().getNodeName());	
				// 파싱할 tag
				NodeList nList = doc.getElementsByTagName("item");
				System.out.println("파싱할 리스트 수 : "+ nList.getLength());
				for(int temp = 0; temp < nList.getLength(); temp++){
					Node nNode = nList.item(temp);
					if(nNode.getNodeType() == Node.ELEMENT_NODE){	
						Element eElement = (Element) nNode;
						System.out.println("######################");
						//System.out.println(eElement.getTextContent());
						System.out.println("종류  : " + getTagValue("dateKind", eElement));
						System.out.println("공휴일명  : " + getTagValue("dateName", eElement));
						System.out.println("휴일 여부 : " + getTagValue("isHoliday", eElement));
						System.out.println("일시  : " + getTagValue("locdate", eElement));
						System.out.println("seq  : " + getTagValue("seq", eElement));
						
						if(getTagValue("isHoliday", eElement).equals("Y"))
						{
							list.add(getTagValue("locdate", eElement));
						}
						// for end
					}	// if end
				}
			}
		 catch (Exception e){	
			e.printStackTrace();
		}	// try~catch end
		
		System.out.println(list);
		return list;
		// main end
 }
}