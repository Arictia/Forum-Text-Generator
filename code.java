package com.arictia;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.PrintStream;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Arrays;
import java.util.Date;
import java.util.Random;
import java.util.Scanner;
import java.util.regex.Pattern;

/**
 * ClassName:code
 * package:com.arictia
 * Description:
 * 自用论坛体格式生成脚本
 * 帖子回复频率可以通过设定时间段和楼层数来修改
 * @Author Aoest
 * @Create 2023/12/711:56
 * @Version 1.0
 */
public class code {
    public static void main(String[] args) {
        Scanner scanner=new Scanner(System.in);
        //设定要生成的楼层数量，开始和结束时间
        System.out.print("输入初始楼层序号：");
        int start=scanner.nextInt();
        System.out.print("输入结束楼层序号：");
        int end=scanner.nextInt();
        if (end-start<0 || start<0 || end<0){
            System.out.println("输入的楼层数量有误！");
            return;
        }
        scanner.nextLine();
        System.out.print("输入开始日期，格式xxxx-xx-xx xx:xx:xx\t");
        //日期格式为"xxxx-xx-xx xx:xx:xx"
        String start_time= scanner.nextLine();
        System.out.print("输入结束日期，格式xxxx-xx-xx xx:xx:xx\t");
        String end_time=scanner.nextLine();
        System.out.println();
        System.out.println("选择输出格式：1.默认 2.晋江论坛");
        int choose=scanner.nextInt();
        System.out.print("输入文件输出地址和文件名：");
        scanner.nextLine();
        String path=scanner.nextLine();
        start(start,end,start_time,end_time,choose,path);
    }
    private static void start(int a,int b,String start_time,String end_Time,int choose,String path){
        int l=b-a;
        Date[] times=new Date[l];
        SimpleDateFormat sdf=new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
        //根据输入的楼层数，确定随机的时间点数量
        for (int i = 0; i < l; i++) {
            times[i]=RandomTime(start_time,end_Time);
        }
        //将时间点按从前往后排序
        Arrays.sort(times);
        //获得随机UID
        Random random=new Random();
        //拼接并输出文本

        PrintStream ps=null;
        try (FileOutputStream fos= new FileOutputStream(path,true)){
            ps=new PrintStream(fos);
            System.setOut(ps);
            switch (choose) {
                case 1:
                    for (int i = 0; i < l; i++) {
                        System.out.println((i + a) + "楼\tUID:" + random.nextInt(10000) + "\t\t\t" + "发布时间\t" + sdf.format(times[i]));
                        System.out.println("\n");
                    }
                    break;
                case 2:
                    for (int i = 0; i < l; i++) {
                        System.out.println("№"+(i + a) + " ☆☆☆= =于 " +  sdf.format(times[i])+" 留言☆☆☆");
                        System.out.println("\n");
                    }
                    break;
                default:
                    System.out.println("选择输入有误");
                    break;
            }
            System.out.flush();

        } catch (Exception e) {
            throw new RuntimeException(e);
        }finally {
            ps.close();
        }
    }
    private static Date RandomTime(String start_time,String end_Time){
        try {
            SimpleDateFormat format=new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
            Date start= format.parse(start_time);
            Date end=format.parse(end_Time);
            if (start.getTime() >= end.getTime()) {
                return null;
            }
            long date = random(start.getTime(), end.getTime());
            return new Date(date);
        } catch (ParseException e) {
            throw new RuntimeException(e);
        }
    }
    private static long random(long begin,long over){
        long rtn = begin + (long) (Math.random() * (over - begin));
        if (rtn == begin || rtn == over) {
            return random(begin, over);
        }
        return rtn;
    }

}
