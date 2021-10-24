# todo: 未完

ret = {}


def count_route(map_list, x, i, j, li, lj, count, pos):
    if (i, j) in pos:
        return 0
    else:
        pos.append((i, j))
    count += 1
    try:
        if x < map_list[i][j]:
            ret[x] = count - 1
            return 0
    except:
        print(x, count)
        ret[x] = count - 1
        return 0
    # if 0 < i < len(map_list)-1 and 0 < j < len(map_list)-1:
    a, b, c, d = 0, 0, 0, 0
    if (li, lj) != (i, j - 1):
        a = count_route(map_list, x, i, j - 1, i, j, count, pos)
    if (li, lj) != (i, j + 1):
        b = count_route(map_list, x, i, j + 1, i, j, count, pos)
    if (li, lj) != (i - 1, j):
        c = count_route(map_list, x, i - 1, j, i, j, count, pos)
    if (li, lj) != (i + 1, j):
        d = count_route(map_list, x, i + 1, j, i, j, count, pos)
    if a+b+c+d == 0:
        return 0
    # else:
    #     a, b = 0, 0
    #     if (i == 0 or i == len(map_list)-1) and (j != 0 and j != len(map_list)-1):
    #         a = count_route(map_list, x, i, j - 1, count)
    #         b = count_route(map_list, x, i, j + 1, count)
    #     if j == 0 or j == len(map_list)-1 and (i != 0 and i != len(map_list)-1):
    #         a = count_route(map_list, x, i - 1, j, count)
    #         b = count_route(map_list, x, i + 1, j, count)
    #     if i == 0 and j == 0:
    #         a = count_route(map_list, x, i + 1, j, count)
    #         b = count_route(map_list, x, i, j + 1, count)
    #     if i == 0 and j == len(map_list)-1:
    #         a = count_route(map_list, x, i + 1, j, count)
    #         b = count_route(map_list, x, i, j - 1, count)
    #     if j == 0 and i == len(map_list)-1:
    #         a = count_route(map_list, x, i - 1, j, count)
    #         b = count_route(map_list, x, i, j + 1, count)
    #     if j == len(map_list)-1 and i == len(map_list)-1:
    #         a = count_route(map_list, x, i - 1, j, count)
    #         b = count_route(map_list, x, i, j - 1, count)
    #     if a + b == -2:
    #         ret.append(count)
    #         return 0


if __name__ == '__main__':
    map_list = [
        [4, 2],
        [3, 1]
        # [4, 1, 2]
    ]
    for i in range(len(map_list)):
        for j in range(len(map_list)):
            pos = []
            ret = count_route(map_list, map_list[i][j], i, j, i, j, 0, pos)
    for k, v in ret.items():
        print(k, v)
    print(max(ret.values()))

