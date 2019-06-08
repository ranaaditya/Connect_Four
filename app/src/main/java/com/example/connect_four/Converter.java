package com.example.connect_four;

public class Converter {

    public static int convert_xy(int mtouchx) {

        if (mtouchx > 0 && mtouchx <= Constants.SCREEN_WIDTH / 6 + 960) {
            if (mtouchx < Constants.SCREEN_WIDTH / 6 + 120) {
                return 0;
            } else if (mtouchx >= Constants.SCREEN_WIDTH / 6 + 120 && mtouchx < Constants.SCREEN_WIDTH / 6 + 240) {
                return 1;
            } else if (mtouchx >= Constants.SCREEN_WIDTH / 6 + 240 && mtouchx < Constants.SCREEN_WIDTH / 6 + 360) {
                return 2;
            } else if (mtouchx >= Constants.SCREEN_WIDTH / 6 + 360 && mtouchx < Constants.SCREEN_WIDTH / 6 + 480) {
                return 3;
            } else if ( mtouchx >= Constants.SCREEN_WIDTH / 6 + 480&&mtouchx<Constants.SCREEN_WIDTH/6+600){
                return 4;
            }else if (mtouchx>=Constants.SCREEN_WIDTH/6+600&&mtouchx<Constants.SCREEN_WIDTH/6+720){
                return 5;
            }else if (mtouchx>=Constants.SCREEN_WIDTH/6+720&&mtouchx<Constants.SCREEN_WIDTH/6+840){
                return 6;
            }else {
                return 6;
            }

        }
    return 0 ;}
}


/**
 paint = new Paint();
 mpaint = new Paint();

 mpaint.setColor(Color.WHITE);
 canvas.drawBitmap(bitmap, width, height, paint);


 for (int i = 1; i < 8; i++) {
 h = h + 120;

 for (int j = 1; j < 7; j++) {

 canvas.drawCircle(w, h, width / 22, mpaint);
 h = h + 150;
 }
 h = height / 2;
 w = w + 120;

 }**/