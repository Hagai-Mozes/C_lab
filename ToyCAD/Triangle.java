
public class Triangle extends Shape {
    private Point pointA;
    private Point pointB;
    private Point pointC;
    
    public static double calculateTriangleArea(Point inputPointA, Point inputPointB, Point inputPointC) {
        double a = calculateDistance(inputPointA, inputPointB);
        double b = calculateDistance(inputPointB, inputPointC);
        double c = calculateDistance(inputPointC, inputPointA);
        double s = (a + b + c) / 2;
        return Math.sqrt(Math.abs(s * (s - a) * (s - b) * (s - c)));
    }
    protected void calculateArea() {
        setArea(calculateTriangleArea(this.pointA, this.pointB, this.pointC));
    }
    protected void calculateCircumference() {
        setCircumference(calculateDistance(pointA, pointB) + calculateDistance(pointB, pointC) + calculateDistance(pointC, pointA));
    }
    public Triangle(Point inputPointA, Point inputPointB, Point inputPointC, String color) {
        super(color);
        this.pointA = inputPointA;
        this.pointB = inputPointB;
        this.pointC = inputPointC;
        calculateArea();
        calculateCircumference();
    }
    public boolean isInside (Point point){
        double areaABP = calculateTriangleArea(this.pointA, this.pointB, point); 
        double areaACP = calculateTriangleArea(this.pointA, this.pointC, point); 
        double areaBCP = calculateTriangleArea(this.pointB, this.pointC, point); 
        if(Math.abs(this.getArea() - (areaABP + areaACP + areaBCP)) < EPSILON){
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
    }
    public Triangle copy(){
        Point newPointA = new Point(this.pointA.x, this.pointA.y);
        Point newPointB = new Point(this.pointB.x, this.pointB.y);
        Point newPointC = new Point(this.pointC.x, this.pointC.y);
        return new Triangle(newPointA, newPointB, newPointC, this.getColor());
    }
}