public class Body{
	public double xxPos, yyPos, xxVel, yyVel, mass;
	public String imgFileName;
	public static double G = 6.67e-11;
	
	public Body(double xP, double yP, double xV,
              double yV, double m, String img){
		this.xxPos = xP;
		this.yyPos = yP;
		this.xxVel = xV;
		this.yyVel = yV;
		this.mass = m;	
		this.imgFileName = img;
	}	
	public Body(Body b){
		this.xxPos = b.xxPos;
		this.yyPos = b.yyPos;
		this.xxVel = b.xxVel;
		this.yyVel = b.yyVel;
		this.mass = b.mass;
		this.imgFileName = b.imgFileName;
	}
	
	public double calcDistance(Body b){
		double dx = this.xxPos - b.xxPos;
		double dy = this.yyPos - b.yyPos;
		return Math.sqrt(dx*dx + dy*dy);
	}
	
	public double calcForceExertedBy(Body b){
		double r, f;
		r = this.calcDistance(b);
		f = this.G * this.mass * b.mass / (r * r);
		return f;
	}
	
	public double calcForceExertedByX(Body b){
		double f = this.calcForceExertedBy(b);
		double r = this.calcDistance(b);
		return f * (b.xxPos - this.xxPos) / r;
	}
	
	public double calcForceExertedByY(Body b){
		double f = this.calcForceExertedBy(b);
		double r = this.calcDistance(b);
		return f * (b.yyPos - this.yyPos) / r;
	}
	
	public double calcNetForceExertedByX(Body[] bs){
		double Nfx = 0;
		for (Body b: bs){
			if (this.equals(b)){continue;}
			Nfx = Nfx + this.calcForceExertedByX(b);
		}
		return Nfx;
	}
	
	public double calcNetForceExertedByY(Body[] bs){
		double Nfy = 0;
		for (Body b: bs){
			if (this.equals(b)){continue;}
			Nfy = Nfy + this.calcForceExertedByY(b);
		}
		return Nfy;
	}
	
	public void update(double dt,double fX,double fY){
		this.xxVel = this.xxVel + dt * fX / this.mass;
		this.yyVel = this.yyVel + dt * fY / this.mass;
		this.xxPos = this.xxPos + dt * this.xxVel;
		this.yyPos = this.yyPos + dt * this.yyVel;
	}
	
	public void draw(){
		StdDraw.picture(this.xxPos, this.yyPos, "images/" + this.imgFileName);
	}
}