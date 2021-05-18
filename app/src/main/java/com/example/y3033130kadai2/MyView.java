package com.example.y3033130kadai2;

import android.content.Context;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.graphics.Rect;
import android.util.AttributeSet;
import android.view.MotionEvent;
import android.view.View;

import java.util.ArrayList;

public class MyView extends View {

    // イベント発生時のX 座標、Y 座標，色，太さを保存するための動的配列
    private ArrayList array_x, array_y;
    private ArrayList array_status;
    private ArrayList array_color;
    private ArrayList array_width;
    public String colorflg = "red"; // ペンの色の交換を伝達
    public String widthflg;

    // Constructor
    public MyView(Context context, AttributeSet attrs) {
        super(context, attrs);
        array_x = new ArrayList();
        array_y = new ArrayList();
        array_status = new ArrayList();
        array_color = new ArrayList();
        array_width = new ArrayList();
    }

    // タッチパネルを操作した時に呼ばれるメソッド
    @Override
    public boolean onTouchEvent(MotionEvent event) {
        // 座標を取得
        int x = (int) event.getX();
        int y = (int) event.getY();
        // イベントに応じて動作を変更
        switch (event.getAction()) {
            case MotionEvent.ACTION_DOWN: // タッチパネルが押されたとき
            case MotionEvent.ACTION_POINTER_DOWN:
                array_x.add(new Integer(x)); // 座標を配列に保存
                array_y.add(new Integer(y));
                array_color.add(new String(colorflg));
                array_width.add(new String(widthflg));
                array_status.add(new Boolean(false)); // 線の描画はしない(false)
                invalidate(); // 画面を強制的に再描画
                break;
            case MotionEvent.ACTION_MOVE:
                array_x.add(new Integer(x)); // 座標を配列に保存
                array_y.add(new Integer(y)); // 線の描画をする(true)
                array_color.add(new String(colorflg));
                array_width.add(new String(widthflg));
                array_status.add(new Boolean(true));
                invalidate(); // 画面を強制的に再描画
                break;
            case MotionEvent.ACTION_UP: // タッチパネルから離れたとき
            case MotionEvent.ACTION_POINTER_UP:
                array_x.add(new Integer(x)); // 座標を配列に保存
                array_y.add(new Integer(y)); // 線の描画をする(true)
                array_color.add(new String(colorflg));
                array_width.add(new String(widthflg));
                array_status.add(new Boolean(true));
                invalidate(); // 画面を強制的に再描画
                break;
        }
        return true;
    }

    // ビューの描画を行うときに呼ばれるメソッド
    @Override
    protected void onDraw(Canvas canvas) {
        super.onDraw(canvas);
        // 背景を白色で塗りつぶす
        Paint p = new Paint();
        p.setStyle(Paint.Style.FILL);
        p.setColor(Color.WHITE);
        canvas.drawRect(new Rect(0, 0,
                canvas.getWidth(), canvas.getHeight()), p);

        // 配列内の座標を読み出して線（軌跡）を描画
        for (int i = 1; i < array_status.size(); i++) {
            // 描画するように(true)状態値が与えられているとき
            // 一度離してしてから次に押されるまでの移動分は描画しない
            if ((Boolean) array_status.get(i)) {
                // 開始点の終了点の座標の値を取得
                int x1 = (Integer) array_x.get(i - 1);
                int x2 = (Integer) array_x.get(i);
                int y1 = (Integer) array_y.get(i - 1);
                int y2 = (Integer) array_y.get(i);

                // 描画用のPaint オブジェクトを用意
                p = new Paint();
                p.setStyle(Paint.Style.STROKE);

                switch ( (String) array_color.get(i-1) ) {
                    case "red":
                        p.setColor(Color.RED);
                        break;
                    case "blue":
                        p.setColor(Color.BLUE);
                        break;
                    default:
                        p.setColor(Color.BLACK);
                        break;
                }

                switch ( (String) array_width.get(i-1) ) {
                    case "中":
                        p.setStrokeWidth(5);
                        break;
                    case "太":
                        p.setStrokeWidth(10);
                        break;
                    default:
                        p.setStrokeWidth(3);
                }
                // 線を描画
                canvas.drawLine(x1, y1, x2, y2, p);

                /* テスト用
                // 黒の中太の線
                p.setColor(Color.BLACK);
                p.setStrokeWidth(5);
                float[] pts = {50, 50, 100, 100};
                canvas.drawLines(pts, p);

                // 青の太い線
                p.setColor(Color.BLUE);
                p.setStrokeWidth(10);
                float[] pts2 = {100, 100, 150, 150};
                canvas.drawLines(pts2, p);
                 */
            }
        }
    }

    public void array_clear(){
        array_x.clear();
        array_y.clear();
        array_color.clear();
        array_status.clear();
        invalidate();
    }
}