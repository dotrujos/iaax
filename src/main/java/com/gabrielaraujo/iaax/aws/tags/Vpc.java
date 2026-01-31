package com.gabrielaraujo.iaax.aws.tags;

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
    private List<EC2> vms;
}
