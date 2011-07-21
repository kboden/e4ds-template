package ch.ralscha.e4ds.web;

import java.io.OutputStream;
import java.util.List;
import java.util.Set;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.poi.hssf.usermodel.HSSFClientAnchor;
import org.apache.poi.hssf.usermodel.HSSFPatriarch;
import org.apache.poi.hssf.usermodel.HSSFSheet;
import org.apache.poi.hssf.usermodel.HSSFWorkbook;
import org.apache.poi.ss.usermodel.Cell;
import org.apache.poi.ss.usermodel.CellStyle;
import org.apache.poi.ss.usermodel.Comment;
import org.apache.poi.ss.usermodel.CreationHelper;
import org.apache.poi.ss.usermodel.Font;
import org.apache.poi.ss.usermodel.IndexedColors;
import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.ss.usermodel.Sheet;
import org.apache.poi.ss.usermodel.Workbook;
import org.joda.time.format.ISODateTimeFormat;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.stereotype.Controller;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import ch.ralscha.e4ds.entity.LoggingEvent;
import ch.ralscha.e4ds.entity.LoggingEventException;
import ch.ralscha.e4ds.entity.LoggingEventProperty;
import ch.ralscha.e4ds.repository.LoggingEventRepository;

@Controller
public class LoggingEventExport {

	@Autowired
	private LoggingEventRepository loggingEventRepository;
	
	@Transactional(readOnly = true)
	@RequestMapping(value = "/loggingEventExport.xls", method = RequestMethod.GET)
	@PreAuthorize("hasRole('ROLE_ADMIN')")
	public void loggingEventExport(HttpServletRequest request, HttpServletResponse response) throws Exception {

		response.setContentType("application/vnd.ms-excel");
		response.addHeader("Content-disposition", "attachment;filename=logs.xls");
		OutputStream out = response.getOutputStream();

		Workbook workbook = new HSSFWorkbook();

		CreationHelper createHelper = workbook.getCreationHelper();

		Font font = workbook.createFont();
		Font titleFont = workbook.createFont();

		font.setColor(IndexedColors.BLACK.getIndex());
		font.setFontName("Arial");
		font.setFontHeightInPoints((short) 10);
		font.setBoldweight(Font.BOLDWEIGHT_NORMAL);

		titleFont.setColor(IndexedColors.BLACK.getIndex());
		titleFont.setFontName("Arial");
		titleFont.setFontHeightInPoints((short) 10);
		titleFont.setBoldweight(Font.BOLDWEIGHT_BOLD);

		CellStyle normalStyle = workbook.createCellStyle();
		normalStyle.setFont(font);

		CellStyle titleStyle = workbook.createCellStyle();
		titleStyle.setFont(titleFont);

		Sheet sheet = workbook.createSheet("Logs");
		HSSFPatriarch patr = ((HSSFSheet) sheet).createDrawingPatriarch();

		Row row = sheet.createRow(0);
		createCell(row, 0, "ID", titleStyle, createHelper);
		createCell(row, 1, "Timestamp", titleStyle, createHelper);
		createCell(row, 2, "User", titleStyle, createHelper);
		createCell(row, 3, "IP", titleStyle, createHelper);
		createCell(row, 4, "Message", titleStyle, createHelper);
		createCell(row, 5, "Level", titleStyle, createHelper);
		createCell(row, 6, "CallerClass", titleStyle, createHelper);
		createCell(row, 7, "CallerLine", titleStyle, createHelper);

		List<LoggingEvent> events = loggingEventRepository.findAll();
		int rowNo = 1;
		for (LoggingEvent event : events) {
			row = sheet.createRow(rowNo);
			rowNo++;
		
			Cell cell = row.createCell(0);
			cell.setCellType(Cell.CELL_TYPE_NUMERIC);
			cell.setCellValue(event.getEventId());
			cell.setCellStyle(normalStyle);

			cell = row.createCell(1);
			cell.setCellType(Cell.CELL_TYPE_STRING);
			cell.setCellValue(ISODateTimeFormat.dateTime().print(event.getTimestmp().longValue()));
			cell.setCellStyle(normalStyle);

			String userName = null;
			String ip = null;
			
			Set<LoggingEventProperty> properties = event.getLoggingEventProperty();
			for (LoggingEventProperty prop : properties) {
				if ("userName".equals(prop.getId().getMappedKey())) {
					userName = prop.getMappedValue();
				} else if ("ip".equals(prop.getId().getMappedKey())) {
					ip = prop.getMappedValue();
				}
			}
			
			if (userName != null) {
				cell = row.createCell(2);
				cell.setCellType(Cell.CELL_TYPE_STRING);
				cell.setCellValue(userName);
				cell.setCellStyle(normalStyle);
			}
			
			if (ip != null) {
				cell = row.createCell(3);
				cell.setCellType(Cell.CELL_TYPE_STRING);
				cell.setCellValue(ip);
				cell.setCellStyle(normalStyle);
			}
			
			cell = row.createCell(4);
			cell.setCellType(Cell.CELL_TYPE_STRING);
			cell.setCellValue(event.getFormattedMessage());
			cell.setCellStyle(normalStyle);

			if (!event.getLoggingEventException().isEmpty()) {
				
				StringBuilder sb = new StringBuilder();
				for (LoggingEventException loggingEventException : event.getLoggingEventException()) {
					sb.append(loggingEventException.getTraceLine());
					sb.append("\n");
				}

				Comment comment = patr.createComment(new HSSFClientAnchor(0, 0, 0, 0, (short) 4, 2, (short) 13, 33));
				comment.setString(createHelper.createRichTextString(sb.toString()));
				comment.setAuthor(null);
				cell.setCellComment(comment);
			}

			cell = row.createCell(5);
			cell.setCellType(Cell.CELL_TYPE_STRING);
			cell.setCellValue(event.getLevelString());
			cell.setCellStyle(normalStyle);

			cell = row.createCell(6);
			cell.setCellType(Cell.CELL_TYPE_STRING);
			cell.setCellValue(event.getCallerClass());
			cell.setCellStyle(normalStyle);

			cell = row.createCell(7);
			cell.setCellType(Cell.CELL_TYPE_STRING);
			cell.setCellValue(event.getCallerLine());
			cell.setCellStyle(normalStyle);
		}

		sheet.autoSizeColumn(0);
		sheet.autoSizeColumn(1);
		sheet.autoSizeColumn(2);
		sheet.autoSizeColumn(3);
		sheet.setColumnWidth(4, 10000);
		sheet.autoSizeColumn(5);
		sheet.autoSizeColumn(6);
		sheet.autoSizeColumn(7);

		workbook.write(out);

		out.close();

	}

	private void createCell(Row row, int column, String value, CellStyle style, CreationHelper createHelper) {
		Cell cell = row.createCell(column);
		cell.setCellValue(createHelper.createRichTextString(value));
		cell.setCellStyle(style);
	}

}
