# -*- coding:utf-8 -*-

# 求斐波那契数列第n个数字

def fib(n):
    if n <= 0:
        return 0
    if n == 1:
        return 1
    n3 = -1
    n1 = 0
    n2 = 1
    for i in range(n-1):
        n3 = n1 + n2
        n1 = n2
        n2 = n3
    return n3


def test():
    print(fib(6))  # 0 1 2 3 5 8


if __name__ == '__main__':
    test()  # 8
