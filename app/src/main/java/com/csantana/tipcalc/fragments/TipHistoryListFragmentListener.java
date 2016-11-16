package com.csantana.tipcalc.fragments;

import com.csantana.tipcalc.entity.TipRecord;

/**
 * Created by CLsantana on 13/10/16.
 */

public interface TipHistoryListFragmentListener {
        void initList();
        void addToList(TipRecord record);
        void clearList();
}
