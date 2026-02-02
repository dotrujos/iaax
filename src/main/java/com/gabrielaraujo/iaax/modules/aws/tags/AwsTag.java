package com.gabrielaraujo.iaax.modules.aws.tags;

import com.fasterxml.jackson.dataformat.xml.annotation.JacksonXmlProperty;
import com.fasterxml.jackson.dataformat.xml.annotation.JacksonXmlRootElement;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@JacksonXmlRootElement(localName = "tag", namespace = "aws")
public class AwsTag {
    @JacksonXmlProperty(isAttribute = true)
    private String key;

    @JacksonXmlProperty(isAttribute = true)
    private String value;
}
