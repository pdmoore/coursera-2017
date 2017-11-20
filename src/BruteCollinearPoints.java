import java.awt.*;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class BruteCollinearPoints {

    private int numSegments;
    private List<LineSegment> lineSegments;

    public BruteCollinearPoints(Point[] points) {
        if (points == null) throw new IllegalArgumentException();

        Point[] sortedPoints = Arrays.copyOf(points, points.length);
        try {
            Arrays.sort(sortedPoints);
        } catch (NullPointerException e) {
            throw new IllegalArgumentException();
        }

        validatePointsNotDuplicated(sortedPoints);

        lineSegments = new ArrayList<>();
        findLindSegments(sortedPoints);
    }

    private void findLindSegments(Point[] sortedPoints) {
        for (int p = 0; p < sortedPoints.length; p++) {
            for (int q = p + 1; q < sortedPoints.length; q++) {
                for (int r = q + 1; r <sortedPoints.length; r++) {
                    for (int s =  r + 1; s < sortedPoints.length; s++) {

                        double slope_pq = sortedPoints[p].slopeTo(sortedPoints[q]);
                        double slope_pr = sortedPoints[p].slopeTo(sortedPoints[r]);
                        double slope_ps = sortedPoints[p].slopeTo(sortedPoints[s]);
                        if (slope_pq == slope_pr && slope_pq == slope_ps) {
                            LineSegment lineSegment = new LineSegment(sortedPoints[p], sortedPoints[s]);
                            lineSegments.add(lineSegment);
                        }
                    }
                }
            }
        }
    }

    private void validatePointsNotDuplicated(Point[] sortedPoints) {
        for (int i = 1; i < sortedPoints.length; i++) {
            if (sortedPoints[i-1].compareTo(sortedPoints[i]) == 0) throw new IllegalArgumentException("Duplicate point at " + sortedPoints[i].toString());
        }
    }

    public int numberOfSegments() {
        return lineSegments.size();
    }

    public LineSegment[] segments() {
        return lineSegments.toArray(new LineSegment[lineSegments.size()]);
    }
}
