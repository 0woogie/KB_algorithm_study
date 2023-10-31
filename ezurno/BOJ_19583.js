const fs = require("fs");
const filePath =
  process.platform === "linux" ? "/dev/stdin" : "20231031/input_19583.txt";
let input = fs.readFileSync(filePath).toString().split("\n");
let check = new Set();
// console.log(input);
[S, E, Q] = input.shift().trim().split(" ");

let counter = 0;
// console.log(S, E, Q);
input.forEach((value) => {
  let line = value.split(" ");
  if (changer(line[0]) <= changer(S)) {
    check.add(line[1]);
  } else if (
    changer(E) <= changer(line[0]) &&
    changer(line[0]) <= changer(Q) &&
    check.has(line[1])
  ) {
    // 끝나고 채팅 남겼는지
    // console.log(value);
    check.delete(line[1]);
    counter += 1;
  }
});

function changer(time) {
  return Number(time.split(":").join(""));
}

//https://www.acmicpc.net/problem/19583 (싸이버개강총회)
