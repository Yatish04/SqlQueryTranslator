	import java.util.*;

public class Conditionals {

	//colname->[condition]
	//condition->[data]

	public static String process(Map<String,String> k,String[] arr,String colname){
		//this method converts it into valid statement for next step by inserting column names
		//sample input->  city->["bangalore","berlin"],"bangalore"->[equals],"berlin"->"not equals"
		//sample output-> city equals bangalore and city not equals berlin
		//default value is equals
		//MAP is the data map created in the main
		String res="";//result
		int i=0;
		int length=arr.length;
		for (String data:arr) {
			i++;
			if(k.containsKey(data)){
				String  temp=k.get(data);
				temp.toString();
				res=res+" "+colname+" "+temp+data+" ";
			}
			else{
				res=res+" "+colname+" equals "+data+" ";
			}
			if(length!=i)
				res+="and ";
		}
		return res;
	}

	public static String stmntgenerator(Map<String,String[]> m,Map<String,String> data){
		String res="";
		List<String> listofcolumns=new ArrayList<>();
		Iterator<String> iter1=m.keySet().iterator();
		while (iter1.hasNext()){
			listofcolumns.add(iter1.next());
		}
		String[] t;
		Iterator<String> iter=m.keySet().iterator();
		int i=0;
		while (iter.hasNext()){
			String arrayname=iter.next();
			String[] array =m.get(arrayname);
			if(!arrayname.equals("comparitives")){
				//this is implicit joining using conjunctions
				System.out.println(arrayname);
				if(i==0)
				{res=process(data,array,arrayname);
					i++;}
				else
					res=res+" and "+process(data,array,arrayname);
				//else {
				// TODO: this part should handle different relations between different columns
				// TODO:this part can be handled later
				//
				// }


			}
			//System.out.println(arrayname+"\n");
			//System.out.println(colname);

			// System.out.println(arrayname);


		}
		System.out.println(res);

		return res;
	}

	public  static String conditions (String cond) {
		//todo:Identify the <,>,=,not,etc operations and apply accordingly
    	/* cond hold the condition that has to be altered in the where clause.
    	Converting it to a character symbol*/

		String parts[] = cond.split(" ");//Maximum of 3 tokens eg. greater than equal
		//System.out.println(parts);
		for (String c:parts
				) {
			System.out.println(c);
		}
		String value="";
		String oreq="or equals";
		int l=parts.length;

		for (String inp:parts)
		{
			if(inp.equalsIgnoreCase("than")||inp.equalsIgnoreCase("or")||inp.equalsIgnoreCase("to")){
				//skip all meaningless word in the relation part
				continue;
			}
			else if(inp.equalsIgnoreCase("not")){
				//if inp is not then ! Similarly to all other cases
				value+='!'+" ";
			}
			else if(inp.equalsIgnoreCase("greater")||inp.equalsIgnoreCase("more")||inp.equalsIgnoreCase("above")||inp.equalsIgnoreCase("over")){
				value+='>'+" " ;
			}
			else if(inp.equalsIgnoreCase("lesser")||inp.equalsIgnoreCase("less")||inp.equalsIgnoreCase("below")||inp.equalsIgnoreCase("under")){
				value+='<'+" ";
			}
			else if(inp.equalsIgnoreCase("equal")||inp.equalsIgnoreCase("is")||inp.equalsIgnoreCase("equals")||inp.equalsIgnoreCase("in")){
				value+='='+" " ;
			}
			else if (inp.equalsIgnoreCase("and")){
				value+="AND"+" ";
			}

			else {

				value+=inp+" ";
			}

		}

		//Return the symbol that was converted.
		return value;
	}//Testing purposes only


	public static void main(String [] args)
	{

		//map is for only colnames->[data]
		//data is only for individual data and their RELATION in the sentence

		Map<String,String[]> map=new HashMap<>();
		Map<String,String> data=new HashMap<>();
		String[] arr={"100"};

		map.put("value",arr);

		String arr1[]={"200"};
		map.put("Cost",arr1);
		//System.out.print((map)+"The mapis\n\n");
		data.put("100","greater than ");//make sure of the spacings-means greater than 100
		data.put("200","less than equal ");
		//System.out.print((data)+"The mapis\n\n");
		String[] condition={"none"};
		//work on condition which are the relations which are there in sentence
		//TODO:After the basic proto type

		String s="";
		map.put("comparitives",condition);
		//System.out.print((map)+"The mapis\n\n");
		s=stmntgenerator(map,data);
		String value=""+conditions(s);
		System.out.println(s);
		System.out.println("WHERE "+ value);
	}

}
