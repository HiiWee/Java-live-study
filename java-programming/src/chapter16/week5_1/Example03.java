package chapter16.week5_1;

import java.io.DataOutputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.util.Scanner;

public class Example03 {
    public static void main(String[] args) throws IOException {
        Scanner sc = new Scanner(System.in);
        FileOutputStream fileOutputStream = new FileOutputStream("phone.txt");
        DataOutputStream dataOutputStream = new DataOutputStream(fileOutputStream);

        for (int i = 0; i < 5; i++) {
            String name = sc.next();
            String phone = sc.next();

            dataOutputStream.writeUTF(name + " ");
            dataOutputStream.writeUTF(phone + "\n");
        }
    }
}
