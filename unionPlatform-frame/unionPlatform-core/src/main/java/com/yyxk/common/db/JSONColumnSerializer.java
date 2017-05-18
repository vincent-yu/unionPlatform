package com.yyxk.common.db;

import java.io.IOException;

import com.fasterxml.jackson.core.JsonGenerator;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.JsonSerializer;
import com.fasterxml.jackson.databind.SerializerProvider;

/**
 * 格式化Jackson返回{@linkplain JSONColumn}格式. <br>
 * 属性加注解@JsonSerialize(using={@linkplain JSONColumn}.class)
 * 
 * @author Houkm
 *
 *         2017年5月5日
 */
public class JSONColumnSerializer extends JsonSerializer<JSONColumn> {

	@Override
	public void serialize(JSONColumn value, JsonGenerator jgen, SerializerProvider provider)
			throws IOException, JsonProcessingException {
		jgen.writeObject(value.getMap());
	}

}
