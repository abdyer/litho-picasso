package com.github.charbgr.litho_picasso_component_sample.lithography;

import android.support.v7.widget.OrientationHelper;
import com.facebook.litho.Component;
import com.facebook.litho.ComponentContext;
import com.facebook.litho.ComponentInfo;
import com.facebook.litho.widget.LinearLayoutInfo;
import com.facebook.litho.widget.RecyclerBinder;

public class PicassoArtist implements ArtistDatum {

  public final String name;
  public final String biography;
  public final int year;
  public final String[] images;

  public PicassoArtist(String name, String biography, int year, String... images) {
    this.name = name;
    this.biography = biography;
    this.year = year;
    this.images = images;
  }

  @Override
  public Component createComponent(ComponentContext c) {
    final RecyclerBinder imageRecyclerBinder =
        new RecyclerBinder(c, 4.0f, new LinearLayoutInfo(c, OrientationHelper.HORIZONTAL, false));

    for (String image : images) {
      ComponentInfo.Builder imageComponentInfoBuilder = ComponentInfo.create();
      imageComponentInfoBuilder.component(
          PicassoSingleImageComponent.create(c).image(image).fit(true).build());
      imageRecyclerBinder.insertItemAt(imageRecyclerBinder.getItemCount(),
          imageComponentInfoBuilder.build());
    }

    return FeedItemCard.create(c).artist(this).binder(imageRecyclerBinder).build();
  }

  @Override
  public String[] getImages() {
    return images;
  }

  @Override
  public String getBiography() {
    return biography;
  }

  @Override
  public String getName() {
    return name;
  }
}
