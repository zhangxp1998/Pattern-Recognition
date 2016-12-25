import java.util.HashMap;
import java.util.Map;
import java.util.Map.Entry;

import edu.princeton.cs.algs4.Stack;

public class FasterCollinearPoints
{
	private LineSegment lines[];

	private static void inc(Map<Double, Integer> map, double key)
	{
		Integer count = map.get(key);
		if (count == null)
		{
			count = 0;
		}
		map.put(key, count + 1);
	}

	private static Map<Double, Integer> getSlopeMap(Point[] points, Point p)
	{
		Map<Double, Integer> map = new HashMap<Double, Integer>(points.length / 4);
		for (Point q : points)
		{
			inc(map, q.slopeTo(p));
		}
		return map;
	}

	public FasterCollinearPoints(Point[] points) // finds all line segments
													// containing 4 points
	{
		Stack<LineSegment> stack = new Stack<LineSegment>();
		Point[] sorted = points.clone();
		QuickX.sort(sorted);

		for (Point p : points)
		{
			Map<Double, Integer> map = getSlopeMap(points, p);
			for (Entry<Double, Integer> entry : map.entrySet())
			{
				if (entry.getValue() >= 3)
				{
					Point max = null;
					Point min = null;
					for (Point q : sorted)
					{
						if (q.slopeTo(p) == entry.getKey())
						{
							if (min == null)
							{
								min = q;
							}
							max = q;
						}
					}
					if (p.compareTo(min) < 0)
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
}