package ir.hhadanooo.converter;

import androidx.appcompat.app.AppCompatActivity;

import android.Manifest;
import android.content.pm.PackageManager;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.os.Build;
import android.os.Bundle;
import android.os.Environment;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.Toast;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.OutputStream;
import java.io.OutputStreamWriter;
import java.text.SimpleDateFormat;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

public class MainActivity extends AppCompatActivity {

    EditText et_file,et_txt;
    Button btn_file,btn_txt;
    ImageView img_file,img_txt;
    String dir_file;
    String dir_txt;
    List<Integer> list_byte_file;
    List<String> list_byte_txt;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        init();


        btn_file.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                String text_et_file = et_file.getText().toString().trim();
                set_imgage_file(text_et_file);
                et_file.setText("");
                try {
                    get_list_file(text_et_file);
                    text_et_file = SubString(text_et_file);
                    writeToTxt(text_et_file);
                } catch (IOException e) {
                    e.printStackTrace();
                }


            }
        });
        btn_txt.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                String text_et_txt = et_txt.getText().toString().trim();
                try {
                    readTxtFile(text_et_txt);
                    text_et_txt = SubString(text_et_txt);
                    text_et_txt = text_et_txt + "_" +  get_time() + ".jpg";
                    set_list_file(text_et_txt);
                    set_imgage_txt(text_et_txt);

                } catch (IOException e) {
                    e.printStackTrace();
                }

            }
        });

    }
    public void init()
    {
        et_file = findViewById(R.id.et_file);
        et_txt = findViewById(R.id.et_txt);
        btn_file = findViewById(R.id.btn_file);
        btn_txt = findViewById(R.id.btn_txt);
        img_file = findViewById(R.id.img_file);
        img_txt = findViewById(R.id.img_txt);
        dir_file = Environment.getExternalStorageDirectory().getAbsolutePath() + "/";
        dir_txt = Environment.getExternalStorageDirectory().getAbsolutePath() + "/raminhackeram/";
        list_byte_file = new ArrayList<>();
        list_byte_txt = new ArrayList<>();
    }

    public void set_imgage_file(String dir)
    {
        Bitmap bmp = BitmapFactory.decodeFile(Environment.getExternalStorageDirectory() + "/" + dir);
        img_file.setImageBitmap(bmp);
    }
    public void set_imgage_txt(String dir)
    {
        Bitmap bmp = BitmapFactory.decodeFile(Environment.getExternalStorageDirectory() + "/raminhackeram/" + dir);
        img_txt.setImageBitmap(bmp);
    }

    public void get_list_file(String dir) throws IOException {
        File file = new File(Environment.getExternalStorageDirectory().getAbsolutePath() + "/" + dir);
        InputStream in = new FileInputStream(file);
        int byteRead;
        while ((byteRead = in.read()) != -1) {
            list_byte_file.add(byteRead);
        }
        in.close();
    }
    public void set_list_file(String dir) throws IOException {
        File file = new File(Environment.getExternalStorageDirectory().getAbsolutePath() + "/raminhackeram/" + dir);
        OutputStream outputStream = new FileOutputStream(file);
        for(int i = 0;i<list_byte_txt.size();i++)
        {
            outputStream.write(Integer.valueOf(list_byte_txt.get(i)));
        }
        outputStream.close();
    }

    public void writeToTxt(String dir) throws IOException {
        File fout = new File(Environment.getExternalStorageDirectory().getAbsolutePath() + "/raminhackeram/"+"matiooo_"+dir + ".txt");
        FileOutputStream fos = new FileOutputStream(fout);

        BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(fos));

        for (int i = 0; i < list_byte_file.size(); i++) {
            bw.write(String.valueOf(list_byte_file.get(i)));
            bw.newLine();
        }

        bw.close();
    }
    public void readTxtFile(String dir) throws IOException {
        FileInputStream fstream = new FileInputStream(Environment.getExternalStorageDirectory().getAbsoluteFile() + "/raminhackeram/" + dir);
        BufferedReader br = new BufferedReader(new InputStreamReader(fstream));

        String strLine;

        while ((strLine = br.readLine()) != null)   {
            list_byte_txt.add(strLine);
        }

        fstream.close();
    }

    public String SubString (String text)
    {
        return text.substring(0,text.indexOf('.'));
    }

    public String get_time()
    {
        SimpleDateFormat formatter = new SimpleDateFormat("HH_mm_ss");
        Date date = new Date();
        return formatter.format(date);
    }


    @Override
    protected void onResume() {
        super.onResume();
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.M) {
            if(checkSelfPermission(Manifest.permission.WRITE_EXTERNAL_STORAGE) != PackageManager.PERMISSION_GRANTED)
            {
                requestPermissions(new String[] {Manifest.permission.WRITE_EXTERNAL_STORAGE,Manifest.permission.READ_EXTERNAL_STORAGE},0);
            }
        }
    }
}
