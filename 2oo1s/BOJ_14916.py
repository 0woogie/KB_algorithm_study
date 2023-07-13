# 25분
import sys
input = sys.stdin.readline

n = int(input())    # 거스름돈 액수
cnt = 0             # 거슬러줄 동전 개수

# 거스름돈 액수가 5의 배수일 때는
# 바로 거슬러줄 동전의 개수는 n // 5 인데
# 거스름돈 액수가 5의 배수가 아닐 때는
# 5의 배수가 될 때까지 2원씩 차감
while n >= 0:
    if n % 5 == 0:
        cnt += n//5
        break
    else:          
        n -= 2
        cnt += 1

if n < 0:
    print(-1)
else:
    print(cnt)
