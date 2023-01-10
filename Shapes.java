public class Constants {
    public static final double EPSILON = 0.00001;
}

public enum Color {
    BLUE("blue"),
    RED("red"),
    YELLOW("yellow"),
    GREEN("green");
    
    private String colorName;
 
    Color(String colorName) {
        this.colorName = colorName;
    }
 
    public String getColor() {
        return colorName;
    }
}

public class Point {
    public double x;
    public double y;
    public Point(double x, double y) {
        this.x = x;
        this.y = y;
    }
    public void movePoint(double x, double y) {
        this.x += x;
        this.y += y;
    }
}

abstract public class Shape {
    static private int counterID = 0;
    private int ID;
    private Color color;
    private double area;
    private double circumference;
    public Shape(String color) {
        this.ID = counterID++;
        this.color = color;
    }
    public int getID() {
        return this.ID;
    }
    public Color getColor() {
        return this.color;
    }
    public double getArea() {
        return this.area;
    }
    public double getCircumference() {
        return this.circumference;
    }
    public void setColor(String color) {
        this.color = color;
    }
    protected void setArea(double area) {
        this.area = area;
    }
    protected void setCircumference(double circumference) {
        this.circumference = circumference;
    }
    static protected void movePoints(Point[] points, double x, double y) {
        for (Point point : points) {
            point.movePoint(x, y);
        }
    }
    static protected double calculateDistance(Point a, Point b) {
        return Math.sqrt(Math.pow(a.x - b.x, 2) + Math.pow(a.y - b.y, 2));
    }
    abstract protected void calculateArea();
    abstract protected void calculateCircumference();
    abstract protected boolean isInside(Point point);
    abstract protected void move(double x, double y);
    abstract protected Shape copy();
}

public class Triangle extends Shape {
    private Point pointA;
    private Point pointB;
    private Point pointC;

    private static double calculateTriangleArea(Point inputPointA, Point inputPointB, Point inputPointC) {
        double a = calculateDistance(inputPointA, inputPointB);
        double b = calculateDistance(inputPointB, inputPointC);
        double c = calculateDistance(inputPointC, inputPointA);
        double s = (a + b + c) / 2;
        return Math.sqrt(s * (s - a) * (s - b) * (s - c));
    }
    private void calculateArea() {
        setArea(calculateTriangleArea(this.pointA, this.pointB, this.pointC));
    }
    private void calculateCircumference() {
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
    public isInside (Point point){
        double areaABP = calculateTriangleArea(this.pointA, this.pointB, point)
        double areaACP = calculateTriangleArea(this.pointA, this.pointC, point)
        double areaBCP = calculateTriangleArea(this.pointB, this.pointC, point)
        if(this.area - (areaABP + areaACP + areaBCP) < Constants.EPSILON){
            return true;
        }
        else{
            return false;
        }
    }
    public void move(double x, double y){
        movePoints(new Point[]{this.pointA, this.pointB, this.pointC}, x, y);
    }
    public Triangle copy(){
        Point newPointA = new Point(this.pointA.x, this.pointA.y);
        Point newPointB = new Point(this.pointB.x, this.pointB.y);
        Point newPointC = new Point(this.pointC.x, this.pointC.y);
        return new Triangle(newPointA, newPointB, newPointC, this.color);
    }
}

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
    private void calculateArea() {
        double majorRadius = 0.5 * this.D;
        double minorRadius = Math.sqrt(Math.pow(majorRadius,2) -  Math.pow(0.5 * calculateDistance(centerA, centerB), 2));
        setArea(Math.PI * majorRadius * minorRadius);
    }
    private void calculateCircumference() {
        double majorRadius = 0.5 * this.D;
        double minorRadius = Math.sqrt(Math.pow(majorRadius,2) -  Math.pow(0.5 * calculateDistance(centerA, centerB), 2));
        double h = Math.pow(majorRadius - minorRadius, 2) / Math.pow(majorRadius + minorRadius, 2);
        double seriesSum = 0;
        for (int n = 1; n <= 10; n++) {
            seriesSum += pow(doubleFactorial(2 * n - 1) / (factorial(n) * pow(2, n)), 2) * pow(h, n) / pow(2 * n - 1, 2);
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
    public isInside (Point point){
        if(calculateDistance(point, centerA) + calculateDistance(point, centerB) <= this.D){
            return true;
        }
        else{
            return false;
        }
    }
    public void move(double x, double y){
        movePoints(new Point[]{this.centerA, this.centerB}, x, y);
    }
    public Ellipse copy(){
        Point newCenterA = new Point(this.centerA.x, this.centerA.y);
        Point newCenterB = new Point(this.centerB.x, this.centerB.y);
        return new Ellipse(newCenterA, newCenterB, this.D, this.color);
    }
}

public class Circle extends Shape {
    private Point center;
    private double radius;
    private void calculateArea() {
        setArea(Math.PI * Math.pow(this.radius, 2));
    }
    private void calculateCircumference() {
        setCircumference(2 * Math.PI * this.radius);
    }
    public Circle(Point inputCenter, double inputRadius, String color) {
        super(color);
        this.center = inputCenter;
        this.radius = inputRadius;
        calculateArea();
        calculateCircumference();
    }
    public isInside (Point point){
        if(calculateDistance(point, this.center) <= this.radius){
            return true;
        }
        else{
            return false;
        }
    }
    public void move(double x, double y){
        center.movePoint(x, y);
    }
    public Circle copy(){
        Point newCenter = new Point(this.center.x, this.center.y);
        return new Circle(newCenter, this.radius, this.color);
    }
}

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
    private void calculateArea() {
        setArea(2 * Triangle.calculateTriangleArea(this.pointA, this.pointB, this.pointC));
    }
    private void calculateCircumference() {
        setCircumference(calculateDistance(this.pointA, this.pointB) + calculateDistance(this.pointB, this.pointC) + \
            calculateDistance(this.pointC, this.pointD) + calculateDistance(this.pointD, this.pointA));
    }

    public Parallelogram(Point inputPointA, Point inputPointB, Point inputPointC, Color color){
        super(color);
        this.pointA = inputPointA;
        this.pointB = inputPointB;
        this.pointC = inputPointC;
        this.pointD = calculatePointD(inputPointA, inputPointB, inputPointC);
        calculateArea();
        calculateCircumference();
    }
    public isInside(Point point){
        double areaABP = Triangle.calculateTriangleArea(this.pointA, this.pointB, point);
        double areaBCP = Triangle.calculateTriangleArea(this.pointB, this.pointC, point);
        double areaCDP = Triangle.calculateTriangleArea(this.pointC, this.pointD, point);
        double areaDAP = Triangle.calculateTriangleArea(this.pointD, this.pointA, point);
        if(this.area - (areaABP + areaBCP + areaCDP + areaDAP) < Constants.EPSILON){
            return true;
        }
        else{
            return false;
        }
    }
    public void move(double x, double y){
        movePoints(new Point[]{this.pointA, this.pointB, this.pointC, this.pointD}, x, y);
    }
    public Parallelogram copy(){
        Point newPointA = new Point(this.pointA.x, this.pointA.y);
        Point newPointB = new Point(this.pointB.x, this.pointB.y);
        Point newPointC = new Point(this.pointC.x, this.pointC.y);
        return new Parallelogram(newPointA, newPointB, newPointC, this.color);
    }
}

public class Rectangle extends Parallelogram {
    public Rectangle(Point inputPointA, Point inputPointC, Color color){
        Point pointB = new Point(inputPointC.x, inputPointA.y);
        super(inputPointA, pointB, inputPointC, color);
    }
}

public class Square extends Rectangle {
    public Square(Point center, double edgeLength, Color color){
        Point pointA = new Point(center.x - edgeLength / 2, center.y - edgeLength / 2);
        Point pointC = new Point(center.x + edgeLength / 2, center.y + edgeLength / 2);
        super(pointA, pointC, color);
    }
}