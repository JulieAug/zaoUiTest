package util;

import org.springframework.jdbc.core.JdbcTemplate;

import java.util.Map;

/**
 * @author anthony
 * @date Feb 28, 2018
 * @updateTime 9:23:48 AM
 */
public class GetToken {
	
	public String getToken(JdbcTemplate jt) {
		String sql = "select token from ecs_temp_joint_login where phone ='fCOI7nE0S5n4O0Tzkjiy/Q==' order by create_time desc limit 1";
		Map<String, Object> resultMap = jt.queryForMap(sql);
		String loanNo = (String) resultMap.get("token");
		return loanNo;
	}

}
