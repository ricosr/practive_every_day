# -*- coding:utf-8 -*-

# 剑指3-2：数组长度n+1, 数组元素大小范围在1-n，数组不可改变，输出任意一个重复的数字
# 思路：不是分数组，而是二分值大小的范围


def duplicate(input_ls):
    ls_len = len(input_ls)
    if ls_len == 0:
        return -1

    start = 1
    end = ls_len - 1
    while start <= end:
        mid = (end - start) // 2 + start    # 防止出现mid为0
        count = cal_num_count(input_ls, start, mid)

        if start == end:
            if count > 1:
                print(start, mid, end)
                return start
            else:
                return -1
        if count > mid - start + 1:    # 如果前面有重复，就搞前面的
            end = mid
        else:
            start = mid + 1


def cal_num_count(input_ls, start, end):
    count = 0
    for each_num in input_ls:
        if end >= each_num >= start:
            count += 1
    return count


def test():
    ret = duplicate([2, 3, 5, 4, 3, 2, 6, 7])
    print(ret)


if __name__ == '__main__':
    test()

