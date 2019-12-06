package ir.hhadanooo.converter.Hadanooo;

import androidx.annotation.RequiresApi;
import androidx.appcompat.app.AppCompatActivity;

import android.Manifest;
import android.content.pm.PackageManager;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.Color;
import android.graphics.Paint;
import android.media.Image;
import android.os.Build;
import android.os.Bundle;
import android.os.Environment;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.Toast;

import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;

import ir.hhadanooo.converter.R;

public class Activity_hadanooo extends AppCompatActivity {

    Button btn_submit;
    ImageView img;

    @RequiresApi(api = Build.VERSION_CODES.M)
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_hadanooo);


        btn_submit = findViewById(R.id.btn_submit);
        img = findViewById(R.id.img);

        if (checkSelfPermission(Manifest.permission.WRITE_EXTERNAL_STORAGE) !=
                PackageManager.PERMISSION_GRANTED){
            requestPermissions(new String[] {Manifest.permission.WRITE_EXTERNAL_STORAGE} , 123);
        }


        final Bitmap bitmap = BitmapFactory.decodeResource(getResources() , R.drawable.whait , null);
        final Bitmap myBitmap = bitmap.copy(Bitmap.Config.ARGB_8888, true);






        btn_submit.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                //method 1
                int[] pixels = new int[myBitmap.getHeight()*myBitmap.getWidth()];
                myBitmap.getPixels(pixels, 0, myBitmap.getWidth(), 0, 0, myBitmap.getWidth(), myBitmap.getHeight());
                for (int i=0; i<myBitmap.getWidth()*20; i++)
                    pixels[i] = Color.BLUE;
                myBitmap.setPixels(pixels, 0, myBitmap.getWidth(), 0, 0, myBitmap.getWidth(), myBitmap.getHeight());


                //method 2

                /*myBitmap.setPixel(0 , 100 , Color.argb(100,250,0,0));
                myBitmap.setPixel(1 , 100 , Color.argb(100,250,0,0));
                myBitmap.setPixel(2 , 100 , Color.argb(100,250,0,0));
                myBitmap.setPixel(3 , 100 , Color.argb(100,250,0,0));
                myBitmap.setPixel(4 , 100 , Color.argb(100,250,0,0));
                myBitmap.setPixel(5 , 100 , Color.argb(100,250,0,0));
                myBitmap.setPixel(6 , 100 , Color.argb(100,250,0,0));
                myBitmap.setPixel(7 , 100 , Color.argb(100,250,0,0));
                myBitmap.setPixel(8 , 100 , Color.argb(100,250,0,0));
                myBitmap.setPixel(9 , 100 , Color.argb(100,50,50,50));
                myBitmap.setPixel(10 , 100 , Color.argb(50,50,50,50));
                myBitmap.setPixel(11 , 100 , Color.argb(50,50,50,50));
                myBitmap.setPixel(12 , 100 , Color.argb(50,50,50,50));*/


                Toast.makeText(Activity_hadanooo.this, ""+myBitmap.isMutable(), Toast.LENGTH_SHORT).show();
                img.setImageBitmap(myBitmap);

                String file_path = Environment.getExternalStorageDirectory().getAbsolutePath() +
                        "/raminhacker";
                File dir = new File(file_path);
                if(!dir.exists())
                    dir.mkdirs();
                File file = new File(dir, "hhdanooo"+ ".png");
                FileOutputStream fOut = null;
                try {
                    fOut = new FileOutputStream(file);
                    myBitmap.compress(Bitmap.CompressFormat.PNG, 100, fOut);
                    fOut.flush();
                    fOut.close();
                } catch (IOException e) {
                    e.printStackTrace();
                }


            }
        });

    }
}
