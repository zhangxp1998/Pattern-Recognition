import edu.princeton.cs.algs4.Stack;

public class FasterCollinearPoints
{
	private LineSegment lines[];

	public FasterCollinearPoints(Point[] points) // finds all line segments
												// containing 4 points
	{
		Stack<LineSegment> stack = new Stack<LineSegment>();
		
		

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