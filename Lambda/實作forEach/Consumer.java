// @FunctionalInterface -> 只有一個 func 的 interface
@FunctionalInterface
public interface Consumer<T> {
    void accept(T t);
}
