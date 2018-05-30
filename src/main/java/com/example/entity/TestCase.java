package com.example.entity;

import org.seasar.doma.Column;
import org.seasar.doma.Entity;

@Entity
public class TestCase {
	
	@Column(name="test_case_no")
	public int testCaseNo;
	
	@Column(name="test_case_content")
    public String testCaseContent;
	
	@Column(name="pre_condition")
    public String preCondition;
	
	@Column(name="test_step")
    public String testStep;
	
	@Column(name="test_data")
    public String testData;
	
	@Column(name="expect_resault")
    public String expectResault;
	
	@Column(name="scenario_code")
    public String scenarioCode;
	
	@Column(name="test_case_type")
    public String testCaseType;
}
