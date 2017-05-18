package com.yyxk.common.json;

import java.io.IOException;

import com.fasterxml.jackson.databind.ObjectMapper;

public class JsonUtil {
	public static <T> T toObj(byte[] bts, Class<T> clazz) {

		ObjectMapper objMapper = new ObjectMapper();
		T t = null;
		try {
			t = objMapper.readValue(bts, clazz);
		} catch (IOException e) {
			e.printStackTrace();
		}
		return t;
	}
}
