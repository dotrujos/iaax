package com.gabrielaraujo.iaax.tags;

import com.fasterxml.jackson.dataformat.xml.annotation.JacksonXmlElementWrapper;
import com.fasterxml.jackson.dataformat.xml.annotation.JacksonXmlProperty;
import com.fasterxml.jackson.dataformat.xml.annotation.JacksonXmlRootElement;
import lombok.Getter;
import lombok.Setter;

import java.util.List;

@Getter
@Setter
@JacksonXmlRootElement(localName = "environment", namespace = "iaax")
public class Environment {
    @JacksonXmlElementWrapper(useWrapping = false)
    @JacksonXmlProperty(localName = "variable", namespace = "iaax")
    private List<Variable> variables;
}
