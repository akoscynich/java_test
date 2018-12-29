import org.testng.Assert;
import org.testng.annotations.Test;

public class PointTests {

    @Test
    public void testDistance(){
        Point p1 = new Point(1.0, 2.0);
        Point p2 = new Point(1.0, 2.0);
        Assert.assertEquals(p1.distance(p2), 0.0);
    }

    @Test
    public void testDistance2(){
        Point p1 = new Point(-1.0, 2.0);
        Point p2 = new Point(1.0, 0.0);
        Assert.assertEquals(p1.distance(p2), 2.8284271247461903);
    }

    @Test
    public void testDistance3(){
        Point p1 = new Point(1.0010, -2.0);
        Point p2 = new Point(198765.0, 2.34343);
        Assert.assertEquals(p1.distance(p2), 198763.99904745677);
    }

}