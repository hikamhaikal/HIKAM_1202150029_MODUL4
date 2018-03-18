package id.simplify.hikam_1202150029_modul4;


import android.app.ProgressDialog;
import android.os.AsyncTask;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.ListView;
import android.widget.ProgressBar;

import java.util.ArrayList;

public class ListMahasiswa extends AppCompatActivity {
    private ListView mListView;
    private ProgressBar mProgressBar;
    private String [] mUsers= {
            "Hikam","Haikal","Alex","Ketua","Teater",
            "Titik"};
    //Memasukkan List Nama yang akan ditampilkan

    private AddItemToListView mAddItemToListView;
    private Button mStartAsyncTask;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_list_mahasiswa);
        //Pendefinisian Atribut
        mProgressBar = (ProgressBar) findViewById(R.id.progressBar);
        mListView = (ListView) findViewById(R.id.ListViews);
        mStartAsyncTask = (Button) findViewById(R.id.button3);
        //Memasukkan listview dengan nilai kosong
        mListView.setAdapter(new ArrayAdapter<String>(this,android.R.layout.simple_list_item_1,new ArrayList<String>()));

        //Inisialisasi semua komponen yang akan digunakan dan melakukan setAdapter terhadap ListView dengan menggunakan Array
        mStartAsyncTask.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                mAddItemToListView = new AddItemToListView();
                mAddItemToListView.execute();
                //Menjalankan asynctask ketika Button diklik
            }
        });
    }
    public class AddItemToListView extends AsyncTask<Void, String, Void>{

        private ArrayAdapter<String> mAdapter;
        private int counter=1;
        ProgressDialog mProgressDialog = new ProgressDialog(ListMahasiswa.this);
        //Deklarasi semua komponen yang akan digunakan

        @Override
        protected void onPreExecute() {
            mAdapter = (ArrayAdapter<String>) mListView.getAdapter();

            //Untuk dialog progress ditampilkan ketika diklik, namun belum berjalan proses update
            mProgressDialog.setProgressStyle(ProgressDialog.STYLE_SPINNER);
            mProgressDialog.setTitle("Mencari Data");
            mProgressDialog.setMessage("Mohon Tunggu");
            mProgressDialog.setCancelable(false);
            mProgressDialog.setProgress(0);
            mProgressDialog.show();
        }

        @Override
        protected Void doInBackground(Void... voids) {
            //Menjalankan sleep
            for (String item : mUsers){
                publishProgress(item);
                try {
                    Thread.sleep(100);
                }catch (Exception e){
                    e.printStackTrace();
                }
                if(isCancelled()){
                    mAddItemToListView.cancel(true);
                }
            }
            return null;
        }
        @Override
        protected void onProgressUpdate(String... values) {
            //Menjalankan progress bar sesuai dengan status unggah data
            mAdapter.add(values[0]);
            Integer current_status = (int) ((counter/(float)mUsers.length)*100);
            mProgressBar.setProgress(current_status);
            mProgressDialog.setProgress(current_status);
            mProgressDialog.setMessage(String.valueOf(current_status+"%"));
            counter++;
        }


        @Override
        protected void onPostExecute(Void aVoid) {
            //Menghilangkan Progress bar dan dialog, dan mensetting listview sesuai dengan array yang disediakan.
            mProgressBar.setVisibility(View.GONE);
            mProgressDialog.dismiss();
            mListView.setAdapter(new ArrayAdapter<String>(ListMahasiswa.this,android.R.layout.simple_list_item_1,mUsers));

        }
    }
}
