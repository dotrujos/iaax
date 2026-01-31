package com.gabrielaraujo.iaax.tags;

import com.fasterxml.jackson.dataformat.xml.annotation.JacksonXmlElementWrapper;
import com.fasterxml.jackson.dataformat.xml.annotation.JacksonXmlProperty;
import com.fasterxml.jackson.dataformat.xml.annotation.JacksonXmlRootElement;
import com.gabrielaraujo.iaax.aws.tags.Vpc;
import lombok.Getter;
import lombok.Setter;

import java.util.List;

@Getter
@Setter
@JacksonXmlRootElement(localName = "infrastructure", namespace = "iaax")
public class Infrastructure<VPC extends Object> {
    @JacksonXmlProperty(localName = "vpc", namespace = "aws")
    @JacksonXmlElementWrapper(useWrapping = false)
    private List<VPC> vpcs;

    @JacksonXmlProperty(localName = "provider", namespace = "iaax")
    private Provider provider;
}
