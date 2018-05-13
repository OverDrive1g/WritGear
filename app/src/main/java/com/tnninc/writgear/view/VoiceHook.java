package com.tnninc.writgear.view;

import android.app.Activity;
import android.content.Intent;
import android.content.res.TypedArray;
import android.graphics.Color;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.util.Log;
import android.widget.Toast;

import com.pushtorefresh.storio3.sqlite.operations.put.PutResult;
import com.tnninc.writgear.R;
import com.tnninc.writgear.di.App;
import com.tnninc.writgear.model.Model;
import com.tnninc.writgear.model.database.entities.NoteDTO;

import javax.inject.Inject;

import io.reactivex.disposables.Disposable;
import io.reactivex.functions.Consumer;

public class VoiceHook extends Activity {

    @Inject
    protected Model model;

    @Inject
    public VoiceHook() {
        App.getAppComponent().inject(this);
    }

    private TypedArray colors;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        int arrayId = getApplication().getResources().getIdentifier("mdcolor_400", "array",
                getApplication().getPackageName());

        if (arrayId != 0) {
            colors = getApplication().getResources().obtainTypedArray(arrayId);
        }

        Intent intent = getIntent();
        if (intent == null) {
            finish();
        } else {
            if (intent.getAction() == "com.google.android.gms.actions.CREATE_NOTE") {
                String text = intent.getStringExtra("android.intent.extra.TEXT");

                NoteDTO note = new NoteDTO(null, null, text, String.valueOf(System.currentTimeMillis()), null, getRandomColor());

                Disposable disposable =
                        model.putNote(note)
                                .subscribe(
                                        new Consumer<PutResult>() {
                                            @Override
                                            public void accept(PutResult putResult) {
                                                showMessage(R.string.note_created);
                                                finish();
                                            }
                                        },
                                        new Consumer<Throwable>() {
                                            @Override
                                            public void accept(Throwable throwable) {
                                                showMessage(R.string.note_created);
                                                Log.e("VoiceHook", "onCreate: " + throwable.getMessage(), throwable);
                                                finish();
                                            }
                                        });
            }

        }
    }

    private void showMessage(int id) {
        Toast.makeText(this, id, Toast.LENGTH_LONG).show();
    }

    private int getRandomColor() {
        int returnColor = Color.GRAY;
        int index = (int) (Math.random() * colors.length());
        returnColor = colors.getColor(index, Color.GRAY);
        return returnColor;
    }
}
