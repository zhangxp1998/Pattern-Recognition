import java.util.Arrays;
import java.util.Comparator;

import edu.princeton.cs.algs4.Stack;


public class FastCollinearPoints
{
	private LineSegment lines[];

	public FastCollinearPoints(Point[] points) // finds all line segments
												// containing 4 points
	{
		QuickX.sort(points);
		Point[] old = new Point[points.length];
		System.arraycopy(points, 0, old, 0, old.length);

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
					if(i - start >= 4)
					{
						stack.push(new LineSegment(points[start], points[i-1]));
						start = i;
						startSlope = points[i].slopeTo(p);
					}
				}
			}
		}
		
		lines = new LineSegment[stack.size()];
		for(int i = 0; i < lines.length; i ++)
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
		return lines;
	}
}