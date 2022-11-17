package socket;

import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.io.IOException;
import java.net.Socket;
import java.util.Scanner;


class ClientSend extends Thread {
    private final Socket socket;
    private final Scanner scanner = new Scanner(System.in);

    public ClientSend(Socket socket) {
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
                System.out.print("클라이언트: ");
                String sendClientMessage = scanner.nextLine();
                messageWriter.writeUTF(sendClientMessage);
                messageWriter.flush();
            }
        } catch (IOException e) {
            System.out.println(e.getMessage());
        }
    }
}

class ClientReceive extends Thread {
    private final Socket socket;

    public ClientReceive(Socket socket) {
        this.socket = socket;
    }

    @Override
    public void run() {
        try {
            DataInputStream messageReader = new DataInputStream(socket.getInputStream());
            while (true) {
                String clientReceiveMessage = messageReader.readUTF();
                if (clientReceiveMessage.equals("exit")) {
                    break;
                }
                System.out.println("\n서버 메시지>> " + clientReceiveMessage);
                System.out.print("클라이언트: ");
            }
        } catch (IOException e) {
            System.out.println(e.getMessage());
        } finally {
            try {
                socket.close();
            } catch (IOException e) {
                System.out.println(e.getMessage());
            }
        }
    }
}
public class Client {
    public static void main(String[] args) {
        try {
            Socket socket = new Socket("localhost", 9999);
            System.out.println("접속 완료");
            ClientReceive clientReceive = new ClientReceive(socket);
            clientReceive.start();
            ClientSend clientSend = new ClientSend(socket);
            clientSend.start();
        } catch (IOException e) {
            throw new RuntimeException(e);
        }

    }
}
