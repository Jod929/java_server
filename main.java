import java.io.*;
import java.net.ServerSocket;
import java.net.Socket;
import java.util.Scanner;

public class MyServer {
  public static void main(String[] args) {
    connectToServer();
  }

  public static void connectToServer() {

    // try to connect to the server on port 8080

    try(ServerSocket serverSocket = new ServerSocket(8080)) {
      Socket connectionSocket = serverSocket.accept();

      // create input & output streams for the connection

      InputStream inputToServer = connectionSocket.getInputStream();
      OutputStream outputFromServer = connectionSocket.getOutputStream();

      Scanner scanner = new Scanner(inputToServer, "UTF-8");
      PrintWriter serverPrintOut = new PrintWriter(new OutputStreamWriter(outputFromServer, "UTF-8"), true);

      serverPrintOut.println("Hello World!, Enter some text to exit.");

      // take input from the client

      boolean done = false;

      while (!done && scanner.hasNextLine()) {

        String line = scanner.nextLine();
        serverPrintOut.println("Echo from <Your Name Here> Server: " + line);

        if (line.toLowerCase().trim().equals("peace")) {
          done = true;
        }
      }

    } catch (IOException e) {
      e.printStackTrace();
    }
  }

}
