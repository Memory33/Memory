package day21;

import java.io.IOException;
import java.nio.charset.Charset;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.*;
import java.util.stream.Collectors;
import java.util.stream.Stream;

public class two {

        public static void main(String[] args) {
            try {

                Stream<String> lines = Files.lines(Paths.get("heroes.txt"), Charset.forName("utf-8"));
                ArrayList<first> list = new ArrayList<first>();
                lines.forEach(x->{
                    String[] split = x.split("\t");
                    first p = new first(Integer.parseInt(split[0]), split[1], split[2], split[3], Integer.parseInt(split[4]), Integer.parseInt(split[5]), Integer.parseInt(split[6]));
                    list.add(p);
                });

                System.out.println("武力前三：");
                list.stream().sorted(new Comparator<first>() {
                    @Override
                    public int compare(first o1, first o2) {

                        return o2.getPower()-o1.getPower();
                    }
                }).limit(3).forEach(p-> System.out.println(p.getName()));

                System.out.println("");

                System.out.println("按地区分组：");
                Map<String, List<first>> collect = list.stream().collect(Collectors.groupingBy((s) -> s.getLocation() ));
                System.out.println(collect);
                for (String s : collect.keySet()) {
                    List<first> jiangList = collect.get(s);
                    long count = jiangList.stream().count();
                    System.out.println(s+" "+count);
                }

                System.out.println("");

                System.out.println("寿命前三：");
                list.stream().sorted(new Comparator<first>() {
                    @Override
                    public int compare(first o1, first o2) {

                        return (o2.getDeath()-o2.getBirth())-(o1.getDeath()-o1.getBirth());
                    }
                }).limit(3).forEach(p-> System.out.println(p.getName()));

                System.out.println("");

                System.out.println("女性寿命最高：");
                list.stream().sorted(new Comparator<first>() {
                    @Override
                    public int compare(first o1, first o2) {

                        return (o2.getDeath()-o2.getBirth())-(o1.getDeath()-o1.getBirth());
                    }
                }).filter(p->p.getSex().equals("女")).limit(3).forEach(p-> System.out.println(p.getName()));

                System.out.println("");

                System.out.println("武力排名前三（2）：");
                List<first> jiangs = list.stream().sorted(new Comparator<first>() {
                    @Override
                    public int compare(first o1, first o2) {
                        return o2.getPower() - o1.getPower();
                    }
                }).collect(Collectors.toList());
                int count=3;
                ListIterator<first> iterator = jiangs.listIterator();
                while (iterator.hasNext()){
                    first next = iterator.next();
                    if(count==0){
                        first next1 = iterator.previous();
                        if(next.getPower()==next1.getPower()){
                            System.out.println(next.getName());
                            count--;
                        }else {
                            break;
                        }
                    }else if(count==3|count==2|count==1){
                        System.out.println(next.getName());
                        count--;
                    }else
                        break;
                }

                System.out.println("");

                System.out.println("按年龄段分组：");
                ArrayList<first> list1 = new ArrayList<first>();
                ArrayList<first> list2 = new ArrayList<first>();
                ArrayList<first> list3 = new ArrayList<first>();
                ArrayList<first> list4 = new ArrayList<first>();
                list.stream().forEach(p->{
                    if((p.getDeath()-p.getBirth())<=20){
                        list1.add(p);
                    }else if((p.getDeath()-p.getBirth())<=40){
                        list2.add(p);
                    }else if((p.getDeath()-p.getBirth())<=60){
                        list3.add(p);
                    }else {
                        list4.add(p);
                    }
                });
                System.out.println("0~20："+list1);
                System.out.println("21~40："+list2);
                System.out.println("41~60："+list3);
                System.out.println("60以上："+list4);

                System.out.println("");

                System.out.println("按武力段分组：");
                ArrayList<first> power1 = new ArrayList<first>();
                ArrayList<first> power2 = new ArrayList<first>();
                ArrayList<first> power3 = new ArrayList<first>();
                ArrayList<first> power4 = new ArrayList<first>();
                list.stream().forEach(p->{
                    if(p.getPower()<70){
                        power1.add(p);
                    }else if(p.getPower()<80){
                        power2.add(p);
                    }else if(p.getPower()<90){
                        power3.add(p);
                    }else {
                        power4.add(p);
                    }
                });
                System.out.println("小于70：："+power1);
                System.out.println("70~79："+power2);
                System.out.println("80~89："+power3);
                System.out.println("90以上："+power4);

            } catch (IOException e) {
                e.printStackTrace();
            }
        }
    }
