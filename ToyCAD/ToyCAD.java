import java.util.LinkedList;
import java.util.Scanner;

public class ToyCAD {
    private static Shape createShape(String[] inputTokens){
        String shapeType = inputTokens[1].toLowerCase();
        String color = inputTokens[2].toLowerCase();
        double pointAX, pointAY, pointBX, pointBY, pointCX, pointCY, centerX, centerY;
        Point pointA, pointB, pointC, center;
        Shape newShape = null;
        switch (shapeType) {
            case "circle":
                centerX = Double.parseDouble(inputTokens[3]);
                centerY = Double.parseDouble(inputTokens[4]);
                double radius = Double.parseDouble(inputTokens[5]);
                center = new Point(centerX, centerY);
                newShape = new Circle(center, radius, color);
                break;
            case "ellipse":
                double centerAX = Double.parseDouble(inputTokens[3]);
                double centerAY = Double.parseDouble(inputTokens[4]);
                double centerBX = Double.parseDouble(inputTokens[5]);
                double centerBY = Double.parseDouble(inputTokens[6]);
                double D = Double.parseDouble(inputTokens[7]);
                Point centerA = new Point(centerAX, centerAY);
                Point centerB = new Point(centerBX, centerBY);
                newShape = new Ellipse(centerA, centerB, D, color);
                break;
            case "triangle":
                pointAX = Double.parseDouble(inputTokens[3]);
                pointAY = Double.parseDouble(inputTokens[4]);
                pointBX = Double.parseDouble(inputTokens[5]);
                pointBY = Double.parseDouble(inputTokens[6]);
                pointCX = Double.parseDouble(inputTokens[7]);
                pointCY = Double.parseDouble(inputTokens[8]);
                pointA = new Point(pointAX, pointAY);
                pointB = new Point(pointBX, pointBY);
                pointC = new Point(pointCX, pointCY);
                newShape = new Triangle(pointA, pointB, pointC, color);
                break;
            case "rectangle":
                pointAX = Double.parseDouble(inputTokens[3]);
                pointAY = Double.parseDouble(inputTokens[4]);
                pointCX = Double.parseDouble(inputTokens[5]);
                pointCY = Double.parseDouble(inputTokens[6]);
                pointA = new Point(pointAX, pointAY);
                pointC = new Point(pointCX, pointCY);
                newShape = new Rectangle(pointA, pointC, color);
                break;
            case "square":
                centerX = Double.parseDouble(inputTokens[3]);
                centerY = Double.parseDouble(inputTokens[4]);
                double edgeLength = Double.parseDouble(inputTokens[5]);
                center = new Point(centerX, centerY);
                newShape = new Square(center, edgeLength, color);
                break;
            case "parallelogram":
                pointAX = Double.parseDouble(inputTokens[3]);
                pointAY = Double.parseDouble(inputTokens[4]);
                pointBX = Double.parseDouble(inputTokens[5]);
                pointBY = Double.parseDouble(inputTokens[6]);
                pointCX = Double.parseDouble(inputTokens[7]);
                pointCY = Double.parseDouble(inputTokens[8]);
                pointA = new Point(pointAX, pointAY);
                pointB = new Point(pointBX, pointBY);
                pointC = new Point(pointCX, pointCY);
                newShape = new Parallelogram(pointA, pointB, pointC, color);
                break;
        }
        return newShape;
    }
    private static int newShape(String[] inputTokens, LinkedList<Shape> shapes){
        Shape shape = createShape(inputTokens);
        shapes.add(shape);
        return shape.getID();
    }
    private static void deleteShape(int id, LinkedList<Shape> shapes){
        for (int i = 0; i < shapes.size(); i++) {
            if (shapes.get(i).getID() == id) {
                shapes.remove(i);
                break;
            }
        }
    }
    private static void moveShape(int id, double deltaX, double deltaY, LinkedList<Shape> shapes){
        for (int i = 0; i < shapes.size(); i++) {
            if (shapes.get(i).getID() == id) {
                shapes.get(i).move(deltaX, deltaY);
                break;
            }
        }
    }
    private static int copyShape(int id, double deltaX, double deltaY, LinkedList<Shape> shapes){
        for (int i = 0; i < shapes.size(); i++) {
            if (shapes.get(i).getID() == id) {
                Shape shape = shapes.get(i).copy();
                shape.move(deltaX, deltaY);
                shapes.add(shape);
                return shape.getID();
            }
        }
        return -1;
    }
    private static void colorShape(String color, int id, LinkedList<Shape> shapes){
        for (int i = 0; i < shapes.size(); i++) {
            if (shapes.get(i).getID() == id) {
                shapes.get(i).setColor(color);
                break;
            }
        }
    }
    private static double totalArea(LinkedList<Shape> shapes, String color){
        double colorArea = 0.0;
        for (int i = 0; i < shapes.size(); i++) {
            if (shapes.get(i).getColor().toString().equals(color)){
                colorArea += shapes.get(i).getArea();
            }
        }
        return colorArea;
    }
    private static double totalCircumference(LinkedList<Shape> shapes, String color){
        double colorCircumference = 0.0;
        for (int i = 0; i < shapes.size(); i++) {
            if (shapes.get(i).getColor().toString().equals(color)){
                colorCircumference += shapes.get(i).getCircumference();
            }
        }
        return colorCircumference;
    }
    private static boolean is_inside(LinkedList <Shape> shapes, double pointX, double pointY){
        Point point = new Point(pointX, pointY);
        for (int i = 0; i < shapes.size(); i++) {
            if (shapes.get(i).isInside(point)) {
                return true;
            }
        }
        return false;
    }
    private static boolean handleCommand(String[] inputTokens, LinkedList<Shape> shapes){
        String command = inputTokens[0].toLowerCase();
        int id;
        double deltaX, deltaY;
        switch (command) {
            case "new":
                id = newShape(inputTokens, shapes);
                System.out.println(id);
                break;
            case "delete":
                id = Integer.parseInt(inputTokens[1]);
                deleteShape(id, shapes);
                break;
            case "move":
                id = Integer.parseInt(inputTokens[1]);
                deltaX = Double.parseDouble(inputTokens[2]);
                deltaY = Double.parseDouble(inputTokens[3]);
                moveShape(id, deltaX, deltaY, shapes);
                break;
            case "copy":
                id = Integer.parseInt(inputTokens[1]);
                deltaX = Double.parseDouble(inputTokens[2]);
                deltaY = Double.parseDouble(inputTokens[3]);
                id = copyShape(id, deltaX, deltaY, shapes);
                System.out.println(id);
                break;
            case "color":
                String color = inputTokens[1].toLowerCase();
                id = Integer.parseInt(inputTokens[2]);
                colorShape(color, id, shapes);
                break;
            case "area":
                color = inputTokens[1].toLowerCase();
                double area = totalArea(shapes, color);
                System.out.printf("%.2f\n", area);
                break;
            case "circumference":
                color = inputTokens[1].toLowerCase();
                double circumference = totalCircumference(shapes, color);
                System.out.printf("%.2f\n", circumference);
                break;
            case "is_inside":
                double pointX = Double.parseDouble(inputTokens[1]);
                double pointY = Double.parseDouble(inputTokens[2]);
                boolean isInside = is_inside(shapes, pointX, pointY);
                if (isInside) {
                    System.out.println("1");
                } else {
                    System.out.println("0");
                }
                break;
            case "exit":
                return true;
        }
        return false;
    }

    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        String input;
        boolean isExit = false;
        LinkedList<Shape> shapes = new LinkedList<>();
        while (!isExit) {
            input = scanner.nextLine();
            String[] inputTokens = input.split(" ");
            isExit = handleCommand(inputTokens, shapes);
        }
        scanner.close();
    }
}