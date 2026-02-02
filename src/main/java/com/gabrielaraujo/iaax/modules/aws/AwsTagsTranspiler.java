package com.gabrielaraujo.iaax.modules.aws;

import com.gabrielaraujo.iaax.modules.aws.tags.Tag;

import java.util.List;

public class AwsTagsTranspiler {
    public static void transpile(StringBuilder hcl, List<Tag> tags) {
        hcl.append("tags = {");
        for (Tag tag : tags) {
            hcl.append("\n");
            hcl.append(tag.getKey()).append(" = \"").append(tag.getValue()).append("\"");
        }
        hcl.append("\n");
        hcl.append("}\n");
    }
}
