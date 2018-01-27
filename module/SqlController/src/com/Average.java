package com;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

public class Average {
    public String finalstring;
    static String avg(String c)
    {    /*
            Finding the average of all elements in the column c_sales
         */
        return "AVG("+c+")";
    }
    public Average(String s1) throws IOException {

        String s = s1;
        //String c="c_sales";
        List<String> l = Files.readAllLines(Paths.get("Average.txt"));
        List<String> list=new ArrayList<String>();
        for (String w : s.split(" ")) {
            list.add(w.toLowerCase());

        }String col1="";
        String col[]=s.split(" ");
        int count=0;
        for (int i=0;i<col.length;i++)
        {
            if(col[i].contains("c_"))
            {   count=1;
                col1=""+col[i];
                break;
            }
        }

        // System.out.println(col1);
        //System.out.println(list);

        for (String t:list)
        {
            if(l.containsAll(Collections.singleton(t))&&count==1)
            {s=avg(col1);
                break;}
            else
                s="";


        }
        this.finalstring=s1;

    }
}
