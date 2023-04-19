package com.swj.tp03oneto25;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import java.util.ArrayList;
import java.util.Collections;

public class MainActivity extends AppCompatActivity {

    TextView tv;
    Button btnRetry;

    // 버튼 배열 참조변수 길이 25
    Button[] btnArr = new Button[25];

    // xml 파일 버튼 id값 실수로 값이 중간에 안 들어갈 경우 노가다..
    //int[] ids = new int[] {};

    int num = 1; // 현재 눌러야 할 번호

    ArrayList<Integer> nums = new ArrayList<>();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        tv = findViewById(R.id.tv);
        btnRetry = findViewById(R.id.btn_retry);

        for(int i=0; i<btnArr.length; i++) {
            btnArr[i] = findViewById(R.id.b01 + i);
            btnArr[i].setOnClickListener(listener);
            nums.add(i+1);
        }

        gameStart(false);

        btnRetry.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                gameStart(true);
                btnRetry.setEnabled(false);
                num = 1;
                tv.setText("1");
            }
        });
    }// onCreate()

    private void gameStart(boolean isRetry) {
        Collections.shuffle(nums);

        for(int i=0; i<btnArr.length; i++) {
            btnArr[i].setText(nums.get(i)+"");
            btnArr[i].setTag(nums.get(i));
            if(isRetry) btnArr[i].setVisibility(View.VISIBLE);
        }
    }

    View.OnClickListener listener = new View.OnClickListener() {

        @Override  // 파라미터 View : 클릭된 객체의 참조변수 - 업캐스팅
        public void onClick(View view) {
            // 클릭된 버튼에 있는 글씨가 num와 같은지 비교
            // 다운 캐스팅 - Button의 고유기능 getText() 사용을 위해
            //Button btn = (Button)view;

            // 실제 게임에서는 텍스트값을 넣지않고 이미지를 넣는다.
            // 이미지만 가지고는 그게 어떤 숫자인지 잘 모른다. (가져올 방법이 없음)
            //String s = btn.getText().toString();

            // 버튼뷰에 저장된 tag값을 읽어와서 num와 같은지 비교
            String s = view.getTag().toString();
            int n = Integer.parseInt(s);

            if(num == n) {
                //btn.setText("OK");
                // 자바는 xml과 다르게 RGB만 있는게 아니라 투명도도 있음.
                // 투명도를 생략하면 자동 00(완전 투명)
                //btn.setTextColor(0xffff0000);
                //btn.setBackground(null); // 버튼은 배경이 그림임.(그림을 없앤 것임)
                view.setVisibility(View.INVISIBLE);

                num++;
                tv.setText(num+"");
            }

            if(num > 25) {
                tv.setText("STAGE CLEAR~~");
                btnRetry.setEnabled(true);
            }
        }
    };
}// MainActivity class