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
    inner_t = Thread(target=innerErr, args=(err_queue,))
    inner_t.start()



def start():
    print('into start func')
    err_queue = queue.Queue()
    t1 = Thread(target=throwErr, args=(err_queue,))
    t2 = Thread(target=throwInnerErr, args=(err_queue,))

    t1.start()
    t2.start()

    while True:
        try:
            err = err_queue.get(block=False)
        except queue.Empty:
            pass
        else:
            print('raise ERROR in queue')
            raise err

        t1.join(0.1)
        t1.join(0.2)
        if(t1.isAlive() or t2.isAlive()):
            continue
        else:
            break




if __name__ == '__main__':
    print('runing start ...')
    start()
    print('start end ...')