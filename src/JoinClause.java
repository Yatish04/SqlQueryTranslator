public class JoinClause {

    public String explicitjoin(Map<Integer,String>m,int key){

        //todo:this handles the joins of tables explicitely mentioned in the sentence
    	
    	String res="INNER JOIN ";
    	String val="";
    	if(m.containsKey(key)) {
       	 val=m.get(key);//Table name
       	}
    	res=res+val;   	
    	/*
    	 * Call On clause to add the rest of the conditions.
    	 */
    	
    	return res;
    	
    }

    public String implicitjoin(Map<Integer,String>m,int key,Map<Integer,String>n,int key2){
        //todo:join operation(inner join,etc)
        //todo:this joins the tables which are not explicitely mentioned in the sentence
    	/*
    	 * Call where class and pass the two table names
    	 */
    	
    }

}
