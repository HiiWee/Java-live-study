package chapter20;

import java.io.DataOutputStream;
import java.io.IOException;
import java.io.OutputStream;
import java.net.ServerSocket;
import java.net.Socket;

public class SocketServer {

    public static final int PORT = 9999;
    public static final int TIMES = 10;

    public static void main(String[] args) throws IOException {
        ServerSocket serverSocket = new ServerSocket(PORT);

        for (int times = 0; times < TIMES; times++) {
            Socket socket = serverSocket.accept();
            OutputStream outputStream = socket.getOutputStream();
            DataOutputStream dataOutputStream = new DataOutputStream(outputStream);
            for (int number = 1; number <= 10; number++) {
                dataOutputStream.write(number);
            }
            socket.close();
            times++;
        }
    }
}
