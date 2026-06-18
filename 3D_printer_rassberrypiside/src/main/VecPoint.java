package main;

public class VecPoint {

	int X;
	int Y;
	int Z;
	int Angle;
	
	double[] Joints = new double[6];
	
	public VecPoint move(String Direction, int amount, VecPoint origin) {
		Direction.toUpperCase();
		VecPoint new_point = origin;
				
		switch(Direction) {
		case "X":
			new_point.X += amount;
			break;
		case "Y":
			new_point.Y += amount;
			break;
		case "Z":
			new_point.Z += amount;
			break;
		}
		
		return new_point;
	}
}
