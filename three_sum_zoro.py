# -*- coding:utf-8 -*-

# 输入一个数组，找到和为0的三元组


def sum_zero(input_list):
    ret = []
    sort_list = sorted(input_list)
    for i in range(len(sort_list)-2):
        low = i + 1
        high = len(sort_list) - 1
        target = -sort_list[i]
        if sort_list[i] > 0:
            break
        if i > 0 and sort_list[i] == sort_list[-1]:
            continue
        while low < high:
            second = sort_list[low]
            third = sort_list[high]
            tmp_sum = second + third
            if tmp_sum == target:
                ret.append([sort_list[i], second, third])
                while low < high and sort_list[low] == sort_list[low+1]:
                    low += 1
                while low < high and sort_list[high] == sort_list[high-1]:
                    high -= 1
                low += 1
                high -= 1
            if tmp_sum > target:
                high -= 1
            if tmp_sum < target:
                low += 1
    return ret


def test():
    input_list = [-3, -1, 0, 1, 2, 3, 4]
    ret = sum_zero(input_list)
    print(ret)


if __name__ == '__main__':
    test()


