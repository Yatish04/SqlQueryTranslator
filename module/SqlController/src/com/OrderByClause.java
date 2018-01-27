package com;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.*;

public class OrderByClause {
    public String finalstring;

    public  static String order(HashMap<String,String> map){
        Set<String> key=map.keySet();
        String res="";
        for (String i:
             key) {
            res=res+" "+i+" "+map.get(i)+" ";
        }
return res;

    }

   public static String[] preprocess(String [] process,ArrayList<String> columnlist) throws IOException{
      /*  reorder the sentence
        accordingly in case of exception cases*/

        List<String> others =Files.readAllLines(Paths.get("others.txt"));

        int n=process.length;
        int i;
        String temp;
        String temp2;
        Map<String,Integer> strmap=new HashMap<String, Integer>();
        Stack<String> columns=new Stack<>();
        int pos;
        for (i=0;i<n;i++) {
            strmap.put(process[i],i);
            if(others.contains(process[i])){
                temp=process[i];
                temp2=columns.pop();
                pos=strmap.get(temp2);
                process[i]=process[pos];
                process[pos]=temp;
            }
            if(columnlist.contains(process[i])){
                columns.push(process[i]);
            }
        }
       return  process;
    }


    public static String orderby(String[] input, ArrayList<String> columns) throws IOException{
        String main_string="ORDER BY ";
        HashMap<String,String> map=new HashMap<>();
        List<String> ascending = Files.readAllLines(Paths.get("ascending.txt"));
        List<String> descending =Files.readAllLines(Paths.get("descending.txt"));


        boolean asc=true;
        boolean des=true;
        for (String s: input) {
            if(ascending.containsAll(Collections.singleton(s))&&asc){
                //System.out.println("inside "+s);
                des=false;
                asc=true;

            }
            if (descending.containsAll(Collections.singleton(s))&&des){
                //System.out.println("inside "+s);
                des=true;
                asc=false;
            }
            if(columns.contains(s)){
                if(asc==false){
                    map.put(s,"DESC");
                    asc=true;
                }
                if(des==false){

                    map.put(s,"ASC");
                    des=true;
                }

            }


        }

        //System.out.println(map.keySet());
        //System.out.println(asc);
        //System.out.println(des);
        //System.out.println(map.values());
        //System.out.println("map is"+order(map));
        if(order(map)=="")
            return "";

return main_string+order(map);
    }


    public OrderByClause(String original,ArrayList<String> columnlist) throws IOException {
        String[] arr =original.split(" ");


        int itr;

        //ArrayList<String> temp = new ArrayList<>();

        //temp.add("country");
        //temp.add("name");

        String[] s=preprocess(arr,columnlist);
        for (String i: s) {
            System.out.print(i+" ");
        }
        String si=orderby(s,columnlist);
        if(si==""){
            this.finalstring="";
        }
        else {
            this.finalstring=si;
        }
    }

}
