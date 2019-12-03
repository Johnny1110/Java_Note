import queue
from threading import Thread


def throwErr(err_queue):
    try:
        raise RuntimeError('throwErr......')
    except Exception as e:
        err_queue.put(e)


def innerErr(err_queue):
    try:
        raise RuntimeError('throw innerErr')
    except Exception as e:
        err_queue.put(e)


def throwInnerErr(err_queue):
    # 執行緒中緒
    inner_t = Thread(target=innerErr, args=(err_queue,))
    inner_t.start()



def start():
    print('into start func')

    # 建立 err_queue 來接所有執行緒錯誤
    err_queue = queue.Queue()
    # 注意傳入的參數 args 必須是 Iterator : args=(err_queue,) 
    t1 = Thread(target=throwErr, args=(err_queue,))
    t2 = Thread(target=throwInnerErr, args=(err_queue,))

    t1.start()
    t2.start()

    # 檢查 queue 裡面是否有 Exception 出現
    while True:
        try:
            err = err_queue.get(block=False)
        except queue.Empty:
            pass
        else:
            print('raise ERROR in queue')
            raise err
        
        # 防阻塞
        t1.join(0.1)
        t1.join(0.1)
        if(t1.isAlive() or t2.isAlive()):
            continue
        else:
            break




if __name__ == '__main__':
    print('run start() ...')
    start()
    print('end start() ...')