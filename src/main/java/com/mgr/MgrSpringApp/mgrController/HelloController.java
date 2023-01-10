package com.mgr.MgrSpringApp.mgrController;



import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.TreeMap;
import java.util.Map.Entry;

import javax.mail.MessagingException;
import javax.persistence.Tuple;
import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.env.Environment;
import org.springframework.http.ResponseEntity;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.ModelAndView;

import com.mgr.MgrSpringApp.config.EmailConfig;
import com.mgr.MgrSpringApp.dto.EmailRequest;
import com.mgr.MgrSpringApp.dto.LoginRequest;
import com.mgr.MgrSpringApp.dto.LoginResponse;
import com.mgr.MgrSpringApp.mgrRepository.ItemsRepository;
import com.mgr.MgrSpringApp.mgrService.ApplicationService;
import com.mgr.MgrSpringApp.mgrService.HelloService;
import com.mgr.MgrSpringApp.pojo.PaytmDetailPojo;
import com.mgr.MgrSpringApp.response.ApiResponse;
import com.mgr.MgrSpringApp.response.ProductsResponse;
import com.paytm.pg.merchant.PaytmChecksum;


import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;

@CrossOrigin(origins = "http://localhost:3000/")
@RestController
@RequestMapping("/mgr/api")
public class HelloController {
    
    @Autowired
    private HelloService helloService;
    
 

    @GetMapping("/wellcome")
    public String hello()
    {
        return "hello wellcome to mgrSpringApp --swagger url: http://localhost:8008/swagger-ui.html#/";
    }

    @GetMapping("/photo/{id}")
    private ResponseEntity<byte[]> getPhoto(@PathVariable Long id)
    {
      return helloService.getPhoto(id);
    }
    
    
    @GetMapping("/getAllCategorys")
    public ApiResponse getAllCategorys()
    {
		return helloService.getAllCategorys();
    }
    
    @GetMapping("/getAllUomsByCategoryId")
    public ApiResponse getAllUomsByCategoryId(@RequestParam Long categoryId)
    {
    	return helloService.getAllUomsByCategoryId(categoryId);
    }
    
    @GetMapping("/allProducts")
    private ApiResponse allProducts(@RequestParam(defaultValue = "0") Long categoryId)
    {
    	return  helloService.allProducts(categoryId);  
    }
   
    
    

    
    
    
    
    
    
    
    
    
    
    
    
    @Autowired
    private PaytmDetailPojo paytmDetailPojo;
    @Autowired
    private Environment env;
    
    @PostMapping(value = "/submitPaymentDetail")
    public ModelAndView getRedirect(@RequestParam(name = "CUST_ID") String customerId,
                                    @RequestParam(name = "TXN_AMOUNT") String transactionAmount,
                                    @RequestParam(name = "ORDER_ID") String orderId)
                                     throws Exception {
 
        ModelAndView modelAndView = new ModelAndView("redirect:" + paytmDetailPojo.getPaytmUrl());
        TreeMap<String, String> parameters = new TreeMap<>();
        paytmDetailPojo.getDetails().forEach((k, v) -> parameters.put(k, v));
        parameters.put("MOBILE_NO", env.getProperty("paytm.mobile"));
        parameters.put("EMAIL", env.getProperty("paytm.email"));
        parameters.put("ORDER_ID", orderId);
        parameters.put("TXN_AMOUNT", transactionAmount);
        parameters.put("CUST_ID", customerId);
        String checkSum = getCheckSum(parameters);
        parameters.put("CHECKSUMHASH", checkSum);
        modelAndView.addAllObjects(parameters);
        return modelAndView;
    }

    private String getCheckSum(TreeMap<String, String> parameters) throws Exception {
        return PaytmChecksum.generateSignature(parameters, paytmDetailPojo.getMerchantKey());
        }

        @PostMapping(value = "/pgresponse")
        public String getResponseRedirect(HttpServletRequest request, Model model) {
     
            Map<String, String[]> mapData = request.getParameterMap();
            TreeMap<String, String> parameters = new TreeMap<String, String>();
            String paytmChecksum = "";
            for (Entry<String, String[]> requestParamsEntry : mapData.entrySet()) {
                if ("CHECKSUMHASH".equalsIgnoreCase(requestParamsEntry.getKey())){
                    paytmChecksum = requestParamsEntry.getValue()[0];
                } else {
                 parameters.put(requestParamsEntry.getKey(), requestParamsEntry.getValue()[0]);
                }
            }
            String result;
     
            boolean isValideChecksum = false;
            System.out.println("RESULT : "+parameters.toString());
            try {
                isValideChecksum = validateCheckSum(parameters, paytmChecksum);
                if (isValideChecksum && parameters.containsKey("RESPCODE")) {
                    if (parameters.get("RESPCODE").equals("01")) {
                        result = "Payment Successful";
                    } else {
                        result = "Payment Failed";
                    }
                } else {
                    result = "Checksum mismatched";
                }
            } catch (Exception e) {
                result = e.toString();
            }
            model.addAttribute("result",result);
            parameters.remove("CHECKSUMHASH");
            model.addAttribute("parameters",parameters);
            return "report";
        }
     
        private boolean validateCheckSum(TreeMap<String, String> parameters, String paytmChecksum) throws Exception {
            return PaytmChecksum.verifySignature(parameters,
                    paytmDetailPojo.getMerchantKey(), paytmChecksum);
        }
    
     

    


}
