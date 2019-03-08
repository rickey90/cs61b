public class NBody {
	//reads and returns the radius of the solar system
	public static double readRadius(String filePath) {
		In in = new In(filePath);

		int numPlanets = in.readInt();
		double radius = in.readDouble();

		return radius;
	}

	//creates a planet object for each planet in the given file. Then returns an array of planets
	public static Planet[] readPlanets(String filePath) {
		In in = new In(filePath);

		int numPlanets = in.readInt(); //get number of planets in file
		double radius = in.readDouble(); //get the radius
		Planet[] planets = new Planet[5]; //create an array for planet objects

		//loop to get the data for the planets
		for(int i = 0; i < numPlanets; i++) {
			planets[i] = new Planet(in.readDouble(), in.readDouble(), in.readDouble(), in.readDouble(), in.readDouble(), in.readString()); //use contructor to initalize member vars
		}

		return planets;
	}

	public static void main(String[] args) {
		double T = Double.parseDouble(args[0]);
		double dt = Double.parseDouble(args[1]);
		String filename = args[2];
		Planet[] planets = readPlanets(filename); //set planet data from info in file 

		//draw the background
		String bgImg = "images/starfield.jpg";
		StdDraw.setScale((-1 * readRadius(filename)), readRadius(filename));
		StdDraw.clear();
		StdDraw.picture(0, 0, bgImg);

		//draw all the planets
		for(int i = 0; i < planets.length; i++) {
			planets[i].draw();
		}

		//clear out draw buffer
		StdDraw.show();
		StdDraw.enableDoubleBuffering();

		double timeVar = 0; //time counter

		//loop to calculate new planet positions based on net forces (simulation driver)
		while(timeVar < T) {
			double[] xForces = new double[planets.length]; //store the net forces on x for all the planets
			double[] yForces = new double[planets.length]; //store the net forces on y for all the planets

			//loop to calculate the net force on both x and y for all the planets
			for(int i = 0; i < planets.length; i++) {
				xForces[i] = planets[i].calcNetForceExertedByX(planets);
				yForces[i] = planets[i].calcNetForceExertedByY(planets);
			}

			//loop to update the position of all the planets based on the net forces
			for(int i = 0; i < planets.length; i++) {
				planets[i].update(dt, xForces[i], yForces[i]);
			}

			StdDraw.picture(0, 0, bgImg); //draw background

			//loop draw the planets
			for(int i = 0; i < planets.length; i++) {
				planets[i].draw();
			}

			//clear draw buffer and pause draw screen for 10 millisecs
			StdDraw.show();
			StdDraw.pause(10);

			timeVar += dt; //increment time counter
		}

		//print universe details 
		StdOut.printf("%d\n", planets.length);
		StdOut.printf("%.2e\n", readRadius(filename));
		for (int i = 0; i < planets.length; i++) {
    		StdOut.printf("%11.4e %11.4e %11.4e %11.4e %11.4e %12s\n",
                  planets[i].xxPos, planets[i].yyPos, planets[i].xxVel,
                  planets[i].yyVel, planets[i].mass, planets[i].imgFileName);   
		}

	}
}