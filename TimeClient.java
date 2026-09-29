import java.net.*; 
import java.util.Scanner; 

public class TimeClient {
	public static void main(String[] args) throws Exception {
		DatagramSocket clientSocket = new DatagramSocket();
	
		Scanner sc = new Scanner(System.in);
		System.out.print("Enter server IP address: ");
		String serverIP = sc.nextLine();
		System.out.print("Enter server port number: ");
		int serverPort = Integer.parseInt(sc.nextLine());
		InetAddress serverAddress = InetAddress.getByName(serverIP);
		String requestMsg = "TIME_REQUEST";
		byte[] sendData = requestMsg.getBytes();
		DatagramPacket requestPacket = new DatagramPacket(sendData, sendData.length, serverAddress, serverPort);
		clientSocket.send(requestPacket);
		byte[] receiveBuffer = new byte[1024];
		DatagramPacket responsePacket = new DatagramPacket(receiveBuffer, receiveBuffer.length);
		clientSocket.receive(responsePacket);
		String timeReceived = new String(responsePacket.getData(), 0, responsePacket.getLength());
		System.out.println("Time received from server: " + timeReceived);
		clientSocket.close();
	}
}
