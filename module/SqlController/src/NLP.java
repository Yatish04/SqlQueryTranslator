

import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;

public class NLP {
	static HashMap<String,ArrayList<String>>m;
	
    public static void DisplayQuery(HashMap<String ,ArrayList<String>> map) {
        ArrayList<String> al1, al2, al3, al4, al5 = new ArrayList<String>();

        al1 = map.get("customers");
        System.out.println("customers contains   " + al1);

        al2 = map.get("colors");
        System.out.println("colors contains   " + al2);

        al3 = map.get("geography");
        System.out.println("georaphy contains   " + al3);

        al4 = map.get("clothes");
        System.out.println("clothes contains   " + al4);

        al5 = map.get("sales");
        System.out.println("sales contains   " + al5);


     }
        public static void databasecreate()
        {
           m = new HashMap<String, ArrayList<String>>();
            ArrayList<String> al1 = new ArrayList<String>();
            ArrayList<String> al2 = new ArrayList<String>();
            ArrayList<String> al3 = new ArrayList<String>();
            ArrayList<String> al4 = new ArrayList<String>();
            ArrayList<String> al5 = new ArrayList<String>();

        String cust[]=new String[]{"management","technical","marketing","accounting","sales"};
        al1.addAll(Arrays.asList(cust));
        m.put("customers",al1);

        String loc[]=new String[]{"India","Bangalore","Canada","Tokyo","California",
                "Chicago","Korea","France","Germany"};

        al2.addAll(Arrays.asList(loc));
        m.put("location",al2);

        String clrs[]=new String[] {"red","blue","yellow","black","pink","green","orange","purple","white"};
        al3.addAll(Arrays.asList(clrs));
        m.put("colors",al3);

        String clths[]=new String[]{"shirt", "trousers", "jacket", "socks", "blouse", "t-shirt", "sweater", "pants", "shoes"};
        al4.addAll(Arrays.asList(clths));
        m.put("clothes",al4);

        String sales[]=new String[]{"$100,000", "$100,250", "$100,500", "$100,750", "$200,000", "$200,250", "$200,500", "$200,750", "$300,000"};
        al5.addAll(Arrays.asList(sales));
        m.put("sales",al5);

     //   DisplayQuery(m);

        }

}