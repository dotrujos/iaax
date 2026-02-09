package com.gabrielaraujo.iaax.modules.aws.tags;

import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.dataformat.xml.annotation.JacksonXmlElementWrapper;
import com.fasterxml.jackson.dataformat.xml.annotation.JacksonXmlProperty;
import com.fasterxml.jackson.dataformat.xml.annotation.JacksonXmlRootElement;
import com.gabrielaraujo.iaax.environment.registries.IaaxVariablesRegistry;
import lombok.Getter;
import lombok.Setter;

import java.util.List;

@Setter
@JacksonXmlRootElement(localName = "vpc", namespace = "aws")
public class AwsVpc {
    @Getter
    @JacksonXmlProperty(localName = "ec2", namespace = "aws")
    @JacksonXmlElementWrapper(useWrapping = false)
    private List<AwsInstance> vms;

    @Getter
    @JacksonXmlProperty(localName = "subnets", namespace = "aws")
    private List<AwsSubnet> awsSubnets;

    @JacksonXmlProperty(isAttribute = true)
    private String cidr;

    @JacksonXmlProperty(isAttribute = true)
    private String region;

    @JacksonXmlProperty(isAttribute = true)
    private String name;

    @JsonInclude(JsonInclude.Include.NON_NULL)
    @JacksonXmlProperty(isAttribute = true)
    private String alias;

    public String getCidr() {
        if (IaaxVariablesRegistry.SINGLETON.haveVariable(cidr)) {
            return IaaxVariablesRegistry.SINGLETON.getVariable(cidr);
        }

        return cidr;
    }

    public String getRegion() {
        if (IaaxVariablesRegistry.SINGLETON.haveVariable(region)) {
            return IaaxVariablesRegistry.SINGLETON.getVariable(region);
        }

        return region;
    }

    public String getName() {
        if (IaaxVariablesRegistry.SINGLETON.haveVariable(name)) {
            return IaaxVariablesRegistry.SINGLETON.getVariable(name);
        }

        return name;
    }

    public String getAlias() {
        if (IaaxVariablesRegistry.SINGLETON.haveVariable(alias)) {
            return IaaxVariablesRegistry.SINGLETON.getVariable(alias);
        }

        return alias;
    }
}
