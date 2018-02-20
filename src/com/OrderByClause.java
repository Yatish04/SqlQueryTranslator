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

       XMLParser s6=new XMLParser();
       s6.input="others";

       List<String> others=s6.xmlParser() ;

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

        String curr_leftcol="";
        String curr_cond="";
        int left_dist=0;
        int right_dist=0;
        boolean isleft=false;
        boolean isright=false;
        for (String s: input) {

            if (columns.contains(s)) {

                if (!isright) {
                    curr_leftcol = s;
                    left_dist = 0;
                    isleft = true;
                    continue;
                }

                else {
                    System.out.println(isleft);
                    System.out.println(isright);
                    System.out.println(curr_cond+curr_leftcol);

                    if (left_dist==0||right_dist < left_dist) {
                        isleft = false;
                        isright = false;
                        map.put(s, curr_cond);
                        curr_cond="";
                        continue;
                    } else {
                        System.out.println("hello");
                        left_dist = 0;
                        isleft = true;
                        map.put(curr_leftcol, curr_cond);
                        curr_cond="";
                        continue;
                    }
                }
            }


            if (ascending.contains(s)) {
                curr_cond = " ASC ";
                isright = true;
                right_dist=0;
                isleft = false;
                continue;
            }
            if (descending.contains(s)) {
                curr_cond = " DESC ";
                isleft = false;
                right_dist=0;
                isright = true;
                continue;
            }
            if (isleft)
                left_dist++;
            if (isright)
                right_dist++;
        }
        if(curr_cond!=""){
            if(curr_leftcol!=""){
                map.put(curr_leftcol,curr_cond);
            }
        }
            System.out.println(map.keySet());
            //System.out.println(asc);
            //System.out.println(des);
            System.out.println(map.values());
            //System.out.println("map is"+order(map));

            if (order(map) == "")
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

    public static void main(String[] args) throws IOException {

        ArrayList<String> temp = new ArrayList<>();

        temp.add("yearlyincome");
        temp.add("age");
        String org=" customers in ascending order of yearlyincome and age by descending order ";
        OrderByClause ob = new OrderByClause(org,temp);
        System.out.println(ob.finalstring);
    }

}
