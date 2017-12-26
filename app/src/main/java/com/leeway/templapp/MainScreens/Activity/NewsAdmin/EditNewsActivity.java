package com.leeway.templapp.MainScreens.Activity.NewsAdmin;

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
import android.widget.Button;
import android.widget.EditText;
import android.widget.GridView;
import android.widget.TextView;
import android.widget.Toast;

import com.leeway.templapp.Adapter.EditNewsAdapterImage;
import com.leeway.templapp.Bus.BusFactory;
import com.leeway.templapp.Bus.Events.DemoEdit;
import com.leeway.templapp.Bus.Events.RefreshNewsListEvent;
import com.leeway.templapp.Connection.HttpRequestDeleteImageNews;
import com.leeway.templapp.Connection.HttpRequestUpdateNews;
import com.leeway.templapp.Connection.Model.News.Model_Two.Model_Two.ImageList;
import com.leeway.templapp.Connection.Model.News.Model_Two.Model_Two.UpdateNewsBody;
import com.leeway.templapp.Interfaces.OnHttpResponseDeleteImageNews;
import com.leeway.templapp.Interfaces.OnHttpResponseUpdateNews;
import com.leeway.templapp.MainScreens.Activity.BaseActivity;
import com.leeway.templapp.R;
import com.squareup.otto.Subscribe;

import java.io.ByteArrayOutputStream;
import java.io.FileNotFoundException;
import java.io.InputStream;
import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;

/**
 * Created by work on 7/27/2017.
 */

public class EditNewsActivity extends BaseActivity implements OnHttpResponseDeleteImageNews, OnHttpResponseUpdateNews {

    private String newsId = "";
    private int PICK_IMAGE_MULTIPLE = 1;
    private String imageEncoded;
    private List<String> imagesEncodedList;
    private ArrayList<String> imgListUrl;
    private ArrayList<String> imgListId;
    private ArrayList<Uri> mArrayUri = new ArrayList<Uri>();

    @BindView(R.id.btnImage)
    TextView btnImage;

    @BindView(R.id.gridView1)
    GridView gridView;

    @BindView(R.id.edt_newsDescription)
    EditText edt_newsDescription;

    @BindView(R.id.edt_newsTitle)
    EditText edt_newsTitle;

    private EditNewsAdapterImage mAdapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_update_news);
        ButterKnife.bind(this);

        setProgressBar();
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);
        // add back arrow to toolbar
        if (getSupportActionBar() != null) {
            getSupportActionBar().setDisplayHomeAsUpEnabled(true);
            getSupportActionBar().setDisplayShowHomeEnabled(true);
            getSupportActionBar().setTitle("Edit News");
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

        Intent i = getIntent();
        imgListUrl = i.getStringArrayListExtra("imgListUrl");
        imgListId = i.getStringArrayListExtra("imgListId");
        newsId = i.getStringExtra("newsId");
        String description = i.getStringExtra("description");
        String title = i.getStringExtra("title");
        edt_newsDescription.setText(description);
        edt_newsTitle.setText(title);
        if (imgListUrl.size() != 0) {
            gridView.setVisibility(View.VISIBLE);
        }
        mAdapter = new EditNewsAdapterImage(this, imgListUrl, imgListId);
        gridView.setAdapter(mAdapter);


    }

    @Subscribe
    public void DeleteImageEvent(DemoEdit event) {
        String imageId = event.getIdImage();

        showProgress("Please wait while processing..");
        HttpRequestDeleteImageNews httpRequestDeleteImageNews = new HttpRequestDeleteImageNews(this);
        httpRequestDeleteImageNews.DeleteImageNews(Integer.parseInt(imageId));
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // handle arrow click here
        if (item.getItemId() == android.R.id.home) {
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
                    UpdateNewsBody updateNewsBody = new UpdateNewsBody();
                    updateNewsBody.setNewsId(newsId);
                    updateNewsBody.setNewsTitle(edt_newsTitle.getText().toString());
                    updateNewsBody.setNewsDescription(edt_newsDescription.getText().toString());

                    List<ImageList> mImageList = new ArrayList<ImageList>();
                    for (int i = 0; i < imgListId.size(); i++) {
                        ImageList imageList = new ImageList();
                        if (imgListId.get(i).equals("local")) {

                            ContentResolver cr = this.getContentResolver();
                            String msa = queryName(cr, Uri.parse(imgListUrl.get(i)));
                            final InputStream imageStream = getContentResolver().openInputStream(Uri.parse(imgListUrl.get(i)));
                            final Bitmap selectedImage = BitmapFactory.decodeStream(imageStream);
                            String encodedImage = encodeImage(selectedImage);

                            imageList.setImagedata(encodedImage);
                            imageList.setFileName(msa);
                            mImageList.add(imageList);
                        }
                    }
                    updateNewsBody.setImageList(mImageList);

                    HttpRequestUpdateNews httpRequestUpdateNews = new HttpRequestUpdateNews(this);
                    httpRequestUpdateNews.UpdateNews(updateNewsBody);
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
        bm.compress(Bitmap.CompressFormat.JPEG, 100, baos);
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
                    imgListUrl.add(String.valueOf(mImageUri));
                    imgListId.add("local");
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
                            imgListUrl.add(String.valueOf(uri));
                            imgListId.add("local");
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


                mAdapter = new EditNewsAdapterImage(this, imgListUrl, imgListId);
                gridView.setAdapter(mAdapter);
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
    public void OnSuccessDeleteImageNews(boolean stautus, String message) {
        cancelProgress();
        showAlertSucces(message);
        BusFactory.getBus().post(new RefreshNewsListEvent());

    }

    @Override
    public void OnFailedDeleteImageNews(String errorMessage) {
        cancelProgress();
        showAlert(errorMessage);
    }

    @Override
    public void OnSuccessUpdateNews(boolean stautus, String message) {
        cancelProgress();
        showAlertSucces(message);
        BusFactory.getBus().post(new RefreshNewsListEvent());
        finish();
    }

    @Override
    public void OnFailedUpdateNews(String errorMessage) {
        cancelProgress();
        showAlert(errorMessage);
    }
}
