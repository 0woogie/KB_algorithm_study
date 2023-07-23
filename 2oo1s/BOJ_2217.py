import sys
input = sys.stdin.readline

N = int(input())
w = []                  # 각 로프가 버틸 수 있는 최대 중량 저장

for _ in range(N):
    w.append(int(input()))

w.sort()
max_weight = []         # 임의의 몇개의 로프를 골랐을 때, 버틸 수 있는 최대 중량 저장

for i in range(len(w)):
    max_weight.append(w[i] * (len(w) - i))

print(max(max_weight))
