package com.fasih.thoughtworkstest;

import java.io.BufferedReader;
import java.io.IOException;

import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;

public class Main {
    private static Path path;
    private static BufferedReader reader;
    private static CommandProvider provider;
    private static Invoker invoker;

    // ------------------------------------------------------------------------------
    public static void main(final String[] args) {
        if (args.length == 0) {
            prUsage();
        }

        path = Paths.get(args[0]);
        if (Files.isDirectory(path)) {
            prUsage();
        }

        try {
            reader = Files.newBufferedReader(path);
            provider = new CommandProvider();
            invoker = new Invoker();

            String line = null;
            while ((line = reader.readLine()) != null) {
                invoker.invoke(provider.getCommandFor(line));
            }

            for (Object o : invoker.getResults()) {
                if (o != null) {
                    System.out.println(o);
                }
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    // ------------------------------------------------------------------------------
    public static void prUsage() {
        System.err.println("Please provide the path to the input file");
        System.err.println("Now exiting...");
        System.exit(1);
    }
    // ------------------------------------------------------------------------------
}
