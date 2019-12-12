import java.io.File;
import java.io.FileNotFoundException;
import java.util.ArrayList;
import java.util.Map;
import java.util.Scanner;

import static java.lang.Math.max;
import static java.lang.Math.min;

public class ParseFile {

    private Scanner in;
    private String setName, type;
    private int rate, x1, x2, x3, x4;


    public ParseFile(String path) throws FileNotFoundException {
        File file = new File(path);
        in=new Scanner(file);
    }

    private  double inferenceTowRules(shape input1, shape input2, String operator){

        double MemberShip;
        double membership1, membership2;
        membership1=input1.fuzzification();
        membership2=input2.fuzzification();
        System.out.println("membership one: " + membership1 + " membership two: "+ membership2 + " operator is : " + operator);

        if(operator.contentEquals("AND"))
            MemberShip = min(membership1, membership2);
        else
            MemberShip=max(membership1, membership2);

        return MemberShip;
    }
    private double inferenceOneRule(shape input1, double OldMemberShip, String operator){

        double MemberShip;
        double membership1;
        membership1=input1.fuzzification();
        if(operator.contentEquals("AND"))
            MemberShip=min(membership1, OldMemberShip);
        else
            MemberShip=max(membership1, OldMemberShip);

        return MemberShip;
    }

    public void readRules(Map<String, shape>input, ArrayList<afterInference> nodes)
    {
        int NumberOfRules=in.nextInt();
        for(int i=0; i<NumberOfRules; i++)
        {

            afterInference node=new afterInference();
            int premise=in.nextInt();
            in.next();
            in.next();
            /**loop on the number of premise */
            double oldMembership=0;
            while(premise>0)
            {
                String setName1 = in.next();
                if(premise>=2)
                {

                    String operator=in.next();
                    in.next();
                    in.next();
                    String setName2=in.next();
                    oldMembership=inferenceTowRules(input.get(setName1), input.get(setName2), operator);
                    premise-=2;

                }
                else {
                    in.next(); in.next();
                    String setName=in.next();
                    oldMembership=inferenceOneRule(input.get(setName),oldMembership, setName1);
                }
            }

            in.next(); in.next(); in.next();
            node.name= in.next();
            node.MemberShip=oldMembership;
            if(node.MemberShip!=0)
                nodes.add(node);

        }
    }
    public void readInput(Map<String, shape> input)
    {

        int numberOfInputs=in.nextInt();

        for(int i=0; i<numberOfInputs; i++)
        {

            in.next();                     /**ignore input name*/
            rate=in.nextInt();            /**read input rate */
            int numberOfSets=in.nextInt(); /**read number of input sets */
            while(numberOfSets>0){         /**read all sets */
                setName=in.next();         /**read set name */
                type=in.next();           /**read set type (trapezoidal or triangle )*/
                if(type.contentEquals("trapezoidal")){
                    x1=in.nextInt();
                    x2=in.nextInt();
                    x3=in.nextInt();
                    x4=in.nextInt();
                    shape sh=new trapezoidal(x1, x2, x3,x4, rate);          /**create trapezoidal shape */
                    input.put(setName, sh);                                 /**insert input set in input map using name as a key */
                }
                else if(type.contentEquals("triangle")){
                    x1=in.nextInt();
                    x2=in.nextInt();
                    x3=in.nextInt();
                    shape sh =new triangle(x1, x2, x3, rate);                 /**create triangle shape*/
                    input.put(setName, sh);                                   /**insert triangle shape in input map using name as a key */
                }
                else {
                    System.out.println("the set type is wrong ");
                }
                numberOfSets--;
            }
        }
        in.next();

    }

    public void readOutput(Map<String, shape>output)
    {
        int numberOfOutputs=in.nextInt();
        for(int i=0; i<numberOfOutputs; i++)        /**read all output sets */
        {
            setName=in.next();
            type=in.next();
            if(type.contentEquals("trapezoidal")){

                x1=in.nextInt();
                x2=in.nextInt();
                x3=in.nextInt();
                x4=in.nextInt();
                shape sh=new trapezoidal(x1, x2, x3,x4, rate); /**create trapezoidal shape */
                output.put(setName, sh);                                /**insert in output map using shape name as a key*/
            }
            else if(type.contentEquals("triangle")){
                x1=in.nextInt();
                x2=in.nextInt();
                x3=in.nextInt();
                shape sh=new triangle(x1, x2, x3, rate);      /**create triangle shape*/
                output.put(setName, sh);                               /**insert in output map using shape name as a key*/
            }
            else {
                System.out.println("the set type is wrong ");
            }

        }

    }

}
