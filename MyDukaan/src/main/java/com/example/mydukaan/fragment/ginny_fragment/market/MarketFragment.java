package com.example.mydukaan.fragment.ginny_fragment.market;

import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.lifecycle.ViewModelProvider;

import com.example.mydukaan.adapter.GadgetsAdapter;
import com.example.mydukaan.adapter.MarketCategoryAdapter;
import com.example.mydukaan.adapter.MobileBrandAdapter;
import com.example.mydukaan.adapter.OfferAdapter;
import com.example.mydukaan.adapter.SliderAdapter;
import com.example.mydukaan.adapter.TopDealsAdapter;
import com.example.mydukaan.databinding.MarketFragmentBinding;
import com.example.mydukaan.other.SessionManage;
import com.example.mydukaan.other.SpacesItemDecoration;
import com.smarteist.autoimageslider.SliderView;

public class MarketFragment extends Fragment {

    private MarketViewModel mViewModel;
    MarketFragmentBinding binding;
    private static final String TAG = "MarketFragment";
    SessionManage sessionManage;


    public static MarketFragment newInstance() {
        return new MarketFragment();
    }

    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container,
                             @Nullable Bundle savedInstanceState) {
        binding = MarketFragmentBinding.inflate(inflater, container, false);
        return binding.getRoot();
    }

    @Override
    public void onActivityCreated(@Nullable Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);
        mViewModel = new ViewModelProvider(this).get(MarketViewModel.class);
        sessionManage = new SessionManage(requireContext());

        Log.e(TAG, "onActivityCreated: " + sessionManage.getUserDetails().get("AccessToken"));


        mViewModel.returnCategoryData().observe(requireActivity(), categoryLists -> {
            Log.e(TAG, "onActivityCreated: " + categoryLists);
            binding.categoryRecyclerView.setAdapter(new MarketCategoryAdapter(categoryLists));
        });
        mViewModel.returnSilderData().observe(requireActivity(), categoryLists -> {
            Log.e(TAG, "onActivityCreated: " + categoryLists);
            binding.imageSlider.setSliderAdapter(new SliderAdapter(requireContext(), categoryLists));
            binding.imageSlider.setAutoCycleDirection(SliderView.AUTO_CYCLE_DIRECTION_RIGHT);
            binding.imageSlider.setScrollTimeInSec(4); //set scroll delay in seconds :
            binding.imageSlider.startAutoCycle();
        });

        mViewModel.returnTopDealsData().observe(requireActivity(), topDealsLists -> {
            binding.TopDealsRecyclerView.setAdapter(new TopDealsAdapter(topDealsLists));
            binding.TopDealsRecyclerView.addItemDecoration(new SpacesItemDecoration(10));
        });

        mViewModel.returnOfferlist().observe(requireActivity(), offerLists -> {
            binding.OfferProductRecyclerView.setAdapter(new OfferAdapter(offerLists));
        });

        mViewModel.returnGadgetsList().observe(requireActivity(), gadgetsList -> {
            binding.GadgetRecyclerView.setAdapter(new GadgetsAdapter(gadgetsList));
        });

        mViewModel.returnMobilebrandList().observe(requireActivity(), mobileBrands -> {
            binding.MobileBrandRecyclerView.setAdapter(new MobileBrandAdapter(mobileBrands));
            binding.MobileBrandRecyclerView.addItemDecoration(new SpacesItemDecoration(10));
        });


    }

}

































