const fs = require("fs");
const filePath =
  process.platform === "linux" ? "/dev/stdin" : "20231024/input_16236.txt";
let input = fs.readFileSync(filePath).toString().split("\n");

let shark = {
  x: 0,
  y: 0,
  exp: 2,
  size: 2,
};

let N = Number(input.shift());

let map = Array(N);
let visited = Array(N);

for (let i = 0; i < N; i++) {
  map[i] = Array(N);
  visited[i] = Array(N).fill(false);
}

for (let i = 0; i < N; i++) {
  let line = input.shift().trim().split(" ").map(Number);
  // 지역 값 저장

  console.log(area);

  for (let j = 0; j < N; j++) {
    map[i][j] = line[j];
    if (map[i][j] === 9) {
      // 상어의 위치이므로
      map[i][j] = 0;
      // 해당 위치 0으로 비운 후
      shark.x = j;
      shark.y = i;
      // 상어 위치 갱신
    }
  }
}

const dx = [0, 0, -1, 1];
const dy = [-1, 1, 0, 0];
// 상 하 좌 우

// 세모세모빔
