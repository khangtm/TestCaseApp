package com.example.service;

import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

import org.apache.poi.ss.usermodel.Cell;
import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.ss.usermodel.Sheet;
import org.apache.poi.ss.usermodel.Workbook;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.io.Resource;
import org.springframework.core.io.ResourceLoader;
import org.springframework.stereotype.Service;
import org.springframework.util.StringUtils;

import com.example.constant.CommonConstants;
import com.example.dao.TestCaseDAO;
import com.example.dao.TestScenarioDAO;
import com.example.entity.TestCase;
import com.example.entity.TestScenario;
import com.example.model.TestScenarioDetail;
import com.example.model.ValidationInfo;
import com.example.util.ExcelUtil;
import com.example.util.FileIOUtils;

@Service
public class GenTestCaseServiceImpl implements GenTestCaseService {

	@Autowired
	private ResourceLoader resourceLoader;

	@Autowired
	TestScenarioDAO testScenarioDAO;

	@Autowired
	TestCaseDAO testCaseDAO;

	private Map<String, TestScenarioDetail> testScenarioDetailMap;

	public GenTestCaseServiceImpl() {
		// init();
	}

	private void initData() {
		testScenarioDetailMap = new HashMap<String, TestScenarioDetail>();
		List<TestScenario> testScenarioList = testScenarioDAO.selectAll();
		List<TestCase> testCaseList = testCaseDAO.selectAll();
		for (TestScenario testScenario : testScenarioList) {
			TestScenarioDetail testScenarioDetail = new TestScenarioDetail();
			testScenarioDetail.setTestScenario(testScenario);
			List<TestCase> testCaseFilterList = testCaseList.stream()
					.filter(testCase -> testScenario.scenarioCode.equals(testCase.scenarioCode))
					.collect(Collectors.toList());
			testScenarioDetail.setTestcaseList(testCaseFilterList);
			testScenarioDetailMap.put(testScenario.scenarioCode, testScenarioDetail);
		}
	}

	private boolean genrateTestCaseProcess(Sheet sheet, List<ValidationInfo> validationInfoList) {

		int COLUMN_TYPE_INDEX = 0;
		int COLUMN_NO_INDEX = 1;
		int COLUMN_TEST_SCENARIO_INDEX = 2;
		int COLUMN_TEST_CASE_INDEX = 3;
		int COLUMN_PRE_CONDITION_INDEX = 4;
		int COLUMN_TEST_STEPS_INDEX = 5;
		int COLUMN_TEST_DATA_INDEX = 6;
		int COLUMN_EXPECT_RESULT_INDEX = 7;
		//int COLUMN_TEST_RESULT = 8;
		int COLUMN_REMARK_INDEX = 13;
		int ROW_START_INDEX = 18;
		String REGEX_REPLACE = "<item>";

		// declare variable
		Cell cell = null;
		String cellValue = "";
		
		
		int rowIndex = ROW_START_INDEX;
		for (ValidationInfo validationInfo : validationInfoList) {
			
			String checkType = validationInfo.getCheckType();
			List<TestCase> testcaseList = testScenarioDetailMap.get(checkType).getTestcaseList();
			
			for (int i = 0; i < testcaseList.size(); i++) {

				TestScenarioDetail testScenarioDetail = testScenarioDetailMap.get(checkType);
				Row row = sheet.getRow(rowIndex);

				// Type
				cellValue = testScenarioDetail.getTestScenario().scenarioType;
				ExcelUtil.setCellValue(row, cell, COLUMN_TYPE_INDEX, cellValue);

				// No
				cellValue = String.valueOf(rowIndex - ROW_START_INDEX + 1);
				ExcelUtil.setCellValue(row, cell, COLUMN_NO_INDEX, cellValue);

				// Test_scenario
				cellValue = testScenarioDetail.getTestScenario().scenarioName.replaceAll(REGEX_REPLACE, validationInfo.getItemName());
				ExcelUtil.setCellValue(row, cell, COLUMN_TEST_SCENARIO_INDEX, cellValue);

				// Test_case
				cellValue = testScenarioDetail.getTestcaseList().get(i).testCaseContent.replaceAll(REGEX_REPLACE, validationInfo.getItemName());
				ExcelUtil.setCellValue(row, cell, COLUMN_TEST_CASE_INDEX, cellValue);

				// Pre-condition
				cellValue = testScenarioDetail.getTestcaseList().get(i).preCondition;
				ExcelUtil.setCellValue(row, cell, COLUMN_PRE_CONDITION_INDEX, cellValue);

				// Test_steps
				cellValue = testScenarioDetail.getTestcaseList().get(i).testStep;
				ExcelUtil.setCellValue(row, cell, COLUMN_TEST_STEPS_INDEX, cellValue);

				// Test_data
				cellValue = testScenarioDetail.getTestcaseList().get(i).testData;
				ExcelUtil.setCellValue(row, cell, COLUMN_TEST_DATA_INDEX, cellValue);

				// Expect_result
				cellValue = testScenarioDetail.getTestcaseList().get(i).expectResault;
				ExcelUtil.setCellValue(row, cell, COLUMN_EXPECT_RESULT_INDEX, cellValue);

				// Remark
				cellValue = "";
				ExcelUtil.setCellValue(row, cell, COLUMN_REMARK_INDEX, cellValue);

				rowIndex++;
			} // end loop test case
		} // end loop 
		
		return true;
	}

	private List<ValidationInfo> readDesignDocumentExcelFile(File file) {

		int ROW_START_INDEX = 13;
		int COLUMN_NO_INDEX = 1;
		int COLUMN_ITEM_CHECK_INDEX = 2;
		int COLUMN_CHECK_TYPE_INDEX = 12;
		int COLUMN_REFERENCE_ITEM_INDEX = 17;
		int COLUMN_MIN_INDEX = 22;
		int COLUMN_MAX_INDEX = 25;
		int COLUMN_ERROR_MESSAGE_INDEX = 28;

		List<ValidationInfo> validationInfoList = new ArrayList<>();
		try {
			Workbook workbook = ExcelUtil.readExcelFile(file);
			Sheet sheet = workbook.getSheetAt(6);
			Iterator<Row> iterator = sheet.iterator();
			String cellValue = "";

			while (iterator.hasNext()) {
				Row nextRow = iterator.next();
				Iterator<Cell> cellIterator = nextRow.cellIterator();
				ValidationInfo validationInfo = new ValidationInfo();
				while (cellIterator.hasNext()) {
					Cell cell = cellIterator.next();
					if (cell.getRowIndex() < ROW_START_INDEX) {
						continue;
					}

					if (COLUMN_NO_INDEX == cell.getColumnIndex()) {
						cellValue = ExcelUtil.getCellValue(cell);
						if (StringUtils.hasLength(cellValue)) {
							validationInfo.setItemNo(Integer.parseInt(cellValue));
						}
					}

					if (COLUMN_ITEM_CHECK_INDEX == cell.getColumnIndex()) {
						cellValue = ExcelUtil.getCellValue(cell);
						validationInfo.setItemName(cellValue);
					}

					if (COLUMN_CHECK_TYPE_INDEX == cell.getColumnIndex()) {
						cellValue = ExcelUtil.getCellValue(cell);
						validationInfo.setCheckType(cellValue);
					}

					if (COLUMN_REFERENCE_ITEM_INDEX == cell.getColumnIndex()) {
						cellValue = ExcelUtil.getCellValue(cell);
						validationInfo.setReferenceItem(cellValue);
					}

					if (COLUMN_MIN_INDEX == cell.getColumnIndex()) {
						cellValue = ExcelUtil.getCellValue(cell);
						if (StringUtils.hasLength(cellValue)) {
							validationInfo.setMin(Integer.parseInt(cellValue));
						}

					}

					if (COLUMN_MAX_INDEX == cell.getColumnIndex()) {
						cellValue = ExcelUtil.getCellValue(cell);
						if (StringUtils.hasLength(cellValue)) {
							validationInfo.setMax(Integer.parseInt(cellValue));
						}
					}

					if (COLUMN_ERROR_MESSAGE_INDEX == cell.getColumnIndex()) {
						cellValue = ExcelUtil.getCellValue(cell);
						validationInfo.setErrorMessage(cellValue);
					}

				} // end loop cell
				
				if (StringUtils.hasLength(validationInfo.getItemName())
						&& StringUtils.hasLength(validationInfo.getCheckType())) {
					validationInfoList.add(validationInfo);
				}
			} // end loop row

		} catch (IOException e) {
			e.printStackTrace();
		}
		return validationInfoList;
	}

	@Override
	public Map<String, TestScenarioDetail> initTestScenarioDetailMap() {

		Map<String, TestScenarioDetail> testScenarioDetailMap = new HashMap<String, TestScenarioDetail>();
		List<TestScenario> testScenarioList = testScenarioDAO.selectAll();
		List<TestCase> testCaseList = testCaseDAO.selectAll();
		TestScenarioDetail testScenarioDetail = new TestScenarioDetail();
		for (TestScenario testScenario : testScenarioList) {
			testScenarioDetail.setTestScenario(testScenario);
			List<TestCase> testCaseFilterList = testCaseList.stream()
					.filter(testCase -> testScenario.scenarioCode.equals(testCase.scenarioCode))
					.collect(Collectors.toList());
			testScenarioDetail.setTestcaseList(testCaseFilterList);
			testScenarioDetailMap.put(testScenario.scenarioCode, testScenarioDetail);
		}

		return testScenarioDetailMap;
	}

	@Override
	public String generateTestCase() {

		// declare variable
		String OUTPUT_DIRECTORY = "C:\\Users\\minh-khang\\Desktop\\Excel File\\output";
		String INPUT_DIRECTORY = "C:\\Users\\minh-khang\\Desktop\\Excel File\\input";
		String outputFileName = "";

		Workbook workbook = null;
		Sheet sheet = null;
		try {

			// 1. Get design document files
			List<File> files = ExcelUtil.getExcelFiles(INPUT_DIRECTORY);

			if (files.size() > 0) {

				// 2. Initial data
				initData();

				// 3. Get test case template excel file from system
				Resource resource = resourceLoader.getResource("classpath:/templates/SDD-TestCase-Template.xlsx");
				File templateFile = resource.getFile();
				String templateFilePath = templateFile.getPath();

				for (File inputFile : files) {

					// 4. Read excel file
					List<ValidationInfo> validationInfoList = readDesignDocumentExcelFile(inputFile);

					// 4. Get test case template excel file from system
					workbook = ExcelUtil.readExcelFile(templateFilePath);

					// 5. process generate test case
					if (workbook != null) {
						sheet = workbook.getSheetAt(2);
						genrateTestCaseProcess(sheet, validationInfoList);
					}

					// 6. write excel file
					outputFileName = inputFile.getName().replace(".xlsx", "-TestCase.xlsx");
					ExcelUtil.writeExcelFile(workbook, OUTPUT_DIRECTORY, outputFileName);
				}
			}
		} catch (IOException e) {
			e.printStackTrace();
		}

		return "successful";
	}

}
