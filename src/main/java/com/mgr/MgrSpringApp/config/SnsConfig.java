//package com.mgr.MgrSpringApp.config;
//
//import org.springframework.context.annotation.Bean;
//import org.springframework.context.annotation.Primary;
//
//import com.amazonaws.auth.AWSStaticCredentialsProvider;
//import com.amazonaws.auth.BasicAWSCredentials;
//import com.amazonaws.services.sns.AmazonSNSClient;
//import com.amazonaws.services.sns.AmazonSNSClientBuilder;
//
//public class SnsConfig {
//	
//	@Bean
//	@Primary
//	public AmazonSNSClient amazonSNSClient()
//	{
//		
//		BasicAWSCredentials basicAWSCredentials=new BasicAWSCredentials("AKIAQ7T6TMXK3WMGZX46", "EM2ylB2+QIIxvzzDDc83KiVYSRnxi+L0PYW34Tu+");
//		
//		return (AmazonSNSClient) AmazonSNSClientBuilder
//				.standard()
//				.withRegion("arn:aws:sns:ap-south-1:067911247317:mgrsns")
//				.withCredentials(new AWSStaticCredentialsProvider(basicAWSCredentials))
//				.build();
//		
//	}
//
//}
