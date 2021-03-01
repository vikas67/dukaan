package com.example.mydukaan.adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;


import com.example.mydukaan.R;
import com.example.mydukaan.modal.DocumentList;

import org.jetbrains.annotations.NotNull;

import java.util.List;

public class DocumentAdapter extends RecyclerView.Adapter<DocumentAdapter.Viewholder> {
    Context context;
    List<DocumentList> documentLists;
    RecyclerView recyclerView;

    public DocumentAdapter(Context context, List<DocumentList>documentLists, RecyclerView recyclerView) {
        this.context = context;
        this.documentLists=documentLists;
        this.recyclerView=recyclerView;
    }

    @NonNull
    @NotNull
    @Override
    public Viewholder onCreateViewHolder(@NonNull @NotNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(context).inflate(R.layout.row_document_layout, parent, false);
        return new Viewholder(view);

    }

    @Override
    public void onBindViewHolder(@NonNull @NotNull Viewholder holder, int position) {
       holder.image.setImageURI(documentLists.get(position).getUri());
       holder.name.setText(documentLists.get(position).getName());
       holder.close.setOnClickListener(v -> {
           documentLists.remove(position);
           notifyItemRemoved(position);
           notifyItemRangeRemoved(position,documentLists.size());
           if (documentLists.isEmpty()){
               recyclerView.setVisibility(View.GONE);
           }
       });
    }

    @Override
    public int getItemCount() {
        return documentLists.size();
    }

    public class Viewholder extends RecyclerView.ViewHolder {
        ImageView image,close;
        TextView name;
        public Viewholder(@NonNull @NotNull View itemView) {
            super(itemView);
            name= itemView.findViewById(R.id.document);
            image= itemView.findViewById(R.id.image);
            close= itemView.findViewById(R.id.close);
        }
    }
}
