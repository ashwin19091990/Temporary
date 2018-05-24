package DataLoader;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;

import org.apache.poi.hssf.usermodel.HSSFCell;
import org.apache.poi.hssf.usermodel.HSSFRow;
import org.apache.poi.hssf.usermodel.HSSFSheet;
import org.apache.poi.hssf.usermodel.HSSFWorkbook;
import org.apache.poi.ss.usermodel.Cell;
import org.apache.poi.ss.usermodel.Row;

public class FeederMechanism {

	static int rownum = -1;

	public void toEnterDataIntoExcel(ArrayList<String> namelist) throws IOException {

		int row = 0;
		int cell = 0;
		String line = null;

		BufferedReader bufferedreader = new BufferedReader(new FileReader("sample.txt"));
		String FilePath = "demo.xls";
		FileInputStream fs = new FileInputStream(FilePath);
		HSSFWorkbook workbook = new HSSFWorkbook(fs);
		HSSFSheet sheet = workbook.getSheet("UserEntry");
		HSSFRow headerrow = sheet.createRow(row);
		// Writing header information
		while ((line = bufferedreader.readLine()) != null) {

			HSSFCell cellinfo = headerrow.createCell(cell);
			cellinfo.setCellValue(line);

			cell++;

		}
		if (FeederMechanism.rownum != -1) {

			System.out.println("creating data");
			HSSFRow writerow = sheet.getRow(rownum);

			if (writerow == null) {

				writerow = sheet.createRow(rownum);
			}

			for (int createcell = 0; createcell < namelist.size(); createcell++) {

				HSSFCell createcelldata = writerow.getCell(createcell);

				if (createcelldata == null) {

					createcelldata = writerow.createCell(createcell);
				}

				createcelldata.setCellType(createcelldata.CELL_TYPE_BLANK);
				createcelldata.setCellValue(namelist.get(createcell));

			}
			rownum = -1;
		}

		FileOutputStream out = new FileOutputStream("demo.xls");
		workbook.write(out);
		//workbook.close();
		out.close();
		fs.close();
		bufferedreader.close();

	}

	public int tochecktheemployeeidvalidity(ArrayList<String> namelist) throws IOException {
		
		String employeeid = namelist.get(0);
		int getrow = 1;
		String FilePath = "demo.xls";
		System.out.println(new File(FilePath).getAbsolutePath());
		FileInputStream fs = new FileInputStream(FilePath);
		HSSFWorkbook workbook = new HSSFWorkbook(fs);
		HSSFSheet getsheet = workbook.getSheet("UserEntry");
		int lastrow = getsheet.getLastRowNum();

		while (getrow <= lastrow) {
			Row employeerow = getsheet.getRow(getrow);
			Cell cell = employeerow.getCell(0);
			cell.setCellType(cell.CELL_TYPE_STRING);
			String cellvalue = cell.getStringCellValue();
			
			if (employeeid != "" && employeeid != null && cellvalue.equals(employeeid)) {
				System.out.println(cellvalue +" -------------- "+employeeid);
				rownum = getrow;
				//System.out.println("Row num value------------------------" + rownum);
				
			}

			getrow++;
		}

		//workbook.close();
		return rownum;
		
	}
	
	public ArrayList toRetriveDefaulter() throws IOException{
		
		String FilePath = "demo.xls";
		FileInputStream fs = new FileInputStream(FilePath);
		HSSFWorkbook workbook = new HSSFWorkbook(fs);
		HSSFSheet sheet = workbook.getSheet("UserEntry");
		
		int row = 1;
		int cell = 1;
		int lastrow = sheet.getLastRowNum();
		ArrayList<String> employeeidlist = new ArrayList<String>();
		while (row <= lastrow) {
			
			Row employeenamerow = sheet.getRow(row);
			Cell employeenamecell = employeenamerow.getCell(cell);
			//employeenamecell.setCellType(employeenamecell.CELL_TYPE_STRING);
			
			
			if(employeenamecell == null){
			
			Cell employeerowcell = employeenamerow.getCell(0);
			employeerowcell.setCellType(employeerowcell.CELL_TYPE_STRING);
			String employeeids = employeerowcell.getStringCellValue();
			employeeidlist.add(employeeids);
			}
			
			row ++;
		}
		
		return employeeidlist;
	}
	

	public static void main(String[] args) throws IOException {

		ArrayList<String> namelist = new ArrayList<String>();
		namelist.add("2");
		namelist.add("593516");
		namelist.add("593516");
		namelist.add("593516");
		namelist.add("593516");
		namelist.add("585858");

		ArrayList<String> namelist1 = new ArrayList<String>();
		namelist1.add("1");
		namelist1.add("593516");
		namelist1.add("593516");
		namelist1.add("593516");
		namelist1.add("593516");
		namelist1.add("585854");

		System.out.println("Hello");
		FeederMechanism fm = new FeederMechanism();
		fm.tochecktheemployeeidvalidity(namelist);
		fm.toEnterDataIntoExcel(namelist);
		fm.tochecktheemployeeidvalidity(namelist1);
		fm.toEnterDataIntoExcel(namelist1);

	}

}
