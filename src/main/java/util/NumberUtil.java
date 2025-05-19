package util;

public class NumberUtil {

    /**
     * Parst den String {@code s} in die gewünschte Number-Unterklasse.
     * Unterstützt alle gebräuchlichen Wrapper-Typen.
     *
     * @param s     der Eingabe-String
     * @param clazz die Klasse von T (z. B. Integer.class, Double.class, Long.class, etc.)
     * @param <T>   eine Unterklasse von Number
     * @return die geparste Zahl
     * @throws NumberFormatException wenn der String ungültig ist
     * @throws IllegalArgumentException bei unsupported clazz
     */
	
    @SuppressWarnings("unchecked")
    public static <T extends Number> T parse(String s, Class<T> clazz) {
        String str = s.trim();
        if (clazz == Integer.class) {
            return (T) Integer.valueOf(str);
        } else if (clazz == Long.class) {
            return (T) Long.valueOf(str);
        } else if (clazz == Double.class) {
            return (T) Double.valueOf(str);
        } else if (clazz == Float.class) {
            return (T) Float.valueOf(str);
        } else if (clazz == Short.class) {
            return (T) Short.valueOf(str);
        } else if (clazz == Byte.class) {
            return (T) Byte.valueOf(str);
        } else if (clazz == java.math.BigInteger.class) {
            return (T) new java.math.BigInteger(str);
        } else if (clazz == java.math.BigDecimal.class) {
            return (T) new java.math.BigDecimal(str);
        } else {
            throw new IllegalArgumentException("Unsupported number type: " + clazz.getName());
        }
    }
}
