package com.example.connect_four;

import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.view.MotionEvent;
import android.view.View;

public class myview extends View {
    Board board=new Board(getContext());
    public int h;
    public int j;
    public int w;
    public int width;
    public int height;
    public int mtouchx=-1;
    public int mtouchy=-1;
    public  int max_width=(width/6)+960;
    public  int max_height= (height/2)+1020;
public int check=0;
    public boolean done = false;
    public Paint mpaint = new Paint();
    public Paint paint = new Paint();
    public boolean player1=false;
    public boolean taken=true;



    public myview(Context context) {

        super(context);
    }

    @Override
    protected void onDraw(Canvas canvas) {
        super.onDraw(canvas);
        height= canvas.getHeight();
        width= canvas.getWidth();
        Constants.SCREEN_WIDTH=width;
        Constants.SCREEN_HEIGHT=height;

        h = height / 2;
        w = width / 6;
        Bitmap bitmap = Bitmap.createBitmap(width, height, Bitmap.Config.ARGB_8888);
        canvas.drawColor(Color.BLUE);


        mpaint.setColor(Color.WHITE);
        paint.setColor(Color.BLUE);

       board.draw (canvas);

       //to redraw the entire board as per the previous move ...


       board.autofill(canvas);




    if (check!=0){
       board.dropball(mtouchx,mtouchy,
               canvas,player1);



        }


    }


    @Override
    public boolean onTouchEvent(MotionEvent event) {


        switch (event.getAction()) {
            case MotionEvent.ACTION_DOWN:
                mtouchx = (int) event.getX();
                mtouchy = (int) event.getY();
                if (mtouchy>height/2)
                {
                    if (player1)
                        player1=false;
                    else player1=true;
                check++;
                invalidate();}
                if (mtouchx>=500&&mtouchx<1000&&mtouchy>=300&&mtouchy<1500){
                    if (player1)
                        player1=false;
                    else player1=true;
                      board.undo();
                    check++;
                    invalidate();

                }
              break;

            default:
                break;
        }
        return true;
    }
}

