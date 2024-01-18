package Greedy;

import java.util.Scanner;

import static java.lang.Math.min;

// 주유소
public class BOJ13305 {

    // 주유비가 가장 싼 곳에서 많이 넣어야함.

    private static int minimum_cost(int[] distances, int[] costs) {
        int total_min_cost = 0;
        // 최소 비용 초기화
        int min_cost = costs[0];

        for (int i=0; i<distances.length; i++) {
            // 현재까지의 최소 주유비 갱신
            min_cost = min(min_cost, costs[i]);

            System.out.println("min:" + min_cost + " " + costs[i]  + " " + distances[i]);
            // 최소 주유비로 이동하는 비용 계산
            total_min_cost += min_cost * distances[i];
        }

        return  total_min_cost;
    }

    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        int n = sc.nextInt();
        int[] distances = new int[n-1];
        int[] costs = new int[n];

        for (int i=0; i<n-1; i++) {
            distances[i] = sc.nextInt();
        }

        for (int i=0; i<n; i++) {
            costs[i] = sc.nextInt();
        }

        int result = minimum_cost(distances, costs);
        System.out.println(result);
    }
}
