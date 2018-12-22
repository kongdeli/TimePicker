package dev.kdl.custompicker;

import android.content.Context;
import android.graphics.Color;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.Gravity;
import android.view.View;
import android.view.ViewGroup;
import android.view.Window;
import android.view.WindowManager;
import android.widget.Button;
import android.widget.FrameLayout;

import com.bigkoo.pickerview.builder.TimePickerBuilder;
import com.bigkoo.pickerview.listener.OnTimeSelectListener;
import com.bigkoo.pickerview.view.TimePickerView;
import com.contrarywind.view.WheelView;

import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;

public class MainActivity extends AppCompatActivity {
    private TimePickerView pvTime;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
//        initTimePicker();
//        final BottomDialog dialog = new BottomDialog(this);
        findViewById(R.id.bt).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                initTimePicker();
                pvTime.show(v);
            }
        });
    }

    private void initTimePicker() {
        //控制时间范围(如果不设置范围，则使用默认时间1900-2100年，此段代码可注释)
        //因为系统Calendar的月份是从0-11的,所以如果是调用Calendar的set方法来设置时间,月份的范围也要是从0-11
        Calendar selectedDate = Calendar.getInstance();

        Calendar startDate = Calendar.getInstance();
        startDate.set(2013, 0, 23);

        Calendar endDate = Calendar.getInstance();
        endDate.set(2019, 11, 28);
        //时间选择器
        pvTime = new TimePickerBuilder(this, new OnTimeSelectListener() {
            @Override
            public void onTimeSelect(Date date, View v) {//选中事件回调
                // 这里回调过来的v,就是show()方法里面所添加的 View 参数，如果show的时候没有添加参数，v则为null
                /*btn_Time.setText(getTime(date));*/
                Button btn = (Button) v;
                btn.setText(getTime(date));
            }
        })
                .setType(new boolean[]{true, true, true, true, true, false})
                .setMinuteInterval(6)
                .setLineSpacingMultiplier(2f)
                .setLabel("年", "月", "日", "时", "分", "") //设置空字符串以隐藏单位提示   hide label
                .setBgColor(Color.WHITE)
                .setSubmitColor(Color.parseColor("#4097FC"))
                .setSubmitText("确定")
                .setCancelColor(Color.parseColor("#888888"))
                .setCancelText("取消")
                .setDividerType(WheelView.DividerType.FILL)
                .setDividerColor(Color.parseColor("#DDDDDD"))
                .setTitleBgColor(Color.WHITE)
                .setTitleSize(17)
                .setTitleText("选择时间")
                .setContentTextSize(20)
                .setDate(selectedDate)
                .setRangDate(startDate, selectedDate)
                .setBackgroundId(0x00000000)
                .setOutSideCancelable(true)
                .isDialog(true)
                .build();

        WindowManager wm = (WindowManager) getSystemService(Context.WINDOW_SERVICE);
        int width = wm.getDefaultDisplay().getWidth();
        FrameLayout.LayoutParams params = new FrameLayout.LayoutParams(width, ViewGroup.LayoutParams.WRAP_CONTENT);
        params.leftMargin = 0;
        params.rightMargin = 0;
        pvTime.getDialogContainerLayout().setLayoutParams(params);
        Window window = pvTime.getDialog().getWindow();
        window.setWindowAnimations(R.style.BottomDialog_Animation);
        window.setGravity(Gravity.BOTTOM);

        pvTime.setKeyBackCancelable(false);//系统返回键监听屏蔽掉
    }

    private String getTime(Date date) {//可根据需要自行截取数据显示
        SimpleDateFormat format = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
        return format.format(date);
    }
}
