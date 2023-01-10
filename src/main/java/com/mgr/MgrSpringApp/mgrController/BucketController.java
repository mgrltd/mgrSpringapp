package com.mgr.MgrSpringApp.mgrController;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestPart;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

import com.mgr.MgrSpringApp.mgrService.AmazonClient;

@RestController
public class BucketController {

	private AmazonClient amazonClient;

	@Autowired
	BucketController(AmazonClient amazonClient) {
		this.amazonClient = amazonClient;
	}

	@PostMapping("/uploadFile")
	public String uploadFile(@RequestBody MultipartFile file) {

		System.out.println("---file upload calling-- file--" + file);

		return this.amazonClient.uploadFile(file);
	}

	@DeleteMapping("/deleteFile")
	public String deleteFile(@RequestPart(value = "url") String fileUrl) {

		System.out.println("--delete file is calling---url--" + fileUrl);

		return this.amazonClient.deleteFileFromS3Bucket(fileUrl);
	}
}