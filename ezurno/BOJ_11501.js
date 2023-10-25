const fs = require("fs");
const filePath =
  process.platform === "linux" ? "/dev/stdin" : "20231024/input_11501.txt";
let input = fs.readFileSync(filePath).toString().split("\n");

const TC = input.shift();
// testcase

for (let i = 0; i < TC * 2; i += 2) {
  const day = Number(input[i]);
  // input 으로 들어온 날짜 값
  const money = input[i + 1].split(" ").map(Number);
  // 주가
  let summary = 0;
  let max = 0;

  // 가장 비쌀때 팔아야 하므로 뒤에서 부터
  for (let i = day - 1; i > -1; i--) {
    if (max < money[i]) max = money[i]; // 최댓값 갱신
    if (money[i] < max) summary += max - money[i];
  }

  console.log(summary);
}

//https://www.acmicpc.net/problem/11501 (주식)
