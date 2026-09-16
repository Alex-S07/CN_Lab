import java.net.*;

public class UDPServer {

    public static void main(String args[]) throws Exception {

        DatagramSocket socket = new DatagramSocket(9876);
        byte[] receiveData = new byte[1024];

        System.out.println("Server Started....");

        DatagramPacket receivePacket = new DatagramPacket(receiveData, receiveData.length);

        socket.receive(receivePacket);

        String sentence = new String(receivePacket.getData(),0,receivePacket.getLength());

        System.out.println("Received: " + sentence);

        sentence = sentence.replace("tbh", "to be honest");
        sentence = sentence.replace("ig", "I guess");
        sentence = sentence.replace("tbf", "to be fair");
        sentence = sentence.replace("atm", "at the moment");
        sentence = sentence.replace("irl", "in real life");
        sentence = sentence.replace("lol", "laughing out loud");
        sentence = sentence.replace("asap", "as soon as possible");
        sentence = sentence.replace("omg", "oh my God");
        sentence = sentence.replace("ttyl", "talk to you later");
        sentence = sentence.replace("idk", "I don't know");
        sentence = sentence.replace("nvm", "never mind");
        sentence = sentence.replace("idc", "I don't care");

        byte[] sendData = sentence.getBytes();

        DatagramPacket sendPacket = new DatagramPacket(sendData,sendData.length,receivePacket.getAddress(),receivePacket.getPort());

        socket.send(sendPacket);

        System.out.println("Translation Sent.");

        socket.close();
    }
}
