package com.gabrielaraujo.iaax.aws;

import com.fasterxml.jackson.dataformat.xml.XmlMapper;
import com.gabrielaraujo.iaax.aws.tags.*;
import lombok.AccessLevel;
import lombok.NoArgsConstructor;

import java.io.File;
import java.io.IOException;
import java.time.OffsetDateTime;

@NoArgsConstructor(access = AccessLevel.PRIVATE)
public class AwsTranspiler {
    public static String transpile(String filePath) throws IOException {
        var xmlMapper = new XmlMapper();
        var file = new File(filePath);

        Infrastructure infra = xmlMapper.readValue(file, Infrastructure.class);
        Provider provider = infra.getProvider();

        var hcl = new StringBuilder();

        hcl.append("// Generated at ").append(OffsetDateTime.now()).append(" with IaaX <github.com/dotrujos/iaax>\n");

        hcl.append("terraform {\n");
        hcl.append("required_providers {\n");
        hcl.append("aws {\n");
        hcl.append("source = \"").append(provider.getSource()).append("\"\n");
        hcl.append("version = \"").append(provider.getVersion()).append("\"\n");
        hcl.append("}\n}\n}\n\n");

        for (Vpc vpc : infra.getVpcs()) {
            hcl.append("provider \"aws\" {\n")
                    .append("  region = \"").append(vpc.getRegion()).append("\"\n")
                    .append("}\n\n");

            hcl.append("resource \"aws_vpc\" \"").append(vpc.getName()).append("\"{\n")
                    .append("  cidr_block = \"").append(vpc.getCidr()).append("\"\n")
                    .append("}\n\n");

            if (!vpc.getSubnets().isEmpty()) {
                for (Subnet subnet : vpc.getSubnets()) {
                    hcl.append("resource \"aws_subnet\" \"").append(subnet.getName()).append("\" {\n")
                            .append("vpc_id = aws_vpc.").append(vpc.getName()).append(".id\n")
                            .append("cidr_block = \"").append(subnet.getCidr()).append("\"\n");

                    if (subnet.getTags() != null && !subnet.getTags().isEmpty()) {
                        hcl.append("tags = {");
                        for (Tag tag : subnet.getTags()) {
                            hcl.append("\n");
                            hcl.append(tag.getKey()).append(" = \"").append(tag.getValue()).append("\"");
                        }
                        hcl.append("\n");
                        hcl.append("}\n");
                    }

                    hcl.append("}\n\n");
                }
            }

            if (vpc.getVms() != null && !vpc.getVms().isEmpty()) {
                for (EC2 ec2 : vpc.getVms()) {
                    hcl.append("resource \"aws_instance\" \"").append(ec2.getName()).append("\" { \n")
                            .append("instance_type = \"").append(ec2.getInstanceType()).append("\"\n")
                            .append("ami = \"").append(ec2.getAmi()).append("\"\n\n");

                    if (ec2.getSubnetName() != null && !ec2.getSubnetName().isEmpty()) {
                        hcl.append("subnet_id = aws_subnet.").append(ec2.getSubnetName()).append(".id");
                        hcl.append("\n");
                    }

                    if (ec2.getTags() != null && !ec2.getTags().isEmpty()) {
                        hcl.append("tags = {");
                        for (Tag tag : ec2.getTags()) {
                            hcl.append("\n");
                            hcl.append(tag.getKey()).append(" = \"").append(tag.getValue()).append("\"");
                        }
                        hcl.append("\n");
                        hcl.append("}\n");
                    }

                    hcl.append("}\n\n");
                }
            }
        }
        return hcl.toString();
    }
}
