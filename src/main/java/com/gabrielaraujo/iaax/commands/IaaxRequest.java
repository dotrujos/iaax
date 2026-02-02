package com.gabrielaraujo.iaax.commands;

import lombok.AccessLevel;
import lombok.AllArgsConstructor;
import lombok.Getter;

import java.io.File;
import java.nio.file.Path;

@Getter
@AllArgsConstructor(access = AccessLevel.PRIVATE)
public class IaaxRequest {
    private File input;
    private Path output;

    private IaaxRequest() {}

    public static IaaxRequest of(File input, Path output) {
        return new IaaxRequest(input, output);
    }
}
