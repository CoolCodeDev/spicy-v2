package se.coolcode.spicy.lab.json2;

import java.lang.reflect.ParameterizedType;
import java.lang.reflect.Type;
import java.util.*;

public class Main {

    public static final String JSON_STRINGS =
            """
            {
                "firstKey": "first value",
                "secondKey": "second value",
                "thirdKey": "third value"
            }
            """;

    public static final String JSON_ARRAY =
            """
            {
                "firstKey": ["val1", "val2"],
                "secondKey": ["val3", "val4"]
            }
            """;

    public static final String JSON_MIX =
            """
            {
                "firstKey": ["val1", "val2"],
                "secondKey": "second value",
            }
            """;

    public static void main(String[] args) {
        System.out.println(Json.fromJson(JSON_STRINGS).asJsonObject().get("firstKey").asJson());
        System.out.println(Json.fromJson(JSON_STRINGS).asJsonObject().get("firstKey").asJsonString().asString());
        System.out.println("-------------------------");
        System.out.println(Json.fromJson(JSON_ARRAY).asJsonObject().get("firstKey").asJson());
        System.out.println(Json.fromJson(JSON_ARRAY).asJsonObject().get("firstKey").asJsonArray().asList());
        System.out.println("-------------------------");
        System.out.println(Json.fromJson(JSON_MIX).asJsonObject().get("firstKey").asJson());
        System.out.println(Json.fromJson(JSON_MIX).asJsonObject().get("firstKey").asJsonArray().asCollection(new HashSet<>()));
        System.out.println(Json.fromJson(JSON_MIX).asJsonObject().get("secondKey").asJson());
        System.out.println("-------------------------");
        System.out.println(Json.fromJson(JSON_MIX).asJson());
        System.out.println("-------------------------");
        System.out.println((Test) Json.fromJson(JSON_MIX).asJsonObject().as(Test.class));
    }

    public static class Test {
        LinkedHashSet<String> firstKey;
        String secondKey;

        @Override
        public String toString() {
            return "Test{" +
                    "firstKey=" + firstKey +
                    ", secondKey='" + secondKey + '\'' +
                    '}';
        }
    }
}
