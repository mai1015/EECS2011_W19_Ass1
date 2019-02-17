/**********************************************************
 * EECS2011: Fundamentals of Data Structures,  Winter 2019
 * Assignment 1: Polygon Hierarchy
 * Section: Z
 * Student Name: Zhili Mai
 * Student eecs account:  mai1015
 * Student ID number:  215234842
 **********************************************************/
package A1;

import java.awt.geom.Point2D;
import java.util.Scanner;

/**
 * The class ConvexPolygon extends SimplePolygon.
 * 
 * TODO:_______ Add more Javadoc comments here. ______ ???
 * 
 * @author Andy Mirzaian
 */
public class ConvexPolygon extends SimplePolygon {

    protected ConvexPolygon(int size) {
        super(size);
    }

    protected ConvexPolygon() {
        super();
    }

    public ConvexPolygon(SimplePolygon polygon) {
        vertices = polygon.vertices;
        n = polygon.n;
    }

    public static ConvexPolygon getNewPoly() {
        SimplePolygon polygon = SimplePolygon.getNewPoly();
        return new ConvexPolygon(polygon);
    }

    public static ConvexPolygon getNewPoly(String desc) {
        SimplePolygon polygon = SimplePolygon.getNewPoly(desc);
        return new ConvexPolygon(polygon);
    }

    /**
     * returns true iff the polygon is convex,
     * with precondition that the polygon is simple.
     * This method runs in 𝑂(𝑛) time.
     * If the polygon is non-simple, the correctness of
     * the returned result is not guaranteed.
     * @return if the polygon is convex
     */
    public boolean isConvex() {
        boolean r = false;
        int l = 0;
        for (int i = 0; i < vertices.length - 1; i++) {
            int t = orb(vertices[i], vertices[i + 1], vertices[(i + 2) % vertices.length]);
            l += t;
            if (t == -1) r = true;
        }
        return (l > 0) != r ;
    }
}
