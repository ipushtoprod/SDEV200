public class Exercise09_09 {
  /** the main */  
  public static void main(String[] args) {

      RegularPolygon polygon1 = new RegularPolygon();
      RegularPolygon polygon2 = new RegularPolygon(6, 4);
      RegularPolygon polygon3 = new RegularPolygon(10, 4, 5.6, 7.8);

      System.out.println("Polygon 1:");
      System.out.println("Perimeter: " + polygon1.getPerimeter());
      System.out.println("Area: " + polygon1.getArea());
      
      System.out.println("\nPolygon 2:");
      System.out.println("Perimeter: " + polygon2.getPerimeter());
      System.out.println("Area: " + polygon2.getArea());
      
      System.out.println("\nPolygon 3:");
      System.out.println("Perimeter: " + polygon3.getPerimeter());
      System.out.println("Area: " + polygon3.getArea());
    }
  }
  /** by default, 3 sides, 1.0 length for each, and an x-y cord of 0,0 */
  class RegularPolygon {
    private int n = 3;          
    private double side = 1.0;  
    private double x = 0.0;     
    private double y = 0.0;     
    
    /** default values */
    public RegularPolygon() {
    }
    
    /** constructor with just number of sides and length of sides*/
    public RegularPolygon(int n, double side) {
      this.n = n;
      this.side = side;
      this.x = 0;
      this.y = 0;
    }
    
    /** constructor with all 4 */
    public RegularPolygon(int n, double side, double x, double y) {
      this.n = n;
      this.side = side;
      this.x = x;
      this.y = y;
    }

    public int getN() {
      return n;
    }
    
    public double getSide() {
      return side;
    }
    
    public double getX() {
      return x;
    }
    
    public double getY() {
      return y;
    }
    
    public void setN(int n) {
      this.n = n;
    }
    
    public void setSide(double side) {
      this.side = side;
    }
    
    public void setX(double x) {
      this.x = x;
    }
    
    public void setY(double y) {
      this.y = y;
    }

    public double getPerimeter() {
      return n * side;
    }

    public double getArea() {
      return (n * side * side) / (4 * Math.tan(Math.PI / n));
    }
  }
