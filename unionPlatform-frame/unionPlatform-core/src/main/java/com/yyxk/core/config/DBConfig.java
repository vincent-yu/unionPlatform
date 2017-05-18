package com.yyxk.core.config;

import java.util.ArrayList;
import java.util.List;

import javax.sql.DataSource;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.boot.autoconfigure.jdbc.DataSourceProperties;
import org.springframework.boot.context.properties.EnableConfigurationProperties;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Primary;
import org.springframework.jdbc.datasource.DataSourceTransactionManager;
import org.springframework.transaction.PlatformTransactionManager;
import org.springframework.transaction.annotation.EnableTransactionManagement;

import com.alibaba.druid.filter.Filter;
import com.alibaba.druid.filter.logging.Slf4jLogFilter;
import com.alibaba.druid.pool.DruidDataSource;

/**
 * 数据源配置.
 * 
 * @author Houkm
 *
 *         2017年5月5日
 */
@Configuration
@EnableConfigurationProperties({ DataSourceProperties.class })
@EnableTransactionManagement
public class DBConfig {

	private Logger logger = LoggerFactory.getLogger(DBConfig.class);

	/**
	 * 配置Druid数据源.
	 * 
	 * @param prop
	 *            数据源配置属性
	 * @return DruidDataSource
	 * @author Houkm
	 */
	@Bean
	@Primary
	public DruidDataSource dataSource(DataSourceProperties prop) {
		logger.info("配置DruidDataSource：url({}), username({})", prop.determineUrl(), prop.determineUsername());
		DruidDataSource datasource = new DruidDataSource();
		datasource.setDriverClassName(prop.determineDriverClassName());
		datasource.setUrl(prop.determineUrl());
		datasource.setUsername(prop.determineUsername());
		datasource.setPassword(prop.determinePassword());

		// 过添加滤器
		Slf4jLogFilter logFilter = new Slf4jLogFilter();
		logger.info("配置DruidDataSource logFilter: statementLogEnabled=false");
		logFilter.setStatementLogEnabled(false);
		logger.info("配置DruidDataSource logFilter: statementLogErrorEnabled=true");
		logFilter.setStatementLogErrorEnabled(true);
		logger.info("配置DruidDataSource logFilter: statementExecutableSqlLogEnable=true");
		logFilter.setStatementExecutableSqlLogEnable(true);
		List<Filter> filters = new ArrayList<>();
		filters.add(logFilter);
		datasource.setProxyFilters(filters);
		logger.info("DruidDataSource配置完毕");
		return datasource;
	}

	/**
	 * 配置事务管理器.
	 * 
	 * @param dataSource
	 *            数据源
	 * @return PlatformTransactionManager
	 * @author Houkm
	 */
	@Bean
	public PlatformTransactionManager transactionManager(DataSource dataSource) {
		logger.info("为数据源{}配置事务管理器DataSourceTransactionManager", dataSource.getClass().getName());
		DataSourceTransactionManager transactionManager = new DataSourceTransactionManager();
		transactionManager.setDataSource(dataSource);
		logger.info("配置事务管理器DataSourceTransactionManager完毕");
		return transactionManager;
	}
}
