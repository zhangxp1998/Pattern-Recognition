import java.util.Arrays;
import java.util.Comparator;

import edu.princeton.cs.algs4.In;
import edu.princeton.cs.algs4.Stack;
import edu.princeton.cs.algs4.StdDraw;
import edu.princeton.cs.algs4.StdOut;

public class FastCollinearPoints
{
	private LineSegment lines[];

	// private Point min(Point p1, Point p2)
	// {
	// if (p1.compareTo(p2) < 0)
	// return p1;
	// else
	// return p2;
	// }
	//
	// private Point max(Point p1, Point p2)
	// {
	// if (p1.compareTo(p2) > 0)
	// return p1;
	// else
	// return p2;
	// }

	public FastCollinearPoints(Point[] points) // finds all line segments
												// containing 4 points
	{
		final Point[] old = points;
		points = points.clone();

		QuickX.sort(points);

		for (int i = 1; i < points.length; i++)
		{
			if (points[i].compareTo(points[i - 1]) == 0)
			{
				throw new IllegalArgumentException();
			}
		}
		
		Stack<LineSegment> stack = new Stack<LineSegment>();

		for (Point p : old)
		{
			Comparator<Point> order = p.slopeOrder();
			Point[] copy = points.clone();
			Arrays.sort(copy, order);
			int start = 0;
			double startSlope = copy[0].slopeTo(p);
			for (int i = 1; i < copy.length; i++)
			{
				if (copy[i].slopeTo(p) != startSlope)
				{
					if (i - start >= 3)
					{
						
						Point min = copy[start];
						Point max = copy[i - 1];
						if (p.compareTo(min) < 0)
						{
							stack.push(new LineSegment(p, max));
						}
					}
					start = i;
					startSlope = copy[i].slopeTo(p);
				}
			}
			if(copy.length - start >= 3)
			{
				Point min = copy[start];
				Point max = copy[copy.length - 1];
				if (p.compareTo(min) < 0)
				{
					stack.push(new LineSegment(p, max));
				}
			}
			
		}

		lines = new LineSegment[stack.size()];
		for (int i = 0; i < lines.length; i++)
		{
			lines[i] = stack.pop();
		}
	}

	public int numberOfSegments() // the number of line segments
	{
		return lines.length;
	}

	public LineSegment[] segments() // the line segments
	{
		return lines.clone();
	}

	public static void main(String[] args)
	{
		// read the n points from a file
		In in = new In(args[0]);
		int n = in.readInt();
		Point[] points = new Point[n];
		for (int i = 0; i < n; i++)
		{
			int x = in.readInt();
			int y = in.readInt();
			points[i] = new Point(x, y);
		}

		// draw the points
		StdDraw.enableDoubleBuffering();
		StdDraw.setXscale(0, 32768);
		StdDraw.setYscale(0, 32768);
		for (Point p : points)
		{
			p.draw();
		}
		StdDraw.show();

		// print and draw the line segments
		FastCollinearPoints collinear = new FastCollinearPoints(points);
		for (LineSegment segment : collinear.segments())
		{
			StdOut.println(segment);
			segment.draw();
		}
		StdDraw.show();
	}
}