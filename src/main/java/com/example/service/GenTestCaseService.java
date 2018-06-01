package com.example.service;

import java.util.Map;

import com.example.model.TestScenarioDetail;

public interface GenTestCaseService {
	Map<String, TestScenarioDetail> initTestScenarioDetailMap();
	String generateTestCase();
}
