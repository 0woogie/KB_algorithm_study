# 10분
import sys
input = sys.stdin.readline

board = input()  # 보드판 입력

board = board.replace("XXXX", "AAAA")
board = board.replace("XX", "BB")

# 'AAAA'와 'BB' 폴리오미노로 변경을 해주고난 뒤에도
# X가 남아있다면,
# 폴리오미노로 다 덮을 수 없는 보드판이므로 -1 출력
if 'X' in board:
    print(-1)

else:
    print(board)
