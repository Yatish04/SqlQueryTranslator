import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.*;

public class Minmax {
    public static String max(String []input ,Set<String> columnnames) throws IOException{
        List<String> maxwords = Files.readAllLines(Paths.get("maximum.txt"));
        List<String> minwords = Files.readAllLines(Paths.get("minimum.txt"));
        String res="";
        for (String iter:
            input ) {

            if(maxwords.containsAll(Collections.singleton(iter))){
                res=res+" MAX(";

            }
            if(minwords.containsAll(Collections.singleton(iter))){
                res=res+" MIN(";

            }

            if(columnnames.contains(iter)&&res.length()>0){

                res=res+iter+"),";
            }

        }
        return res;

    }


    public static void main(String[] args) throws IOException {


        //keywords to look for- minimum, maximum else return ""

        String []s={"Select","product","with","minimum","price"};
        Set<String> temp=new HashSet<>();
        temp.add("product");
        temp.add("price");
        System.out.println(max(s,temp));

    }

}
