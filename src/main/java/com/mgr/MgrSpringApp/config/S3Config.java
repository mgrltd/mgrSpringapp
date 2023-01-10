//package com.mgr.MgrSpringApp.config;
//
//import org.springframework.beans.factory.annotation.Value;
//import org.springframework.context.annotation.Bean;
//import org.springframework.context.annotation.Configuration;
//
//import com.amazonaws.auth.AWSStaticCredentialsProvider;
//import com.amazonaws.auth.BasicAWSCredentials;
//import com.amazonaws.services.s3.AmazonS3;
//import com.amazonaws.services.s3.AmazonS3Client;
//import com.amazonaws.services.s3.AmazonS3ClientBuilder;
//
//@Configuration
//public class S3Config 
//{
//	//@Value("${amazon.aws.s3.mgrs3.bucket}")
////	private String accesskey;
////
//	//@Value("${amazon.aws.s3.mgrs3.bucket}")
////	private String accessSecretkey;
////
//	//@Value("${amazon.aws.s3.mgrs3.bucket}")
////	private String region;
//	
//	private String region ="ap-south-1";
//	
//	@Bean
//	public AmazonS3 s3Client() {    	
//        BasicAWSCredentials credentials = new BasicAWSCredentials("", "");
//        return AmazonS3ClientBuilder.standard()
//        		.withCredentials( new AWSStaticCredentialsProvider( credentials))
//        		.withRegion( region).build();
//    }
//    
//
//
//}
