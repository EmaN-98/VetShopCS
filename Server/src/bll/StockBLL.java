package bll;

import java.util.List;
import operations.StockOp;

import model.Stock;




public class StockBLL {

	public static List<Object> viewStock() {

		return StockOp.viewAll();
	}

	public static boolean checkStock() {
		return StockOp.checkStock();
	}

	public static Stock updateStock(Stock s) {

		return StockOp.updateStock(s);
	}

	public static void decreaseQuantity() {
		StockOp.decreaseQuantity();
	}




}
