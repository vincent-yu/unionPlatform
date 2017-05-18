package com.yyxk.config.client;

import org.springframework.boot.context.properties.ConfigurationProperties;

/**
 * 
 * 加载yyxk.exceptions中的异常定义配置.
 * <p>
 * 在application.properties/application.yaml配置文件中配置yyxk.exceptions.props.msgId=msgValue
 * 
 * @author Houkm
 *
 *         2017年5月5日
 */
@ConfigurationProperties(prefix = "yyxk.exceptions")
public class ExceptionProperties extends AbstractProperties {

}
