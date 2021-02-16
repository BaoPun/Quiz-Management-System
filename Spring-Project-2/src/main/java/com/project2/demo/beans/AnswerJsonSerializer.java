package com.project2.demo.beans;

import java.io.IOException;

import org.springframework.boot.jackson.JsonComponent;

import com.fasterxml.jackson.core.JsonGenerator;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.JsonSerializer;
import com.fasterxml.jackson.databind.SerializerProvider;

@JsonComponent
public class AnswerJsonSerializer extends JsonSerializer<Answer> {

    @Override
    public void serialize(Answer answer, JsonGenerator jsonGenerator,SerializerProvider serializerProvider) throws IOException, JsonProcessingException {
        jsonGenerator.writeStartObject();
        jsonGenerator.writeNumberField("id", answer.getId());
        jsonGenerator.writeStringField("answerText", answer.getAnswerText());
        jsonGenerator.writeNumberField("questionID", answer.getQuestion().getId());
        jsonGenerator.writeBooleanField("isCorrect", answer.getIsCorrect());
        jsonGenerator.writeEndObject();
    }
}
