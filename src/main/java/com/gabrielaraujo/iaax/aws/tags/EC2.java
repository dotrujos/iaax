package com.gabrielaraujo.iaax.aws.tags;

import com.fasterxml.jackson.dataformat.xml.annotation.JacksonXmlProperty;
import com.fasterxml.jackson.dataformat.xml.annotation.JacksonXmlRootElement;

@JacksonXmlRootElement(localName = "ec2", namespace = "aws")
public class EC2 {
    @JacksonXmlProperty(localName = "ram", namespace = "aws")
    private String ram;

    @JacksonXmlProperty(localName = "cpu", namespace = "aws")
    private String cpu;
}
