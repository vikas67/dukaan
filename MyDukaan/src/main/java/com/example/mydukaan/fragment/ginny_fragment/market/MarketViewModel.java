package com.example.mydukaan.fragment.ginny_fragment.market;

import androidx.lifecycle.MutableLiveData;
import androidx.lifecycle.ViewModel;

import com.example.mydukaan.ginnymodal.category.CategoryList;
import com.example.mydukaan.ginnymodal.gadgets.Gadgets;
import com.example.mydukaan.ginnymodal.mobilebrand.MobileBrand;
import com.example.mydukaan.ginnymodal.offer.OfferList;
import com.example.mydukaan.ginnymodal.silder.SilderList;
import com.example.mydukaan.ginnymodal.topDeals.TopDealsList;

import java.util.List;

public class MarketViewModel extends ViewModel {

    MarketRepo marketRepo;
    MutableLiveData<List<SilderList>> listMutableLiveData = new MutableLiveData<>();
    MutableLiveData<List<CategoryList>> categoryListMutableLiveData = new MutableLiveData<>();
    MutableLiveData<List<TopDealsList>> topdeal = new MutableLiveData<>();

    MutableLiveData<List<OfferList>> offerlist = new MutableLiveData<>();
    MutableLiveData<List<Gadgets>> gadgetsList = new MutableLiveData<>();
    MutableLiveData<List<MobileBrand>> mobilebrandList = new MutableLiveData<>();

    public MarketViewModel() {
        marketRepo = new MarketRepo();
        getSilder();
        getCategory();
        getTopdeals();
        getgadgets();
        getmobilebrand();
        getoffer();
    }

    private void getSilder() {
        listMutableLiveData = marketRepo.getSilder();
    }

    private void getCategory() {
        categoryListMutableLiveData = marketRepo.getCategory();
    }

    private void getTopdeals() {
        topdeal = marketRepo.getTopDeals();
    }


    private void getoffer(){ offerlist = marketRepo.getOfferlist();}
    private void getgadgets(){gadgetsList = marketRepo.getGadgetsList();}
    private void getmobilebrand(){mobilebrandList = marketRepo.getMobilebrandList();}


    public MutableLiveData<List<SilderList>> returnSilderData() {
        return listMutableLiveData;
    }

    public MutableLiveData<List<CategoryList>> returnCategoryData() {
        return categoryListMutableLiveData;
    }

    public MutableLiveData<List<TopDealsList>> returnTopDealsData() {
        return topdeal;
    }


    public MutableLiveData<List<OfferList>>  returnOfferlist() {
        return offerlist;
    }

    public MutableLiveData<List<Gadgets>>  returnGadgetsList() {
        return gadgetsList;
    }

    public MutableLiveData<List<MobileBrand>>  returnMobilebrandList() {
        return mobilebrandList;
    }



}























