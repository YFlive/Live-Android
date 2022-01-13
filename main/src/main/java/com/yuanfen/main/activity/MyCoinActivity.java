package com.yuanfen.main.activity;

import android.content.Intent;
import android.net.Uri;
import androidx.swiperefreshlayout.widget.SwipeRefreshLayout;
import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.LinearLayoutManager;
import  androidx.recyclerview.widget.RecyclerView;
import androidx.swiperefreshlayout.widget.SwipeRefreshLayout;
import android.text.TextUtils;
import android.util.Log;
import android.view.View;
import android.widget.TextView;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONArray;
import com.alibaba.fastjson.JSONObject;
import com.google.gson.JsonObject;
import com.yuanfen.common.CommonAppConfig;
import com.yuanfen.common.Constants;
import com.yuanfen.common.HtmlConfig;
import com.yuanfen.common.activity.AbsActivity;
import com.yuanfen.common.activity.WebViewActivity;
import com.yuanfen.common.bean.CoinBean;
import com.yuanfen.common.bean.CoinPayBean;
import com.yuanfen.common.bean.UserItemBean;
import com.yuanfen.common.custom.ItemDecoration;
import com.yuanfen.common.event.CoinChangeEvent;
import com.yuanfen.common.http.CommonHttpConsts;
import com.yuanfen.common.http.CommonHttpUtil;
import com.yuanfen.common.http.HttpCallback;
import com.yuanfen.common.interfaces.OnItemClickListener;
import com.yuanfen.common.pay.PayCallback;
import com.yuanfen.common.pay.PayPresenter;
import com.yuanfen.common.utils.SpUtil;
import com.yuanfen.common.utils.StringUtil;
import com.yuanfen.common.utils.ToastUtil;
import com.yuanfen.common.utils.WordUtil;
import com.yuanfen.main.R;
import com.yuanfen.main.adapter.CoinAdapter;
import com.yuanfen.main.adapter.CoinPayAdapter;

import org.greenrobot.eventbus.EventBus;
import org.greenrobot.eventbus.Subscribe;
import org.greenrobot.eventbus.ThreadMode;
import org.json.JSONException;

import java.io.IOException;
import java.io.UnsupportedEncodingException;
import java.util.Calendar;
import java.util.Date;
import java.util.List;

import cn.jmessage.support.okhttp3.Call;
import cn.jmessage.support.okhttp3.Callback;
import cn.jmessage.support.okhttp3.MediaType;
import cn.jmessage.support.okhttp3.OkHttpClient;
import cn.jmessage.support.okhttp3.Request;
import cn.jmessage.support.okhttp3.RequestBody;
import cn.jmessage.support.okhttp3.Response;

import static com.tencent.qcloud.core.http.HttpConstants.Header.MD5;

/**
 * Created by cxf on 2018/10/23.
 * 充值
 */
public class MyCoinActivity extends AbsActivity implements View.OnClickListener {

    private SwipeRefreshLayout mRefreshLayout;
    private RecyclerView mRecyclerView;
    private RecyclerView mPayRecyclerView;
    private CoinAdapter mAdapter;
    private CoinPayAdapter mPayAdapter;
    private TextView mBalance;
    private long mBalanceValue;
    private boolean mFirstLoad = true;
    private PayPresenter mPayPresenter;
    private String mCoinName;
    //    private TextView mTip1;
//    private TextView mTip2;
    private TextView mCoin2;
    private TextView mBtnCharge;
    private TextView mBtnArtificialCharge;
    private boolean mIsPayPal;


    @Override
    protected int getLayoutId() {
        return R.layout.activity_coin;
    }

    @Override
    protected void main() {
        setTitle(WordUtil.getString(R.string.wallet));
//        mTip1 = findViewById(R.id.tip_1);
//        mTip2 = findViewById(R.id.tip_2);
        mBtnCharge = findViewById(R.id.btn_charge);
        mBtnCharge.setOnClickListener(this);
        mBtnArtificialCharge = findViewById(R.id.btn_artificial_charge);
        mBtnArtificialCharge.setOnClickListener(this);
        mCoin2 = findViewById(R.id.coin_2);
        mRefreshLayout = findViewById(R.id.refreshLayout);
        mRefreshLayout.setColorSchemeResources(R.color.blue3);
        mRefreshLayout.setOnRefreshListener(new SwipeRefreshLayout.OnRefreshListener() {
            @Override
            public void onRefresh() {
                loadData();
            }
        });
        mCoinName = CommonAppConfig.getInstance().getCoinName();
        mBalance = findViewById(R.id.coin);
        TextView coinNameTextView = findViewById(R.id.coin_name);
        coinNameTextView.setText(String.format(WordUtil.getString(R.string.wallet_coin_name), mCoinName));
        TextView scoreName = findViewById(R.id.score_name);
        scoreName.setText(String.format(WordUtil.getString(R.string.wallet_coin_name), CommonAppConfig.getInstance().getScoreName()));
        mBtnArtificialCharge.setText("人工充值");
        mRecyclerView = findViewById(R.id.recyclerView);
        mRecyclerView.setLayoutManager(new GridLayoutManager(mContext, 3, GridLayoutManager.VERTICAL, false));
        ItemDecoration decoration = new ItemDecoration(mContext, 0x00000000, 5, 10);
        decoration.setOnlySetItemOffsetsButNoDraw(true);
        mRecyclerView.addItemDecoration(decoration);
        mAdapter = new CoinAdapter(mContext, mCoinName);
        mAdapter.setOnItemClickListener(new OnItemClickListener<CoinBean>() {
            @Override
            public void onItemClick(CoinBean bean, int position) {
                if (bean != null && mBtnCharge != null) {
                    String money = StringUtil.contact(mIsPayPal ? "$" : "￥", bean.getMoney());
                    mBtnCharge.setText(String.format(WordUtil.getString(R.string.chat_charge_tip), money));
                }
            }
        });
//        mAdapter.setContactView(findViewById(R.id.top));
        mRecyclerView.setAdapter(mAdapter);
        findViewById(R.id.btn_tip).setOnClickListener(this);
//        View headView = mAdapter.getHeadView();
        mPayRecyclerView = findViewById(R.id.pay_recyclerView);
        ItemDecoration decoration2 = new ItemDecoration(mContext, 0x00000000, 14, 10);
        decoration2.setOnlySetItemOffsetsButNoDraw(true);
        mPayRecyclerView.addItemDecoration(decoration2);
        mPayRecyclerView.setLayoutManager(new LinearLayoutManager(mContext, LinearLayoutManager.VERTICAL, false));
        mPayAdapter = new CoinPayAdapter(mContext);
        mPayAdapter.setOnItemClickListener(new OnItemClickListener<CoinPayBean>() {
            @Override
            public void onItemClick(CoinPayBean bean, int position) {
                boolean isPayPal = Constants.PAY_TYPE_PAYPAL.equals(bean.getId());
                if (mIsPayPal != isPayPal) {
                    if (mAdapter != null) {
                        CoinBean coinBean = mAdapter.getCheckedBean();
                        if (coinBean != null && mBtnCharge != null) {
                            String money = StringUtil.contact(isPayPal ? "$" : "￥", coinBean.getMoney());
                            mBtnCharge.setText(String.format(WordUtil.getString(R.string.chat_charge_tip), money));
                        }
                    }
                }
                mIsPayPal = isPayPal;
                if (mAdapter != null) {
                    mAdapter.setIsPaypal(isPayPal);
                }
            }
        });
        mPayRecyclerView.setAdapter(mPayAdapter);
        mPayPresenter = new PayPresenter(this);
        mPayPresenter.setServiceNameAli(Constants.PAY_BUY_COIN_ALI);
        mPayPresenter.setServiceNameWx(Constants.PAY_BUY_COIN_WX);
        mPayPresenter.setServiceNamePaypal(Constants.PAY_BUY_COIN_PAYPAL);
        mPayPresenter.setAliCallbackUrl(HtmlConfig.ALI_PAY_COIN_URL);
        mPayPresenter.setPayCallback(new PayCallback() {
            @Override
            public void onSuccess() {
                if (mPayPresenter != null) {
                    mPayPresenter.checkPayResult();
                }
            }

            @Override
            public void onFailed() {

            }
        });
        EventBus.getDefault().register(this);
    }

    @Override
    protected void onResume() {
        super.onResume();
        if (mFirstLoad) {
            mFirstLoad = false;
            loadData();
        }
    }

    private void loadData() {
        CommonHttpUtil.getBalance(new HttpCallback() {
            @Override
            public void onSuccess(int code, String msg, String[] info) {
                if (code == 0 && info.length > 0) {
                    JSONObject obj = JSON.parseObject(info[0]);
                    String coin = obj.getString("coin");
                    mBalanceValue = Long.parseLong(coin);
                    mBalance.setText(coin);
//                    mTip1.setText(obj.getString("tip_t"));
//                    mTip2.setText(obj.getString("tip_d"));
                    mCoin2.setText(obj.getString("score"));
                    List<CoinPayBean> payList = JSON.parseArray(obj.getString("paylist"), CoinPayBean.class);
                    CoinPayBean okPay = new CoinPayBean();
                    okPay.setName("OkPay");
                    payList.add(okPay);
                    if (mPayAdapter != null) {
                        mPayAdapter.setList(payList);
                    }
                    List<CoinBean> list = JSON.parseArray(obj.getString("rules"), CoinBean.class);
                    if (mAdapter != null) {
                        mAdapter.setList(list);
                        if (payList != null && payList.size() > 0) {
                            mIsPayPal = Constants.PAY_TYPE_PAYPAL.equals(payList.get(0).getId());
                            mAdapter.setIsPaypal(mIsPayPal);
                        }
                        CoinBean coinBean = mAdapter.getCheckedBean();
                        if (coinBean != null && mBtnCharge != null) {
                            String money = StringUtil.contact(mIsPayPal ? "$" : "￥", coinBean.getMoney());
                            mBtnCharge.setText(String.format(WordUtil.getString(R.string.chat_charge_tip), money));
                        }
                    }
                    if (mPayPresenter != null) {
                        mPayPresenter.setBalanceValue(mBalanceValue);
                        mPayPresenter.setAliPartner(obj.getString("aliapp_partner"));
                        mPayPresenter.setAliSellerId(obj.getString("aliapp_seller_id"));
                        mPayPresenter.setAliPrivateKey(obj.getString("aliapp_key_android"));
                        mPayPresenter.setWxAppID(obj.getString("wx_appid"));
                    }
                }
            }

            @Override
            public void onFinish() {
                if (mRefreshLayout != null) {
                    mRefreshLayout.setRefreshing(false);
                }
            }
        });
    }

    /**
     * 充值
     */
    private void charge() {
        if (mAdapter == null) {
            return;
        }
        if (mPayPresenter == null) {
            return;
        }
        if (mPayAdapter == null) {
            return;
        }
        CoinBean bean = mAdapter.getCheckedBean();
        if (bean == null) {
            return;
        }
        CoinPayBean coinPayBean = mPayAdapter.getPayCoinPayBean();
        if (coinPayBean == null) {
            ToastUtil.show(R.string.wallet_tip_5);
            return;
        }
        String href = coinPayBean.getHref();
        if (coinPayBean.getName() == "OkPay") {
            OkHttpClient client = new OkHttpClient().newBuilder().build();
            Date currentTime = Calendar.getInstance().getTime();
            String money = bean.getMoney();
            try {
                MediaType JSON = MediaType.parse("application/json;charset=utf-8");
                org.json.JSONObject json = new org.json.JSONObject();
                String recvid = "d3bd5ec4-570c-4cae-ad12-21734a3ef882";
                String apiKey = "10381d74fb3a4587965c471f386d97ef";
                json.put("recvid", recvid);
                json.put("orderid", currentTime.toString());
                json.put("amount", money);
                json.put("sign", MD5(recvid+currentTime.toString()+money+apiKey));
                Request request = new Request.Builder()
                        .url("https://jw3hxe429q.okpay777.com/createpay")
                        .post(RequestBody.create(JSON, String.valueOf(json)))
                        .build();
                client.newCall(request).enqueue(new Callback() {
                    @Override
                    public void onFailure(Call call, IOException e) {

                    }

                    @Override
                    public void onResponse(Call call, Response response) throws IOException {
                        try {
                            String string = response.body().string();
                            org.json.JSONObject obj = new org.json.JSONObject(string);
                            String data = obj.get("data").toString();
                            org.json.JSONObject dataObj = new org.json.JSONObject(data);
                            Intent intent = new Intent();
                            intent.setAction(Intent.ACTION_VIEW);
                            intent.setData(Uri.parse(dataObj.getString("navurl")));
                            mContext.startActivity(intent);
                        } catch (JSONException e) {
                            e.printStackTrace();
                        }
                    }
                });
            } catch (JSONException e) {
                e.printStackTrace();
            }
        } else if (TextUtils.isEmpty(href)) {
            String money = bean.getMoney();
            String coin = Constants.PAY_TYPE_PAYPAL.equals(coinPayBean.getId()) ? bean.getCoinPaypal() : bean.getCoin();
            String goodsName = StringUtil.contact(coin, mCoinName);
            String orderParams = StringUtil.contact(
                    "&uid=", CommonAppConfig.getInstance().getUid(),
                    "&token=", CommonAppConfig.getInstance().getToken(),
                    "&money=", money,
                    "&changeid=", bean.getId(),
                    "&coin=", coin);
            mPayPresenter.pay(coinPayBean.getId(), money, goodsName, orderParams);
        } else {
            Intent intent = new Intent();
            intent.setAction(Intent.ACTION_VIEW);
            intent.setData(Uri.parse(href));
            mContext.startActivity(intent);
        }
    }

    public String MD5(String md5) {
        try {
            java.security.MessageDigest md = java.security.MessageDigest.getInstance("MD5");
            byte[] array = md.digest(md5.getBytes("UTF-8"));
            StringBuffer sb = new StringBuffer();
            for (int i = 0; i < array.length; ++i) {
                sb.append(Integer.toHexString((array[i] & 0xFF) | 0x100).substring(1,3));
            }
            return sb.toString();
        } catch (java.security.NoSuchAlgorithmException e) {
        } catch(UnsupportedEncodingException ex){
        }
        return null;
    }


    @Subscribe(threadMode = ThreadMode.MAIN)
    public void onCoinChangeEvent(CoinChangeEvent e) {
        if (mBalance != null) {
            mBalance.setText(e.getCoin());
        }
    }

    @Override
    public void onClick(View v) {
        int i = v.getId();
        if (i == R.id.btn_tip) {
            WebViewActivity.forward(mContext, HtmlConfig.CHARGE_PRIVCAY);
        } else if (i == R.id.btn_charge) {
            charge();
        } else if (i == R.id.btn_artificial_charge) {
            String userBeanJson = SpUtil.getInstance().getStringValue(SpUtil.USER_INFO);
            JSONObject obj = JSON.parseObject(userBeanJson);
            JSONArray arr = obj.getJSONArray("list");
            JSONObject obj2 = arr.getJSONObject(1);
            List<UserItemBean> list = JSON.parseArray(obj2.getString("list"), UserItemBean.class);
            WebViewActivity.forward(mContext, list.get(4).getHref());
        }
    }

    @Override
    protected void onDestroy() {
        EventBus.getDefault().unregister(this);
        CommonHttpUtil.cancel(CommonHttpConsts.GET_BALANCE);
        CommonHttpUtil.cancel(CommonHttpConsts.GET_ALI_ORDER);
        CommonHttpUtil.cancel(CommonHttpConsts.GET_WX_ORDER);
        if (mRefreshLayout != null) {
            mRefreshLayout.setOnRefreshListener(null);
        }
        mRefreshLayout = null;
        if (mPayPresenter != null) {
            mPayPresenter.release();
        }
        mPayPresenter = null;
        super.onDestroy();
    }

}
