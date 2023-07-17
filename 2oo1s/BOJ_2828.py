# 15분
import sys
input = sys.stdin.readline

N, M = map(int, input().split())
J = int(input())
d = []                  # 사과 떨어지는 위치 저장
s = 1                   # 바구니 시작점
e = s + (M - 1)         # 바구니 끝지점
move = 0                # 이동 횟수

for i in range(J):
    d.append(int(input()))

for i in d:
    while True:
        if s <= i and i <= e:
            break
        elif i < s:
            s -= 1
            e -= 1
            move += 1
        else:
            s += 1
            e += 1
            move += 1

print(move)
