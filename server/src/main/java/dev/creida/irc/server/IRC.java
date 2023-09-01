package dev.creida.irc.server;

import java.io.IOException;
import java.net.ServerSocket;
import java.net.Socket;
import java.util.*;

/**
 * This class is responsible for handling the IRC server.
 *
 * <p>
 *     This class is responsible for adding clients to the cache and starting. It will also tell you if it could not accept a client connection.
 *     This class will also tell you where the client connection is from.
 * </p>
 *
 * @author Creida
 * @since 9/1/2023, 1.0.0
 * @version 1.0.0
 */
public final class IRC {

    // the serverSocket is the socket that the server is listening on.
    private final ServerSocket serverSocket;

    // the clientCache is a set of sockets that are connected to the server
    private final Set<Socket> clientCache = Collections.synchronizedSet(new HashSet<>());

    public IRC(final ServerSocket serverSocket) {
        this.serverSocket = serverSocket;
    }

    public void start() {
        // notify that we are starting the IRC server.
        System.out.println("Starting IRC server...");
        try {
            // notify that we are waiting for a client connection.
            System.out.println("Waiting for client connection...");

            // accept the client connection.
            final Socket clientSocket = serverSocket.accept();

            // notify where the client connection is from.
            System.out.println("Client connected from " + clientSocket.getRemoteSocketAddress());

            // add the client to the cache
            this.clientCache.add(clientSocket);

            // start a new thread for the client
            new Thread(new ClientHandler(clientSocket, this.clientCache)).start();
        } catch (final IOException exception) {
            // if the server could not accept a client connection, print the stack trace and state that we could not accept a client connection.
            exception.printStackTrace();
            System.out.println("Unable to accept client connection.");
        }
    }
}
