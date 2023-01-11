public class Square extends Rectangle {
    public Square(Point center, double edgeLength, String color){
        super(new Point(center.x - edgeLength / 2, center.y - edgeLength / 2),
            new Point(center.x + edgeLength / 2, center.y + edgeLength / 2), color);
    }
}