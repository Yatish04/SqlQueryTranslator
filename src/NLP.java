

import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;

public class NLP {
	static HashMap<String,ArrayList<String>>m;
	
    public static void DisplayQuery(HashMap<String ,ArrayList<String>> map) {
        ArrayList<String> al1, al2, al3, al4, al5 = new ArrayList<String>();

        al1 = map.get("customers");
        System.out.println("customers contains   " + al1);

        al2 = map.get("product");
        System.out.println("colors contains   " + al2);

        al3 = map.get("geography");
        System.out.println("georaphy contains   " + al3);

        al4 = map.get("productcategory");
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

            String inp="Name MaritalStatus Gender EmailAddress YearlyIncome TotalChildren intChildrenAtHome Education Occupation CarsOwned Phone";

            String cust[]=inp.toLowerCase().split(" ");
        al1.addAll(Arrays.asList(cust));
        m.put("customers",al1);

        String inp1="city state country";
        String loc[]=inp1.toLowerCase().split(" ");
        al2.addAll(Arrays.asList(loc));
        m.put("location",al2);

        String inp2="ProductCategoryName";
        String clrs[]=inp2.toLowerCase().split(" ");
        al3.addAll(Arrays.asList(clrs));
        m.put("productcategory",al3);

        String inp3="ProductName StandardCost Color SafetyStockLevel ReorderPoint ListPrice Description";
        String clths[]=inp3.toLowerCase().split(" ");
        al4.addAll(Arrays.asList(clths));
        m.put("product",al4);

        String inp4="Order DateShip DateOrder Quantity UnitPrice UnitPrice DiscountPct DiscountAmount ProductStandardCost SalesAmount TotalProductCost";
        String sales[]=inp4.toLowerCase().split(" ");
        al5.addAll(Arrays.asList(sales));
        m.put("sales",al5);

        //DisplayQuery(m);

        }

}