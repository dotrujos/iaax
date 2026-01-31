package com.gabrielaraujo.iaax;

import com.gabrielaraujo.iaax.aws.AwsTranspiler;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;

//TIP To <b>Run</b> code, press <shortcut actionId="Run"/> or
// click the <icon src="AllIcons.Actions.Execute"/> icon in the gutter.
public class Main {
    public static void main(String[] args) throws IOException {
        String inputFile = null;
        String outputFile = null;

        for (int i = 0; i < args.length; i++) {
            if ("--file".equals(args[i]) && i + 1 < args.length) {
                inputFile = args[++i];
            } else if ("--output".equals(args[i]) && i + 1 < args.length) {
                outputFile = args[++i];
            }
        }

        if (inputFile == null || outputFile == null) {
            System.err.println("Usage: java com.gabrielaraujo.iaax.Main --file <input-file> --output <output-file>");
            System.exit(1);
        }

        var file = AwsTranspiler.transpile(inputFile);
        Path output = Path.of(outputFile);
        Files.writeString(output, file);
    }
}