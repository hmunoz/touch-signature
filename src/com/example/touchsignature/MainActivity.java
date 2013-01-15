package com.example.touchsignature;

import java.io.File;
import java.io.FileOutputStream;

import android.os.Bundle;
import android.os.Environment;
import android.app.Activity;
import android.gesture.GestureOverlayView;
import android.graphics.Bitmap;
import android.view.View;
import android.widget.Toast;

public class MainActivity extends Activity {

	private GestureOverlayView gestureView;

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_main);
		gestureView = (GestureOverlayView) findViewById(R.id.signaturePad);
	}

	public void tryAgain(View view) {
		gestureView.clear(false);
	}

	public void done(View view) {
		try {
			gestureView.setDrawingCacheEnabled(true);
			Bitmap bm = Bitmap.createBitmap(gestureView.getDrawingCache());
			File f = new File(Environment.getExternalStorageDirectory()
					+ File.separator + "signature.png");
			f.createNewFile();
			FileOutputStream os = new FileOutputStream(f);
			os = new FileOutputStream(f);
			// compress to specified format (PNG), quality - which is ignored
			// for PNG, and out stream
			bm.compress(Bitmap.CompressFormat.PNG, 100, os);
			os.close();

			Toast.makeText(this, "Done", Toast.LENGTH_LONG).show();
		} catch (Exception e) {
			Toast.makeText(this, e.getMessage(), Toast.LENGTH_LONG).show();
		}
	}

}
