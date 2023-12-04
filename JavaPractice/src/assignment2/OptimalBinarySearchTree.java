package assignment2;
import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.HashMap;
import java.util.Map;

public class OptimalBinarySearchTree {

    public static void main(String[] args) {
        Map<String, Double> keyProbabilities = new HashMap<>();

        try {
            // 입력 파일 "input2.txt" 읽기
            BufferedReader reader = new BufferedReader(new FileReader("./src/assignment2/input2.txt"));
            String line;
            boolean isHeader = true;

            while ((line = reader.readLine()) != null) {
                if (isHeader) {
                    isHeader = false;
                    continue;
                }

                String[] parts = line.split("\\s+");
                String key = parts[0];
                double probability = Double.parseDouble(parts[1]);
                keyProbabilities.put(key, probability);
            }

            reader.close();

            double result = optimalBST(keyProbabilities);
            System.out.println("최적 이진 탐색 트리의 평균 검색 시간: " + result);
        } catch (IOException e) {
            System.err.println("입력 파일을 읽는 중 오류가 발생했습니다.");
        }
    }

    public static double optimalBST(Map<String, Double> keyProbabilities) {
        int n = keyProbabilities.size();
        double[] probabilities = new double[n];
        String[] keys = new String[n];

        int i = 0;
        for (Map.Entry<String, Double> entry : keyProbabilities.entrySet()) {
            keys[i] = entry.getKey();
            probabilities[i] = entry.getValue();
            i++;
        }

        double[][] cost = new double[n][n];

        for (i = 0; i < n; i++) {
            cost[i][i] = probabilities[i];
        }

        for (int l = 2; l <= n; l++) {
            for (i = 0; i <= n - l; i++) {
                int j = i + l - 1;
                cost[i][j] = Double.MAX_VALUE;
                double totalProbabilities = 0.0;

                for (int k = i; k <= j; k++) {
                    totalProbabilities += probabilities[k];
                }

                for (int k = i; k <= j; k++) {
                    double leftCost = (k > i) ? cost[i][k - 1] : 0;
                    double rightCost = (k < j) ? cost[k + 1][j] : 0;
                    double rootCost = totalProbabilities + leftCost + rightCost;

                    if (rootCost < cost[i][j]) {
                        cost[i][j] = rootCost;
                    }
                }
            }
        }

        return cost[0][n - 1];
    }
}
