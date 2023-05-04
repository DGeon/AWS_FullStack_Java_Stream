import java.io.BufferedReader;
import java.io.FileInputStream;
import java.io.FileReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

public class Subway {
  public static void main(String[] args) throws IOException {
    // csv=> List<Map<String, Object>>
    List<String> inputListString = new ArrayList<>();
    BufferedReader br = new BufferedReader(new InputStreamReader(
        new FileInputStream("C:\\workspaces\\workspace-java\\STREAM\\stream\\src\\CARD_SUBWAY_MONTH_202303 copy.csv"),
        "utf-8"));
    String str = null;
    while ((str = br.readLine()) != null) {
      inputListString.add(str);
    }

    br.close();
    System.out.println("============================");
    System.out.println(inputListString.get(0));
    inputListString.remove(0);
    System.out.println(inputListString.get(0));
    System.out.println("============================");
    // inputListString.stream().map():List<Map<String, Object>>

    int max = 0;
    int min = 0;
    int total = 0;
    int count = 0;
    Map<Object, List<Map<String, Object>>> mapList = inputListString.stream().map((obj) -> {
      Map<String, Object> map = new HashMap<>();
      SimpleDateFormat sdf = new SimpleDateFormat("yyyyMMdd");
      Date date;

      map.put("사용일자", obj.split(",")[0]);
      map.put("노선명", obj.split(",")[1]);
      map.put("역명", obj.split(",")[2]);
      map.put("승차총승객수", Integer.parseInt(obj.split(",")[3].replaceAll("\"", "")));
      map.put("하차총승객수", Integer.parseInt(obj.split(",")[4].replaceAll("\"", "")));
      try {
        map.put("등록일자", sdf.parse(obj.split(",")[5].replaceAll("\"", "")));
      } catch (ParseException e) {
        e.printStackTrace();
      }
      return map;
    }).collect(Collectors.groupingBy(map -> {
      return map.get("등록일자");
    }));

    System.out.println(mapList.toString());
    // List<Map<String, Object>> mapList = inputListString.stream().map((obj) -> {
    // Map<String, Object> map = new HashMap<>();
    // map.put("사용일자", obj.split(",")[0]);
    // map.put("노선명", obj.split(",")[1]);
    // map.put("역명", obj.split(",")[2]);
    // map.put("승차총승객수", Integer.parseInt(obj.split(",")[3].replaceAll("\"", "")));
    // map.put("하차총승객수", Integer.parseInt(obj.split(",")[4].replaceAll("\"", "")));
    // map.put("등록일자", obj.split(",")[5]);
    // return map;
    // }).collect(Collectors.toList());

    // for (int i = 0; i < mapList.size() - 1; i++) {
    // max = Math.max((int) mapList.get(i).get("승차총승객수"), (int) mapList.get(i +
    // 1).get("승차총승객수"));
    // min = Math.min((int) mapList.get(i).get("승차총승객수"), (int) mapList.get(i +
    // 1).get("승차총승객수"));
    // }
    // System.out.println("Max" + max);
    // System.out.println("Min" + min);
    // for (int i = 0; i < mapList.size(); i++) {
    // total += (int) mapList.get(i).get("승차총승객수");
    // }
    // System.out.println("Total" + total);
    // System.out.println("avg" + (double) total / mapList.size());
    // System.out.println("==========================");
    // 집계
    // max, min, sum, avg, count

    // group 날짜별(10일단위) 총 승객수, 노선별 승객수
    // inputListString.stream().collect(Collectors.groupingBy());

  }
}