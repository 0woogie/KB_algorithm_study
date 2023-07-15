# 20분
def dfs(n, s, c):
    global maxS
    if c > L:
        return
    if n == N:
        if s > maxS:
            maxS = s
        return

    dfs(n + 1, s + arr[n][0], c + arr[n][1])
    dfs(n + 1, s, c)


T = int(input())

for tc in range(1, T + 1):
    N, L = map(int, input().split())
    arr = [list(map(int, input().split())) for _ in range(N)]

    maxS = 0
    dfs(0, 0, 0)

    print(f'#{tc} {maxS}')
