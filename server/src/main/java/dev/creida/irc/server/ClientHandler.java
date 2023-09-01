package dev.creida.irc.server;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.net.Socket;
import java.util.Map;
import java.util.Set;

/**
 * This class is responsible for handling the client connection and reading from the socket.
 *
 * <p>
 *     This class is responsible from receiving messages from the client and sending them to the server.
 *     It will also tell you if it could not read from the socket.
 * </p>
 *
 * @author Creida
 * @since 9/1/2023, 1.0.0
 * @version 1.0.0
 */
public final class ClientHandler implements Runnable {
    // the socket is the connection between the client and the server
    private final Socket socket;
    // the usersAndClientCache is a set of sockets that are connected to the server
    private final Set<Socket> clientCache;


    public ClientHandler(final Socket socket, Set<Socket> clientCache) {
        this.socket = socket;
        this.clientCache = clientCache;
    }

    @Override
    public void run() {
        System.out.println("Starting client handler...");

        // Simple try resources block to read from the socket
        try (final BufferedReader bufferedReader = new BufferedReader(new InputStreamReader(socket.getInputStream()))) {
            String line;
            while ((line = bufferedReader.readLine()) != null) {
                // print the recieved message.
                System.out.println("Received message: " + line);
            }

            // for each client socket in the cache print using print writer.
            for (final Socket clientSocket : this.clientCache) {
                final PrintWriter printWriter = new PrintWriter(clientSocket.getOutputStream(), true);
                printWriter.println(line);
            }

            // if the socket is closed, print the stack trace and state that we could not read from the socket.
        } catch (final Exception exception) {
            exception.printStackTrace();
            System.out.println("Could not read from socket.");
        }
    }
}