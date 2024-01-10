package assignment4;

//import java.util.*;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Comparator;
import java.util.Scanner;

import java.util.List;

public class Workerholic {
    public static class Task {
        int min, cost;
    }

    public static int getMinimumEnergy(Task[] tasks) {
        // cost 기준 오름차순 정렬
//        Arrays.sort(tasks, Comparator.comparingInt(task -> task.cost));

        // min 기준 내림차순 정렬
        Arrays.sort(tasks, Comparator.comparingInt((Task task) -> task.min).reversed());

        int curEnergy = 0;
        int totalCost = 0;
        int total = 0;
        int required = 0;

        List<Integer> costs = new ArrayList<>();
        List<Integer> mins = new ArrayList<>();

        for (Task task : tasks) {
            costs.add(task.cost);
            mins.add(task.min);
        }

        totalCost = costs.stream().mapToInt(Integer::intValue).sum();

        int diff = mins.get(mins.size() - 1) - costs.get(costs.size() - 1);
        curEnergy = totalCost + diff;
        total = curEnergy;


//        System.out.println("check: " + tasks[0].min + " " + tasks[0].cost);
//        diff = tasks[].min - tasks[0].cost;
//        curEnergy = totalCost + diff;
//
//        return minEnergy;

        for (int i = 0; i < costs.size(); i++) {
            if (curEnergy < mins.get(i)) {
                required = Math.abs(mins.get(i) - curEnergy);
                System.out.println("req: " + required);
            } else {
                curEnergy -= costs.get(i);
            }
        }

        total += required;

        return total;



//            for (int i = 0; i < tasks.length; i ++) {
//                for (int j = 0; j < tasks.length; j++) {
//                    if (minEnergy <= tasks[j].min) {
//
//
//                        if (minEnergy < tasks[i].cost) {
//                            minEnergy += tasks[j].min;
//                        }
//                    }
//                    int diff = tasks[j].min - minEnergy;
//                    minEnergy += diff;
//                    System.out.println("diff: " + diff + ", " + minEnergy);
//                    if (minEnergy <= tasks[j].min) {
//                        minEnergy += 1;
//                    }
//                }
//
//                System.out.println("check: " + minEnergy);
//            }

    }


    public static void main(String[] args) {

        // min과 cost를 나타내는 task 구조체의 배열인 tasks
        // cost: 일을 하는데 드는 에너지, min: 일을 하는데 필요한 최소한의 에너지
        // tasks[i] = { cost = 4, min = 7 }인 경우 현재 에너지가 7 미만이라면 해당 일을 진행 할 수 없다.
        // 만약 현재 에너지가 7 이상이라면 일을 시작 할 수 있고, 일을 완료하면 4의 에너지를 소모한다.

//        ArrayList<Task> tasks = new ArrayList<Task>();
        Task[] tasks = {};
        Scanner scanner = new Scanner(System.in);

        while (true) {
            String[] input = scanner.nextLine().split(" ");
            if (input.length < 2) {
                break;
            }

//            Task task = new Task(Integer.parseInt(input[1]), Integer.parseInt(input[0]));
            Task task = new Task();
            task.cost = Integer.parseInt(input[0]);
            task.min = Integer.parseInt(input[1]);
            tasks = addTask(tasks, task);
        }


        int result = getMinimumEnergy(tasks);
        System.out.println(result);
    }

    private static Task[] addTask(Task[] tasks, Task newTask) {
        Task[] newTasks = Arrays.copyOf(tasks, tasks.length + 1);
        newTasks[tasks.length] = newTask;
        return newTasks;
    }
}
