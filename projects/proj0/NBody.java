public class NBody{
	public static double readRadius(String filename){
		In in = new In(filename);
		int firstItemInFile = in.readInt();
		double Radius = in.readDouble();
		return Radius;
	}
	
	public static Body[] readBodies(String filename){
		In in = new In(filename);
		int num_of_b = in.readInt();
		in.readDouble();
		Body[] bodies = new Body[num_of_b];
		
		for (int i = 0; i < num_of_b; i = i + 1){
			bodies[i] = new Body(in.readDouble(), in.readDouble(), in.readDouble(),
			                   in.readDouble(), in.readDouble(), in.readString());
		}
		return bodies;
	}
	
	public static void main(String[] args){
		double T = Double.parseDouble(args[0]);
		double dt = Double.parseDouble(args[1]);
		String filename = args[2];
		double radius = readRadius(filename);
		Body[] bodies = readBodies(filename);
		In in = new In(filename);
		int num_of_b = in.readInt();
		
		String background = "images/starfield.jpg";
		StdDraw.setScale(-2.5e11, 2.5e11);
		StdDraw.enableDoubleBuffering();
		
		StdDraw.picture(0, 0, background);
		for (Body b: bodies){
			b.draw();
		}
		StdDraw.show();
		StdAudio.play("./audio/2001.mid");
		StdDraw.pause(10);
		
		double time = 0;
		double[] xForces = new double[num_of_b];
		double[] yForces = new double[num_of_b];
		
		while (time <= T){ 
			for (int i = 0; i < num_of_b; i++){
				xForces[i] = bodies[i].calcNetForceExertedByX(bodies);
				yForces[i] = bodies[i].calcNetForceExertedByY(bodies);
			}
			for (int i = 0; i < num_of_b; i++){
				bodies[i].update(dt, xForces[i], yForces[i]);
			}
			StdDraw.picture(0, 0, background);
			for (Body b: bodies){
			b.draw();
			}
			StdDraw.show();
			StdDraw.pause(10);
			time = time + dt;
        } 
		
		StdOut.printf("%d\n", bodies.length);
		StdOut.printf("%.2e\n", radius);
		for (int i = 0; i < bodies.length; i++) {
			StdOut.printf("%11.4e %11.4e %11.4e %11.4e %11.4e %12s\n",
						  bodies[i].xxPos, bodies[i].yyPos, bodies[i].xxVel,
						  bodies[i].yyVel, bodies[i].mass, bodies[i].imgFileName);   
		}
	}
}