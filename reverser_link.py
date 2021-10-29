# -*- coding:utf-8 -*-

# 反转列表，最简单的笔试题，虾皮面经有kaoguo


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


def reverse_link_list(head):
    p1 = Node
    while head != Node:
        print(head.value)
        next = head.next
        head.next = p1
        p1 = head
        print(p1)
        head = next
    return p1


def test(head):
    ret = reverse_link_list(head)
    while ret.next != Node:
        print(ret.value)
        ret = ret.next


if __name__ == '__main__':
    link = LinkList(1)
    for i in range(2, 11):
        link.add_node(i)
    test(link.head)

