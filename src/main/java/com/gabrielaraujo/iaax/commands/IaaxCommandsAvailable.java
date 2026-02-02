package com.gabrielaraujo.iaax.commands;

import lombok.Getter;

@Getter
public enum IaaxCommandsAvailable {
    INPUT("--input"),
    OUTPUT("--output");

    private String value;

    IaaxCommandsAvailable(String value) {
       this.value = value;
    }
}
