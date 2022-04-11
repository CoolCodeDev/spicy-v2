package se.coolcode.spicy.json;

import java.lang.reflect.Field;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class Main {


    public static final String STRING_VALUE = "{\"key\": \"value\"}";
    public static final String STRING_VALUE_PATTERN = "\"(.*?)\": \"(.*?)\"";
    public static final String ARRAY_VALUE = "{\"key\": [\"val\"]}";
    public static final String ARRAY_VALUE_PATTERN = "\"(.*?)\": \\[";
    public static final String OBJECT_VALUE = "{\"key\": {\"subkey\": \"subvalue\"}}";
    public static final String OBJECT_VALUE_PATTERN = "\"(.*?)\": \\{";

    public static final String COMPLEX_VALUE = """
            {
                "level": "one",
                "objArray": [{"arrObjKey": "arrObjVal"}],
                "obj": {"objkey1": "objval1", "objkey2": "objval2"},
                "bool": true
            }
            """;

        /**
         * Utgå från klassen
         * Gå igenom alla declared fields
         * För varje fält:
         *  Om boolean - hitta \"field.name\": (true|false)
         *  Om string - hitta  \"field.name\": \"(.*?)\"
         *  Om nummer - hitta \"field.name\":
         */
    public static void main(String[] args) throws Exception {
//        Test test1 = Wip.fromJson("{\"string\": \"stringvalue\"}", Test.class);
//        Test test2 = Wip.fromJson("{\"another\": {\"one\": \"hello\"}}", Test.class);
//        Test test3 = Wip.fromJson("{\"string\": \"stringvalue\", \"another\": {\"one\": \"hello\"}}", Test.class);
//        System.out.println(test1);
//        System.out.println(test2);
//        System.out.println(test3);

        System.out.println(Json.fromJson("{\"flt\": 1.23}", Test.class));

//        System.out.println(Pattern.compile("(true|false)").matcher("false").find());
//        System.out.println(Pattern.compile("(\\d+.\\d+)[,]?").matcher("{\"key\": 1.65, \"some\": \"other\", \"number\": 1.23}").results().map(result -> result.group(1)).collect(Collectors.toList()));
    }


    public static class Wip {

        public static <T> T fromJson(String json, Class<T> type) throws Exception {
            TypeAdapter adapter = TypeAdapters.getAdapter(Object.class);
            T object = (T) adapter.getValue(type, null, json);
            return object;
        }
    }

    public static class Test {
        private String string;
        private Double dbl;
        private Float flt;
        private Integer integer;
        private Boolean bool;
        private Another another;

        @Override
        public String toString() {
            return "Test{" +
                    "string='" + string + '\'' +
                    ", dbl=" + dbl +
                    ", flt=" + flt +
                    ", integer=" + integer +
                    ", bool=" + bool +
                    ", another=" + another +
                    '}';
        }
    }

    public static class Another {
        private String one;

        @Override
        public String toString() {
            return "Another{" +
                    "one='" + one + '\'' +
                    '}';
        }
    }

}
