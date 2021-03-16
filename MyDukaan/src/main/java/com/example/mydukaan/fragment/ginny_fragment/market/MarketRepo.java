package com.example.mydukaan.fragment.ginny_fragment.market;

import androidx.lifecycle.MutableLiveData;

import com.example.mydukaan.ginnymodal.category.CategoryList;
import com.example.mydukaan.ginnymodal.gadgets.Gadgets;
import com.example.mydukaan.ginnymodal.mobilebrand.MobileBrand;
import com.example.mydukaan.ginnymodal.offer.OfferList;
import com.example.mydukaan.ginnymodal.silder.SilderList;
import com.example.mydukaan.ginnymodal.topDeals.TopDealsList;
import com.example.mydukaan.service.ApiGinnyClient;
import com.example.mydukaan.service.MyGinnyInterface;

import java.util.ArrayList;
import java.util.List;

public class MarketRepo {


    MyGinnyInterface anInterface;
    MutableLiveData<List<CategoryList>> categoryListMutableLiveData = new MutableLiveData<>();
    MutableLiveData<List<SilderList>> listMutableLiveData = new MutableLiveData<>();
    MutableLiveData<List<TopDealsList>> topdeal = new MutableLiveData<>();

    MutableLiveData<List<OfferList>> offerlist = new MutableLiveData<>();
    MutableLiveData<List<Gadgets>> gadgetsList = new MutableLiveData<>();
    MutableLiveData<List<MobileBrand>> mobilebrandList = new MutableLiveData<>();

    public MarketRepo() {
        anInterface = ApiGinnyClient.getClient().create(MyGinnyInterface.class);
        fakeListCategory();
        fakeListSilder();
        fakeListTopDeals();
        fakeListOffer();
        fakeListGadgets();
        fakeListMobilebrand();
    }


    public MutableLiveData<List<SilderList>> getSilder() {
        return listMutableLiveData;
    }

    public MutableLiveData<List<CategoryList>> getCategory() {
        return categoryListMutableLiveData;
    }

    public MutableLiveData<List<TopDealsList>> getTopDeals() {
        return topdeal;
    }

    public MutableLiveData<List<OfferList>> getOfferlist() {
        return offerlist;
    }

    public MutableLiveData<List<Gadgets>> getGadgetsList() {
        return gadgetsList;
    }

    public MutableLiveData<List<MobileBrand>> getMobilebrandList() {
        return mobilebrandList;
    }


    private void fakeListCategory() {
        List<CategoryList> categoryLists = new ArrayList<>();
        categoryLists.add(new CategoryList("Mobiles", "https://reapp.com.gh/wp-content/uploads/2018/03/nk.jpeg"));
        categoryLists.add(new CategoryList("Electronics", "https://purepng.com/public/uploads/large/purepng.com-laptop-notebooklaptopsnotebooknotebook-computerclamshell-17015283548647iqlh.png"));
        categoryLists.add(new CategoryList("Application", "https://reapp.com.gh/wp-content/uploads/2018/03/nk.jpeg"));
        categoryLists.add(new CategoryList("Fashion", "https://purepng.com/public/uploads/large/purepng.com-laptop-notebooklaptopsnotebooknotebook-computerclamshell-17015283548647iqlh.png"));
        categoryLists.add(new CategoryList("Mobiles", "https://reapp.com.gh/wp-content/uploads/2018/03/nk.jpeg"));
        categoryLists.add(new CategoryList("Electronics", "https://purepng.com/public/uploads/large/purepng.com-laptop-notebooklaptopsnotebooknotebook-computerclamshell-17015283548647iqlh.png"));
        categoryLists.add(new CategoryList("Application", "https://reapp.com.gh/wp-content/uploads/2018/03/nk.jpeg"));
        categoryLists.add(new CategoryList("Fashion", "https://purepng.com/public/uploads/large/purepng.com-laptop-notebooklaptopsnotebooknotebook-computerclamshell-17015283548647iqlh.png"));
        categoryListMutableLiveData.setValue(categoryLists);
    }


    private void fakeListSilder() {
        List<SilderList> silderLists = new ArrayList<>();
        silderLists.add(new SilderList("https://images-eu.ssl-images-amazon.com/images/G/31/img20/Smallappliances/SA_SummerStore/2021_summer/hero_1500x300_2.jpg"));
        silderLists.add(new SilderList("https://images-eu.ssl-images-amazon.com/images/G/31/img17/Home/LA/Reshma_LA/SUMMER_FEST-2021/0Summer-Appliances-Carnival---12th---14th-Feb_1500x300_1.jpg"));
        silderLists.add(new SilderList("https://images-eu.ssl-images-amazon.com/images/G/31/IN-hq/2020/img/Home_Improvement/XCM_Manual_1222330_1159608_IN_in_home_improvement_home_improvement_3075237_1500x300_null_en_IN.jpg"));
        silderLists.add(new SilderList("https://images-eu.ssl-images-amazon.com/images/G/31/IMG20/Home/BAU/Storage/XCM_Manual_1221589_1154216_IN_IN_Home_Storage1_BAU_2db16745_0197_4758_9f31_dff1033d139a_1500x250_null_en_IN._CB432545016_.jpg"));
        listMutableLiveData.setValue(silderLists);
    }

    private void fakeListTopDeals() {
        List<TopDealsList> topDealsLists = new ArrayList<>();
        topDealsLists.add(new TopDealsList("https://reapp.com.gh/wp-content/uploads/2018/03/nk.jpeg", "iphone 6", 10));
        topDealsLists.add(new TopDealsList("https://purepng.com/public/uploads/large/purepng.com-laptop-notebooklaptopsnotebooknotebook-computerclamshell-17015283548647iqlh.png", "iphone 10", 10));
        topDealsLists.add(new TopDealsList("https://reapp.com.gh/wp-content/uploads/2018/03/nk.jpeg", "mobile", 10));
        topDealsLists.add(new TopDealsList("https://purepng.com/public/uploads/large/purepng.com-laptop-notebooklaptopsnotebooknotebook-computerclamshell-17015283548647iqlh.png", "iphone 11", 10));
        topdeal.setValue(topDealsLists);
    }

    private void fakeListOffer() {
        List<OfferList> offerLists = new ArrayList<>();
        offerLists.add(new OfferList("https://reapp.com.gh/wp-content/uploads/2018/03/nk.jpeg", "Mobile", "543"));
        offerLists.add(new OfferList("https://reapp.com.gh/wp-content/uploads/2018/03/nk.jpeg", "Mobile", "6534"));
        offerLists.add(new OfferList("https://purepng.com/public/uploads/large/purepng.com-laptop-notebooklaptopsnotebooknotebook-computerclamshell-17015283548647iqlh.png", "Laptop", "2344"));
        offerLists.add(new OfferList("https://purepng.com/public/uploads/large/purepng.com-laptop-notebooklaptopsnotebooknotebook-computerclamshell-17015283548647iqlh.png", "Laptop", "6532"));
        offerlist.setValue(offerLists);
    }

    private void fakeListGadgets() {
        List<Gadgets> gadgetsLists = new ArrayList<>();
        gadgetsLists.add(new Gadgets("https://reapp.com.gh/wp-content/uploads/2018/03/nk.jpeg"));
        gadgetsLists.add(new Gadgets("https://purepng.com/public/uploads/large/purepng.com-laptop-notebooklaptopsnotebooknotebook-computerclamshell-17015283548647iqlh.png"));
        gadgetsLists.add(new Gadgets("https://purepng.com/public/uploads/large/purepng.com-laptop-notebooklaptopsnotebooknotebook-computerclamshell-17015283548647iqlh.png"));
        gadgetsList.setValue(gadgetsLists);
    }

    private void fakeListMobilebrand() {
        List<MobileBrand> mobileBrandList = new ArrayList<>();
        mobileBrandList.add(new MobileBrand("https://purepng.com/public/uploads/large/purepng.com-laptop-notebooklaptopsnotebooknotebook-computerclamshell-17015283548647iqlh.png", "Laptop"));
        mobileBrandList.add(new MobileBrand("https://purepng.com/public/uploads/large/purepng.com-laptop-notebooklaptopsnotebooknotebook-computerclamshell-17015283548647iqlh.png", "Laptop"));
        mobileBrandList.add(new MobileBrand("https://purepng.com/public/uploads/large/purepng.com-laptop-notebooklaptopsnotebooknotebook-computerclamshell-17015283548647iqlh.png", "Laptop"));
        mobileBrandList.add(new MobileBrand("https://purepng.com/public/uploads/large/purepng.com-laptop-notebooklaptopsnotebooknotebook-computerclamshell-17015283548647iqlh.png", "Laptop"));
        mobilebrandList.setValue(mobileBrandList);
    }


}






































































