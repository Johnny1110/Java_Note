# 資料夾刪除與複製

<br>

-----------------------------

## 概要

Java IO 刪除/複製資料夾的時候有一個限制，就是若資料夾不爲空則刪除時會噴錯，只能刪除空資料夾或單一文件。複製時也只能複製單一文件或空資料夾。所以我自己實作一個可以刪資料夾與複製資料夾的類別。

## 文件

### NIO 基礎處理
* [Main.java](./Basic_Process/Main.java)
* [DirKit.java](./Basic_Process/DirKit.java)

### 進階使用 WalkFileTree 與 FileVisitor
* [Main.java](./WalkFileTree&FileVisitor/Main.java)
* [FileVisitorDemo.java](./WalkFileTree&FileVisitor/FileVisitorDemo.java)
* [Copyer.java](./WalkFileTree&FileVisitor/Copyer.java)

    FileVisitorDemo 繼承 SimpleFileVisitor，只覆寫父類別的2個方法 visitFile()  和  preVisitDirectory()。這兩個方法分別用來處理經過檔案時的動作與進入資料夾前的動作。

    我就在這兩個方法裏加入 Files.copy()

    比較要注意的是 path.subpath(int begin, int end) 的機制，end 是最後一個再加 1。

    


<br>

## 說明

* NIO 基礎處理的刪除與複製兩個功能都寫在 DirKit.java 裡面。
* 進階版 Copy 使用 WalkFileTree 與 FileVisitor﹐ 利用這兩個 API 歷遍 dir 的特性來做處理。