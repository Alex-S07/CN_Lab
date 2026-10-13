import java.io.*;
import java.net.*;
import java.util.Scanner;
public class FileClient{
public static void main(String[] args) throws IOException{
Scanner sc = new Scanner(System.in);
System.out.print("Enter server IP address: ");
String serverIP = sc.nextLine();
System.out.print("Enter server port number: ");
int serverPort = Integer.parseInt(sc.nextLine());
Socket socket = new Socket(serverIP, serverPort);
BufferedReader in = new BufferedReader(
            new InputStreamReader(socket.getInputStream())
        );
PrintWriter out = new PrintWriter(socket.getOutputStream(), true);
System.out.print("Enter the name of the file to request: ");
String fileName = sc.nextLine();
out.println(fileName);
String pidLine = in.readLine();
String status = in.readLine();
System.out.println();
System.out.println("Response from server (" + pidLine + ")");
if (status.equals("FOUND")) {
System.out.println("----- File Contents -----");
String line;
while (!(line = in.readLine()).equals("<<END>>")) {
System.out.println(line);}
} else {
String message = in.readLine();
in.readLine();
System.out.println(message);}
socket.close();
}
}
