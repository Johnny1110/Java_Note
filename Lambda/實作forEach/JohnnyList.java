/* JohnnyList 是我定義的一個 List 介面，並沒有 implements Collection，
 * 這裡簡單的定義了幾個 List 類別應有的基本 API (add() get() size())。
 * 利用 java 1.8 的 default function 特性，定義 forEach()。
 */

public interface JohnnyList<E> {
    void add(E e);

    E get(int index);

    int size();

    // 歷遍自己的屬性，傳入 Consumer 的 accept() 中。
    default void forEach(Consumer<? super E> action){
        for(int i = 0; i < size(); i++){
            action.accept(this.get(i));
        }
    }
}
