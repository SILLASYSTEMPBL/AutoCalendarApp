package com.cookandroid.calendar;

import android.app.Activity;
import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.TextView;
import android.widget.Toast;

import java.util.ArrayList;

public class ListViewAdapter extends BaseAdapter {

    private ArrayList<String> list;
    private Activity activity;


    public void Clear(){
        list.clear();
    }
    // 생성할 클래스
    ListViewAdapter(Activity activity){
        this.activity = activity;
        list = new ArrayList<String>();
    }
    // 리스트에 값을 추가할 메소드
    public void setTitle(String title)
    {
        list.add(title);
    }

    @Override
    public int getCount() {

        return list.size();
    }

    @Override
    public Object getItem(int position) {
        return  list.get(position);
    }

    @Override
    public long getItemId(int position) {
        return  position;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        ListViewHolder    holder  = null;
        final int pos = position;
        TextView title;

        
        // 최초 뷰 생성
        if(convertView == null)
        {
            LayoutInflater inflater = (LayoutInflater) activity.getApplicationContext().getSystemService(Context.LAYOUT_INFLATER_SERVICE);

            convertView = inflater.inflate(R.layout.listview_item, parent, false);
            title    = (TextView) convertView.findViewById(R.id.itemTitle);

            holder = new ListViewHolder();
            holder.calendar = title;

            // list values save
            convertView.setTag(holder);
            // 텍스트 보이기
            title.setVisibility(View.VISIBLE);
        }
        else
        {
            // list values get
            holder = (ListViewHolder) convertView.getTag();
            title = holder.calendar;
        }

        title.setText(list.get(pos));

        // 리스트 아이템을 터치 했을 때 이벤트 발생
        convertView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Toast.makeText(activity.getApplicationContext(), "선택한 이름:" + list.get(pos), Toast.LENGTH_SHORT).show();
            }
        });

        return convertView;


    }

    // list values class
    private class ListViewHolder {

        TextView calendar;
    }




}


