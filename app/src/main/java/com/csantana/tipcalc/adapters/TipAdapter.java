package com.csantana.tipcalc.adapters;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;
import java.util.*;

import com.csantana.tipcalc.R;
import com.csantana.tipcalc.models.TipRecord;

import butterknife.Bind;
import butterknife.ButterKnife;

/**
 * Created by CLsantana on 16/10/16.
 */

public class TipAdapter extends RecyclerView.Adapter<TipAdapter.ViewHolder> {
    private Context context;
        private List<TipRecord> dataset;

                public TipAdapter(Context context, List<TipRecord> dataset) {
                this.context = context;
                this.dataset = dataset;
            }

    @Override
    public ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.item_row, parent, false);
                        return new ViewHolder(view);
            }

                @Override
        public void onBindViewHolder(ViewHolder holder, int position) {
                TipRecord element = dataset.get(position);
                String strTip = String.format(context.getString(R.string.global_message_tip), element.getTip());
                holder.txtContent.setText(strTip);
            }

                @Override
        public int getItemCount() {
                return dataset.size();
            }

                public void add(TipRecord record) {
                dataset.add(0, record);
                notifyDataSetChanged();
            }

                public void clear() {
                dataset.clear();
                notifyDataSetChanged();
            }



        public static class ViewHolder extends RecyclerView.ViewHolder {
            @Bind(R.id.txtContent)
            TextView txtContent;

                            public ViewHolder(View itemView) {
                            super(itemView);
                            ButterKnife.bind(this, itemView);
                        }
        }
}