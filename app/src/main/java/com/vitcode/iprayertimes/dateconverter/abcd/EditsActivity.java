package com.vitcode.iprayertimes.dateconverter.abcd;

import android.annotation.SuppressLint;
import android.content.Intent;
import android.graphics.Bitmap;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.drawable.Drawable;
import android.net.Uri;
import android.os.Bundle;
import android.provider.MediaStore;
import android.view.MotionEvent;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;
import androidx.cardview.widget.CardView;
import androidx.fragment.app.Fragment;

import com.google.android.material.bottomnavigation.BottomNavigationView;
import com.vitcode.iprayertimes.R;

import java.io.ByteArrayOutputStream;

public class EditsActivity extends AppCompatActivity implements TextFragment.TextClickListener {
    // Your activity code


    private ImageView Image0001,ShareImge,bt_back;

    private TextView txtOne;
    CardView Cradviewww;
    private BottomNavigationView bottomNavigationView;

    @SuppressLint({"MissingInflatedId", "ClickableViewAccessibility"})
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_edits);


        ShareImge = findViewById(R.id.ShareImge);
        bt_back = findViewById(R.id.bt_back);
        Cradviewww = findViewById(R.id.Cradviewww);

        bottomNavigationView = findViewById(R.id.bottom_navigation);
        txtOne = findViewById(R.id.txtOne);
        bottomNavigationView.setOnNavigationItemSelectedListener(item -> {
            switch (item.getItemId()) {
                case R.id.navigation_text:
                    loadFragment(new TextFragment());
                    return true;
                case R.id.navigation_images:
                    loadFragment(new ImageFragment());
                    return true;
                default:
                    return false;
            }
        });


        bt_back.setOnClickListener(v -> {
            onBackPressed();
        });

        // Load initial fragment
        loadFragment(new TextFragment());

        TextFragment textFragment = new TextFragment();
        getSupportFragmentManager().beginTransaction()
                .replace(R.id.fragment_container, textFragment)
                .commit();

        Image0001 = findViewById(R.id.Image0001);

        ShareImge.setOnClickListener(v -> {
            // CardView se image aur text ko extract karein
            Bitmap cardImage = getBitmapFromView(Cradviewww);
            String cardText = txtOne.getText().toString();

            // Share karne ke liye Intent banayein
            Intent shareIntent = new Intent();
            shareIntent.setAction(Intent.ACTION_SEND);
            shareIntent.putExtra(Intent.EXTRA_TEXT, cardText);

            // Image ko shareIntent mein add karein
            ByteArrayOutputStream bytes = new ByteArrayOutputStream();
            cardImage.compress(Bitmap.CompressFormat.JPEG, 100, bytes);
            String path = MediaStore.Images.Media.insertImage(getContentResolver(), cardImage, "Card Image", null);
            Uri imageUri = Uri.parse(path);
            shareIntent.putExtra(Intent.EXTRA_STREAM, imageUri);
            shareIntent.setType("image/jpeg");

            // Intent ko start karein
            startActivity(Intent.createChooser(shareIntent, "Share Card via"));
        });

        txtOne.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                // Make txtOne editable when clicked
                txtOne.setFocusableInTouchMode(true);
                txtOne.requestFocus();
            }
        });
        txtOne.setOnTouchListener(new View.OnTouchListener() {
            @Override
            public boolean onTouch(View v, MotionEvent event) {
                switch (event.getAction() & MotionEvent.ACTION_MASK) {
                    case MotionEvent.ACTION_DOWN:
                        // Save initial touch positions
                        initialX = event.getX();
                        initialY = event.getY();

                        // Save original position of TextView
                        originalX = txtOne.getX();
                        originalY = txtOne.getY();
                        break;
                    case MotionEvent.ACTION_POINTER_DOWN:
                        // Reset initialDistance when second finger touches the screen
                        initialDistance = getDistance(event);
                        break;
                    case MotionEvent.ACTION_MOVE:
                        if (event.getPointerCount() == 1) {
                            // Calculate movement offset
                            float offsetX = event.getX() - initialX;
                            float offsetY = event.getY() - initialY;

                            // Move txtOne based on movement offset
                            float newX = originalX + offsetX;
                            float newY = originalY + offsetY;

                            // Constrain movement within the frame
                            newX = Math.max(0, Math.min(newX, ((View) v.getParent()).getWidth() - txtOne.getWidth()));
                            newY = Math.max(0, Math.min(newY, ((View) v.getParent()).getHeight() - txtOne.getHeight()));

                            txtOne.setX(newX);
                            txtOne.setY(newY);
                        } else if (event.getPointerCount() == 2) {
                            // Calculate distance between fingers for pinch gesture
                            float distance = getDistance(event);

                            // Calculate scale factor
                            float scaleFactor = distance / initialDistance;

                            // Limit the scale factor to prevent 'Infinity' or very large values
                            float maxScaleFactor = 3.0f; // Adjust as needed
                            scaleFactor = Math.min(scaleFactor, maxScaleFactor);

                            // Calculate new dimensions for txtOne
                            float newWidth = txtOne.getWidth() * scaleFactor;
                            float newHeight = txtOne.getHeight() * scaleFactor;

                            // Constrain dimensions within the frame
                            newWidth = Math.max(300, Math.min(newWidth, 300 * maxScaleFactor));
                            newHeight = Math.max(220, Math.min(newHeight, 220 * maxScaleFactor));

                            // Adjust txtOne size based on pinch gesture
                            txtOne.getLayoutParams().width = (int) newWidth;
                            txtOne.getLayoutParams().height = (int) newHeight;
                            txtOne.requestLayout();
                        }
                        break;
                    case MotionEvent.ACTION_POINTER_UP:
                        // Reset initialDistance when fingers leave the screen
                        initialDistance = 0f;
                        break;
                }
                return true;
            }
        });



    }
    private float initialX, initialY;
    private float originalX, originalY;
    private float initialDistance = 0f;

    private float getDistance(MotionEvent event) {
        float x = event.getX(0) - event.getX(1);
        float y = event.getY(0) - event.getY(1);
        return (float) Math.sqrt(x * x + y * y);
    }

    private Bitmap getBitmapFromView(View view) {
        Bitmap bitmap = Bitmap.createBitmap(view.getWidth(), view.getHeight(), Bitmap.Config.ARGB_8888);
        Canvas canvas = new Canvas(bitmap);
        Drawable bgDrawable = view.getBackground();
        if (bgDrawable != null) {
            bgDrawable.draw(canvas);
        } else {
            canvas.drawColor(Color.WHITE);
        }
        view.draw(canvas);
        return bitmap;
    }

    private void loadFragment(Fragment fragment) {
        getSupportFragmentManager().beginTransaction()
                .replace(R.id.fragment_container, fragment)
                .commit();
    }


    public void setImage(int imageResourceId) {
        Image0001.setImageResource(imageResourceId);
    }

    @Override
    public void onTextClicked(String text) {
        TextView txtOne = findViewById(R.id.txtOne);
        txtOne.setText(text);
    }

}