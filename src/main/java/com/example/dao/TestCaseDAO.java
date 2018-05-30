package com.example.dao;

import java.util.List;

import org.seasar.doma.Dao;
import org.seasar.doma.Insert;
import org.seasar.doma.Select;
import org.seasar.doma.boot.ConfigAutowireable;
import org.springframework.transaction.annotation.Transactional;

import com.example.entity.TestCase;

@ConfigAutowireable
@Dao
public interface TestCaseDAO {
	@Select
	List<TestCase> selectAll();

	@Insert
	@Transactional
	int insert(TestCase testCase);
}
