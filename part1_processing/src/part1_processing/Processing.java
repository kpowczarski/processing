package part1_processing;

import processing.core.PApplet;

public class Processing extends PApplet{

	public static void main(String[] args) {
		PApplet.main("part1_processing.Processing");

	}
	public void settings(){
        size(300,300);
    }

    public void setup(){
        fill(120,50,240);
    }

    public void draw(){
        ellipse(width/2,height/2,second(),second());
    }

}
