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
    int                  x1         = 30;                        
    int                  y1         = 840;
    boolean              jump;                                   
    float rC;
    float gC;
    float bC;
    ArrayList<Rectangle> rect       = new ArrayList<Rectangle>();
    float velxs[];
    float velys[];
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
        pos = new PVector( width / 2 + 40, height / 2 - 100 );
        vely = 3;
        velx = 0;
        rVely = 0;
        rVelx = 0;
        rC = random(0, 255);
        gC = random(0, 255);
        bC = random(0, 255);
        velxs = new float[10];
        velys = new float[10];
        for (int i = 0; i < 7; i++) {
        	velxs[i] = random(-5, 5);
            velys[i] = random(-5, 5);
        }
        gravity = 0.5;
        rect.add( new Rectangle( 60, 800, 32, 32 ) );
        rect.add( new Rectangle( 160, 800, 32, 32 ) );
        rect.add( new Rectangle( 260, 800, 32, 32 ) );
        rect.add( new Rectangle( 560, 800, 32, 32 ) );
        rect.add( new Rectangle( 960, 800, 32, 32 ) );
        rect.add( new Rectangle( 1060, 800, 32, 32 ) );
        rect.add( new Rectangle( 1160, 800, 32, 32 ) );
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
        background( 51 );
        collidingP = false;
        vely += gravity;
        player.y += vely;
        if ( vely > 5 ) {
            vely = 5;
        }
        if ( g.intersects( player ) ) {
            collidingP = true;
            player.y = g.y - 32;
        }
        fill( 0, 0, 0 );
        rect( g.x, g.y, 1300, 10 );
        rect( c.x, c.y, 1300, 10 );
        stroke( 0 );
        fill( 255, 0, 0 );
        rect( player.x, player.y, 32, 32 );
        player.x += velx;
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
                if ( velxs[i] < 0 && velx < 0 ) {
                	velxs[i] += velx;
                }
                else if ( velxs[i] > 0 && velx > 0 ) {
                	velxs[i] += velx;
                }
                else {
                	velxs[i] *= -1;
                	velxs[i] += velx;
                }
                if ( velys[i] < 0 && vely < 0 ) {
                	velys[i] += vely;
                }
                else if ( velys[i] > 0 && vely > 0 ) {
                	velys[i] += vely;
                }
                else {
                	velys[i] *= -1;
                	velys[i] += vely;
                }

                // rVely *= -1;
                justCol = true;

            }
            // rVely += gravity;
            if ( velxs[i] > 10 ) {
            	velxs[i] = 10;
            }
            if ( velxs[i] < -10 ) {
            	velxs[i] = -10;
            }

            if ( velys[i] > 10 ) {
            	velys[i] = 10;
            }
            if ( velys[i] < -10 ) {
            	velys[i] = -10;
            }
            if ( r.y > 858 ) {
            	r.y = 858;
            }
            if (r.y < 10) {
            	r.y = 10;
            }
            r.y += velys[i];
            if ( g.intersects( r ) ) {
                velys[i] *= -1;
            }
            if ( c.intersects( r ) ) {
                velys[i] *= -1;
            }
            if ( r.x > width ) {
                r.x = -32;
            }
            if ( r.x < -32 ) {
                r.x = width;
            }
            r.x += velxs[i];
            fill( rC, gC, bC );
            rect( r.x, r.y, r.width, r.height );

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
