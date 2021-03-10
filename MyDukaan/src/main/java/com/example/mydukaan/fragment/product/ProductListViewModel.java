package com.example.mydukaan.fragment.product;

import androidx.lifecycle.LiveData;
import androidx.lifecycle.MutableLiveData;
import androidx.lifecycle.ViewModel;

import com.example.mydukaan.modal.productList.ProductList;

import java.util.ArrayList;
import java.util.List;

public class ProductListViewModel extends ViewModel {

    private MutableLiveData<List<ProductList>> mutableProductList = new MutableLiveData<>();

    public ProductListViewModel() {
        getProductList();
    }


    public LiveData<List<ProductList>> getProducts() {
        if (mutableProductList == null) {
            mutableProductList = new MutableLiveData<>();
            getProductList();
        }
        return mutableProductList;
    }

    private void getProductList() {
        List<ProductList> productLists = new ArrayList<>();
        productLists.add(new ProductList(1, "Anjir", "https://www.ayurtimes.com/wp-content/uploads/2014/09/Figs.jpg", "100", "10", true));
        productLists.add(new ProductList(2, "Golden Kiwi", "https://media1.popsugar-assets.com/files/thumbor/9a4yuHSZCwFU2kf-W2jhejsnuKQ/fit-in/2048xorig/filters:format_auto-!!-:strip_icc-!!-/2015/08/18/229/n/28443503/8cc6a844a2ebb815_shutterstock_266640194/i/Gold-Kiwifruit.jpg", "150", "100", true));
        productLists.add(new ProductList(3, "Raw Dates", "http://mnmtraders.weebly.com/uploads/2/5/1/0/25105846/9370726_orig.jpg", "50", "30", true));
        productLists.add(new ProductList(4, "Cherry Tomatoes", "https://fafard.com/wp-content/uploads/2015/01/Tomato_Fantastico-fruitInBowl-resize.jpg", "150", "100", true));
        productLists.add(new ProductList(5, "Lettuce", "https://www.farmizen.com/wp-content/uploads/2017/07/Lettuce_refill_1200x960.jpg", "130", "10", false));
        productLists.add(new ProductList(6, "Bell Peper Yellow", "https://www.parkswholesaleplants.com/wp-content/uploads/2009/02/Yellow-Bell-Pepper.jpg", "150", "100", true));
        productLists.add(new ProductList(7, "Sweet Caorn Packet", "https://images.lowes.com/product/converted/041530/041530564105.jpg", "310", "70", true));
        productLists.add(new ProductList(8, "Baby Corn", "https://s3.amazonaws.com/images.pdpics.com/images/3251-baby-corn.jpg", "420", "73", true));
        productLists.add(new ProductList(9, "Mushroom", "https://www.iamexpat.nl/sites/default/files/styles/article--full/public/poisonous-mushroom.jpg?itok=niJxjUMs", "432", "54", true));
        mutableProductList.setValue(productLists);
    }


}





























