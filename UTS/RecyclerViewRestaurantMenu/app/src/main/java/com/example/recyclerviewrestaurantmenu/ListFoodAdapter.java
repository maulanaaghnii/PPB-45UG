package com.example.recyclerviewrestaurantmenu;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Filter;
import android.widget.Filterable;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.bumptech.glide.Glide;
import com.bumptech.glide.request.RequestOptions;

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;

public class ListFoodAdapter extends RecyclerView.Adapter<ListFoodAdapter.ListViewHolder> implements Filterable {
    private ArrayList<Food> listFood;
    private OnItemClickCallback onItemClickCallback;
    List<String> foodListAll;

    public void setOnItemClickCallback(OnItemClickCallback onItemClickCallback) {
        this.onItemClickCallback = onItemClickCallback;
    }

    public ListFoodAdapter(ArrayList<Food> list) {
        this.listFood = list;
        foodListAll = new ArrayList<>();
        foodListAll.addAll(foodListAll);
    }

    @NonNull
    @Override
    public ListViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.item_row_food, parent, false);
        return new ListViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull ListViewHolder holder, int position) {
        Food food = listFood.get(position);

        Glide.with(holder.itemView.getContext())
                .load(food.getPhoto())
                .apply(new RequestOptions().override(55,55))
                .into(holder.imgPhoto);

        holder.tvName.setText(food.getName());
        holder.tvPrice.setText(food.getPrice());
        holder.tvDetail.setText(food.getDetail());

        holder.itemView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                onItemClickCallback.onItemClicked(listFood.get(holder.getAdapterPosition()));
            }
        });
    }

    @Override
    public int getItemCount() {
        return listFood.size();
    }

    @Override
    public Filter getFilter() {
        return myFilter;
    }

    Filter myFilter = new Filter() {
        @Override
        protected FilterResults performFiltering(CharSequence charSequence) {

            List<String> filteredList = new ArrayList<>();

            if (charSequence == null || charSequence.length() == 0) {
                filteredList.addAll(foodListAll);
            } else {
                for (String food: foodListAll) {
                    if (food.toLowerCase().contains(charSequence.toString().toLowerCase())) {
                        filteredList.add(food);
                    }
                }
            }

            FilterResults filterResults = new FilterResults();
            filterResults.values = filteredList;
            return filterResults;
        }

        @Override
        protected void publishResults(CharSequence charSequence, FilterResults filterResults) {
            listFood.clear();
            listFood.addAll((Collection<? extends Food>) filterResults.values);
            notifyDataSetChanged();

        }
    };

    class ListViewHolder extends RecyclerView.ViewHolder {
        ImageView imgPhoto;
        TextView tvName;
        TextView tvDetail;
        TextView tvPrice;

        ListViewHolder(View itemview) {
            super(itemview);
            imgPhoto = itemview.findViewById(R.id.img_item_food);
            tvName = itemview.findViewById(R.id.tv_item_name);
            tvDetail = itemview.findViewById(R.id.tv_detail);
            tvPrice = itemview.findViewById(R.id.tv_price);
        }
    }

    public interface OnItemClickCallback {
        void onItemClicked(Food data);
    }
}
