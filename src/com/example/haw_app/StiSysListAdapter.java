package com.example.haw_app;

import java.util.List;

import android.content.Context;
import android.graphics.Color;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.TextView;

public class StiSysListAdapter extends ArrayAdapter{
	
	private Context mContext;
	private int id;
	private List<String> items;
	private int textColour = Color.WHITE,bgColor = Color.BLACK;
	float fontSize = 14, captionFontSize = 22;
	/**
	 * @param list Eine Liste deren Elemente auf der Liste dargestellt werden. Ueberschriften werden mit "<h>" am Anfang markiert.
	 */
	public StiSysListAdapter(Context context, int textViewResourceId , List<String> list) 
    {
        super(context, textViewResourceId, list);           
        mContext = context;
        id = textViewResourceId;
        items = list ;
    }
	
	@Override
    public View getView(int position, View v, ViewGroup parent)
    {
        View mView = v ;
        if(mView == null){
            LayoutInflater vi = (LayoutInflater)mContext.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
            mView = vi.inflate(id, null);
        }

        TextView text;
        java.lang.String str = items.get(position);
//        String str = items.get(position);
        
        if(str != null )
        {
        	if (str.startsWith("<h>"))
        	{
        		str = str.substring(3, str.length());
        		text = (TextView) mView.findViewById(R.id.myTextField);
        		text.setTextSize(captionFontSize);
        		text.setTextColor(textColour);
        		text.setBackgroundColor(bgColor);
        	}else{
        		text = (TextView) mView.findViewById(R.id.myTextField);
        		text.setTextSize(fontSize);
        		text.setTextColor(textColour);
        		text.setBackgroundColor(bgColor);
        	}
//		        text.setTextColor(textColour);
//		        text.setText((CharSequence)items.get(position));
		        text.setText((CharSequence)str);
//		        text.setBackgroundColor(bgColor);
		        
//	            int color = Color.argb( 200, 255, 64, 64 );
//	                text.setBackgroundColor( color );
        	
        }        
        return mView;
    }

	/**
	 * setzt die Textfarbe fuer die Liste.
	 * @param color ist eine Konstante aus android.graphics.Color
	 */
	public void setTextColor(int color)
	{
		this.textColour = color;
	}
	/**
	 * setzt die Hintergrundfarbe fuer die Liste 
	 * @param color ist eine Konstante aus android.graphics.Color
	 */
	public void setBGColor(int color)
	{
		this.bgColor = color;
	}

	/**
	 * setzt die Schriftgroeße fuer die Listenelemente.
	 * @param size eine Float-Zahl groesser 0
	 */
	public void setFontSize(float size)
	{
		if(size >0)
			this.fontSize = size;
	}
	/**
	 * setzt die Schriftgroeße fuer die Uberschriften.
	 * @param size eine Float-Zahl groesser 0
	 */
	public void setCaptionFontSize(float size)
	{
		if(size>0)
			this.captionFontSize = size;
	}
}
