package com.gabrielaraujo.iaax.modules.aws;

import com.gabrielaraujo.iaax.modules.aws.tags.AwsSubnet;
import com.gabrielaraujo.iaax.modules.aws.tags.AwsVpc;
import lombok.AccessLevel;
import lombok.NoArgsConstructor;

@NoArgsConstructor(access = AccessLevel.PRIVATE)
public class AwsSubnetsTranspiler {
    public static void transpile(StringBuilder hcl, AwsVpc awsVpc) {
        for (AwsSubnet awsSubnet : awsVpc.getAwsSubnets()) {
            hcl.append("resource \"aws_subnet\" \"").append(awsSubnet.getName()).append("\" {\n")
                    .append("vpc_id = aws_vpc.").append(awsVpc.getName()).append(".id\n")
                    .append("cidr_block = \"").append(awsSubnet.getCidr()).append("\"\n");
            if (awsVpc.getAlias() != null && !awsVpc.getAlias().isEmpty()) {
                hcl.append("provider = aws.").append(awsVpc.getAlias()).append("\n");
            }

            if (awsSubnet.getAwsTags() != null && !awsSubnet.getAwsTags().isEmpty()) {
                AwsTagsTranspiler.transpile(hcl, awsSubnet.getAwsTags());
            }

            hcl.append("}\n\n");
        }
    }
}
