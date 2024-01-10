package com.qa.opencart.constants;

import java.util.Arrays;
import java.util.List;

public class AppConstant {

	public static final String LOGIN_PAGE_TITLE = "Account Login";
	public static final String ACCOUNTS_PAGE_TITLE = "My Account";
	public static final String REGISTER_LINK = "Register";
	public static final String REGISTER_SUCCESS_MSG = "Your Account Has Been Created!";

	public static final int SHORT_DEFAULT_WAIT = 5;
	public static final int MEDIUM_DEFAULT_WAIT = 10;
	public static final int LONG_DEFAULT_WAIT = 20;

	public static final int POLLING_DEFAULT_WAIT = 2;
	public static final String CONTACT_DETAILS = "";
	public static final String SHEET_NAME ="RegisterUser";

	public static final List<String> LOGIN_MENU_ITEMS = Arrays.asList("Desktops","Laptops & Notebooks","Components","Tablets","Software","Phones & PDAs","Cameras","MP3 Players");

	public static final List<String> ACCOUNT_OPTIONS = Arrays.asList(
			"My Account",
			"Edit Account",
			"Password",
			"Addres Book",
			"Wish List",
			"Order History",
			"Downloads",
			"Recurring Payments",
			"Reward Points",
			"Returns",
			"Transactions",
			"Newsletter",
			"Logout");
}

