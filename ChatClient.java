import java.io.*;
import java.net.*;
import java.util.Scanner;

public class ChatClient {

    public static void main(String[] args) throws Exception {

        
        Socket socket = new Socket("localhost", 5000);

        BufferedReader in = new BufferedReader(
                new InputStreamReader(socket.getInputStream())
        );

        PrintWriter out = new PrintWriter(
                socket.getOutputStream(), true
        );

        Scanner sc = new Scanner(System.in);

        
        System.out.print("Enter your name: ");
        String name = sc.nextLine();

        
        out.println(name);

        
        Thread receive = new Thread(() -> {
            try {
                String msg;

                while ((msg = in.readLine()) != null) {
                    System.out.println(msg);
                }

            } catch (IOException e) {
                System.out.println("Disconnected from server.");
            }
        });

        receive.start();

        
        while (true) {
            String message = sc.nextLine();
            out.println(message);
        }
    }
}
