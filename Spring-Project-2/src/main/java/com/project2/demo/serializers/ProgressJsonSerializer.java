package com.project2.demo.serializers;

import java.io.IOException;

import org.springframework.boot.jackson.JsonComponent;

import com.fasterxml.jackson.core.JsonGenerator;
import com.fasterxml.jackson.databind.JsonSerializer;
import com.fasterxml.jackson.databind.SerializerProvider;
import com.project2.demo.beans.Progress;

@JsonComponent
public class ProgressJsonSerializer extends JsonSerializer<Progress> {

	@Override
	public void serialize(Progress value, JsonGenerator gen, SerializerProvider serializers) throws IOException {
		gen.writeStartObject();
		gen.writeNumberField("progressid", value.getId());
		gen.writeNumberField("userid", value.getUser().getId());
		gen.writeObjectField("answer", value.getAnswer());
		gen.writeEndObject();
	}
	
}
