// Generated at 2026-02-09T15:03:58.105126400-03:00 with IaaX <github.com/dotrujos/iaax>
terraform {
required_providers {
aws {
source = "hashicorp/aws"
version = "~> 5.0"
}
}
}

provider "aws" {
region = "us-east-1"
alias = "us_east"
}

resource "aws_vpc" "vpc_prd"{
cidr_block = "10.0.0.0/16"
provider = aws.us_east
}

resource "aws_subnet" "public_subnet" {
vpc_id = aws_vpc.vpc_prd.id
cidr_block = "10.0.1.0/24"
provider = aws.us_east
}

resource "aws_subnet" "private_subnet" {
vpc_id = aws_vpc.vpc_prd.id
cidr_block = "10.0.10.0/24"
provider = aws.us_east
tags = {
Name = "Private-Subnet-1A"
}
}

resource "aws_instance" "web_server_01" { 
instance_type = "t3.medium"
ami = "ami-04b70fa74e45c3917"
provider = aws.us_east

subnet_id = aws_subnet.public_subnet.id
tags = {
Name = "MyWebServer_1"
Environment = "Dev"
}
}

resource "aws_instance" "web_server_02" { 
instance_type = "t3.medium"
ami = "ami-04b70fa74e45c3917"
provider = aws.us_east

subnet_id = aws_subnet.public_subnet.id
tags = {
Name = "MyWebServer_2"
Environment = "Dev"
}
}

resource "aws_instance" "db_server" { 
instance_type = "t3.large"
ami = "ami-04b70fa74e45c3917"
provider = aws.us_east

subnet_id = aws_subnet.private_subnet.id
tags = {
Name = "MyDatabase"
Environment = "Dev"
}
}

