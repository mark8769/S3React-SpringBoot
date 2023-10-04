package com.amigoscode.s3;

import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.context.annotation.Configuration;

@Configuration
// From src/main/resources/application.yml file. This is the tree to get down to our bucket name field
@ConfigurationProperties(prefix="aws.s3.buckets")
public class S3Buckets {
    // aws.s3.buckets.customer
    private String customer;

    // NO NEED FOR CONSTRUCTOR HERE

    public String getCustomer(){
        return this.customer;
    }
    public void setCustomer(String customer){
        this.customer = customer;
    }
}
