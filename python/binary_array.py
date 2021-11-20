# -*- coding:utf-8 -*-

# 剑指4：二维数组左到右增大，上到下增大，确定输入的数字是否存在其中
# 思路：确保每次只能移动一个维度的方向，或者说在两个维度上，一个变大，一个变小，不能两个维度一样的变化趋势，那么程序就没法写了

def if_exist(matrix, rows, columns, x):
    i = 0
    j = columns - 1
    while i < rows and j >= 0:
        if matrix[i][j] == x:
            return True
        else:
            if matrix[i][j] > x:
                j -= 1
            if matrix[i][j] < x:
                i += 1
    return False


def test():
    matrix = [
        [1, 2, 8, 9],
        [2, 4, 9, 12],
        [4, 7, 10, 13],
        [6, 8, 11, 15]
    ]
    print(if_exist(matrix, 4, 4, 7))


if __name__ == '__main__':
    test()
