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
//float left=width/4, right=left+400, top=height/4, bottom=top+150;        // Table boundaries
float middle=250;
boolean wall=true;

int tableRed=150, tableGreen=250, tableBlue=150;      // green pool table
int score=0,m=0,k=0;

    // ADD YOUR OWN DECLARATIONS HERE. ++++

//// SETUP:  size and table
void setup() {
    size( 640, 480 );
    //rectMode(CENTER);
    //rect(width/2, height/2, (right-left), (bottom-top));
    //rectMode(CORNER); 
    reset() ;  
    //// MODIFY THIS CODE TO MAKE TABLE CENTERED IN WINDOW.  ++++

    // Table boundaries
 }

void reset() {
  boolean wall=true;
  //int score=0,m=0,k=0;
  tableRed=150; tableGreen=250; tableBlue=150;
  
  // Set position for ball spawn
  kingX= random(middle+100, right); kingY= random(top, bottom);
  funX= random(middle+100, right); funY= random(top, bottom);
  songX= random(middle+100, right); songY= random(top,bottom);
  
  // Set the speed for ball spawn
  kingDX=  random( -3,3 );   kingDY=  random( -3,3 );
  funDX=  random( -3,3 );   funDY=  random( -3,3 );
  songDX=  random( -3,3 );   songDY=  random( -3,3 );
}

//// NEXT FRAME:  table, bounce off walls, collisions, show all
void draw() {
  background( 250,250,200 );
  rectMode( CORNERS );
  table( left, top, right, bottom );
  buttons();
  bounce();
  collisions();
  show();
  messages();
}

//// HANDLERS:  keys, click
void keyPressed() {
  if (key == 'q') { exit();  }
  if (key == 'r') { reset(); }
  if (key == 'w') { wall=false; }
  if (key == 'p') { tableRed= 250; tableGreen=150; tableBlue=235; }
  if (key == 'm') { mice(); }
}

//void mouseClicked() {
  //if ( dist(kingX,kingY, mouseX,mouseY) < 15) {
       //dist(funX,funY, mouseX,mouseY) < 15 ||
       //dist(songX,songY, mouseX,mouseY) < 15 { 
         //reset();
      //}
//}

//// SCENE:  draw the table with wall down the middle
void table( float east, float north, float west, float south ) {
  fill( tableRed, tableGreen, tableBlue );    // pool table
  strokeWeight(20);
  stroke( 127, 0, 0 );      // Brown walls
  rect( east-20, north-20, west+20, south+20 );

            //++++ MODIFY THIS CODE, as necessary. ++++

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
  
  if (wall==true && kingX<middle+94) {
    kingDX *= -1; }
  if (wall==true && funX<middle+94) {
    funDX *= -1; }
  if (wall==true && songX<middle+94) {
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

void mice() {
  fill(0,0,0);
  ellipse(85,height-75,20,20);
  ellipse(115,height-75,20,20);  
  ellipse(100,height-60, 30,30);

}
 
void buttons() {
    //++++ ADD YOUR OWN CODE HERE. ++++
}
void messages() {
  fill(0);
  text( title, width/3, 15 );
  text( news, width/9, 30 );
  text( author, 10, height-5 );

  // Also, display the number of collisions.

    //++++ ADD YOUR OWN CODE HERE. ++++

}
