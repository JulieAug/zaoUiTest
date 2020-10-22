package util;

import org.springframework.jdbc.core.JdbcTemplate;

import java.math.BigDecimal;
import java.util.Map;

/**
 * @author anthony
 * @date Feb 11, 2018
 * @updateTime 3:47:19 PM
 */
public class GetOrder {
	
	public String getOrderNo(JdbcTemplate jt) {
		String sql = "select id from t_loan order by create_time desc limit 1";
		Map<String, Object> resultMap = jt.queryForMap(sql);
		String loanNo = (String) resultMap.get("id");
		return loanNo;
	}
	
	public String getPlanId(JdbcTemplate jt) {
		String sql = "select id from t_repayment_plan order by create_time desc limit 1";
		Map<String, Object> resultMap = jt.queryForMap(sql);
		String planId = (String) resultMap.get("id");
		return planId;
	}
	
	public BigDecimal getTotalRepayMoney(JdbcTemplate jt) {
		String sql = "select amount from t_repayment_plan order by create_time desc limit 1";
		Map<String, Object> resultMap = jt.queryForMap(sql);
		BigDecimal totalReapyMoney = (BigDecimal) resultMap.get("amount");
		return totalReapyMoney;
	}
	
}
