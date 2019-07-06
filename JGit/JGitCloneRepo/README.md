# 使用 JGit 實現 git clone

<br>

---------------------------------

<br>

## 說明

* 主程式是 startClone.java。

* Clone 相關 git 指令寫在 CloneRepoThread.java 裡面。

* TimerThread.java 是一個計時器，原本沒有要用到執行緒，因為我第一次實驗 clone 了一個超大的 repo 害我以為程式當掉沒在跑，結果回頭找問題找很久，最後發現已經 clone 好了。

    所以我寫了一個計時器，當 clone 動作在執行時，計時器每一秒會 print 出已等待時間。clone 執行緒結束時，計時器同時結束。

    當一個執行緒動作結束，關閉另一個執行緒的處理方法，我這裡選擇使用 volatile 來動態傳入 boolean 中止 TimerThread 的無限計時迴圈。(動態解釋不一定對拉..)

* 最後因為剛好把 print commit 做完了就剛好做一個整合，加入 WalkCommit.java。

<br>

## 文件 

* [startClone.java](./startClone.java)
* [CloneRepoThread.java](./CloneRepoThread.java)
* [TimerThread.java](./TimerThread.java)
* [WalkCommit.java](./WalkCommit.java)