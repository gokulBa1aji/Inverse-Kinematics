package armKinematics;

import java.util.function.DoubleSupplier;
import java.util.Arrays;

public class Arm {

	private double[] m_lengths;
	private double[] m_angles;

	private Point m_target;

	double increment = 0.001;
	
	public Arm(double... lengths) {
		m_lengths = lengths;
		m_angles = new double[m_lengths.length];

		m_target = getEndpoint();
	}

	public Point getEndpoint() {
		return getEndpoint(m_angles);
	}

	public Point getEndpoint(double[] angles) {
		double x = 0;
		double y = 0;
		for (int i=0; i<m_lengths.length; i++) {
			x += m_lengths[i] * Math.cos(angles[i]);
			y += m_lengths[i] * Math.sin(angles[i]);
		}
		return new Point(x, y);
	}

	public void setTarget(Point target) {
		this.m_target = target;
	}

	public void periodic() {
		double[] errorAngleDerivs = new double[m_angles.length];
		
		for (int i=0; i<m_angles.length; i++) {
			double[] tempAngles = Arrays.copyOf(m_angles, m_angles.length);
			tempAngles[i] += increment;
			
			double errorDeriv = (m_target.distance(getEndpoint(tempAngles)) - m_target.distance(getEndpoint(m_angles))) / increment;
			errorAngleDerivs[i] = errorDeriv;
		}

		for (int i=0; i<m_angles.length; i++) {
			m_angles[i] -= errorAngleDerivs[i] * 0.01 * getAdjustmentScalar();
		}
	}

	public double[] getAngles() {
		return m_angles;
	}

	public double getError() {
		return m_target.distance(getEndpoint());
	}

	public double getAdjustmentScalar() {
		double error = Math.min(getError(), 1);
		return Math.pow(1 - Math.pow(error - 1, 2), 0.5);
	}
	
}