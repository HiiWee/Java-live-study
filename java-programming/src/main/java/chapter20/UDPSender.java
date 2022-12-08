package chapter20;

import java.io.IOException;
import java.net.DatagramPacket;
import java.net.DatagramSocket;
import java.net.InetAddress;

public class UDPSender {
    private static final String HOST = "localhost";
    private static final int PORT = 9999;

    public static void main(String[] args) throws IOException {
        DatagramSocket datagramSocket = new DatagramSocket();
        InetAddress byName = InetAddress.getByName(HOST);
        byte[] buffer = "Hello World".getBytes();
        DatagramPacket datagramPacket = new DatagramPacket(buffer, buffer.length, byName, PORT);
        datagramSocket.send(datagramPacket);
    }
}
