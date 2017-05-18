package com.yyxk.common.datetime;

import java.io.IOException;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

import com.fasterxml.jackson.core.JsonGenerator;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.JsonSerializer;
import com.fasterxml.jackson.databind.SerializerProvider;

/**
 * 格式化Jackson返回{@linkplain LocalDateTime}格式.
 * 属性加注解@JsonSerialize(using=CustomLocalDateTimeSerializer.class)
 * 
 * @author Houkm
 *
 *         2017年5月5日
 */
public class CustomLocalDateTimeSerializer extends JsonSerializer<LocalDateTime> {

	@Override
	public void serialize(LocalDateTime value, JsonGenerator jgen, SerializerProvider provider)
			throws IOException, JsonProcessingException {
		DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss");
		String str = value.format(formatter);

		jgen.writeString(str);
	}

}
