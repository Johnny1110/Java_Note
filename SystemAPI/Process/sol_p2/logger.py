import platform
import sys

from time import sleep

def print(*args):
    msg = ''
    for d in args:
        msg += str(d)

    if (platform.system().__eq__("Windows")):
        msg += "\n"
    else:
        msg += "\r\n"

    msg += " "*8191
    sys.stdout.write(msg)

if __name__ == '__main__':
    for i in range(10):
        print("第 {} 次迴圈...".format(i+1))
        sleep(1)