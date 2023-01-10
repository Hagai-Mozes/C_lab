import Shapes
import java.util.LinkedList;
import java.util.Scanner;
import java.util.Math;

public class Main {
    private static Shape createShape(String[] inputTokens){
        String shapeType = inputTokens[1];
        String color = inputTokens[2].toLowerCase();
        switch (shapeType) {
            case "circle":
                double centerX = Double.parseDouble(inputTokens[3]);
                double centerY = Double.parseDouble(inputTokens[4]);
                double radius = Double.parseDouble(inputTokens[5]);
                Point center = new Point(centerX, centerY);
                Circle shape = new Circle(center, radius, color);
                break;
            case "ellipse":
                double centerAX = Double.parseDouble(inputTokens[3]);
                double centerAY = Double.parseDouble(inputTokens[4]);
                double centerBX = Double.parseDouble(inputTokens[5]);
                double centerBY = Double.parseDouble(inputTokens[6]);
                double D = Double.parseDouble(inputTokens[7]);
                Point centerA = new Point(centerAX, centerAY);
                Point centerB = new Point(centerBX, centerBY);
                Ellipse shape = new Ellipse(centerA, centerB, D, color);
                break;
            case "triangle":
                double pointAX = Double.parseDouble(inputTokens[3]);
                double pointAY = Double.parseDouble(inputTokens[4]);
                double pointBX = Double.parseDouble(inputTokens[5]);
                double pointBY = Double.parseDouble(inputTokens[6]);
                double pointCX = Double.parseDouble(inputTokens[7]);
                double pointCY = Double.parseDouble(inputTokens[8]);
                Point pointA = new Point(pointAX, pointAY);
                Point pointB = new Point(pointBX, pointBY);
                Point pointC = new Point(pointCX, pointCY);
                Triangle shape = new Triangle(pointA, pointB, pointC, color);
                break;
            case "rectangle":
                double pointAX = Double.parseDouble(inputTokens[3]);
                double pointAY = Double.parseDouble(inputTokens[4]);
                double pointCX = Double.parseDouble(inputTokens[5]);
                double pointCY = Double.parseDouble(inputTokens[6]);
                Point pointA = new Point(pointAX, pointAY);
                Point pointC = new Point(pointCX, pointCY);
                Rectangle shape = new Rectangle(pointA, pointC, color);
                break;
            case "square":
                double centerX = Double.parseDouble(inputTokens[3]);
                double centerY = Double.parseDouble(inputTokens[4]);
                double edgeLength = Double.parseDouble(inputTokens[5]);
                Point center = new Point(centerX, centerY);
                Square shape = new Square(center, edgeLength, color);
                break;
            case "parallelogram":
                double pointAX = Double.parseDouble(inputTokens[3]);
                double pointAY = Double.parseDouble(inputTokens[4]);
                double pointBX = Double.parseDouble(inputTokens[5]);
                double pointBY = Double.parseDouble(inputTokens[6]);
                double pointCX = Double.parseDouble(inputTokens[7]);
                double pointCY = Double.parseDouble(inputTokens[8]);
                Point pointA = new Point(pointAX, pointAY);
                Point pointB = new Point(pointBX, pointBY);
                Point pointC = new Point(pointCX, pointCY);
                Parallelogram shape = new Parallelogram(pointA, pointB, pointC, color);
                break;
        }
        return shape;
    }
    private static int newShape(String[] inputTokens, ArrayList<Shape> shapes){
        Shape shape = createShape(inputTokens);
        shapes.add(shape);
        return shape.getID();
    }
    private static void deleteShape(int id, ArrayList<Shape> shapes){
        for (int i = 0; i < shapes.size(); i++) {
            if (shapes.get(i).getID() == id) {
                shapes.remove(i);
                break;
            }
        }
    }
    private static void moveShape(int id, double deltaX, double deltaY, ArrayList<Shape> shapes){
        for (int i = 0; i < shapes.size(); i++) {
            if (shapes.get(i).getID() == id) {
                shapes.get(i).move(deltaX, deltaY);
                break;
            }
        }
    }
    private static int copyShape(int id, double deltaX, double deltaY, ArrayList<Shape> shapes){
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
    private static void colorShape(String color, int id, ArrayList<Shape> shapes){
        for (int i = 0; i < shapes.size(); i++) {
            if (shapes.get(i).getID() == id) {
                shapes.get(i).setColor(color);
                break;
            }
        }
    }
    private static double totalArea(ArrayList<Shape> shapes){
        double totalArea = 0;
        for (int i = 0; i < shapes.size(); i++) {
            totalArea += shapes.get(i).getArea();
        }
        return totalArea;
    }
    private static double totalCircumference(ArrayList<Shape> shapes){
        double totalCircumference = 0;
        for (int i = 0; i < shapes.size(); i++) {
            totalCircumference += shapes.get(i).getCircumference();
        }
        return totalCircumference;
    }
    private static boolean is_inside(ArrayList <Shape> shapes, double pointX, double pointY){
        Point point = new Point(pointX, pointY);
        for (int i = 0; i < shapes.size(); i++) {
            if (shapes.get(i).isInside(point)) {
                return true;
            }
        }
        return false;
    }
    private static boolean handleCommand(String[] inputTokens, ArrayList<Shape> shapes){
        String command = inputTokens[0].toLowerCase();
        switch (command) {
            case "new":
                int id = newShape(inputTokens, shapes);
                System.out.println(id);
                break;
            case "delete":
                int id = Integer.parseInt(inputTokens[1]);
                deleteShape(id, shapes);
                break;
            case "move":
                int id = Integer.parseInt(inputTokens[1]);
                double deltaX = Double.parseDouble(inputTokens[2]);
                double deltaY = Double.parseDouble(inputTokens[3]);
                moveShape(id, deltaX, deltaY, shapes);
                break;
            case "copy":
                int id = Integer.parseInt(inputTokens[1]);
                double deltaX = Double.parseDouble(inputTokens[2]);
                double deltaY = Double.parseDouble(inputTokens[3]);
                int id = copyShape(id, deltaX, deltaY, shapes);
                System.out.println(id);
                break;
            case "color":
                String color = inputTokens[1].toLowerCase();
                int id = Integer.parseInt(inputTokens[2]);
                colorShape(color, id, shapes);
                break;
            case "area":
                double area = totalArea(shapes);
                System.out.printf("%.2f", area);
                break;
            case "circumference":
                double circumference = totalCircumference(shapes);
                System.out.printf("%.2f", circumference);
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
                break;
        }
        return false;
    }

    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        String input = scanner.nextLine();
        boolean isExit = false;
        list<Shape> shapes = new LinkedList<>();
        while (!isExit) {
            String[] inputTokens = input.split(" ");
            isExit = handleCommand(inputTokens, shapes);
            input = scanner.nextLine();
        }   
    }
}