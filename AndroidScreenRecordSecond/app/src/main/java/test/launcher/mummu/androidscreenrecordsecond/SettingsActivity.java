package test.launcher.mummu.androidscreenrecordsecond;

import android.graphics.Color;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.text.Editable;
import android.text.TextWatcher;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.CheckBox;
import android.widget.CompoundButton;
import android.widget.EditText;
import android.widget.LinearLayout;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.SeekBar;
import android.widget.TextView;

import test.launcher.mummu.androidscreenrecordsecond.utils.RecordPreference;

/**
 * Created by muhammed on 19/7/16.
 */
public class SettingsActivity extends AppCompatActivity implements CompoundButton.OnCheckedChangeListener, RadioGroup.OnCheckedChangeListener, SeekBar.OnSeekBarChangeListener {
    private Toolbar mToolbar;
    private CheckBox mCheckBoxAudioEnable;
    private CheckBox mCheckBoxCameraEnable;
    private CheckBox mCheckBoxWatermarkEnable;
    private CheckBox mCheckBoxNotification;
    private EditText mEditTextWatermark;
    private RadioGroup mRadioGroupColor;
    private String colorName;
    private SeekBar mSeekBarTextSize;
    private TextView mTextViewPreviewTextView;
    private LinearLayout mSizeFixLayout;
    private String textSize;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        setContentView(R.layout.activity_settings);

        mToolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(mToolbar);
        getSupportActionBar().setDisplayShowTitleEnabled(false);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        getSupportActionBar().setHomeAsUpIndicator(R.drawable.ic_arrow_back_white_dp);


        mCheckBoxAudioEnable = (CheckBox) findViewById(R.id.checkBoxAudio);
        mCheckBoxCameraEnable = (CheckBox) findViewById(R.id.chesckBoxCamera);
        mCheckBoxWatermarkEnable = (CheckBox) findViewById(R.id.checkBoxWaterMark);
        mEditTextWatermark = (EditText) findViewById(R.id.editTextWatermark);
        mRadioGroupColor = (RadioGroup) findViewById(R.id.colorRadioGroup);
        mSeekBarTextSize = (SeekBar) findViewById(R.id.textSizeSeekBar);
        mTextViewPreviewTextView = (TextView) findViewById(R.id.previewTextView);
        mSizeFixLayout = (LinearLayout) findViewById(R.id.sizeFixLayout);
        mCheckBoxNotification = (CheckBox) findViewById(R.id.checkBoxNotification);


        setDefaultValues();


        // enable listeners

//        mCheckBoxAudioEnable.setOnCheckedChangeListener(this);
//        mCheckBoxCameraEnable.setOnCheckedChangeListener(this);


        super.onCreate(savedInstanceState);
    }

    private void setDefaultValues() {

        mSeekBarTextSize.setMax(20);
        mSeekBarTextSize.setOnSeekBarChangeListener(this);
        mRadioGroupColor.setOnCheckedChangeListener(this);
        mCheckBoxWatermarkEnable.setOnCheckedChangeListener(this);
        mCheckBoxAudioEnable.setChecked(RecordPreference.getBooleanData(this, RecordPreference.AUDIO_RECORD_ENNABLED));
        mCheckBoxWatermarkEnable.setChecked(RecordPreference.getBooleanData(this, RecordPreference.WATER_MARK_ENABLE));
        mCheckBoxCameraEnable.setChecked(RecordPreference.getBooleanData(this, RecordPreference.CAMERA_ENABLED));
        mCheckBoxNotification.setChecked(RecordPreference.getBooleanData(this, RecordPreference.NOTIFICATION_ENABLED));
        mEditTextWatermark.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {

            }

            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {
                mTextViewPreviewTextView.setText(s.toString());
            }

            @Override
            public void afterTextChanged(Editable s) {

            }
        });
        if (mCheckBoxWatermarkEnable.isChecked()) {
            ((RadioButton) findViewById(RecordPreference.getIntData(this, RecordPreference.COLOR_ID))).setChecked(true);
            mEditTextWatermark.setText(RecordPreference.getStringData(this, RecordPreference.WATER_MARK_TEXT));
        }


    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        return super.onCreateOptionsMenu(menu);
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        switch (item.getItemId()) {
            case android.R.id.home:
                finish();
                break;
        }
        return super.onOptionsItemSelected(item);
    }

    @Override
    public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {
        if (isChecked) {
            mEditTextWatermark.setVisibility(View.VISIBLE);
            mRadioGroupColor.setVisibility(View.VISIBLE);
            mSizeFixLayout.setVisibility(View.VISIBLE);
            mTextViewPreviewTextView.setVisibility(View.VISIBLE);
        } else {
            mEditTextWatermark.setVisibility(View.GONE);
            mRadioGroupColor.setVisibility(View.GONE);
            mSizeFixLayout.setVisibility(View.GONE);
            mTextViewPreviewTextView.setVisibility(View.GONE);
        }
    }

    @Override
    public void onCheckedChanged(RadioGroup group, int checkedId) {
        setColorValue(group.getCheckedRadioButtonId());
    }

    private void setColorValue(int id) {
        switch (id) {
            case R.id.radioBlue:
                setColorName("#33B4E4");
                break;
            case R.id.radioRed:
                setColorName("#CB0001");
                break;
            case R.id.radioPink:
                setColorName("#946CD1");
                break;
            case R.id.radioGreen:
                setColorName("#85C420");
                break;
            default:
                setColorName("#FDBA33");
                break;
        }
        mEditTextWatermark.setTextColor(Color.parseColor(getColorName()));
        mTextViewPreviewTextView.setTextColor(Color.parseColor(getColorName()));
    }

    @Override
    protected void onDestroy() {
        saveData();
        super.onDestroy();

    }

    private void saveData() {
        RecordPreference.insertBooleanData(this, RecordPreference.AUDIO_RECORD_ENNABLED, mCheckBoxAudioEnable.isChecked());
        RecordPreference.insertBooleanData(this, RecordPreference.CAMERA_ENABLED, mCheckBoxCameraEnable.isChecked());
        RecordPreference.insertBooleanData(this, RecordPreference.WATER_MARK_ENABLE, mCheckBoxWatermarkEnable.isChecked());
        RecordPreference.insertBooleanData(this, RecordPreference.NOTIFICATION_ENABLED, mCheckBoxNotification.isChecked());

        if (mCheckBoxWatermarkEnable.isChecked()) {
            RecordPreference.insertIntData(this, RecordPreference.COLOR_ID, mRadioGroupColor.getCheckedRadioButtonId());
            RecordPreference.insertStringData(this, RecordPreference.WATER_MARK_TEXT, mEditTextWatermark.getText().toString());
            RecordPreference.insertStringData(this, RecordPreference.WATER_MARK_TEXT_COLOR, getColorName());
            RecordPreference.insertStringData(this, RecordPreference.WATER_MARK_TEXT_SIZE, getTextSize());
        }
    }

    @Override
    protected void onPause() {
        saveData();
        super.onPause();
    }

    public String getColorName() {
        return colorName;
    }

    public void setColorName(String colorName) {
        this.colorName = colorName;
    }

    @Override
    public void onProgressChanged(SeekBar seekBar, int progress, boolean fromUser) {
        mTextViewPreviewTextView.setTextSize(progress + 12);
        textSize = (progress + 12) + "";
    }

    @Override
    public void onStartTrackingTouch(SeekBar seekBar) {

    }

    @Override
    public void onStopTrackingTouch(SeekBar seekBar) {

    }

    public String getTextSize() {
        return textSize;
    }

    public void setTextSize(String textSize) {
        this.textSize = textSize;
    }
}
