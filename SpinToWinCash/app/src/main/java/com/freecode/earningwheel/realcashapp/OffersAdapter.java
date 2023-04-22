package com.freecode.earningwheel.realcashapp;

import android.content.Context;
import android.content.Intent;
import android.net.Uri;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.bumptech.glide.Glide;

import java.util.List;

public class OffersAdapter extends RecyclerView.Adapter<OffersAdapter.OfferViewHolder> {

    private Context context;
    private List<Offer> offers;

    public OffersAdapter(Context context, List<Offer> offers) {
        this.context = context;
        this.offers = offers;
    }

    @NonNull
    @Override
    public OfferViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(context).inflate(R.layout.offer_item, parent, false);
        return new OfferViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull OfferViewHolder holder, int position) {
        final Offer offer = offers.get(position);

        holder.tvOfferName.setText(offer.name);
        holder.tvOfferPayout.setText(offer.payout);

        Glide.with(context)
                .load(offer.thumbnail)
                .into(holder.ivOfferThumbnail);

        holder.itemView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(Intent.ACTION_VIEW, Uri.parse(offer.url));
                context.startActivity(intent);
            }
        });
    }

    @Override
    public int getItemCount() {
        return offers.size();
    }

    public class OfferViewHolder extends RecyclerView.ViewHolder {
        public TextView tvOfferName;
        public TextView tvOfferPayout;
        public ImageView ivOfferThumbnail;

        public OfferViewHolder(View itemView) {
            super(itemView);
            tvOfferName = itemView.findViewById(R.id.tvOfferName);
            tvOfferPayout = itemView.findViewById(R.id.tvOfferPayout);
            ivOfferThumbnail = itemView.findViewById(R.id.ivOfferThumbnail);
        }
    }

}
