package others.trianglify.generator.point;


import java.util.Vector;

import others.trianglify.domain.Point;

/**
 * Point generator
 *
 * @author manolovn
 */
public interface PointGenerator {

    Vector<Point> generatePoints(int width, int height);

    void setBleedX(int bleedX);

    void setBleedY(int bleedY);
}
