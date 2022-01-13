package com.yuanfen.main.adapter;

import android.content.Context;
import android.graphics.Paint;
import androidx.annotation.NonNull;
import  androidx.recyclerview.widget.RecyclerView;;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.yuanfen.common.adapter.RefreshAdapter;
import com.yuanfen.common.glide.ImgLoader;
import com.yuanfen.common.utils.StringUtil;
import com.yuanfen.common.utils.WordUtil;
import com.yuanfen.main.R;
import com.yuanfen.mall.activity.GoodsDetailActivity;
import com.yuanfen.mall.bean.GoodsSimpleBean;

public class MallClassAdapter extends RefreshAdapter<GoodsSimpleBean> {

    private static final int LEFT = 1;
    private static final int RIGHT = 2;
    private View.OnClickListener mOnClickListener;
    private String mSaleString;
    private String mMoneySymbol;

    public MallClassAdapter(Context context) {
        super(context);
        mSaleString = WordUtil.getString(R.string.mall_114);
        mMoneySymbol = WordUtil.getString(R.string.money_symbol);
        mOnClickListener = new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (!canClick()) {
                    return;
                }
                GoodsSimpleBean bean = (GoodsSimpleBean) v.getTag();
                GoodsDetailActivity.forward(mContext, bean.getId(), false, bean.getType());
            }
        };
    }


    @Override
    public int getItemViewType(int position) {
        if (position % 2 == 1) {
            return RIGHT;
        }
        return LEFT;
    }

    @NonNull
    @Override
    public RecyclerView.ViewHolder onCreateViewHolder(@NonNull ViewGroup viewGroup, int viewType) {
        if (viewType == LEFT) {
            return new Vh(mInflater.inflate(R.layout.item_shop_home_left, viewGroup, false));
        }
        return new Vh(mInflater.inflate(R.layout.item_shop_home_right, viewGroup, false));
    }

    @Override
    public void onBindViewHolder(@NonNull RecyclerView.ViewHolder vh, int position) {
        if (vh instanceof Vh) {
            ((Vh) vh).setData(mList.get(position));
        }
    }

    class Vh extends RecyclerView.ViewHolder {

        ImageView mThumb;
        TextView mName;
        TextView mPirce;
        TextView mSaleNum;
        TextView mOriginPrice;

        public Vh(@NonNull View itemView) {
            super(itemView);
            mThumb = itemView.findViewById(R.id.thumb);
            mName = itemView.findViewById(R.id.name);
            mPirce = itemView.findViewById(R.id.price);
            mSaleNum = itemView.findViewById(R.id.sale_num);
            mOriginPrice = itemView.findViewById(R.id.origin_price);
            mOriginPrice.getPaint().setFlags(Paint.STRIKE_THRU_TEXT_FLAG);
            itemView.setOnClickListener(mOnClickListener);
        }

        void setData(GoodsSimpleBean bean) {
            itemView.setTag(bean);
            ImgLoader.display(mContext, bean.getThumb(), mThumb);
            mName.setText(bean.getName());
            mPirce.setText(StringUtil.contact(mMoneySymbol, bean.getPrice()));
            if (bean.getType() == 1) {
                mSaleNum.setText(null);
                mOriginPrice.setText(bean.getOriginPrice());
            } else {
                mSaleNum.setText(String.format(mSaleString, bean.getSaleNum()));
                mOriginPrice.setText(null);
            }
        }

    }
}
