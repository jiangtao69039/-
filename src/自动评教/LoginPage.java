package 自动评教;

import java.awt.image.BufferedImage;
import java.io.ByteArrayInputStream;
import java.io.File;
import java.io.FileOutputStream;

import javax.imageio.ImageIO;


import org.apache.http.Header;
import org.apache.http.HttpEntity;
import org.apache.http.client.methods.CloseableHttpResponse;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.impl.client.CloseableHttpClient;
import org.apache.http.impl.client.HttpClients;
import org.apache.http.util.EntityUtils;

import com.sun.xml.internal.messaging.saaj.util.ByteOutputStream;

public class LoginPage {

	
	public String getLoginPageURL() throws Exception{
		String url = null;
		CloseableHttpClient httpClient = HttpClients.createDefault();
		
		try{
			HttpGet httpGetFirst = new HttpGet("http://jwc3.wzu.edu.cn");
			CloseableHttpResponse response = httpClient.execute(httpGetFirst);
			String body = EntityUtils.toString(response.getEntity());
		//	System.out.println(body);
			int urlstart = body.indexOf("top.location.href");
			if(urlstart<0)
				throw new Exception();
			String reDirectURL = body.substring(urlstart+19,body.indexOf("\"", urlstart+21));
			//System.out.println(reDirectURL);
			url=reDirectURL;
			try{
			
			}finally{
				response.close();
			}
			
		}finally{
			httpClient.close();
		}
		
		return url;
	}
	
	public String getSessionID(String reDirectURL) throws Exception {
		CloseableHttpClient httpClient = HttpClients.createDefault();
		String jsession=null;
		try {
			HttpGet httpGetFirst = new HttpGet(
					"http://rz.wzu.edu.cn/zfca?yhlx=teacher&login=0122579031373493708&url=js_main.aspx");
			CloseableHttpResponse response = httpClient.execute(httpGetFirst);
			String str = null;
			Header[] cookieHeaders = response.getHeaders("Set-Cookie");
			for (Header h : cookieHeaders) {
				//System.out.println(h.toString());
				if (h.getValue().contains("JSESSIONID=")) {
					str = h.getValue();
					break;
				}
			}
			String sessionid = str.substring(str.indexOf("JSESSIONID"),
					str.indexOf(";", str.indexOf("JSESSIONID=") + 10));
		//	System.out.println(sessionid);
			jsession=sessionid;

			try {

			} finally {
				response.close();
			}

		} finally {
			httpClient.close();
		}
		return jsession;
	}
	
	public byte[] getCode(String sessionid) throws Exception{
		CloseableHttpClient httpClient = HttpClients.createDefault();
		String code = null;
		byte[] bytearray;
		try{
			
		
		double x= Math.random();
		HttpGet httpGet = new HttpGet("http://rz.wzu.edu.cn/zfca/captcha.htm?random="+x);
		httpGet.addHeader("Cookie", sessionid);
		
		System.out.println("Executing request " + httpGet.getURI());
		CloseableHttpResponse response = httpClient.execute(httpGet);
		
		try{
			
			
			HttpEntity entity=response.getEntity();
			bytearray=EntityUtils.toByteArray(entity);
			/*ByteArrayInputStream bytearrayInput = new ByteArrayInputStream(bytearray);
			
			BufferedImage image = ImageIO.read(bytearrayInput);
			
			File file = new File("C:\\Users\\jiangtao\\Desktop\\code.jpeg");
			if(!file.exists())
				file.createNewFile();
			FileOutputStream fout = new 
					FileOutputStream(file);
			fout.write(bytearray);
			fout.flush();
			fout.close();*/
		}finally{
			response.close();
		}
		}finally{
			httpClient.close();
		}
		
		return bytearray;
	}
	
}
