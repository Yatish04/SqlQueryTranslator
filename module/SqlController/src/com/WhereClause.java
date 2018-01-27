package com;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.*;

public class WhereClause {
	public String finalstring;
	//colname->[condition]
	//condition->[data]

    public WhereClause(Map<String,String[]> map,Map<String,String> data,Map<String,String> conditions) throws  IOException{
        String[] arr = {"mumbai","bangalore"};
        if(map==null){
            this.finalstring="";
            return;
        }
        map.put("city", arr);

        //System.out.print((map)+"The mapis\n\n");
        data.put("mumbai", "is ");//make sure of the spacings-means greater than 100
        //data.put("bangalore", "is ");
        //System.out.print((data)+"The mapis\n\n");
        conditions.put("city","or");
        //work on condition which are the relations which are there in sentence
        //TODO:After the basic proto type

        String s = "";


        s = stmntgenerator(map, data,conditions);
        String value = "" + conditions(s);

        System.out.println("WHERE " + value);
        this.finalstring="WHERE " + value;

    }



	public static String process(Map<String, String> k, String[] arr, String colname,Map<String,String> conditions) {
		//this method converts it into valid statement for next step by inserting column names
		//sample input->  city->["bangalore","berlin"],"bangalore"->[equals],"berlin"->"not equals"
		//sample output-> city equals bangalore and city not equals berlin
		//default value is equals
		//MAP is the data map created in the main
		String res = "";//result
		String condition=conditions.get(colname);
		System.out.println(condition);
		int i = 0;
		int length = arr.length;
		for (String data : arr) {
			i++;
			if (k.containsKey(data)) {
				String temp = k.get(data);
				res = res + " " + colname + " " + temp + data + " ";
			}
			else {
				res = res + " " + colname + " equals " + data + " ";
			}
			if (length != i) {
				if(condition==null){
					res+=" AND ";
					continue;
				}
				res += condition;
			}
		}
		return res;
	}

	public static String stmntgenerator(Map<String, String[]> m, Map<String, String> data,Map<String,String> conditions) {
		//m contains column name->data
		String res = "";
		List<String> listofcolumns = new ArrayList<>();
		Iterator<String> iter1 = m.keySet().iterator();
		while (iter1.hasNext()) {
			listofcolumns.add(iter1.next());
		}
		String[] t;
		Iterator<String> iter = m.keySet().iterator();
		int i = 0;
		while (iter.hasNext()) {
			String arrayname = iter.next();
			String[] array = m.get(arrayname);
			//this is implicit joining using conjunctions
			System.out.println(arrayname);
			if (i == 0)
			{
				res = process(data, array, arrayname,conditions);
				i++;
			} else
				res = res + " and " + process(data, array, arrayname,conditions);
			//else {
			// TODO: this part should handle different relations between different columns
			// TODO:this part can be handled later
			//
			// }


		}

		System.out.println(res);

		return res;
	}

	public static String conditions(String cond) throws IOException {
		//todo:Identify the <,>,=,not,etc operations and apply accordingly
    	/* cond hold the condition that has to be altered in the where clause.
    	Converting it to a character symbol*/

		String parts[] = cond.split(" ");//Maximum of 3 tokens eg. greater than equal
		//System.out.println(parts);
		for (String c : parts
				) {
			System.out.println(c);
		}
		String value = "";
		int l = parts.length;
		List<String> great = Files.readAllLines(Paths.get("Greater.txt"));
		List<String> less = Files.readAllLines(Paths.get("Lesser.txt"));
		List<String> equal = Files.readAllLines(Paths.get("Equals.txt"));
		List<String> not = Files.readAllLines(Paths.get("Not.txt"));
		for (String inp : parts) {
			if (inp.equalsIgnoreCase("than") || inp.equalsIgnoreCase("to")) {
				//skip all meaningless word in the relation part
				continue;
			} else if (not.containsAll(Collections.singleton(inp))) {
				//if inp is not then ! Similarly to all other cases
				value += '!' + " = "+" ";
			} else if (great.containsAll(Collections.singleton(inp))) {
				value += '>' + " ";
			} else if (less.containsAll(Collections.singleton(inp))) {
				value += '<' + " ";
			} else if (equal.containsAll(Collections.singleton(inp))) {
				value += '=' + " ";
			} else if (inp.equalsIgnoreCase("and")) {
				value += "AND" + " ";
			}
			else  if(inp.equalsIgnoreCase("or")){
				value+= " OR ";
			}
			else {

				value += inp + " ";
			}

		}

		//Return the symbol that was converted.
		return value;
	}//Testing purposes only


}