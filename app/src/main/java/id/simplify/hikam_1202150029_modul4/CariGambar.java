package id.simplify.hikam_1202150029_modul4;

import android.app.ProgressDialog;
import android.graphics.Bitmap;
import android.os.AsyncTask;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.Toast;

public class CariGambar extends AppCompatActivity {
    private EditText txcari;
    private Button bcari;
    private ImageView imej;
    private ProgressDialog progressDialog;
    private String toinput;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_cari_gambar);
        txcari = (EditText) findViewById(R.id.inputurl);
        bcari = (Button) findViewById(R.id.button4);
        imej = (ImageView) findViewById(R.id.imageView1);
        //Inisialisasi semua komponen yang akan digunakan
    }

    public void cariGambar(View view) {
        toinput = txcari.getText().toString();
        //memastikan bahwa input sudah dimasukkan/tidak kosong, sehingga asynctask baru bisa dijalankan
        if (toinput.isEmpty()) {
            Toast.makeText(this, "Masukkan URL gambar terlebih dahulu", Toast.LENGTH_LONG).show();
        } else {
            new TemukanGambar().execute(toinput);
        }
    }


    private class TemukanGambar extends AsyncTask<String, Void, Bitmap> {

        @Override
        protected void onPreExecute() {
            //Seharusnya method ini ada untuk menampilkan progress
            progressDialog = new ProgressDialog(CariGambar.this);
            progressDialog.setTitle("Mencari Gambar");
            progressDialog.setMessage("Sabar ya");
            progressDialog.setIndeterminate(false);
            progressDialog.show();
        }

        @Override
        protected Bitmap doInBackground(String... urls) {
            //Method ini ada untuk menemukan gambar di background
            String imageURL = urls[0];
            Bitmap bimage = null;
            try {

            } catch (Exception e) {

            }
            return bimage;
        }

        @Override
        protected void onPostExecute(Bitmap bitmap) {
            //Seharusnya method ini berguna, ya, bagi mengupdate gambar
            imej.setImageBitmap(bitmap);
            progressDialog.dismiss();
        }
    }
}
