import com.*;
import opennlp.tools.postag.POSModel;
import opennlp.tools.postag.POSTaggerME;
import opennlp.tools.tokenize.Tokenizer;
import opennlp.tools.tokenize.TokenizerME;
import opennlp.tools.tokenize.TokenizerModel;

import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.HashSet;
import java.util.Set;

public class POSTaggerExample
{
    public static  String tablename="";
    public static String sentence;
    public static String cleaned2;
    public static int idfrombigram=-1;
    static ArrayList<String> tablenameslist=new ArrayList<>();
    static ArrayList<String> datalist=new ArrayList<>();
    static ArrayList<String> columnnameslist=new ArrayList<>();
    static HashMap<String,String>sentenceparser=new HashMap<>();
    static HashMap<String,ArrayList<String>> map=new HashMap<>();
    public static void mapper(String mapkey,String Item)
    {
        ArrayList<String> itemlist=map.get(mapkey);
        if(itemlist==null)
        {
            itemlist=new ArrayList<String>();
            itemlist.add(Item);
            map.put(mapkey, itemlist);

        }
        else
        {
            if(!itemlist.contains(Item))
            {
                itemlist.add(Item);
            }
        }
    }
    public static void main(String[] args)
    {
        InputStream tokenModelIn = null;
        InputStream posModelIn = null;


        try {
            sentence="get all th customers";
            tokenModelIn = new FileInputStream("en-token.bin");
            TokenizerModel tokenModel = new TokenizerModel(tokenModelIn);
            Tokenizer tokenizer = new TokenizerME(tokenModel);
            String tokens[] = tokenizer.tokenize(sentence);
            posModelIn = new FileInputStream("en-pos-maxent.bin");
            POSModel posModel = new POSModel(posModelIn);
            POSTaggerME posTagger = new POSTaggerME(posModel);
            String tags[] = posTagger.tag(tokens);
            double probs[] = posTagger.probs();

            System.out.println("Token\t:\tTag\t:\tProbability\n---------------------------------------------");
            for(int i=0;i<tokens.length;i++)
            {
                System.out.println(tokens[i]+"\t:\t"+tags[i]+"\t:\t"+probs[i]);
            }
            System.out.println("\n\n");

            for(int i=0;i<tags.length;i++)
            {
                if(tags[i].matches("NNS")||tags[i].matches("NNP")||tags[i].matches("NNPS")||tags[i].matches("JJ"))
                {
                    tags[i]="NN";
                }
                if(tags[i].matches("VBD")||tags[i].matches("VBG")||tags[i].matches("VBN")||tags[i].matches("VBP")||tags[i].matches("VBZ"))
                {
                    tags[i]="VB";
                }
                mapper(tags[i],tokens[i]);
            }
            ArrayList<String> nounlist=map.get("NN");
            System.out.println("The nouns identified are"+nounlist.toString());
            tablenameslist.clear();
            NLP n=new NLP();
            n.databasecreate();
            ArrayList<String> sampletablenames=new ArrayList<>();
            sampletablenames.addAll(n.m.keySet());
            tablenameslist.addAll(simplechecker(nounlist,sampletablenames));
            if(tablenameslist.size()==0)
            {

                ArrayList<String> temp=new ArrayList<>();
                temp=bigramchecker(nounlist,sampletablenames);

                if(temp.size()!=0)
                {
                    tablenameslist.add(temp.get(0));
                    System.out.println("Bigram check successful");

                }
                System.out.println("The detected tables are "+tablenameslist.toString());
                if(tablenameslist.size()==0)
                {
                    // Please call the hypernyms function here
                    temp=hypernymchecker(nounlist,sampletablenames);
                    if(temp.size()!=0)
                    {
                        tablenameslist.addAll(temp);
                        System.out.println("Hypernyms check successful");


                    }
                }
            }
            Set<String> tablenm=new HashSet<>();
            tablenm.addAll(tablenameslist);
            tablenameslist.clear();
            tablenameslist.addAll(tablenm);
            System.out.println("The detected tables are "+tablenameslist.toString());

            if(tablenameslist.size()>0)
            {
                ArrayList<String> temp=new ArrayList<>();
                ArrayList<String> temp1=new ArrayList<>();
                ArrayList<String> temp2=new ArrayList<>();
                ArrayList<String> temp3=new ArrayList<>();
                temp=columnfinder(tablenameslist.get(0));
                System.out.println("All columns in the detected table are "+temp.toString());
                System.out.println("The nouns remaining"+nounlist.toString());
                temp1=simplechecker(nounlist,temp);
                columnnameslist.clear();
                columnnameslist.addAll(temp1);
                if(nounlist.size()!=0)
                {
                    System.out.println("Bigram check for columns\n");
                    temp2=bigramchecker(nounlist, temp);
                    columnnameslist.addAll(temp2);
                    if(nounlist.size()!=0)
                    {
                        System.out.println("Hypernym check for columns\n");
                        temp3=hypernymchecker(nounlist,temp);
                        columnnameslist.addAll(temp3);
                        //hypernym check for columns
                        if(columnnameslist.size()==0)
                        {
                            System.out.println("Specific columns cannot be detected indicates a select all operation\n");
                        }
                    }


                }
            }
            else
            {
                System.out.println("Ask user to re-enter the query");
            }
            datalist.removeAll(columnnameslist);
            System.out.println("The detected columns are "+columnnameslist.toString());
            System.out.println("Datalist: "+datalist.toString());
            System.out.println(sentenceparser);
            System.out.println("the number of words to replace are " +sentenceparser.size());
            ArrayList<String> keyset=new ArrayList<String>();
            keyset.addAll(sentenceparser.keySet());
            cleaned2=sentence;
            for(int i=0;i<keyset.size();i++)
            {


                String key=keyset.get(i);
                String value=sentenceparser.get(keyset.get(i));
                System.out.println(key+"->"+value);

                if(value.matches(tablenameslist.get(0)))
                {
                    sentence=sentence.replaceAll(key,"t_"+value);
                }
                else
                {
                    sentence=sentence.replaceAll(key,"c_"+value);
                }

            }
            int i;

            for(i=0;i<keyset.size();i++)
            {


                String key=keyset.get(i);
                String value=sentenceparser.get(keyset.get(i));
                System.out.println(key+"->"+value);

                if(value.matches(tablenameslist.get(0)))
                {
                    cleaned2=cleaned2.replaceAll(key,value);
                }
                else
                {
                    cleaned2=cleaned2.replaceAll(key,value);
                }

            }


            System.out.println("\n\nINTEGRATION BEGINS FROM HERE\n\n");
            System.out.println(cleaned2);
            System.out.println(sentence);
            boolean context=false;
            String res="";
            Select s=new Select(sentence);
            res=s.finalstring;
            Count s1=new Count(sentence,context);
            res+=" "+s1.finalstring;
            context=s1.context;
            Minmax s3=new Minmax(cleaned2,columnnameslist);
            res+=" "+s3.finalstring;
            Sum s4=new Sum(sentence,context);
            res+=s4.finalstring;
            From s5=new From(sentence);
            res+=" "+s5.finalstring;
//System.out.println(cleaned2+"cleaned");

            OrderByClause s6=new OrderByClause(cleaned2,columnnameslist);
            res+=" "+s6.finalstring;

            System.out.println("\n\nThe Sql query is");
            System.out.println(res);



        }
        catch (IOException e)
        {

        }
    }
    public static ArrayList<String> simplechecker(ArrayList<String> nounsslist,ArrayList<String> samplenameslist)
    {
        int x[]=new int[100];
        int k=0;

        ArrayList<String> simplecheckdata=new ArrayList<>();
        simplecheckdata.clear();
        simplecheckdata.addAll(samplenameslist);
        simplecheckdata.retainAll(nounsslist);
        nounsslist.removeAll(simplecheckdata);
        for(int i=0;i<simplecheckdata.size();i++)
        {
            sentenceparser.put(simplecheckdata.get(i),simplecheckdata.get(i));
        }


        return simplecheckdata;
    }
    public static ArrayList<String> bigramchecker(ArrayList<String> nounslist,ArrayList<String> actualnameslist)
    {
        ArrayList<String> samplewords=new ArrayList<String>();
        ArrayList<String> bigramcontainer=new ArrayList<String>();
        String returnword=null;
        bigram b=new bigram();
        bigramcontainer.clear();
        samplewords=actualnameslist;
        b.getbigramready(samplewords);
        for(int i=0;i<nounslist.size();i++)
        {
            b.initialisenoun(nounslist.get(i));
            b.initialize();
            returnword=b.bigramize();
            if(returnword!=null)
            {
                sentenceparser.put(nounslist.get(i),returnword);
                bigramcontainer.add(returnword);
            }
            nounslist.removeAll(bigramcontainer);
        }

        if(bigramcontainer.size()>0)
        {
            System.out.println("Bigram test passed\n");
        }
        else
        {
            System.out.println("Bigram test fail\nmoving to hypernyms check");
        }
        return bigramcontainer;
    }

    public static ArrayList<String> hypernymchecker(ArrayList<String> nounsslist,ArrayList<String> samplenameslist)
    {

        WordNet hyp=new WordNet("synsets.txt","hypernyms.txt");
        String ancestor;
        ArrayList<String> hypernymlist = new ArrayList<>();
        hypernymlist.clear();
        for (int i=0;i<nounsslist.size();i++)
        {
            System.out.println("i: "+i);
            String string1=nounsslist.get(i);
            for (String check:samplenameslist)
            {
                System.out.println("String: "+string1+" Check "+check);
                try {
                    ancestor = hyp.findhypernym(string1.toLowerCase(), check); //Lower case
                    if (!ancestor.equals("Not found"))
                    {
                        hypernymlist.add(ancestor);
                        datalist.add(string1);
                        sentenceparser.put(string1, ancestor);
                        break;
                    }
                }
                catch(IllegalArgumentException e)
                {
                    ;
                }
            }

        }
        nounsslist.removeAll(hypernymlist);
        return hypernymlist;
    }

    public static ArrayList<String> columnfinder(String cols)
    {
        ArrayList<String> columns=new ArrayList<>();
        NLP c1=new NLP();
        c1.databasecreate();
        columns=c1.m.get(cols);
        return columns;
    }
}




