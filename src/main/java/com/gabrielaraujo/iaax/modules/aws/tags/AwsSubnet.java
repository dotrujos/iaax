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
@JacksonXmlRootElement(localName = "subnet", namespace = "aws")
public class AwsSubnet {
    @JacksonXmlProperty(isAttribute = true)
    private String name;

    @JacksonXmlProperty(isAttribute = true)
    private String cidr;

    @Getter
    @JsonInclude(JsonInclude.Include.NON_NULL)
    @JacksonXmlProperty(localName = "tag", namespace = "aws")
    @JacksonXmlElementWrapper(useWrapping = false)
    private List<AwsTag> awsTags;

    public String getName() {
        if (IaaxVariablesRegistry.SINGLETON.haveVariable(name)) {
            return IaaxVariablesRegistry.SINGLETON.getVariable(name);
        }

        return name;
    }

    public String getCidr() {
        if (IaaxVariablesRegistry.SINGLETON.haveVariable(cidr)) {
            return IaaxVariablesRegistry.SINGLETON.getVariable(cidr);
        }

        return cidr;
    }
}
