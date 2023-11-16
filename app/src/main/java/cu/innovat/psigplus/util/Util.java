package cu.innovat.psigplus.util;

import java.text.Normalizer;
import java.text.Normalizer.Form;
import java.util.UUID;
import java.util.Locale;
import java.util.regex.Pattern;
import java.sql.Timestamp;
import java.util.Date;

/**
 * @author Luis Andrés Valido Fajardo +53 53694742  luis.valido1989@gmail.com
 * @date 30/10/23
 */
public class Util {

    private static final Pattern NONLATIN = Pattern.compile("[^\\w-]");
    private static final Pattern WHITESPACE = Pattern.compile("[\\s]");

    public static String generateUUID(){
        UUID uuid = UUID.randomUUID();
        return uuid.toString();
    }

    public static String toSlug(String input) {
        String nowhitespace = WHITESPACE.matcher(input).replaceAll("-");
        String normalized = Normalizer.normalize(nowhitespace, Form.NFD);
        String slug = NONLATIN.matcher(normalized).replaceAll("");
        return slug.toLowerCase(Locale.ENGLISH);
    }

    public static long getCurrentTimeStamp(){
        Date date = new Date();
        Timestamp ts = new Timestamp(date.getTime());
        return ts.getTime();
    }

    public static String convertToMMSS(int _second){
        return  String.format("%02d:%02d",0,_second);

    }

    public static String convertTo00(int _number){
        return String.format("%02d",_number);
    }
}
