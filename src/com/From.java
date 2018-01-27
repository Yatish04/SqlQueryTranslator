package com;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

public class From {

    public String finalstring;
    public static String from(String s) {
        if (s.isEmpty())
            return "";

        return "FROM "+s;
    }

    public From(String s1) throws IOException {
        String s = s1;
        //System.out.println("The string entered is "+s);
        //String c="c_sales";
        List<String> l1 = Files.readAllLines(Paths.get("From.txt"));
        List<String> list1=new ArrayList<String>();
        for (String w : s.split(" ")) {
            /*if(w.contains("the")||w.contains("of"))//Can add to this later.
            {
                list1.add("");
            }
            else*/
            list1.add(w.toLowerCase());

        }String col1="";
        String col[]=s.split(" ");
        for (int i=0;i<col.length;i++)
        {
            if(col[i].contains("t_"))
            {
                col1=""+col[i];//As of now col1 is a single string or single table
                break;
            }
        }

        //System.out.println(col1);
        //System.out.println(list);
        String s2="";
        for (String t:list1)
        {
            //System.out.println(t+"Test");
            if(l1.containsAll(Collections.singleton(t)))
            { s2=from(col1);
                //System.out.println("Hello"+s);

                break;
            }

        }
        this.finalstring=s2;

    }
}

