package armKinematics;

import java.util.Arrays;

public class ArmRunner {
  public static void main(String[] args) {
		Arm testArm = new Arm(3, 2, 1);
		
		System.out.print(testArm.getEndpoint());

		testArm.setTarget(new Point(-3, 4));
		
		double error = 10;
		
		int count = 0;
		
		while (error > 0.05) {
			testArm.periodic();
//			System.out.print(testArm.getEndpoint());
			error = testArm.getError();
		}
		
		System.out.print(Arrays.toString(testArm.getAngles()));
  }
}