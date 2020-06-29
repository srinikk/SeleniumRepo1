package tests;

import java.io.File;
import java.io.IOException;
import java.util.concurrent.TimeUnit;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.edge.EdgeDriver;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.ie.InternetExplorerDriver;
import org.testng.annotations.AfterTest;
import org.testng.annotations.BeforeTest;
import org.testng.annotations.Optional;
import org.testng.annotations.Parameters;
import org.testng.annotations.Test;

import pages.dhtmlx_HomePage;
import utils.ConfigurationRead;
import utils.ExcelXUtil;

public class ProductsCheck {
	
	public WebDriver driver;

	@Parameters
	@BeforeTest
	public void setupbrowser(@Optional("chrome") String browserName) throws IOException {
		String driversPath=System.getProperty("user.dir")+"\\src\\test\\resources\\drivers";
		if(browserName.equalsIgnoreCase("chrome")) {
			System.setProperty("webdriver.chrome.driver", driversPath+"\\chromedriver.exe");
			driver= new ChromeDriver();
		}
		else if(browserName.equalsIgnoreCase("firefox")) {
			System.setProperty("webdriver.gecko.driver", driversPath+"\\geckodriver.exe");
			driver= new FirefoxDriver();
		}
		else if(browserName.equalsIgnoreCase("edge")) {
			driver= new EdgeDriver();
		}
		else if(browserName.equalsIgnoreCase("ie")) {
			System.setProperty("webdriver.ie.driver", driversPath+"\\IEDriverServer.exe");
			driver= new InternetExplorerDriver();
		}
		
		driver.manage().window().maximize();
		driver.manage().timeouts().implicitlyWait(30, TimeUnit.SECONDS);
		ConfigurationRead read = new ConfigurationRead();
		
		driver.get(read.readProperties("URL"));
	}

	@Test
	public void getProductDetails() throws IOException, InterruptedException {
		
		String ExcelprodId=null;
		String ExcelprodName=null;
		String Excelprod_cartID=null;
		String WebprodId=null;
		String WebprodName=null;
		String Webprod_cartID=null;
		
		
		ConfigurationRead cf = new ConfigurationRead();

		

		dhtmlx_HomePage dh = new dhtmlx_HomePage(driver);
		dh.click_main_hierarchy();
		dh.click_sub_hierarchy();
		dh.click_products_Table();
		Thread.sleep(5000);
		int product_rowcount = dh.products_Table_data().size();
		

		
		ExcelXUtil excel = new ExcelXUtil(cf.readProperties("excel_path"));

		int excelrowcount = excel.getRowCount("Sheet1");

		

		
		for (int i = 1; i < excelrowcount; i++) {
			ExcelprodId = excel.getCellValue("Sheet1", i, 0);
			ExcelprodName=excel.getCellValue("Sheet1", i, 1);
			Excelprod_cartID=excel.getCellValue("Sheet1", i, 2);
			
			
			excel.writeCellValue("Sheet1", i, 3, "FAIL");

			for (int j = 1; j < product_rowcount; j++) {
				int extractrow = j + 1;

				WebprodId = dh.getproducts_Table_Data(extractrow, 1);
				WebprodName = dh.getproducts_Table_Data(extractrow, 2);
				Webprod_cartID= dh.getproducts_Table_Data(extractrow, 3);
				
				if(ExcelprodId.contains(WebprodId) && ExcelprodName.equalsIgnoreCase(WebprodName) && Excelprod_cartID.contains(Webprod_cartID) )
				{
					excel.writeCellValue("Sheet1", i, 3, "PASS");
					System.out.println("Pass "+WebprodId);
					break;
				}
				System.out.println("in for loop " +j);

			}
			
			
			

		}

	}

	@AfterTest
	public void Cleanup() {
		driver.quit();
	}

}
