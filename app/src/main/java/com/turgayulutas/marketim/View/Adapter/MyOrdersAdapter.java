package com.turgayulutas.marketim.View.Adapter;

import android.annotation.SuppressLint;
import android.content.Context;
import android.graphics.Color;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.turgayulutas.marketim.Service.Models.MyOrdersDatum;
import com.turgayulutas.marketim.R;

import java.text.DateFormatSymbols;
import java.util.ArrayList;
import java.util.List;

/**
 * Created by turgayulutas on 22/09/2019.
 */

public class MyOrdersAdapter extends RecyclerView.Adapter {
    private ArrayList<Integer> openedPositions = new ArrayList<>();

    private List<MyOrdersDatum> myOrdersData;
    private LayoutInflater mInflater;
    private Context mContext;

    public MyOrdersAdapter(List<MyOrdersDatum> dummyData, Context context) {
        if (context == null)
            return;
        this.mContext = context;
        this.myOrdersData = dummyData;
        this.mInflater = (LayoutInflater) context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
    }

    @Override
    public RecyclerView.ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View vRoot = mInflater.inflate(R.layout.item_orders, parent, false);
        return new ViewHolder(vRoot);
    }

    @Override
    public int getItemCount() {
        return myOrdersData == null ? 0 : myOrdersData.size();
    }

    @SuppressLint("SetTextI18n")
    @Override
    public void onBindViewHolder(RecyclerView.ViewHolder holder, final int position) {
        final MyOrdersDatum currentModel = myOrdersData.get(position);
        final ViewHolder viewHolder = (ViewHolder) holder;

        /** null kontrollerinin yapılması ve datanın view'lara basılması*/
        if (currentModel.getMarketName() != null)
            viewHolder.mTv_orderName.setText(currentModel.getMarketName());

        if (currentModel.getDate() != null)
            viewHolder.mTv_orderDate.setText(currentModel.getDate());

        if (currentModel.getMonth() != null)
            viewHolder.mTv_orderMonth.setText(getMonth(Integer.parseInt(currentModel.getMonth())));

        if (currentModel.getProductPrice() != null)
            viewHolder.mTv_orderProductPrice.setText(currentModel.getProductPrice() + " ₺");

        if (currentModel.getProductState() != null)
            viewHolder.mTv_orderProductState.setText(currentModel.getProductState());

        if (currentModel.getOrderName() != null)
            viewHolder.mTv_orderProductName.setText(currentModel.getOrderName());

        if (currentModel.getProductDetail() != null && currentModel.getProductDetail().getOrderDetail() != null)
            viewHolder.mTv_productDetail.setText(currentModel.getProductDetail().getOrderDetail());

        if (currentModel.getProductDetail() != null && currentModel.getProductDetail().getSummaryPrice() != null)
            viewHolder.mTv_orderSummaryPrice.setText(currentModel.getProductDetail().getSummaryPrice() + " ₺");

        /** State türleri için renklendirme */
        viewHolder.mImg_productState.setBackgroundColor(
                currentModel.getProductState().equals("Yolda") ? mContext.getResources().getColor(R.color.state_on_the_way) :
                        currentModel.getProductState().equals("Hazırlanıyor") ? mContext.getResources().getColor(R.color.state_preparing) :
                                currentModel.getProductState().equals("Onay Bekliyor") ? mContext.getResources().getColor(R.color.state_pending_for_approval) :
                                        Color.BLACK);

        viewHolder.mTv_orderProductState.setTextColor(
                currentModel.getProductState().equals("Yolda") ? mContext.getResources().getColor(R.color.state_on_the_way) :
                        currentModel.getProductState().equals("Hazırlanıyor") ? mContext.getResources().getColor(R.color.state_preparing) :
                                currentModel.getProductState().equals("Onay Bekliyor") ? mContext.getResources().getColor(R.color.state_pending_for_approval) :
                                        Color.BLACK);


        /** Açılmış dataların scrool sonrasında state'ini koruması için açık positionların array içinde tutulması */
        viewHolder.mLy_orderRoot.setOnClickListener(v -> {
            if (viewHolder.mRl_productDetails.getVisibility() == View.GONE) {
                openedPositions.add(position);
            } else {
                openedPositions.remove((Object) position);
            }
            notifyDataSetChanged();
        });

        viewHolder.mRl_productDetails.setVisibility(openedPositions.contains(position) ? View.VISIBLE : View.GONE);
    }

    public class ViewHolder extends RecyclerView.ViewHolder {
        TextView mTv_orderName, mTv_productDetail, mTv_orderProductName, mTv_orderMonth, mTv_orderDate, mTv_orderProductPrice, mTv_orderProductState, mTv_orderSummaryPrice;
        LinearLayout mLy_orderRoot;
        ImageView mImg_productState;
        RelativeLayout mRl_productDetails;

        ViewHolder(View itemView) {
            super(itemView);
            mTv_orderName = itemView.findViewById(R.id.tv_itemOrderName);
            mTv_orderSummaryPrice = itemView.findViewById(R.id.tv_itemOrderSummaryPrice);
            mTv_orderProductState = itemView.findViewById(R.id.tv_itemOrderProductState);
            mTv_productDetail = itemView.findViewById(R.id.tv_itemProductDetail);
            mTv_orderProductName = itemView.findViewById(R.id.tv_itemOrderProductName);
            mTv_orderMonth = itemView.findViewById(R.id.tv_itemOrderMonth);
            mTv_orderDate = itemView.findViewById(R.id.tv_itemOrderDate);
            mTv_orderProductPrice = itemView.findViewById(R.id.tv_itemOrderProductPrice);
            mLy_orderRoot = itemView.findViewById(R.id.ly_itemOrderRoot);
            mImg_productState = itemView.findViewById(R.id.img_productState);
            mRl_productDetails = itemView.findViewById(R.id.rl_productDetails);
        }
    }

    private String getMonth(int month) {
        /** int tipinde gelen Ay formatının MMMM şeklinde alınması */
        return new DateFormatSymbols().getMonths()[month - 1];
    }
}