public class Rectangle extends Parallelogram {
    public Rectangle(Point inputPointA, Point inputPointC, String color){
        super(inputPointA, new Point(inputPointA.x, inputPointC.y), inputPointC, color);
    }
}