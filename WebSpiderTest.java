import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.MalformedURLException;
import java.net.URL;
import java.nio.charset.Charset;
import java.util.ArrayList;
import java.util.List;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class WebSpiderTest {
    public static String grtUrlContent(String urlStr,String charset){
        StringBuffer sb = new StringBuffer();
        try {
            URL url = new URL(urlStr);
            BufferedReader reader = new BufferedReader(new InputStreamReader(url.openStream(), Charset.forName(charset)));
            String temp = "";
            while ((temp = reader.readLine()) != null) {
                sb.append(temp);
            }
        } catch (MalformedURLException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }
        return sb.toString();
    }

    public static List<String> getMatcherSubstr(String destStr,String regexStr){

       // Pattern p = Pattern.compile("<a(\\s\\S)</a>");//超链接整个内容
        Pattern p = Pattern.compile(regexStr);
        Matcher m = p.matcher(destStr);

        List<String> result = new ArrayList<>();
        while (m.find()){
            result.add(m.group());
        }
        return result;


    }
    public static void main(String[] args) {
        String  destStr = grtUrlContent("http://www.163.com", "gbk");
        List<String> result = getMatcherSubstr(destStr,"href=\"[\\w\\s\\./:]+?\"");
        for (String temp : result){
            System.out.println(temp);
        }
    }

}
