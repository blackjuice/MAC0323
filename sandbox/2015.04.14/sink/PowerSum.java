/*************************************************************************
 *  
 *************************************************************************/

public class PowerSum implements Bifunction {
    private int a, b;

    public PowerSum(int a, int b) {
	this.a = a;
	this.b = b;
    }

    long pow (long a, int b)
    {
	if ( b == 0)        return 1;
	if ( b == 1)        return a;
	if (b % 2 == 0)     return     pow ( a * a, b/2); //even a=(a^2)^b/2
	else                return a * pow ( a * a, b/2); //odd  a=a*(a^2)^b/2
	
    }

    public long eval(int i, int j) {
	return pow(a, i) + pow(b, j);
    }

}
