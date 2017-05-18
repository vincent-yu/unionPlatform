package com.yyxk.core.service;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

import org.apache.ibatis.session.SqlSession;
import org.apache.ibatis.session.SqlSessionFactory;
import org.mybatis.spring.SqlSessionFactoryBean;

import com.yyxk.common.Result;
import com.yyxk.common.Results;

//@Service
public class MonitorService {

	// @Autowired
	private SqlSessionFactoryBean sqlSessionFactoryBean;

	public Result<Boolean> test() {

		SqlSessionFactory sqlSessionFactory = null;
		try {
			sqlSessionFactory = sqlSessionFactoryBean.getObject();
		} catch (Exception e) {
			e.printStackTrace();
		}
		SqlSession sqlSession = sqlSessionFactory.openSession();
		java.sql.Connection conn = sqlSession.getConnection();
		PreparedStatement preparedStatement = null;
		ResultSet result = null;
		String $date = null;
		try {
			preparedStatement = conn.prepareStatement("select now()");
			result = preparedStatement.executeQuery();
			while (result.next()) {
				$date = result.getString(1);
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}
		try {
			conn.close();
		} catch (SQLException e) {
			e.printStackTrace();
		}
		if ($date != null) {
			return Results.success(true);
		} else {
			return Results.failure("");
		}

	}

}
