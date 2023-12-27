package cu.innovat.psigplus.util;

import java.text.Normalizer;
import java.text.Normalizer.Form;
import java.time.Instant;
import java.time.LocalDateTime;
import java.time.ZoneId;
import java.time.format.DateTimeFormatter;
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
    private static final Pattern CI = Pattern.compile("^\\d{11}$");

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

    public static boolean isEmptyString(String str){
        boolean empty = true;
        empty = str.strip().isEmpty();
        return empty;
    }

    public static boolean isValidCI(String ci){
        boolean valid = true;
        valid = CI.matcher(ci).matches();
        if(valid){
            int year = Integer.parseInt(ci.substring(0,2));
            int month = Integer.parseInt(ci.substring(2,4));
            int day = Integer.parseInt(ci.substring(4,6));
            //TODO Falta validar las fechas
        }
        return valid;
    }

    public static boolean isValidNumberPhone(String numberPhone){
        boolean valid = true;
        //TODO Falta
        return valid;
    }

    public static boolean isValidIMEI(String imei){
        boolean valid = true;
        //TODO Falta
        return valid;
    }

    public static String convertToStringFillZeros(int number,int places){
        String str = String.valueOf(number);
        while(str.length()<places) str="0"+str;
        return str;
    }

    public static String formatTimeStamp(long timesStamp){
        LocalDateTime dateTime = LocalDateTime.ofInstant(Instant.ofEpochMilli(timesStamp), ZoneId.systemDefault());
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("dd/MM/yyyy");
        String formatteDate = dateTime.format(formatter);
        return formatteDate;
    }
}
