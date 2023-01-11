
public class Ellipse extends Shape {
    private Point centerA;
    private Point centerB;
    private double D;
    private static int factorial(int n) {
        if (n == 0 || n == 1) {
            return 1;
        }
        return n * factorial(n - 1);
    }
    private static int doubleFactorial(int n) {
        if (n == 0 || n == 1) {
            return 1;
        }
        return n * doubleFactorial(n - 2);
    }
    protected void calculateArea() {
        double majorRadius = 0.5 * this.D;
        double minorRadius = Math.sqrt(Math.pow(majorRadius,2) -  Math.pow(0.5 * calculateDistance(centerA, centerB), 2));
        setArea(Math.PI * majorRadius * minorRadius);
    }
    protected void calculateCircumference() {
        double majorRadius = 0.5 * this.D;
        double minorRadius = Math.sqrt(Math.pow(majorRadius,2) -  Math.pow(0.5 * calculateDistance(centerA, centerB), 2));
        double h = Math.pow(majorRadius - minorRadius, 2) / Math.pow(majorRadius + minorRadius, 2);
        double seriesSum = 0;
        for (int n = 1; n <= 10; n++) {
            seriesSum += Math.pow(doubleFactorial(2 * n - 1) / (factorial(n) * Math.pow(2, n)), 2) * Math.pow(h, n) / Math.pow(2 * n - 1, 2);
        }
        setCircumference(Math.PI * (majorRadius + minorRadius) * (1 + seriesSum));
    }
    public Ellipse(Point inputCenterA, Point inputCenterB, double inputD, String color) {
        super(color);
        this.centerA = inputCenterA;
        this.centerB = inputCenterB;
        this.D = inputD;
        calculateArea();
        calculateCircumference();
    }
    public boolean isInside (Point point){
        if((calculateDistance(point, centerA) + calculateDistance(point, centerB)) <= this.D){
            return true;
        }
        else{
            return false;
        }
    }
    public void move(double x, double y){
        this.centerA.x += x;
        this.centerA.y += y;
        this.centerB.x += x;
        this.centerB.y += y;
    }
    public Ellipse copy(){
        Point newCenterA = new Point(this.centerA.x, this.centerA.y);
        Point newCenterB = new Point(this.centerB.x, this.centerB.y);
        return new Ellipse(newCenterA, newCenterB, this.D, this.getColor());
    }
}