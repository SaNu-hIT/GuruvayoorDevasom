package com.leeway.templapp;

import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v4.app.ActivityCompat;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;

/**
 * Created by Umang Chamaria
 */
public abstract class BaseActivity extends AppCompatActivity {
//  MoEHelper mHelper=null;
protected static final int REQUEST_STORAGE_READ_ACCESS_PERMISSION = 101;
  protected static final int REQUEST_STORAGE_WRITE_ACCESS_PERMISSION = 102;

  private AlertDialog mAlertDialog;
  @Override protected void onCreate(Bundle savedInstanceState) {
    super.onCreate(savedInstanceState);

  }

  @Override protected void onStart() {
    super.onStart();
  }

  @Override protected void onStop() {
    super.onStop();
    if (mAlertDialog != null && mAlertDialog.isShowing()) {
      mAlertDialog.dismiss();
    }
  }

  @Override protected void onResume() {
    super.onResume();
  }

  @Override protected void onSaveInstanceState(Bundle outState) {
    super.onSaveInstanceState(outState);
  }

  @Override protected void onRestoreInstanceState(Bundle savedInstanceState) {
    super.onRestoreInstanceState(savedInstanceState);
  }

  @Override protected void onNewIntent(Intent intent) {
    super.onNewIntent(intent);
  }

  protected void requestPermission(final String permission, String rationale, final int requestCode) {
    if (ActivityCompat.shouldShowRequestPermissionRationale(this, permission)) {
      showAlertDialog(getString(R.string.permission_title_rationale), rationale,
              new DialogInterface.OnClickListener() {
                @Override
                public void onClick(DialogInterface dialog, int which) {
                  ActivityCompat.requestPermissions(BaseActivity.this,
                          new String[]{permission}, requestCode);
                }
              }, getString(R.string.label_ok), null, getString(R.string.label_cancel));
    } else {
      ActivityCompat.requestPermissions(this, new String[]{permission}, requestCode);
    }
  }
  protected void showAlertDialog(@Nullable String title, @Nullable String message,
                                 @Nullable DialogInterface.OnClickListener onPositiveButtonClickListener,
                                 @NonNull String positiveText,
                                 @Nullable DialogInterface.OnClickListener onNegativeButtonClickListener,
                                 @NonNull String negativeText) {
    AlertDialog.Builder builder = new AlertDialog.Builder(this);
    builder.setTitle(title);
    builder.setMessage(message);
    builder.setPositiveButton(positiveText, onPositiveButtonClickListener);
    builder.setNegativeButton(negativeText, onNegativeButtonClickListener);

    try { mAlertDialog = builder.show();
    }catch (Exception e){

    }

  }

}