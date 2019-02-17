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
 * The class SimplePolygon implements the Polygon interface.
 *
 * It is intended to be further extended by ConvexPolygon.
 *
 * @author Andy Mirzaian
 */
public class SimplePolygon implements Polygon {

	/********* protected fields ************************/

	protected int n; // number of vertices of the polygon
	protected Point2D.Double[] vertices; // vertices[0..n-1] around the polygon boundary

	/********* protected constructors ******************/

	/**
	 * constructor used in the static factory method getNewPoly()
	 *
	 * @param size
	 *            number of edges (also vertices) of the polygon
	 */
	protected SimplePolygon(int size) {
		n = size;
		// TODO: place the rest of your code here
		vertices = new Point2D.Double[size];
	}

	/** default no-parameter constructor */
	protected SimplePolygon() {
		n = 0; // make sure to init so it dont crash.
		vertices = new Point2D.Double[n];
	}

	/********* public getters & toString ***************/

	/**
	 * static factory method constructs and returns an unverified simple-polygon, initialised
	 * according to user provided input data. Runs in O(n) time.
	 *
	 * @return an unverified simple-polygon instance
	 */
	public static SimplePolygon getNewPoly() {
        Scanner sc = new Scanner(System.in);
        System.out.print("Input the Poly description: ");
		String dis = sc.next();
		Point2D.Double[] p = getPoints(dis);
		SimplePolygon polygon = new SimplePolygon(p.length);
		polygon.vertices = p;
		return polygon;
	}


	public static SimplePolygon getNewPoly(String desc) {
		Point2D.Double[] p = getPoints(desc);
		SimplePolygon sp = new SimplePolygon(p.length);
		sp.vertices = p;
		return sp;
	}

	private static Point2D.Double[] getPoints(String description) {
		String[] points = description.split(" ");
		int num = Integer.parseInt(points[0]);
		Point2D.Double[] p = new Point2D.Double[num];
		int j = 0;
		for (int i = 1; i < points.length; i += 2) {
			double x = Double.parseDouble(points[i]);
			double y = Double.parseDouble(points[i + 1]);
			p[j++] = new Point2D.Double(x, y);
		}
		return p;
	}

	/**
	 *
	 * @return n, the number of edges (equivalently, vertices) of the polygon.
	 */
	public int getSize() {
		return n;
	}

	/**
	 *
	 * @param i
	 *            index of the vertex.
	 * @return the i-th vertex of the polygon.
	 * @throws IndexOutOfBoundsException
	 *             if {@code i < 0 || i >= n }.
	 */
	public Point2D.Double getVertex(int i) throws IndexOutOfBoundsException {
        try {
            return vertices[i];
        } catch (IndexOutOfBoundsException e) {
            e.printStackTrace();
            throw e;
        }
	}

	/**
	 * @return a String representation of the polygon in O(n) time.
	 */
	@Override
	public String toString() {
        StringBuilder sb = new StringBuilder();
        for (int i = 0; i < vertices.length; i++) {
            sb.append(vertices[i].toString() + ",");
        }
        String points = sb.substring(0, sb.length() - 1);
        return String.format("SimplePolygon(%d)[%s]", n, points);
	}

	/************** utilities *********************/

	/**
	 *
	 * @param a
	 * @param b
	 * @param c
	 *            three input points.
	 * @return twice the signed area of the oriented triangle (a,b,c). Runs in O(1) time.
	 */
	public static double delta(Point2D.Double a, Point2D.Double b,
			Point2D.Double c) {
		return (a.x * b.y - a.x * c.y) + (a.y * c.x - a.y * b.x) + (b.x * c.y - c.x * b.y);
	}

//    public static boolean isCross(Point2D.Double a, Point2D.Double b,
//								  Point2D.Double c, Point2D.Double d) {
//		Point2D.Double p = closest2Line(a, b, c);
//		Point2D.Double q = closest2Line(a, b, d);
//		double d1 = Math.sqrt(Math.pow(p.x - c.x, 2) + Math.pow(p.y - c.y, 2));
//		double d2 = Math.sqrt(Math.pow(q.x - d.x, 2) + Math.pow(q.y - d.y, 2));
//		if (!inRect(a, b, p)) return false;
//		if (d1 == 0 || d2 == 0) return true;
//
//		double m = (a.y - b.y) / (a.x - b.x);
//		if (m != 0) {
//			int orb = orb(a, b, d);
//			if (orb == 0) {
//				return q.y < d.y == p.y < c.y || q.y > d.y == p.y > c.y;
//			} else {
//				return m > 0 == (orb > 0 == q.y > c.y);
//			}
//		}
//
//		return p.y > c.y == p.y < d.y;
//	}

	/**
	 * Conver delta data into turing position
	 *
	 * @param  a
	 * @param  b
	 * @param  c
	 *            three input points.
	 * @return   1 left, -1 right, 0 no turn
	 */
	public static int orb(Point2D.Double a, Point2D.Double b,
						  Point2D.Double c) {
		int i = (int) delta(a, b, c);

		if (i == 0)
			return 0;
		return i < 0 ? -1 : 1;
	}

	/**
	 * Calculate two points distance
	 *
	 * @param  a
	 * @param  b
	 *            two input points.
	 * @return   distance
	 */
	public static double distance(Point2D.Double a, Point2D.Double b) {
		return Math.sqrt(Math.pow(a.x + b.x, 2) + Math.pow(a.y + b.y, 2));
	}

	/**
	 * if a point is in a rectangle
	 *
	 * @param  a one point of rectangle
	 * @param  b diagonal point of rectangle
	 * @param  c the point got to test
	 * @return   is point in rectangle
	 */
	public static boolean inRect(Point2D.Double a, Point2D.Double b,
								 Point2D.Double c) {
		return Math.max(a.x, b.x) >= c.x && Math.max(a.y, b.y) >= c.y && Math.min(a.x, b.x) <= c.x && Math.min(a.y, b.y) <= c.y;
	}

	/**
	 * calculate the closest point to line
	 *
	 * @param  a point of line
	 * @param  b point of line
	 * @param  c point to calculate
	 * @return   the closest point on line to point c
	 */
	public static Point2D.Double closest2Line(Point2D.Double a, Point2D.Double b,
											  Point2D.Double c) {
		Point2D.Double ab = new Point2D.Double(b.x - a.x, b.y - a.y);
		Point2D.Double ac = new Point2D.Double(c.x - a.x, c.y - a.y);
		double proj = (ab.x * ac.x + ab.y * ac.y) / (ab.x * ab.x + ab.y * ab.y);
		Point2D.Double pv = new Point2D.Double(proj * ab.x, proj * ab.y);
		return new Point2D.Double(a.x + pv.x, a.y + pv.y);
	}

	/**
	 * @param a
	 * @param b
	 *            end points of line-segment (a,b).
	 * @param c
	 * @param d
	 *            end points of line-segment (c,d).
	 * @return true if closed line-segments (a,b) and (c,d) are disjoint. Runs in O(1) time.
	 */
	public static boolean disjointSegments(Point2D.Double a, Point2D.Double b,
			Point2D.Double c, Point2D.Double d) {
//		return !isCross(a, b, c, d);
	    int d1 = orb(a, b, c);
	    int d2 = orb(a, b, d);
        int d3 = orb(c, d, a);
        int d4 = orb(c, d, b);

		if (d1 == 0)
			return !inRect(a, b, c);
		if (d2 == 0)
			return !inRect(a, b, d);
		if (d3 == 0)
			return !inRect(c, d, a);
		if (d4 == 0)
			return !inRect(c, d, b);
		if (d1 != d2 && d3 != d4) return false;
		if (d1 == d2 && d3 == d4) return true;

		return true;
	}

	/**
	 * @param i
	 * @param j
	 *            indices of two edges of the polygon.
	 * @return true if the i-th and j-th edges of the polygon are disjoint. Runs in O(1) time.
	 * @throws IndexOutOfBoundsException
	 *             if i or j are outside the index range [0..n-1].
	 */
	public boolean disjointEdges(int i, int j) throws IndexOutOfBoundsException {
        try {
            return disjointSegments(vertices[i - 1], vertices[i], vertices[j - 1], vertices[j]);
        } catch (IndexOutOfBoundsException e) {
            e.printStackTrace();
            throw e;
        }
	}

	/**
	 * This method verifies whether the claimed "simple-polygon" is indeed simple.
	 *
	 * @return true if the polygon is simple. Runs in O(n^2) time.
	 */
	public boolean isSimple() {
		for (int i = 1; i < vertices.length - 2; i++) {
			for (int j = i + 2; j < vertices.length - 1; j++) {
				if (!disjointEdges(i, j)) {
//                    System.out.printf("overlap at %d and %d\n", i, j);
					return false;
				}
			}
		}
		return true;
	}

	/************ perimeter & area ***************/

	/**
	 *
	 * @return the sum of the edge lengths of the polygon. Runs in O(n) time.
	 */
	public double perimeter() {
		double result = 0.0;
		Point2D.Double o = new Point2D.Double(0.0, 0.0);
		for (int i = 0; i < vertices.length; i++) {
			result += distance(vertices[i], vertices[(i + 1) % vertices.length]);
		}
		return result;
	}

	/**
	 *
	 * @return area of the polygon interior. Runs in O(n) time not counting the simplicity test.
	 * @throws NonSimplePolygonException
	 *             if the polygon is non-simple.
	 */
	public double area() throws NonSimplePolygonException {
            if (!isSimple()) throw new NonSimplePolygonException();
            double result = 0.0;
            Point2D.Double o = new Point2D.Double(0.0, 0.0);
            for (int i = 0; i < vertices.length; i++) {
                result += delta(o, vertices[i], vertices[(i + 1) % vertices.length]);
            }
            return result / 2;
	}

	/**
	 * Test if the point in the polygon
	 *
	 * @param  p                         point to test
	 * @return                           if point in polygon
	 * @throws NonSimplePolygonException if it is not a simple polygon
	 */
	public boolean contains(Point2D.Double p) throws NonSimplePolygonException {
		if (!isSimple()) throw new NonSimplePolygonException();
		Point2D.Double right = new Point2D.Double(0, p.y);

		int count = 0;
		for (int i = 0; i < vertices.length; i++) {
			if (!disjointSegments(p, right, vertices[i], vertices[(i + 1) % vertices.length])) {
				if (closest2Line(p, right, vertices[i]).equals(vertices[i])) {
					count--;
				}
				count++;
			}
		}

		return count % 2 == 1;
	}
}
