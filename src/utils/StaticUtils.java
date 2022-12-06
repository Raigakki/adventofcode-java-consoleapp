package utils;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class StaticUtils {

    public static List<String> fromInputToStringList(String input) {
        return new ArrayList<>(Arrays.asList(input.split("\n")));
    }
}
