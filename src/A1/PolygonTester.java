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

/**
 * PolygonTester should enable a thorough testing of the polygon hierarchy.
 * 
 * It should provide an easy to read input-output recording of the test cases.
 * 
 * The student should also submit these recorded test results in TestIO.txt file as part
 * of Assignment1.
 * 
 * @author Andy Mirzaian
 */
public class PolygonTester {


	private static String[] testPoly = {"5 8.9 21.8 29.1 8.8 39.2 20.3 14 11 28 25",
			"7 28 2 31 5 28 10 14 14 5 10 8 4 18 1",
			"9 6 10 20 3 23 3 23 8 27 3 30 3 20 15 16 5 20 14",
			"13 5 6 13 2 12 6 20 2 16 12 17 11 19 5 13 11 19 15 8 12 14 7 5 11 9 6",
			"13 5 6 13 2 12 6 20 2 18 12 17 11 19 5 13 11 19 15 8 12 14 7 5 11 9 6",
			"22 14 7 15 8 17 7 17 5 15 6 14 4 12 6 11 9 15 11 7 12 8 11 7 9 10 11 8 6 10 5 11 3 16 3 18 4 19 8 16 9 14 9 13 8",
			"4 6 1 9 5 5 8 2 4"};

	private static Point2D.Double[] testPoint = {new Point2D.Double(),
			new Point2D.Double(14, 10), new Point2D.Double(),
			new Point2D.Double(), new Point2D.Double(8, 8),
			new Point2D.Double(7, 7), new Point2D.Double(2.2, 7.1)};

	// TODO: place additional test-helper methods here if you like
	public static void main(String[] args) {
		proj();
		delta();
		disjoin();
		polygon();
		contains();
	}

	private static void proj() {
		System.out.println("========= Proj Test =========");
		Point2D.Double p1 = new Point2D.Double(0, 3);
		Point2D.Double p2 = new Point2D.Double(2, -1);
		Point2D.Double p3 = new Point2D.Double(3, 2);

		Point2D.Double result = SimplePolygon.closest2Line(p1, p2, p3);
		System.out.printf("Expected:(1,1) Actual:(%.1f, %.1f)\n", result.x, result.y);
	}

	private static void delta() {
		System.out.println("========= Delta Test =========");
		Point2D.Double a = new Point2D.Double(1.0, 2.0);
		Point2D.Double b = new Point2D.Double(3.0, 4.0);
		Point2D.Double c = new Point2D.Double(5.0, 6.0);
		System.out.printf("Test1: %.2f (Expect: 0.0)\n", SimplePolygon.delta(a, b, c));

		Point2D.Double d = new Point2D.Double(5.0, 8.0);
		System.out.printf("Test2: %.2f (Expect: -4.0)\n", SimplePolygon.delta(c, b, d));
	}

	private static void disjoin() {
		System.out.println("========= Disjoin Test =========");
		Point2D.Double a = new Point2D.Double(1.0, 1.0);
		Point2D.Double b = new Point2D.Double(2.0, 2.0);
		Point2D.Double c = new Point2D.Double(3.0, 3.0);
		Point2D.Double d = new Point2D.Double(4.0, 4.0);
//		System.out.printf("Test1: %b (Expect: True)\n", SimplePolygon.isCross(a, b, c, d));
		System.out.printf("Test1: %b (Expect: True)\n", SimplePolygon.disjointSegments(a, b, c, d));

		a = new Point2D.Double(1.0, 1.0);
		b = new Point2D.Double(2.0, 2.0);
		c = new Point2D.Double(1.0, 2.0);
		d = new Point2D.Double(2.0, 1.0);
//		System.out.printf("Test2: %b (Expect: False)\n", SimplePolygon.isCross(a, b, c, d));
		System.out.printf("Test2: %b (Expect: False)\n", SimplePolygon.disjointSegments(a, b, c, d));

		c = new Point2D.Double(2.0, 1.0);
		d = new Point2D.Double(3.0, 2.0);
//		System.out.printf("Test3: %b (Expect: True)\n", SimplePolygon.isCross(a, b, c, d));
		System.out.printf("Test3: %b (Expect: True)\n", SimplePolygon.disjointSegments(a, b, c, d));

		c = new Point2D.Double(3.0, 2.0);
		d = new Point2D.Double(4.0, 1.0);
//		System.out.printf("Test4: %b (Expect: True)\n", SimplePolygon.isCross(a, b, c, d));
		System.out.printf("Test4: %b (Expect: True)\n", SimplePolygon.disjointSegments(a, b, c, d));

		b = new Point2D.Double(2.0, 3.0);
		c = new Point2D.Double(1.0, 2.0);
		d = new Point2D.Double(3.0, 1.0);
//		System.out.printf("Test5: %b (Expect: False)\n", SimplePolygon.isCross(a, b, c, d));
		System.out.printf("Test5: %b (Expect: False)\n", SimplePolygon.disjointSegments(a, b, c, d));

		a = new Point2D.Double(1.0, 1.0);
		b = new Point2D.Double(3.0, 3.0);
		c = new Point2D.Double(2.0, 2.0);
		d = new Point2D.Double(4.0, 4.0);
//		System.out.printf("Test6: %b (Expect: False)\n", SimplePolygon.isCross(a, b, c, d));
		System.out.printf("Test6: %b (Expect: False)\n", SimplePolygon.disjointSegments(a, b, c, d));
	}

	private static void polygon() {
		System.out.println("========= Polygon Test =========");
		for (int i = 0; i < testPoly.length; i++) {
			ConvexPolygon conv = ConvexPolygon.getNewPoly(testPoly[i]);
			System.out.printf("--------- %d ---------\n" +
							"vertex: %d\n" +
							"isSimple: %b\n" +
							"isConvex: %b\n",
					i + 1, conv.getSize(), conv.isSimple(), conv.isConvex());
			if (conv.isSimple()) {
				try {
					System.out.printf("area: %.2f\n", conv.area());
				} catch (NonSimplePolygonException e) {
					e.printStackTrace();
				}
			}
		}
	}

	private static void contains() {
		// T T F F
		System.out.println("========= Contains Test =========");
		System.out.println("Should be True True False False");
		for (int i = 0; i < testPoint.length; i++) {
			System.out.printf("--------- %d ---------\n", i + 1);
			ConvexPolygon conv = ConvexPolygon.getNewPoly(testPoly[i]);
			try {
				System.out.printf("Contains: %b\n", conv.contains(testPoint[i]));
			} catch (NonSimplePolygonException e) {
				System.out.println("Cannot test contain for non simple poly");
			}
		}
	}

}
