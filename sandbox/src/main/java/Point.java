public class Point {

    public double a;
    public double b;

    Point p1 = new Point(a, b);
    Point p2 = new Point(a, b);

    public Point(double a, double b){
        this.a = a;
        this.b = b;
    }

    /*public Point(){
    }*/

    public double distance(){
        return Math.sqrt(Math.pow((p1.a-p2.a),2)*Math.pow((p1.b-p2.b),2));
    }
}
