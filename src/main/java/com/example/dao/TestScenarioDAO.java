package com.example.dao;

import org.seasar.doma.Dao;
import org.seasar.doma.Insert;
import org.seasar.doma.Select;
import org.seasar.doma.boot.ConfigAutowireable;
import org.springframework.transaction.annotation.Transactional;
import com.example.entity.TestScenario;
import java.util.List;

@ConfigAutowireable
@Dao
public interface TestScenarioDAO {
	@Select
	List<TestScenario> selectAll();

	@Insert
	@Transactional
	int insert(TestScenario testScenario);
}
