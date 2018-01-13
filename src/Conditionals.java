public class Conditionals {

    public static String conditions (String cond) {
        //todo:Identify the <,>,=,not,etc operations and apply accordingly
    	/* cond hold the condition that has to be altered in the where clause. 
    	Converting it to a character symbol*/
    	String parts[] = cond.split(" ");//Maximum of 3 tokens eg. greater than equal
    	String value="";
    	String oreq="or equals";
    	int l=parts.length;
    	if(parts[0].equalsIgnoreCase("greater")||parts[0].equalsIgnoreCase("more")||parts[0].equalsIgnoreCase("above")||parts[0].equalsIgnoreCase("over"))//If true
    	{//Greater, more, above,over are typical comparative inputs. Others may be added.
    		if(l>1&&parts[1].equalsIgnoreCase("than"))
    		{
    			if(l>2&&oreq.contains(parts[2].toLowerCase()))
    			{
    				//if(l>3&&parts[3].equalsIgnoreCase("to")) Optional
    					value=""+">=";// Greater than equal to.
    					
    			}
    			else {
    				value=""+">";//Only greater than
    			}
    		}//'than' is not a suffix for above so skipping that check
    		else {
    			if(l>1&&oreq.contains(parts[1].toLowerCase()))
    			{
    				//if(l>2&&parts[2].equalsIgnoreCase("to")) Optional
    					value=""+">=";// Greater than equal to.
    					
    			}
    			else {
    				value=""+">";//Only greater than
    			}
    		}
    	}
    	else if(parts[0].equalsIgnoreCase("lesser")||parts[0].equalsIgnoreCase("less")||parts[0].equalsIgnoreCase("below")||parts[0].equalsIgnoreCase("under"))//If true
    	    	{//Lesser, less, below, under. Others may be added later.
    	    		if(l>1&&parts[1].equalsIgnoreCase("than"))
    	    		{
    	    			if(l>2&&oreq.contains(parts[2].toLowerCase()))
    	    			{
    	    				//if(l>3&&parts[3].equalsIgnoreCase("to")) Optional
    	    					value=""+"<=";// Lesser than equal to.
    	    					
    	    			}
    	    			else {
    	    				value=""+"<";//Only lesser than
    	    			}
    	    		}//'than' is not a suffix for below so skipping that check
    	    		else {
    	    			if(l>1&&oreq.contains(parts[1].toLowerCase()))
    	    			{
    	    				//if(l>2&&parts[2].equalsIgnoreCase("to")) Optional
    	    					value=""+"<=";// Greater than equal to.
    	    					
    	    			}
    	    			else {
    	    				value=""+"<";//Only greater than
    	    			}
    	    		}
    	    	}
    	else if(oreq.contains(parts[0].toLowerCase()))
    	{
    		value=""+"=";
    	}
    	
    	//Checking if a value is attached at the end of the string
    	if(Integer.parseInt(parts[l-1])>=0&&Integer.parseInt(parts[l-1])<=10000000)//Assuming this is a fair upper bound
    			value+=" "+Integer.parseInt(parts[l-1]);
    	//Return the symbol that was converted.
    	return value;
    }/*Testing purposes only
    public static void main(String [] args)
    {
    	String value=""+conditions("under 5000");
    	System.out.println(value);
    }*/

}
