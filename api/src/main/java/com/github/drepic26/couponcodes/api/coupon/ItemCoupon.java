package com.github.drepic26.couponcodes.api.coupon;

import java.util.HashMap;

public interface ItemCoupon extends Coupon {
	
	/**
	 * @return The items and amounts the coupon is for
	 */
	public HashMap<Integer, Integer> getIDs();

}