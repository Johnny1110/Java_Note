# 深度優先搜尋（DFS）

<br>

---

<br>

## 遞迴 DFS

遍歷 Tree 可以使用深度優先方法（DFS），DFS 從 Tree 的 root 開始。選擇第一個子節點，若子節點還有子節點，那就再選第一個子節點。當它位於沒有子節點的 node 時，就向 Tree 的父節點移動，此時選擇第二個節點，若沒有第二個節點就再向父節點移動，當它最後停在 root 最後一個子節點時，遍歷就完成了。

遞迴迭代是兩種常見實作 DFS 的方法，其中 遞迴時做很簡單：

<br>

```java
private static void recursiveDFS(Node node) {
    if (node instanceof TextNode)
        System.out.println(node);
    for (Node child: node.childNodes()) {
        recursiveDFS(child);
    }
}
```

<br>
<br>
<br>
<br>

## 迭代 DFS

<br>

stack（堆疊） 被稱為 LIFO（last in first out），與 stack 相返的是 queue，也就是 FIFO（first in first out）。

<br>

Java 中定義堆疊的 interface 有 2 種：__Stack__ 與 __Deque__。

stack 有 4 個主要方法：

* `push()` 將新元素押入 stack 頂端。

* `pop()` 取出 stack 頂端元素不放回。

* `peek()` 偷看一下 stack 頂端元素，不取出。

* `isEmpty()` 是否為空。

<br>

在 Java 裡實作 stack 有三種選擇：

1. 用 ArrayList 或 LinkedList，如果用 ArrayList 那加入與移除就從尾端，

2. Java 提供一個 Stack 類別，但是這個類不屬於 __Java Collections Framework__，所以不能與常用資料結構 API 整合，故不推薦。

3. 最佳策略使用 __Deque__ interface，像是 __ArrayDeque__ 實作。

<br>
<br>

__Deque__ 其實就是 "double-ended queue" 的縮寫。Java 的 __Deque__ interface 規範了 `push()` `pop()` `peek()` `isEmpty()` 方法。

<br>

下面是迭代 DFS，使用 __ArrayDeque__ 實現 Node 物件 stacl：

<br>

```java
private static void iterativeDFS(Node root) {
    Deque<Node> stack = new ArrayDeque<Node>();
    stack.push(root);

    while (!stack.isEmpty()) {
        Node node = stack.pop();
        if (node instanceof TextNode) {
            System.out.println(node);
        }

        List<Node> nodes = new ArrayList<>(node.childNodes());
        Collections.reverse(nodes);

        for (Node child : nodes){
            stack.push(child);
        }
    }
}
```

<br>

先 push root 到 stack 中，迴圈繼續直到 stack 為空，每次都從 stack pop 一個 Node，如果這個 Node 是 TextNode 就印出內容，接下來將子節點 push 到 stack 中，為了要以正確順序處理子節點，我們要 reverse 一下他們，然後在 push 到 stack 中。


<br>
<br>
<br>
<br>

除了 __ArrayDeque__ 之外，Java 還有另一個 Deque 實作，就是 __LinkedList__，__LinkedList__ 同時實現 __List__ 與 __Deque__ 兩個 interface。可以視形況選擇介面。

我們可以這樣：

```java
Deque<Node> deque = new LinkedList<Node>();
```

<br>

這樣就可以使用 __Deque__ 定義的方法，但不能使用 __List__ 定義的方法。反之亦然，如果介面宣告改成 __List__，那就不能用 __Deque__ 方法。

所以 __LinkedList__ 既可以當 __Deque__ 又可以當 __List__。

