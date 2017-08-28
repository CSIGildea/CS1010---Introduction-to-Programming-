
public class Rational{
    private int numerator;
    private int denominator;
    
	Rational(int num, int den)
	{
        if (den == 0) 
        {
            throw new IllegalArgumentException("Denominator cannot be zero");
        }
		numerator = num;
		denominator = den;
		this.simplify();
	}
	Rational(int num)
	{
		numerator = num;
		denominator = 1;
	}
	Rational add(Rational inputtedRational) { 
		if(inputtedRational !=null)
		{
			Rational currentRational = this;
			Rational rationalToAdd = inputtedRational;
			if (currentRational.numerator==0) 
			{
				return rationalToAdd;
			}
			else if (rationalToAdd.numerator==0) 
			{
				return currentRational;
			}

			//Addition through cross multiplication, finding common denominator by multiplying each denominator by each other then simplifying.
			Rational crossMultipliedAddedAns = new Rational((currentRational.numerator*rationalToAdd.denominator)
					+ (rationalToAdd.numerator) * (currentRational.denominator)
					,currentRational.denominator*rationalToAdd.denominator);
			crossMultipliedAddedAns.simplify();
			return crossMultipliedAddedAns;
		}
		else
		{
			throw new NullPointerException();
		}
	}
	int getNumerator(){
		return this.numerator;
	}
	int getDenominator(){
		return this.denominator;
	}
	Rational subtract(Rational inputtedRational) {
		if(inputtedRational !=null)
		{
			Rational subbedThis = this;
			return subbedThis.add(new Rational(-inputtedRational.numerator,inputtedRational.denominator));
		}
		else
		{
			throw new NullPointerException();
		}
	}
	Rational multiply(Rational inputtedRational) {
		if(inputtedRational !=null)
		{
			return new Rational(this.numerator * inputtedRational.numerator, this.denominator*inputtedRational.denominator);
		}
		else
		{
			throw new NullPointerException();
		}
	}
	Rational divide(Rational inputtedRational) { 
		if(inputtedRational !=null)
		{
			if(inputtedRational.numerator!=0)
			{
				//Swapped numerator and denominator places in dividing rational number, we then multiple this to divide.
				return this.multiply(new Rational(inputtedRational.denominator,inputtedRational.numerator));
			}
			throw new IllegalArgumentException("Cannot divide by zero");
		}
		else
		{
			throw new NullPointerException();
		}
	}
	boolean equals(Rational inputtedRational){
		if(inputtedRational !=null)
		{
			inputtedRational.simplify();
			this.simplify();
			return numerator == inputtedRational.numerator && denominator == inputtedRational.denominator;
		}
		else
		{
			throw new NullPointerException();
		}
	}
	boolean isLessThan(Rational inputtedRational){
		if(inputtedRational !=null)
		{
			Rational currentRational = this;
			int leftSideValue = currentRational.numerator * inputtedRational.denominator;
			int rightSideValue = currentRational.denominator * inputtedRational.numerator;
			if (leftSideValue < rightSideValue) return true;
			else
			{
				return false;
			}
		}
		else
		{
			throw new NullPointerException();
		}
	}
	Rational simplify() {					
        int gcdValue = gcd(this.numerator, this.denominator);
        this.numerator = this.numerator   / gcdValue;
        this.denominator = this.denominator / gcdValue;

        if (this.denominator < 0) 
        { 
        	this.denominator= -this.denominator; 
        	this.numerator = -this.numerator; 
        }
        return this;
	}
	//Using Euclid's algorithm to find the GCD
	public int gcd(int firstValue,int secondValue){
		int x = firstValue;
		int y = secondValue;
		
		if (x < 0)
		{
			x = -x;
		}
		if (y < 0) 
		{
			y = -y;
		}
		if (y == 0) 
		{
			return x;
		}
		else 
		{
			return gcd(y, x % y);
		}
	}
	public String toString(){
		this.simplify();
        if (denominator == 1) 
        {
        	return this.numerator + "";
        }
        else
        {
        	return this.numerator + "/" + this.denominator;
        }
	}
}

