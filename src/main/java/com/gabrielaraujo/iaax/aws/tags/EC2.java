package com.gabrielaraujo.iaax.aws.tags;

import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.dataformat.xml.annotation.JacksonXmlProperty;
import com.fasterxml.jackson.dataformat.xml.annotation.JacksonXmlRootElement;
import lombok.Getter;
import lombok.Setter;

import java.util.List;

@Getter
@Setter
@JacksonXmlRootElement(localName = "ec2", namespace = "aws")
public class EC2 {
    @JacksonXmlProperty(isAttribute = true)
    private String name;

    @JacksonXmlProperty(localName = "instance-type", namespace = "aws")
    private String instanceType;

    @JacksonXmlProperty(localName = "ami", namespace = "ami")
    private String ami;

    @JsonInclude(JsonInclude.Include.NON_NULL)
    @JacksonXmlProperty(localName = "subnet-name", namespace = "aws")
    private String subnetName;

    @JsonInclude(JsonInclude.Include.NON_NULL)
    @JacksonXmlProperty(localName = "tags", namespace = "aws")
    private List<Tag> tags;
}
