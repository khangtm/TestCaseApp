package com.example.entity;

import org.seasar.doma.Column;
import org.seasar.doma.Entity;

@Entity
public class TestScenario {
	
	@Column(name="scenario_code")
	public String scenarioCode;
	
	@Column(name="scenario_name")
    public String scenarioName;
	
	@Column(name="scenario_type")
    public String scenarioType;
	
	@Column(name="error_message")
    public String errorMessage;
}
