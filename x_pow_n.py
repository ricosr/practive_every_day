# -*- coding:utf-8 -*-

# 求x的n次幂

def pow_x_n(x, n):
    if n == 0:
        return 1
    if n == 1:
        return x
    tmp = pow_x_n(x, abs(n//2))
    if n > 0:
        if n % 2 == 0:
            return tmp*tmp
        else:
            return tmp*tmp*x
    else:
        if n % 2 == 0:
            return 1 / (tmp*tmp)
        else:
            return 1 / (tmp*tmp*x)

print(pow_x_n(2,-2))




