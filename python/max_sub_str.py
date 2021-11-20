# -*- coding:utf-8 -*-

# leetcode最长重复子串：https://leetcode-cn.com/problems/longest-substring-without-repeating-characters/

class Solution(object):
    def lengthOfLongestSubstring(self, s):
        """
        :type s: str
        :rtype: int
        """
        if not s:
            return 0
        tmp_dict = {}
        max_length = 0
        curr_length = 0
        start_index = 0
        for i in range(len(s)):
            ss = s[i]
            if ss in tmp_dict:
                if curr_length > max_length:
                    max_length = curr_length
                start_index = max(tmp_dict[ss] + 1, start_index)    # 处理如abba这种问题，遇到最后一个a，起点从2开始，而不是1
                curr_length = i - start_index + 1    # 关键在于计算新的起点在哪，以及剩余新的长度是多少abcbefg，发现b重复时，从c开始
                tmp_dict[ss] = i
            else:
                tmp_dict[ss] = i
                curr_length += 1
        if curr_length > max_length:
            max_length = curr_length
        return max_length


if __name__ == '__main__':
    so = Solution()
    print(so.lengthOfLongestSubstring("abba"))

