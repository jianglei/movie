package com.wuxianmeihao.utils;

public class categoryUtil {
	public static String getCategory(Integer category) {
		String categorys = null;
		if (null != category) {
			if (category.equals(Constants.CATEGORY_DZDY)) {
				categorys = Constants.Category.DZDY;
			} else if (category.equals(Constants.CATEGORY_XGDSJ)) {
				categorys = Constants.Category.XGDSJ;
			} else if (category.equals(Constants.CATEGORY_OMDSJ)) {
				categorys = Constants.Category.OMDSJ;
			} else if (category.equals(Constants.CATEGORY_GCDSJ)) {
				categorys = Constants.Category.GCDSJ;
			} else if (category.equals(Constants.CATEGORY_HGDSJ)) {
				categorys = Constants.Category.HGDSJ;
			} else if (category.equals(Constants.CATEGORY_RBDSJ)) {
				categorys = Constants.Category.RBDSJ;
			} else if (category.equals(Constants.CATEGORY_HWDSJ)) {
				categorys = Constants.Category.HWDSJ;
			} else if (category.equals(Constants.CATEGORY_KTDM)) {
				categorys = Constants.Category.KTDM;
			} else if (category.equals(Constants.CATEGORY_KBDY)) {
				categorys = Constants.Category.KBDY;
			} else if (category.equals(Constants.CATEGORY_JQDY)) {
				categorys = Constants.Category.JQDY;
			} else if (category.equals(Constants.CATEGORY_DZDY)) {
				categorys = Constants.Category.DZDY;
			} else if (category.equals(Constants.CATEGORY_XJDY)) {
				categorys = Constants.Category.XJDY;
			} else if (category.equals(Constants.CATEGORY_JLDY)) {
				categorys = Constants.Category.JLDY;
			} else if (category.equals(Constants.CATEGORY_AQDY)) {
				categorys = Constants.Category.AQDY;
			} else if (category.equals(Constants.CATEGORY_KHDY)) {
				categorys = Constants.Category.KHDY;
			} else if (category.equals(Constants.CATEGORY_ZZDY)) {
				categorys = Constants.Category.ZZDY;
			}
		}

		return categorys;
	}
}
