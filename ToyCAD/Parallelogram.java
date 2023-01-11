
public class Parallelogram extends Shape {
    private Point pointA;
    private Point pointB;
    private Point pointC;
    private Point pointD;

    private static Point calculatePointD(Point inputPointA, Point inputPointB, Point inputPointC){
        Point resultPointD = new Point(0, 0);
        resultPointD.x = inputPointA.x + inputPointC.x - inputPointB.x;
        resultPointD.y = inputPointA.y + inputPointC.y - inputPointB.y;
        return resultPointD;
    }
    protected void calculateArea() {
        setArea(2 * Triangle.calculateTriangleArea(this.pointA, this.pointB, this.pointC));
    }
    protected void calculateCircumference() {
        setCircumference(calculateDistance(this.pointA, this.pointB) + calculateDistance(this.pointB, this.pointC) + 
            calculateDistance(this.pointC, this.pointD) + calculateDistance(this.pointD, this.pointA));
    }

    public Parallelogram(Point inputPointA, Point inputPointB, Point inputPointC, String color){
        super(color);
        this.pointA = inputPointA;
        this.pointB = inputPointB;
        this.pointC = inputPointC;
        this.pointD = calculatePointD(inputPointA, inputPointB, inputPointC);
        calculateArea();
        calculateCircumference();
    }
    public boolean isInside(Point point){
        double areaABP = Triangle.calculateTriangleArea(this.pointA, this.pointB, point);
        double areaBCP = Triangle.calculateTriangleArea(this.pointB, this.pointC, point);
        double areaCDP = Triangle.calculateTriangleArea(this.pointC, this.pointD, point);
        double areaDAP = Triangle.calculateTriangleArea(this.pointD, this.pointA, point);
        if(Math.abs(this.getArea() - (areaABP + areaBCP + areaCDP + areaDAP)) < EPSILON){
            return true;
        }
        else{
            return false;
        }
    }
    public void move(double x, double y){
        this.pointA.x += x;
        this.pointA.y += y;
        this.pointB.x += x;
        this.pointB.y += y;
        this.pointC.x += x;
        this.pointC.y += y;
        this.pointD.x += x;
        this.pointD.y += y;
    }
    public Parallelogram copy(){
        Point newPointA = new Point(this.pointA.x, this.pointA.y);
        Point newPointB = new Point(this.pointB.x, this.pointB.y);
        Point newPointC = new Point(this.pointC.x, this.pointC.y);
        return new Parallelogram(newPointA, newPointB, newPointC, this.getColor());
    }
}