package org.charmeck.trailofhistory.core.ui.poi.list;

import android.content.Context;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v4.content.ContextCompat;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.AttributeSet;
import com.dgreenhalgh.android.simpleitemdecoration.linear.DividerItemDecoration;
import java.util.ArrayList;
import java.util.List;
import org.charmeck.trailofhistory.core.R;
import org.charmeck.trailofhistory.core.model.PointOfInterest;

public class PointOfInterestRecyclerView extends RecyclerView implements POIClickHandler {

  private PointOfInterestAdapter adapter;
  private POIClickHandler poiClickHandler;

  public PointOfInterestRecyclerView(Context context) {
    this(context, null);
  }

  public PointOfInterestRecyclerView(Context context, @Nullable AttributeSet attrs) {
    this(context, attrs, 0);
  }

  public PointOfInterestRecyclerView(Context context, @Nullable AttributeSet attrs, int defStyle) {
    super(context, attrs, defStyle);
    initView(context);
  }

  private void initView(Context context) {
    super.setLayoutManager(new LinearLayoutManager(context));
    super.addItemDecoration(
        new DividerItemDecoration(ContextCompat.getDrawable(context, R.drawable.simple_divider)));
    adapter = new PointOfInterestAdapter(new ArrayList<PointOfInterest>(), this);
    super.setAdapter(adapter);
  }

  public void addPointOfInterest(@NonNull PointOfInterest pointOfInterest) {
    adapter.addPointOfInterest(pointOfInterest);
    adapter.notifyDataSetChanged();
  }

  public void addPointsOfInterest(@NonNull List<PointOfInterest> pointsOfInterest) {
    for (PointOfInterest pointOfInterest : pointsOfInterest) {
      adapter.addPointOfInterest(pointOfInterest);
    }
    adapter.notifyDataSetChanged();
  }

  public void setPOIClickHandler(POIClickHandler poiClickHandler) {
    this.poiClickHandler = poiClickHandler;
  }

  @Override public void handlePOIClicked(PointOfInterest pointOfInterest) {
    if (poiClickHandler != null) {
      poiClickHandler.handlePOIClicked(pointOfInterest);
    }
  }
}
