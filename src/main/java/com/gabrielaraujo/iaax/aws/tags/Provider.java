package com.gabrielaraujo.iaax.aws.tags;

import com.fasterxml.jackson.dataformat.xml.annotation.JacksonXmlProperty;
import com.fasterxml.jackson.dataformat.xml.annotation.JacksonXmlRootElement;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@JacksonXmlRootElement(localName = "provider", namespace = "aws")
public class Provider {
    @JacksonXmlProperty(localName = "source", namespace = "aws")
    private String source;

    @JacksonXmlProperty(localName = "version", namespace = "aws")
    private String version;
}
