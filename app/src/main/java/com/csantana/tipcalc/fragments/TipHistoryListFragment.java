package com.csantana.tipcalc.fragments;

import android.content.Intent;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.csantana.tipcalc.R;
import com.csantana.tipcalc.activities.TipDetailActivity;
import com.csantana.tipcalc.adapters.OnItemClickListener;
import com.csantana.tipcalc.adapters.TipAdapter;
import com.csantana.tipcalc.entity.TipRecord;
import com.csantana.tipcalc.utils.TipUtils;

import butterknife.Bind;
import butterknife.ButterKnife;

/**
 * Created by CLsantana on 13/10/16.
 */

public class TipHistoryListFragment extends Fragment implements TipHistoryListFragmentListener, OnItemClickListener {
    @Bind(R.id.recyclerView)
        RecyclerView recyclerView;

    TipAdapter adapter;


    public TipHistoryListFragment() {
                // Required empty public constructor
    }


                @Override
                public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
                // Inflate the layout for this fragment
                    View view = inflater.inflate(R.layout.fragment_tip_history_list, container, false);
                            ButterKnife.bind(this, view);
                            initAdapter();
                            initRecyclerView();
                            return view;

                }


                private void initAdapter() {
                if(adapter == null) {
                        adapter = new TipAdapter(getActivity().getApplicationContext(), this);
                    }
            }

                private void initRecyclerView() {
                recyclerView.setLayoutManager(new LinearLayoutManager(getActivity()));
                recyclerView.setAdapter(adapter);
            }

    @Override
    public void addToList(TipRecord record) {
        adapter.add(record);
    }

    @Override
    public void clearList() {
        adapter.clear();

    }

    @Override
    public void onItemClick(TipRecord tipRecord) {
        Intent intent = new Intent(getActivity(), TipDetailActivity.class);
        intent.putExtra(TipDetailActivity.TIP_KEY, TipUtils.getTip(tipRecord));
        intent.putExtra(TipDetailActivity.BILL_TOTAL_KEY, tipRecord.getBill());
        intent.putExtra(TipDetailActivity.DATE_KEY, TipUtils.getDateFormated(tipRecord));
        startActivity(intent);
    }
}
