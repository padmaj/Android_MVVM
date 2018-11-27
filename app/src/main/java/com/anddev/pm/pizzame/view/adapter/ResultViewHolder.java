package com.anddev.pm.pizzame.view.adapter;

import android.databinding.ViewDataBinding;
import android.support.v7.widget.RecyclerView;
import android.view.View;

import com.anddev.pm.pizzame.BR;
import com.anddev.pm.pizzame.api.model.Result;
import com.anddev.pm.pizzame.databinding.ItemPizzaListBinding;
import com.anddev.pm.pizzame.view.listener.RecyclerViewItemClickListener;
import com.anddev.pm.pizzame.viewModel.ResultsViewModel;

/**
 * A view holder class of recyclerView item
 */
public class ResultViewHolder extends RecyclerView.ViewHolder implements View.OnClickListener {
    private final ViewDataBinding binding;
    private RecyclerViewItemClickListener clickListener;

    ResultViewHolder(ViewDataBinding binding, RecyclerViewItemClickListener clickListener) {
        super(binding.getRoot());
        this.binding = binding;
        this.clickListener = clickListener;
        binding.getRoot().setOnClickListener(this);
    }

    void bind(Result result, ResultsViewModel viewModel) {
        binding.setVariable(BR.result, result);
        binding.setVariable(BR.viewModel, viewModel);
        binding.executePendingBindings();
    }

    public ViewDataBinding getBinding() {
        return binding;
    }

    @Override
    public void onClick(View view) {
        clickListener.onClick(((ItemPizzaListBinding) binding).getResult());
    }
}
