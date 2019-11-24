package com.jyj.callloggen;

import java.io.FileWriter;
import java.text.DecimalFormat;
import java.text.SimpleDateFormat;
import java.util.*;

/**
 * @Classname GenDataAppMain
 * @Description TODO
 * @Date 2019/11/18 2:31 下午
 * @Created by lipeijing
 */
public class GenDataAppMain {

    private static Random random = new Random();
    private static List<String> phoneNumbers = new ArrayList<String>();
    private static Map<String, String> callers = new HashMap<String, String>();

    static {
        callers.put("15810092493", "史玉龙");
        callers.put("18000696806", "赵贺彪");
        callers.put("15151889601", "张倩");
        callers.put("13269361119", "王世昌");
        callers.put("15032293356", "张涛");
        callers.put("17731088562", "张阳");
        callers.put("15338595369", "李进全");
        callers.put("15733218050", "杜泽文");
        callers.put("15614201525", "任宗阳");
        callers.put("15778423030", "梁鹏");
        callers.put("18641241020", "郭美彤");
        callers.put("15732648446", "刘飞飞");
        callers.put("13341109505", "段光星");
        callers.put("13560190665", "唐会华");
        callers.put("18301589432", "杨力谋");
        callers.put("13520404983", "温海英");
        callers.put("18332562075", "朱尚宽");
        callers.put("18620192711", "刘能宗");
        phoneNumbers.addAll(callers.keySet());
    }
    public static void main(String[] args) throws Exception {
        getCallLog();
    }

    public static void getCallLog() throws Exception {
//        String logFilePath = System.getProperty("user.dir");
//        FileWriter fileWriter = new FileWriter("callLog.log");
        FileWriter fileWriter = new FileWriter(PropertiesUtil.getString("log.file"), true);
        while (true) {
            String caller = phoneNumbers.get(random.nextInt(callers.size()));
            String callerName = callers.get(caller);

            String callee = null;
            String calleeName = null;
            while (true) {
                callee = phoneNumbers.get(random.nextInt(callers.size()));
                if (!callee.equals(caller)) {
                    break;
                }
            }
            calleeName = callers.get(callee);

            int duration = random.nextInt(PropertiesUtil.getInt("call.duration.max")) + 1;
            DecimalFormat decimalFormat = new DecimalFormat();
            decimalFormat.applyPattern(PropertiesUtil.getString("call.duration.format"));
            String durationStr = decimalFormat.format(duration);

            int year = PropertiesUtil.getInt("call.year");
            int month = 0;
            int day = 0;
            int hour = 0;
            int min = 0;
            int sec = 0;
            Date date = null;
            Date now = new Date();
            while (true) {
                month = random.nextInt(12);
                day = random.nextInt(29) + 1;
                hour = random.nextInt(24);
                min = random.nextInt(60);
                sec = random.nextInt(60);

                Calendar calendar = Calendar.getInstance();
                calendar.set(Calendar.YEAR, year);
                calendar.set(Calendar.MONTH, month);
                calendar.set(Calendar.DAY_OF_MONTH, day);
                calendar.set(Calendar.HOUR_OF_DAY, hour);
                calendar.set(Calendar.MINUTE, min);
                calendar.set(Calendar.SECOND, sec);
                date = calendar.getTime();
                if (date.compareTo(now) <= 0) {
                    break;
                }
            }
            SimpleDateFormat simpleDateFormat = new SimpleDateFormat();
            simpleDateFormat.applyPattern(PropertiesUtil.getString("call.time.format"));
            String dateStr = simpleDateFormat.format(date);

            String log = caller + "," + callee + "," + dateStr + "," + durationStr;
            System.out.println(log);
            fileWriter.write(log + "\n");
            fileWriter.flush();
            Thread.sleep(PropertiesUtil.getInt("gen.data.interval.ms"));
        }
    }
}
