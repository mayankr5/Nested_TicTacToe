package com.example.tictactoe;

import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;
import androidx.gridlayout.widget.GridLayout;

import java.util.Arrays;

import android.content.DialogInterface;
import android.os.Bundle;
import android.view.View;
import android.widget.ImageView;
import android.widget.Toast;

public class MainActivity extends AppCompatActivity {

    int player = 1;
    int []cellClicked = {-1,-1,-1,-1,-1,-1,-1,-1,-1,-1,-1,-1,-1,-1,-1,-1,-1,-1,-1,-1,-1,-1,-1,-1,-1,-1,-1,-1,-1,-1,-1,-1,-1,-1,-1,-1,-1,-1,-1,-1,-1,-1,-1,-1,-1,-1,-1,-1,-1,-1,-1,-1,-1,-1,-1,-1,-1,-1,-1,-1,-1,-1,-1,-1,-1,-1,-1,-1,-1,-1,-1,-1,-1,-1,-1,-1,-1,-1,-1,-1,-1};
    boolean isWinner = false;
    int []blockWinner = {-1,-1,-1,-1,-1,-1,-1,-1,-1};
    int [][]winStates = {{0,1,2},{3,4,5},{6,7,8},{0,3,6},{1,4,7},{2,5,8},{0,4,8},{2,4,6}};


    public void onClick(View view){
        ImageView v = (ImageView) view;
        int tag = Integer.parseInt(v.getTag().toString());
        int i = tag/10;
        int cInd = Integer.parseInt(String.valueOf(tag), 9);
        if(!isWinner){
            if(blockWinner[i] == -1 && cellClicked[cInd] == -1){
                if(player == 1){
                    v.setImageResource(R.drawable.cross);
                    cellClicked[cInd] = player;
                    player = 2;
                }else{
                    v.setImageResource(R.drawable.zero);
                    cellClicked[cInd] = player;
                    player = 1;
                }
                checkBlockWinner(i);
            }
        }
    }

    public void checkBlockWinner(int k){
        int beg = 9*k;
        int []currState = new int[9];
        for(int i = 0; i < 9; i++){
            currState[i] =  cellClicked[beg];
            beg++;
        }

        for(int i = 0; i < winStates.length; i++){
            if(currState[winStates[i][0]] == currState[winStates[i][1]] && currState[winStates[i][1]] == currState[winStates[i][2]] && currState[winStates[i][0]] != -1)
            {
                ImageView img = null;
                if(k == 0)
                    img = findViewById(R.id.img0);
                else if(k == 1)
                    img = findViewById(R.id.img1);
                else if(k == 2)
                    img = findViewById(R.id.img2);
                else if(k == 3)
                    img = findViewById(R.id.img3);
                else if(k == 4)
                    img = findViewById(R.id.img4);
                else if(k == 5)
                    img = findViewById(R.id.img5);
                else if(k == 6)
                    img = findViewById(R.id.img6);
                else if(k == 7)
                    img = findViewById(R.id.img7);
                else if(k == 8)
                    img = findViewById(R.id.img8);

                if(player == 1){
                    img.setImageResource(R.drawable.zero);
                }else{
                    img.setImageResource(R.drawable.cross);
                }
                img.bringToFront();
                blockWinner[k] = player;
                checkGameWinner();
            }
        }
    }

    public void checkGameWinner(){
        for(int i = 0; i < winStates.length; i++)
        {
            if(blockWinner[winStates[i][0]] == blockWinner[winStates[i][1]] && blockWinner[winStates[i][1]] == blockWinner[winStates[i][2]] && blockWinner[winStates[i][0]] != -1)
            {
                isWinner = true;
                dialoge_winner();
            }
        }
    }

    public void dialoge_winner(){   // this is done by shaurya
        new AlertDialog.Builder(this)
                .setIcon(R.drawable.winner)
                .setTitle("Congratulation")
                .setMessage("Player "+(player == 2?1:2)+" is Winner!")
                .setNeutralButton("OK", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialogInterface, int i) {

                    }
                }).show();
    }

    public static String baseConversion(String number, int sBase, int dBase)
    {
        return Integer.toString(
                Integer.parseInt(number, sBase), dBase);
    }

    public void reset(View view)
    {
        String imgId;
        for(int i = 0; i < 9; i++){
            imgId = "img" + i;
            int resId = getResources().getIdentifier(imgId, "id",getPackageName());
            ImageView v = (ImageView) findViewById(resId);
            v.setImageDrawable(null);
        }
        for(int i = 0; i < 81; i++){
            String j = baseConversion(String.valueOf(i), 10, 9);
            if(i < 9)
                imgId = "img0" + j;
            else
                imgId = "img" + j;
            int resId = getResources().getIdentifier(imgId,"id",getPackageName());
            ImageView v = (ImageView) findViewById(resId);
            v.setImageDrawable(null);
        }
        for(int i = 0; i < 81; i++){
            cellClicked[i] = -1;
        }
        for(int i = 0; i < 9; i++){
            blockWinner[i] = -1;
        }
        isWinner = false;
        player = 1;
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
    }
}
