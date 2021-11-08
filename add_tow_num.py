# -*- coding:utf-8 -*-

# leetcode:https://leetcode-cn.com/problems/add-two-numbers/


# Definition for singly-linked list.
class ListNode(object):
    def __init__(self, val=0, next=None):
        self.val = val
        self.next = next


class Solution(object):
    def addTwoNumbers(self, l1, l2):
        """
        :type l1: ListNode
        :type l2: ListNode
        :rtype: ListNode
        """
        ret = ListNode()
        head = ret
        count = 0
        tmp = 0   # 进位值 0 或 1
        while l1 != None or l2 != None:
            a = 0
            b = 0
            if l1 != None:
                a = l1.val
                l1 = l1.next
            if l2 != None:
                b = l2.val
                l2 = l2.next
            tmp_sum = a + b + tmp
            tmp_ret = tmp_sum % 10
            if count == 0:
                ret.val = tmp_ret  # 第一个节点
            else:
                node = ListNode(tmp_ret)
                ret.next = node
                ret = ret.next

            # 处理进位
            if tmp_sum // 10 >= 1:
                tmp = tmp_sum // 10
                if l1 == None and l2 == None:  # 最后一位进上去，链表已经遍历完了
                    node = ListNode(tmp)
                    ret.next = node
                    ret = ret.next
            else:
                tmp = 0

            count += 1
        return head
