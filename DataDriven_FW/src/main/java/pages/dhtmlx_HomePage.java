package pages;

import java.util.List;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

public class dhtmlx_HomePage {
	
	private WebDriver driver;
	
	@FindBy(xpath="//div[contains(@class,'dhxtree_material')]/div[1]/table[1]/tbody[1]/tr[2]/td[2]/table[1]/tbody[1]/tr[1]/td[1]/div[1]")
	WebElement main_hierarchy;
	@FindBy(xpath="//div[contains(@class,'dhxtree_material')]/div[1]/table[1]/tbody[1]/tr[2]/td[2]/table[1]/tbody[1]/tr[2]/td[2]/table[1]/tbody[1]/tr[1]/td[1]/div[1]")
	WebElement sub_hierarchy;
	@FindBy(xpath="//*[contains(text(),'products')]")
	WebElement products_Table;
	@FindBy(xpath="//div[@class='objbox']/table/tbody/tr")
    List<WebElement> products_Table_data;
	@FindBy(xpath="//div[@class='objbox']/table/tbody")
    WebElement products_Table_content;

	public dhtmlx_HomePage(WebDriver driver)
	{
		
		this.driver=driver;
		PageFactory.initElements(driver, this);
	}
	
	public void click_main_hierarchy()
	{
		main_hierarchy.click();
	}
	
	public void click_sub_hierarchy()
	{
		sub_hierarchy.click();
	}
	public void click_products_Table()
	{
		 products_Table.click();
	}
	
	public List<WebElement> products_Table_data()
	{
		return products_Table_data;
	}
	
	public String getproducts_Table_Data(int rowindex,int colindex)
	{
		return products_Table_content.findElement(By.xpath("tr[ "+ rowindex + "]/td[" + colindex + "]")).getText();
		
	}
	
	

}
