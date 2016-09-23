package com.example.hp.waverrmerchant;

import android.app.Activity;
import android.app.AlertDialog;
import android.app.DatePickerDialog;
import android.app.TimePickerDialog;
import android.content.ActivityNotFoundException;
import android.content.DialogInterface;
import android.content.Intent;
import android.database.Cursor;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.net.Uri;
import android.os.Bundle;
import android.provider.MediaStore;
import android.util.Base64;
import android.view.View;
import android.widget.Button;
import android.widget.DatePicker;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.TimePicker;
import android.widget.Toast;

import com.squareup.picasso.Picasso;

import org.json.JSONArray;

import java.io.ByteArrayOutputStream;
import java.text.DecimalFormat;
import java.util.Calendar;

/**
 * Created by HP on 21-05-2015.
 */
public class AddDeal extends Activity implements View.OnClickListener {

    Button startTimePicker,endTimePicker,startDatePicker,endDatePicker,takepic,upload,selectPic;
    TextView startTime,endTime,startDate,endDate;
    ImageView pic;
    EditText dealText;
    Bitmap photo;
    String url="";
    Uri picUri;
    int starthour,startmin,endhour,endmin,startyear,startmonth,startdate,endyear,endmonth,enddate;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_adddeal);
        startTimePicker=(Button)findViewById(R.id.startTimePicker);
        endTimePicker=(Button)findViewById(R.id.endTimePicker);
        startDatePicker=(Button)findViewById(R.id.startDatePicker);
        endDatePicker=(Button)findViewById(R.id.endDatePicker);
        startTime=(TextView)findViewById(R.id.tv_startTime);
        endTime=(TextView)findViewById(R.id.tv_endTime);
        startDate=(TextView)findViewById(R.id.tv_startDate);
        endDate=(TextView)findViewById(R.id.tv_endDate);
        takepic=(Button)findViewById(R.id.takepic);
        pic=(ImageView)findViewById(R.id.pic);
        upload=(Button)findViewById(R.id.upload);
        dealText=(EditText)findViewById(R.id.dealText);
        selectPic=(Button)findViewById(R.id.selectpic);

        startTimePicker.setOnClickListener(this);
        endTimePicker.setOnClickListener(this);
        startDatePicker.setOnClickListener(this);
        endDatePicker.setOnClickListener(this);
        takepic.setOnClickListener(this);
        upload.setOnClickListener(this);
        selectPic.setOnClickListener(this);
    }

    @Override
    public void onClick(View v) {
        switch (v.getId())
        {
            case R.id.startTimePicker:{ Calendar mcurrentTime = Calendar.getInstance();
                int hour = mcurrentTime.get(Calendar.HOUR_OF_DAY);
                int minute = mcurrentTime.get(Calendar.MINUTE);
                TimePickerDialog mTimePicker;
                mTimePicker = new TimePickerDialog(this, new TimePickerDialog.OnTimeSetListener() {
                    @Override
                    public void onTimeSet(TimePicker timePicker, int selectedHour, int selectedMinute) {
                        startTime.setText(new DecimalFormat("00").format(selectedHour) + ":" + new DecimalFormat("00").format(selectedMinute)+":00");
                        starthour=selectedHour;
                        startmin=selectedMinute;
                    }
                }, hour, minute, true);//Yes 24 hour time
                mTimePicker.setTitle("Select Time");
                mTimePicker.show();
                break;}
            case R.id.selectpic:{

                Intent i = new Intent(AddDeal.this,ImageSelect.class);
                finish();
                startActivityForResult(i,100);
                break;
            }

            case R.id.endTimePicker:{ Calendar mcurrentTime = Calendar.getInstance();
                int hour = mcurrentTime.get(Calendar.HOUR_OF_DAY);
                int minute = mcurrentTime.get(Calendar.MINUTE);
                TimePickerDialog mTimePicker;
                mTimePicker = new TimePickerDialog(this, new TimePickerDialog.OnTimeSetListener() {
                    @Override
                    public void onTimeSet(TimePicker timePicker, int selectedHour, int selectedMinute) {
                        endTime.setText( new DecimalFormat("00").format(selectedHour) + ":" + new DecimalFormat("00").format(selectedMinute)+":00");
                        endhour=selectedHour;
                        endmin=selectedMinute;
                    }
                }, hour, minute, true);//Yes 24 hour time
                mTimePicker.setTitle("Select Time");
                mTimePicker.show();
                break; }
            case R.id.startDatePicker:{Calendar mcurrentDate = Calendar.getInstance();
                int mYear = mcurrentDate.get(Calendar.YEAR);
                int mMonth = mcurrentDate.get(Calendar.MONTH);
                int mDay = mcurrentDate.get(Calendar.DAY_OF_MONTH);

                DatePickerDialog mDatePicker;
                mDatePicker = new DatePickerDialog(this, new DatePickerDialog.OnDateSetListener() {
                    public void onDateSet(DatePicker datepicker, int selectedyear, int selectedmonth, int selectedday) {
                        // TODO Auto-generated method stub
                    /*      Your code   to get date and time    */
                        selectedmonth = selectedmonth + 1;
                        startDate.setText("" + selectedyear + "-" + selectedmonth + "-" + selectedday);
                        startyear=selectedyear;
                        startmonth=selectedmonth;
                        startdate=selectedday;
                    }
                }, mYear, mMonth, mDay);
                mDatePicker.setTitle("Select Date");
                mDatePicker.show();
                break;}
            case R.id.endDatePicker:{Calendar mcurrentDate = Calendar.getInstance();
                int mYear = mcurrentDate.get(Calendar.YEAR);
                int mMonth = mcurrentDate.get(Calendar.MONTH);
                int mDay = mcurrentDate.get(Calendar.DAY_OF_MONTH);

                DatePickerDialog mDatePicker;
                mDatePicker = new DatePickerDialog(this, new DatePickerDialog.OnDateSetListener() {
                    public void onDateSet(DatePicker datepicker, int selectedyear, int selectedmonth, int selectedday) {
                        // TODO Auto-generated method stub
                    /*      Your code   to get date and time    */
                        selectedmonth = selectedmonth + 1;
                        endDate.setText("" + selectedyear + "-" + selectedmonth + "-" + selectedday);
                        endyear=selectedyear;
                        endmonth=selectedmonth;
                        enddate=selectedday;
                    }
                }, mYear, mMonth, mDay);
                mDatePicker.setTitle("Select Date");
                mDatePicker.show();break;}
            case R.id.takepic:{Intent cameraIntent = new Intent(android.provider.MediaStore.ACTION_IMAGE_CAPTURE);
                startActivityForResult(cameraIntent, 1888); break;}
            case R.id.upload: {
                if(dealText.getText().toString().compareTo("")==0||startTime.getText().toString().compareTo("Start Time")==0||endTime.getText().toString().compareTo("End Time")==0
                        ||startDate.getText().toString().compareTo("Start Date")==0||endDate.getText().toString().compareTo("End Date")==0||photo==null){
                    new AlertDialog.Builder(this)
                            .setTitle("Empty Fields!")
                            .setMessage("You cannot leave any field empty. Please fill in all the details")
                            .setPositiveButton(android.R.string.yes, new DialogInterface.OnClickListener() {
                                public void onClick(DialogInterface dialog, int which) {
                                    // continue with delete
                                }
                            })

                            .show();
                }
                else if(endyear<startyear){
                    new AlertDialog.Builder(this)
                            .setTitle("Date Error!")
                            .setMessage("The End Date cannot be before the Start Date.")
                            .setPositiveButton(android.R.string.yes, new DialogInterface.OnClickListener() {
                                public void onClick(DialogInterface dialog, int which) {
                                    // continue with delete
                                }
                            })

                            .show();
                }
                else if((endyear==startyear)&&endmonth<startmonth){
                    new AlertDialog.Builder(this)
                            .setTitle("Date Error!")
                            .setMessage("The End Date cannot be before the Start Date.")
                            .setPositiveButton(android.R.string.yes, new DialogInterface.OnClickListener() {
                                public void onClick(DialogInterface dialog, int which) {
                                    // continue with delete
                                }
                            })

                            .show();
                }
                else if((endmonth==startmonth)&&enddate<startdate){
                    new AlertDialog.Builder(this)
                            .setTitle("Date Error!")
                            .setMessage("The End Date cannot be before the Start Date.")
                            .setPositiveButton(android.R.string.yes, new DialogInterface.OnClickListener() {
                                public void onClick(DialogInterface dialog, int which) {
                                    // continue with delete
                                }
                            })

                            .show();
                }
                else if(startyear==endyear&&startmonth==endmonth&&startdate==enddate){
                    if(endhour<starthour)
                    {
                        new AlertDialog.Builder(this)
                                .setTitle("Time Error!")
                                .setMessage("The End Time cannot be before the Start Time.")
                                .setPositiveButton(android.R.string.yes, new DialogInterface.OnClickListener() {
                                    public void onClick(DialogInterface dialog, int which) {
                                        // continue with delete
                                    }
                                })

                                .show();
                    }
                    else if(endhour==starthour){
                        if(endmin<startmin){
                            new AlertDialog.Builder(this)
                                    .setTitle("Time Error!")
                                    .setMessage("The End Time cannot be before the Start Time.")
                                    .setPositiveButton(android.R.string.yes, new DialogInterface.OnClickListener() {
                                        public void onClick(DialogInterface dialog, int which) {
                                            // continue with delete
                                        }
                                    })

                                    .show();
                        }

                    }
                    else{
                        ByteArrayOutputStream bao = new ByteArrayOutputStream();
                        photo.compress(Bitmap.CompressFormat.JPEG, 100, bao);

                        byte[] ba = bao.toByteArray();
                        String ba1 = Base64.encodeToString(ba, Base64.DEFAULT);
                        String[] things = {
                                "http://waverr.in/app/images/merchantadd.php",
                                "deal", dealText.getText().toString(),
                                "stime", startTime.getText().toString(),
                                "etime", endTime.getText().toString(),
                                "sdate", startDate.getText().toString(),
                                "edate", endDate.getText().toString(),
                                "base64", ba1,
                                "ImageName", System.currentTimeMillis() + ".jpg"

                        };

                        new JSONObtainer() {
                            protected void onPostExecute(JSONArray array) {
                                Toast.makeText(getBaseContext(), "Data Added", Toast.LENGTH_SHORT).show();
                            }
                        }.execute(things);

                    }

                }
else {
                    ByteArrayOutputStream bao = new ByteArrayOutputStream();

                    photo.compress(Bitmap.CompressFormat.JPEG, 100, bao);

                    byte[] ba = bao.toByteArray();
                    String ba1 = Base64.encodeToString(ba, Base64.DEFAULT);
                    String[] things = {
                            "http://waverr.in/app/images/merchantadd.php",
                            "deal", dealText.getText().toString(),
                            "stime", startTime.getText().toString(),
                            "etime", endTime.getText().toString(),
                            "sdate", startDate.getText().toString(),
                            "edate", endDate.getText().toString(),
                            "base64", ba1,
                            "ImageName", System.currentTimeMillis() + ".jpg"

                    };

                    new JSONObtainer() {
                        protected void onPostExecute(JSONArray array) {
                            Toast.makeText(getBaseContext(), "Data Added", Toast.LENGTH_SHORT).show();
                        }
                    }.execute(things);
                }
                break;}
        }

    }
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        if (requestCode == 1888 && resultCode == RESULT_OK) {

            picUri = data.getData();
            performCrop();
            photo = (Bitmap) data.getExtras().get("data");
            pic.setImageBitmap(photo);

        }
        else if (resultCode == RESULT_OK && requestCode == 2) {
            // get the returned data
            Bundle extras = data.getExtras();
            // get the cropped bitmap
            Bitmap thePic = extras.getParcelable("data");
            pic.setImageBitmap(thePic);
        }
    }
    @Override
    protected void onResume() {
        super.onResume();

        Bundle extras = getIntent().getExtras();
        if(extras!=null) {
            url = extras.getString("result");
            Picasso.with(this)
                    .load(url)
                    .into(pic);
        }
        if(photo!=null)
        {
            pic.setImageBitmap(photo);
        }

    }
    private void performCrop() {
        // take care of exceptions
        try {
            // call the standard crop action intent (the user device may not
            // support it)
            Intent cropIntent = new Intent("com.android.camera.action.CROP");
            // indicate image type and Uri
            cropIntent.setDataAndType(picUri, "image/*");
            // set crop properties
            cropIntent.putExtra("crop", "true");
            // indicate aspect of desired crop
            cropIntent.putExtra("aspectX", 1);
            cropIntent.putExtra("aspectY", 1);
            // indicate output X and Y
            cropIntent.putExtra("outputX", 256);
            cropIntent.putExtra("outputY", 256);
            // retrieve data on return
            cropIntent.putExtra("return-data", true);
            // start the activity - we handle returning in onActivityResult
            startActivityForResult(cropIntent, 2);
        }
        // respond to users whose devices do not support the crop action
        catch (ActivityNotFoundException anfe) {
            Toast toast = Toast
                    .makeText(this, "This device doesn't support the crop action!", Toast.LENGTH_SHORT);
            toast.show();
        }
    }

}
