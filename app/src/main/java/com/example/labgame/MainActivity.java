package com.example.labgame;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.os.CountDownTimer;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.CompoundButton;
import android.widget.EditText;
import android.widget.SeekBar;
import android.widget.TextView;
import android.widget.Toast;

import java.util.Random;

public class MainActivity extends AppCompatActivity {

    private TextView txtCoin;
    private EditText edTextBet;
    private Button btnStart, btnReset;
    private CheckBox cbOne, cbTwo, cbThree, cbFour;
    private SeekBar sbOne, sbTwo, sbThree, sbFour;
    private int bet = 0;
    private int coin = 0;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        mapping();
        String coinString = txtCoin.getText().toString();
        coin = Integer.parseInt(coinString);


        CountDownTimer countDownTimer = new CountDownTimer(30000, 100) {
            @Override
            public void onTick(long millisUntilFinished) {
                int number = 2;
                Random random = new Random();
                int one = random.nextInt(number);
                int two = random.nextInt(number);
                int three = random.nextInt(number);
                int four = random.nextInt(number);

                if (sbOne.getProgress() >= sbOne.getMax()) {
                    this.cancel();
                    Toast.makeText(MainActivity.this, "ONE WIN", Toast.LENGTH_SHORT).show();

                    if (cbOne.isChecked()) {
                        coin += bet;
                        Toast.makeText(MainActivity.this, "You guessed correctly!", Toast.LENGTH_SHORT).show();
                    } else {
                        coin -= bet;
                        Toast.makeText(MainActivity.this, "You guessed wrong!", Toast.LENGTH_SHORT).show();
                    }
                    txtCoin.setText(coin + "");
                }

                if (sbTwo.getProgress() >= sbTwo.getMax()) {
                    this.cancel();
                    Toast.makeText(MainActivity.this, "TWO WIN", Toast.LENGTH_SHORT).show();

                    if (cbTwo.isChecked()) {
                        coin += bet;
                        Toast.makeText(MainActivity.this, "You guessed correctly!", Toast.LENGTH_SHORT).show();
                    } else {
                        coin -= bet;
                        Toast.makeText(MainActivity.this, "You guessed wrong!", Toast.LENGTH_SHORT).show();

                    }
                    txtCoin.setText(coin + "");
                }

                if (sbThree.getProgress() >= sbThree.getMax()) {
                    this.cancel();
                    Toast.makeText(MainActivity.this, "THREE WIN", Toast.LENGTH_SHORT).show();

                    if (cbThree.isChecked()) {
                        coin += bet;
                        Toast.makeText(MainActivity.this, "You guessed correctly!", Toast.LENGTH_SHORT).show();
                    } else {
                        coin -= bet;
                        Toast.makeText(MainActivity.this, "You guessed wrong!", Toast.LENGTH_SHORT).show();
                    }
                    txtCoin.setText(coin + "");
                }

                if (sbFour.getProgress() >= sbFour.getMax()) {
                    this.cancel();
                    Toast.makeText(MainActivity.this, "FOUR WIN", Toast.LENGTH_SHORT).show();

                    if (cbFour.isChecked()) {
                        coin += bet;
                        Toast.makeText(MainActivity.this, "You guessed correctly!", Toast.LENGTH_SHORT).show();
                    } else {
                        coin -= bet;
                        Toast.makeText(MainActivity.this, "You guessed wrong!", Toast.LENGTH_SHORT).show();

                    }
                    txtCoin.setText(coin + "");
                }

                sbOne.setProgress(sbOne.getProgress() + one);
                sbTwo.setProgress(sbOne.getProgress() + two);
                sbThree.setProgress(sbOne.getProgress() + three);
//                sbFour.setProgress(sbOne.getProgress() + four);
            }

            @Override
            public void onFinish() {

            }
        };

        cbOne.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {
                disableCheckbox();
            }
        });

        cbTwo.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {
                disableCheckbox();
            }
        });

        cbThree.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {
                disableCheckbox();
            }
        });

        cbFour.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {
                disableCheckbox();
            }
        });

        btnStart.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String betString = edTextBet.getText().toString();
                if (betString == null) {
                    Toast.makeText(MainActivity.this, "Please input the coin bet to playing", Toast.LENGTH_SHORT).show();
                } else {
                    bet = Integer.parseInt(betString);
                }

                if ((cbOne.isChecked() || cbTwo.isChecked() || cbThree.isChecked()) && bet > 0) {
                    edTextBet.setEnabled(false);
                    countDownTimer.start();
                } else {
                    Toast.makeText(MainActivity.this, "Please bet before playing!", Toast.LENGTH_SHORT).show();
                }
            }
        });

        btnReset.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                sbOne.setProgress(0);
                sbTwo.setProgress(0);
                sbThree.setProgress(0);
                sbFour.setProgress(0);
                txtCoin.setText(100 + "");
            }
        });
    }

    private void mapping() {
        txtCoin = (TextView) findViewById(R.id.coin);
        edTextBet = (EditText) findViewById(R.id.editTextBet);
        cbOne = (CheckBox) findViewById(R.id.checkBoxOne);
        cbTwo = (CheckBox) findViewById(R.id.checkBoxTwo);
        cbThree = (CheckBox) findViewById(R.id.checkBoxThree);
        cbFour = (CheckBox) findViewById(R.id.checkBoxFour);
        sbOne = (SeekBar) findViewById(R.id.seekBarOne);
        sbTwo = (SeekBar) findViewById(R.id.seekBarTwo);
        sbThree = (SeekBar) findViewById(R.id.seekBarThree);
        sbFour = (SeekBar) findViewById(R.id.seekBarFour);
        btnStart = (Button) findViewById(R.id.btnPLay);
        btnReset = (Button) findViewById(R.id.btnReset);
    }

    private void disableCheckbox() {
        if(cbOne.isChecked()) {
           cbTwo.setChecked(false);
           cbThree.setChecked(false);
           cbFour.setChecked(false);
        }
        if (cbTwo.isChecked()) {
            cbOne.setChecked(false);
            cbThree.setChecked(false);
            cbFour.setChecked(false);
        }
        if (cbThree.isChecked()) {
            cbOne.setChecked(false);
            cbTwo.setChecked(false);
            cbFour.setChecked(false);
        }
        if (cbFour.isChecked()) {
            cbOne.setChecked(false);
            cbTwo.setChecked(false);
            cbThree.setChecked(false);
        }
    }

}