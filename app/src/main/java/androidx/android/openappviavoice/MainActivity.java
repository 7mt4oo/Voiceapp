package androidx.android.openappviavoice;

import android.content.ActivityNotFoundException;
import android.content.Intent;
import android.os.Build;
import android.os.Bundle;
import android.provider.MediaStore;
import android.speech.RecognizerIntent;
import android.view.View;
import android.widget.ImageButton;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.RequiresApi;
import androidx.appcompat.app.AppCompatActivity;

import java.util.ArrayList;
import java.util.Locale;
import java.util.Objects;

public class MainActivity extends AppCompatActivity {

    private static final int REQ_CODE_SPEECH_INPUT = 100;
    private TextView mVoiceInputTv;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        mVoiceInputTv = findViewById(R.id.voiceInput);
        ImageButton mSpeakBtn = findViewById(R.id.btnSpeak);
        mSpeakBtn.setOnClickListener(new View.OnClickListener() {

            @Override
            public void onClick(View v) {
                startVoiceInput();
            }
        });
    }

    private void startVoiceInput() {
        try {
            Intent voice = new Intent(RecognizerIntent.ACTION_RECOGNIZE_SPEECH);
            voice.putExtra(RecognizerIntent.EXTRA_LANGUAGE_MODEL, RecognizerIntent.LANGUAGE_MODEL_FREE_FORM);
            voice.putExtra(RecognizerIntent.EXTRA_LANGUAGE, Locale.getDefault());
            voice.putExtra(RecognizerIntent.EXTRA_PROMPT, "Hello, How can I help you?");

            startActivityForResult(voice, REQ_CODE_SPEECH_INPUT);
        } catch (ActivityNotFoundException ignored) {

        }
    }

    @RequiresApi(api = Build.VERSION_CODES.KITKAT)
    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);

        if (requestCode == REQ_CODE_SPEECH_INPUT) {
            if (resultCode == RESULT_OK && null != data) {
                ArrayList<String> arrayList = data.getStringArrayListExtra(RecognizerIntent.EXTRA_RESULTS);
                switch (Objects.requireNonNull(arrayList).get(0)) {
                    case "open Facebook": {
                        Intent intent = getPackageManager().getLaunchIntentForPackage("com.facebook.katana");
                        if (intent == null) {
                            Toast.makeText(this, "The application is not installed", Toast.LENGTH_LONG).show();
                        } else {
                            startActivity(intent);
                            mVoiceInputTv.setText(arrayList.get(0));

                        }
                        break;

                    }
                    case "open WhatsApp": {
                        Intent intent = getPackageManager().getLaunchIntentForPackage("com.whatsapp");
                        if (intent == null) {
                            Toast.makeText(this, "The application is not installed", Toast.LENGTH_LONG).show();
                        } else {
                            startActivity(intent);
                            mVoiceInputTv.setText(arrayList.get(0));

                        }
                        break;

                    }
                    case "open Gmail": {
                        Intent intent = getPackageManager().getLaunchIntentForPackage("com.google.android.gm");
                        if (intent == null) {
                            Toast.makeText(this, "The application is not installed", Toast.LENGTH_LONG).show();
                        } else {
                            startActivity(intent);
                            mVoiceInputTv.setText(arrayList.get(0));

                        }
                        break;

                    }
                    case "open camera": {
                        Intent camera = new Intent(MediaStore.ACTION_IMAGE_CAPTURE);
                        startActivity(camera);
                        mVoiceInputTv.setText(arrayList.get(0));

                        break;
                    }
                    case "open map": {
                        Intent intent = getPackageManager().getLaunchIntentForPackage("com.google.android.apps.maps");
                        if (intent == null) {
                            Toast.makeText(this, "The application is not installed", Toast.LENGTH_LONG).show();
                        } else {
                            startActivity(intent);
                            mVoiceInputTv.setText(arrayList.get(0));


                        }
                        break;
                    }
                    case "open Messenger": {
                        Intent intent = getPackageManager().getLaunchIntentForPackage("com.facebook.orca");
                        if (intent == null) {
                            Toast.makeText(this, "The application is not installed", Toast.LENGTH_LONG).show();
                        } else {
                            startActivity(intent);
                            mVoiceInputTv.setText(arrayList.get(0));


                        }
                        break;
                    }
                    case "open Instagram": {
                        Intent intent = getPackageManager().getLaunchIntentForPackage("com.instagram.android");
                        if (intent == null) {
                            Toast.makeText(this, "The application is not installed", Toast.LENGTH_LONG).show();
                        } else {
                            startActivity(intent);
                            mVoiceInputTv.setText(arrayList.get(0));

                        }
                        break;
                    }
                    default:
                        Toast.makeText(MainActivity.this, "The application is not installed", Toast.LENGTH_LONG).show();
                        break;
                }
            }
        }
    }
}


