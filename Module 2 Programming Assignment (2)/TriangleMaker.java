import java.util.Scanner;

public class TriangleMaker {
    public static void main(String[] args) {
        Scanner input = new Scanner(System.in);
        
        System.out.print("Enter the three sides: ");
        double side1 = input.nextDouble();
        double side2 = input.nextDouble();
        double side3 = input.nextDouble();
        
        System.out.print("Enter the color: ");
        String color = input.next();
        
        System.out.print("Is the triangle filled? (true/false)");
        boolean filled = input.nextBoolean();
        
        Triangle triangle = new Triangle(side1, side2, side3);
        triangle.setColor(color);
        triangle.setFilled(filled);
        
        System.out.println("\nTriangle properties:");
        System.out.printf("Area: %.2f\n", triangle.getArea());
        System.out.printf("Perimeter: %.2f\n", triangle.getPerimeter());
        System.out.println("Color: " + triangle.getColor());
        System.out.println("Filled: " + triangle.isFilled());
        System.out.println(triangle.toString());
        
        input.close();
    }
}

class Triangle extends GeometricObject {
    private double side1;
    private double side2;
    private double side3;

    //default 
    public Triangle() {
        this(1.0, 1.0, 1.0);
    }

    //given inputs
    public Triangle(double side1, double side2, double side3) {
        this.side1 = side1;
        this.side2 = side2;
        this.side3 = side3;
    }

    //getters
    public double getSide1() {
        return side1;
    }

    public double getSide2() {
        return side2;
    }

    public double getSide3() {
        return side3;
    }

    //heron's formula
    @Override
    public double getArea() {
        double s = (side1 + side2 + side3) / 2;
        return Math.sqrt(s * (s - side1) * (s - side2) * (s - side3));
    }

    //perim
    @Override
    public double getPerimeter() {
        return side1 + side2 + side3;
    }

    //string rep
    @Override
    public String toString() {
        return "Triangle: side1 = " + side1 + " side2 = " + side2 +" side3 = " + side3;
    }
}
