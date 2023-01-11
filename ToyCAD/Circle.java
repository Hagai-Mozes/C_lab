
public class Circle extends Shape {
    private Point center;
    private double radius;
    protected void calculateArea() {
        setArea(Math.PI * Math.pow(this.radius, 2));
    }
    protected void calculateCircumference() {
        setCircumference(2 * Math.PI * this.radius);
    }
    public Circle(Point inputCenter, double inputRadius, String color) {
        super(color);
        this.center = inputCenter;
        this.radius = inputRadius;
        calculateArea();
        calculateCircumference();
    }
    public boolean isInside (Point point){
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
        return new Circle(newCenter, this.radius, this.getColor());
    }
}
