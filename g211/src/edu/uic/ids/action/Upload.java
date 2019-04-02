package edu.uic.ids.action;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileOutputStream;
import java.io.FileReader;
import java.io.IOException;
import java.sql.ResultSet;
import java.sql.ResultSetMetaData;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Map;
import java.util.Scanner;

import javax.annotation.PostConstruct;
import javax.faces.application.FacesMessage;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.SessionScoped;
import javax.faces.context.FacesContext;

import org.apache.commons.io.FilenameUtils;
import org.apache.myfaces.custom.fileupload.UploadedFile;

import edu.uic.ids.model.DatabaseAccessInfoBean;
import edu.uic.ids.model.MathManagedBean;
import edu.uic.ids.model.StatsBean;
import edu.uic.ids.model.StatsManagedBean;

@ManagedBean(name = "upload")
@SessionScoped
public class Upload {
	private DatabaseAccessInfoBean dbBean;	
	private String dataSetName;	
	private String fileContentType;
	private int noOfColumns;
	private boolean fileImport;
	private UploadedFile uploadedFile;
	private String fileName;
	private long fileSize;
	private String contentsUploadedFile;
	public StatsBean getStatsBean() {
		return statsBean;
	}

	public void setStatsBean(StatsBean statsBean) {
		this.statsBean = statsBean;
	}

	private boolean fileImportError;
	private String filePath;
	private String tmpFileName;
	private String header;
	private String fileType;
	private String fileFormat;
	private FacesContext facesContext;
	private DbAccess dbAccess;
/*begin of changes*/
	private DatabaseOperations databaseOperations;
	private StatsManagedBean statsManagedBean;
	private MathManagedBean mathManagedBean;
	private StatsBean statsBean;
	/*end of changes*/

	@PostConstruct
	public void init() {
		Map<String, Object> m = FacesContext.getCurrentInstance().getExternalContext().getSessionMap();
		dbAccess = (DbAccess) m.get("dbAccess");
	}

	public DatabaseOperations getDatabaseOperations() {
		return databaseOperations;
	}

	public void setDatabaseOperations(DatabaseOperations databaseOperations) {
		this.databaseOperations = databaseOperations;
	}

	public StatsManagedBean getStatsManagedBean() {
		return statsManagedBean;
	}

	public void setStatsManagedBean(StatsManagedBean statsManagedBean) {
		this.statsManagedBean = statsManagedBean;
	}

	public MathManagedBean getMathManagedBean() {
		return mathManagedBean;
	}

	public void setMathManagedBean(MathManagedBean mathManagedBean) {
		this.mathManagedBean = mathManagedBean;
	}

	public String processFile() {
		String status = "FAIL";
		contentsUploadedFile = null;
		facesContext = FacesContext.getCurrentInstance();
		filePath = facesContext.getExternalContext().getRealPath("/temp");
		File tempFile = null;
		FileOutputStream fos = null;
//		int n = 0;
		fileImport = false;
		fileImportError = true;
		try {

			fileName = uploadedFile.getName();
			String baseName = FilenameUtils.getBaseName(fileName);
			fileSize = uploadedFile.getSize();
			fileContentType = uploadedFile.getContentType();
			contentsUploadedFile = new String(uploadedFile.getBytes());
			tempFile =new File(filePath + "/" + fileName);

			System.out.println(contentsUploadedFile);
	
			String eachRow = "";
			List<Integer> errList = new ArrayList<Integer>();
			int counter = 0;
	/*begin new changes 27//11//2018*/
			String[] lines = contentsUploadedFile.split(System.getProperty("line.separator"));	
			List<String> split;
			ArrayList<String> splitList = new ArrayList<String>();
			int i ;
			String string="";
			ArrayList<String> columnSelected;
			for(i=0;i<lines.length;i++)
			{
				eachRow=lines[i];
				if(eachRow.toLowerCase().contains("tablelist"))
				{
					string= statsBean.getTables();
					
				}
				if(eachRow.toLowerCase().contains("use"))
				{
					
					statsBean.setTableSelected(eachRow.substring(3));
					string= statsBean.getColumnNames();
				}
				if(eachRow.toLowerCase().contains("select"))
				{
					eachRow=eachRow.substring(7);
//					split= (ArrayList<String>) Arrays.asList(eachRow.split("\t"));
					splitList.addAll(Arrays.asList(eachRow.split("\t")));
//					split.remove("select");
					/*changes from 10*/
					columnSelected = new ArrayList<String>();
					columnSelected = splitList;
//					splitList = null;
					StringBuilder rString = new StringBuilder();
					for (String each : columnSelected) {
						rString.append(",").append(each);
					}
					String sqlQuery = rString.toString();
					int index = sqlQuery.indexOf(",");
					
					sqlQuery = sqlQuery.substring(index + 1, sqlQuery.length());
					sqlQuery = "select " + sqlQuery + " from "+dbBean.getDatabase()+"." + statsBean.getTableSelected();
					/*changes from 10*/
//					String sqlQuery= "select"+split+"from"+statsBean.getTableSelected();					
					ResultSet resultSet = dbAccess.fetchColumnNames(sqlQuery);
					if (resultSet != null) {
						splitList.clear();
						ResultSetMetaData resultSetmd = (ResultSetMetaData) resultSet.getMetaData();
						int columnCount = resultSetmd.getColumnCount();
						for (int j = 1; j <= columnCount; j++) {
							String name = resultSetmd.getColumnName(j);
							String datatype = resultSetmd.getColumnTypeName(j);
//							columns.add(name);
							splitList.add(name + " " + datatype);
						}
					}
					statsBean.setColumnSelected(splitList);
					statsBean.generateReport();
				}
			}
			System.out.println(string);
	
//			noOfRows = n;
			fileImport = true;
			//scan.close();
		} catch (IOException inpopEx) {
			FacesMessage message1 = new FacesMessage(inpopEx.getMessage());
			FacesContext.getCurrentInstance().addMessage(null, message1);
			return "FAIL";
		} catch (Exception e) {
			FacesMessage message1 = new FacesMessage("ERROR OCCURED");
			FacesContext.getCurrentInstance().addMessage(null, message1);

			return "FAIL";
		}
		return status;
	}

	

	
	
	public UploadedFile getUploadedFile() {
		return uploadedFile;
	}

	public void setUploadedFile(UploadedFile uploadedFile) {
		this.uploadedFile = uploadedFile;
	}

	

	public String getFileName() {
		return fileName;
	}

	public void setFileName(String fileName) {
		this.fileName = fileName;
	}

	public long getFileSize() {
		return fileSize;
	}

	public void setFileSize(long fileSize) {
		this.fileSize = fileSize;
	}

	public String getFileContentType() {
		return fileContentType;
	}

	public void setFileContentType(String fileContentType) {
		this.fileContentType = fileContentType;
	}



	public int getNumberColumns() {
		return noOfColumns;
	}

	public void setNumberColumns(int numberColumns) {
		this.noOfColumns = numberColumns;
	}

	public String getUploadedFileContents() {
		return contentsUploadedFile;
	}

	public void setUploadedFileContents(String uploadedFileContents) {
		this.contentsUploadedFile = uploadedFileContents;
	}

	public boolean isFileImport() {
		return fileImport;
	}

	public void setFileImport(boolean fileImport) {
		this.fileImport = fileImport;
	}

	public boolean isFileImportError() {
		return fileImportError;
	}

	public void setFileImportError(boolean fileImportError) {
		this.fileImportError = fileImportError;
	}

	public String getFilePath() {
		return filePath;
	}

	public void setFilePath(String filePath) {
		this.filePath = filePath;
	}

	public String getTempFileName() {
		return tmpFileName;
	}

	public void setTempFileName(String tempFileName) {
		this.tmpFileName = tempFileName;
	}

	public FacesContext getFacesContext() {
		return facesContext;
	}

	public String getHeader() {
		return header;
	}

	public void setHeader(String header) {
		this.header = header;
	}

	public String getFileType() {
		return fileType;
	}

	public void setFileType(String fileType) {
		this.fileType = fileType;
	}

	public String getFileFormat() {
		return fileFormat;
	}

	public void setFileFormat(String fileFormat) {
		this.fileFormat = fileFormat;
	}

	public void setFacesContext(FacesContext facesContext) {
		this.facesContext = facesContext;
	}

	public String getDataSetName() {
		return dataSetName;
	}

	public void setDataSetName(String dataSetName) {
		this.dataSetName = dataSetName;
	}

	public DbAccess getDbAccess() {
		return dbAccess;
	}

	public void setDbAccess(DbAccess dbAccess) {
		this.dbAccess = dbAccess;
	}

	

	public DatabaseAccessInfoBean getDbBean() {
		return dbBean;
	}

	public void setDbBean(DatabaseAccessInfoBean dbBean) {
		this.dbBean = dbBean;
	}


}