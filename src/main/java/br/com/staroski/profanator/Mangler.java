package br.com.staroski.profanator;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.StringReader;

/**
 * The <tt>Mangler</tt> is <B>Staroski's Code Profanator</B>'s most important class.<BR>
 * It simple mangles the legibility of the source code being profanated.
 */
final class Mangler {

    private static final String NEW_LINE = "\n";
    private static final String LINE_COMMENT = "// ";
    private static final String UNICODE_LINE_FEED = "\\u000a";

    public String mangle(String origin) {
        StringBuilder fucked = new StringBuilder();
        try {
            BufferedReader reader = new BufferedReader(new StringReader(origin));
            String line = null;
            int count = 0;
            while ((line = reader.readLine()) != null) {
                if (count > 0) {
                    fucked.append(NEW_LINE);
                }
                String trimmed = line.trim();
                String indent = line.substring(0, line.indexOf(trimmed));
                String unicode = toUnicode(trimmed);
                fucked.append(indent);
                fucked.append(LINE_COMMENT);
                fucked.append(UNICODE_LINE_FEED);
                fucked.append(unicode);
                count++;
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
        return fucked.toString();
    }

    private String toUnicode(String origin) {
        StringBuilder unicode = new StringBuilder();
        for (char symbol : origin.toCharArray()) {
            unicode.append("\\u" + unicode(symbol));
        }
        return unicode.toString();
    }

    private String unicode(int symbol) {
        StringBuilder unicode = new StringBuilder(Integer.toHexString(symbol));
        while (unicode.length() < 4) {
            unicode.insert(0, '0');
        }
        return unicode.toString();
    }
}
