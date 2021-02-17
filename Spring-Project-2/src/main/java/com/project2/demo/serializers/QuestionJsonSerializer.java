package com.project2.demo.serializers;

import java.io.IOException;

import org.springframework.boot.jackson.JsonComponent;

import com.fasterxml.jackson.core.JsonGenerator;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.JsonSerializer;
import com.fasterxml.jackson.databind.SerializerProvider;
import com.project2.demo.beans.Question;

@JsonComponent
public class QuestionJsonSerializer extends JsonSerializer<Question> {

	
	/**
	 * id
quiz.quizid
type
description
ordering
	 */
    @Override
    public void serialize(Question question, JsonGenerator jsonGenerator,SerializerProvider serializerProvider) throws IOException, JsonProcessingException {
        jsonGenerator.writeStartObject();
        jsonGenerator.writeNumberField("id", question.getId());
        jsonGenerator.writeNumberField("quizid", question.getQuiz().getId());
        jsonGenerator.writeStringField("type", question.getQuestionType());
        jsonGenerator.writeStringField("description", question.getDescription());
        jsonGenerator.writeNumberField("ordering", question.getOrdering());
        jsonGenerator.writeEndObject();
    }
}
