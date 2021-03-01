package com.example.mydukaan.adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.mydukaan.R;
import com.example.mydukaan.modal.ContactList;

import org.jetbrains.annotations.NotNull;

import java.util.ArrayList;
import java.util.List;

public class ContactAdapter extends RecyclerView.Adapter<ContactAdapter.Viewholder> {
    Context context;
    List<ContactList>contactLists= new ArrayList<>();

    public ContactAdapter(Context context, List<ContactList>contactLists) {
        this.context = context;
        this.contactLists=contactLists;
    }

    @NonNull
    @NotNull
    @Override
    public Viewholder onCreateViewHolder(@NonNull @NotNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(context).inflate(R.layout.row_contact_layout, parent, false);
        return new Viewholder(view);

    }

    @Override
    public void onBindViewHolder(@NonNull @NotNull Viewholder holder, int position) {
         holder.name.setText(contactLists.get(position).getName());
         holder.number.setText(contactLists.get(position).getNumber());
         holder.alpha.setText(contactLists.get(position).getName().substring(0,1));
    }

    @Override
    public int getItemCount() {
        return contactLists.size();
    }

    public class Viewholder extends RecyclerView.ViewHolder {
         TextView name,number,alpha;
        public Viewholder(@NonNull @NotNull View itemView) {
            super(itemView);
          name=itemView.findViewById(R.id.name);
          number=itemView.findViewById(R.id.number);
          alpha=itemView.findViewById(R.id.alpha);
        }
    }

}
