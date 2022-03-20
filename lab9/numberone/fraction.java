package numberone;

public class fraction implements globals{
    private int numerator;
    private int denominator;

    public fraction (int numerator, int denominator) {
        if (denominator == 0) {
            throw new IllegalArgumentException("Деление на 0");
        }
        if(numerator < 0 && denominator < 0){
            this.numerator = Math.abs(numerator);
            this.denominator = Math.abs(denominator);
        } else {
            this.numerator = numerator;
            this.denominator = denominator; 
        }
          
    }
    // Конструктор по умолчанию
    public fraction() {
        this.numerator = 1;
        this.denominator = 1; 
    }
    // Вызывается принтлн
    public String toString() {
        return new String(this.numerator + "/" + this.denominator);
    }

    public String optimization() {
        if (this.numerator % this.denominator == 0) {
            return new String(Integer.toString(this.numerator / this.denominator));
        }
        for(int simple: simpleValues){
            while(this.numerator % simple == 0 && this.denominator % simple == 0) {
                this.numerator = this.numerator / simple;
                this.denominator = this.denominator / simple;
            }
        }
        return new String(this.numerator + "/" + this.denominator);
    }

    public fraction sum(fraction b) {
        if (this.denominator == b.denominator) {
            return new fraction(this.numerator + b.numerator, b.denominator);
        }
        return new fraction(this.numerator * b.denominator + b.numerator * this.denominator,this.denominator * b.denominator);
    }

    public static fraction sum(fraction a, fraction b) {
        return a.sum(b);
    }

    public fraction sub(fraction b) {
        if (this.denominator == b.denominator) {
            return new fraction(this.numerator - b.numerator, b.denominator);
        }
        return new fraction(this.numerator * b.denominator - b.numerator * this.denominator,this.denominator * b.denominator);
    }

    public static fraction sub(fraction a, fraction b) {
        return a.sub(b);
    }

    public fraction multi(fraction b) {
        return new fraction(this.numerator * b.numerator, this.denominator * b.denominator);
    }

    public static fraction multi(fraction a, fraction b) {
        return a.multi(b);
    }

    public fraction div(fraction b) {
        if (b.numerator == 0) {
            throw new IllegalArgumentException("Деление на 0");
        }
        return new fraction(this.numerator * b.denominator, this.denominator * b.numerator);
    }

    public static fraction div(fraction a, fraction b) {
        return a.div(b);
    }
}