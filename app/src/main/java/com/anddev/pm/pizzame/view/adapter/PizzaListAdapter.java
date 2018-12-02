package com.anddev.pm.pizzame.view.adapter;

import android.databinding.DataBindingUtil;
import android.support.annotation.NonNull;
import android.support.v7.util.DiffUtil;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.ViewGroup;

import com.anddev.pm.pizzame.R;
import com.anddev.pm.pizzame.api.model.Result;
import com.anddev.pm.pizzame.databinding.ItemPizzaListBinding;
import com.anddev.pm.pizzame.view.listener.RecyclerViewItemClickListener;
import com.anddev.pm.pizzame.viewModel.ResultsViewModel;

import java.util.List;

/**
 * An adapter class to update the recyclerView
 */
public class PizzaListAdapter extends RecyclerView.Adapter<ResultViewHolder> {

    private List<Result> resultsList;
    private ResultsViewModel resultsViewModel;
    private RecyclerViewItemClickListener clickListener;

    public PizzaListAdapter(ResultsViewModel viewModel, RecyclerViewItemClickListener clickListener) {
        this.resultsViewModel = viewModel;
        this.clickListener = clickListener;
    }

    @NonNull
    @Override
    public ResultViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        ItemPizzaListBinding binding = DataBindingUtil
                .inflate(LayoutInflater.from(parent.getContext()), R.layout.item_pizza_list, parent, false);

        return new ResultViewHolder(binding, clickListener);
    }

    @Override
    public void onBindViewHolder(@NonNull ResultViewHolder holder, int position) {
        holder.bind(resultsList.get(position), resultsViewModel);
    }

    @Override
    public int getItemCount() {
        return resultsList != null ? resultsList.size() : 0;
    }

    /**
     * update the UI when receiving the new data
     *
     * @param newResultsList
     */
    public void setResultsList(final List<Result> newResultsList) {
        if (this.resultsList == null) {
            this.resultsList = newResultsList;
            notifyItemRangeInserted(0, resultsList.size());
        } else {
            DiffUtil.DiffResult result = DiffUtil.calculateDiff(new DiffUtil.Callback() {
                @Override
                public int getOldListSize() {
                    return resultsList.size();
                }

                @Override
                public int getNewListSize() {
                    return newResultsList.size();
                }

                @Override
                public boolean areItemsTheSame(int oldItemPosition, int newItemPosition) {
                    Result oldItem = resultsList.get(oldItemPosition);
                    Result newItem = newResultsList.get(newItemPosition);
                    return oldItem.id.equals(newItem.id);
                }

                @Override
                public boolean areContentsTheSame(int oldItemPosition, int newItemPosition) {
                    Result oldItem = resultsList.get(oldItemPosition);
                    Result newItem = newResultsList.get(newItemPosition);
                    return oldItem.title.equals(newItem.title);

                }
            });
            this.resultsList = newResultsList;
            result.dispatchUpdatesTo(this);
        }
    }
}
