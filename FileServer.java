import java.io.*;
import java.net.*;

public class FileServer {
public static void main(String[] args) throws IOException {
ServerSocket serverSocket = new ServerSocket(6000);
long serverPid = ProcessHandle.current().pid();
System.out.println("Concurrent File Server Started on port 6000 (PID: " + serverPid + ")");
while (true) {
Socket socket = serverSocket.accept();
System.out.println("New client connected: " + socket.getInetAddress());
FileHandler handler = new FileHandler(socket, serverPid);
handler.start();
        }
    }
}

class FileHandler extends Thread {
private Socket socket;
private long serverPid;
FileHandler(Socket socket, long serverPid) {
this.socket = socket;
this.serverPid = serverPid;
}
public void run() {
try (BufferedReader in = new BufferedReader(
new InputStreamReader(socket.getInputStream())
);PrintWriter out = new PrintWriter(
socket.getOutputStream(), true)) {
String fileName = in.readLine();
System.out.println("Requested file: " + fileName + " from " + socket.getInetAddress());
out.println("PID:" + serverPid);
File file = new File(fileName);
if (file.exists() && !file.isDirectory()) {
out.println("FOUND");
BufferedReader fileReader = new BufferedReader(new FileReader(file));
String line;
while ((line = fileReader.readLine()) != null) {out.println(line);}
fileReader.close(); } 
else {
out.println("NOTFOUND");
out.println("Requested file \"" + fileName + "\" does not exist on the server.");
}
out.println("<<END>>");
} catch (IOException e) {
            e.printStackTrace();
} finally {
try {
socket.close();
            } catch (IOException e) {
            }
        }
    }
}
