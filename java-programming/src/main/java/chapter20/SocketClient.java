package chapter20;

import java.io.DataInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.net.Socket;

public class SocketClient {
    private static final String SERVER = "localhost";
    private static final int PORT = 9999;

    public static void main(String[] args) throws IOException {
        Socket socket = new Socket(SERVER, PORT);
        InputStream inputStream = socket.getInputStream();
        DataInputStream dataInputStream = new DataInputStream(inputStream);
        for (int times = 0; times < 10; times++) {
            int number = dataInputStream.readInt();
            System.out.println("서버로 부터 받은 데이터 : " + number);

        }
        socket.close();
    }
}
