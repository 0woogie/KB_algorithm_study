const fs = require("fs");
const filePath =
  process.platform === "linux" ? "/dev/stdin" : "20231024/input_17298.txt";
let input = fs.readFileSync(filePath).toString().split("\n");

const N = Number(input.shift());
// N 은 크기

const array = input[0].split(" ").map(Number);
const answer = Array(N).fill(-1);
const stack = [];

for (let i = 0; i < N; i++) {
  while (0 < stack.length && stack[stack.length - 1][0] < array[i]) {
    // 길이가 0이 아니거나 스택에 넣은 value 값보다 다음 값이 더 클 때
    const [value, index] = stack.pop();
    // 스택에서 꺼냄
    answer[index] = array[i];
    // 정답 배열의 해당 index 값을 그 값으로 변경
  }

  stack.push([array[i], i]);
  // 그렇지 않다면 스택에 적재 (다음 값이 더 작을 경우)
}

console.log(answer.join(" "));

// https://www.acmicpc.net/problem/17298 (오큰수)
