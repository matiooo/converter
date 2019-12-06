package ir.hhadanooo.converter;

import androidx.appcompat.app.AppCompatActivity;

import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.os.Bundle;
import android.os.Environment;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;

import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.OutputStream;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

public class converter_test extends AppCompatActivity {
    EditText et_code;
    Button btn_add,btn_check;
    ImageView img_code;
    List<String> list_code;
    String name;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_converter_text);
        name = "matiooo_"+get_time()+".jpg";
        init();
        btn_add.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                String text_et_code = et_code.getText().toString().trim();
                list_code.add(text_et_code);
            }
        });
        btn_check.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                try {
                    set_list_file(name);
                    set_imgage_file(name);
                } catch (IOException e) {
                    e.printStackTrace();
                }


            }
        });




    }
    public void set_imgage_file(String dir)
    {
        Bitmap bmp = BitmapFactory.decodeFile(Environment.getExternalStorageDirectory() + "/raminhackeram/" + dir);
        img_code.setImageBitmap(bmp);
    }
    public void set_list_file(String dir) throws IOException {
        File file = new File(Environment.getExternalStorageDirectory().getAbsolutePath() + "/raminhackeram/" + dir);
        OutputStream outputStream = new FileOutputStream(file);
        for(int i = 0;i<list_code.size();i++)
        {
            outputStream.write(Integer.valueOf(list_code.get(i)));
        }
        outputStream.close();
    }
    void init()
    {
        et_code = findViewById(R.id.et_code);
        btn_add = findViewById(R.id.btn_add);
        btn_check = findViewById(R.id.btn_check);
        img_code = findViewById(R.id.img_code);
        list_code = new ArrayList<>();
    }
    public String get_time()
    {
        SimpleDateFormat formatter = new SimpleDateFormat("HH_mm_ss");
        Date date = new Date();
        return formatter.format(date);
    }
}
