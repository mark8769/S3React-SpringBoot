package com.amigoscode.s3;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
// Auto added by IDE
// This is the interface class?
import software.amazon.awssdk.regions.Region;
import software.amazon.awssdk.services.s3.S3Client;

@Configuration
public class S3Config {
    // Have to add a configuration setting in application.yml file.
    // src/main/resources/application.yml
    // Think of aws as the key in a dictionary
    // Think of region as A value of aws. (make @value connection)
    // Think of configuration like the configurations used at Ameriprise
    // Except here, @Configuration looks for default application.yml file
    // without having to specify the location
    // aws:
    //      region: us_west_2
    //      some_other_attr: some_value
    /// access by aws.region, aws.some_other_attr
    @Value("${aws.region}")
    private String awsRegion;
    @Bean
    public S3Client Client(){
//        S3Client client = S3Client.builder()
//                .region(Region.US_WEST_2)
//                .build();

        // With this implementation, we can set out Region to something else
        // through the private string awsRegion var
        S3Client client = S3Client.builder()
                .region(Region.of(awsRegion))
                .build();
        return client;
        // For some reason the below are not needed, do some research
//                .endpointOverride(URI.create("https://s3.us-west-2.amazonaws.com"))
//                .forcePathStyle(true)
    }

}
