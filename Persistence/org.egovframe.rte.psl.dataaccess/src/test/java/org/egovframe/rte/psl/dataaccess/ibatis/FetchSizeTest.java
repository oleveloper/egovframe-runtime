package org.egovframe.rte.psl.dataaccess.ibatis;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotNull;

import java.math.BigDecimal;
import java.util.List;

import org.egovframe.rte.psl.dataaccess.TestBase;
import org.egovframe.rte.psl.dataaccess.dao.EmpDAO;
import org.egovframe.rte.psl.dataaccess.vo.EmpVO;

import javax.annotation.Resource;

import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.core.io.ClassPathResource;
import org.springframework.jdbc.datasource.init.ScriptUtils;
import org.springframework.test.annotation.Rollback;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.transaction.annotation.Transactional;

/**
 *  == 개정이력(Modification Information) ==
 *   
 *   수정일      수정자           수정내용
 *  -------    --------    ---------------------------
 *   2014.01.22 권윤정  SimpleJdbcTestUtils -> JdbcTestUtils 변경
 *   2014.01.22 권윤정  SimpleJdbcTemplate -> JdbcTemplate 변경
 */
@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(locations = { "classpath*:META-INF/spring/context-*.xml" })
@Transactional
public class FetchSizeTest extends TestBase {

	@Resource(name = "empDAO")
	EmpDAO empDAO;

	@Before
	public void onSetUp() throws Exception {

		ScriptUtils.executeSqlScript(dataSource.getConnection(), new ClassPathResource("META-INF/testdata/sample_schema_ddl_" + usingDBMS + ".sql"));

		// init data
		ScriptUtils.executeSqlScript(dataSource.getConnection(), new ClassPathResource("META-INF/testdata/sample_schema_initdata_" + usingDBMS + ".sql"));
	}

	@Rollback(false)
	@Test
	public void testFetchSize() throws Exception {
		// 검색조건 없이 전체 EMP 리스트 조회

		// select
		List<EmpVO> resultList = empDAO.selectEmpList("selectEmpUsingFetchSize", null);

		// check
		assertNotNull(resultList);
		assertEquals(14, resultList.size());
		assertEquals(new BigDecimal(7369), resultList.get(0).getEmpNo());
		assertEquals(new BigDecimal(7499), resultList.get(1).getEmpNo());
		assertEquals(new BigDecimal(7521), resultList.get(2).getEmpNo());
		assertEquals(new BigDecimal(7566), resultList.get(3).getEmpNo());
		assertEquals(new BigDecimal(7654), resultList.get(4).getEmpNo());
		assertEquals(new BigDecimal(7698), resultList.get(5).getEmpNo());
		assertEquals(new BigDecimal(7782), resultList.get(6).getEmpNo());
		assertEquals(new BigDecimal(7788), resultList.get(7).getEmpNo());
		assertEquals(new BigDecimal(7839), resultList.get(8).getEmpNo());
		assertEquals(new BigDecimal(7844), resultList.get(9).getEmpNo());
		assertEquals(new BigDecimal(7876), resultList.get(10).getEmpNo());
		assertEquals(new BigDecimal(7900), resultList.get(11).getEmpNo());
		assertEquals(new BigDecimal(7902), resultList.get(12).getEmpNo());
		assertEquals(new BigDecimal(7934), resultList.get(13).getEmpNo());

	}
}
