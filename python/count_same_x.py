# -*- coding:utf-8 -*-

# 求有序数列里某个数字个数

def count_same(input_list, x):
    def search(input_list, x):
        head = 0
        tail = len(input_list)-1
        while head <= tail:
            mid = (head + tail) // 2
            mid_value = input_list[mid]
            if mid_value > x:
                tail = mid - 1
            else:
                head = mid + 1
        return head
    return search(input_list, x) - search(input_list, x-1)


def test():
    print(count_same([1, 2, 2, 2, 2, 3], 2))


if __name__ == '__main__':
    test()   # output: 4
