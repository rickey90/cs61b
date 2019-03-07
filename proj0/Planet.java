public class Planet {
	double xxPos; //current x position
	double yyPos; //current y position 
	double xxVel; //current velocity in x direction
	double yyVel; //current velocity in y direction
	double mass;
	String imgFileName; //imag file that depicts the body
	final static double G = 6.67e-11; //gravitational constant

	public Planet(double xP, double yP, double xV, double yV, double m, String img) {
		xxPos = xP;
		yyPos = yP;
		xxVel = xV;
		yyVel = yV;
		mass = m;
		imgFileName = img;
	}

	public Planet(Planet b) {
		xxPos = b.xxPos;
		yyPos = b.yyPos;
		xxVel = b.xxVel;
		yyVel = b.yyVel;
		mass = b.mass;
		imgFileName = b.imgFileName;
	}

	//calcuates and returns the distance between two planets
	public double calcDistance(Planet p) {
		double dx = xxPos - p.xxPos; //calc difference in x-coord
		double dy = yyPos - p.yyPos; //calc difference in y-coord

		return Math.sqrt((dx * dx) + (dy * dy)); //return the distance
	}

	//calcualtes and returns the force exerted on the planet by the given planet
	public double calcForceExertedBy(Planet p) {
		return (G * mass * p.mass)/(calcDistance(p) * calcDistance(p));
	}

	//calculates and returns the force exerted on the x-coord by the given planet
	public double calcForceExertedByX(Planet p) {
		double dx = p.xxPos - xxPos; 

		return (calcForceExertedBy(p) * dx) / calcDistance(p);
	}

	//calculates and returns the force exerted on the y-coord by the given planet
	public double calcForceExertedByY(Planet p) {
		double dy = p.yyPos - yyPos;

		return (calcForceExertedBy(p) * dy) / calcDistance(p);
	}

	//calculate and return the net force exerted by the given planets on the x-coord
	public double calcNetForceExertedByX(Planet[] p) {
		double netForce = 0; 
		for(int i = 0; i < p.length; i++) {
			if(this.equals(p[i])) {
				continue;
			}

			netForce = netForce + calcForceExertedByX(p[i]);
		}

		return netForce;
	}

	//calculate and return the net force exerted by the given planets on the y-coord
	public double calcNetForceExertedByY(Planet[] p) {
		double netForce = 0; 
		for(int i = 0; i < p.length; i++) {
			if(this.equals(p[i])) {
				continue;
			}

			netForce = netForce + calcForceExertedByY(p[i]);
		}

		return netForce;
	}
}