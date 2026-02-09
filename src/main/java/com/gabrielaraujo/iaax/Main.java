package com.gabrielaraujo.iaax;

import com.fasterxml.jackson.dataformat.xml.XmlMapper;
import com.gabrielaraujo.iaax.commands.IaaxRequest;
import com.gabrielaraujo.iaax.commands.registries.IaaxCommandsControllerRegistry;
import com.gabrielaraujo.iaax.modules.aws.AwsInfrastructure;
import com.gabrielaraujo.iaax.modules.aws.AwsTranspiler;
import com.gabrielaraujo.iaax.modules.aws.tags.AwsVpc;
import com.gabrielaraujo.iaax.environment.registries.IaaxVariablesRegistry;
import com.gabrielaraujo.iaax.tags.Infrastructure;

import java.io.IOException;
import java.nio.file.Files;

public class Main {
    public static void main(String[] args) throws IOException {
        var xmlMapper = new XmlMapper();

        IaaxRequest request = IaaxCommandsControllerRegistry.SINGLETON.serve(args);
        // TODO: Refactor to dynamic infrastructure module
        Infrastructure<AwsVpc> infra = xmlMapper.readValue(request.getInput(), AwsInfrastructure.class);
        IaaxVariablesRegistry.SINGLETON.loadVariables(infra);

        var file = AwsTranspiler.transpile(request.getInput());
        Files.writeString(request.getOutput(), file);
    }
}