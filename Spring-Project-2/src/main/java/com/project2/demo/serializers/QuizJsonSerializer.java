package com.project2.demo.serializers;

import java.io.IOException;

import org.springframework.boot.jackson.JsonComponent;

import com.fasterxml.jackson.core.JsonGenerator;
import com.fasterxml.jackson.databind.JsonSerializer;
import com.fasterxml.jackson.databind.SerializerProvider;
import com.project2.demo.beans.Quiz;

@JsonComponent
public class QuizJsonSerializer extends JsonSerializer<Quiz> {

	public QuizJsonSerializer() {
		super();
	}

	@Override
	public void serialize(Quiz value, JsonGenerator gen, SerializerProvider serializers) throws IOException {
        gen.writeStartObject();
        gen.writeNumberField("id", value.getId());
        gen.writeStringField("name", value.getName());
        gen.writeNumberField("userid", value.getUser().getId());
        gen.writeEndObject();
		
	}

}
