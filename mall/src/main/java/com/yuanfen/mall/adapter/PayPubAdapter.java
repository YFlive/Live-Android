package com.yuanfen.mall.adapter;

import android.content.Context;
import androidx.annotation.NonNull;
import  androidx.recyclerview.widget.RecyclerView;;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.yuanfen.common.adapter.RefreshAdapter;
import com.yuanfen.common.glide.ImgLoader;
import com.yuanfen.common.utils.RouteUtil;
import com.yuanfen.common.utils.WordUtil;
import com.yuanfen.mall.R;
import com.yuanfen.mall.bean.PayContentBean;

public class PayPubAdapter extends RefreshAdapter<PayContentBean> {

    private String mSaleNumString;
    private String mShenHeString0;
    private String mShenHeString1;
    private View.OnClickListener mOnClickListener;

    public PayPubAdapter(Context context) {
        super(context);
        mSaleNumString = WordUtil.getString(R.string.mall_341);
        mShenHeString0 = WordUtil.getString(R.string.mall_117);
        mShenHeString1 = WordUtil.getString(R.string.mall_342);
        mOnClickListener = new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                PayContentBean bean = (PayContentBean) v.getTag();
                RouteUtil.forwardPayContentDetail(mContext, bean.getId());
            }
        };
    }

    @NonNull
    @Override
    public RecyclerView.ViewHolder onCreateViewHolder(@NonNull ViewGroup viewGroup, int i) {
        return new Vh(mInflater.inflate(R.layout.item_pay_pub, viewGroup, false));
    }

    @Override
    public void onBindViewHolder(@NonNull RecyclerView.ViewHolder vh, int i) {
        ((Vh) vh).setData(mList.get(i));
    }

    class Vh extends RecyclerView.ViewHolder {

        ImageView mThumb;
        TextView mTitle;
        TextView mNum;
        TextView mPrice;
        TextView mStatus;
        TextView mSaleNum;

        public Vh(@NonNull View itemView) {
            super(itemView);
            mThumb = itemView.findViewById(R.id.thumb);
            mTitle = itemView.findViewById(R.id.title);
            mNum = itemView.findViewById(R.id.num);
            mPrice = itemView.findViewById(R.id.price);
            mStatus = itemView.findViewById(R.id.status);
            mSaleNum = itemView.findViewById(R.id.sale_num);
            itemView.setOnClickListener(mOnClickListener);
        }

        void setData(PayContentBean bean) {
            itemView.setTag(bean);
            ImgLoader.display(mContext, bean.getThumb(), mThumb);
            mTitle.setText(bean.getTitle());
            mNum.setText(bean.getVideoNum());
            mPrice.setText(bean.getMoney());
            if (bean.getStatus() == 1) {
                mStatus.setText("");
                mSaleNum.setText(String.format(mSaleNumString, bean.getSaleNum()));
            } else {
                mStatus.setText(bean.getStatus() == 0 ? mShenHeString0 : mShenHeString1);
                mSaleNum.setText("");
            }
        }
    }
}
