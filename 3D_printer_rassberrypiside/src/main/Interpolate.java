package main;

import java.util.stream.DoubleStream;

public class Interpolate {
	
	public double[] Interpolation(VecPoint Near_point, VecPoint Far_point, VecPoint Real_point) {
		//this is the ref img. https://en.wikipedia.org/wiki/File:Enclosing_points.svg
		//Starting Point is C000 and ending is C111
		double C000, C001, C010, C011, C100, C101, C110, C111;
		double x, y, z;
		
		x = (Real_point.X - Near_point.X) / (Far_point.X - Near_point.X);
		y = (Real_point.Y - Near_point.Y) / (Far_point.Y - Near_point.Y);
		z = (Real_point.Z - Near_point.Z) / (Far_point.Z - Near_point.Z);

        DoubleStream.Builder builder = DoubleStream.builder();

		for(int i = 0; i < Real_point.Joints.length; i++) {
		C000 = Near_point.Joints[i];
		C111 = Far_point.Joints[i];
		
		C001 = Near_point.move("Z", 1, Near_point).Joints[i];
		C010 = Near_point.move("Y", 1, Near_point).Joints[i];
		C011 = Far_point.move("X", -1, Far_point).Joints[i];
		C100 = Near_point.move("X", 1, Near_point).Joints[i];
		C101 = Far_point.move("Y", -1, Far_point).Joints[i];
		C110 = Far_point.move("Z", -1, Far_point).Joints[i];
		
		double C00, C01, C10, C11, C0, C1, C;
		
			
		
		C00 = C000 + x*(C100 - C000);
		C01 = C001 + x*(C101 - C001);
		C10 = C010 + x*(C110 - C010);
		C11 = C011 + x*(C111 - C011);

		C0 = C00 + y*(C10-C00);
		C1 = C01 + y*(C11 - C01);
		
		C = C0 + z*(C1 - C0);
		
		builder.add(C);
		}
		
		double[] final_array = builder.build().toArray();
		
		Real_point.Joints = final_array;
		
		return Real_point.Joints;
	}
	
}
