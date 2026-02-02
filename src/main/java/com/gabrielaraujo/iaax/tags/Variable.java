package com.gabrielaraujo.iaax.tags;

import com.fasterxml.jackson.dataformat.xml.annotation.JacksonXmlProperty;
import com.fasterxml.jackson.dataformat.xml.annotation.JacksonXmlRootElement;
import com.fasterxml.jackson.dataformat.xml.annotation.JacksonXmlText;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@JacksonXmlRootElement(localName = "variable", namespace = "iaax")
public class Variable {
    @JacksonXmlProperty(isAttribute = true)
    private String key;

    @JacksonXmlText
    private String value;
}
