package com.gabrielaraujo.iaax.commands.controller;

import com.gabrielaraujo.iaax.commands.IaaxCommandType;
import com.gabrielaraujo.iaax.commands.IaaxCommandsAvailable;
import com.gabrielaraujo.iaax.commands.IaaxModuleType;
import com.gabrielaraujo.iaax.commands.IaaxRequest;

import java.io.File;
import java.nio.file.Path;

public class IaaxCommandsController {
    private File input;
    private Path output;

    public IaaxRequest serve(String[] args) {
        for (int i = 0; i < args.length; i++) {
            var commandType = getCommandType(args[i]);
            if (commandType.equals(IaaxCommandType.ARGUMENT)) {
                var argument = args[i];
                if (!isCommandValid(argument))
                    return null;

                if (i + 1 >= args.length) return null;

                var value = args[i + 1];

                if (IaaxCommandsAvailable.INPUT.getValue().equals(argument)) {
                    this.input = new File(value);
                }

                if (IaaxCommandsAvailable.OUTPUT.getValue().equals(argument)) {
                    this.output = Path.of(value);
                }
            }
        }

        if (input == null || output == null) {
            return null; // TODO Refactor to show usage errors
        }

        return IaaxRequest.of(input, output);
    }

    private boolean isCommandValid(String command) {
        for (IaaxCommandsAvailable availableCommand : IaaxCommandsAvailable.values()) {
            if (availableCommand.getValue().equals(command)) {
                return true;
            }
        }
        return false;
    }

    private IaaxCommandType getCommandType(String arg) {
        if (arg.startsWith("--")) {
            return IaaxCommandType.ARGUMENT;
        }
        return IaaxCommandType.VALUE;
    }
}
