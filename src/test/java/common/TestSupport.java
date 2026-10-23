package common;

import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.OutputStream;
import java.util.Map;
import java.util.Random;
import java.util.regex.Pattern;

import org.junit.jupiter.api.Assertions;
import org.opentest4j.AssertionFailedError;

import engine.TestBase;
import io.restassured.response.Response;
import tools.jackson.core.type.TypeReference;
import tools.jackson.databind.JsonNode;
import tools.jackson.databind.ObjectMapper;
import tools.jackson.databind.node.ArrayNode;

public class TestSupport implements TestBase {
    protected static Response response = null;
    protected static JsonNode responseBody = null;

	protected void setTestProperty(String propertyName, String propertyValue) {
		properties.setProperty(propertyName, propertyValue);
		try (OutputStream output = new FileOutputStream(properties.getProperty("project.resources.default") + "/config.properties")) {
            properties.store(output, null);
        } catch (IOException e) {
            e.printStackTrace();
        }
	}

    protected JsonNode toJson(String sourceValue) {
        return new ObjectMapper().readTree(sourceValue);
    }
	
    protected JsonNode getRandomJsonObject(String arrayObjectName, JsonNode responseBody) {
        ArrayNode totalContent = responseBody.get(arrayObjectName).asArray();
        int targetIndex = getRandomInt(totalContent.size());
        return totalContent.get(targetIndex);
    }

	protected Map<String, Object> deserializeFileJson(String filePath) {
		File jsonFile = new File(filePath);
		return new ObjectMapper().readValue(jsonFile, new TypeReference<Map<String, Object>>() {});
	}

	protected int getRandomInt(Integer limit) {
		if(limit != null && limit > 0)
			return new Random().nextInt(limit);
		return Math.abs(new Random().nextInt());
	}

	protected boolean textMatch(String value, String pattern) {
		return Pattern.matches(pattern, value);
	}

	protected void validateEquals(Object expected, Object current, String logMessage) {
		try {
			Assertions.assertEquals(expected, current);
			reporter.setPass(logMessage);
		} catch (AssertionFailedError e) {
			reporter.setFail("Test failed due result error.<br/>"+logMessage+".<br/>Expected: {"+expected+"} | Current: {"+current+"}");
			throw e;
		}
	}

	protected boolean validateRequiredField(ArrayNode jsonArray, String fieldName) {
		for (JsonNode jsonNode : jsonArray) {
			if(jsonNode.get(fieldName) == null || jsonNode.get(fieldName).asString().isBlank()) return false;
		}
		return true;
	}
}
