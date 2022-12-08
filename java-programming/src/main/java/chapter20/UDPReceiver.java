package chapter20;

import java.io.IOException;
import java.net.DatagramPacket;
import java.net.DatagramSocket;

public class UDPReceiver {
    private static final int PORT = 9999;
    private static final int TIMES = 10;

    public static void main(String[] args) throws IOException {
        DatagramSocket datagramSocket = new DatagramSocket(PORT);
        int time = 1;
        while (time <= TIMES) {
            byte[] buffer = new byte[30];
            DatagramPacket datagramPacket = new DatagramPacket(buffer, buffer.length);
            datagramSocket.receive(datagramPacket);
            String receiveData = new String(datagramPacket.getData());
            System.out.println("수신된 데이터 : " + receiveData);
            time++;
        }
    }
}
