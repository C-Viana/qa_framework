package common;

import java.io.File;
import java.util.Map;
import java.util.Random;
import java.util.regex.Pattern;

import org.junit.jupiter.api.Assertions;
import org.opentest4j.AssertionFailedError;

import io.restassured.response.Response;
import reporter.Reports;
import tools.jackson.core.type.TypeReference;
import tools.jackson.databind.JsonNode;
import tools.jackson.databind.ObjectMapper;
import tools.jackson.databind.node.ArrayNode;

public class TestSupport {
    protected static Response response = null;
    protected static JsonNode responseBody = null;

    public JsonNode toJson(String sourceValue) {
        return new ObjectMapper().readTree(sourceValue);
    }
	
    public JsonNode getRandomJsonObject(String arrayObjectName, JsonNode responseBody) {
        ArrayNode totalContent = responseBody.get(arrayObjectName).asArray();
        int targetIndex = getRandomInt(totalContent.size());
        return totalContent.get(targetIndex);
    }

	public Map<String, Object> deserializeFileJson(String filePath) {
		File jsonFile = new File(filePath);
		return new ObjectMapper().readValue(jsonFile, new TypeReference<Map<String, Object>>() {});
	}

	public int getRandomInt(Integer limit) {
		if(limit != null && limit > 0)
			return new Random().nextInt(limit);
		return Math.abs(new Random().nextInt());
	}

	public boolean textMatch(String value, String pattern) {
		return Pattern.matches(pattern, value);
	}

	public void validateEquals(Object expected, Object current, String logMessage) {
		try {
			Assertions.assertEquals(expected, current);
			Reports.get().setPass(logMessage);
		} catch (AssertionFailedError e) {
			Reports.get().setFail("Test failed due result error.<br/>"+logMessage+".<br/>Expected: {"+expected+"} | Current: {"+current+"}");
			throw e;
		}
	}

	public boolean validateRequiredField(ArrayNode jsonArray, String fieldName) {
		for (JsonNode jsonNode : jsonArray) {
			if(jsonNode.get(fieldName) == null || jsonNode.get(fieldName).asString().isBlank()) return false;
		}
		return true;
	}
}
