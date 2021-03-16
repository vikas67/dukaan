package com.example.mydukaan.fragment.referearn;

import android.database.Cursor;
import android.os.Bundle;
import android.provider.ContactsContract;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.Fragment;
import androidx.navigation.NavController;
import androidx.navigation.Navigation;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.example.mydukaan.R;
import com.example.mydukaan.activity.DashboardActivity;
import com.example.mydukaan.adapter.ContactAdapter;
import com.example.mydukaan.databinding.FragmentRegerEarnBinding;
import com.example.mydukaan.modal.ContactList;

import org.jetbrains.annotations.NotNull;

import java.util.ArrayList;
import java.util.List;


public class RegerEarnFragment extends Fragment {


    List<ContactList> contactLists = new ArrayList<>();
    FragmentRegerEarnBinding binding;


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        actionbar();
        binding = FragmentRegerEarnBinding.inflate(inflater, container, false);
        return binding.getRoot();
    }

    private void actionbar() {
        ((AppCompatActivity) getActivity()).getSupportActionBar().setDisplayShowCustomEnabled(true);
        ((AppCompatActivity) getActivity()).getSupportActionBar().setDisplayShowTitleEnabled(false);
        LayoutInflater inflator = LayoutInflater.from(getActivity());
        View v = inflator.inflate(R.layout.refer_earn_action_bar, null);
        ((AppCompatActivity) getActivity()).getSupportActionBar().setCustomView(v);
        ((AppCompatActivity) getActivity()).getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        ((AppCompatActivity) getActivity()).getSupportActionBar().setElevation(0);
        ((AppCompatActivity) getActivity()).getSupportActionBar().setElevation(0);
        ((AppCompatActivity) getActivity()).getSupportActionBar().show();
    }

    @Override
    public void onViewCreated(@NonNull @NotNull View view, @Nullable @org.jetbrains.annotations.Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);


//        GetContacts();
        SetFragmentData();
        FragmentAction();

    }

    @Override
    public void onStart() {
        super.onStart();
//        new DashboardActivity().HideBottomNavigation();
    }

    private void FragmentAction() {
        binding.claim.setOnClickListener(v -> {
            NavController navController = Navigation.findNavController(v);
            navController.navigate(R.id.action_regerEarnFragment_to_rewardsFragment);
        });
    }

    private void SetFragmentData() {
        LinearLayoutManager linearLayoutManager = new LinearLayoutManager(getActivity());
        linearLayoutManager.setOrientation(RecyclerView.VERTICAL);
        binding.recycle.setLayoutManager(linearLayoutManager);
        binding.recycle.setAdapter(new ContactAdapter(getActivity(), contactLists));
    }

    private void GetContacts() {
        String sorting = ContactsContract.Contacts.DISPLAY_NAME + " DESC";
        Cursor phones = getActivity().getContentResolver()
                .query(ContactsContract.CommonDataKinds.Phone.CONTENT_URI,
                        null, null, null, sorting);
        while (phones.moveToNext()) {
            String name = phones.getString(phones.getColumnIndex(ContactsContract.CommonDataKinds.Phone.DISPLAY_NAME));
            String phoneNumber = phones.getString(phones.getColumnIndex(ContactsContract.CommonDataKinds.Phone.NUMBER));
            contactLists.add(new ContactList(name, phoneNumber));
        }
        phones.close();
    }

}