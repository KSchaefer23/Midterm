//// Midterm code for 59CST112

/**************************************************************
BE SURE TO CHANGE THE NEXT SEVERAL LINES, USING YOUR REAL NAME.
    MY FIRST NAME IS......Kevin
    MY MIDDLE NAME IS.....Frederick
    MY LAST NAME IS.......Schaefer
    
PICK 3 WORDS THAT START WITH YOUR INITIALS, using lower case letters:
    first word............king
    second word...........fun
    third word............song
    
USE THESE 3 WORDS TO NAME ALL VARIABLES FOR 3 POOL BALLS.
(You may abbreviate any words, but start with same letter.)
***************************************************************/

//// GLOBALS FOR 3 colored balls ////
//// (Assume ball diameter is 30.) ////
float kingX,  kingY,  kingDX,  kingDY;
float funX,  funY,  funDX,  funDY;
float songX,  songY,  songDX,  songDY;

//// OTHER GLOBALS:  strings, pool table, etc ////
String title=  "CST112 MIDTERM EXAM";
String news=   "Click any ball to reset it to right half of table.  (r resets all.)";
String author=  "Kevin Schaefer";

float left=120, right=520, top=165, bottom=315;        // Table boundaries
float middle=250;
boolean wall=true;
boolean miceclick=false;
float miceX = left;

float button1X=100, button1Y=50, button1W=80, button1H=40;
float button2X=200, button2Y=50, button2W=80, button2H=40;
float button3X=300, button3Y=50, button3W=80, button3H=40;
float button4X=400, button4Y=50, button4W=80, button4H=40;

int tableRed=150, tableGreen=250, tableBlue=150;      // Green pool table
int score=0,m=0,k=0;
int count= 0;


//// SETUP:  size and table
void setup() {
    //size( 640, 480 );
    size ( 700, 500 );
    left = ((width/2)-200);
    top = ((height/2)-75); 
    right = ((width/2)+200);
    bottom = ((height/2)+75);
    middle = (width/2);
    reset() ;
}

void reset() {
  wall=true;
  miceX=left;
  miceclick=false;
  //int score=0,m=0,k=0;
  tableRed=150; tableGreen=250; tableBlue=150;
  
  // Set position for ball spawn
  kingX= random(middle+40, right); kingY= random(top, bottom);
  funX= random(middle+40, right); funY= random(top, bottom);
  songX= random(middle+40, right); songY= random(top,bottom);
  
  // Set the speed for ball spawn
  kingDX=  random( -3,3 );   kingDY=  random( -3,3 );
  funDX=  random( -3,3 );   funDY=  random( -3,3 );
  songDX=  random( -3,3 );   songDY=  random( -3,3 );
}

//// NEXT FRAME:  table, bounce off walls, butotns, collisions, show all
void draw() {
  background( 250,250,200 );
  rectMode( CORNERS );
  table( left, top, right, bottom );  
  showButton( button1X, button1Y, button1W, button1H );
  showButton( button2X, button2Y, button2W, button2H );  
  showButton( button3X, button3Y, button3W, button3H );  
  showButton( button4X, button4Y, button4W, button4H );    
  bounce();
  collisions();
  show();
  messages();
  count += 1;
  if (key == 'm' && miceX >left ) { mice(); }
  if (miceclick = true && miceX > left) { mice(); }
  
  // Buttons
  fill(255,255,255);
  text( "RESET", button1X+button1W/4, button1Y+button1H*5/8 );
  text( "WALL", button2X+button2W/3, button2Y+button2H*5/8 );
  text( "PINK", button3X+button3W/3, button3Y+button3H*5/8 );
  text( "MOUSE", button4X+button4W/4, button4Y+button4H*5/8 );
  
}

// Creates button
void showButton( float x, float y, float w, float h ) {
  fill(255,175,0);
  rectMode( CORNER );
  rect ( x,y,w,h, 7 );
}

//// HANDLERS:  keys, click
void keyPressed() {
  if (key == 'q') { exit();  }
  if (key == 'r') { reset(); }
  if (key == 'w') { wall=false; }  // removes wall
  if (key == 'p') { tableRed= 250; tableGreen=150; tableBlue=235; } // Turns table pink
  
  /// m starts mouse animation
  if (key == 'm') { mice(); }  
  
  /// 1, 2, 3 keys reset balls
  if (key == '1') { kingX= random(middle+40, right); kingY= random(top, bottom); 
    kingDX=  random( -3,3 );   kingDY=  random( -3,3 ); }
  if (key == '2') { funX= random(middle+40, right); funY= random(top, bottom); 
    funDX=  random( -3,3 );   funDY=  random( -3,3 ); }
  if (key == '3') { songX= random(middle+40, right); songY= random(top, bottom); 
    songDX=  random( -3,3 );   songDY=  random( -3,3 ); }  
  
}

/// Resets balls when clicked
void mouseClicked() {
  if ( dist(kingX,kingY, mouseX,mouseY) < 18) { kingX= random(middle+40, right); kingY= random(top, bottom); 
    kingDX=  random( -3,3 );   kingDY=  random( -3,3 );}
  if ( dist(funX,funY, mouseX,mouseY) < 18) { funX= random(middle+40, right); funY= random(top, bottom); 
    funDX=  random( -3,3 );   funDY=  random( -3,3 );}
  if ( dist(songX,songY, mouseX,mouseY) < 18) { songX= random(middle+40, right); songY= random(top, bottom); 
    songDX=  random( -3,3 );   songDY=  random( -3,3 );}
    
/// Button functions: reset, no wall, pink, mouse    
  if ((mouseX) > (button1X) && (mouseX) < (button1X+button1W) &&
     (mouseY) > (button1Y) && (mouseY) < (button1Y+button1H)) {
       reset();
     }
  if ((mouseX) > (button2X) && (mouseX) < (button2X+button2W) &&
     (mouseY) > (button2Y) && (mouseY) < (button2Y+button2H)) {
       wall=false;
     }
  if ((mouseX) > (button3X) && (mouseX) < (button3X+button3W) &&
     (mouseY) > (button3Y) && (mouseY) < (button3Y+button3H)) {
       tableRed= 250; tableGreen=150; tableBlue=235;
     }
  if ((mouseX) > (button4X) && (mouseX) < (button4X+button4W) &&
     (mouseY) > (button4Y) && (mouseY) < (button4Y+button4H)) {
       miceclick=true;
       mice();
     }
}

//// SCENE:  draw the table with wall down the middle
void table( float east, float north, float west, float south ) {
  fill( tableRed, tableGreen, tableBlue );    // pool table
  strokeWeight(20);
  stroke( 127, 0, 0 );      // Brown walls
  rect( east-20, north-20, west+20, south+20 );

  // Start with a WALL down the middle of the table! 
  if (wall==true) {
    float middle=  (east+west)/2;    
    stroke( 0, 127, 0 );
    line( middle,north+10, middle,south-10 );
  }
  stroke(0);
  strokeWeight(1);
}

//// ACTION:  bounce off walls, collisions
void bounce() {
  kingX += kingDX;  if ( kingX<left || kingX>right ) kingDX *= -1;
  kingY += kingDY;  if ( kingY<top || kingY>bottom ) kingDY *=  -1;  
  funX += funDX;    if ( funX<left || funX>right ) funDX *= -1;
  funY += funDY;    if ( funY<top || funY>bottom ) funDY *=  -1;
  songX += songDX;  if ( songX<left || songX>right ) songDX *= -1;
  songY += songDY;  if ( songY<top || songY>bottom ) songDY *=  -1;
  
  if (wall==true && kingX<middle+23) {
    kingDX *= -1; }
  if (wall==true && funX<middle+23) {
    funDX *= -1; }
  if (wall==true && songX<middle+23) {
    songDX *= -1; }
  }
  
void collisions() {
  float tmp;
    if ( dist( kingX,kingY, funX,funY ) < 30 ) {
      tmp=funDX;  funDX=kingDX;  kingDX=tmp;
      tmp=funDY;  funDY=kingDY;  kingDY=tmp;
    }
    if ( dist( kingX,kingY, songX,songY ) < 30 ){ 
      tmp=songDX;  songDX=kingDX;  kingDX=tmp;
      tmp=songDY;  songDY=kingDY;  kingDY=tmp;
    } 
    if ( dist( funX,funY, songX,songY ) < 30 ){ 
      tmp=songDX;  songDX=funDX;  funDX=tmp;
      tmp=songDY;  songDY=funDY;  funDY=tmp;
    }
}

//// SHOW:  balls, messages, etc.
void show() {
  // Ball 1
  fill(255,0,0); ellipse(kingX, kingY, 30,30);
  fill(255,255,255); text('1', kingX-3,kingY+4);

  // Ball 2
  fill(0,255,0); ellipse(funX, funY, 30,30);
  fill(0,0,0); text('2', funX-3,funY+4);

  // Ball 3
  fill(0,0,255); ellipse(songX, songY, 30,30);
  fill(255,255,255); text('3', songX-3,songY+4);
}

// Creates the mouse
void mice() {
  if (miceX < right) {
    stroke(245,150,220);                          // tail
    strokeWeight(3);
    line(miceX-40, bottom, miceX,bottom); 
    stroke(0,0,0);
    strokeWeight(2);
    
    if (count/30 % 2 == 0) {
      line(miceX-20,bottom, miceX-20,bottom+15);  // back left
      line(miceX+10,bottom, miceX+10,bottom+15);  // front right
      line(miceX-15,bottom, miceX-15,bottom+15);  // back right
      line(miceX+5,bottom, miceX+5,bottom+15);    // front left
    } else {
      line(miceX-20,bottom, miceX-15,bottom+15);  // angle back left
      line(miceX+10,bottom, miceX+10,bottom+15);  // front right
      line(miceX-15,bottom, miceX-15,bottom+15);  // back right      
      line(miceX+5,bottom, miceX+10,bottom+15);    // angle front left
    }
    
    strokeWeight(1);
    fill(125,125,125);
    ellipse(miceX,bottom, 50,20);   // body   
    ellipse(miceX+2,bottom-11,17,17);  // ears    
    fill(0,0,0);
    ellipse(miceX+14,bottom-4,3,3);   // eyes
    ellipse(miceX+25,bottom,3,3);   // nose
    miceX += 2;
  } else { miceX = left;
  }
}

// Display messages
void messages() {
  fill(0);
  text( title, width/3, 15 );
  text( news, width/9, 30 );
  text( author, 10, height-5 );
}
