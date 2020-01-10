package com.example.sampletvapp.actvities;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.lifecycle.Observer;
import androidx.lifecycle.ViewModelProviders;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.RelativeLayout;
import android.widget.Toast;

import com.example.sampletvapp.R;
import com.example.sampletvapp.adapter.TvChannelListAdapter;
import com.example.sampletvapp.dialog.CustomDialog;
import com.example.sampletvapp.manager.DataManager;
import com.example.sampletvapp.model.DataItem;
import com.example.sampletvapp.model.Token;
import com.example.sampletvapp.model.TvChannel;
import com.example.sampletvapp.viewModel.MainActivityViewModel;

import java.util.ArrayList;

public class MainActivity extends AppCompatActivity {
    private MainActivityViewModel mainActivityViewModel;

    // TODO : network state checking + alert - 4
    // TODO : fix progressbar + background

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);


        initRecyclerView();
        initProgressbarLayout();
        initViewModel();

    }

    RelativeLayout progressBarLayout;

    private void initProgressbarLayout() {
        progressBarLayout = findViewById(R.id.layout_progress_bar);
        setProgressBarLayoutVisibilty(true);
    }

    RecyclerView recyclerView;
    TvChannelListAdapter adapter;

    private void initRecyclerView() {
        recyclerView = findViewById(R.id.recyclerView);
        adapter = new TvChannelListAdapter(this);
        adapter.setChannelAdapterCallbacks(new TvChannelListAdapter.TvChannelAdapterCallbacks() {
            @Override
            public void playButtonClicked(int position) {
                DataItem dataItem = mainActivityViewModel.getDataItem(position);
                DataManager.getInstance().setSelectedDataItem(dataItem);
                gotoPlayActivity();
            }

            @Override
            public void genreButtonClicked(int position) {
                DataItem dataItem = mainActivityViewModel.getDataItem(position);
                showDialog(dataItem);
            }
        });
        recyclerView.setLayoutManager(new LinearLayoutManager(this, RecyclerView.VERTICAL, false));
        recyclerView.setAdapter(adapter);
        recyclerView.addOnScrollListener(new RecyclerView.OnScrollListener() {
            @Override
            public void onScrollStateChanged(@NonNull RecyclerView recyclerView, int newState) {
                super.onScrollStateChanged(recyclerView, newState);
                if (!recyclerView.canScrollVertically(1) && newState == RecyclerView.SCROLL_STATE_IDLE) {
                    mainActivityViewModel.getLiveDataItemNextPage(DataManager.getInstance().getToken().getAccessToken());
                    setProgressBarLayoutVisibilty(true);
                }
            }

        });
    }

    private void gotoPlayActivity() {
        Intent intent = new Intent(this, PlayActivity.class);
        startActivity(intent);
    }

    private void initViewModel() {
        mainActivityViewModel = ViewModelProviders.of(this).get(MainActivityViewModel.class);
        if (DataManager.getInstance().getToken() != null) {
            mainActivityViewModel.getTokenMutableLiveData().observe(this, new Observer<Token>() {
                @Override
                public void onChanged(Token token) {
                    Log.d("Tag", "onChanged: " + "ww");
                    if (DataManager.getInstance().getToken() != null) {
                        DataManager.getInstance().setToken(token);
                        mainActivityViewModel.getLiveDataItem(token.getAccessToken());
                    } else {
                        setProgressBarLayoutVisibilty(true);
                    }
                }
            });
            mainActivityViewModel.getLiveDataDataItem().observe(this, new Observer<TvChannel>() {
                @Override
                public void onChanged(TvChannel channel) {
                    if (DataManager.getInstance().getToken() != null) {
                        Log.d("TAG", "onChanged: " + channel.getData().size());
                        adapter.setDataItemArrayList(new ArrayList<>(channel.getData()));
                        setProgressBarLayoutVisibilty(false);
                    } else {
                        setProgressBarLayoutVisibilty(true);
                    }
                }
            });
            mainActivityViewModel.getToken();
        } else {
            setProgressBarLayoutVisibilty(true);
            Toast.makeText(getApplicationContext(), "Server isn't responding", Toast.LENGTH_LONG).show();
        }
    }

    private void showDialog(DataItem dataItem) {
        CustomDialog dialog = CustomDialog.newInstance(dataItem);
        dialog.show(getSupportFragmentManager(), dialog.getClass().getName());
    }


    private void setProgressBarLayoutVisibilty(boolean visible) {
        if (visible) {
            progressBarLayout.setVisibility(View.VISIBLE);
            return;
        }
        progressBarLayout.setVisibility(View.INVISIBLE);
    }
}
