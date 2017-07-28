package com.github.vaibhavsinha.kong.impl.helper;

import com.google.gson.Gson;
import com.google.gson.TypeAdapter;
import com.google.gson.stream.JsonReader;
import lombok.extern.java.Log;
import okhttp3.MediaType;
import okhttp3.ResponseBody;
import retrofit2.Converter;

import java.io.ByteArrayInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.Reader;
import java.nio.charset.Charset;

@Log
final class CustomGsonResponseBodyConverter<T> implements Converter<ResponseBody, T> {

	private static final Charset UTF_8 = Charset.forName("UTF-8");

	private final Gson gson;
	private final TypeAdapter<T> adapter;

	CustomGsonResponseBodyConverter(Gson gson, TypeAdapter<T> adapter) {
		this.gson = gson;
		this.adapter = adapter;
	}

	@Override
	public T convert(ResponseBody value) throws IOException {

		String response = value.string();

		if(response == null || response.isEmpty()) {
			//It may response empty body...
			log.warning("Why Response empty body ???");
			return null;
		}

		MediaType contentType = value.contentType();
		Charset charset = contentType != null ? contentType.charset(UTF_8) : UTF_8;
		InputStream inputStream = new ByteArrayInputStream(response.getBytes());
		Reader reader = new InputStreamReader(inputStream, charset);
		JsonReader jsonReader = gson.newJsonReader(reader);

		try {
			return adapter.read(jsonReader);
		} finally {
			value.close();
		}

	}
}

