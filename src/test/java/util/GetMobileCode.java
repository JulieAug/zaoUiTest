package util;

import org.springframework.jdbc.core.JdbcTemplate;

import java.util.Map;

/**
 * @author anthony
 * @date Aug 18, 2017
 * @updateTime 3:08:17 PM
 */
public class GetMobileCode {

	public String getCode(JdbcTemplate jt) {
		String sql = "select * from t_msg_captcha order by create_time desc limit 1";
		Map<String, Object> resultMap = jt.queryForMap(sql);
		String mobileCode = (String) resultMap.get("captcha");
		return mobileCode;
	}

}
