package smartoccupation.util;

import java.text.SimpleDateFormat;
import java.util.Date;

public class DateUtil {
    public static final String ISO = "yyyy-MM-dd";

    public static String toIso(Date date) {
        if (date == null) return null;
        return new SimpleDateFormat(ISO).format(date);
    }
}
