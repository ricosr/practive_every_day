# -*- coding:utf-8 -*-

# 剑指3：输入长度为n的数组，数组元素为0~n-1范围的数，输出是否有重复元素

def if_duplicate1(input_list):
    list_length = len(input_list)

    if list_length == 0:
        return False

    for num in input_list:
        if num < 0 or num > list_length-1:
            return False

    sort_list = sorted(input_list)
    for i in range(list_length):
        if i < list_length - 1 and sort_list[i] == sort_list[i+1]:
            return True
    return False


def if_duplicate2(input_list):   # 书上思路
    list_length = len(input_list)

    if list_length == 0:
        return False

    for num in input_list:
        if num < 0 or num > list_length-1:
            return False

    for i in range(list_length):
        while input_list[i] != i:
            if input_list[i] == input_list[input_list[i]]:
                return True
            else:    # 因为数字大小是在数组长度范围内，将数字放在它大小对应的位置上，如果后面有重复的，在上一个if处会触发重复判断
                tmp = input_list[i]
                input_list[i] = input_list[tmp]
                input_list[tmp] = tmp
    return False


def test():
    print(if_duplicate1([2, 3, 1, 0, 2, 5, 3]))
    print(if_duplicate1([3, 4, 5, 6, 2, 1]))
    print(if_duplicate2([2, 3, 1, 2, 5, 3]))
    print(if_duplicate2([3, 4, 5, 6, 2, 1]))


if __name__ == '__main__':
    test()

