/*
-> This "DataReader" utility file is responsible for reading data from "Opencart_LoginData" Excel file.
   With these data, we perform Login Automation ("LoginDDTExcel.feature").
*/

package utilities;

import java.io.FileInputStream;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

import org.apache.poi.ss.usermodel.Cell;
import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.xssf.usermodel.XSSFSheet;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;


public class DataReader
{
	public static HashMap <String,String> storeValues = new HashMap();

	public static List<HashMap <String,String>> data (String filepath, String sheetName)  // "filepath" => path of "Opencart_LoginData" Excel file.
	{
		List<HashMap<String, String>> myData = new ArrayList<>();

		try
		{
			FileInputStream fis = new FileInputStream(filepath);
			XSSFWorkbook workbook = new XSSFWorkbook(fis);
			XSSFSheet sheet = workbook.getSheet(sheetName);
			Row HeaderRow = sheet.getRow(0);

			for (int i = 1; i < sheet.getPhysicalNumberOfRows(); i++)
			{
				Row currentRow = sheet.getRow(i);
				HashMap <String,String> currentHash = new HashMap<String, String>();

				for (int j = 0; j < currentRow.getPhysicalNumberOfCells(); j++)
				{
					Cell currentCell = currentRow.getCell(j);

					switch (currentCell.getCellType())
					{
						case STRING:
							currentHash.put(HeaderRow.getCell(j).getStringCellValue(), currentCell.getStringCellValue());
							break;
					}
				}
				myData.add(currentHash);
			}

			fis.close();

		}

		catch (Exception e)
		{
			e.printStackTrace();
		}

		return myData;
	}

}