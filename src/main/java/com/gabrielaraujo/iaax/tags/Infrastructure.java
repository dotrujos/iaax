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
@JacksonXmlRootElement(localName = "infrastructure", namespace = "aws")
public class Infrastructure {
    @JacksonXmlProperty(localName = "vpc", namespace = "aws")
    @JacksonXmlElementWrapper(useWrapping = false)
    private List<Vpc> vpcs;

    @JacksonXmlProperty(localName = "provider", namespace = "aws")
    private Provider provider;
}
