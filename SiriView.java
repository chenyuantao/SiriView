package com.tao.view;

import android.content.Context;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.graphics.Paint.Style;
import android.graphics.Path;
import android.os.Handler;
import android.os.Message;
import android.util.AttributeSet;
import android.view.View;

public class SiriView extends View {

	// 内部
	private int progress;
	private Thread thread;
	private float targetHeight;
	private Paint paint;
	private Path path;
	// 属性
	private float waveHeight;
	private float waveWidth;
	private int waveColor;
	private float waveOffsetX;
	private int waveAmount;
	private float waveSpeed;

	public SiriView(Context context) {
		super(context);
		init(context);
	}

	public SiriView(Context context, AttributeSet attr) {
		super(context, attr);
		init(context);
	}

	public SiriView(Context context, AttributeSet attr, int style) {
		super(context, attr, style);
		init(context);
	}

	private void init(Context context) {
		paint = new Paint();
		paint.setAntiAlias(true);
		paint.setStyle(Style.STROKE);
		path = new Path();
		waveSpeed = 0.1f;
		waveOffsetX = 0f;
		waveAmount = 4;
		waveColor = Color.rgb(39, 188, 136);
		waveHeight = 1f;
		targetHeight = 1f;
		waveWidth = 5f;
		progress = 0;
		thread = new Thread(runnable);
		thread.start();
	}

	private Handler handler = new Handler() {
		@Override
		public void handleMessage(Message msg) {
			if (msg.what == 1) {
				// 通知控件重绘
				invalidate();
			}
		}
	};

	private Runnable runnable = new Runnable() {

		@Override
		public void run() {
			try {
				while (true) {
					handler.sendEmptyMessage(1);
					Thread.sleep((int) (1 / waveSpeed));
					progress += 1;
					if (progress >= (20 * waveAmount)) {
						progress = 0;
					}
					if (waveHeight < targetHeight) {
						waveHeight += 0.01f;
					} else {
						waveHeight -= 0.01f;
					}
				}
			} catch (InterruptedException e) {
				e.printStackTrace();
			}

		}

	};

	@Override
	protected void onDraw(Canvas canvas) {
		super.onDraw(canvas);
		float line = canvas.getWidth() / waveAmount;
		path.reset();
		paint.setColor(waveColor);
		paint.setStrokeWidth(waveWidth);
		float h;
		for (int i = 0; i < (waveAmount * 2); i++) {
			float x1 = (-canvas.getWidth() + line * i) + line * waveOffsetX;
			float x2 = (-canvas.getWidth() + line * (i + 0.5f)) + line
					* waveOffsetX;
			float x3 = (-canvas.getWidth() + line * (i + 1f)) + line
					* waveOffsetX;

			if (i % 2 == 0) {
				h = (float) (canvas.getHeight() / 2 + canvas.getHeight() / 2
						* waveHeight);
			} else {
				h = (float) (canvas.getHeight() / 2 - canvas.getHeight() / 2
						* waveHeight);
			}
			path.moveTo(x1 + canvas.getWidth() * progress / (20 * waveAmount),
					canvas.getHeight() / 2);
			path.quadTo(x2 + canvas.getWidth() * progress / (20 * waveAmount),
					h, x3 + canvas.getWidth() * progress / (20 * waveAmount),
					canvas.getHeight() / 2);
		}
		canvas.drawPath(path, paint);
	}

	public void stop() {
		targetHeight = 0;
	}

	public void setWaveHeight(float height) {
		this.targetHeight = height;
	}

	public float getWaveHeight() {
		return this.waveHeight;
	}

	public void setWaveWidth(float width) {
		this.waveWidth = width;
	}

	public float getWaveWidth() {
		return this.waveWidth;
	}

	public int getWaveColor() {
		return waveColor;
	}

	public void setWaveColor(int waveColor) {
		this.waveColor = waveColor;
	}

	public float getWaveOffsetX() {
		return waveOffsetX;
	}

	public void setWaveOffsetX(float waveOffsetX) {
		this.waveOffsetX = waveOffsetX;
	}

	public int getWaveAmount() {
		return waveAmount;
	}

	public void setWaveAmount(int waveAmount) {
		this.waveAmount = waveAmount;
	}

	public float getWaveSpeed() {
		return waveSpeed;
	}

	public void setWaveSpeed(float waveSpeed) {
		this.waveSpeed = waveSpeed;
	}
	
	

}
