package com.gabrielaraujo.iaax.modules.aws.tags;

import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.dataformat.xml.annotation.JacksonXmlProperty;
import com.fasterxml.jackson.dataformat.xml.annotation.JacksonXmlRootElement;
import com.gabrielaraujo.iaax.registries.IaaxVariablesRegistry;
import lombok.Getter;
import lombok.Setter;

import java.util.List;

@Setter
@JacksonXmlRootElement(localName = "ec2", namespace = "aws")
public class AwsInstance {
    @JacksonXmlProperty(isAttribute = true)
    private String name;

    @JacksonXmlProperty(localName = "instance-type", namespace = "aws")
    private String instanceType;

    @JacksonXmlProperty(localName = "ami", namespace = "ami")
    private String ami;

    @JsonInclude(JsonInclude.Include.NON_NULL)
    @JacksonXmlProperty(localName = "subnet-name", namespace = "aws")
    private String subnetName;

    @Getter
    @JsonInclude(JsonInclude.Include.NON_NULL)
    @JacksonXmlProperty(localName = "tags", namespace = "aws")
    private List<AwsTag> awsTags;

    public String getName() {
        if (IaaxVariablesRegistry.SINGLETON.haveVariable(name)) {
            return IaaxVariablesRegistry.SINGLETON.getVariable(name);
        }

        return name;
    }

    public String getInstanceType() {
        if (IaaxVariablesRegistry.SINGLETON.haveVariable(instanceType)) {
            return IaaxVariablesRegistry.SINGLETON.getVariable(instanceType);
        }

        return instanceType;
    }

    public String getAmi() {
        if (IaaxVariablesRegistry.SINGLETON.haveVariable(ami)) {
            return IaaxVariablesRegistry.SINGLETON.getVariable(ami);
        }

        return ami;
    }

    public String getSubnetName() {
        if (IaaxVariablesRegistry.SINGLETON.haveVariable(subnetName)) {
            return IaaxVariablesRegistry.SINGLETON.getVariable(subnetName);
        }

        return subnetName;
    }
}
