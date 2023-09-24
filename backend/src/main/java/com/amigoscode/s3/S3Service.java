package com.amigoscode.s3;

import org.springframework.stereotype.Service;
import software.amazon.awssdk.core.ResponseInputStream;
import software.amazon.awssdk.core.sync.RequestBody;
import software.amazon.awssdk.services.s3.S3Client;
import software.amazon.awssdk.services.s3.model.GetObjectRequest;
import software.amazon.awssdk.services.s3.model.PutObjectRequest;
import software.amazon.awssdk.services.s3.model.GetObjectResponse;

import java.io.IOException;

//
//
@Service
public class S3Service {
    // Method to upload and a method to downlaod objects.
    private final S3Client s3;

    public S3Service(S3Client s3Client){
        this.s3 = s3Client;
    }
    // Upload method
    // https://docs.aws.amazon.com/sdk-for-java/latest/developer-guide/examples-s3-objects.html
    public void putObject(String bucketName, String key, byte[] file){
// NOTICE:
// We have already configured the S3Client in S3Config, (no reason to create here again)
// NOTICE:
// WE SHOULD NEVER CREATE S3 BUCKETS WITH CODE (JAVA CODE, or any other)
// In these cases, an infrastructure team would already have the resources (buckets)
// available. Usually this is done with infrastructure as code solutions:
// E.g. Terraform, Ansible, Polumi, cloud formation
//        Region region = Region.US_WEST_2;
//        s3 = S3Client.builder()
//                .region(region)
//                .build();
//
//        createBucket(s3, bucketName, region);
        PutObjectRequest objectRequest = PutObjectRequest.builder()
                .bucket(bucketName)
                .key(key)
                .build();
        // https://sdk.amazonaws.com/java/api/latest/software/amazon/awssdk/core/sync/RequestBody.html
        s3.putObject(objectRequest, RequestBody.fromBytes(file));
    }

    public byte[] getObject(String bucketName, String key){
        GetObjectRequest getObjectRequest = GetObjectRequest.builder()
                .bucket(bucketName)
                .key(key)
                .build();
        // ResponseInputStream is part of the amazon SDK
        ResponseInputStream<GetObjectResponse> res = s3.getObject(getObjectRequest);

        // Read the file that was retrieved
        try {
            return res.readAllBytes();
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }



}
