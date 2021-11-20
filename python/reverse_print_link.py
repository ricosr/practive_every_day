# -*- coding:utf-8 -*-

# 剑指6：输入链表头节点，从尾部开始反过来打印
# 思路：入栈再出栈

class Node:
    def __init__(self, val):
        self.value = val
        self.next = Node


class LinkList:
    def __init__(self, val):
        self.head = Node(val)

    def add_node(self, val):
        node = Node(val)
        tmp_point = self.head
        while tmp_point.next != Node:
            tmp_point = tmp_point.next
        tmp_point.next = node


def print_reverse(head):
    head_p = head
    stack = []
    while head_p != Node:
        stack.append(head_p.value)
        head_p = head_p.next
    for val in stack[::-1]:
        print(val)


if __name__ == '__main__':
    link = LinkList(1)
    for i in range(2, 11):
        link.add_node(i)
    print_reverse(link.head)

