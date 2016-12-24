import java.util.Arrays;
import java.util.Comparator;

import edu.princeton.cs.algs4.Stack;

public class FastCollinearPoints
{
	private LineSegment lines[];

	private Point min(Point p1, Point p2)
	{
		if (p1.compareTo(p2) < 0)
			return p1;
		else
			return p2;
	}

	private Point max(Point p1, Point p2)
	{
		if (p1.compareTo(p2) > 0)
			return p1;
		else
			return p2;
	}

	public FastCollinearPoints(Point[] points) // finds all line segments
												// containing 4 points
	{
		final Point[] old = points;
		points = points.clone();

		QuickX.sort(points);

		Stack<LineSegment> stack = new Stack<LineSegment>();

		for (Point p : old)
		{
			Comparator<Point> order = p.slopeOrder();
			Arrays.sort(points, order);
			int start = 0;
			double startSlope = points[0].slopeTo(p);
			for (int i = 1; i < points.length; i++)
			{
				if (points[i].slopeTo(p) != startSlope)
				{
					if (i - start >= 3)
					{
						stack.push(new LineSegment(max(points[start], p), min(points[i - 1], p)));
						start = i;
						startSlope = points[i].slopeTo(p);
					}
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
}