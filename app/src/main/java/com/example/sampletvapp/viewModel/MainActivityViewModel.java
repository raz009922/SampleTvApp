package com.example.sampletvapp.viewModel;

import androidx.lifecycle.LiveData;
import androidx.lifecycle.MutableLiveData;
import androidx.lifecycle.ViewModel;

import com.example.sampletvapp.model.DataItem;
import com.example.sampletvapp.model.Token;
import com.example.sampletvapp.model.TvChannel;
import com.example.sampletvapp.repository.Repository;

import java.util.ArrayList;

public class MainActivityViewModel extends ViewModel {

    //  TODO :: Response Model + Live Data Implementation - 3

    private MutableLiveData<Token> tokenMutableLiveData = new MutableLiveData<>();
    private MutableLiveData<TvChannel> liveDataDataItem = new MutableLiveData<>();

    private int currentPage = 1;
    private int pageSize = 20;

    public LiveData<Token> getTokenMutableLiveData() {
        return tokenMutableLiveData;
    }

    public LiveData<TvChannel> getLiveDataDataItem() {
        return liveDataDataItem;
    }

    public void getToken() {
        Repository.getInstance().getToken(tokenMutableLiveData);
    }

    public void getLiveDataItem(String accessToken) {
        Repository.getInstance().getDataItem(accessToken, liveDataDataItem, currentPage, pageSize);
    }

    public void getLiveDataItemNextPage(String accessToken) {
        if (liveDataDataItem.getValue() == null) return;
        int totalpage = liveDataDataItem.getValue().getTotalPage();
        if (currentPage < totalpage) {
            currentPage++;
            Repository.getInstance().getDataItem(accessToken, liveDataDataItem, currentPage,
                    pageSize);
        }
    }

    public DataItem getDataItem(int position) {
        if (position < 0) return null;
        if (position > liveDataDataItem.getValue().getData().size()) return null;
        ArrayList<DataItem> arrayList = new ArrayList<>(liveDataDataItem.getValue().getData());
        return arrayList.get(position);
    }
}
