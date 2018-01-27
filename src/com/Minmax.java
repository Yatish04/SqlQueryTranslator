package com;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.*;

public class Minmax {

    public String finalstring;
    public static String[] preprocess(String input) throws IOException{
        String[] process=new String[20];
        int i=0;
        String[] temp=input.split(" ");
        List<String> otherwords=Files.readAllLines(Paths.get("maxwords.txt"));
        List<String> minwords=Files.readAllLines(Paths.get("minwords.txt"));
        for (String inp:temp) {
            if(otherwords.contains(inp)){
                process[i++]="maximum";
                process[i++]="price";
            }
            else if(minwords.contains(inp)){
                process[i++]="minimum";
                process[i++]="price";
            }
            else {
                process[i++]=inp;
            }
        }



        return process;
    }

    public static String max(String []input ,Set<String> columnnames) throws IOException{
        List<String> maxwords = Files.readAllLines(Paths.get("maximum.txt"));
        List<String> minwords = Files.readAllLines(Paths.get("minimum.txt"));
        String res="";

        boolean switc=false;
        for (String iter: input ) {

            if(maxwords.containsAll(Collections.singleton(iter))){
                res=res+" MAX(";
                switc=true;
            }
            if(minwords.containsAll(Collections.singleton(iter))){
                res=res+" MIN(";
                switc=true;
            }

            if(columnnames.contains(iter)&&res.length()>0&&switc){

                res=res+iter+")";
                switc=false;
            }

        }
        return res;

    }


    public Minmax(String input,ArrayList<String> collist) throws IOException {

        //keywords to look for- minimum, maximum else return ""
        //String sq="finds the price of the most expensive product";

        Set<String> temp=new HashSet<>();

        for (String inp:collist) {
            temp.add(inp);
        }
        //temp.add("price");
        //temp.add("product");
        String [] s=preprocess(input);
        this.finalstring=max(s,temp);

    }

}
