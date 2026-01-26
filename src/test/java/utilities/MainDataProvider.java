package utilities;

import org.testng.annotations.DataProvider;

import java.io.IOException;

public class MainDataProvider {

    @DataProvider(name="dp")
    public Object [][] getData() throws IOException
    {
        String path=".\\testData\\testData.xlsx";//taking xl file from testData

        ExcelUtility xlutil=new ExcelUtility(path);//creating an object for XLUtility

        int totalrows=xlutil.getRowCount("Sheet1");
        int totalcols=xlutil.getCellCount("Sheet1",1);

        Object data[][]=new String[totalrows][totalcols];//created for two dimension array which can store the data user and password

        for(int i=1;i<=totalrows;i++)  //1   //read the data from xl storing in two deminsional array
        {
            for(int j=0;j<totalcols;j++)  //0    i is rows j is col
            {
                data[i-1][j]= xlutil.getCellData("Sheet1",i, j);  //1,0
            }
        }
        return data;//returning two dimension array

    }

    @DataProvider(name="resultPageCheck")
    Object[][] resultPageCheckData() throws Exception{
        Object[][] obj={
                {"30", "70", "120/30"}
        };
        return obj;
    }
}
