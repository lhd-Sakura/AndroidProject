package com.example.afinal;

import android.graphics.Color;
import android.os.Bundle;
import android.os.Handler;
import android.os.Message;
import android.support.v7.app.AppCompatActivity;
import com.github.mikephil.charting.charts.LineChart;
import com.github.mikephil.charting.components.Legend;
import com.github.mikephil.charting.data.Entry;
import com.github.mikephil.charting.data.LineData;
import com.github.mikephil.charting.data.LineDataSet;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import com.github.mikephil.charting.components.Legend.LegendForm;
import com.github.mikephil.charting.components.Legend.LegendPosition;

public class pic1Activity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_pic1);
        new Thread(new Runnable() {
            @Override
            public void run() {
                try {
                    Class.forName("com.mysql.jdbc.Driver");
                    Connection cn = DriverManager.getConnection("jdbc:mysql://192.168.1.103:3306/test", "root", "12345678");
                    String sql = "select * from food";
                    Statement st = (Statement) cn.createStatement();
                    ResultSet rs = st.executeQuery(sql);
                    ArrayList y1 = new ArrayList();
                    ArrayList y2 = new ArrayList();
                    ArrayList y3 = new ArrayList();
                    while (rs.next()) {
                        float y = rs.getFloat("temp");
                        String x=rs.getString("time");
                        y1.add(y);
                        y2.add(x);
                    }
                    y3.add(y1);
                    y3.add(y2);
                    Message msg = handler.obtainMessage();
                    msg.obj = y3;
                    handler.sendMessage(msg);
                    cn.close();
                    st.close();
                    rs.close();
                } catch (ClassNotFoundException e) {
                    e.printStackTrace();
                } catch (SQLException e) {
                    e.printStackTrace();
                }
            }
        }).start();
    }
    private Handler handler = new Handler() {
        public void handleMessage(Message msg) {
            ArrayList y1 = new ArrayList();
            y1.add(msg.obj);
            ArrayList y2 =(ArrayList) y1.get(0);
            ArrayList<Float> y =(ArrayList) y2.get(0);  //y轴
            System.out.println(y);
            ArrayList x =(ArrayList) y2.get(1);  //x轴
            System.out.println(x);
            LineChart chart = (LineChart) findViewById(R.id.chart);
            // 制作10个数据点（沿x坐标轴）
            LineData mLineData = makeLineData(x,y);
            setChartStyle(chart, mLineData, Color.WHITE);

        }


    };
    // 设置chart显示的样式
    private void setChartStyle(LineChart mLineChart, LineData lineData,int color) {
        // 是否在折线图上添加边框
        mLineChart.setDrawBorders(false);
        mLineChart.setDescription("温度");// 数据描述
        // 如果没有数据的时候，会显示这个，类似listview的emtpyview
        mLineChart.setNoDataTextDescription("");
        // 是否绘制背景颜色。
        // 如果mLineChart.setDrawGridBackground(false)，
        // 那么mLineChart.setGridBackgroundColor(Color.CYAN)将失效;
        mLineChart.setDrawGridBackground(false);
        mLineChart.setGridBackgroundColor(Color.CYAN);
        // 触摸
        mLineChart.setTouchEnabled(true);
        // 拖拽
        mLineChart.setDragEnabled(true);
        // 缩放
        mLineChart.setScaleEnabled(true);
        mLineChart.setPinchZoom(false);
        // 设置背景
        mLineChart.setBackgroundColor(color);
        // 设置x,y轴的数据
        mLineChart.setData(lineData);
        // 设置比例图标示，就是那个一组y的value的
        Legend mLegend = mLineChart.getLegend();
        mLegend.setPosition(LegendPosition.BELOW_CHART_CENTER);
        mLegend.setForm(LegendForm.CIRCLE);// 样式
        mLegend.setFormSize(15.0f);// 字体
        mLegend.setTextColor(Color.BLUE);// 颜色
        // 沿x轴动画，时间2000毫秒。
        mLineChart.animateX(2000);
    }
    private LineData makeLineData(ArrayList xval,ArrayList<Float> yval) {
        ArrayList x = new ArrayList();
        for (int i = 0; i < 10; i++) {
            // x轴显示的数据
            x.add(xval.get(i));
        }
        // y轴的数据
        ArrayList<Entry> y = new ArrayList<Entry>();
        Float[] a  = yval.toArray(new Float[0]);
        float[] b = new float[10];
        for (int i = 0; i < 10; i++) {
            b[i] = a[i].floatValue();
            Entry entry = new Entry(b[i],i);
            y.add(entry);
        }
        // y轴数据集
        LineDataSet mLineDataSet = new LineDataSet(y, "测试数据集。by ZhangPhil");
        // 用y轴的集合来设置参数
        // 线宽
        mLineDataSet.setLineWidth(3.0f);
        // 显示的圆形大小
        mLineDataSet.setCircleSize(5.0f);
        // 折线的颜色
        mLineDataSet.setColor(Color.DKGRAY);
        // 圆球的颜色
        mLineDataSet.setCircleColor(Color.GREEN);
        // 设置mLineDataSet.setDrawHighlightIndicators(false)后，
        // Highlight的十字交叉的纵横线将不会显示，
        // 同时，mLineDataSet.setHighLightColor(Color.CYAN)失效。
        mLineDataSet.setDrawHighlightIndicators(true);
        // 按击后，十字交叉线的颜色
        mLineDataSet.setHighLightColor(Color.CYAN);
        // 设置这项上显示的数据点的字体大小。
        mLineDataSet.setValueTextSize(10.0f);
        // mLineDataSet.setDrawCircleHole(true);
        // 改变折线样式，用曲线。
        // mLineDataSet.setDrawCubic(true);
        // 默认是直线
        // 曲线的平滑度，值越大越平滑。
        // mLineDataSet.setCubicIntensity(0.2f);
        // 填充曲线下方的区域，红色，半透明。
        // mLineDataSet.setDrawFilled(true);
        // mLineDataSet.setFillAlpha(128);
        // mLineDataSet.setFillColor(Color.RED);
        // 填充折线上数据点、圆球里面包裹的中心空白处的颜色。
        mLineDataSet.setCircleColorHole(Color.YELLOW);
        // 设置折线上显示数据的格式。如果不设置，将默认显示float数据格式。
        ArrayList<LineDataSet> mLineDataSets = new ArrayList<LineDataSet>();
        mLineDataSets.add(mLineDataSet);
        LineData mLineData = new LineData(x, mLineDataSets);
        return mLineData;
    }
}
