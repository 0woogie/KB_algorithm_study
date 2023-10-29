import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.*;
import java.util.Map.Entry;

public class BOJ_17140_이차원배열과연산 {
	public static ArrayList<ArrayList<Integer>> A;
	public static ArrayList<Integer> op(ArrayList<Integer> arr) {
		ArrayList<Integer> result = new ArrayList<Integer>();
		//100넘어가면 사용안함
		if(arr.size()>100)
		{
			ArrayList<Integer> temp = arr;
			arr.clear();
			for(int i = 0; i<100; i++) {
				arr.add(temp.get(i));
			}
		}
		//Map 활용
		Map<Integer, Integer> m = new HashMap<Integer, Integer>();
		for(int n : arr) {
			//정렬할때 0무시
			if(n == 0)
				continue;
			m.put(n, 0);
		}
		for(int n : arr) {
			if(m.keySet().contains(n))
			{
				int temp = (int) m.get(n) + 1;
				//System.out.println("temp : "+temp);
				m.put(n, temp);
			}
		}
		//커스텀 정렬 할거니까 Comparator 인터페이스 구현
		//key, value 다 쓸거니까 Map.entrySet 활용
		List<Map.Entry<Integer, Integer>> entryList = new LinkedList<>(m.entrySet());
		entryList.sort(new Comparator<Map.Entry<Integer, Integer>>() {
			@Override
			public int compare(Entry<Integer, Integer> o1, Entry<Integer, Integer> o2) {
				if(o1.getKey() != o2.getKey())
					return o2.getKey() - o1.getKey();
				else
					return o2.getValue() - o2.getValue();
			}
		});
		for(Map.Entry<Integer, Integer> entry : entryList) {
			result.add(entry.getKey());
			result.add(entry.getValue());
		}
		//System.out.println(result);
		return result;
	}
	public static void main(String[] args) throws IOException {
		// 문제의 정렬
		// map key : 숫자, value : 등장 횟수
		// 정렬시 comparable override : value 적은것 우선, value 같으면 key 적은거 우선
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st;
		A = new ArrayList<>();
		for(int i = 0; i<3; i++) {
			st = new StringTokenizer(br.readLine());
			A.add(new ArrayList<Integer>());
			for(int j = 0; j<3; j++) {
				A.get(i).add(Integer.parseInt(st.nextToken()));
			}
		}
		
		st = new StringTokenizer(br.readLine());
		int r = Integer.parseInt(st.nextToken());
		int c = Integer.parseInt(st.nextToken());
		int k = Integer.parseInt(st.nextToken());
		
		int totalTime = 0;
		
		/*
		 * ArrayList asdf = new ArrayList<Integer>(); 
		 * asdf.add(3); asdf.add(1);
		 * asdf.add(1); op(asdf);
		 */		
		
		while(true) {
			if(A.get(r).get(c) == k && totalTime <= 100)
			{
				break;
			}
			else if(totalTime > 100) {
				break;
			}
			else {
				totalTime++;
				boolean flag = true;
				int rowQuantity = A.size();
				int colQuantity = A.get(0).size();
				//배열의 모든 열에 대해서 정렬 수행 C
				if(rowQuantity < colQuantity) {
					flag = false;
					for(int i = 0; i<colQuantity; i++) {
						ArrayList<Integer> input = new ArrayList<Integer>();
						for(int j = 0; j<rowQuantity; j++) {
							input.add(A.get(i).get(j));
						}
						ArrayList<Integer> result = op(input);
						for(int j = 0; j<rowQuantity; j++) {
							input.add(A.get(i).get(j));
						}
					}
				}
				//배열의 모든 행에 대해서 정렬 수행 R
				else{
					for(int i = 0; i<rowQuantity; i++) {
						ArrayList<Integer> input = A.get(i);
						A.get(i).clear();
						ArrayList<Integer> result = op(input);
						for(int n : result) {
							A.get(i).add(n);
						}
					}
				}
				//R연산 가장 큰 행 기준으로 모든 행 크기 변함
				if(flag) {
					int maxSize = -1;
					for(int i = 0; i<rowQuantity; i++) {
						int curSize = A.get(i).size();
						if(maxSize < curSize)
							maxSize = curSize;
					}
					for(int i = 0; i<rowQuantity; i++) {
						int curSize = A.get(i).size();
						ArrayList<Integer> input = A.get(i);
						A.get(i).clear();
						for(int j = 0; j<maxSize; j++) {
							if(j<curSize)
								A.get(i).add(input.get(j));
							else
								A.get(i).add(0);
						}
					}
				}
				//C연산 가장 큰 열 기준으로 모든 열 크기 변함
				else {
					//ArrayList면 열단위 maxSize 어떻게 구함???ㅈㅈ
				}
			}
		}
		if(totalTime <= 100)
			System.out.println(totalTime);
		else
			System.out.println(-1);
	}

}
