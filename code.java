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
 * ������̳���ʽ���ɽű�
 * ���ӻظ�Ƶ�ʿ���ͨ���趨ʱ��κ�¥�������޸�
 * @Author Aoest
 * @Create 2023/12/711:56
 * @Version 1.0
 */
public class code {
    public static void main(String[] args) {
        Scanner scanner=new Scanner(System.in);
        //�趨Ҫ���ɵ�¥����������ʼ�ͽ���ʱ��
        System.out.print("�����ʼ¥����ţ�");
        int start=scanner.nextInt();
        System.out.print("�������¥����ţ�");
        int end=scanner.nextInt();
        if (end-start<0 || start<0 || end<0){
            System.out.println("�����¥����������");
            return;
        }
        scanner.nextLine();
        System.out.print("���뿪ʼ���ڣ���ʽxxxx-xx-xx xx:xx:xx\t");
        //���ڸ�ʽΪ"xxxx-xx-xx xx:xx:xx"
        String start_time= scanner.nextLine();
        System.out.print("����������ڣ���ʽxxxx-xx-xx xx:xx:xx\t");
        String end_time=scanner.nextLine();
        System.out.println();
        System.out.println("ѡ�������ʽ��1.Ĭ�� 2.������̳");
        int choose=scanner.nextInt();
        System.out.print("�����ļ������ַ���ļ�����");
        scanner.nextLine();
        String path=scanner.nextLine();
        start(start,end,start_time,end_time,choose,path);
    }
    private static void start(int a,int b,String start_time,String end_Time,int choose,String path){
        int l=b-a;
        Date[] times=new Date[l];
        SimpleDateFormat sdf=new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
        //���������¥������ȷ�������ʱ�������
        for (int i = 0; i < l; i++) {
            times[i]=RandomTime(start_time,end_Time);
        }
        //��ʱ��㰴��ǰ��������
        Arrays.sort(times);
        //������UID
        Random random=new Random();
        //ƴ�Ӳ�����ı�

        PrintStream ps=null;
        try (FileOutputStream fos= new FileOutputStream(path,true)){
            ps=new PrintStream(fos);
            System.setOut(ps);
            switch (choose) {
                case 1:
                    for (int i = 0; i < l; i++) {
                        System.out.println((i + a) + "¥\tUID:" + random.nextInt(10000) + "\t\t\t" + "����ʱ��\t" + sdf.format(times[i]));
                        System.out.println("\n");
                    }
                    break;
                case 2:
                    for (int i = 0; i < l; i++) {
                        System.out.println("��"+(i + a) + " ����= =�� " +  sdf.format(times[i])+" ���ԡ���");
                        System.out.println("\n");
                    }
                    break;
                default:
                    System.out.println("ѡ����������");
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
