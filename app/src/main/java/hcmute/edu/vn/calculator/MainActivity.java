package hcmute.edu.vn.calculator;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

public class MainActivity extends AppCompatActivity  implements View.OnClickListener{
    TextView val, res;
    Button no0,no1,no2,no3,no4,no5,no6,no7,no8,no9;
    Button buttonPlus, buttonSubtract, buttonMultiple, buttonDivine;
    Button buttonPoint, buttonClear, buttonDelete, buttonEqual;
    String result = "";
    boolean check = false;
    boolean dot = false;
    String secdataCalculate = "";
    float num1 = 0;
    float num2 = 0;
    String type = "";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        val = findViewById(R.id.value);
        res = findViewById(R.id.result);

        assignId(no0,R.id.zero);
        assignId(no1,R.id.one);
        assignId(no2,R.id.two);
        assignId(no3,R.id.three);
        assignId(no4,R.id.four);
        assignId(no5,R.id.five);
        assignId(no6,R.id.six);
        assignId(no7,R.id.seven);
        assignId(no8,R.id.eight);
        assignId(no9,R.id.nine);
        assignId(buttonPlus,R.id.plus);
        assignId(buttonSubtract,R.id.subtract);
        assignId(buttonMultiple,R.id.multiple);
        assignId(buttonDivine,R.id.divine);
        assignId(buttonClear,R.id.clear);
        assignId(buttonDelete,R.id.delete);
        assignId(buttonEqual,R.id.equal);
        assignId(buttonPoint,R.id.point);
    }

     void assignId(Button btn, int id) {
            btn = findViewById(id);
            btn.setOnClickListener(this);
    }

    float Calculate(String type,float num1,float num2){
        float result = 0;
        switch (type){
            case "+":
                result = num1+num2;
                break;
            case "-":
                result = num1-num2;
                break;
            case "x":
                result = num1*num2;
                break;
            case "÷":
                result = num1/num2;
        }
        return result;
    }

    @Override
    public void onClick(View view) {
        Button button = (Button) view;
        String buttonText = button.getText().toString();
        String dataCalculate = val.getText().toString();

        if(buttonText.equals(".")){
            dot=true;
        }

        if(buttonText.equals("C")){
            num1 = 0;
            num2 = 0;
            check = false;
            dot = false;
            secdataCalculate = "";
            type = "";
            result = "";
            val.setText("");
            res.setText("0");
            return;
        }

        if(buttonText.equals("DEL") && dataCalculate.length()>0){
            dataCalculate = dataCalculate.substring(0,dataCalculate.length()-1);
            val.setText(dataCalculate);
            return;
        }

        if(buttonText.equals("+")||buttonText.equals("-")||buttonText.equals("x")||buttonText.equals("÷")){
            if(!result.equals("")){
                num1 = Float.parseFloat(result);
            }else{
                num1 = Float.parseFloat(dataCalculate);
            }
            type = buttonText;
            dataCalculate += buttonText;
            val.setText(dataCalculate);
            check = true;
            return;
        }

        if(buttonText.equals("=")){
            num2  = Float.parseFloat(secdataCalculate);
            secdataCalculate = "";
            float rs = Calculate(type,num1,num2);
            result = Float.toString(rs);
            if(dot==false) {
                int newrs = (int) rs;
                result = Integer.toString(newrs);
            }
            res.setText(result);
            val.setText(res.getText());
            return;
        }

        dataCalculate += buttonText;
        if(check==true) secdataCalculate += buttonText;
        val.setText(dataCalculate);
    }
}