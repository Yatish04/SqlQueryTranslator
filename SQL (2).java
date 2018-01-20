 import java.util.*;

public class SQL {
    public static void DisplayQuery(HashMap<String ,ArrayList<String>> map){
        ArrayList<String> al1,al2,al3,al4 = new ArrayList<String>();
        al1=map.get("t_cstmrs");
        System.out.println("t_cstmrs contains "+al1);
        al1=map.get("t_colours");

        System.out.println("t_colours contains"+al1);

        al1= map.get("t_grphy");
        System.out.println("t_grphy contains "+al1);

        al1=map.get("t_prdcts");
        System.out.println("t_prdcts contains "+al1);
        // al2.clear();


    }

    public static void main(String[] args) {
        HashMap<String,ArrayList<String>>hashmap = new HashMap<String, ArrayList<String>>();
        ArrayList<String> al1 = new ArrayList<String>();
        ArrayList<String> al2 = new ArrayList<String>();
        ArrayList<String> al3 = new ArrayList<String>();
        ArrayList<String> al4 = new ArrayList<String>();
        String cust[]=new String[]{"Shubham","Tanmay","Yatish","Prathika","Shashi","Abhiram"};
        al1.addAll(Arrays.asList(cust));

        hashmap.put("t_cstmrs",al1);
        String loc[]=new String[]{"India","Bangalore","Canada","Tokyo","California",
                "Chicago","Tumkur","France","Germany"};

        al2.addAll(Arrays.asList(loc));
        hashmap.put("t_grphy",al2);


        String clrs[]=new String[] {"red","blue","yellow","black"};
        al3.addAll(Arrays.asList(clrs));
        hashmap.put("t_colours",al3);
        String prdcts[]=new String[]{"mountain bikes","T-shirts","rolls royce phantom","colour pencils","playing cards"
        ,"Hamburgers"};
        al4.addAll(Arrays.asList((prdcts)));
        hashmap.put("t_prdcts",al4);
        DisplayQuery(hashmap);

    }

}