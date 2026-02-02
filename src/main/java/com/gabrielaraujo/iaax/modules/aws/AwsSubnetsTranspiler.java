package com.gabrielaraujo.iaax.modules.aws;

import com.gabrielaraujo.iaax.modules.aws.tags.Subnet;
import com.gabrielaraujo.iaax.modules.aws.tags.Vpc;
import lombok.AccessLevel;
import lombok.NoArgsConstructor;

@NoArgsConstructor(access = AccessLevel.PRIVATE)
public class AwsSubnetsTranspiler {
    public static void transpile(StringBuilder hcl, Vpc vpc) {
        for (Subnet subnet : vpc.getSubnets()) {
            hcl.append("resource \"aws_subnet\" \"").append(subnet.getName()).append("\" {\n")
                    .append("vpc_id = aws_vpc.").append(vpc.getName()).append(".id\n")
                    .append("cidr_block = \"").append(subnet.getCidr()).append("\"\n");
            if (vpc.getAlias() != null && !vpc.getAlias().isEmpty()) {
                hcl.append("provider = aws.").append(vpc.getAlias()).append("\n");
            }

            if (subnet.getTags() != null && !subnet.getTags().isEmpty()) {
                AwsTagsTranspiler.transpile(hcl, subnet.getTags());
            }

            hcl.append("}\n\n");
        }
    }
}
