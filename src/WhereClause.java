public class WhereClause {
    public static String whereClause(String str){
        //todo:Where part of the query
    	/*Checks the lines after the WHERE clause to load into the SQL query*/
    	String parts[]=str.split(" ");
    	String sent="";//Holds the statement after the 'WHERE' clause.
    	sent.concat(parts[0]);//The first input is generally a column name.
    	/*The second part would contain >, <, = or some such thing.
    	 * Converting the text input to a symbol using the class Conditionals
    	 */
    	Conditionals c1=new Conditionals();
    	//int l=parts.length;
    	String s1=str.substring(str.indexOf(parts[0])+parts[0].length());
    	//System.out.println(s1+"\tIs the string");
    	s1=c1.conditions(s1);
    	/* Calling the conditions function from Conditionals class.
    	 * The class performs the checking of signs of symbols
    	 */
    	//System.out.println(s1+"\tIs the edited string");
    	
    	sent=" "+parts[0]+" " + s1;
    	return sent;
    }
    /*Testing purposes only
    public static void main(String [] args)
    {
    	String s=whereClause("Salary under 100000");
    	System.out.println(s);
    }*/
}
