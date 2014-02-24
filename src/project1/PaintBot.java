package project1;

public class PaintBot {
	Location joint1;
	Location joint2;
	double psi;
	Location joint3;
	double theta;
	Location brush;
	double brushangle;
	PaintBot(int x, int y, int x2, int y2, int x3, int y3, int x4, int y4){
		joint1 = new Location(x,y);
		joint2 = new Location(x2,y2);
		joint3 = new Location(x3,y3);
		brush = new Location(x4,y4);
		psi = 3.14;
		theta = 3.14;
		brushangle = 3.14;
	}
}
