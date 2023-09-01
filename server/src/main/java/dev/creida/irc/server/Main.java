package dev.creida.irc.server;

import java.io.IOException;
import java.net.ServerSocket;

class Main {

    public static void main(final String[] args) throws IOException {
        // create a new object of the IRC class.
        final IRC irc = new IRC(new ServerSocket(6667));

        // start the IRC.
        irc.start();
    }
}