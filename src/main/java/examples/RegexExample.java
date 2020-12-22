package examples;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class RegexExample {

    public static void findTheMatch() {

        String message = "Hi my phone number is 123-123-1234, call me anytime!";

        Pattern phoneNumberPattern = Pattern.compile("([0-9]{3}-[0-9]{3}-[0-9]{4})");

        Matcher phoneNumberMatches = phoneNumberPattern.matcher(message);

        if (phoneNumberMatches.find()) {
            System.out.println(phoneNumberMatches.group(1).replaceAll("-", ""));
        }

    }

}
