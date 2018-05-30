package com.example.controller;

import java.io.File;
import java.io.IOException;
import java.util.List;
import java.util.Map;

import javax.servlet.ServletContext;

import org.apache.poi.openxml4j.exceptions.InvalidFormatException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.io.ClassPathResource;
import org.springframework.core.io.Resource;
import org.springframework.core.io.ResourceLoader;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import com.example.dao.TestCaseDAO;
import com.example.dao.TestScenarioDAO;
import com.example.entity.TestCase;
import com.example.entity.TestScenario;
import com.example.model.TestScenarioDetail;
import com.example.service.GenTestCaseService;
import com.example.util.ExcelUtil;
import com.fasterxml.jackson.core.JsonParseException;
import com.fasterxml.jackson.databind.JsonMappingException;
import com.fasterxml.jackson.databind.ObjectMapper;

@RestController
public class GenTestCaseController {

	@Autowired
	ServletContext context;
	
	/*@Autowired
	TestScenarioDAO testScenarioDAO;
	
	@Autowired
	TestCaseDAO testCaseDAO;*/
	
	@Autowired
	GenTestCaseService genTestCaseService;
	
	@Autowired
    private ResourceLoader resourceLoader;
	
	private static String INPUT_DERECTORY = "C:\\Users\\minh-khang\\Desktop\\Excel File\\input";
	private static String OUTPUT_DERECTORY = "C:\\Users\\minh-khang\\Desktop\\Excel File\\output";
	
	@RequestMapping("/api/getfiles")
	public List<File> getDesignFiles() {

		//String directory = context.getRealPath("resources/work/upload");
		String directory = INPUT_DERECTORY;
		List<File> files = ExcelUtil.getExcelFiles(directory);

		return files;
	}
	
	/*@RequestMapping("/api/testScenario")
	public List<TestScenario> getTestScenario() {

		List<TestScenario> list = testScenarioDAO.selectAll();
		return list;
	}
	
	@RequestMapping("/api/testcase")
	public List<TestCase> getTestCase() {

		List<TestCase> list = testCaseDAO.selectAll();
		return list;
	}
	*/
	
	@RequestMapping("/api/testScenariodetail")
	public String getTestScenarioDetail() {

		Map<String, TestScenarioDetail> map =  genTestCaseService.initTestScenarioDetailMap();
		return "AAAA";
	}

	@RequestMapping("/api/downloadexcelfiles")
	public String downloadExcelFiles() throws IOException, InvalidFormatException {
		
		/*List<File> files = ExcelUtil.getExcelFiles(INPUT_DERECTORY);
		for (File file : files) {
			String outputPath = OUTPUT_DERECTORY.concat("\\").concat(file.getName());
			ExcelUtil.downloadExcelFile(file.getPath(), outputPath);
		}*/
		 
		//Resource resource = new ClassPathResource("/templates/SDD-TestCase-Template.xlsx");
		Resource resource = resourceLoader.getResource("classpath:/templates/SDD-TestCase-Template.xlsx");
		File templateFile = resource.getFile();
		String outputPath = OUTPUT_DERECTORY.concat("\\").concat("SDD-001-TestCase.xlsx");
		ExcelUtil.downloadExcelFile(templateFile.getPath(), outputPath);
		
		
		return "Successful";
	}
	
	
	
	public static void main(String[] args) {
		
		//Map<String, TestScenarioDetail> map =  Repository.testScenarioDetailMap;
		//Map<String, TestScenarioDetail> map =  genTestCaseService.initTestScenarioDetailMap();
		String a = "aaa";
		System.out.println(a);
		/*ObjectMapper mapper = new ObjectMapper();
		Staff staff;
		try {
			
			Resource resource = new ClassPathResource("testcase.definition.json");
			File file = resource.getFile();
			staff = mapper.readValue(file, Staff.class);
			System.out.println(staff);
		} catch (JsonParseException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (JsonMappingException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}*/
		
	}
}
