package Greedy;

import java.util.Scanner;

// 주유소
public class BOJ13305 {

    // 주유비가 가장 싼 곳에서 많이 넣어야함.

//    private static int minimum_cost(int[] distances, int[] costs) {
//        int total_min_cost = 0;
//        // 최소 비용 초기화
//        int min_cost = costs[0];
//
//        for (int i=0; i<distances.length; i++) {
//            // 현재까지의 최소 주유비 갱신 (현재 주유소가 이전 주유소보다 쌀 경우 최소비용 갱신)
//            min_cost = min(min_cost, costs[i]);
//
//            System.out.println("min:" + min_cost + " " + costs[i]  + " " + distances[i]);
//            // 최소 주유비로 이동하는 비용 계산
//            total_min_cost += (min_cost * distances[i]);
//        }
//
//        return  total_min_cost;
//    }

    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        int n = sc.nextInt();
        long[] distances = new long[n-1];
        long[] costs = new long[n];

        for (int i=0; i<n-1; i++) {
            distances[i] = sc.nextInt();
        }

        for (int i=0; i<n; i++) {
            costs[i] = sc.nextInt();
        }

        long total_min_cost = 0;
        // 최소 비용 초기화
        long min_cost = costs[0];


        for (int i=0; i<n-1; i++) {
            // 현재까지의 최소 주유비 갱신 (현재 주유소가 이전 주유소보다 쌀 경우 최소비용 갱신)

            if (min_cost > costs[i])
                min_cost = costs[i];

            System.out.println("min:" + min_cost + " " + costs[i]  + " " + distances[i]);
            // 최소 주유비로 이동하는 비용 계산
            total_min_cost += (min_cost * distances[i]);
        }

        System.out.println(total_min_cost);
    }
}
