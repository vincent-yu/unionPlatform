package com.yyxk.common.datetime;

import java.io.IOException;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;

import com.fasterxml.jackson.core.JsonGenerator;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.JsonSerializer;
import com.fasterxml.jackson.databind.SerializerProvider;

/**
 * 格式化Jackson返回{@linkplain LocalDate}格式.
 * 属性加注解@JsonSerialize(using=CustomLocalDateSerializer.class)
 * 
 * @author Houkm
 *
 *         2017年5月5日
 */
public class CustomLocalDateSerializer extends JsonSerializer<LocalDate> {

	@Override
	public void serialize(LocalDate value, JsonGenerator jgen, SerializerProvider provider)
			throws IOException, JsonProcessingException {
		DateTimeFormatter formatter = DateTimeFormatter.ofPattern("uuuu-MM-dd");
		String str = value.format(formatter);
		jgen.writeString(str);
	}

}
