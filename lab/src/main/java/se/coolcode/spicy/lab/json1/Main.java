package se.coolcode.spicy.lab.json1;

import java.io.BufferedInputStream;
import java.io.IOException;
import java.io.StringReader;
import java.util.HashMap;
import java.util.Map;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

class Main {

    public static final String jsonData = """
            {
            	"name": "Johnny",
            	"age": 30,
            	"developer": true,
            	"adress": {
            		"street": "cool street",
            		"number": 12
            	},
            	"emails": ["one@gmail.com", "two@gmail.com"],
            	"objects": [{"key": "value"}, {"key": "value"}]
            }
            """;

    public static void main(String[] args) throws Exception {
        int BEGIN_STRING = '"';
        int END_STRING = '"';

        StringReader reader = new StringReader(jsonData);
        Map<String, String> keyValue = new HashMap<>();

        StringBuilder keyBuilder = new StringBuilder();
        String currentKey = null;

        boolean keyStart = false;
        boolean valueStart = false;
        int data = reader.read();
        while (data != -1) {
            //Håll koll på om det är nyckel eller värde som ska byggas
            if (data == BEGIN_STRING && !keyStart && !valueStart) {
                keyStart = true;
            } else if (data == END_STRING && keyStart && !valueStart) {
                keyStart = false;
                valueStart = true;
                currentKey = keyBuilder.toString();
                keyBuilder.setLength(0);
            }

            //Bygg nyckel
            if (keyStart) {
                if (data != BEGIN_STRING || data != END_STRING) {
                    keyBuilder.append((char) data);
                }
            }
            // Bygg värde
            if (valueStart) {
//                if (data != BEGIN_STRING)

            }


//            if (keyStart != -1 && data != END_STRING) {
//                keyBuilder.append((char) data);
//            } else if (keyStart != -1 && data == END_STRING) {
//                keyStart = -1;
//                currentKey = keyBuilder.toString();
//                keyBuilder.setLength(0);
//                valueStart = 0;
//            }
//            value:
//            if (data == BEGIN_STRING && keyStart == -1 && valueStart == 0) {
//
//            }


            data = reader.read();
        }
        System.out.println(currentKey);
    }

//    public static void main(String[] args) throws Exception {
//        StringReader reader = new StringReader(jsonData);
//        StringBuilder keyBuilder = new StringBuilder();
//        StringBuilder valueBuilder = new StringBuilder();
//        boolean key = true;
//        int BEGIN_STRING = '"';
//        int END_STRING = '"';
//        int BEGIN_OBJECT = '{';
//        int END_OBJECT = '}';
//        int BEGIN_ARRAY = '[';
//        int END_ARRAY = ']';
//
//        int keyStart = -1;
//        int valueStart = -1;
//        int character = reader.read();
//        while (character != -1) {
//            if (key) {
//                if (character == BEGIN_STRING && keyBuilder.isEmpty()) {
//                    keyBuilder.append((char) character);
//                } else if (character != END_STRING && !keyBuilder.isEmpty()) {
//                   keyBuilder.append((char) character);
//                } else if (character == END_STRING && !keyBuilder.isEmpty()) {
//                    keyBuilder.append((char) character);
//                    key = false;
//                }
//            } else {
//                if (valueStart == -1) {
//                    if (character == BEGIN_STRING || character == BEGIN_ARRAY || character == BEGIN_OBJECT) {
//                        valueStart = character;
//                        valueBuilder.append((char) character);
//                    }
//                } else if (valueStart == BEGIN_STRING) {
//                    if (character != END_STRING) {
//                        valueBuilder.append((char) character);
//                    } else {
//                        valueBuilder.append((char) character);
//                        break;
//                    }
//                }
//            }
//            character = reader.read();
//        }
//        System.out.println(keyBuilder.append(": ").append(valueBuilder));
//    }


//    public static void main(String[] args) {
//        Pattern pattern = Pattern.compile("(\".*\":[ ]?\".*\")", Pattern.MULTILINE);
//        Matcher matcher = pattern.matcher(jsonData);
//        while(matcher.find()) {
//            System.out.println(matcher.group(1));
//            System.out.println("---");
//        }
//    }
}
