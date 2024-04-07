package recipes.utility;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class PTToInteger {
    public static void main(String[] args) {};
    public static int PTConvert(String inputString){
        String ToFormat = inputString;
        String trimmedString = ToFormat.substring(2);

        if (!trimmedString.contains("H")) {
            trimmedString = "0H" + trimmedString;
        }
        if (!trimmedString.contains("M")) {
            trimmedString = trimmedString+"0M";
        }

        String regex = "(\\d+)H(\\d+)M";
        Pattern pattern = Pattern.compile(regex);
        Matcher matcher = pattern.matcher(trimmedString);

        int Hours = 0;
        int Minutes = 0;

        // Проверка соответствия регулярному выражению и извлечение значений
        if (matcher.matches()) {
            // Группа 1 содержит значение часов
            if (matcher.group(1) != null) {
                Hours = Integer.parseInt(matcher.group(1));
            }

            // Группа 2 содержит значение минут
            if (matcher.group(2) != null) {
                Minutes = Integer.parseInt(matcher.group(2));
            }
        }
        return Hours*60+Minutes;
    }
}