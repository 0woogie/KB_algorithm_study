function solution(survey, choices) {
  let container = {
    R: 0,
    T: 0,
    C: 0,
    F: 0,
    J: 0,
    M: 0,
    A: 0,
    N: 0,
  };

  survey.forEach((test, index) => {
    let [no, yes] = test.split("");
    let choice = choices[index];
    if (choice < 4) {
      container[no] += 4 - choice;
    } else if (4 < choice) {
      container[yes] += choice - 4;
    }
  });

  let result = "";
  container.R < container.T ? (result += "T") : (result += "R");
  container.C < container.F ? (result += "F") : (result += "C");
  container.J < container.M ? (result += "M") : (result += "J");
  container.A < container.N ? (result += "N") : (result += "A");

  return result;
}
