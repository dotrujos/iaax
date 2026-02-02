package com.gabrielaraujo.iaax.modules.aws;

import com.fasterxml.jackson.dataformat.xml.XmlMapper;
import com.gabrielaraujo.iaax.modules.aws.tags.AwsVpc;
import com.gabrielaraujo.iaax.tags.Provider;
import lombok.AccessLevel;
import lombok.NoArgsConstructor;

import java.io.File;
import java.io.IOException;
import java.time.OffsetDateTime;

@NoArgsConstructor(access = AccessLevel.PRIVATE)
public class AwsTranspiler {
    public static String transpile(File file) throws IOException {
        var xmlMapper = new XmlMapper();

        AwsInfrastructure infra = xmlMapper.readValue(file, AwsInfrastructure.class);
        Provider provider = infra.getProvider();

        var hcl = new StringBuilder();

        hcl.append("// Generated at ").append(OffsetDateTime.now()).append(" with IaaX <github.com/dotrujos/iaax>\n");

        hcl.append("terraform {\n");
        hcl.append("required_providers {\n");
        hcl.append("aws {\n");
        hcl.append("source = \"").append(provider.getSource()).append("\"\n");
        hcl.append("version = \"").append(provider.getVersion()).append("\"\n");
        hcl.append("}\n}\n}\n\n");

        for (AwsVpc awsVpc : infra.getVpcs()) {
            hcl.append("provider \"aws\" {\n")
                    .append("region = \"").append(awsVpc.getRegion()).append("\"\n");

            if (awsVpc.getAlias() != null && !awsVpc.getAlias().isEmpty()) {
                hcl.append("alias = \"").append(awsVpc.getAlias()).append("\"\n");
            }

            hcl.append("}\n\n");

            hcl.append("resource \"aws_vpc\" \"").append(awsVpc.getName()).append("\"{\n")
                    .append("cidr_block = \"").append(awsVpc.getCidr()).append("\"\n");

            if (awsVpc.getAlias() != null && !awsVpc.getAlias().isEmpty()) {
                hcl.append("provider = aws.").append(awsVpc.getAlias()).append("\n");
            }

            hcl.append("}\n\n");

            if (!awsVpc.getAwsSubnets().isEmpty()) {
                AwsSubnetsTranspiler.transpile(hcl, awsVpc);
            }

            if (awsVpc.getVms() != null && !awsVpc.getVms().isEmpty()) {
                AwsInstancesTranspiler.transpile(hcl, awsVpc.getVms(), awsVpc.getAlias());
            }
        }
        return hcl.toString();
    }
}
