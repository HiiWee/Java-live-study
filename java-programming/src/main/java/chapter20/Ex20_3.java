package chapter20;

import java.io.IOException;
import java.io.InputStream;
import java.net.URL;
import java.net.URLConnection;

public class Ex20_3 {
    public static void main(String[] args) throws IOException {
        URL wiki = new URL("https://en.wikipedia.org/wiki/Random-access_channel");
        URLConnection wikiConnection = wiki.openConnection();
        System.out.println("문서의 타입: " + wikiConnection.getContentType());
        System.out.println("======== 문서의 내용 =======");
        InputStream input = wikiConnection.getInputStream();
        int c;

        while (((c = input.read()) != -1)) {
            System.out.print((char) c);
        }
        input.close();
    }
}
