package com.ven.balu.common;

import android.content.Context;

import androidx.annotation.NonNull;

import com.bumptech.glide.Glide;
import com.ven.balu.R;

import de.hdodenhof.circleimageview.CircleImageView;

public class MyUtils {
    private static MyUtils mInstance;

    private static Context ctx;
    private MyUtils(Context context) {
        ctx = context.getApplicationContext();
    }
    public static synchronized MyUtils getInstance(Context context) {
        if (mInstance == null) {
            mInstance = new MyUtils(context);
        }
        return mInstance;
    }
    public void print (@NonNull String data) {
        System.out.println(data);
    }
    public void showCircleImageView (String imageUrl, CircleImageView id_ImageView) {
        Glide.with(ctx)
                .load(imageUrl)
                .placeholder(R.drawable.logo_balu_rmbg) // hiển thị ảnh tạm thời nếu tải thất bại
                .error(R.drawable.img_error) // hiển thị ảnh lỗi nếu không tải được ảnh
                .into(id_ImageView);
    }
}
