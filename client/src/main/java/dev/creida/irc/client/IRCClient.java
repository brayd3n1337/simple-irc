package dev.creida.irc.client;

import java.io.*;
import java.net.InetSocketAddress;
import java.net.Socket;
import java.nio.charset.StandardCharsets;

public final class IRCClient {
    // the socket is the connection between the client and the server
    private final Socket socket;

    // the input and output streams are used to read and write to the socket.
    private BufferedReader input;
    private BufferedWriter output;

    public IRCClient() throws IOException {
        // create a new socket object.
        this.socket = new Socket();
        // try to connect to the hostname and port then time out if the time exceeds 10000 milliseconds.
        this.socket.connect(new InetSocketAddress(ServerConstants.HOSTNAME, ServerConstants.PORT));
    }

    public void connect() throws IOException {
        // if the socket is null notify that we couldn't connect to the socket. then return.
        if (this.socket == null) {
            System.out.println("Couldn't Connect To Socket");
            return;
        }

        // create a new input and output stream to read and write to the socket.
        this.input = new BufferedReader(new InputStreamReader(socket.getInputStream()));
        this.output = new BufferedWriter(new OutputStreamWriter(socket.getOutputStream(), StandardCharsets.UTF_8));

        // read the server messages. When implementing into a client send it to the chat (client side only obviously).
        this.readServerMessages();
    }

    private void readServerMessages() {
        // if the socket is null return.
        if (this.socket == null) {
            return;
        }

        new Thread(() -> {
            try {
                // the server input line
                String line;
                while ((line = input.readLine()) != null) {
                    // the message from the server
                    System.out.println("Server message: " + line);
                }
            } catch (IOException e) {
                // notify that we couldn't read the server message.
                System.err.println("Failed to read server message");
            }
        }).start();
    }

    /**
     * This method will disconnect the client from the server. This should be called on the shutdown or disable of the IRC Module.
     * @throws IOException If the socket is not connected or closed.
     */
    public void disconnect() throws IOException {
        // if the socket isnt connect and isnt closed return.
        if (!this.socket.isConnected() && !this.socket.isClosed())
            return;

        // close the socket.
        this.socket.close();
    }

    public void sendMessage(final String message) throws IOException {
        // write the message.
        this.output.write(message);

        // write a new line.
        this.output.newLine();

        // flush the output stream.
        this.output.flush();

        // print the message.
        System.out.println(message);
    }

}