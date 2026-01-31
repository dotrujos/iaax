package com.gabrielaraujo.iaax.aws;

import com.fasterxml.jackson.dataformat.xml.XmlMapper;
import com.gabrielaraujo.iaax.aws.tags.*;
import com.gabrielaraujo.iaax.tags.Infrastructure;
import com.gabrielaraujo.iaax.tags.Provider;
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
                    .append("region = \"").append(vpc.getRegion()).append("\"\n");

            if (vpc.getAlias() != null && !vpc.getAlias().isEmpty()) {
                hcl.append("alias = \"").append(vpc.getAlias()).append("\"\n");
            }

            hcl.append("}\n\n");

            hcl.append("resource \"aws_vpc\" \"").append(vpc.getName()).append("\"{\n")
                    .append("cidr_block = \"").append(vpc.getCidr()).append("\"\n");

            if (vpc.getAlias() != null && !vpc.getAlias().isEmpty()) {
                hcl.append("provider = aws.").append(vpc.getAlias()).append("\n");
            }

            hcl.append("}\n\n");

            if (!vpc.getSubnets().isEmpty()) {
                AwsSubnetsTranspiler.transpile(hcl, vpc);
            }

            if (vpc.getVms() != null && !vpc.getVms().isEmpty()) {
                AwsInstancesTranspiler.transpile(hcl, vpc.getVms(), vpc.getAlias());
            }
        }
        return hcl.toString();
    }
}
