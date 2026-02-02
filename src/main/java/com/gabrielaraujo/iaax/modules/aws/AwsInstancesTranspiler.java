package com.gabrielaraujo.iaax.modules.aws;

import com.gabrielaraujo.iaax.modules.aws.tags.AwsInstance;
import lombok.AccessLevel;
import lombok.NoArgsConstructor;

import java.util.List;

@NoArgsConstructor(access = AccessLevel.PRIVATE)
public class AwsInstancesTranspiler {
    public static void transpile(StringBuilder hcl, List<AwsInstance> vms, String alias) {
        for (AwsInstance awsInstance : vms) {
            hcl.append("resource \"aws_instance\" \"").append(awsInstance.getName()).append("\" { \n")
                    .append("instance_type = \"").append(awsInstance.getInstanceType()).append("\"\n")
                    .append("ami = \"").append(awsInstance.getAmi()).append("\"\n");

            if (alias != null && !alias.isEmpty()) {
                hcl.append("provider = aws.").append(alias).append("\n\n");
            }

            if (awsInstance.getSubnetName() != null && !awsInstance.getSubnetName().isEmpty()) {
                hcl.append("subnet_id = aws_subnet.").append(awsInstance.getSubnetName()).append(".id");
                hcl.append("\n");
            }

            if (awsInstance.getAwsTags() != null && !awsInstance.getAwsTags().isEmpty()) {
                AwsTagsTranspiler.transpile(hcl, awsInstance.getAwsTags());
            }

            hcl.append("}\n\n");
        }
    }
}
