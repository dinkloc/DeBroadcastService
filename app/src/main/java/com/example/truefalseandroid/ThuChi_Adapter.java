package com.example.truefalseandroid;

import android.content.Context;
import android.graphics.Color;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.Filter;
import android.widget.Filterable;
import android.widget.TextView;

import java.util.ArrayList;
import java.util.List;
import java.util.zip.Inflater;

public class ThuChi_Adapter extends BaseAdapter implements Filterable {
    private Context context;
    private List<ThuChi> listDataOld;
    private Filter filter;
    private int layout;
    private List<ThuChi> listData;

    public ThuChi_Adapter(Context context, int layout, List<ThuChi> listData) {
        this.context = context;
        this.layout = layout;
        this.listData = listData;
        this.listDataOld = listData;
    }

    @Override
    public int getCount() {
        return listData.size();
    }

    @Override
    public Object getItem(int position) {
        return listData.get(position);
    }

    @Override
    public long getItemId(int position) {
        return position;
    }


    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        convertView = LayoutInflater.from(context).inflate(layout, parent, false);
        TextView thuOrChi = convertView.findViewById(R.id.tvThuChi);
        TextView ngay = convertView.findViewById(R.id.tvNgay);
        TextView LoaithuChi = convertView.findViewById(R.id.tvLoaiThuChi);
        TextView soTien = convertView.findViewById(R.id.tvSoTien);

        ThuChi thuChi = listData.get(position);
        if (thuChi.getThuChi() == 1) {
            thuOrChi.setText("Thu");
            convertView.setBackgroundColor(Color.GREEN);
        } else {
            thuOrChi.setText("Chi");
        }
        ngay.setText( " " +  thuChi.getNgayThang());
        LoaithuChi.setText( "" + thuChi.getTenKhoan());
        soTien.setText( "" + Double.parseDouble(thuChi.getSoTien()));
        return convertView;
    }



    public Filter getFilter() {
        if (filter == null) {
            filter = new MyFilter();
        }
        return filter;
    }
    private class MyFilter extends Filter {

        @Override
        protected FilterResults performFiltering(CharSequence constraint) {
            String keySearch = constraint.toString();
            if (keySearch.isEmpty()) {
                listData = listDataOld;
            } else {
                int sum = Integer.parseInt(keySearch);
                List<ThuChi> listSearch = new ArrayList<>();
                for (ThuChi soTien : listDataOld) {
                    if (Double.parseDouble(soTien.getSoTien().toString()) == sum) {
                        listSearch.add(soTien);
                    }
                }
                listData = listSearch;
            }
            FilterResults results = new FilterResults();
            results.values = listData;
            return results;
        }

        @Override
        protected void publishResults(CharSequence constraint, FilterResults results) {
            listData = (List<ThuChi>) results.values;
            notifyDataSetChanged();
        }
    }
}
