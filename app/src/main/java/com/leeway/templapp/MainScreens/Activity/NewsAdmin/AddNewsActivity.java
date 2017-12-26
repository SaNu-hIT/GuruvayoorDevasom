package com.leeway.templapp.MainScreens.Activity.NewsAdmin;

import android.app.Activity;
import android.content.ClipData;
import android.content.ContentResolver;
import android.content.Intent;
import android.database.Cursor;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.net.Uri;
import android.os.Build;
import android.os.Bundle;
import android.provider.MediaStore;
import android.provider.OpenableColumns;
import android.support.annotation.RequiresApi;
import android.support.v7.widget.Toolbar;
import android.util.Base64;
import android.view.MenuItem;
import android.view.MotionEvent;
import android.view.View;
import android.view.inputmethod.InputMethodManager;
import android.widget.AdapterView;
import android.widget.Button;
import android.widget.EditText;
import android.widget.GridView;
import android.widget.TextView;
import android.widget.Toast;

import com.leeway.templapp.Adapter.ImagesAdapter;
import com.leeway.templapp.Bus.BusFactory;
import com.leeway.templapp.Bus.Events.RefreshNewsUserSideEvent;
import com.leeway.templapp.Connection.HttpRequestAddNews;
import com.leeway.templapp.Connection.Model.News.Model_Two.Model_Two.ImageList;
import com.leeway.templapp.Connection.Model.News.Model_Two.Model_Two.NewsAddBody;
import com.leeway.templapp.Interfaces.OnHttpResponseAddNews;
import com.leeway.templapp.MainScreens.Activity.BaseActivity;
import com.leeway.templapp.R;

import java.io.ByteArrayOutputStream;
import java.io.FileNotFoundException;
import java.io.InputStream;
import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;

/**
 * Created by work on 7/20/2017.
 */

public class AddNewsActivity extends BaseActivity implements OnHttpResponseAddNews {

    private int PICK_IMAGE_MULTIPLE = 1;
    private String imageEncoded;
    private List<String> imagesEncodedList;
    private ArrayList<Uri> mArrayUri = new ArrayList<Uri>();
    private ImagesAdapter mAdapter;

    @BindView(R.id.gridView1)
    GridView gridView;

    @BindView(R.id.btnImage)
    TextView btnImage;

    @BindView(R.id.edt_newsDescription)
    EditText edt_newsDescription;

    @BindView(R.id.edt_newsTitle)
    EditText edt_newsTitle;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_add_news);
        ButterKnife.bind(this);
        setTitle("Add News");
        setProgressBar();
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);

        edt_newsDescription = (EditText) findViewById(R.id.edt_newsDescription);
        setSupportActionBar(toolbar);
        // add back arrow to toolbar
        if (getSupportActionBar() != null) {
            getSupportActionBar().setDisplayHomeAsUpEnabled(true);
            getSupportActionBar().setDisplayShowHomeEnabled(true);
            getSupportActionBar().setTitle("Add News");
        }
        gridView.setVisibility(View.INVISIBLE);
        btnImage.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent();
                intent.setType("image/*");
                intent.putExtra(Intent.EXTRA_ALLOW_MULTIPLE, true);
                intent.setAction(Intent.ACTION_GET_CONTENT);
                startActivityForResult(Intent.createChooser(intent, "Select Picture"), PICK_IMAGE_MULTIPLE);
            }
        });
        gridView.setOnTouchListener(new View.OnTouchListener() {
            @Override
            public boolean onTouch(View v, MotionEvent event) {
                v.getParent().requestDisallowInterceptTouchEvent(true);
                return false;
            }

        });

    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // handle arrow click here
        if (item.getItemId() == android.R.id.home) {
            InputMethodManager inputMethodManager = (InputMethodManager)getSystemService(Activity.INPUT_METHOD_SERVICE);
            inputMethodManager.hideSoftInputFromWindow(getCurrentFocus().getWindowToken(), 0);

            finish(); // close this activity and return to preview activity (if there is any)
        }

        return super.onOptionsItemSelected(item);
    }

    @OnClick(R.id.btn_cancel)
    public void FinishBack(Button button) {
        finish();
    }

    @OnClick(R.id.btn_addNews)
    public void AddNews(Button button) {
        if (isConnectedToNet(getBaseContext())) {
            if (edt_newsTitle.getText().toString().trim().equals("") || edt_newsDescription.getText().toString().trim().equals("")) {
                if (edt_newsTitle.getText().toString().trim().equals("")) {
                    edt_newsTitle.setError("Field can't be empty");
                }
                if (edt_newsDescription.getText().toString().trim().equals("")) {
                    edt_newsDescription.setError("Field can't be empty");
                }
            } else {
                try {
                    showProgress("Please wait while processing...");
                    NewsAddBody newsAddBody = new NewsAddBody();
                    newsAddBody.setNewsTitle(edt_newsTitle.getText().toString());
                    newsAddBody.setNewsDescription(edt_newsDescription.getText().toString());

                    List<ImageList> mImageList = new ArrayList<ImageList>();
                    for (int i = 0; i < mArrayUri.size(); i++) {
                        ImageList imageList = new ImageList();
                        ContentResolver cr = this.getContentResolver();
                        String msa = queryName(cr, mArrayUri.get(i));
                        final InputStream imageStream = getContentResolver().openInputStream(mArrayUri.get(i));
                        final Bitmap selectedImage = BitmapFactory.decodeStream(imageStream);
                        String encodedImage = encodeImage(selectedImage);

                        imageList.setImagedata(encodedImage);
                        imageList.setFileName(msa);
                        mImageList.add(imageList);
                    }
                    newsAddBody.setImageList(mImageList);

                    HttpRequestAddNews httpRequestAddNews = new HttpRequestAddNews(this);
                    httpRequestAddNews.AddNewsEvent(newsAddBody);
                } catch (FileNotFoundException e) {
                    e.printStackTrace();
                }
            }


        } else {
            //TODO : handle if no network connection is available
            showAlert("No internet connectivity");

        }
    }


    private String encodeImage(Bitmap bm) {
        ByteArrayOutputStream baos = new ByteArrayOutputStream();
        bm.compress(Bitmap.CompressFormat.JPEG, 30, baos);
        byte[] b = baos.toByteArray();
        String encImage = Base64.encodeToString(b, Base64.DEFAULT);

        return encImage;
    }

    @RequiresApi(api = Build.VERSION_CODES.JELLY_BEAN)
    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        try {
            // When an Image is picked
            if (requestCode == PICK_IMAGE_MULTIPLE && resultCode == RESULT_OK
                    && null != data) {
                // Get the Image from data

                String[] filePathColumn = {MediaStore.Images.Media.DATA};
                imagesEncodedList = new ArrayList<String>();
                if (data.getData() != null) {

                    Uri mImageUri = data.getData();


                    mArrayUri.add(mImageUri);
                    // Get the cursor
                    Cursor cursor = getContentResolver().query(mImageUri,
                            filePathColumn, null, null, null);
                    // Move to first row
                    cursor.moveToFirst();

                    int columnIndex = cursor.getColumnIndex(filePathColumn[0]);
                    imageEncoded = cursor.getString(columnIndex);
                    cursor.close();

                } else {
                    if (data.getClipData() != null) {
                        ClipData mClipData = data.getClipData();

                        for (int i = 0; i < mClipData.getItemCount(); i++) {

                            ClipData.Item item = mClipData.getItemAt(i);
                            Uri uri = item.getUri();

                            mArrayUri.add(uri);
                            // Get the cursor
                            Cursor cursor = getContentResolver().query(uri, filePathColumn, null, null, null);
                            // Move to first row
                            cursor.moveToFirst();

                            int columnIndex = cursor.getColumnIndex(filePathColumn[0]);
                            imageEncoded = cursor.getString(columnIndex);
                            imagesEncodedList.add(imageEncoded);
                            cursor.close();

                        }

                    }
                }
                // prepared arraylist and passed it to the Adapter class
                if (mArrayUri.size() != 0) {
                    gridView.setVisibility(View.VISIBLE);
                }
                mAdapter = new ImagesAdapter(this, mArrayUri);
                gridView.setAdapter(mAdapter);
                // Implement On Item click listener
                gridView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
                    @Override
                    public void onItemClick(AdapterView<?> arg0, View arg1, int position,
                                            long arg3) {
                        Toast.makeText(getApplicationContext(), mAdapter.getItem(position), Toast.LENGTH_SHORT).show();
                    }
                });

            } else {
                Toast.makeText(this, "You haven't picked Image",
                        Toast.LENGTH_LONG).show();
            }

        } catch (Exception e) {
            Toast.makeText(this, "Something went wrong", Toast.LENGTH_LONG)
                    .show();
        }

        super.onActivityResult(requestCode, resultCode, data);
    }



    private String queryName(ContentResolver resolver, Uri uri) {
        Cursor returnCursor =
                resolver.query(uri, null, null, null, null);
        assert returnCursor != null;
        int nameIndex = returnCursor.getColumnIndex(OpenableColumns.DISPLAY_NAME);
        returnCursor.moveToFirst();
        String name = returnCursor.getString(nameIndex);
        returnCursor.close();
        return name;
    }

    @Override
    public void OnSuccessAddNews(boolean stautus, String message) {
        cancelProgress();
        showAlertSucces(message);
        edt_newsTitle.setText("");
        edt_newsDescription.setText("");
        mArrayUri = new ArrayList<Uri>();
        gridView.setVisibility(View.INVISIBLE);
        BusFactory.getBus().post(new RefreshNewsUserSideEvent());
    }

    @Override
    public void OnFailedAddNews(String message) {
        cancelProgress();
        showAlert(message);
//        BusFactory.getBus().post(new RefreshNewsUserSideEvent());
    }
}
