package com.example.connect_four;

import android.content.Context;
import android.content.Intent;
import android.graphics.Bitmap;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;


public class Board {
    public int xcor;
    public int ycor;
    public static int array[][] = new int[6][7];
    public int i;
    public int xcell;
    Context context;



    public Board(Context context) {
        this.context = context;


        for (int i = 0; i < 6; i++) {
            for (int j = 0; j < 7; j++) {
                array[i][j] = 0;
            }
        }
    }

    public void draw(Canvas canvas) {
        int height = canvas.getHeight();
        int width = canvas.getWidth();
        int w = width / 6;
        int h = height / 2;


        Paint mpaint = new Paint();
        mpaint.setColor(Color.WHITE);
        Paint paint = new Paint();
        Bitmap bitmap = Bitmap.createBitmap(w, h, Bitmap.Config.ARGB_8888);
        canvas.drawColor(Constants.BLUE);
        canvas.drawBitmap(bitmap, w, h, paint);

        for (int i = 1; i < 8; i++) {
            h = h + 120;

            for (int j = 1; j < 7; j++) {

                canvas.drawCircle(w, h, width / 22, mpaint);
                h = h + 150;
            }
            h = canvas.getHeight() / 2;
            w = w + 120;

        }
        mpaint.setTextSize(80);
        canvas.drawText(" UNDO ", 700, 500, mpaint);
    }

    public void dropball(int x, int y, Canvas canvas, boolean player1) {
        // x is the location of touching the screen

        int height = canvas.getHeight();
        int width = canvas.getWidth();
        Paint paint = new Paint();

        if (y > height / 2) {

            xcor = getx(x, xcor, canvas);
            ycor = gety(x, canvas, array);              // to drop the balls at proper index...

            if (ycor >= 0) {
                if (player1 == true) {
                    // to update the array[][]...

                    update(i, xcell, player1);
                    paint.setColor(Color.RED);

                    canvas.drawCircle(xcor, ycor, width / 22, paint);
                    check(i, xcell,player1);


                } else {
                    update(i, xcell, player1);
                    paint.setColor(Color.YELLOW);
                    canvas.drawCircle(xcor, ycor, width / 22, paint);
                    check(i, xcell,player1);


                }
            }
        }
    }

    public int getx(int mtouchx, int y, Canvas canvas) {
        int width = canvas.getWidth();
        int height = canvas.getHeight();
        if (mtouchx > 0) {
            if (mtouchx < width / 6 + 60) {
                y = width / 6;
            } else if (mtouchx >= width / 6 + 60 && mtouchx < width / 6 + 180) {
                y = width / 6 + 120;
            } else if (mtouchx >= width / 6 + 180 && mtouchx < width / 6 + 300) {
                y = width / 6 + 240;
            } else if (mtouchx >= width / 6 + 300 && mtouchx < width / 6 + 420) {
                y = width / 6 + 360;
            } else if (mtouchx >= width / 6 + 420 && mtouchx < width / 6 + 540) {
                y = width / 6 + 480;
            } else if (mtouchx >= width / 6 + 540 && mtouchx < width / 6 + 660) {
                y = width / 6 + 600;
            } else if (mtouchx >= width / 6 + 660 && mtouchx < width / 6 + 780) {
                y = width / 6 + 720;
            } else {
                y = width / 6 + 720;
            }

        }
        return y;

    }

    public int gety(int mtouchx, Canvas canvas, int arr[][]) {
        int width = canvas.getWidth();
        int height = canvas.getHeight();

        int actualheight = -1;

        if (mtouchx > 0) {
            if (mtouchx < width / 6 + 60) {
                xcell = 0;
            } else if (mtouchx >= width / 6 + 60 && mtouchx < width / 6 + 180) {
                xcell = 1;
            } else if (mtouchx >= width / 6 + 180 && mtouchx < width / 6 + 300) {
                xcell = 2;
            } else if (mtouchx >= width / 6 + 300 && mtouchx < width / 6 + 420) {
                xcell = 3;
            } else if (mtouchx >= width / 6 + 420 && mtouchx < width / 6 + 540) {
                xcell = 4;
            } else if (mtouchx >= width / 6 + 540 && mtouchx < width / 6 + 660) {
                xcell = 5;
            } else if (mtouchx >= width / 6 + 660 && mtouchx < width / 6 + 780) {
                xcell = 6;
            } else {
                xcell = 6;
            }
        }
        for (i = 5; i >= 0; i--) {
            if (arr[i][xcell] == 0)
                break;
        }
        if (i == 5) {
            actualheight = height / 2 + 870;
        } else if (i == 4) {
            actualheight = height / 2 + 720;
        } else if (i == 3) {
            actualheight = height / 2 + 570;
        } else if (i == 2) {
            actualheight = height / 2 + 420;
        } else if (i == 1) {
            actualheight = height / 2 + 270;
        } else if (i == 0) {
            actualheight = height / 2 + 120;
        } else {
            actualheight = -500;
        }

        return actualheight;
    }

    public void update(int x, int y, boolean player) {
        if (x >= 0 && x < 6 && y >= 0 && y < 7) {
            if (player == true)
                array[x][y] = 2;
            else
                array[x][y] = 1;

        }
    }

    //these both functions will be used inside the updated method on the oDraw() inside the myview..
    public int parse_row(int h, Canvas canvas) {
        int num = -100;
        int width = canvas.getWidth();
        if (h >= 0) {
            if (h == 0) {
                num = width / 6;
            } else if (h == 1) {
                num = width / 6 + 120;
            } else if (h == 2) {
                num = width / 6 + 240;
            } else if (h == 3) {
                num = width / 6 + 360;
            } else if (h == 4) {
                num = width / 6 + 480;
            } else if (h == 5) {
                num = width / 6 + 600;
            } else if (h == 6) {
                num = width / 6 + 720;
            }
        }
        return num;
    }

    public int parse_column(int k, Canvas canvas) {
        int num = -100;
        int height = canvas.getHeight();
        if (k >= 0) {
            if (k == 0) {
                num = height / 2 + 120;
            } else if (k == 1) {
                num = height / 2 + 270;
            } else if (k == 2) {
                num = height / 2 + 420;
            } else if (k == 3) {
                num = height / 2 + 570;
            } else if (k == 4) {
                num = height / 2 + 720;
            } else if (k == 5) {
                num = height / 2 + 870;
            }
        }
        return num;
    }

    public void autofill(Canvas canvas) {
        int width = canvas.getWidth();
        int row = -100;
        int column = -100;
        for (int p = 0; p < 7; p++) {
            for (int q = 0; q < 6; q++) {
                row = parse_row(p, canvas);
                column = parse_column(q, canvas);
                if (row >= 0 && column >= 0) {
                    if (array[q][p] == 1) {
                        Paint paint = new Paint();
                        paint.setColor(Color.YELLOW);
                        canvas.drawCircle(row, column, width / 22, paint);
                    } else if (array[q][p] == 2) {
                        Paint paint = new Paint();
                        paint.setColor(Color.RED);
                        canvas.drawCircle(row, column, width / 22, paint);

                    }
                }
            }
        }
    }

// TO RESET THE GAME TO THE NEW GAME ...

    public void reset_game() {

        for (int i = 0; i < 6; i++) {
            for (int j = 0; j < 7; j++) {
                array[i][j] = 0;
            }
        }

    }

    public void check(int x, int y,boolean player) { // checking vertically up the grid..
        boolean won = false;
        int var = array[x][y];

        if (x <= 2 && x >= 0) {
            if (array[x + 1][y] == var) {
                if (array[x + 2][y] == var) {
                    if (array[x + 3][y] == var) {
                        won = true;
                        reset_game();
                        gotoResult(player);

                    }
                }
            }
        }
        if (x >= 3 && x <= 5) { //checking vertically down the grid ...
            if (array[x - 1][y] == var) {
                if (array[x - 2][y] == var) {
                    if (array[x - 3][y] == var) {
                        won = true;
                        reset_game();
                        gotoResult(player);
                    }
                }
            }
        }
        if (y <= 3 && y >= 0) {   //right side checking...
            if (array[x][y + 1] == var) {
                if (array[x][y + 2] == var) {
                    if (array[x][y + 3] == var) {
                        won = true;
                        reset_game();
                        gotoResult(player);
                    }
                }
            }


        }
        if (y >= 3 && y <= 6) { //checking the left side of the grid...
            if (array[x][y - 1] == var) {
                if (array[x][y - 2] == var) {
                    if (array[x][y - 3] == var) {
                        won = true;
                        reset_game();
                        gotoResult(player);
                    }
                }
            }

        }
        if (x >= 3 && x <= 5 && y >= 0 && y <= 3) {//for  checking diagonally up..
            if (array[x - 1][y + 1] == var) {
                if (array[x - 2][y + 2] == var) {
                    if (array[x - 3][y + 3] == var) {
                        won = true;
                        reset_game();
                        gotoResult(player);
                    }
                }
            }
        }
        if (x >= 0 && x <= 2 && y >= 3 && y <= 6) {  //for checking diagonally  down ...

            if (array[x + 1][y - 1] == var) {
                if (array[x + 2][y - 2] == var) {
                    if (array[x + 3][y - 3] == var) {
                        won = true;
                        reset_game();
                        gotoResult(player);

                    }
                }
            }
        }
        if (x >= 1 && x <= 3 && y >= 2 && y <= 5) {
            if (array[x + 1][y - 1] == var) {
                if (array[x + 2][y - 2] == var) {
                    if (array[x - 1][y + 1] == var) {
                        won = true;
                        reset_game();
                        gotoResult(player);

                    }
                }
            }
        }
        if (x >= 2 && x <= 4 && y >= 1 && y <= 4) {
            if (array[x - 1][y + 1] == var) {
                if (array[x - 2][y + 2] == var) {
                    if (array[x + 1][y - 1] == var) {
                        won = true;
                        reset_game();
                        gotoResult(player);
                    }
                }
            }
        }


    }

    public void undo() {
        if (array[i][xcell] == 1 || array[i][xcell] == 2) {
            array[i][xcell] = 0;
        }


    }
    public void gotoResult(boolean player){
        Intent intent=new Intent(context,Result.class);
        intent.putExtra("playerstate",player);
        context.startActivity(intent);
    }

}






