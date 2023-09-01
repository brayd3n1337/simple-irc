import dev.creida.irc.client.IRCClient;

/**
 * This class is responsible for starting the IRC Client, connecting, and sending messages although if you implemented this into a minecraft client you would not use this class to send messages.
 *
 * @author Creida
 * @since 9/1/2023
 * @version 1.0.0
 */
class Main {
    public static void main(String[] args) {
        try {
            // create a new object of the irc client.
            final IRCClient ircClient = new IRCClient();

            // connect to the server.
            ircClient.connect();

            // send a message, when implementing this into a minecraft client you would want to create a new command class.
            ircClient.sendMessage("Crazy IRC");
        } catch (final Exception exception) {
            exception.printStackTrace();
        }
    }
}