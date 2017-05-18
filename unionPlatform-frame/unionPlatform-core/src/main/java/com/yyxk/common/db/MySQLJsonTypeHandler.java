package com.yyxk.common.db;

import java.io.UnsupportedEncodingException;
import java.sql.CallableStatement;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

import org.apache.ibatis.type.BaseTypeHandler;
import org.apache.ibatis.type.JdbcType;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONObject;

/**
 * Mybatis中Json类型字段{@linkplain JSONColumn}的解析类.
 * 
 * @author Houkm
 *
 *         2017年5月5日
 */
public class MySQLJsonTypeHandler extends BaseTypeHandler<JSONColumn> {

	@Override
	public void setNonNullParameter(PreparedStatement ps, int i, JSONColumn parameter, JdbcType jdbcType)
			throws SQLException {
		ps.setString(i, JSON.toJSONString(parameter.map));

	}

	@Override
	public JSONColumn getNullableResult(ResultSet rs, String columnName) throws SQLException {
		return getJSONColumn(rs.getString(columnName));
	}

	@Override
	public JSONColumn getNullableResult(ResultSet rs, int columnIndex) throws SQLException {
		return getJSONColumn(rs.getString(columnIndex));
	}

	@Override
	public JSONColumn getNullableResult(CallableStatement cs, int columnIndex) throws SQLException {
		return getJSONColumn(cs.getString(columnIndex));
	}

	private static JSONColumn getJSONColumn(String json) {
		JSONObject map = null;
		try {

			map = JSON.parseObject(new String(json.getBytes("ISO8859-1"), "UTF-8"), JSONObject.class);
		} catch (UnsupportedEncodingException e) {
			e.printStackTrace();
		}
		JSONColumn jsonColumn = new JSONColumn();
		jsonColumn.map = map;
		return jsonColumn;
	}

}
