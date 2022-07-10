package com.example.basicandroidconcepts;

import android.content.Context;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.graphics.RectF;
import android.view.View;

public class cPlantsHousePlan extends View {

    //Declaration of components needed
    private Paint painter;
    private Paint blackpaint;
    private Paint yellowpaint;
    private Paint lightbluepaint;

    RectF rect;

    public cPlantsHousePlan(Context context) {
        super(context);
        initializeView();
    }

    private void initializeView() {
        //Instantiation of paints
        painter = new Paint();
        blackpaint = new Paint();
        yellowpaint = new Paint();
        lightbluepaint = new Paint();
        rect = new RectF();
    }

    @Override
    protected void onDraw(Canvas canvas) {
        //setting paint attributes
        painter.setColor(Color.GREEN);
        painter.setStrokeWidth(35f);

        blackpaint.setColor(Color.BLACK);
        blackpaint.setStrokeWidth(35f);

        yellowpaint.setColor(Color.YELLOW);
        yellowpaint.setStrokeWidth(30f);

        lightbluepaint.setColor(Color.parseColor("#00FFFF"));
        lightbluepaint.setStrokeWidth(35f);
        lightbluepaint.setTextSize(80);
        lightbluepaint.setFakeBoldText(true);

        //cPlantsHousePlan arcs
        rect.set(1, 80, canvas.getWidth()/3, canvas.getWidth()/3);
        canvas.drawArc(rect, 45, 90, false, lightbluepaint);
        canvas.drawArc(rect, -90, -180, true, painter);
        canvas.drawArc(rect, -0, -125, false, yellowpaint);
        canvas.drawArc(rect, 45, -120, false, blackpaint);

        //cPlantsHousePlan the lines
        canvas.drawLine(canvas.getWidth()/2-180, canvas.getWidth(), canvas.getWidth()/2+180, canvas.getWidth(), blackpaint);
        canvas.drawLine(canvas.getWidth()/2-180, canvas.getWidth() + 200, canvas.getWidth()/2+180, canvas.getWidth() + 200, blackpaint);
        canvas.drawLine(0, canvas.getWidth()/2, canvas.getWidth(), canvas.getWidth()/2, painter);
        canvas.drawLine(0, canvas.getWidth()/2, canvas.getWidth()/2, 0, painter);
        canvas.drawLine(canvas.getWidth()/2, 0, canvas.getWidth(), canvas.getWidth()/2,painter);
        canvas.drawLine(canvas.getWidth()/2, 0, canvas.getWidth()/2, canvas.getWidth() + 200,painter);
        canvas.drawLine(canvas.getWidth()/5, 0, canvas.getWidth()/2+200, canvas.getWidth()/2,painter);
        canvas.drawLine(canvas.getWidth()/5 - 50, canvas.getWidth()/2, canvas.getWidth()/5 - 50, canvas.getWidth() + 400, painter);
        canvas.drawLine(canvas.getWidth()/2 + canvas.getWidth()/5 + 200, canvas.getWidth()/2, canvas.getWidth()/2 + canvas.getWidth()/5 + 200, canvas.getWidth() + 400, painter);
        canvas.drawLine(canvas.getWidth()/5 - 50, canvas.getWidth() + 400, canvas.getWidth()/2 + canvas.getWidth()/5 + 200,canvas.getWidth() + 400, painter);
        canvas.drawLine(canvas.getWidth()/2-180, canvas.getWidth(),canvas.getWidth()/2-180, canvas.getWidth() + 200, painter);
        canvas.drawLine(canvas.getWidth()/2+180, canvas.getWidth(),canvas.getWidth()/2+180, canvas.getWidth() + 200, painter);
        canvas.drawLine(canvas.getWidth()/2-90, canvas.getWidth() + 100, canvas.getWidth()/2+90,canvas.getWidth() + 100, painter);
        canvas.drawLine(canvas.getWidth()/5 - 50, canvas.getWidth()-100,canvas.getWidth()/2 + canvas.getWidth()/5 + 200, canvas.getWidth()-100, yellowpaint);
        canvas.drawLine(canvas.getWidth()/2-90, canvas.getWidth()/2,canvas.getWidth()/2-90, canvas.getWidth(), lightbluepaint);
        canvas.drawLine(canvas.getWidth()/2+90, canvas.getWidth()/2,canvas.getWidth()/2+90, canvas.getWidth(), lightbluepaint);

        canvas.drawText("Plants House Plan", 230, canvas.getWidth() + 510, lightbluepaint);
        super.onDraw(canvas);
        canvas.save();
    }
}
