package john.EDU;

import java.io.FileOutputStream;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.Statement;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;

import org.apache.poi.ss.usermodel.CellStyle;
import org.apache.poi.ss.usermodel.CreationHelper;
import org.apache.poi.xssf.usermodel.XSSFCell;
import org.apache.poi.xssf.usermodel.XSSFRow;
import org.apache.poi.xssf.usermodel.XSSFSheet;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;

import john.MiHEALTH.MiHEALTHproperties;

public class Student {
	/**從教育部匯出學生資料(xlsx)(MiHEALTH匯入格式)*/
	public static void exportExcel(String excelPath) throws Exception {
		String connectionString = MiHEALTHproperties.getEDU_ConnectionString();
//				"jdbc:sqlserver://localhost:1433;" + "database=Health;" + "user=sa;"
//				+ "password=john;"
//				+ "trustServerCertificate=false;"
//				;

		// Declare the JDBC objects.
		Connection connection = null;
		Statement statement = null;
		ResultSet resultSet = null;
		XSSFWorkbook wb = null;
		try {
			connection = DriverManager.getConnection(connectionString);

			// Create and execute a SELECT SQL statement.
			String selectSql = "select * from Health.dbo.St;";
			statement = connection.createStatement();
			resultSet = statement.executeQuery(selectSql);

			wb = new XSSFWorkbook();
			CellStyle cellStyle = wb.createCellStyle();
			CreationHelper createHelper = wb.getCreationHelper();
			cellStyle.setDataFormat(
			    createHelper.createDataFormat().getFormat("yyyy/m/d"));
			
			XSSFSheet sheet = wb.createSheet("Excel Sheet");
			XSSFRow rowhead = sheet.createRow((short) 0);
			rowhead.createCell((short) 0).setCellValue("id");
			rowhead.createCell((short) 1).setCellValue("GuyID");
			rowhead.createCell((short) 2).setCellValue("PID");
			rowhead.createCell((short) 3).setCellValue("RFID");
			rowhead.createCell((short) 4).setCellValue("Guy");
			rowhead.createCell((short) 5).setCellValue("SexID");
			rowhead.createCell((short) 6).setCellValue("Years");
			rowhead.createCell((short) 7).setCellValue("Grade");
			rowhead.createCell((short) 8).setCellValue("ClassID");
			rowhead.createCell((short) 9).setCellValue("Seat");
			rowhead.createCell((short) 10).setCellValue("Birthday");
			rowhead.createCell((short) 11).setCellValue("Dad");
			rowhead.createCell((short) 12).setCellValue("Mom");
			rowhead.createCell((short) 13).setCellValue("Guardian");
			rowhead.createCell((short) 14).setCellValue("Zip");
			rowhead.createCell((short) 15).setCellValue("Tel1");
			rowhead.createCell((short) 16).setCellValue("Address");
			rowhead.createCell((short) 17).setCellValue("EmergencyCall");
			rowhead.createCell((short) 18).setCellValue("NewClassID");
			rowhead.createCell((short) 19).setCellValue("NewSeat");
			rowhead.createCell((short) 20).setCellValue("Blood");

			int index = 1;
			while (resultSet.next()) {

				XSSFRow row = sheet.createRow((short) index);
				row.createCell((short) 0).setCellValue(index);
				row.createCell((short) 1).setCellValue(resultSet.getString(1));
				row.createCell((short) 2).setCellValue(resultSet.getString(1));
				row.createCell((short) 3).setCellValue("");
				row.createCell((short) 4).setCellValue(resultSet.getString(2));
				int edu_SexID = resultSet.getInt(3);//男1女2
				int miHEALTH_SexID = 1;//男1女0
				if((edu_SexID-1)==0)miHEALTH_SexID=1;
				else if((edu_SexID-1)==1)miHEALTH_SexID=0;
				row.createCell((short) 5).setCellValue(miHEALTH_SexID);
				row.createCell((short) 6).setCellValue(MiHEALTHproperties.getNowYear());
				int edu_Years = resultSet.getInt(4);
				int miHEALTH_ClassID = MiHEALTHproperties.getNowYear()-edu_Years+1;
				row.createCell((short) 7).setCellValue(String.valueOf(miHEALTH_ClassID));
				row.createCell((short) 8).setCellValue(resultSet.getString(5));
				row.createCell((short) 9).setCellValue(resultSet.getString(6));
				XSSFCell dateCell = row.createCell((short) 10);
				dateCell.setCellValue(parseDate(resultSet.getString(7)));
				dateCell.setCellStyle(cellStyle);
				row.createCell((short) 11).setCellValue(resultSet.getString(8));
				row.createCell((short) 12).setCellValue(resultSet.getString(9));
				row.createCell((short) 13).setCellValue(resultSet.getString(10));
				row.createCell((short) 14).setCellValue(resultSet.getString(11));
				row.createCell((short) 15).setCellValue(resultSet.getString(12));
				row.createCell((short) 16).setCellValue(resultSet.getString(13));
				row.createCell((short) 17).setCellValue(resultSet.getString(14));
				row.createCell((short) 18).setCellValue(resultSet.getString(15));
				row.createCell((short) 19).setCellValue(resultSet.getString(16));
				row.createCell((short) 20).setCellValue(resultSet.getString(17));
				index++;
			}
			FileOutputStream fileOut = new FileOutputStream(excelPath);//"excelFile.xlsx"
			wb.write(fileOut);
			fileOut.close();
			resultSet.close();
			connection.close();
			wb.close();
		} catch (Exception e) {
			e.printStackTrace();
			throw e;
		} finally {
			// Close the connections after the data has been handled.
			if (resultSet != null)
				try {
					resultSet.close();
				} catch (Exception e) {
				}
			if (statement != null)
				try {
					statement.close();
				} catch (Exception e) {
				}
			if (connection != null)
				try {
					connection.close();
				} catch (Exception e) {
				}
			if (wb != null)
				try {
					wb.close();
			} catch (Exception e) {
			}
		}
	}
	private static Date parseDate(String oldDateString) throws ParseException{
		final String OLD_FORMAT = "yyyy-MM-dd HH:mm:ss";
		SimpleDateFormat sdf = new SimpleDateFormat(OLD_FORMAT);
		Date d = sdf.parse(oldDateString);
		return d;
	}
	public static void main(String[] args) throws Exception{
		exportExcel(MiHEALTHproperties.getExcelPath());
	}
}
