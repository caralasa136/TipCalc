package com.csantana.tipcalc.adapters;

import android.content.Context;
import android.database.Cursor;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;
import java.sql.Timestamp;
import java.util.*;
import java.util.jar.Attributes;

import com.csantana.tipcalc.R;
import com.csantana.tipcalc.db.TipsDatabase;
import com.csantana.tipcalc.entity.TipRecord;
import com.csantana.tipcalc.utils.TipUtils;
import com.raizlabs.android.dbflow.sql.language.Delete;
import com.raizlabs.android.dbflow.sql.language.OrderBy;
import com.raizlabs.android.dbflow.sql.language.Select;



import butterknife.Bind;
import butterknife.ButterKnife;

/**
 * Created by CLsantana on 16/10/16.
 */

public class TipAdapter extends RecyclerView.Adapter<TipAdapter.ViewHolder> {
    private Context context;
        private List<TipRecord> dataset;
        private OnItemClickListener onItemClickListener;

    public TipAdapter(Context context, List<TipRecord> dataset, OnItemClickListener onItemClickListener) {
                this.context = context;
                this.dataset = dataset;
        this.onItemClickListener = onItemClickListener;
            }

    public TipAdapter(Context context, OnItemClickListener onItemClickListener) {
        this.context = context;
        this.dataset = new ArrayList<TipRecord>();
        this.onItemClickListener = onItemClickListener;
    }

    @Override
    public ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.item_row, parent, false);
                        return new ViewHolder(view);
            }

                @Override
        public void onBindViewHolder(ViewHolder holder, int position) {
                TipRecord element = dataset.get(position);
                    String strTip = String.format(context.getString(R.string.global_message_tip), TipUtils.getTip(element));
                holder.txtContent.setText(strTip);
                    holder.setOnItemClickListener(element, onItemClickListener);
            }

                @Override
        public int getItemCount() {
                return dataset.size();
            }

    public void init(){
        dataset = new Select().from(TipRecord.class).queryList();
    }

                public void add(TipRecord record) {
                    //dataset.add(0, record);
                    record.save();
                    dataset = new Select().from(TipRecord.class).queryList();
                notifyDataSetChanged();
            }

                public void clear() {
                    dataset = new Delete().from(TipRecord.class).queryList();
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

            public void setOnItemClickListener(final TipRecord element, final OnItemClickListener onItemClickListener){
                itemView.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View view) {
                        onItemClickListener.onItemClick(element);
                    }
                });
            }

        }
}
