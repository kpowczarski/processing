package part1_processing;

import java.awt.Rectangle;
import java.util.ArrayList;

import processing.core.PApplet;
import processing.core.PVector;

public class Processing extends PApplet {

    Rectangle            g;
    Rectangle            c;
    PVector              pos;
    float                velx;
    float                vely;
    float                rVelx;
    float                rVely;
    double               gravity;
    boolean              justCol    = false;
    float                moving     = 0;
    float                walk       = 5;
    boolean              collidingP = false;
    boolean              walking    = false;
    int                  x1         = 30;                        // random( 0,
                                                                 // 1300 );
    int                  y1         = 840;
    boolean              jump;                                   // random( 0,
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
        vely = 3;
        velx = 0;
        rVely = 0;
        rVelx = 0;
        gravity = 0.5;
        rect.add( new Rectangle( 60, 800, 32, 32 ) );
        c = new Rectangle( 0, 0, 1300, 10 );
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
        vely += gravity;
        player.y += vely;
        if ( vely > 5 ) {
            vely = 5;
        }
        // if ( g.collide( player.x, player.y ) ) {
        // collidingP = true;
        // player.y = (int) g.ground.y;
        // }
        if ( g.intersects( player ) ) {
            collidingP = true;
            player.y = g.y - 32;
        }
        rect( g.x, g.y, 1300, 10 );
        rect( c.x, c.y, 1300, 10 );
        stroke( 0 );
        rect( player.x, player.y, 32, 32 );
        // rect( pos.x, pos.y - 32, 32, 32 );
        // if ( !collidingP ) {
        // vel.y += .5;
        // player.y += 3;
        // }
        // pos.add( vel );
        // player.x = (int) pos.x;
        // player.y = (int) pos.y;
        player.x += velx;
        // vel.mult( (float) .9 );
        if ( player.x > width ) {
            player.x = -32;
        }
        if ( player.x < -32 ) {
            player.x = width;
        }
        if ( collidingP && jump != false ) {
            vely = -10;
        }

        for ( int i = 0; i < rect.size(); i++ ) {
            final Rectangle r = rect.get( i );
            if ( r.intersects( player ) ) {
                if ( rVelx < 0 && velx < 0 ) {
                    rVelx += velx;
                }
                else if ( rVelx > 0 && velx > 0 ) {
                    rVelx += velx;
                }
                else {
                    rVelx *= -1;
                    rVelx += velx;
                }
                if ( rVely < 0 && vely < 0 ) {
                    rVely += vely;
                }
                else if ( rVely > 0 && vely > 0 ) {
                    rVely += vely;
                }
                else {
                    rVely *= -1;
                    rVely += vely;
                }

                // rVely *= -1;
                justCol = true;

            }
            // rVely += gravity;
            if ( rVelx > 10 ) {
                rVelx = 10;
            }
            if ( rVelx < -10 ) {
                rVelx = -10;
            }

            if ( rVely > 10 ) {
                rVely = 10;
            }
            if ( rVely < -10 ) {
                rVely = -10;
            }
            r.y += rVely;
            if ( g.intersects( r ) ) {
                rVely *= -1;
            }
            if ( c.intersects( r ) ) {
                rVely *= -1;
            }
            if ( r.x > width ) {
                r.x = -32;
            }
            if ( r.x < -32 ) {
                r.x = width;
            }
            r.x += rVelx;
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
            velx = -walk;
            walking = true;
        }
        if ( key == 'd' ) {
            velx = walk;
            walking = true;
        }
        if ( key == ' ' ) {
            if ( collidingP ) {
                jump = true;
            }
        }
    }

    @Override
    public void keyReleased () {
        if ( key == 'a' ) {
            velx = 0;
            walking = false;
        }
        if ( key == 'd' ) {
            velx = 0;
            walking = false;
        }
        if ( key == ' ' ) {
            jump = false;
        }
    }

}
