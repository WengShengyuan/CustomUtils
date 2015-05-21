package main.custom;

import java.io.BufferedReader;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.net.HttpURLConnection;
import java.net.URL;
import java.util.HashMap;

public class NetUtils {

	/**
	 * @param args
	 * @throws Exception
	 */
	public static void main(String[] args) throws Exception {
//		System.out
//				.println(get(
//						"http://192.168.100.253:7755/login.cgi?act=login&s=192.168.100.253&p=5280&usrname=S1307&usrpwd=123456",
//						"UTF8"));
		HashMap<String, String> map = new HashMap<String, String>();
		map.put("usrpwd", "123456");
		map.put("usrname", "S1307");
		
		// System.out.println(post("http://192.168.100.253:5280/login",map,"8859_1"));
		System.out.println(post("http://192.168.100.253:5280/login", map,
				"UTF-8"));
		
		
		System.out.println(HttpClientUtil.sendPostRequest("http://192.168.100.253:5280/login", parseParam(map),false, null, null));
		System.out.println(HttpClientUtil.sendPostSSLRequest("http://192.168.100.253:5280/login", map));
		System.out.println(HttpClientUtil.sendPostRequestByJava("http://192.168.100.253:5280/login", map));
	}

	public static String get(String pageUrl, String encoding) throws Exception {

		StringBuffer sb = new StringBuffer();
		try {
			// 构建一URL对象
			URL url = new URL(pageUrl);
			// 使用openStream得到一输入流并由此构造一个BufferedReader对象
			BufferedReader in = new BufferedReader(new InputStreamReader(
					url.openStream(), encoding));
			String line;
			// 读取www资源
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

	public static String post(String postUrl, HashMap<String, String> map,
			String encoding) throws Exception {
		URL url = new URL(postUrl);
		HttpURLConnection  connection = (HttpURLConnection) url.openConnection();
		try {
			connection.setRequestMethod("POST");
			connection.setDoOutput(true);
			connection.setDoInput(true);
			connection.setUseCaches(false);
			connection.setRequestProperty("Content-Type",
					"application/x-www-form-urlencoded");
			OutputStreamWriter osw = new OutputStreamWriter(
					connection.getOutputStream(), encoding);
			osw.write(parseParam(map));
			osw.flush();
			osw.close();
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			if (connection != null) {
				connection.disconnect();
			}
		}
		// 读取返回内容
		StringBuffer buffer = new StringBuffer();
		try {
			// 一定要有返回值，否则无法把请求发送给server端。
			BufferedReader br = new BufferedReader(new InputStreamReader(
					connection.getInputStream(), encoding));
			String temp;
			while ((temp = br.readLine()) != null) {
				buffer.append(temp);
				buffer.append("\n");
			}
		} catch (Exception e) {
			e.printStackTrace();
		}

		return buffer.toString();

	}

	private static String parseParam(HashMap<String, String> map)
			throws Exception {
		StringBuilder sb = new StringBuilder();
		for (String key : map.keySet()) {
			sb.append(key).append("=").append(map.get(key));
			sb.append("&");
		}
		System.out.println("param:" + sb.substring(0, sb.length() - 1));
		return sb.substring(0, sb.length() - 1);

	}

}
