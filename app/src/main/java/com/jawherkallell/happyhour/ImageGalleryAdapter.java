package com.jawherkallell.happyhour;

import android.content.Context;
import android.content.Intent;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;

import com.jawherkallell.happyhour.Json.model.Photos;
import com.squareup.picasso.Picasso;

public class ImageGalleryAdapter extends RecyclerView.Adapter<ImageGalleryAdapter.MyViewHolder>{
    Photos[] photos;

    private Context mContext;


    public ImageGalleryAdapter(Context context, Photos[] photos) {
        mContext = context;
        this.photos=photos;

    }

    @Override
    public ImageGalleryAdapter.MyViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {

        Context context = parent.getContext();
        LayoutInflater inflater = LayoutInflater.from(context);
        View photoView = inflater.inflate(R.layout.item_image, parent, false);
        ImageGalleryAdapter.MyViewHolder viewHolder = new ImageGalleryAdapter.MyViewHolder(photoView);
        return viewHolder;
    }

    @Override
    public void onBindViewHolder(ImageGalleryAdapter.MyViewHolder holder, int position) {

        Photos photo = photos[position];
        ImageView imageView = holder.mPhotoImageView;
        Picasso.with(mContext)
                .load(getPhotoOfPlace(photo.getPhoto_reference(), 1000))
                .placeholder(R.drawable.ic_image_black_24dp)
                .error(R.drawable.ic_error_black_24dp)
                .into(imageView);
    }

    @Override
    public int getItemCount() {
        return (photos.length);
    }

    public class MyViewHolder extends RecyclerView.ViewHolder implements View.OnClickListener {

        public ImageView mPhotoImageView;

        public MyViewHolder(View itemView) {

            super(itemView);
            mPhotoImageView = (ImageView) itemView.findViewById(R.id.iv_photo);
            itemView.setOnClickListener(this);
        }

        @Override
        public void onClick(View view) {

            int position = getAdapterPosition();
            if(position != RecyclerView.NO_POSITION) {
                Photos photo =photos[position];
                // Intent intent = new Intent(mContext, SpacePhotoActivity.class);
                //  intent.putExtra(SpacePhotoActivity.EXTRA_SPACE_PHOTO, spacePhoto);
                //   startActivity(intent);
            }
        }
    }
    public String getPhotoOfPlace(String photos_reference, int maxWidth) {
        StringBuilder url = new StringBuilder("https://maps.googleapis.com/maps/api/place/photo");
        url.append("?maxwidth="+maxWidth);
        url.append("&photoreference="+photos_reference);
        url.append("&key="+mContext.getResources().getString(R.string.browser_service));
        return url.toString();

    }


}

