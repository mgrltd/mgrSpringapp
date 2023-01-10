//package com.mgr.MgrSpringApp.mgrService;
//
//import org.springframework.beans.factory.annotation.Autowired;
//import org.springframework.beans.factory.annotation.Value;
//import org.springframework.stereotype.Service;
//import org.springframework.web.multipart.MultipartFile;
//
//import com.mgr.MgrSpringApp.config.S3Config;
//
//@Service
//public class StorageService {
//	
//	@Value("${amazon.aws.s3.mgrs3.bucket}")
//	private String bucketName;
//	
//	@Autowired
//	private  S3Config s3Client;
//	
//	public String upload(MultipartFile file)
//	{
//	String fileName= file.getOriginalFilename();
//	s3Client.putObject ( new PutObjectRequest(bucketName,fileName,convertnormalfile(file))  ;
//	fileObj.delete();
//	return “sucessfully uploaded”;
//	}
//
//}
