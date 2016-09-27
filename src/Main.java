import org.w3c.dom.Document;
import org.xml.sax.SAXException;

import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import javax.xml.parsers.ParserConfigurationException;
import java.io.IOException;
import java.io.OutputStream;
import java.net.HttpURLConnection;
import java.net.URL;

public class Main {

    public static void main(String[] args) throws IOException,ParserConfigurationException,SAXException {
        StringBuilder sb = new StringBuilder();
              sb.append("<?xml version=\"1.0\" encoding=\"utf-8\"?>                                 ")
                .append("<soap12:Enverlope xmlns:xsi=\"http://www.w3.org/2001/XMLSchema-instance\"  ")
                      .append("            xmlns:xsd=\"http://www.w3.org/2001/XMLSchema\"           ")
                      .append("            xmlns:soap12=\"http://www.w3.org/2003/05/soap-envelope\">")
                      .append("<soap12:Body>")
                      .append(" <GetWeather xmlns=\"http://www.webserviceX.NET\">                    ")
                      .append("     <CityNmae>shanghai</CityName>")
                      .append("     <CountryName>China</CountryNmae>")
                      .append(" </GetWeather>")
                      .append("</soap12:Body>")
                      .append("</soap12:Envelope>");


        System.setProperty("http.proxyHost", "proxy6.taikanglife.com");
        System.setProperty("http.proxyPort", "8080");
//        Properties prop = System.getProperties();
//// 设置http访问要使用的代理服务器的地址
//        prop.setProperty("http.proxyHost", "proxy6.taikanglife.com");
//// 设置http访问要使用的代理服务器的端口
//        prop.setProperty("http.proxyPort", "8080");
//// 设置http访问要使用的代理服务器的用户名
//        prop.setProperty("http.proxyUser", "luzl03 ");
//// 设置http访问要使用的代理服务器的密码
//        prop.setProperty("http.proxyPassword", "Begin520");

        URL url = new URL("http://www.webservicex.net/globalweather.asmx");
        HttpURLConnection httpConn = (HttpURLConnection)url.openConnection();
        httpConn.setRequestProperty("User-Agent", "Mozilla/4.0 (compatible; MSIE 5.0; Windows NT; DigExt)");

        httpConn.setRequestProperty("Content-length",String.valueOf(sb.length()));
        httpConn.setRequestProperty("Content-Type","application/soap+xml;charset=utf-8");
        httpConn.setRequestMethod("POST");
        httpConn.setDoOutput(true);
        httpConn.setDoInput(true);



        OutputStream out = httpConn.getOutputStream();
        out.write(sb.toString().getBytes());
        out.close();

        DocumentBuilder builder = DocumentBuilderFactory.newInstance().newDocumentBuilder();
        Document doc = builder.parse(httpConn.getInputStream());
        String body = doc.getElementsByTagName("GetWeatherResult").item(0).getChildNodes().item(0).getNodeValue();
        System.out.println(body);


    }
}
