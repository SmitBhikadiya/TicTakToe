package com.spatel.tictaktoe;

import androidx.appcompat.app.AppCompatActivity;

import android.graphics.Color;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import static android.graphics.Color.BLACK;
import static android.graphics.Color.RED;
import static android.graphics.Color.WHITE;

public class MainActivity extends AppCompatActivity implements View.OnClickListener{

    private Button[][] buttons = new Button[3][3];

    private boolean player1turn = true;
    private int countRound = 0;
    private int player1point = 0;
    private int player2point = 0;

    private TextView txtforplayer1;
    private TextView txtforplayer2;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        for(int i=0;i<3;i++){
            for(int j=0;j<3;j++){
                String btnid = "button"+i+j;
                int resid = getResources().getIdentifier(btnid,"id", getPackageName());
                buttons[i][j] = findViewById(resid);
                buttons[i][j].setOnClickListener(this);
            }
        }

        txtforplayer1 = findViewById(R.id.txtPlayer1);
        txtforplayer2 = findViewById(R.id.txtPlayer2);
        Button resetBtn = findViewById(R.id.btnReset);

        resetBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                resetbtndata();
                updatepoints();
            }
        });
    }

    private void resetbtndata() {
        resetdata();
        player1point = 0;
        player2point = 0;
        updatepoints();
    }

    @Override
    public void onClick(View view) {
        if(!((Button) view).getText().toString().equals("")) {
            return;
        }

        if(player1turn){
            ((Button) view).setText("X");
            final int color = 0xFF1100;
            ((Button) view).setTextColor(color);
        }
        else{
            ((Button) view).setText("O");
            ((Button) view).setTextColor(BLACK);
        }

        countRound++;

        if(Checkforwin()){
            Log.i("tag", String.valueOf(player1turn));
            if(player1turn){
                player1wins();
            }
            else{
                player2wins();
            }
        }
        else if(countRound == 9){
            draw();
        }
        else{
            player1turn = !player1turn;
        }
    }

    private boolean Checkforwin(){
        String[][]  allfield = new String[3][3];

        for(int i=0;i<=2;i++){
            for(int j=0;j<=2;j++){
                allfield[i][j] = buttons[i][j].getText().toString();
                Log.i("tag", allfield[i][j]);
            }
        }

        for(int i=0;i<3;i++){
            if(!allfield[0][i].equals("") && allfield[0][i].equals(allfield[1][i]) && allfield[0][i].equals(allfield[2][i])){
                Log.i("tag", "Win");
                return true;
            }
            if(!allfield[i][0].equals("") && allfield[i][0].equals(allfield[i][1]) && allfield[i][0].equals(allfield[i][2])){
                return true;
            }
            if(allfield[0][0].equals(allfield[1][1]) && allfield[1][1].equals(allfield[2][2]) && !allfield[0][0].equals("")){
                return true;
            }
            if(allfield[0][2].equals(allfield[1][1]) && allfield[1][1].equals(allfield[2][0]) && !allfield[0][2].equals("")){
                return true;
            }
        }
        return false;
    }

    private void player1wins(){
        Log.i("tag", "player1wins");
        player1point += 1;
        Toast.makeText(this,"Player 1 Wins",Toast.LENGTH_SHORT).show();
        updatepoints();
        resetdata();
    }

    private void player2wins(){
        Log.i("tag", "player2wins");
        player2point += 1;
        Toast.makeText(this, "Player 2 Wins", Toast.LENGTH_SHORT).show();
        updatepoints();
        resetdata();
    }
    private void draw(){
        Toast.makeText(this,"Match is Draw",Toast.LENGTH_SHORT).show();
        resetdata();
    }

    private void resetdata() {
        for(int i=0;i<=2;i++){
            for(int j=0;j<=2;j++){
                buttons[i][j].setText("");
            }
        }
        countRound = 0;
        Log.i("tag", "ResetDataOK");
    }

    private void updatepoints(){
        txtforplayer1.setText("Player1: "+player1point);
        txtforplayer2.setText("Player2: "+player2point);
        Log.i("tag", "updatepointsOK");

    }

}