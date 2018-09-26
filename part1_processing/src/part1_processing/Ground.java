package part1_processing;

import processing.core.PApplet;
import processing.core.PVector;

public class Ground {

    PApplet parent;
    PVector ground;

    public Ground ( final PApplet p, final float x, final float y ) {
        parent = p;
        ground = new PVector( x, y );
    }

    boolean collide ( final PVector p ) {
        if ( p.x > ground.x && p.y > ground.y && p.x < ground.x + 100 && p.y < ground.y + 10 ) {
            return true;
        }
        return false;
    }

}
