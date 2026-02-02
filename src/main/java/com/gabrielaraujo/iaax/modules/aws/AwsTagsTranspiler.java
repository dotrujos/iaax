package com.gabrielaraujo.iaax.modules.aws;

import com.gabrielaraujo.iaax.modules.aws.tags.AwsTag;

import java.util.List;

public class AwsTagsTranspiler {
    public static void transpile(StringBuilder hcl, List<AwsTag> awsTags) {
        hcl.append("tags = {");
        for (AwsTag awsTag : awsTags) {
            hcl.append("\n");
            hcl.append(awsTag.getKey()).append(" = \"").append(awsTag.getValue()).append("\"");
        }
        hcl.append("\n");
        hcl.append("}\n");
    }
}
