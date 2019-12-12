public class triangle extends shape {

    private   double x1,x2,x3, rate;

    public triangle(double x1, double x2, double x3, double rate)
    {
        this.x1=x1;
        this.x2=x2;
        this.x3=x3;
        this.rate=rate;
    }

    @Override
    protected double fuzzification() {
        double Membership=0.0, slope, c, y1, y2;
        if(rate>x1 && rate<x3){
            if(rate>x1 && rate<=x2){
                y1=0; y2=1;
                if(x2-x1==0)
                    slope=1;
                else
                    slope=(y2-y1)/(x2-x1);
                /** Y=ax+c*/
                c=y1-(x1*slope);
            }
            else{
                y1=1; y2=0;
                if(x3-x2==0)
                    slope=1;
                else
                    slope=(y2-y1)/(x3-x2);
                c=y1-(slope*x2);
            }
            Membership=(slope*rate)+c;
        }

        return Membership;
    }

    @Override
    public double getCentroid() {
        return (x1+x3+x2)/3;
    }


}
