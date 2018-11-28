package com.jawherkallell.happyhour;

import android.content.Context;
import android.support.v4.view.PagerAdapter;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.RelativeLayout;

import com.jawherkallell.happyhour.Json.model.Photos;
import com.squareup.picasso.Picasso;

public class CustomSwipeAdapter extends PagerAdapter {
    private Photos[] photos;
    private Context cntx;
    private LayoutInflater layoutinflater;

    public CustomSwipeAdapter(Photos[] photos, Context cntx) {
        this.photos = photos;
        this.cntx = cntx;
    }

    @Override
    public int getCount() {
        return photos.length;    }

    @Override
    public boolean isViewFromObject(View view, Object object) {
        return (view==(LinearLayout)object);
    }

    @Override
    public Object instantiateItem(ViewGroup container, int position) {
        layoutinflater = (LayoutInflater)cntx.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
        View item_view=layoutinflater.inflate(R.layout.swipe_layout,container,false);
        ImageView img= (ImageView)item_view.findViewById(R.id.ad_image_view);
        if(photos[position]!=null) {
            Picasso.with(cntx)
                    .load(getPhotoOfPlace(photos[position].getPhoto_reference(), 1000))
                    .placeholder(R.drawable.ic_image_black_24dp)
                    .error(R.drawable.ic_error_black_24dp)
                    .into(img);
        }
        container.addView(item_view);
        return item_view;
    }

    @Override
    public void destroyItem(ViewGroup container, int position, Object object) {
        container.removeView((LinearLayout)object);

    }
    public String getPhotoOfPlace(String photos_reference, int maxWidth) {
        StringBuilder url = new StringBuilder("https://maps.googleapis.com/maps/api/place/photo");
        url.append("?maxwidth="+maxWidth);
        url.append("&photoreference="+photos_reference);
        url.append("&key="+cntx.getResources().getString(R.string.browser_service));
        return url.toString();

    }
}
