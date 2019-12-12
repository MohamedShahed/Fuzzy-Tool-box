public class trapezoidal extends shape {

    private double x1,x2,x3, x4, rate;
    public trapezoidal(double x1, double x2, double x3, double x4, double rate)
    {
        this.x1=x1;
        this.x2=x2;
        this.x3=x3;
        this.x4=x4;
        this.rate=rate;
    }

    @Override
    protected double fuzzification() {
        double Membership=0.0, slope, c, y1, y2;
        if(rate>x1 && rate<x4){
            if(rate>x1 && rate<=x2){
                y1=0; y2=1;
                if(x2-x1==0)
                    slope=1;
                else
                    slope=(y2-y1)/(x2-x1);
                /** Y=ax+c
                 * c=Y-ax
                 * */
                c=y1-(slope*x1);
            }
            else if(rate>x2 && rate<=x3){
                y1=1; y2=1;
                if(x3-x2==0)
                    slope=1;
                else
                    slope=(y2-y1)/(x3-x2);
                c=y1-(slope*x2);
            }
            else{
                y1=1;y2=0;
                if(x4-x3==0)
                    slope=1;
                else
                    slope=(y2-y1)/(x4-x3);
                c=y1-(slope*x3);
            }
            Membership=(slope*rate)+c;
        }

        return Membership;

    }

    @Override
    public double getCentroid() {
        return (x1+x2+x3+x4)/4;
    }


}
