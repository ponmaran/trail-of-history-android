package org.charmeck.trailofhistory.poi;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;
import butterknife.BindView;
import butterknife.ButterKnife;
import com.squareup.picasso.Picasso;
import java.util.List;
import jp.wasabeef.picasso.transformations.BlurTransformation;
import org.charmeck.trailofhistory.R;
import org.charmeck.trailofhistory.core.model.PointOfInterest;

/**
 * Created by Trey Robinson on 3/8/16.
 */
public class PointOfInterestAdapter
    extends RecyclerView.Adapter<PointOfInterestAdapter.ViewHolder> {

  private List<PointOfInterest> pointOfInterestList;

  static class ViewHolder extends RecyclerView.ViewHolder {

    @BindView(R.id.poiName) TextView name;
    @BindView(R.id.poiDescription) TextView description;
    @BindView(R.id.poiImage) ImageView image;

    public ViewHolder(View itemView) {
      super(itemView);
      ButterKnife.bind(this, itemView);
    }
  }

  public PointOfInterestAdapter(List<PointOfInterest> pointOfInterestList) {
    this.pointOfInterestList = pointOfInterestList;
  }

  public void addPointOfIntrest(PointOfInterest pointOfInterest) {
    pointOfInterestList.add(pointOfInterest);
  }

  @Override public ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {

    View v = LayoutInflater.from(parent.getContext())
        .inflate(R.layout.row_point_of_interest, parent, false);
    return new ViewHolder(v);
  }

  @Override public void onBindViewHolder(ViewHolder holder, int position) {
    PointOfInterest poi = pointOfInterestList.get(position);
    holder.name.setText(poi.getName());
    // TODO set calculated distance
    //        holder.description.setText(pointOfInterestList.get(position).description);
    Context context = holder.image.getContext();
    // TODO udpate blur params after getting correctly sized images in Firebase
    Picasso.with(context)
        .load(poi.getImageUrl())
        .transform(new BlurTransformation(context, 2, 2))
        .into(holder.image);
  }

  @Override public int getItemCount() {
    return pointOfInterestList.size();
  }
}