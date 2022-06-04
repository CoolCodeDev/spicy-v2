package se.coolcode.spicy.json.reader;

import java.util.regex.MatchResult;

class NoMatchResult implements MatchResult {
    
    public static final NoMatchResult NO_MATCH_RESULT = new NoMatchResult();
    
    NoMatchResult() {}
    
    @Override
    public int start() {
        return 0;
    }

    @Override
    public int start(int group) {
        return 0;
    }

    @Override
    public int end() {
        return 0;
    }

    @Override
    public int end(int group) {
        return 0;
    }

    @Override
    public String group() {
        return null;
    }

    @Override
    public String group(int group) {
        return null;
    }

    @Override
    public int groupCount() {
        return 0;
    }
}
