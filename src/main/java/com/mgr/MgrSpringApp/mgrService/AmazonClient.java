package com.mgr.MgrSpringApp.mgrService;

import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.util.Date;

import javax.annotation.PostConstruct;

import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import com.amazonaws.auth.AWSStaticCredentialsProvider;
import com.amazonaws.auth.BasicSessionCredentials;
import com.amazonaws.auth.DefaultAWSCredentialsProviderChain;
import com.amazonaws.auth.InstanceProfileCredentialsProvider;
import com.amazonaws.client.builder.AwsClientBuilder;
import com.amazonaws.regions.Regions;
import com.amazonaws.services.s3.AmazonS3;
import com.amazonaws.services.s3.AmazonS3Client;
import com.amazonaws.services.s3.AmazonS3ClientBuilder;
import com.amazonaws.services.s3.model.CannedAccessControlList;
import com.amazonaws.services.s3.model.DeleteObjectRequest;
import com.amazonaws.services.s3.model.PutObjectRequest;
import com.amazonaws.services.securitytoken.AWSSecurityTokenService;
import com.amazonaws.services.securitytoken.model.Credentials;
import com.amazonaws.services.securitytoken.model.GetSessionTokenRequest;
import com.amazonaws.services.securitytoken.model.GetSessionTokenResult;

@Service
public class AmazonClient {

	private AmazonS3 s3client;
	private String endpointUrl = "https://mgrusersphotos.s3.ap-south-1.amazonaws.com";
	private String bucketName = "mgrusersphotos";
	private String accessKey = "AKIAQ7T6TMXKSXFQPZUF";
	private String secretKey = "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";

	@PostConstruct
	private void initializeAmazon() {
		//AWSCredentials credentials = new BasicAWSCredentials(this.accessKey, this.secretKey);
		//this.s3client = new AmazonS3Client(credentials);
		
//		AWSSecurityTokenService sts_client = AWSSecurityTokenServiceClientBuilder()
//				.standard()
//				.withEndpointConfiguration(new AwsClientBuilder.EndpointConfiguration("sts.ap-south-1.amazonaws.com	", "ap-south-1"))
//				.build();
//		
//		GetSessionTokenRequest session_token_request = new GetSessionTokenRequest();
//		session_token_request.setDurationSeconds(7200); // optional.
//
//		GetSessionTokenResult session_token_result =
//			    sts_client.getSessionToken(session_token_request);
//		
//		Credentials session_creds = session_token_result.getCredentials();
//		
//		BasicSessionCredentials sessionCredentials = new BasicSessionCredentials(
//				   session_creds.getAccessKeyId(),
//				   session_creds.getSecretAccessKey(),
//				   session_creds.getSessionToken());
//
//				this.s3client = AmazonS3ClientBuilder.standard()
//				                        .withCredentials(new AWSStaticCredentialsProvider(sessionCredentials))
//				                        .build();
		
				
				//this.s3client  = new AmazonS3Client(new DefaultAWSCredentialsProviderChain());

				
//		this.s3client =  AmazonS3ClientBuilder.standard()
//                .withCredentials(new InstanceProfileCredentialsProvider(false))
//                .build();
		//OR
//		this.s3client =AmazonS3ClientBuilder.standard().build();
		
		InstanceProfileCredentialsProvider provider = new InstanceProfileCredentialsProvider(true);
		   
		this.s3client = AmazonS3ClientBuilder.standard()
			            .withCredentials(provider)
			             .build();
		
	}

	private File convertMultiPartToFile(MultipartFile file) throws IOException {
		File convFile = new File(file.getOriginalFilename());
		FileOutputStream fos = new FileOutputStream(convFile);
		fos.write(file.getBytes());
		fos.close();
		return convFile;
	}

	private void uploadFileTos3bucket(String fileName, File file) {
		
		System.out.println("--file upload is calling---");
		s3client.putObject(
				new PutObjectRequest(bucketName, fileName, file).withCannedAcl(CannedAccessControlList.PublicRead));
	}

	private String generateFileName(MultipartFile multiPart) {
		return new Date().getTime() + "-" + multiPart.getOriginalFilename().replace(" ", "_");
	}

	public String uploadFile(MultipartFile multipartFile) {

		String fileUrl = "";
		try {
			File file = convertMultiPartToFile(multipartFile);
			String fileName = generateFileName(multipartFile);
			fileUrl = endpointUrl + "/" + fileName;
			uploadFileTos3bucket(fileName, file);
			System.out.println("--upload sucessfully--");
			file.delete();
		} catch (Exception e) {
			e.printStackTrace();
		}
		return fileUrl;
	}

	public String deleteFileFromS3Bucket(String fileUrl) {
		String fileName = fileUrl.substring(fileUrl.lastIndexOf("/") + 1);
		System.out.println("filename--" + fileName);
		s3client.deleteObject(new DeleteObjectRequest(bucketName + "/", fileName));
		return "Successfully deleted";
	}
}
