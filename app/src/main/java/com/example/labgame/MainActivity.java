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
import android.widget.ImageView;
import android.widget.SeekBar;
import android.widget.TextView;
import android.widget.Toast;

import java.util.Random;

public class MainActivity extends AppCompatActivity {

    private TextView txtCoin, txtBet;
    private EditText edTextBet;
    private Button btnStart, btnReset;
    private ImageView imgWinner;
    private CheckBox cbOne, cbTwo, cbThree, cbFour;
    private SeekBar sbOne, sbTwo, sbThree, sbFour;
    private int bet = 0;
    private int coin = 0;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        mapping();
        disableSeekbar();

        String coinString = txtCoin.getText().toString();
        coin = Integer.parseInt(coinString);


        CountDownTimer countDownTimer = new CountDownTimer(60000, 300) {
            @Override
            public void onTick(long millisUntilFinished) {
                int number = 4;
                Random random = new Random();
                int one = random.nextInt(number);
                int two = random.nextInt(number);
                int three = random.nextInt(number);
                int four = random.nextInt(number);
                btnReset.setEnabled(false);

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
                    imgWinner.setImageResource(R.drawable.duck60);
                    edTextBet.setEnabled(true);
                    btnReset.setEnabled(true);
                    enableCheckbox();
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
                    imgWinner.setImageResource(R.drawable.dog_green60);
                    edTextBet.setEnabled(true);
                    btnReset.setEnabled(true);
                    enableCheckbox();
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
                    imgWinner.setImageResource(R.drawable.dog60);
                    edTextBet.setEnabled(true);
                    btnReset.setEnabled(true);
                    enableCheckbox();
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
                    imgWinner.setImageResource(R.drawable.mouse60);
                    edTextBet.setEnabled(true);
                    btnReset.setEnabled(true);
                    enableCheckbox();
                }

                sbOne.setProgress(sbOne.getProgress() + one);
                sbTwo.setProgress(sbTwo.getProgress() + two);
                sbThree.setProgress(sbThree.getProgress() + three);
                sbFour.setProgress(sbFour.getProgress() + four);
            }

            @Override
            public void onFinish() {
            }
        };

        cbOne.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {
                disableCheckedCheckbox();
            }
        });

        cbTwo.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {
                disableCheckedCheckbox();
            }
        });

        cbThree.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {
                disableCheckedCheckbox();
            }
        });

        cbFour.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {
                disableCheckedCheckbox();
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
                    txtBet.setText(betString);
                }

                if ((cbOne.isChecked() || cbTwo.isChecked() || cbThree.isChecked()) && bet > 0) {
                    resetSeekBar();
                    disableSeekbar();
                    edTextBet.setEnabled(false);
                    countDownTimer.start();
                    disableCheckbox();
                } else {
                    Toast.makeText(MainActivity.this, "Please bet before playing!", Toast.LENGTH_SHORT).show();
                }
            }
        });

        btnReset.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                resetSeekBar();
                resetCheckbox();
                enableCheckbox();
                txtCoin.setText("100");
                edTextBet.setText(0 + "");
                edTextBet.setEnabled(true);
                txtBet.setText("0");
                imgWinner.setImageResource(android.R.color.transparent);
                countDownTimer.cancel();
            }
        });
    }

    private void mapping() {
        txtCoin = (TextView) findViewById(R.id.coin);
        txtBet = (TextView) findViewById(R.id.textViewBet);
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
        imgWinner = (ImageView) findViewById(R.id.imgWinner);
    }

    private void resetSeekBar() {
        sbOne.setProgress(0);
        sbTwo.setProgress(0);
        sbThree.setProgress(0);
        sbFour.setProgress(0);
    }

    private void resetCheckbox() {
        cbOne.setChecked(false);
        cbTwo.setChecked(false);
        cbThree.setChecked(false);
        cbFour.setChecked(false);
    }

    private void disableSeekbar() {
        sbOne.setEnabled(false);
        sbTwo.setEnabled(false);
        sbThree.setEnabled(false);
        sbFour.setEnabled(false);
    }

    private void disableCheckedCheckbox() {
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

    private void disableCheckbox() {
        cbOne.setEnabled(false);
        cbTwo.setEnabled(false);
        cbThree.setEnabled(false);
        cbFour.setEnabled(false);
    }

    private void enableCheckbox() {
        cbOne.setEnabled(true);
        cbTwo.setEnabled(true);
        cbThree.setEnabled(true);
        cbFour.setEnabled(true);
    }

}