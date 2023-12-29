package assi_5;

/************ this is just a simple json converter, just not to use the additional libraries ************/
import java.lang.reflect.Field;
import java.util.Arrays;
import java.util.HashMap;
import java.util.Map;

public class SimpleJsonConverter {

    public static String toJson(Object object) {
        StringBuilder json = new StringBuilder("{");

        Field[] fields = object.getClass().getDeclaredFields();
        for (Field field : fields) {
            field.setAccessible(true);
            try {
                json.append("\"").append(field.getName()).append("\":");
                if (field.getType().isPrimitive() || field.getType() == String.class) {
                    json.append("\"").append(field.get(object)).append("\",");
                } else if (field.getType().isArray()) {
                    json.append(Arrays.toString((Object[]) field.get(object))).append(",");
                } else {
                    // Handle nested objects (recursively)
                    json.append(toJson(field.get(object))).append(",");
                }
            } catch (IllegalAccessException e) {
                e.printStackTrace();
            }
        }

        // Remove the trailing comma and close the JSON object
        if (json.length() > 1) {
            json.setLength(json.length() - 1);
        }
        json.append("}");

        return json.toString();
    }

    public static Map<String, Object> fromJson(String jsonString) {
        Map<String, Object> keyValueMap = new HashMap<>();

        try {

            // we have to remove , between objects
            jsonString = jsonString.endsWith(",") ? jsonString.substring(0, jsonString.length() - 1) : jsonString;
            String[] keyValuePairs = jsonString.substring(1, jsonString.length() - 1).split(",");
            for (String pair : keyValuePairs) {
                String[] keyValue = pair.split(":");
                String fieldName = keyValue[0].replaceAll("\"", "").trim();
                String value = keyValue[1].trim();

                keyValueMap.put(fieldName, parseValue(value));
            }

        } catch (Exception e) {
            e.printStackTrace();
        }

        return keyValueMap;
    }

    private static Object parseValue(String value) {
        if (value.startsWith("\"") && value.endsWith("\"")) {
            // String value
            return value.substring(1, value.length() - 1);
        } else {
            // Numeric value (assuming it's an integer for simplicity)
            try {
                return Integer.parseInt(value);
            } catch (NumberFormatException e) {
                // If parsing as an integer fails, return the original string
                return value;
            }
        }
    }

}
