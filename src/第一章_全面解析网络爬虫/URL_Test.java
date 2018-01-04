package 第一章_全面解析网络爬虫;

import java.io.IOException;

import org.apache.http.Header;
import org.apache.http.HttpMessage;
import org.apache.http.client.HttpClient;
import org.apache.http.client.methods.CloseableHttpResponse;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.entity.ContentType;
import org.apache.http.impl.client.CloseableHttpClient;
import org.apache.http.impl.client.HttpClients;
import org.apache.http.params.HttpParams;
import org.apache.http.util.EntityUtils;

public class URL_Test {

	public static void main(String[] args) throws Exception {
		// TODO Auto-generated method stub
		CloseableHttpClient httpClient = HttpClients.createDefault();
		
		try{
			HttpGet httpGet = new HttpGet("http://httpbin.org/get");
			
			
			System.out.println("Executing request " + httpGet.getURI());
			CloseableHttpResponse response = httpClient.execute(httpGet);
			try{
				System.out.println("----------------------------");
				System.out.println(response.getStatusLine());
				System.out.println("-------------------------------");
				
				Header[]  headers= response.getAllHeaders();
				
				for(Header head:headers)
					System.out.println(head.toString());
				
				System.out.println("-------------------------------");
				System.out.println(EntityUtils.toString(response.getEntity()));
				
				
				System.out.println("-------------------------------");
				System.out.println(response.getEntity());
				
				System.out.println("-------------------------------");
				ContentType contentType = ContentType.getOrDefault(response.getEntity());
				System.out.println(contentType.getMimeType());
				System.out.println(contentType.getCharset());
				System.out.println(contentType.toString());
			}finally{
				response.close();
			}
			
		}finally{
			httpClient.close();
		}
		
		
	}

}
