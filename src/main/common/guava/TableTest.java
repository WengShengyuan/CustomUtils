package main.common.guava;

import com.google.common.collect.HashBasedTable;
import com.google.common.collect.Table;

public class TableTest {
	public static void main(String[] args) {
		Table<String, Integer, String> aTable = HashBasedTable.create();

		for (char a = 'A'; a <= 'C'; ++a) {
			for (Integer b = 1; b <= 3; ++b) {
				/**
				 * Parameters: 
				 * rowKey 	row key that the value should be
				 * 			associated with 
				 * 
				 * columnKey column key that the value should be
				 *			 associated with 
				 * 
				 * value 	value to be associated with the
				 * 			specified keys Returns: the value previously associated with
				 * 			the keys, or null if no mapping existed for the keys
				 */
				aTable.put(Character.toString(a), b,
						String.format("%c%d", a, b));
			}
		}

		System.out.println(aTable.column(2));
		System.out.println(aTable.row("B"));
		System.out.println(aTable.get("B", 2));

		System.out.println(aTable.contains("D", 1));
		System.out.println(aTable.containsColumn(3));
		System.out.println(aTable.containsRow("C"));
		System.out.println(aTable.columnMap());
		System.out.println(aTable.rowMap());

		System.out.println(aTable.remove("B", 3));
		System.out.println(aTable.rowMap());
		System.out.println(aTable.columnMap());
	}

}
