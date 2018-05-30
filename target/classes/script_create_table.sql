CREATE TABLE test_scenario(
	scenario_code character varying(255) PRIMARY KEY,
	scenario_name character varying(255),
	scenario_type character varying(255),
	error_message character varying(500),
);

CREATE TABLE test_case(
	test_case_no int PRIMARY KEY,
	test_case_content character varying(255),
	pre_condition character varying(255),
	test_step character varying(255),
	test_data character varying(255),
	expect_resault character varying(255),
	test_case_type character varying(30), --normal,error
	scenario_code character varying(255)
);

ALTER TABLE test_case
ADD FOREIGN KEY (scenario_code) REFERENCES test_scenario(scenario_code);