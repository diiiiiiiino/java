package 모던자바인액션.인터페이스;

@FunctionalInterface
public interface TripleFunction<T, U, K, R> {
    R apply(T weight, U color, K price);
}
