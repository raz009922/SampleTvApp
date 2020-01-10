package com.example.sampletvapp.adapter;

import android.content.Context;
import android.text.Layout;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.TextView;

import com.example.sampletvapp.R;
import com.example.sampletvapp.model.DataItem;

import java.util.ArrayList;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

public class TvChannelListAdapter extends RecyclerView.Adapter<TvChannelListAdapter.Holder> {

    private Context context;
    private LayoutInflater inflater;

    public TvChannelListAdapter(Context context) {
        this.context = context;
        inflater = LayoutInflater.from(context);
    }

    private ArrayList<DataItem> dataItemArrayList = new ArrayList<>();

    public void setDataItemArrayList(ArrayList<DataItem> dataItemArrayList) {
        this.dataItemArrayList = dataItemArrayList;
        notifyDataSetChanged();
    }

    @NonNull
    @Override
    public Holder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = inflater.inflate(R.layout.item_tv_channels, parent, false);
        return new Holder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull Holder holder, int position) {
        if (position < 0 || position >= dataItemArrayList.size()) {
            return;
        }
        holder.setPosition(position);
        holder.channelName.setText(dataItemArrayList.get(position).getTitle());

    }

    @Override
    public int getItemCount() {
        return dataItemArrayList.size();
    }

    class Holder extends RecyclerView.ViewHolder {

        TextView channelName;
        Button playButton;
        Button genreButton;
        int position = -1;

        public Holder(@NonNull View itemView) {
            super(itemView);
            channelName = itemView.findViewById(R.id.text_channel_name);
            playButton = itemView.findViewById(R.id.button_play);
            genreButton = itemView.findViewById(R.id.button_genres);
            handleClicks();
        }

        public void setPosition(int position) {
            this.position = position;
        }

        private void handleClicks() {
            playButton.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    channelAdapterCallbacks.playButtonClicked(position);
                }
            });
            genreButton.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    channelAdapterCallbacks.genreButtonClicked(position);
                }
            });

        }


    }

    private TvChannelAdapterCallbacks channelAdapterCallbacks;

    public void setChannelAdapterCallbacks(TvChannelAdapterCallbacks channelAdapterCallbacks) {
        this.channelAdapterCallbacks = channelAdapterCallbacks;
    }

    public interface TvChannelAdapterCallbacks {
        void playButtonClicked(int position);

        void genreButtonClicked(int position);
    }

}
