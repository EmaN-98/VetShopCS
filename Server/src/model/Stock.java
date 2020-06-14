package model;

import java.io.Serializable;
import java.util.List;

public class Stock implements Serializable{

	private static final long serialVersionUID = 1L;
	private int itemId;
	private String item;
	private int itemQuantity;
	public Stock(int itemId, String item, int itemQuantity) {
		super();
		this.itemId = itemId;
		this.item = item;
		this.itemQuantity = itemQuantity;
	}
	public Stock(String item, int itemQuantity) {
		super();
		this.item = item;
		this.itemQuantity = itemQuantity;
	}
	public Stock() {
		super();
	}
	public int getItemId() {
		return itemId;
	}
	public void setItemId(int itemId) {
		this.itemId = itemId;
	}
	public String getItem() {
		return item;
	}
	public void setItem(String item) {
		this.item = item;
	}
	public int getItemQuantity() {
		return itemQuantity;
	}
	public void setItemQuantity(int itemQuantity) {
		this.itemQuantity = itemQuantity;
	}
	
	public static String[] getHeader(){
		String []head=new String[3];
		head[0]="itemId";
		head[1]="item";
	 	head[2]="itemQuantity";
		return head;
	}
	
	public static Object[][] getData(List<Object> list){
		Object [][]data=new Object[list.size()][3];
		int i=0;
		for(Object o:list){
			Stock s=(Stock)o;
			data[i][0]=s.getItemId();
			data[i][1]=s.getItem();
			data[i][2]=s.getItemQuantity();
			i++;
		}
		return data;
	}
	
	
	@Override
	public String toString() {
		return "Stock [itemId=" + itemId + ", item=" + item + ", itemQuantity=" + itemQuantity + "]\n";
	}
	
}
