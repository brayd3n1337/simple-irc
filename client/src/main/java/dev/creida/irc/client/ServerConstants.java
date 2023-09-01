package dev.creida.irc.client;

/**
 * The server constants class provides constants for the server including the Hostname and Port.
 *
 * @author Creida
 * @since 9/1/2023
 * @version 1.0.0
 */
public final class ServerConstants {
    // the host name is the ip address of the server.
    public static final String HOSTNAME = "127.0.0.1";

    // the port is the port that the server is listening on.
    public static final int PORT = 6667;


    /**
     * Private constructor because this class should not be instantiated.
     */
    private ServerConstants() {}
}