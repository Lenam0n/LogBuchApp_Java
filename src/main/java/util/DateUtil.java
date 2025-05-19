package util;

import java.util.Calendar;
import java.util.Date;

/**
 * Utility-Klasse zum Erzeugen von java.util.Date-Objekten
 * für "jetzt", "heute" oder beliebige Datum/Uhrzeit-Kombinationen.
 */
public class DateUtil {

    private DateUtil() {}

    /**
     * @return Date für den aktuellen Zeitpunkt
     */
    public static Date now() {
        return new Date();
    }

    /**
     * @return java.util.Date für heute um 00:00:00.000
     */
    public static Date today() {
        Calendar c = Calendar.getInstance();
        // setze Uhrzeit auf Mitternacht
        c.set(Calendar.HOUR_OF_DAY, 0);
        c.set(Calendar.MINUTE,      0);
        c.set(Calendar.SECOND,      0);
        c.set(Calendar.MILLISECOND, 0);
        return c.getTime();
    }

    /**
     * Erzeugt ein Date aus den übergebenen Komponenten.
     *
     * @param year        Jahr, z.B. 2025
     * @param month       Monat 1–12 (1=Januar, 12=Dezember)
     * @param dayOfMonth  Tag im Monat 1–31
     * @param hour        Stunde 0–23
     * @param minute      Minute 0–59
     * @param second      Sekunde 0–59
     * @return das entsprechende java.util.Date
     */
    public static Date of(int year, int month, int dayOfMonth,
                          int hour, int minute, int second) {
        Calendar c = Calendar.getInstance();
        c.set(Calendar.YEAR,        year);
        c.set(Calendar.MONTH,       month - 1);
        c.set(Calendar.DAY_OF_MONTH,dayOfMonth);
        c.set(Calendar.HOUR_OF_DAY, hour);
        c.set(Calendar.MINUTE,      minute);
        c.set(Calendar.SECOND,      second);
        c.set(Calendar.MILLISECOND, 0);
        return c.getTime();
    }
}
