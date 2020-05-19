package com.palette.s3;

import java.io.IOException;
import java.time.LocalDateTime;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import com.amazonaws.services.s3.AmazonS3;
import com.amazonaws.services.s3.model.CannedAccessControlList;
import com.amazonaws.services.s3.model.ObjectMetadata;
import com.amazonaws.services.s3.model.PutObjectRequest;
import com.amazonaws.util.IOUtils;

@Service
public class S3Uploader {

    @Autowired
    private AmazonS3 amazonS3Client;

    @Value("${cloud.aws.s3.bucket}")
    private String bucket;

    public String upload(MultipartFile uploadFile, String dirName) throws IOException {
        String uploadFileName = dirName + "/" + uploadFile.getOriginalFilename().replace(".", "-" + LocalDateTime.now() + ".");
        String url = putS3(uploadFile, uploadFileName);
        return url;
    }

    private String putS3(MultipartFile uploadFile, String uploadFileName) throws IOException {
        ObjectMetadata objMeta = new ObjectMetadata();
        byte[] bytes = IOUtils.toByteArray(uploadFile.getInputStream());
        objMeta.setContentLength(bytes.length);
        amazonS3Client.putObject(new PutObjectRequest(bucket, uploadFileName, uploadFile.getInputStream(), objMeta).withCannedAcl(CannedAccessControlList.PublicRead));
        return amazonS3Client.getUrl(bucket, uploadFileName).toString();
    }

}