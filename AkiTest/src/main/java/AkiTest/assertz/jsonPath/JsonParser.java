package AkiTest.assertz.jsonPath;


import java.util.HashMap;
import java.util.Map;

import static com.google.common.base.Preconditions.checkArgument;

public class JsonParser {
    private final String json;

    public JsonParser(String json) {
        this.json = json;
    }

    public String findMatchingValue(String matcher) {
        checkArgument(!matcher.isEmpty());
        JsonCharFeed jsonCharFeed = new JsonCharFeed();
        for (int i = 0; i < matcher.length(); i++) {
            jsonCharFeed.feedCharacter(matcher.charAt(i));
        }
        return jsonCharFeed.getMatchingComponent(matcher);
    }

    private class JsonCharFeed {

        private Character startingChar = null;
        private Character endingChar = null;

        private Map<JsonKey, JsonValue> hashMap = new HashMap<>();

        private StringBuilder currentKeyBuilder = new StringBuilder();
        private JsonKey currentKey;

        void feedCharacter(char c) {
            if (startingChar == null) {
                //Check starting character is a bracket
                checkArgument(c == '{', "Missing left bracket '{', not valid JSON");
                startingChar = c;
                return;
            }
            //Starting char already set
            processCharacter(c);
        }

        private void processCharacter(char c) {
            //TODO Refactor to context engine after some time
            if (c == ':') {
                //key found, set it as the currentKey
                this.currentKey = new JsonKey(currentKeyBuilder.toString());
                currentKeyBuilder = new StringBuilder();
                return;
            }
            //skip on whitespace TODO
            currentKeyBuilder.append(c);
        }

        String getMatchingComponent(String matcher) {
            checkArgument(endingChar != null, "Missing closing right bracket '}', not valid JSON");
            return "";
        }
    }
}