public class BruteCollinearPoints
{
	private LineSegment lines[];
	
	public BruteCollinearPoints(Point[] points) // finds all line segments
												// containing 4 points
	{
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