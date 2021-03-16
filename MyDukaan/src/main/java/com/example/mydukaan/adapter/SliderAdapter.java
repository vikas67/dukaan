package com.example.mydukaan.adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.bumptech.glide.Glide;
import com.example.mydukaan.R;
import com.example.mydukaan.ginnymodal.silder.SilderList;
import com.smarteist.autoimageslider.SliderViewAdapter;

import java.util.List;

public class SliderAdapter extends SliderViewAdapter<SliderAdapter.SliderAdapterVH> {

    private Context context;
    private List<SilderList> mSliderItems;

    public SliderAdapter(Context context, List<SilderList> mSliderItems) {
        this.context = context;
        this.mSliderItems = mSliderItems;
    }


    public void renewItems(List<SilderList> sliderItems) {
        this.mSliderItems = sliderItems;
        notifyDataSetChanged();
    }

    public void deleteItem(int position) {
        this.mSliderItems.remove(position);
        notifyDataSetChanged();
    }

    public void addItem(SilderList sliderItem) {
        this.mSliderItems.add(sliderItem);
        notifyDataSetChanged();
    }

    @Override
    public SliderAdapterVH onCreateViewHolder(ViewGroup parent) {
        View inflate = LayoutInflater.from(parent.getContext()).inflate(R.layout.slider_image_layout, null);
        return new SliderAdapterVH(inflate);
    }

    @Override
    public void onBindViewHolder(SliderAdapterVH viewHolder, final int position) {

        SilderList sliderItem = mSliderItems.get(position);


//        viewHolder.textViewDescription.setText(sliderItem.getTitle());
//        viewHolder.textViewDescription.setTextSize(16);
//        viewHolder.textViewDescription.setTextColor(Color.WHITE);
        Glide.with(viewHolder.itemView)
                .load( sliderItem.getImg())
                .into(viewHolder.imageViewBackground);


        viewHolder.itemView.setOnClickListener(v -> Toast.makeText(context, "This is item in position " + position, Toast.LENGTH_SHORT).show());
    }

    @Override
    public int getCount() {
        //slider view count could be dynamic size
        return mSliderItems.size();
    }

    class SliderAdapterVH extends SliderViewAdapter.ViewHolder {

        View itemView;
        ImageView imageViewBackground;
        ImageView imageGifContainer;
        TextView textViewDescription;

        public SliderAdapterVH(View itemView) {
            super(itemView);
            imageViewBackground = itemView.findViewById(R.id.iv_auto_image_slider);
            imageGifContainer = itemView.findViewById(R.id.iv_gif_container);
            textViewDescription = itemView.findViewById(R.id.tv_auto_image_slider);
            this.itemView = itemView;
        }
    }


}
