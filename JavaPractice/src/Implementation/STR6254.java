package Implementation;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.*;

public class STR6254 {

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        String[] week = new String[5];

        List<HashMap<Integer, List<Integer>>> listOfMaps = new ArrayList<>();

        int workHours = 0;
        int workMins = 0;

        for (int i=0; i<week.length; i++) {
            week[i] = br.readLine();

            String[] times = week[i].split(" ");
            String[] startTime = times[0].split(":");
            String[] endTime = times[1].split(":");
            int startHour = convert(startTime[0]);
            int startMin = convert(startTime[1]);
            int endHour = convert(endTime[0]);
            int endMin = convert(endTime[1]);

            // Integer : 0은 시작, 1은 종료

            HashMap<Integer, List<Integer>> map = new HashMap();
            map.put(0, List.of(startHour, startMin));
            map.put(1, List.of(endHour, endMin));

            listOfMaps.add(map);
        }

        for (int i=0; i<listOfMaps.size(); i++) {
            var hashMap = listOfMaps.get(i);

            workHours += hashMap.get(1).get(0) - hashMap.get(0).get(0);
            workMins += hashMap.get(1).get(1) - hashMap.get(0).get(1);

        }

        int result = (workHours * 60) + workMins;
        System.out.println(result);

    }

    public static Integer convert(String str) {
        return Integer.parseInt(str);
    }
}
