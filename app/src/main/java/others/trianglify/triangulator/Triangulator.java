package others.trianglify.triangulator;


import java.util.Vector;

import others.trianglify.domain.Point;
import others.trianglify.domain.Triangle;

/**
 * Triangulator behavior
 *
 * @author manolovn
 */
public interface Triangulator {

    Vector<Triangle> triangulate(Vector<Point> pointSet);
}
