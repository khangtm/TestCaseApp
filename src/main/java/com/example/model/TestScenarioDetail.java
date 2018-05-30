package com.example.model;

import java.util.List;

import com.example.entity.TestCase;
import com.example.entity.TestScenario;;

public class TestScenarioDetail {
	
	private TestScenario testScenario;
	private List<TestCase> testcaseList;
	
	public TestScenario getTestScenario() {
		return testScenario;
	}
	public void setTestScenario(TestScenario testScenario) {
		this.testScenario = testScenario;
	}
	public List<TestCase> getTestcaseList() {
		return testcaseList;
	}
	public void setTestcaseList(List<TestCase> testcaseList) {
		this.testcaseList = testcaseList;
	}
	
}
