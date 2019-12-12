import java.io.*;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;

public class FuzzyTool {

    public static Map<String, shape> input =new HashMap<>();
    public static Map<String, shape>output=new HashMap<>();
    public static ArrayList<afterInference> nodes=new ArrayList<>();




    public  static double deFuzzification(){

        double sum1=0.0, sum2=0.0;
        for (int i=0; i<nodes.size(); i++)
        {
            sum1+=(nodes.get(i).MemberShip)*(output.get(nodes.get(i).name).getCentroid());
            sum2+=nodes.get(i).MemberShip;

        }

        return sum1/sum2;

    }

    public static void print()
    {
        System.out.println("after inference ");
        for(int i=0; i<nodes.size(); i++)
            nodes.get(i).pritn();
        System.out.println("=========================\nweighted average = " + deFuzzification());
    }

    public static void main(String arg[]) throws IOException {

        ParseFile parse=new ParseFile("input.txt");
        parse.readInput(input);
        parse.readOutput(output);
        parse.readRules(input,nodes);  /**do inference */
        print();                      /**do deFuzzification and print */

        }
    }
