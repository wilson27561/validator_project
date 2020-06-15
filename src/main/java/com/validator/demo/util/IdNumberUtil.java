package com.validator.demo.util;

import java.util.Arrays;

import org.apache.commons.lang3.StringUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class IdNumberUtil {

	private static final Logger LOGGER = LoggerFactory.getLogger(IdNumberUtil.class);

	public static boolean isValidNationalIndividual(String id) {
		if (StringUtils.isEmpty(id)) {
			return false;
		}
		String checkHead = "ABCDEFGHJKLMNPQRSTUVWXYZIO"; // 字母代號對照表
		if (id.length() == 10) {
			char[] c = id.toUpperCase().toCharArray(); // 建立 c 陣列，同時將s字串轉大寫後，轉成字元陣列放入 c 陣列
			int[] ID = new int[c.length]; // 建立一個運算用的整數陣列，空間為 c 的字元個數
			// 驗證首位字母是否合法 (該字元是否能在checkHead[]找到), 驗證第一位是否為 1 or 2 (1=男生, 2=女生)
			if (checkHead.indexOf(c[0]) == -1 || (c[1] != '1' && c[1] != '2')) {
				return false;
			} else {
				int sum = 0;
				ID[0] = checkHead.indexOf(c[0]) + 10; // 第一個英文字運算
				sum += ID[0] / 10; // .. 將商數加總 sum += ID[0]/10
				ID[0] %= 10; // .. 取餘數放回 ID[0] 以便之後的運算
				for (int i = 1; i < 10; i++) // 將身分證後9碼轉成整數型態 (ASCII碼-48)
					ID[i] = (int) c[i] - 48;
				for (int i = 0; i < 9; i++) { // 代入公式:
					ID[i] *= (9 - i); // 總和 sum += (ID[0])*9 + ID[1]*8 + ID[2]*7 + ... + ID[9]*1
					sum += ID[i];
				}
				// 檢查(10-sum%10)是否相等於檢查碼，且 sum%10(餘數)為0時，檢查碼為0 => (10-sum%10)%10
				if ((10 - sum % 10) % 10 == ID[9]) {
					return true;
				} else {
					return false;
				}
			}
		} else {
			return false;
		}
	}

	public static boolean isValidNationalLegalPerson(String id) {
		if (StringUtils.isEmpty(id) || id.length() != 8) {
			return false;
		}
		final int[] weight = { 1, 2, 1, 2, 1, 2, 4, 1 };
		boolean type2 = id.charAt(6) == '7';
		final char[] tebidArr = id.toCharArray();

		if (id.matches("^[0-9]{8}$")) {
			int sum = 0;
			for (int i = 0; i < 8; i++) {
				int tmp = Character.getNumericValue(tebidArr[i]) * weight[i];
				sum += (tmp / 10) + (tmp % 10);
			}
			if ((sum % 10) == 0 || (type2 && ((sum + 1) % 10 == 0))) {
				return true;
			}
		}
		return false;
	}

	public static boolean isValidForeignIndividual(String id) {

		if (StringUtils.isEmpty(id)) {
			return false;
		}

		final char[] pidCharArray = { 'A', 'B', 'C', 'D', 'E', 'F', 'G', 'H', 'I', 'J', 'K', 'L', 'M', 'N', 'O', 'P',
				'Q', 'R', 'S', 'T', 'U', 'V', 'W', 'X', 'Y', 'Z' };

		// 原身分證英文字應轉換為10~33，這裡直接作個位數*9+10
		final int[] pidIDInt = { 1, 10, 19, 28, 37, 46, 55, 64, 39, 73, 82, 2, 11, 20, 48, 29, 38, 47, 56, 65, 74, 83,
				21, 3, 12, 30 };

		// 原居留證第一碼英文字應轉換為10~33，十位數*1，個位數*9，這裡直接作[(十位數*1) mod 10] + [(個位數*9) mod 10]
		final int[] pidResidentFirstInt = { 1, 10, 9, 8, 7, 6, 5, 4, 9, 3, 2, 2, 11, 10, 8, 9, 8, 7, 6, 5, 4, 3, 11, 3,
				12, 10 };

		// 原居留證第二碼英文字應轉換為10~33，並僅取個位數*8，這裡直接取[(個位數*8) mod 10]
		final int[] pidResidentSecondInt = { 0, 8, 6, 4, 2, 0, 8, 6, 2, 4, 2, 0, 8, 6, 0, 4, 2, 0, 8, 6, 4, 2, 6, 0, 8,
				4 };

		id = id.toUpperCase();// 轉換大寫
		final char[] strArr = id.toCharArray();// 字串轉成char陣列
		int verifyNum = 0;

		/* 檢查身分證字號 */
		if (id.matches("[A-Z]{1}[1-2]{1}[0-9]{8}")) {
			// 第一碼
			verifyNum = verifyNum + pidIDInt[Arrays.binarySearch(pidCharArray, strArr[0])];
			// 第二~九碼
			for (int i = 1, j = 8; i < 9; i++, j--) {
				verifyNum += Character.digit(strArr[i], 10) * j;
			}
			// 檢查碼
			verifyNum = (10 - (verifyNum % 10)) % 10;

			return verifyNum == Character.digit(strArr[9], 10);
		}

		/* 檢查統一證(居留證)編號 */
		verifyNum = 0;
		if (id.matches("[A-Z]{1}[A-D]{1}[0-9]{8}")) {
			// 第一碼
			verifyNum += pidResidentFirstInt[Arrays.binarySearch(pidCharArray, strArr[0])];
			// 第二碼
			verifyNum += pidResidentSecondInt[Arrays.binarySearch(pidCharArray, strArr[1])];
			// 第三~八碼
			for (int i = 2, j = 7; i < 9; i++, j--) {
				verifyNum += Character.digit(strArr[i], 10) * j;
			}
			// 檢查碼
			verifyNum = (10 - (verifyNum % 10)) % 10;

			return verifyNum == Character.digit(strArr[9], 10);
		}

		return false;
	}

	public static boolean isValidForeignLegalPerson(String id) {
		return id.matches("[A-Z]{5}[0-9]{2}[A-Z]{2}");
	}

}
