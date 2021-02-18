package com.project2.demo.serializers;

import java.io.IOException;

import com.fasterxml.jackson.core.JsonGenerator;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.JsonSerializer;
import com.fasterxml.jackson.databind.SerializerProvider;
import com.project2.demo.beans.User;

public class UserJsonSerializer extends JsonSerializer<User>{

	@Override
	public void serialize(User user, JsonGenerator jsonGenerator,SerializerProvider serializerProvider) throws IOException, JsonProcessingException {
		jsonGenerator.writeStartObject();
		jsonGenerator.writeNumberField("id", user.getId());
		jsonGenerator.writeStringField("username", user.getUsername());
		jsonGenerator.writeStringField("role", user.getRole().toString());
		jsonGenerator.writeNumberField("teacherId", (user.getTeacher() != null ? user.getTeacher().getId() : -1));
		jsonGenerator.writeEndObject();
	}
}
