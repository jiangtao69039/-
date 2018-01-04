package 第一章_全面解析网络爬虫;

import java.io.File;
import java.io.FileOutputStream;








import java.util.List;
import java.util.Scanner;









import com.gargoylesoftware.htmlunit.WebClient;
import com.gargoylesoftware.htmlunit.html.DomElement;
import com.gargoylesoftware.htmlunit.html.DomNode;
import com.gargoylesoftware.htmlunit.html.DomNodeList;
import com.gargoylesoftware.htmlunit.html.HtmlButton;
import com.gargoylesoftware.htmlunit.html.HtmlElement;
import com.gargoylesoftware.htmlunit.html.HtmlInput;
import com.gargoylesoftware.htmlunit.html.HtmlOption;
import com.gargoylesoftware.htmlunit.html.HtmlPage;
import com.gargoylesoftware.htmlunit.html.HtmlSelect;
import com.gargoylesoftware.htmlunit.util.Cookie;

import 自动评教.LoginPage;
import 自动评教.MyWindow;

public class 下载验证码图片测试 {

	public static void main(String[] args) throws Exception{
		LoginPage loginPage = new LoginPage();
	//	loginPage.getCode(loginPage.getSessionID(loginPage.getLoginPageURL()));
	
		//String jsession = loginPage.getSessionID(loginPage.getLoginPageURL());
		//loginPage.getCode(jsession);
	//	System.out.println("获取验证码的cokie sessionid"+jsession);
	//	Cookie cookie = new Cookie("session","JSESSIONID",jsession.substring(jsession.indexOf("=")+1, jsession.length()));
		
		System.out.println("----------------------------------------------");
		WebClient client = new WebClient();
		client.getOptions().setJavaScriptEnabled(false);
		client.getOptions().setCssEnabled(false);
		client.getCookieManager().setCookiesEnabled(true);
		//client.getCookieManager().addCookie(cookie);
		HtmlPage page = client.getPage(loginPage.getLoginPageURL());
		String codecookie = null;
		for(Cookie c:client.getCookieManager().getCookies())
		{
			codecookie="";
			System.out.println("cookie name:"+c.getName());
			System.out.println("cookie value:"+c.getValue());
			codecookie=codecookie+c.getName();
			codecookie=codecookie+"=";
			codecookie=codecookie+c.getValue();
			
		}
		MyWindow window = new MyWindow(loginPage.getCode(codecookie));
		
		
		while(true){
			if(window.getStrcode()!=null )
			{
			break;
			}

			Thread.sleep(1000);
		}
		
		HtmlInput name = (HtmlInput) page.getElementById("username");
		HtmlInput password = (HtmlInput) page.getElementById("password");
		HtmlInput code = (HtmlInput) page.getElementById("j_captcha_response");
		HtmlButton submit = (HtmlButton) page.getElementsByTagName("button").get(0);
		
		name.setValueAttribute(window.getStrusername());
		password.setValueAttribute(window.getStrpass());
		//System.out.println("输入验证码");
		//Scanner s=new Scanner(System.in);
		//String codeinput = s.nextLine();
		code.setValueAttribute(window.getStrcode());
		//client.getCookieManager().addCookie(cookie);
		 HtmlPage page2=submit.click();
		
		 System.out.println(page2.asText());
		 
		 DomElement targetA = null;
		 for(DomElement n:page2.getElementsByTagName("a"))
		 {
			 if(n.getAttribute("onclick").equals("GetMc('教学质量评价');"))
			 {
				 targetA =n;
				 break;
			 }
		 }
		 if(targetA==null)
			 throw new Exception("已经评价过");
		 
		 HtmlPage p1 = targetA.click();
		 System.out.println("评价点击-----------------------------");
		// System.out.println(p1.asText());
		 
		 boolean finish =false;
		while (true) {
			System.out.println("开始评价-------------------");
			HtmlInput submitbtn = (HtmlInput) p1.getElementById("Button1");
			HtmlInput Button2 = (HtmlInput) p1.getElementById("Button2");
			String content = Button2.getAttribute("value");
			if(content.length() <10)
				{
				submitbtn = Button2;
				finish = true;
				}
			DomElement trpjs = p1.getElementById("trPjs");
			if(trpjs !=null)
			{DomElement tbody = trpjs.getElementsByTagName("tbody").get(0);
			DomNodeList<HtmlElement> trlist = tbody.getElementsByTagName("tr");

			for (int i = 1; i < trlist.getLength(); i++) {
				HtmlElement tr = trlist.get(i);
				// System.out.println(tr.getElementsByTagName("td").get(0).getTextContent());
				HtmlElement td = tr.getElementsByTagName("td").get(3);
				HtmlSelect select = (HtmlSelect) td.getElementsByTagName(
						"select").get(0);
				HtmlOption option = null;
				if (i == 1) {
					option = select.getOption(2);
					// System.out.println(option.getValueAttribute()+option.getTextContent());
					option.click();
				} else {
					option = select.getOption(1);
					// System.out.println(option.getValueAttribute()+option.getTextContent());
					option.click();
				}

			}
			}
			DomElement trpkc = p1.getElementById("trPkc");
			if(trpkc!=null)
			{DomElement tbodykc = trpkc.getElementsByTagName("tbody").get(0);
			DomNodeList<HtmlElement> trlistkc = tbodykc
					.getElementsByTagName("tr");
			for (int i = 1; i < trlistkc.getLength(); i++) {
				HtmlElement tr = trlistkc.get(i);
				// System.out.println(tr.getElementsByTagName("td").get(0).getTextContent());
				HtmlElement td = tr.getElementsByTagName("td").get(3);
				HtmlSelect select = (HtmlSelect) td.getElementsByTagName(
						"select").get(0);
				HtmlOption option = null;
				if (i == 1) {
					option = select.getOption(2);
					// System.out.println(option.getValueAttribute()+option.getTextContent());
					option.click();
				} else {
					option = select.getOption(1);
					// System.out.println(option.getValueAttribute()+option.getTextContent());
					option.click();
				}
			}
			}
			
			p1=submitbtn.click();
			//Thread.sleep(1000);
			System.out.println("填完一个页面-----------");
			if(finish)
				break;
		}
		 System.out.println("成功！");
	}
	

}
