package main.custom;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.net.URL;

public class NetUtils {
	
	/**
	 * @param args
	 * @throws Exception 
	 */
	public static void main(String[] args) throws Exception {
		System.out.println(getPageSource("http://www.baidu.com","UTF8"));
	}
	
	
	public static String getPageSource(String pageUrl,String encoding) throws Exception { 
		
        StringBuffer sb = new StringBuffer();    
        try {    
            //构建一URL对象    
            URL url = new URL(pageUrl);    
            //使用openStream得到一输入流并由此构造一个BufferedReader对象    
            BufferedReader in = new BufferedReader(new InputStreamReader(url    
                    .openStream(), encoding));    
            String line;    
            //读取www资源    
            while ((line = in.readLine()) != null) {    
                sb.append(line);    
                sb.append("\n");  
            }    
            in.close();    
        } catch (Exception ex) {   
            throw ex;    
        }    
        return sb.toString();    
    } 

}
