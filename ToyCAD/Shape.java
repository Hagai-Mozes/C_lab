abstract public class Shape {
    enum Color {
        BLUE("blue"),
        RED("red"),
        YELLOW("yellow"),
        GREEN("green");
        private String color;
        Color(String color) {
            this.color = color;
        }
        public String toString() {
            return this.color;
        }
    }
    public static final double EPSILON = 0.00001;
    static private int counterID = 0;
    private int ID;
    private Color color;
    private double area;
    private double circumference;
    public Shape(String color) {
        this.ID = counterID++;
        this.color = Color.valueOf(color.toUpperCase());
    }
    public int getID() {
        return this.ID;
    }
    public String getColor() {
        return this.color.toString();
    }
    public double getArea() {
        return this.area;
    }
    public double getCircumference() {
        return this.circumference;
    }
    public void setColor(String color) {
        this.color = Color.valueOf(color.toUpperCase());
    }
    protected void setArea(double area) {
        this.area = area;
    }
    protected void setCircumference(double circumference) {
        this.circumference = circumference;
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