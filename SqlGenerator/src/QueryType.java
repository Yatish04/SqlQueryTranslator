public class QueryType {

    public static String typeOfQuery(String query){

        //todo:identify the type of query
    	/*
    	 * Select clause checks the query entered. Find the column from a specified table
    	 * or if multiple columns are asked for it generates them. 
    	 */
    	String res="";
    	String parts[]=query.split(" ");
    	String first=parts[0];
    	if(first.equalsIgnoreCase("show")||first.equalsIgnoreCase("list")||first.equalsIgnoreCase("give"))
    	{
    		res="SELECT ";
    	}
    	/*
    	 * Anything entered after this is a column name
    	 */
    	if(parts[1].equalsIgnoreCase("all")||parts[1].equalsIgnoreCase("every"))
    	{
    		res=res +"* ";
    	}
    	else {
    		//Only a selected set of columns are imported
    		for(int i=1;i<parts.length-1;i++)
    		{
    			if(parts[i].equalsIgnoreCase("and")||parts[i].equalsIgnoreCase(","))
    				continue;
    			res=res+parts[i]+", ";
    		}
    		res=res+parts[parts.length-1]+"";
    	}
    	
    	return res;

    }/*
    Testing Purposes only
    
    public static void main(String [] args)
    {
    	String s=typeOfQuery("Show customers and addresses");
    	System.out.println(s);
    	
    }*/
}
