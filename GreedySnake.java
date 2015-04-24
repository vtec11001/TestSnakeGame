//I hoped this commenting works. 

import java.util.ArrayList;

import javafx.application.Application;
import javafx.event.EventHandler;
import javafx.scene.Group;
import javafx.scene.Scene;
import javafx.scene.layout.*;
import javafx.scene.text.Text;
import javafx.scene.control.Label;
import javafx.scene.input.KeyCode;
import javafx.scene.input.KeyEvent;
import javafx.scene.paint.Color;
import javafx.scene.shape.*;
import javafx.stage.Stage;

public class GreedySnake extends Application {

	private Egg egg; 
	private Circle previousEgg;
	private ArrayList<Rectangle> snake; 
	private ArrayList<Integer> moves;
	private int score;
	private Group root; 
	private Label scoreBoard; 

	public static void main(String[] args) {
		launch(args); 
	}

	@SuppressWarnings({ "unchecked", "rawtypes" })
	@Override
	public void start(Stage primaryStage) throws Exception {

		root = new Group();
		
		GridPane grid  = new GridPane();
      

		grid.setPrefSize(800, 600); 
		grid.setVisible(true);
		grid.setGridLinesVisible(true);
		root.getChildren().add(grid); 
		
		String scoreText =("Your score: 0");
		scoreBoard = new Label(scoreText); 
		
		Pane scorePane = new Pane();
		scorePane.getChildren().add(scoreBoard);
		

		Pane snakePane = new Pane();
		snakePane.getChildren().add(root);

		BorderPane borderPane = new BorderPane();
		borderPane.setTop(scorePane);
		borderPane.setCenter(snakePane);		

        Scene scene = new Scene(borderPane, 800, 600, Color.WHITE);
		
 		egg = new Egg();
		

		snake = new ArrayList(); 
		
		Rectangle head = new Rectangle(125, 125, 25, 25);
		head.setFill(Color.RED); 
		snake.add(head); 
		root.getChildren().add(head); 

		moves = new ArrayList();
		
		moves.add(3);        

	
		score = 0;


        	scene.setOnKeyPressed(new EventHandler<KeyEvent>() 
        			{
            		
			@Override
			public void handle(KeyEvent ke) {

				if (ke.getCode() == KeyCode.UP) 
				{

                                        updateTail();
                                        snake.get(0).setY(snake.get(0).getY() - 25);
                                        updateMoveList(0);
                }


				if (ke.getCode() == KeyCode.DOWN) {

					

                                        updateTail();
                                        snake.get(0).setY(snake.get(0).getY() + 25);
                                        updateMoveList(1);
                                }

				if (ke.getCode() == KeyCode.LEFT) {

                                        updateTail();
                                        snake.get(0).setX(snake.get(0).getX() - 25);
                                        updateMoveList(2);
                                }

            			if (ke.getCode() == KeyCode.RIGHT) {


					updateTail();
            				snake.get(0).setX(snake.get(0).getX() + 25);
					updateMoveList(3);
				}


				for(int i = 1; i < snake.size(); i++){
					if(snake.get(i).intersects(	snake.get(0).getX() + 6.25,
									snake.get(0).getY() + 6.25,
									12.5,
									12.5	))
						System.out.println("Game over");

				}

				drawEgg();
				eggCollision();
				snakeCollision();
            		}
       	 	});

		drawEgg();
	
        	primaryStage.setScene(scene);
        	

        
        	primaryStage.show();
	}
	
	public void updateTail(){


		for(int i = 1; i < snake.size(); i++){
			
			switch (moves.get(i-1)) {
				case 0:
                                        //move up
					snake.get(i).setY(snake.get(i).getY() - 25);
					break;
				case 1:
					//move down
                                        snake.get(i).setY(snake.get(i).getY() + 25);
					break;

				case 2:
					//move left
                                        snake.get(i).setX(snake.get(i).getX() - 25);
					break;
					
				case 3:
					//move right
                                        snake.get(i).setX(snake.get(i).getX() + 25);
					break;

			}
		
		}
	}	

	public void updateMoveList(int x)
	{

		for(int i = moves.size() - 1; i > 0; i--)
			moves.set(i, moves.get(i-1));

		moves.set(0, x);

	}
	
	public void drawEgg()
	{
		
		if(previousEgg != null)
			root.getChildren().remove(previousEgg);
	
		previousEgg = egg.getCircle();
		root.getChildren().add(previousEgg);
		
	}

	public void eggCollision(){

		if(snake.get(0).intersects(egg.getX(), egg.getY(), 2, 2)){

			score++;
			//System.out.println("Score: " + score);
			scoreBoard.setText("Eggs eaten:" + score  );

			egg.setRandom();

			Rectangle tail = new Rectangle(0, 0, 25, 25);

			switch (moves.get(moves.size() - 1)){
		
				case 0:
					
					tail.setX( snake.get(snake.size() - 1).getX());
					tail.setY( snake.get(snake.size() - 1).getY() + 25);
					moves.add(0);
					break;

				case 1:
					
					tail.setX( snake.get(snake.size() - 1).getX());
					tail.setY( snake.get(snake.size() - 1).getY() - 25);
					moves.add(1);
					break;

				case 2:
					
					tail.setX( snake.get(snake.size() - 1).getX() + 25);
					tail.setY( snake.get(snake.size() - 1).getY());
					moves.add(2);
					break;

				case 3:
					
					tail.setX( snake.get(snake.size() - 1).getX() - 25);
					tail.setY( snake.get(snake.size() - 1).getY());
					moves.add(3);
					break;
			}
	
			snake.add(tail);
			root.getChildren().add(tail);


		}
	}
	
	public void snakeCollision(){
	
		for(int i = 1; i < snake.size(); i++){
                        if(snake.get(i).intersects(     snake.get(0).getX() + 6.25,
                                                        snake.get(0).getY() + 6.25,
                                                        12.5,
   				                	12.5    )) {
 	        		System.out.println("Game over");
				System.exit(0);
			}
                }

		if (snake.get(0).getX() < 0
			|| snake.get(0).getX() > 800
			|| snake.get(0).getY() < 0
			|| snake.get(0).getY() > 600) {
			
			System.out.println("Game Over");
			root.getChildren().clear();
			
		Label ResultBoard = new Label("The final score is: " + score + " eggs eaten with a snake  " + (score+1) + " cube long"); 
		root.getChildren().add(ResultBoard); 
			//System.exit(0);
		}
	}

}
