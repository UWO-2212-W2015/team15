package team15.GUI;

public class CubicSpline {
    double[] x, y, coeff;
    
    public CubicSpline (double[] x, double[] y){
	final int n = x.length-1;
	this.x = x;
	this.y = y;
	double[] xdiff = new double [n];
	double[] ydiff = new double [n];
	//Calculate diff in x
	for (int i = 0; i < n; i++){
	    xdiff[i] = x[i+1] - x[i];
	    ydiff[i] = y[i+1] - y[i];
	}

	//Calculate tridiagonal matrix
	double [] h = new double [n];
	//Upper and lower diagonal
	for (int i = 0; i < n; i++){
	    h[i] = 1 / xdiff[i];
	}
	double [] diag = new double[n+1];
	double [] b = new double[n+1];
	b[0] = 3*(ydiff[0]*h[0]*h[0]);
	diag[0] = 2 / h[0];
	for (int i = 1; i < n; i++){
	    diag[i] = 2*(h[i-1] + h[i]);
	    b[i] = 3*(ydiff[i-1]*h[i-1]*h[i-1] + ydiff[i]*h[i]*h[i]);
	}
	diag[n] = 2 * h[n-1];
	b[n] = 3*(ydiff[n-1]*h[n-1]*h[n-1]);

	this.coeff = triDiagSolve (h, diag, h, b);
    }

    public double[] triDiagSolve (double[] a, double[] b, double[] c, double[] d){
	//length of diagonal
	final int n = b.length;
	double [] newC = new double [n-1];
	double [] newD = new double [n];

	newC[0] = c[0] / b[0];
	newD[0] = d[0] / b[0];
	for (int i = 1; i < n-1; i++){
	    newC[i] = c[i] / (b[i] - a[i-1]*newC[i-1]);
	}

	for (int i = 1; i < n; i++){
	    newD[i] = (d[i] - a[i-1]*newD[i-1]) / (b[i] - a[i-1]*newC[i-1]);
	}

	double [] ans = new double [n];
	ans[n-1] = newD[n-1];
	for (int i = n-2; i >= 0; i--){
	    ans[i] = newD[i] - newC[i]*ans[i+1];
	}
	
	return ans;
    }

    public double evaluate (double xC){
        int in = -1;
	for (int i = 0; i < this.x.length-1; i++){
	    if (x[i] < xC && xC < x[i+1]){
		in = i;
		break;
	    }
	} 
	double t = (xC - this.x[in]) / (this.x[in+1] - this.x[in]);
	double a = coeff[in]*(x[in+1] - x[in]) - (y[in+1]-y[in]);
	double b = -coeff[in+1]*(x[in+1] - x[in]) + (y[in+1]-y[in]);
	return (1-t)*y[in] + t*y[in+1] + t*(1-t)*(a*(1-t)+b*t);
    }
    public static void main (String[] args){
	CubicSpline spline = new CubicSpline (new double[] {-1, 0, 3}, new double[] {0.5, 0, 3});
	System.out.println(spline.evaluate (-0.9999));
    }
}
