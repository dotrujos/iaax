package com.gabrielaraujo.iaax.modules.aws.tags;

import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.dataformat.xml.annotation.JacksonXmlElementWrapper;
import com.fasterxml.jackson.dataformat.xml.annotation.JacksonXmlProperty;
import com.fasterxml.jackson.dataformat.xml.annotation.JacksonXmlRootElement;
import lombok.Getter;
import lombok.Setter;

import java.util.List;

@Getter
@Setter
@JacksonXmlRootElement(localName = "vpc", namespace = "aws")
public class Vpc {
    @JacksonXmlProperty(localName = "ec2", namespace = "aws")
    @JacksonXmlElementWrapper(useWrapping = false)
    private List<EC2> vms;

    @JacksonXmlProperty(localName = "subnets", namespace = "aws")
    private List<Subnet> subnets;

    @JacksonXmlProperty(isAttribute = true)
    private String cidr;

    @JacksonXmlProperty(isAttribute = true)
    private String region;

    @JacksonXmlProperty(isAttribute = true)
    private String name;

    @JsonInclude(JsonInclude.Include.NON_NULL)
    @JacksonXmlProperty(isAttribute = true)
    private String alias;
}
