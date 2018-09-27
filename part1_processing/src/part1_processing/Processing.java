package part1_processing;

import java.awt.Rectangle;
import java.util.ArrayList;

import processing.core.PApplet;
import processing.core.PVector;

public class Processing extends PApplet {

    Rectangle            g;
    PVector              pos;
    PVector              vel;
    float                moving     = 0;
    float                walk       = 2;
    boolean              collidingP = false;
    int                  x1         = 30;                        // random( 0,
                                                                 // 1300 );
    int                  y1         = 840;                       // random( 0,
                                                                 // 900 );
    ArrayList<Rectangle> rect       = new ArrayList<Rectangle>();
    Rectangle            player;

    public static void main ( final String[] args ) {
        PApplet.main( "part1_processing.Processing" );

    }

    @Override
    public void settings () {
        size( 1300, 900 );
    }

    @Override
    public void setup () {
        // g = new Ground( this, width / 2, height - 20 );
        pos = new PVector( width / 2 + 40, height / 2 - 100 );
        vel = new PVector();
        rect.add( new Rectangle( 60, 800, 32, 32 ) );
        // rect.add( new Rectangle( 0, 890, 900, 10) );
        x1 = (int) pos.x;
        y1 = (int) pos.y;
        g = new Rectangle( 0, 890, 1300, 10 );
        player = new Rectangle( x1, y1, 32, 32 );
        // fill( 120, 50, 240 );
    }

    @Override
    public void draw () {
        // for ( int i = 0; i < 6; i++ ) {
        // rect( ( i * 175 ) + 150, 150, 150, 150 );
        // }
        background( 51 );
        fill( 255, 0, 0 );
        // g.show();
        collidingP = false;
        // if ( g.collide( player.x, player.y ) ) {
        // collidingP = true;
        // player.y = (int) g.ground.y;
        // }
        if ( g.intersects( player ) ) {
            collidingP = true;
            player.y = g.y - 32;
        }
        rect( g.x, g.y, 1300, 10 );
        stroke( 0 );
        rect( player.x, player.y, 32, 32 );
        // rect( pos.x, pos.y - 32, 32, 32 );
        if ( !collidingP ) {
            // vel.y += .5;
            player.y += 3;
        }
        // pos.add( vel );
        // player.x = (int) pos.x;
        // player.y = (int) pos.y;
        player.x += moving;
        // vel.mult( (float) .9 );
        if ( player.x > width ) {
            player.x = -32;
        }
        if ( player.x < -32 ) {
            player.x = width;
        }

        for ( int i = 0; i < rect.size(); i++ ) {
            final Rectangle r = rect.get( i );
            if ( pos.x + 32 > r.x && pos.x < r.x + r.width && pos.y + 32 > r.y && pos.y < r.y + r.height ) {

            }
            fill( 0, 255, 0 );
            rect( r.x, r.y, r.width, r.height );
            // fill( 0, 255, 0 );

            // rect( x1, y1, 32, 32 );
            // if ( pos.x < x1 + 32 && pos.x > x1 && pos.y < y1 + 32 && pos.y >
            // y1 ) {
            // collidingP = true;
            // pos.x = x1 + 32;
            // x1 += moving;

        }
    }

    @Override
    public void keyPressed () {
        if ( key == 'a' ) {
            moving = -walk;
        }
        if ( key == 'd' ) {
            moving = walk;
        }
        if ( key == ' ' ) {
            if ( collidingP ) {
                player.y -= 20;
            }
        }
    }

    @Override
    public void keyReleased () {
        if ( key == 'a' ) {
            moving = 0;
        }
        if ( key == 'd' ) {
            moving = 0;
        }
    }

}
