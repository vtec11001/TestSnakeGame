import javafx.scene.Group;
import javafx.scene.shape.Circle;
import javafx.scene.paint.Color;

public class Egg {
	private Circle egg; 
	private int x; 
	private int y; 

	public Egg () {
		x = 50 + (int)(Math.random()*20)*25; 
		y = 50 + (int)(Math.random()*15)*25; 
		egg = new Circle (x+12.5, y+12.5, 12); 
		egg.setFill(Color.ORANGE);
	}

	public void setRandom() {

			x = 50 + (int)(Math.random()*20)*25; 
			y = 50 + (int)(Math.random()*15)*25; 
			egg.setCenterX(x+12.5);
			egg.setCenterY(y+12.5); 
	
	}

	public Circle getCircle() {
		return egg; 
	}
		

	public int getX(){
		return (int)egg.getCenterX();
	}

	public int getY(){
		return (int)egg.getCenterY();
	}

	public int getRadius(){
		return (int)egg.getRadius();

	}


}
