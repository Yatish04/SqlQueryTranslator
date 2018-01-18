import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
import java.util.Map;

public class NLP {

    public static void DisplayQuery(HashMap<String ,ArrayList<String>> map) {
        ArrayList<String> al1, al2, al3, al4, al5 = new ArrayList<String>();

        al1 = map.get("t_cstmrs");
        System.out.println("t_cstmrs contains   " + al1);

        al2 = map.get("t_clrs");
        System.out.println("t_colours contains   " + al2);

        al3 = map.get("t_grphy");
        System.out.println("t_georaphy contains   " + al3);

        al4 = map.get("t_clths");
        System.out.println("t_clothes contains   " + al4);

        al5 = map.get("t_sales");
        System.out.println("t_sales contains   " + al5);


    }
        public static void main(String[] args) {
        HashMap<String,ArrayList<String>>m = new HashMap<String, ArrayList<String>>();
            ArrayList<String> al1 = new ArrayList<String>();
            ArrayList<String> al2 = new ArrayList<String>();
            ArrayList<String> al3 = new ArrayList<String>();
            ArrayList<String> al4 = new ArrayList<String>();
            ArrayList<String> al5 = new ArrayList<String>();

        String cust[]=new String[]{"Deepamala","Shobha","Chandra","Shubham","Tanmay","Yatish","Prathika","Shashi","Abhiram"};
        al1.addAll(Arrays.asList(cust));
        m.put("t_cstmrs",al1);

        String loc[]=new String[]{"India","Bangalore","Canada","Tokyo","California",
                "Chicago","Korea","France","Germany"};

        al2.addAll(Arrays.asList(loc));
        m.put("t_grphy",al2);

        String clrs[]=new String[] {"red","blue","yellow","black","pink","green","orange","purple","white"};
        al3.addAll(Arrays.asList(clrs));
        m.put("t_clrs",al3);

        String clths[]=new String[]{"shirt", "trousers", "jacket", "socks", "blouse", "t-shirt", "sweater", "pants", "shoes"};
        al4.addAll(Arrays.asList(clths));
        m.put("t_clths",al4);

        String sales[]=new String[]{"$100,000", "$100,250", "$100,500", "$100,750", "$200,000", "$200,250", "$200,500", "$200,750", "$300,000"};
        al5.addAll(Arrays.asList(sales));
        m.put("t_sales",al5);

        DisplayQuery(m);

        }

}
