package com.mgr.MgrSpringApp.reports;

import java.io.File;
import java.io.IOException;
import java.io.InputStream;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import com.mgr.MgrSpringApp.entity.Employee;
import com.mgr.MgrSpringApp.entity.Users;
import com.mgr.MgrSpringApp.mgrRepository.UserRepository;

import net.sf.jasperreports.engine.JRException;
import net.sf.jasperreports.engine.JasperCompileManager;
import net.sf.jasperreports.engine.JasperExportManager;
import net.sf.jasperreports.engine.JasperFillManager;
import net.sf.jasperreports.engine.JasperPrint;
import net.sf.jasperreports.engine.JasperReport;
import net.sf.jasperreports.engine.data.JRBeanCollectionDataSource;
import net.sf.jasperreports.engine.design.JasperDesign;
import net.sf.jasperreports.engine.export.JRPdfExporter;
import net.sf.jasperreports.engine.xml.JRXmlLoader;
import net.sf.jasperreports.export.SimpleExporterInput;
import net.sf.jasperreports.export.SimpleOutputStreamExporterOutput;
import net.sf.jasperreports.export.SimplePdfExporterConfiguration;

@RestController
public class GenerateJasperReport {
	
	@Autowired
	private UserRepository userRepository;
	
	
	@GetMapping("/report")
	public  String Report() throws JRException, IOException 
	{
		File pdfFile = File.createTempFile("ram", ".pdf");

		List<Users> allusers=userRepository.findAll();
		List<Employee> allemple=new ArrayList<>();
		
		for (Users user : allusers) {

			Employee emple=new Employee(user.getFirstName()+""+user.getLastName(), user.getEmailId(), user.getPhotoId(), new Date(), user.getId(), user.getId(), user.getId());
			allemple.add(emple);
		}
				
		
		InputStream reportInputStream = getClass().getResourceAsStream("/jasper/id_report.jrxml");
		JasperDesign jasperDesign = JRXmlLoader.load(reportInputStream);
		JasperReport jasperReport = JasperCompileManager.compileReport(jasperDesign);

		JRBeanCollectionDataSource dataSource = new JRBeanCollectionDataSource(allemple);

		// Add parameters
		Map<String, Object> parameters = new HashMap<>();
		parameters.put("createdBy", "ramachandra");

		// Fill the report
		List<JasperPrint> jasperPrintList = new ArrayList<>();

		JasperPrint jasperPrint = JasperFillManager.fillReport(jasperReport, parameters,dataSource);
		jasperPrintList.add(jasperPrint);

		//JasperExportManager.exportReportToPdfFile(jasperPrint, "/home/ramachandrareddymittapalli/Desktop/rama details/Emp-Rpt-Database.pdf");

		JasperExportManager.exportReportToHtmlFile(jasperPrint, "/home/ramachandrareddymittapalli/Desktop/rama details/Userdetailsnoid.html");

		
		return "report generate sucessfully";
	}

	
	
	
}
