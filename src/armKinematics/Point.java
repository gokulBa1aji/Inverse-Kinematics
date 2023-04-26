package armKinematics;

public class Point {
	private double x;
	private double y;

	public Point(double x, double y) {
		this.x = x;
		this.y = y;
	}

	public double distance(Point other) {
		return Math.hypot(x - other.getX(), y - other.getY());
	}

	public double getX() {
		return x;
	}

	public double getY() {
		return y;
	}

	@Override
	public String toString() {
		return "\\left(" + Math.round(x * 100) / 100.0 + "," + Math.round(y * 100) / 100.0 + "\\right),";
	}
}