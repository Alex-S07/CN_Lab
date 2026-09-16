import java.net.*;
import java.util.Scanner;

public class UDPClient {

    public static void main(String args[]) throws Exception {

        Scanner sc = new Scanner(System.in);

        DatagramSocket socket = new DatagramSocket();

        InetAddress ip = InetAddress.getByName("localhost");

        System.out.println("Enter a sentence:");

        String sentence = sc.nextLine();

        byte[] sendData = sentence.getBytes();

        DatagramPacket sendPacket = new DatagramPacket(sendData,sendData.length,ip,9876);

        socket.send(sendPacket);

        byte[] receiveData = new byte[1024];

        DatagramPacket receivePacket = new DatagramPacket(receiveData,receiveData.length);

        socket.receive(receivePacket);

        String translated = new String(receivePacket.getData(),0,receivePacket.getLength());

        System.out.println("\nTranslated Sentence:");
        System.out.println(translated);

        socket.close();
        sc.close();
    }
}
