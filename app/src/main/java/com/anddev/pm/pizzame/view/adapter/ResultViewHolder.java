package com.anddev.pm.pizzame.view.adapter;

import android.os.Build;
import android.support.v7.widget.RecyclerView;
import android.view.View;

import com.anddev.pm.pizzame.BR;
import com.anddev.pm.pizzame.Utils;
import com.anddev.pm.pizzame.api.model.Result;
import com.anddev.pm.pizzame.databinding.ItemPizzaListBinding;
import com.anddev.pm.pizzame.view.listener.RecyclerViewItemClickListener;
import com.anddev.pm.pizzame.viewModel.ResultsViewModel;

/**
 * A view holder class of recyclerView item
 */
public class ResultViewHolder extends RecyclerView.ViewHolder implements View.OnClickListener {
    private final ItemPizzaListBinding binding;
    private RecyclerViewItemClickListener clickListener;

    ResultViewHolder(ItemPizzaListBinding binding, RecyclerViewItemClickListener clickListener) {
        super(binding.getRoot());
        this.binding = binding;
        this.clickListener = clickListener;

        if (Build.VERSION.SDK_INT < Build.VERSION_CODES.LOLLIPOP) {
            Utils.updateRatingProgressDrawable(binding.rbRating);
        }

        binding.getRoot().setOnClickListener(this);
    }

    void bind(Result result, ResultsViewModel viewModel) {
        binding.setVariable(BR.result, result);
        binding.setVariable(BR.viewModel, viewModel);
        binding.executePendingBindings();
    }

    public ItemPizzaListBinding getBinding() {
        return binding;
    }

    @Override
    public void onClick(View view) {
        clickListener.onClick(binding.getResult());
    }
}
