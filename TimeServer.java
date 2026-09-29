import java.net.*;
import java.util.Date;

public class TimeServer {
	public static void main(String[] args) throws Exception {
		DatagramSocket serverSocket = new DatagramSocket(9876);
		System.out.println("Concurrent UDP Time Server Started...");
		byte[] receiveBuffer = new byte[1024];
		while (true) {
			DatagramPacket requestPacket = new DatagramPacket(receiveBuffer, receiveBuffer.length);
			serverSocket.receive(requestPacket);
			System.out.println("Request received from " + requestPacket.getAddress() + ":" + requestPacket.getPort());
			RequestHandler handler = new RequestHandler(serverSocket, requestPacket);
			handler.start();
		}
	}
}

class RequestHandler extends Thread { 
	DatagramSocket socket; 
	DatagramPacket requestPacket; 
	RequestHandler(DatagramSocket socket, DatagramPacket requestPacket) {
		this.socket = socket;
		this.requestPacket = requestPacket;
	}
	public void run() {
		try {
			InetAddress clientAddress = requestPacket.getAddress();
			int clientPort = requestPacket.getPort();
			String timeString = new Date().toString();
			byte[] sendData = timeString.getBytes();
			DatagramPacket responsePacket = new DatagramPacket(sendData, sendData.length, clientAddress, clientPort);
			synchronized (socket) {
				socket.send(responsePacket);
			}
			System.out.println("Time sent to " + clientAddress + ":" + clientPort + " -> " + timeString);
		} 
		catch (Exception e) {
			e.printStackTrace();
		}
	}
}
			
