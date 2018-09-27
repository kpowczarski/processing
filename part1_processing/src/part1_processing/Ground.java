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

    void show () {
        parent.stroke( 255 );
        parent.line( ground.x - parent.width, ground.y, ground.x + parent.width, ground.y );
    }

    boolean collide ( final int x, final int y ) {
        if ( x > ground.x - parent.width && y > ground.y && x < ground.x + parent.width && y < ground.y + 10 ) {
            return true;
        }
        return false;
    }

}
