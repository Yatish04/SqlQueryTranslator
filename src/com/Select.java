package com;

import java.io.IOException;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

public class Select {
    public String finalstring;
    public static String select(String s[]) {
        String s1 = "";
        int count = 0;
        if (s[0].equalsIgnoreCase(" "))
            return "";
        for (int i = 0; i < s.length; i++) {//System.out.println(s[i]);
            if (s[i] != null) {
                if (count == 0) {
                    s1 = s[i];
                    count++;
                } else {
                    s1 = s1 + "," + s[i];
                }

            }


        }

        return "SELECT " + s1;
    }

    public Select(String s) throws IOException {
        String s1 = s;
        //System.out.println(s);
        //String c="c_sales";
        XMLParser s6=new XMLParser();
        s6.input="Average";
        List<String> average=s6.xmlParser() ;
        s6.input="Sum";
        List<String> sum=s6.xmlParser() ;
        s6.input="Count";
        List<String> counts=s6.xmlParser() ;
        s6.input="maximum";
        List<String> maxwords=s6.xmlParser() ;
        s6.input="All";
        List<String> all=s6.xmlParser() ;
        s6.input="minimum";
        List<String> minwords=s6.xmlParser() ;
        s6.input="Select";
        List<String> l=s6.xmlParser() ;
        List<String> list = new ArrayList<String>();
        for (String w : s.split(" ")) {
            list.add(w.toLowerCase());

        }
        String col1[] = new String[20];
        col1[0] = " ";
        String col[] = s.split(" ");
        int j = 0;
        int count = 2;
        for (String inp:col) {
            if(maxwords.contains(inp)||minwords.contains(inp)||counts.contains(inp)||sum.contains(inp)||average.contains(inp)){
                this.finalstring="SELECT";
                return;
            }
        }
        for (int i = 0; i < col.length; i++) {
            if (col[i].contains("c_")) {
                col1[j++] = "" + col[i];
                count = 1;
            }

        }
        for (String t : all) {
            for (int i = 0; i < col.length; i++) {
                if (t.contains(col[i]) && count == 1) {//all is present
                    col[i] = "";
                    count = 3;
                    // col1[0]="";
                    break;
                }
                //else count=2;
            }
        }
        if (col1[0].isEmpty())
            col1[0] = " ";

        for (String t : all) {
            for (int i = 0; i < col.length; i++) {
                if (count == 2 && t.contains(col[i]))//c_doesnt exist
                {
                    col1[0] = "*";
                    for (int k = 1; k < col1.length; k++)
                        col1[k] = null;
                }
            }
        }
            /*for (int i=0;i<col.length;i++){

             if((col[i].contains("all")||col[i].contains("every")||col[i].contains("complete")||col[i].contains("each"))&&count==0)
                {   col[i]="";
                    break;}
            }*/

        //System.out.println(col1);
        //System.out.println(list);
        String s2 = "";
        for (String t : list)
        {
            if (l.containsAll(Collections.singleton(t))) {
                s2 = select(col1);
                break;
            }

        }

//        System.out.println("\n\n\n");
 //       System.out.print(s2 + " ");
this.finalstring=s2+" ";

    }
}