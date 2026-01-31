package com.gabrielaraujo.iaax.tags;

import com.fasterxml.jackson.dataformat.xml.annotation.JacksonXmlProperty;
import com.fasterxml.jackson.dataformat.xml.annotation.JacksonXmlRootElement;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@JacksonXmlRootElement(localName = "provider", namespace = "iaax")
public class Provider {
    @JacksonXmlProperty(localName = "source", namespace = "iaax")
    private String source;

    @JacksonXmlProperty(localName = "version", namespace = "iaax")
    private String version;
}
