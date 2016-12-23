import edu.princeton.cs.algs4.Stack;

public class BruteCollinearPoints
{
	private LineSegment lines[];

	private boolean arePointsCollinear(Point p1, Point p2, Point p3, Point p4)
	{
		double s1 = p2.slopeTo(p1);
		double s2 = p3.slopeTo(p1);
		if (Double.compare(s1, s2) != 0)
			return false;

		double s3 = p4.slopeTo(p1);
		return Double.compare(s3, s1) == 0;
	}

	private static Point min(Point... arr)
	{
		Point min = arr[0];
		for (int i = 1; i < arr.length; i++)
		{
			if (arr[i].compareTo(min) < 0)
			{
				min = arr[i];
			}
		}
		return min;
	}

	private static Point max(Point... arr)
	{
		Point max = arr[0];
		for (int i = 1; i < arr.length; i++)
		{
			if (arr[i].compareTo(max) > 0)
			{
				max = arr[i];
			}
		}
		return max;
	}

	public BruteCollinearPoints(Point[] points) // finds all line segments
												// containing 4 points
	{
		Stack<LineSegment> list = new Stack<LineSegment>();

		for (int i = 0; i < points.length; i++)
		{
			Point p1 = points[i];
			for (int j = i + 1; j < points.length; j++)
			{
				Point p2 = points[j];
				for (int k = j + 1; k < points.length; k++)
				{
					Point p3 = points[k];
					for (int l = k + 1; l < points.length; l++)
					{
						Point p4 = points[l];
						if (arePointsCollinear(p1, p2, p3, p4))
						{
							Point min = min(p1, p2, p3, p4);
							Point max = max(p1, p2, p3, p4);
							list.push(new LineSegment(min, max));
						}
					}
				}
			}
		}
		lines = new LineSegment[list.size()];
		for(int i = 0; i < lines.length; i ++)
		{
			lines[i] = list.pop();
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