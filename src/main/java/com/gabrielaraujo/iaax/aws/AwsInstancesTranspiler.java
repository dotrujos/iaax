package com.gabrielaraujo.iaax.aws;

import com.gabrielaraujo.iaax.aws.tags.EC2;
import lombok.AccessLevel;
import lombok.NoArgsConstructor;

import java.util.List;

@NoArgsConstructor(access = AccessLevel.PRIVATE)
public class AwsInstancesTranspiler {
    public static void transpile(StringBuilder hcl, List<EC2> vms, String alias) {
        for (EC2 ec2 : vms) {
            hcl.append("resource \"aws_instance\" \"").append(ec2.getName()).append("\" { \n")
                    .append("instance_type = \"").append(ec2.getInstanceType()).append("\"\n")
                    .append("ami = \"").append(ec2.getAmi()).append("\"\n");

            if (alias != null && !alias.isEmpty()) {
                hcl.append("provider = aws.").append(alias).append("\n\n");
            }

            if (ec2.getSubnetName() != null && !ec2.getSubnetName().isEmpty()) {
                hcl.append("subnet_id = aws_subnet.").append(ec2.getSubnetName()).append(".id");
                hcl.append("\n");
            }

            if (ec2.getTags() != null && !ec2.getTags().isEmpty()) {
                AwsTagsTranspiler.transpile(hcl, ec2.getTags());
            }

            hcl.append("}\n\n");
        }
    }
}
