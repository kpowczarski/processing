package part1_processing;

import processing.core.PApplet;
import processing.core.PVector;

public class Processing extends PApplet {

    Ground  g;
    PVector pos;
    PVector vel;

    public static void main ( final String[] args ) {
        PApplet.main( "part1_processing.Processing" );

    }

    @Override
    public void settings () {
        size( 1300, 900 );
    }

    @Override
    public void setup () {
        g = new Ground( this, width, height );
        pos = new PVector( width / 2, height / 2 - 100 );
        vel = new PVector();
        // fill( 120, 50, 240 );
    }

    @Override
    public void draw () {
        // for ( int i = 0; i < 6; i++ ) {
        // rect( ( i * 175 ) + 150, 150, 150, 150 );
        // }
        fill( 255, 0, 0 );
        final boolean colliding = false;
        rect( pos.x, pos.y, 16, 16 );
        if ( !colliding ) {
            vel.y += .2;
        }
        pos.add( vel );
        vel.mult( (float) .9 );
    }

}
