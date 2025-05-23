package util;

public class Result<V, E> {
    private final V value;
    private final E error;

    private Result(V value, E error) {
        this.value = value;
        this.error = error;
    }

    public static <V, E> Result<V, E> success(V value) {
        return new Result<>(value, null);
    }

    public static <V, E> Result<V, E> error(E error) {
        return new Result<>(null, error);
    }

    public boolean isSuccess()  { return error == null; }
    public boolean isError()    { return error != null; }
    public V getValue()        { return value; }
    public E getError()        { return error; }
}
