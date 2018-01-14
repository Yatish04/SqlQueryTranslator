import java.util.*;

import com.sun.org.apache.xerces.internal.dom.PSVIDOMImplementationImpl;
public class FromClause {

    public static String fromClause(Map<Integer,String> m,int key){
        //todo:From clause completion
    	
    	String res="FROM";
    	/*
    	 * The map m holds the key value pair of a primary key and its respective table name
    	 * Each table name is to have a specific key in a hash map. Referring to that map, we
    	 * can attach a table name to the FROM clause
    	 * t_customers->geography (foreign key)
    	 * geography->t_geography table
    	 * t_geography table has column state_province ->California and Berlin
    	 * key hold local key
    	 * This performs only the primary key mapping of the table hash set as of now.
       	 */
    	String val="";
    	if(m.containsKey(key)) {
    	 val=m.get(key);
    	}
    	
    	
    	System.out.println("Value="+val);    	
    	res=res+" "+val;    	
    	return res;
    }/*For testing purposes only
    public static void main(String []args)
    {
    	 Map<Integer,String> map=new HashMap<Integer,String>();  
    	 map.put(1, "custmr");
    	 map.put(2,"geography");
    	 map.put(3, "sales");
    	 map.put(4,"weaponry");
    	 
    	 String res=fromClause(map,3);
    	 System.out.println(res);
    }*/

}
