package com.example.mateusz.goodgamesapplication;

import android.content.Intent;
import android.graphics.Bitmap;
import android.graphics.Color;
import android.media.MediaPlayer;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.MotionEvent;
import android.view.View;
import android.widget.ImageView;
import android.widget.Toast;

public class DisplayImageActivity extends AppCompatActivity
        implements View.OnTouchListener
{
    private String _name;
    private MediaPlayer _mp;

    private TouchImageView _topImage;
    private TouchImageView _topImageMask;
    private TouchImageView _bottomImage;
    private TouchImageView _bottomImageMask;

    private boolean _syncImages = true;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_display_message);

        Intent intent = getIntent();
        _name = intent.getStringExtra(MainActivity.EXTRA_MESSAGE);
        _syncImages = ! intent.getBooleanExtra(MainActivity.HARD_CHECKED, true);

        _topImage = (TouchImageView) findViewById (R.id.imageViewUp);
        _topImageMask = (TouchImageView) findViewById (R.id.imageViewMaskUp);
        _bottomImage = (TouchImageView) findViewById (R.id.imageViewDown);
        _bottomImageMask = (TouchImageView) findViewById (R.id.imageViewMaskDown);
        if (_bottomImage != null) {
            _bottomImage.setOnTouchListener (this);
        }

        _topImage.setOnTouchImageViewListener(new TouchImageView.OnTouchImageViewListener() {

            @Override
            public void onMove() {
                _topImageMask.setZoom(_topImage);
                if (_syncImages) {
                    _bottomImage.setZoom(_topImage);
                    _bottomImageMask.setZoom(_topImage);
                }
            }
        });

        _bottomImage.setOnTouchImageViewListener(new TouchImageView.OnTouchImageViewListener() {

            @Override
            public void onMove() {
                _bottomImageMask.setZoom(_bottomImage);
                if (_syncImages) {
                    _topImage.setZoom(_bottomImage);
                    _topImageMask.setZoom(_bottomImage);
                }
            }
        });

        _mp = MediaPlayer.create(this, R.raw.ring01);
/*
        TextView textView = new TextView(this);
        textView.setTextSize(40);
        textView.setText(message);

        ViewGroup layout = (ViewGroup) findViewById(R.id.activity_display_message);
        layout.addView(textView);
*/
    }

    public boolean onTouch(View view, MotionEvent ev) {
        final int action = ev.getAction();

        final int evX = (int) ev.getX();
        final int evY = (int) ev.getY();

        if (evX < 0 || evY < 0)
        {
            return true;
        }

        switch (action) {
            case MotionEvent.ACTION_DOWN :
                break;
            case MotionEvent.ACTION_UP :
                int touchColor = getHotspotColor (R.id.imageViewMaskDown, evX, evY);
                int tolerance = 25;
                if (closeMatch (Color.RED, touchColor, tolerance)) {
                    _mp.start();
                    toast ("Brawo " + _name + "!!");

                } else {
                }
                break;
        }
        return true;
    }

    public int getHotspotColor (int hotspotId, int x, int y) {
        ImageView img = (ImageView) findViewById (hotspotId);
        img.setDrawingCacheEnabled(true);
        Bitmap hotspots = Bitmap.createBitmap(img.getDrawingCache());
        img.setDrawingCacheEnabled(false);
        return hotspots.getPixel(x, y);
    }

    public boolean closeMatch (int color1, int color2, int tolerance) {
        if (Math.abs (Color.red (color1) - Color.red (color2)) > tolerance )
            return false;
        if (Math.abs (Color.green (color1) - Color.green (color2)) > tolerance )
            return false;
        if (Math.abs (Color.blue (color1) - Color.blue (color2)) > tolerance )
            return false;
        return true;
    }

    public void toast (String msg)
    {
        Toast.makeText (getApplicationContext(), msg, Toast.LENGTH_LONG).show ();
    }
}
