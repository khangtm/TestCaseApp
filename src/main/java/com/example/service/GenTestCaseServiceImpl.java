package com.example.service;

import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.example.dao.TestCaseDAO;
import com.example.dao.TestScenarioDAO;
import com.example.entity.TestCase;
import com.example.entity.TestScenario;
import com.example.model.TestScenarioDetail;

@Service
public class GenTestCaseServiceImpl implements GenTestCaseService {

	@Autowired
	TestScenarioDAO testScenarioDAO;

	@Autowired
	TestCaseDAO testCaseDAO;

	@Override
	public Map<String, TestScenarioDetail> initTestScenarioDetailMap() {
		// TODO Auto-generated method stub
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

}
