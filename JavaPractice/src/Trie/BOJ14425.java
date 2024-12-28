package Trie;

import java.util.Scanner;

public class BOJ14425 {
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);

        // 문자열의 개수
        int N = sc.nextInt();
        // 검사해야 하는 문자열
        int M = sc.nextInt();

        tNode root = new tNode();

        while (N > 0) {
            // 집합 S에 포함되어 있는 문자열들이 주어짐
            String text = sc.next();
            tNode now = root;
            for (int i=0; i<text.length(); i++) {
                char c = text.charAt(i);
                // 26개 알파벳 위치 배열 index로 나타냄 (-'a' 수행)
                if (now.next[c-'a'] == null) {
                    now.next[c-'a'] = new tNode();
                }
                now = now.next[c-'a'];
                if (i == text.length() - 1) {
                    now.isEnd = true;
                }
            }
            N--;
        }

        int count = 0;
        while (M > 0) {
            // 검사해야 하는 문자열들
            String text = sc.nextLine();
            tNode now = root;
            for (int i=0; i<text.length(); i++) {
                char c = text.charAt(i);
                if (now.next[c-'a'] == null) {
                    break;
                }
                now = now.next[c-'a'];
                if (i == text.length() - 1 && now.isEnd) {
                    count++;
                }
                M--;
            }
            System.out.println(count);
        }
    }

    static class tNode {
        tNode[] next = new tNode[26];
        // 문자열 마지막 유무 표시
        boolean isEnd;
    }
}

