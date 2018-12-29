public class HelloWorld {

    public static void main(String[] args) {
        Point p1 = new Point(1.0010, -2.0);
        Point p2 = new Point(198765.0, 2.34343);

        //System.out.println("Hello World!");
        //System.out.println("Расстояние = " + distance(p1, p2));

        System.out.println("Расстояние от точки с координатами " + p1.a+ ", " + p1.b + " до точки " + p2.a + ", " + p2.b + " = " + p1.distance(p2));

    }

    //public static double distance(Point p1, Point p2){
    //    return Math.sqrt(Math.pow((p1.a-p2.a),2)+Math.pow((p1.b-p2.b),2));
    //}

}
