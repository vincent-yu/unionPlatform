package com.yyxk.core.config;

import org.springframework.boot.context.properties.EnableConfigurationProperties;
import org.springframework.context.annotation.Configuration;

import com.yyxk.config.client.ExceptionProperties;
import com.yyxk.config.client.YyxkConfigProperties;

/**
 * 打开配置类.
 * <p>
 * 允许其它程序中可以直接注入在{@linkplain EnableConfigurationProperties}注解中配置的类<br>
 * 可直接注入的类有{@linkplain ExceptionProperties
 * 异常配置},{@linkplain YyxkConfigProperties 通用配置}
 *
 * @author Houkm
 * 
 *         2017年5月5日
 */
@Configuration
@EnableConfigurationProperties({ ExceptionProperties.class, YyxkConfigProperties.class })
public class PropertiesSwitch {
}
