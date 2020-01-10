package com.example.sampletvapp.dialog;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.example.sampletvapp.R;
import com.example.sampletvapp.model.DataItem;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.DialogFragment;

public class CustomDialog extends DialogFragment {


    // TODO :: Recyceler View for Genres

    public static CustomDialog newInstance(DataItem dataItem) {
        CustomDialog fragment = new CustomDialog();
        fragment.dataItem = dataItem;
        Bundle args = new Bundle();
        fragment.setArguments(args);
        return fragment;
    }

    private DataItem dataItem;

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container,
                             @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.dialog_genres, container, false);
        sampleText(view);
        return view;
    }

    private void sampleText(View view) {
        TextView textView = view.findViewById(R.id.textView);
        textView.setText(dataItem.getListGenres().get(0).getGenre());
    }
}
