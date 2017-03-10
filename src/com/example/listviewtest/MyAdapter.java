package com.example.listviewtest;

import java.util.ArrayList;
import java.util.LinkedList;
import android.content.Context;
import android.util.SparseArray;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.ListView;
import android.widget.TextView;

public abstract class MyAdapter<T> extends BaseAdapter {

    //private LinkedList<Data> mData;
    //修改传递的Entitiy为泛型
    private ArrayList<T> mData;
    private int mLayoutRes;           //布局id


    public MyAdapter() {
    }

    public MyAdapter(ArrayList<T> mData, int mLayoutRes) {
        this.mData = mData;
        this.mLayoutRes = mLayoutRes;
    }

    @Override
    public int getCount() {
    	return mData != null ? mData.size() : 0;
    }

    @Override
    public T getItem(int position) {
    	return mData.get(position);
    }

    @Override
    public long getItemId(int position) {
        return position;
    }

//    @Override
//    public View getView(int position, View convertView, ViewGroup parent) {
//        ViewHolder holder = null;
//        if(convertView == null){
//            convertView = LayoutInflater.from(mContext).inflate(R.layout.item_list,parent,false);
//            holder = new ViewHolder();
//            holder.img_icon = (ImageView) convertView.findViewById(R.id.img_icon);
//            holder.txt_content = (TextView) convertView.findViewById(R.id.txt_content);
//            convertView.setTag(holder);
//        }else{
//            holder = (ViewHolder) convertView.getTag();
//        }
//        holder.img_icon.setImageResource(((Data) mData.get(position)).getImgId());
//        holder.txt_content.setText(((Data) mData.get(position)).getContent());
//        return convertView;
//    }
    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        ViewHolder1 holder = ViewHolder1.bind(parent.getContext(), convertView, parent, mLayoutRes
                , position);
        bindView(holder, getItem(position));
        return holder.getItemView();
    }

    public abstract void bindView(ViewHolder1 holder, T obj);
    private class ViewHolder{
        ImageView img_icon;
        TextView txt_content;
    }
    public void add(T data) {
        if (mData == null) {
            mData = new ArrayList<T>();
        }
        mData.add(data);
        notifyDataSetChanged();
    }

    //往特定位置，添加一个元素
    public void add(int position, T data) {
        if (mData == null) {
            mData = new ArrayList<T>();
        }
        mData.add(position, data);
        notifyDataSetChanged();
    }
    public void remove(int position) {
        if(mData != null) {
            mData.remove(position);
        }
        notifyDataSetChanged();
    }
    public void clear() {
        if(mData != null) {
            mData.clear();
        }
        notifyDataSetChanged();
    }
    public void updateListItem(ListView list,int postion,Data mData){
        int visiblePosition = list.getFirstVisiblePosition();
        View v = list.getChildAt(postion - visiblePosition);
        ImageView img = (ImageView) v.findViewById(R.id.img_icon);
        TextView tv = (TextView) v.findViewById(R.id.txt_content);
        img.setImageResource(mData.getImgId());
        tv.setText(mData.getContent());
        
    }
    
    public static class ViewHolder1{
    	private SparseArray<View> mViews;	//存储ListView的item中的View
    	private View item;					//存放convertView
    	private int position;				//游标
    	private Context context;			//Context上下文
    	
    	private ViewHolder1(Context context,ViewGroup parent,int layoutRes){
    		mViews =new SparseArray<View>();
    		this.context = context;
    		View convertView = LayoutInflater.from(context).inflate(layoutRes, parent,false);
    		convertView.setTag(this);
    		item = convertView;
    	}
    	
    	ImageView img_icon;
    	TextView txt_content;
    	public static ViewHolder1 bind(Context context,View convertView,ViewGroup parent,int layoutRes,int position){
    		ViewHolder1 holder;
    		if(convertView==null){
    			holder = new ViewHolder1(context,parent,layoutRes);
    		}else{
    			holder = (ViewHolder1)convertView.getTag();
    			holder.item = convertView;
    		}
    		holder.position=position;
    		return holder;
    		
    	}
    	@SuppressWarnings("unchecked")
    	public <T extends View> T getView(int id){
    		T t = (T)mViews.get(id);
    		if(t == null ){
    			t = (T)item.findViewById(id);
    			mViews.put(id,t);
    		}
    		return t;
    	}
    	
    	public View getItemView(){
    		return item;
    	}
    	
    	public int getItemPosition(){
    		return position;
    	}
    	
    	public ViewHolder1 setText(int id,CharSequence text){
    		View view =getView(id);
    				if(view instanceof TextView){
    					((TextView)view).setText(text);
    				}
    		return this;
    	}
    	
    	public ViewHolder1 setImageResource(int id,int drawableRes){
    		View view = getView(id);
    		if(view instanceof ImageView){
    			((ImageView) view).setImageResource(drawableRes);
    		}else{
    			view.setBackgroundResource(drawableRes);
    		}
    		return this;
    	}
    	
    	public ViewHolder1 setOnClickListener(int id,View.OnClickListener listener){
    		getView(id).setOnClickListener(listener);
    		return this;
    	}
    	
    	public ViewHolder1 setVisibility(int id,int visible){
    		getView(id).setVisibility(visible);
    		return this;
    	}
    	
    	public ViewHolder1 setTag(int id,Object obj){
    		getView(id).setTag(obj);
    		return this;
    	}
    	
    	//public abstract void bindView(ViewHolder1 holder,T obj);
    	
    	
    }
    
}
