package com.example.connect_four;

//this is player class for the hackermode of the task and used only when the user opts to play with the
public class Aiplayer {
    private Board b;
    private int nextMoveLocation=-1;
    private int maxDepth=4;

    public Aiplayer(Board b){
        this.b=b;
    }




    public int gameResult(Board b){
        int aiScore=0;
        int userscore=0;
        for (int i=5;i>=0;--i){
            for (int j=0;j<=6;++j){
                if (b.array[i][j]==0)continue;

                if(j<=3){
                    for(int k=0;k<4;++k){
                        if(b.array[i][j+k]==1) aiScore++;
                        else if(b.array[i][j+k]==2) userscore++;
                        else break;
                    }
                    if(aiScore==4)return 1; else if (userscore==4)return 2;
                    aiScore = 0; userscore = 0;
                }

                //Checking cells up
                if(i>=3){
                    for(int k=0;k<4;++k){
                        if(b.array[i-k][j]==1) aiScore++;
                        else if(b.array[i-k][j]==2) userscore++;
                        else break;
                    }
                    if(aiScore==4)return 1; else if (userscore==4)return 2;
                    aiScore = 0; userscore = 0;
                }

                //Checking diagonal up-right
                if(j<=3 && i>= 3){
                    for(int k=0;k<4;++k){
                        if(b.array[i-k][j+k]==1) aiScore++;
                        else if(b.array[i-k][j+k]==2) userscore++;
                        else break;
                    }
                    if(aiScore==4)return 1; else if (userscore==4)return 2;
                    aiScore = 0; userscore = 0;
                }

                //Checking diagonal up-left
                if(j>=3 && i>=3){
                    for(int k=0;k<4;++k){
                        if(b.array[i-k][j-k]==1) aiScore++;
                        else if(b.array[i-k][j-k]==2) userscore++;
                        else break;
                    }
                    if(aiScore==4)return 1; else if (userscore==4)return 2;
                    aiScore = 0; userscore = 0;
                }
            }
        }

        for(int j=0;j<7;++j){
            //Game has not ended yet
            if(b.array[0][j]==0)return -1;
        }
        //Game draw!
        return 0;
    }

   public int calculateScore(int aiScore, int moreMoves){
        int moveScore = 4 - moreMoves;
        if(aiScore==0)return 0;
        else if(aiScore==1)return 1*moveScore;
        else if(aiScore==2)return 10*moveScore;
        else if(aiScore==3)return 100*moveScore;
        else return 1000;
    }


    public int evaluateBoard(Board b){

        int aiScore=1;
        int score=0;
        int blanks = 0;
        int k=0, moreMoves=0;
        for(int i=5;i>=0;--i){
            for(int j=0;j<=6;++j){

                if(b.array[i][j]==0 || b.array[i][j]==2) continue;

                if(j<=3){
                    for(k=1;k<4;++k){
                        if(b.array[i][j+k]==1)aiScore++;
                        else if(b.array[i][j+k]==2){aiScore=0;blanks = 0;break;}
                        else blanks++;
                    }

                    moreMoves = 0;
                    if(blanks>0)
                        for(int c=1;c<4;++c){
                            int column = j+c;
                            for(int m=i; m<= 5;m++){
                                if(b.array[m][column]==0)moreMoves++;
                                else break;
                            }
                        }

                    if(moreMoves!=0) score += calculateScore(aiScore, moreMoves);
                    aiScore=1;
                    blanks = 0;
                }

                if(i>=3){
                    for(k=1;k<4;++k){
                        if(b.array[i-k][j]==1)aiScore++;
                        else if(b.array[i-k][j]==2){
                            aiScore=0;break;   }
                    }
                    moreMoves = 0;

                    if(aiScore>0){
                        int column = j;
                        for(int m=i-k+1; m<=i-1;m++){
                            if(b.array[m][column]==0)moreMoves++;
                            else break;
                        }
                    }
                    if(moreMoves!=0) score += calculateScore(aiScore, moreMoves);
                    aiScore=1;
                    blanks = 0;
                }

                if(j>=3){
                    for(k=1;k<4;++k){
                        if(b.array[i][j-k]==1)aiScore++;
                        else if(b.array[i][j-k]==2){aiScore=0; blanks=0;break;}
                        else blanks++;
                    }
                    moreMoves=0;
                    if(blanks>0)
                        for(int c=1;c<4;++c){
                            int column = j- c;
                            for(int m=i; m<= 5;m++){
                                if(b.array[m][column]==0)moreMoves++;
                                else break;
                            }
                        }

                    if(moreMoves!=0) score += calculateScore(aiScore, moreMoves);
                    aiScore=1;
                    blanks = 0;
                }

                if(j<=3 && i>=3){
                    for(k=1;k<4;++k){
                        if(b.array[i-k][j+k]==1)aiScore++;
                        else if(b.array[i-k][j+k]==2){aiScore=0;blanks=0;break;}
                        else blanks++;
                    }
                    moreMoves=0;
                    if(blanks>0){
                        for(int c=1;c<4;++c){
                            int column = j+c, row = i-c;
                            for(int m=row;m<=5;++m){
                                if(b.array[m][column]==0)moreMoves++;
                                else if(b.array[m][column]==1);
                                else break;
                            }
                        }
                        if(moreMoves!=0) score += calculateScore(aiScore, moreMoves);
                        aiScore=1;
                        blanks = 0;
                    }
                }


                if(i>=3 && j>=3){
                    for(k=1;k<4;++k){
                        if(b.array[i-k][j-k]==1)aiScore++;
                        else if(b.array[i-k][j-k]==2){aiScore=0;blanks=0;break;}
                        else blanks++;
                    }
                    moreMoves=0;
                    if(blanks>0){
                        for(int c=1;c<4;++c){
                            int column = j-c, row = i-c;
                            for(int m=row;m<=5;++m){
                                if(b.array[m][column]==0)moreMoves++;
                                else if(b.array[m][column]==1);
                                else break;
                            }
                        }
                        if(moreMoves!=0) score += calculateScore(aiScore, moreMoves);
                        aiScore=1;
                        blanks = 0;
                    }
                }
            }
        }
        return score;
    }

    //main logic of the HACKER_MODE ....

    public int minimax(int depth, int turn, int alpha, int beta){

        if(beta<=alpha){if(turn == 1) return Integer.MAX_VALUE; else return Integer.MIN_VALUE; }
        int gameResult = gameResult(b);

        if(gameResult==1)return Integer.MAX_VALUE/2;
        else if(gameResult==2)return Integer.MIN_VALUE/2;
        else if(gameResult==0)return 0;

        if(depth==maxDepth)return evaluateBoard(b);

        int maxScore=Integer.MIN_VALUE, minScore = Integer.MAX_VALUE;

        for(int j=0;j<=6;++j){

            int currentScore = 0;

            if(!b.isLegalMove(j)) continue;

            if(turn==1){
                b.placeMove(j, 1);
                currentScore = minimax(depth+1, 2, alpha, beta);

                if(depth==0){
                    System.out.println("Score for location "+j+" = "+currentScore);
                    if(currentScore > maxScore)nextMoveLocation = j;
                    if(currentScore == Integer.MAX_VALUE/2){b.undoMove(j);break;}
                }

                maxScore = Math.max(currentScore, maxScore);

                alpha = Math.max(currentScore, alpha);
            }
            else if(turn==2){
                b.placeMove(j, 2);
                currentScore = minimax(depth+1, 1, alpha, beta);
                minScore = Math.min(currentScore, minScore);

                beta = Math.min(currentScore, beta);
            }
            b.undoMove(j);
            if(currentScore == Integer.MAX_VALUE || currentScore == Integer.MIN_VALUE) break;
        }
        return turn==1?maxScore:minScore;
    }



//todo to be used when the user opts to play with the AI...
    public int getAIMove(){
        nextMoveLocation = -1;
        minimax(0, 1, Integer.MIN_VALUE, Integer.MAX_VALUE);
        return nextMoveLocation;
    }



}
