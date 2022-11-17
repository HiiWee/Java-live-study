package socket;

import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.io.IOException;
import java.net.ServerSocket;
import java.net.Socket;
import java.util.Scanner;

class ServerSend extends Thread {
    private final Socket socket;
    private final Scanner scanner = new Scanner(System.in);

    public ServerSend(Socket socket) {
        this.socket = socket;
    }

    @Override
    public void run() {
        try {
            DataOutputStream messageWriter = new DataOutputStream(socket.getOutputStream());
            while (true) {
                if (socket.isClosed()) {
                    System.out.println("---서버 종료---");
                    break;
                }
                System.out.print("서버: ");
                String serverSendMessage = scanner.nextLine();
                messageWriter.writeUTF(serverSendMessage);
                messageWriter.flush();
            }
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }
}

class ServerReceive extends Thread {
    private final Socket socket;

    public ServerReceive(Socket socket) {
        this.socket = socket;
    }

    @Override
    public void run() {
        try {
            DataInputStream messageReader = new DataInputStream(socket.getInputStream());

            while (true) {
                String serverReceiveMessage = messageReader.readUTF();
                if (serverReceiveMessage.equals("exit")) {
                    break;
                }
                System.out.println("\n클라이언트 메시지>> " + serverReceiveMessage);
                System.out.print("서버: ");
            }
        } catch (IOException e) {
            throw new RuntimeException(e);
        } finally {
            try {
                socket.close();
            } catch (IOException e) {
                System.out.println(e.getMessage());
            }
        }
    }
}

public class Server {
    public static void main(String[] args) throws IOException {
        ServerSocket listener = new ServerSocket(9999);
        System.out.println("연결 대기중...");
        Socket acceptSocket = listener.accept();
        System.out.println("연결 완료");
        ServerReceive serverReceive = new ServerReceive(acceptSocket);
        serverReceive.start();
        ServerSend serverSend = new ServerSend(acceptSocket);
        serverSend.start();
    }
}
