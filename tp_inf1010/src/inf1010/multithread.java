

package inf1010;

import java.io.PrintStream;
import java.io.IOException;
import java.net.Socket;
import java.net.ServerSocket;


// class serveur
public class multithread {
   // serveur socket.
  private static ServerSocket serverSocket = null;
  // socket client .
  private static Socket clientSocket = null;
  // ne peut quaccepeter un maximum de 5 utlisateurs
  private static final int maxClientsCount = 5;
  private static final clientThread[] threads = new clientThread[maxClientsCount];

  public static void main(String args[]) {

    // numero de port par defaut
    int portNumber = 1234;
    if (args.length < 1) {
      System.out.println("Usage: java MultiThreadChatServerSync <portNumber>\n"
          + "maintenant utilise le port =" + portNumber);
    } else {
      portNumber = Integer.valueOf(args[0]).intValue();
    }

    // ouvre un socket serveur (default 1234). 
   
    try {
      serverSocket = new ServerSocket(portNumber);
    } catch (IOException e) {
      System.out.println(e);
    }

    // creer un socket client pour chaque connexion et le passer au nouveau client

    while (true) {
      try {
        clientSocket = serverSocket.accept();
        int i = 0;
        for (i = 0; i < maxClientsCount; i++) {
          if (threads[i] == null) {
            (threads[i] = new clientThread(clientSocket, threads)).start();
            break;
          }
        }
        if (i == maxClientsCount) {
          PrintStream os = new PrintStream(clientSocket.getOutputStream());
          os.println("serveur occupé reessayer plus tard ");
          os.close();
          clientSocket.close();
        }
      } catch (IOException e) {
        System.out.println(e);
      }
    }
  }  
}
