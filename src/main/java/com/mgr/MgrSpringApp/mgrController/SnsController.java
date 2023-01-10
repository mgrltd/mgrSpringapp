//package com.mgr.MgrSpringApp.mgrController;
//
//import org.springframework.beans.factory.annotation.Autowired;
//import org.springframework.web.bind.annotation.GetMapping;
//import org.springframework.web.bind.annotation.PathVariable;
//import org.springframework.web.bind.annotation.RestController;
//
//import com.amazonaws.services.sns.AmazonSNSClient;
//import com.amazonaws.services.sns.model.SubscribeRequest;
//
//@RestController 
//public class SnsController {
//		
//	//@Autowired
//	private AmazonSNSClient amazonSNSClient;
//	
//	@GetMapping("/subscribeNumber/{phoneNumber}")
//	public String subscribe(@PathVariable String phoneNumber)
//	{        
//		System.out.println("---subscribe is calling phoneNumber--"+phoneNumber);
//		SubscribeRequest subscribeRequest=new SubscribeRequest("arn:aws:sns:ap-south-1:067911247317:mgrsns","SMS",phoneNumber);
//		amazonSNSClient.subscribe(subscribeRequest);
//		
//		return "sucessfully subcrebed"+phoneNumber;
//		
//	}
//
//}
